package kr.co.sist.prj3.user.review.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import kr.co.sist.prj3.MyBatisHandler;
import kr.co.sist.prj3.user.review.domain.MovieInfoDomain;
import kr.co.sist.prj3.user.review.domain.MyReviewDomain;
import kr.co.sist.prj3.user.review.domain.ReviewBoardDomain;
import kr.co.sist.prj3.user.review.domain.ReviewInfoDomain;
import kr.co.sist.prj3.user.review.domain.ReviewLikeUserDomain;
import kr.co.sist.prj3.user.review.domain.ReviewWriteDomain;
import kr.co.sist.prj3.user.review.vo.LikeVO;
import kr.co.sist.prj3.user.review.vo.MyReviewSearchVO;
import kr.co.sist.prj3.user.review.vo.ReviewModifyVO;
import kr.co.sist.prj3.user.review.vo.ReviewSearchVO;
import kr.co.sist.prj3.user.review.vo.WriteReviewVO;

@Component
public class UserReviewDAO {

	/**
	 * ���� �ش� ��ȭ�� ���� ���� ����Ʈ �����ֱ� + �˻���� 2023.05.20
	 * 
	 * @author KT
	 */
	public List<ReviewBoardDomain> selectSearchReview(ReviewSearchVO rsVO) throws PersistenceException {
		List<ReviewBoardDomain> list = new ArrayList<ReviewBoardDomain>();

		// 1. MyBatis Handler ���
		SqlSession ss = MyBatisHandler.getInstance().getMyBatisHandler(false);

		// 2. handler ������ ����
		if (rsVO.getSearch_type() == null || rsVO.getSearch() == null) { // �˻� ���� �������� ����
			//rsVO.setM_num(2);
			list = ss.selectList("kr.co.sist.prj3.reviewListMapper.reviewUserList", rsVO);
		} else {
			list = ss.selectList("kr.co.sist.prj3.reviewListMapper.reviewUserList", rsVO);
		} // end else

		// 3. ��ȸ��� ó��

		// 4. MyBatis Handler �ݱ�
		if (ss != null) {
			ss.close();
		} // end if

		return list;
	}// selectSearchReview

	/**
	 * ���� �ش� ��ȭ�� ���� ���� !�ۼ�! 2023.05.21
	 * 
	 * @author KT
	 */
	public int insertReview(WriteReviewVO wrVO) {

		int cnt = 0;

		// 1. MyBatis Handler ���
		SqlSession ss = MyBatisHandler.getInstance().getMyBatisHandler(false);

		// 2. handler ������ ����
		cnt = ss.delete("kr.co.sist.prj3.reviewMapper.insertReview", wrVO);
		
		// 3. ��ȸ��� ó��
		if (cnt == 1) {
			ss.commit();
		} // end if

		// 4. MyBatis Handler �ݱ�
		if (ss != null) {
			ss.close();
		} // end if

		return cnt;

	}// insertReview

	/**
	 * ���� �ش� ��ȭ�� ���� ���� !����! 2023.05.22
	 * 
	 * @author KT
	 */
	public int deleteReview(int rv_num) {
		
		int cnt = 0;
		
		// 1. MyBatis Handler ���
		SqlSession ss = MyBatisHandler.getInstance().getMyBatisHandler(false);
		
		// 2. handler ������ ����
		cnt = ss.delete("kr.co.sist.prj3.reviewMapper.deleteReview", rv_num);

		// 3. ��ȸ��� ó��
		if (cnt == 1) {
			ss.commit();
		} // end if
		
		// 4. MyBatis Handler �ݱ�
		if (ss != null) {
			ss.close();
		} // end if
		
		return cnt;
		
	}// insertReview

	/**
	 * ���� �ش� ��ȭ�� ���� ���� !��ȸ! 2023.05.21
	 * 
	 * @author KT
	 */
	public ReviewWriteDomain selectReview(int rv_num) {
		
		ReviewWriteDomain rwd = null;
		
		// 1. MyBatis Handler ���
		SqlSession ss = MyBatisHandler.getInstance().getMyBatisHandler(false);
		
		// 2. handler ������ ����
		rwd = ss.selectOne("kr.co.sist.prj3.reviewMapper.selectReview", rv_num);
		
		// 3. ��ȸ��� ó��
		
		// 4. MyBatis Handler �ݱ�
		if (ss != null) {
			ss.close();
		} // end if
		
		return rwd;
	}//selectReview
	
	/**
	 * ���� �ش� ��ȭ�� ���� ���� !����! 2023.05.21
	 * 
	 * @author KT
	 */
	public int updateReview(ReviewModifyVO rmVO) {

		int cnt = 0;

		// 1. MyBatis Handler ���
		SqlSession ss = MyBatisHandler.getInstance().getMyBatisHandler(false);

		// 2. handler ������ ����
		cnt = ss.update("kr.co.sist.prj3.reviewMapper.updateReview", rmVO);
		
		// 3. ��ȸ��� ó��
		if (cnt == 1) {
			ss.commit();
		} // end if

		// 4. MyBatis Handler �ݱ�
		if (ss != null) {
			ss.close();
		} // end if

		return cnt;

	}// updateReview

	
	/**
	 * ����
	 * Ŭ���� �������� ���� ���� �����ֱ�
	 * 2023.05.21
	 * @author KT
	 */
	  public ReviewInfoDomain selectReview(LikeVO lVO) {
	  
		ReviewInfoDomain rid = null;
		  
		//1. MyBatis Handler ���
		SqlSession ss = MyBatisHandler.getInstance().getMyBatisHandler(false);
		
		//2. handler ������ ����
//		if (lVO.getUser_id()==null) { 
//			rid = ss.selectOne("kr.co.sist.prj3.reviewPostMapper.reviewInfo");
//		} else {
//			rid = ss.selectOne("kr.co.sist.prj3.reviewPostMapper.reviewInfo",lVO);
//		}//end else
		
		// ������ �Ǵ�
		rid = ss.selectOne("kr.co.sist.prj3.reviewPostMapper.reviewInfo",lVO);
		
		
		//3. ��ȸ��� ó��
		
		//4. MyBatis Handler �ݱ�
		if( ss!= null) {ss.close();}//end if
	  
		return rid;
	  
	  }//selectReview
	 
