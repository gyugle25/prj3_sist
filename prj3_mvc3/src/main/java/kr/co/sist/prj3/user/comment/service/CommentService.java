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
 * 댓글 서비스
 * @author user
 *
 */
@Component
public class CommentService {

	@Autowired(required = false)
	private CommentDAO commDAO;

	/**
	 * 댓글 등록 - ajax
	 * @param cmVO
	 */
	public boolean addCommentService( CommVO cmVO ) {
		
		boolean success=false;
		success = commDAO.insertComment(cmVO)==1;
		return success;
		
	}//insertComment


	/**
	 * 대댓글이 없는 댓글은 삭제
	 * @param comNum
	 * @return
	 */
	public boolean removeCommentService ( int comNum ) {
		
		int result = 0;
		result = commDAO.deleteComment(comNum);
		
		return result==1;
		
		
	}//deleteComment
	
	
	/**
	 * 대댓글이 있는 경우는 내용을 업데이트
	 * @param comNum
	 * @return
	 */
	public boolean updateCommentService  (int comNum ) {
		
		int result = 0;
		result = commDAO.updateComment(comNum);
		
		return result==1;
		
		
	}//deleteComment

	
	
	/**
	 * 대댓글 등록
	 * @param rpVO
	 */
	public boolean addReplyService( ReplyVO rpVO ) {
		
		boolean success=false;
		success = commDAO.insertReply(rpVO)==1;
		
		return success;
		
	}//insertReply

	
	/**
	 * 대댓글 삭제(업뎃)
	 * @param replyNum
	 * @return
	 */
	public boolean removeReplyService( int replyNum ) {
		
		boolean success=false;
		success = commDAO.deleteReplyService(replyNum)==1;
		
		return success;
		
	}//deleteReplyService


	
	/**
	 * 댓글 리스트 셀렉
	 * @param rvNum
	 * @return
	 */
	public List<CommentDomain> getCommentsService ( int rv_num ) {
		
		//최종 리스트
		List<CommentDomain> combinedList = new ArrayList<CommentDomain>();
		
		
		List<CommentDomain> commList = null;
		List<ReplyDomain> replyList = null;
		
		//댓글
		commList = commDAO.selectComments(rv_num);
		//대댓글
		replyList = commDAO.selectReplies(rv_num);
		
		
		// 댓글과 대댓글을 연결하여 하나의 리스트로 반환
	    List<ReplyDomain> replyListTemp = null;
	    
	    if(replyList !=null) {
	    	//대댓글이 있는 경우
	    for (CommentDomain comment : commList) { // 전체 댓글을 순회
	    	
	    	replyListTemp = new ArrayList<ReplyDomain>();
	    	
	        for (ReplyDomain reply : replyList) { //전체 대댓글을 순회
	            if (reply.getCom_num() == comment.getCom_num()) {
	            	replyListTemp.add(reply);
	            }//end if
	        }//end for
	        
	        comment.setReplies(replyListTemp);
	        combinedList.add(comment);
	    }//end for
	    
	    }else {
	    	 // 댓글 목록을 그대로 최종 리스트로 설정
	    	combinedList = commList;
	    }
	    
		return combinedList;
		
	}//getCommentsService

	

	
}//class
