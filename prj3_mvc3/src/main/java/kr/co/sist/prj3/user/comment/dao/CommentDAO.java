package kr.co.sist.prj3.user.comment.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import kr.co.sist.prj3.MyBatisHandler;
import kr.co.sist.prj3.user.comment.domain.CommentDomain;
import kr.co.sist.prj3.user.comment.domain.ReplyDomain;
import kr.co.sist.prj3.user.comment.vo.CommVO;
import kr.co.sist.prj3.user.comment.vo.ReplyVO;

@Component
public class CommentDAO {


	/**
	 * ��� ��� - ajax
	 * @param cmVO
	 */
	
	public int insertComment( CommVO cmVO ) {
		
		int cnt = 0;
		
		//1.MyBatis �ڵ鷯 ���
		SqlSession ss =MyBatisHandler.getInstance().getMyBatisHandler(false);
		
		//2. Handler�� ����ϱ�
		cnt = ss.insert("insertComment", cmVO);		
		
		//3. transaction �Ϸ��ϱ�
				if(cnt==1) {
					ss.commit();
				}
				
		//4. ���� ����
				if(ss!=null) {ss.close();}//end if
				
		return cnt;
		
		
	}//insertComment


	/**
	 * ������ ���� ����� ����
	 * @param comNum
	 * @return
	 */
	public int deleteComment ( int comNum ) {
		
		int cnt = 0;
		// 1.MyBatis �ڵ鷯 ���
		SqlSession ss = MyBatisHandler.getInstance().getMyBatisHandler(false);

		// 2. Handler�� ����ϱ�
		cnt = ss.insert("deleteComm", comNum);

		// 3. transaction �Ϸ��ϱ�
		if (cnt == 1) {
			ss.commit();
		}

		// 4. ���� ����
		if (ss != null) {
			ss.close();
		} // end if

		return cnt;
		
		
	}//deleteComment
	
	
	/**
	 * ������ �ִ� ���� ������ ������Ʈ
	 * @param comNum
	 * @return
	 */
	public int updateComment (int comNum ) {
		
		int cnt = 0;
		// 1.MyBatis �ڵ鷯 ���
		SqlSession ss = MyBatisHandler.getInstance().getMyBatisHandler(false);

		// 2. Handler�� ����ϱ�
		cnt = ss.insert("updateDelComm", comNum);

		// 3. transaction �Ϸ��ϱ�
		if (cnt == 1) {
			ss.commit();
		}

		// 4. ���� ����
		if (ss != null) {
			ss.close();
		} // end if

		return cnt;
		
		
	}//deleteComment

	
	
	/**
	 * ���� ���
	 * @param rpVO
	 */
	public int insertReply( ReplyVO rpVO ) {
		
		int cnt = 0;
		
		//1.MyBatis �ڵ鷯 ���
		SqlSession ss =MyBatisHandler.getInstance().getMyBatisHandler(false);
		
		//2. Handler�� ����ϱ�
		cnt = ss.insert("insertReply", rpVO);		
		
		
		//3. transaction �Ϸ��ϱ�
				if(cnt==1) {
					ss.commit();
				}
				
		//4. ���� ����
				if(ss!=null) {ss.close();}//end if
				
		
				
		return cnt;
	}//insertReply

	
	
	/**
	 * ���� ����/������Ʈ
	 * @param replyNum
	 * @return
	 */
	public int deleteReplyService( int replyNum ) {
		
		int cnt = 0;
		// 1.MyBatis �ڵ鷯 ���
		SqlSession ss = MyBatisHandler.getInstance().getMyBatisHandler(false);

		// 2. Handler�� ����ϱ�
		cnt = ss.insert("updateDelReply", replyNum);

		// 3. transaction �Ϸ��ϱ�
		if (cnt == 1) {
			ss.commit();
		}

		// 4. ���� ����
		if (ss != null) {
			ss.close();
		} // end if

		return cnt;
		
	}//deleteReplyService


	
	/**
	 * ��� ����Ʈ ����
	 * @param rvNum
	 * @return
	 */
	public List<CommentDomain> selectComments ( int rv_num ) {
		
		List<CommentDomain> list = null;
		
		// 1.MyBatis �ڵ鷯 ���
		SqlSession ss = MyBatisHandler.getInstance().getMyBatisHandler(false);

		// 2. Handler�� ����ϱ�
		list = ss.selectList("selectComm", rv_num);

	
		// 4. ���� ����
		if (ss != null) {
			ss.close();
		} // end if

		return list;
		
		
	}//selectComments
	
	
	
	/**
	 * ���� ����Ʈ ����
	 * @param comNum
	 * @return
	 */
	public List<ReplyDomain> selectReplies (int rv_num ) {
		
		List<ReplyDomain> list = null;
		
		// 1.MyBatis �ڵ鷯 ���
				SqlSession ss = MyBatisHandler.getInstance().getMyBatisHandler(false);

				// 2. Handler�� ����ϱ�
				list = ss.selectList("selectReply", rv_num);

			
				// 4. ���� ����
				if (ss != null) {
					ss.close();
				} // end if
		
		
		return list;
		
		
		
	}//selectReplies


	
	
}//class
