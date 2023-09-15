package kr.co.sist.prj3.user.review.service;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import kr.co.sist.prj3.user.review.dao.UserReviewDAO;
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

/**
 * ���� ���� Service
 * @author kt
 */
@Controller
public class UserReviewService {
	
	@Autowired(required = false)
	private UserReviewDAO urDAO ; 
	
	/**
	 * ����
	 * �ش� ��ȭ�� ���� ���� ����Ʈ �����ֱ� + �˻����
	 * 2023.05.20
	 * @author KT
	 */
	public List<ReviewBoardDomain> showSearchReviewList(ReviewSearchVO rsVO){
		List<ReviewBoardDomain> list = new ArrayList<ReviewBoardDomain>();
		
		list = urDAO.selectSearchReview(rsVO);
		
		return list;
	}//showSearchReviewList

	/**
	 * ����
	 * ���� �ۼ��� ���� !����!
	 * 2023.05.22
	 * @author KT
	 */
	public boolean reviewDelete(int rv_num) {
		
		int cnt = urDAO.deleteReview(rv_num);
		
		return cnt==1;
		
	}//reviewDelete
	
	/**
	 * ����
	 * �ش� ��ȭ�� ���� ���� ���� frm(select)
	 * 2023.05.21
	 * @author KT
	 */
	public ReviewWriteDomain reviewWriteShow(int rv_num) {
		
		ReviewWriteDomain rwd = null;
		rwd = urDAO.selectReview(rv_num);
		
		return rwd;
	}//reviewModify

	/**
	 * ����
	 * �ش� ��ȭ�� ���� ���� ���� process(update)
	 * 2023.05.21
	 * @author KT
	 */
	public boolean reviewModify(ReviewModifyVO rmVO) {
		int cnt = urDAO.updateReview(rmVO);
		
		return cnt == 1;
	}//reviewModify
	
	/**
	 * ����
	 * �ش� ��ȭ�� ���� ���� �ۼ�
	 * 2023.05.21
	 * @author KT
	 */
	public boolean reviewProcess(WriteReviewVO wrVO) {
		
		int cnt = urDAO.insertReview(wrVO);
		
		return cnt==1;
		
	}//reviewProcess
	
	/**
	 * ����
	 * Ŭ���� �������� ���� ���� �����ֱ�
	 * 2023.05.21
	 * @author KT
	 */
	public ReviewInfoDomain showReview(LikeVO lVO){
		
		ReviewInfoDomain reviewInfo = null;
		reviewInfo = urDAO.selectReview(lVO);
		
		return reviewInfo;
	}//showReview
	
	/**
	 * ����
	 * ���� Ŭ���� ���� ���ƿ� �������� �����ֱ�
	 * 2023.05.22
	 * @author KM
	 */
	public boolean likeStatusService(LikeVO lVO) {
	
		String result="";
		boolean likeStatus = false;
		
		result = urDAO.selectLikeStatus(lVO);
		
		if(result != null && !result.isEmpty()) { //���ƿ� ���ڵ尡 ��ȸ�ȴٸ�
			likeStatus = true;
		}//end if
		
				
		return likeStatus;
	}//likeStatusService
	
	/**
	 * ����
	 * ���ƿ� ������
	 * 2023.05.22
	 * @author KM
	 */
	public String likeOnService(LikeVO lVO) {
		
		boolean resultFlag=false;
		JSONObject jsonObj = new JSONObject();
		
		int cnt = urDAO.insertLike(lVO);
		
		if(cnt==1) {
		
			jsonObj.put("resultFlag", true);
			
		}//end if
		
		return jsonObj.toJSONString();
		
	}//likeOnService
	

	
	/**
	 * ����
	 * ���ƿ� ����
	 * 2023.05.22
	 * @author KM
	 */
	public String likeOffService(LikeVO lVO) {
		
		boolean resultFlag=false;
		JSONObject jsonObj = new JSONObject();
		
		int cnt = urDAO.deleteLike(lVO);
		
		if(cnt==1) {
		
			jsonObj.put("resultFlag", true);
			
		}//end if
		
		return jsonObj.toJSONString();
		
	}//likeOffService
	
	
	/**
	 * ����
	 * ���ƿ� ���� �����ֱ�
	 * 2023.05.22
	 * @author KM
	 */
	public int likeCount (int rvNum) {
		int cnt=0;
		
		cnt = urDAO.selectCntLike(rvNum);
		
		return cnt;
	}//likeCount
	
	/**
	 * ����
	 * (����������) ���� �� ���� �˻�
	 * 2023.05.22
	 * @author KM
	 */
	public List<MyReviewDomain> myReviewService(MyReviewSearchVO mrsVO){
		List<MyReviewDomain> list = new ArrayList<MyReviewDomain>();
		
		list = urDAO.selectMyReview(mrsVO);
		
		return list;
	}//MyReviewService

	/**
	 * ����
	 * ���� ���ƿ� ���� user�� �����ֱ�
	 * 2023.05.22
	 * @author KT
	 */
	public List<ReviewLikeUserDomain> showLikeUser(int rv_num){
		List<ReviewLikeUserDomain> list = new ArrayList<ReviewLikeUserDomain>();
		
		list = urDAO.selectLikeUser(rv_num);
		
		return list;
	}//showLikeUser
	
	///////////////////////////////////////////////////////////////////
	
	/**
	 * �ش� ���� â���� ��ȸ�� +1 ����
	 * @param rvNum(�����ȣ)
	 */
	public boolean hitsUp(int rv_num) {
		
		boolean success=false;
		success = urDAO.updateHits(rv_num)==1;
		return success;
		
	}//hitsUp
}//LikeService
