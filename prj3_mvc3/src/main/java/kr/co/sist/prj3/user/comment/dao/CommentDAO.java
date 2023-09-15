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
	 * 댓글 등록 - ajax
	 * @param cmVO
	 */
	
	public int insertComment( CommVO cmVO ) {
		
		int cnt = 0;
		
		//1.MyBatis 핸들러 얻기
		SqlSession ss =MyBatisHandler.getInstance().getMyBatisHandler(false);
		
		//2. Handler를 사용하기
		cnt = ss.insert("insertComment", cmVO);		
		
		//3. transaction 완료하기
				if(cnt==1) {
					ss.commit();
				}
				
		//4. 연결 끊기
				if(ss!=null) {ss.close();}//end if
				
		return cnt;
		
		
	}//insertComment


	/**
	 * 대댓글이 없는 댓글은 삭제
	 * @param comNum
	 * @return
	 */
	public int deleteComment ( int comNum ) {
		
		int cnt = 0;
		// 1.MyBatis 핸들러 얻기
		SqlSession ss = MyBatisHandler.getInstance().getMyBatisHandler(false);

		// 2. Handler를 사용하기
		cnt = ss.insert("deleteComm", comNum);

		// 3. transaction 완료하기
		if (cnt == 1) {
			ss.commit();
		}

		// 4. 연결 끊기
		if (ss != null) {
			ss.close();
		} // end if

		return cnt;
		
		
	}//deleteComment
	
	
	/**
	 * 대댓글이 있는 경우는 내용을 업데이트
	 * @param comNum
	 * @return
	 */
	public int updateComment (int comNum ) {
		
		int cnt = 0;
		// 1.MyBatis 핸들러 얻기
		SqlSession ss = MyBatisHandler.getInstance().getMyBatisHandler(false);

		// 2. Handler를 사용하기
		cnt = ss.insert("updateDelComm", comNum);

		// 3. transaction 완료하기
		if (cnt == 1) {
			ss.commit();
		}

		// 4. 연결 끊기
		if (ss != null) {
			ss.close();
		} // end if

		return cnt;
		
		
	}//deleteComment

	
	
	/**
	 * 대댓글 등록
	 * @param rpVO
	 */
	public int insertReply( ReplyVO rpVO ) {
		
		int cnt = 0;
		
		//1.MyBatis 핸들러 얻기
		SqlSession ss =MyBatisHandler.getInstance().getMyBatisHandler(false);
		
		//2. Handler를 사용하기
		cnt = ss.insert("insertReply", rpVO);		
		
		
		//3. transaction 완료하기
				if(cnt==1) {
					ss.commit();
				}
				
		//4. 연결 끊기
				if(ss!=null) {ss.close();}//end if
				
		
				
		return cnt;
	}//insertReply

	
	
	/**
	 * 대댓글 삭제/업데이트
	 * @param replyNum
	 * @return
	 */
	public int deleteReplyService( int replyNum ) {
		
		int cnt = 0;
		// 1.MyBatis 핸들러 얻기
		SqlSession ss = MyBatisHandler.getInstance().getMyBatisHandler(false);

		// 2. Handler를 사용하기
		cnt = ss.insert("updateDelReply", replyNum);

		// 3. transaction 완료하기
		if (cnt == 1) {
			ss.commit();
		}

		// 4. 연결 끊기
		if (ss != null) {
			ss.close();
		} // end if

		return cnt;
		
	}//deleteReplyService


	
	/**
	 * 댓글 리스트 셀렉
	 * @param rvNum
	 * @return
	 */
	public List<CommentDomain> selectComments ( int rv_num ) {
		
		List<CommentDomain> list = null;
		
		// 1.MyBatis 핸들러 얻기
		SqlSession ss = MyBatisHandler.getInstance().getMyBatisHandler(false);

		// 2. Handler를 사용하기
		list = ss.selectList("selectComm", rv_num);

	
		// 4. 연결 끊기
		if (ss != null) {
			ss.close();
		} // end if

		return list;
		
		
	}//selectComments
	
	
	
	/**
	 * 대댓글 리스트 셀렉
	 * @param comNum
	 * @return
	 */
	public List<ReplyDomain> selectReplies (int rv_num ) {
		
		List<ReplyDomain> list = null;
		
		// 1.MyBatis 핸들러 얻기
				SqlSession ss = MyBatisHandler.getInstance().getMyBatisHandler(false);

				// 2. Handler를 사용하기
				list = ss.selectList("selectReply", rv_num);

			
				// 4. 연결 끊기
				if (ss != null) {
					ss.close();
				} // end if
		
		
		return list;
		
		
		
	}//selectReplies


	
	
}//class
