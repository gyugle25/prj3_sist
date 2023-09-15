package kr.co.sist.prj3.user.comment.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.sist.prj3.user.comment.dao.CommentDAO;
import kr.co.sist.prj3.user.comment.domain.CommentDomain;
import kr.co.sist.prj3.user.comment.domain.ReplyDomain;
import kr.co.sist.prj3.user.comment.vo.CommVO;
import kr.co.sist.prj3.user.comment.vo.ReplyVO;

/**
 * ��� ����
 * @author user
 *
 */
@Component
public class CommentService {

	@Autowired(required = false)
	private CommentDAO commDAO;

	/**
	 * ��� ��� - ajax
	 * @param cmVO
	 */
	public boolean addCommentService( CommVO cmVO ) {
		
		boolean success=false;
		success = commDAO.insertComment(cmVO)==1;
		return success;
		
	}//insertComment


	/**
	 * ������ ���� ����� ����
	 * @param comNum
	 * @return
	 */
	public boolean removeCommentService ( int comNum ) {
		
		int result = 0;
		result = commDAO.deleteComment(comNum);
		
		return result==1;
		
		
	}//deleteComment
	
	
	/**
	 * ������ �ִ� ���� ������ ������Ʈ
	 * @param comNum
	 * @return
	 */
	public boolean updateCommentService  (int comNum ) {
		
		int result = 0;
		result = commDAO.updateComment(comNum);
		
		return result==1;
		
		
	}//deleteComment

	
	
	/**
	 * ���� ���
	 * @param rpVO
	 */
	public boolean addReplyService( ReplyVO rpVO ) {
		
		boolean success=false;
		success = commDAO.insertReply(rpVO)==1;
		
		return success;
		
	}//insertReply

	
	/**
	 * ���� ����(����)
	 * @param replyNum
	 * @return
	 */
	public boolean removeReplyService( int replyNum ) {
		
		boolean success=false;
		success = commDAO.deleteReplyService(replyNum)==1;
		
		return success;
		
	}//deleteReplyService


	
	/**
	 * ��� ����Ʈ ����
	 * @param rvNum
	 * @return
	 */
	public List<CommentDomain> getCommentsService ( int rv_num ) {
		
		//���� ����Ʈ
		List<CommentDomain> combinedList = new ArrayList<CommentDomain>();
		
		
		List<CommentDomain> commList = null;
		List<ReplyDomain> replyList = null;
		
		//���
		commList = commDAO.selectComments(rv_num);
		//����
		replyList = commDAO.selectReplies(rv_num);
		
		
		// ��۰� ������ �����Ͽ� �ϳ��� ����Ʈ�� ��ȯ
	    List<ReplyDomain> replyListTemp = null;
	    
	    if(replyList !=null) {
	    	//������ �ִ� ���
	    for (CommentDomain comment : commList) { // ��ü ����� ��ȸ
	    	
	    	replyListTemp = new ArrayList<ReplyDomain>();
	    	
	        for (ReplyDomain reply : replyList) { //��ü ������ ��ȸ
	            if (reply.getCom_num() == comment.getCom_num()) {
	            	replyListTemp.add(reply);
	            }//end if
	        }//end for
	        
	        comment.setReplies(replyListTemp);
	        combinedList.add(comment);
	    }//end for
	    
	    }else {
	    	 // ��� ����� �״�� ���� ����Ʈ�� ����
	    	combinedList = commList;
	    }
	    
		return combinedList;
		
	}//getCommentsService

	

	
}//class
