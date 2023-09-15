package kr.co.sist.prj3.admin.review.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import kr.co.sist.prj3.MyBatisHandler;
import kr.co.sist.prj3.admin.review.domain.ReviewBoardDomain;
import kr.co.sist.prj3.admin.review.domain.ReviewInfoDomain;
import kr.co.sist.prj3.admin.review.vo.ReviewSearchVO;

@Component
public class AdminReviewDAO {

	/**
	 * ������ 
	 * ���� ����Ʈ ��Ȳ�� �����ֱ� + �˻����
	 * 2023.05.19
	 * @author KT
	 */
	public List<ReviewBoardDomain> selectReviewList(ReviewSearchVO rsVO) throws PersistenceException{
		List<ReviewBoardDomain> list = new ArrayList<ReviewBoardDomain>();
		
		//1. MyBatis Handler ���
		SqlSession ss = MyBatisHandler.getInstance().getMyBatisHandler(false);
		
		//2. handler ������ ����
		if (rsVO.getSearch_type()==null || rsVO.getSearch()==null) { // �˻� ���� �������� ����
			list=ss.selectList("kr.co.sist.prj3.reviewMapper.reviewList");
		} else {
			list=ss.selectList("kr.co.sist.prj3.reviewMapper.reviewListSearch",rsVO);
		}//end else
		
		//3. ��ȸ��� ó��
		
		//4. MyBatis Handler �ݱ�
		if( ss!= null) {ss.close();}//end if

		return list;
	}//selectReviewList

	/**
	 * ������
	 * �ش� ���� ���� ��ư
	 * 2023.05.20
	 * @author KT
	 */
	public int deleteReview(int rv_num) {
		
		int cnt = 0;
		
		//1. MyBatis Handler ���
		SqlSession ss = MyBatisHandler.getInstance().getMyBatisHandler(false);
		
		//2. handler ������ ����
		cnt = ss.delete("kr.co.sist.prj3.reviewMapper.reviewDelete",rv_num);
		
		//3. ��ȸ��� ó��
		if(cnt==1) {
			ss.commit();
		}//end if
		
		//4. MyBatis Handler �ݱ�
		if( ss!= null) {ss.close();}//end if
		
		return cnt;
	}//deleteReview

	public ReviewInfoDomain selectReview(int rvNum) {
		
		return null;
	}//selectReview

	/*public List<CommentsDomain> showReviewComments(int rvNum){
		���Domain ��������
	}*/
}//class