	  /**
	 * ����
	 * ���� Ŭ���� ���� ���ƿ� �������� �����ֱ�
	 * 2023.05.22
	 * @author KM
	 */
	public String selectLikeStatus(LikeVO lVO) {

		String result = "";

		// 1.MyBatis �ڵ鷯 ���
		SqlSession ss = MyBatisHandler.getInstance().getMyBatisHandler(false);

		// 2. Handler�� ����ϱ�
		result = ss.selectOne("selectLikeStatus", lVO);

		// 4. ���� ����
		if (ss != null) { 	ss.close();		} // end if

		return result;
	}// selectLikeStatus

	/**
	 * ����
	 * ���ƿ� ���� �����ֱ�
	 * 2023.05.22
	 * @author KM
	 */
	public int selectCntLike ( int rv_num ) {
		
		int cnt = 0;
		
		//1.MyBatis �ڵ鷯 ���
		SqlSession ss = MyBatisHandler.getInstance().getMyBatisHandler(false);
		
		//2. Handler�� ����ϱ�
		cnt = ss.selectOne("selectLikes", rv_num);		
		// 3. transaction �Ϸ��ϱ�
		
				
		//4. ���� ����
		if(ss!=null) {ss.close();}//end if
				
		return cnt;
	}//selectCntLike

	
	/**
	 * ����
	 * ���ƿ� ������
	 * 2023.05.22
	 * @author KM
	 */
	public int insertLike(LikeVO lVO) {
		
		int cnt = 0;
		
		//1.MyBatis �ڵ鷯 ���
		SqlSession ss =MyBatisHandler.getInstance().getMyBatisHandler(false);
		
		//2. Handler�� ����ϱ�
		cnt = ss.insert("insertLike", lVO);		
		
		//3. transaction �Ϸ��ϱ�
				if(cnt==1) {
					ss.commit();
				}
				
		//4. ���� ����
				if(ss!=null) {ss.close();}//end if
				
		return cnt;
	}//insertLike
	
	/**
	 * ����
	 * ���ƿ� ����
	 * 2023.05.22
	 * @author KM
	 */
	public int deleteLike(LikeVO lVO) {

		int cnt = 0;

		// 1.MyBatis �ڵ鷯 ���
		SqlSession ss = MyBatisHandler.getInstance().getMyBatisHandler(false);

		// 2. Handler�� ����ϱ�
		cnt = ss.delete("deleteLike", lVO);

		// 3. transaction �Ϸ��ϱ�
		if (cnt == 1) {
			ss.commit();
		}

		// 4. ���� ����
		if (ss != null) {
			ss.close();
		} // end if

		return cnt;
	}//deleteLike
	

	/**
	 * ����
	 * ���� �� ���� �˻�(����������)
	 * 2023.05.22
	 * @author KM
	 */
	public List<MyReviewDomain> selectMyReview (MyReviewSearchVO mrsVO) {
		
		List<MyReviewDomain> list = new ArrayList<MyReviewDomain>();
		
		// 1.MyBatis �ڵ鷯 ���
		SqlSession ss = MyBatisHandler.getInstance().getMyBatisHandler(false);
		// 2. Handler�� ����ϱ�
		
		list = ss.selectList("selectMyReview", mrsVO);

		// 4. ���� ����
		if (ss != null) { 	ss.close();		} // end if

		return list;
		
		
	}//selectMyReview

	/**
	 * ����
	 * ���� ���ƿ� ���� user�� �����ֱ�
	 * 2023.05.22
	 * @author KT
	 */
	public List<ReviewLikeUserDomain> selectLikeUser(int rv_num) {
		List<ReviewLikeUserDomain> list = new ArrayList<ReviewLikeUserDomain>();
		
		// 1. MyBatis Handler ���
		SqlSession ss = MyBatisHandler.getInstance().getMyBatisHandler(false);
		
		// 2. handler ������ ����
		list = ss.selectList("kr.co.sist.prj3.reviewPostMapper.reviewLikeUser", rv_num);
		// 3. ��ȸ��� ó��

		// 4. MyBatis Handler �ݱ�
		if (ss != null) {
			ss.close();
		} // end if
		
		return list;
	}//likeUser
	
	
	//////////////////////////////////////////////////////////////////
	
	/**
	 * �ش� ���� â���� ��ȸ�� +1 ����
	 * 
	 * @param rvNum(�����ȣ)
	 */
	public int updateHits(int rv_num) {
		

		int cnt = 0;

		// 1.MyBatis �ڵ鷯 ���
		SqlSession ss = MyBatisHandler.getInstance().getMyBatisHandler(false);

		// 2. Handler�� ����ϱ�
		cnt = ss.update("hitsUp", rv_num);

		// 3. transaction �Ϸ��ϱ�
		if (cnt == 1) {
			ss.commit();
		}

		// 4. ���� ����
		if (ss != null) {
			ss.close();
		} // end if

		return cnt;
		
	}// updateHits
	
}// class