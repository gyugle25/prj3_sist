package kr.co.sist.prj3.user.review.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import kr.co.sist.prj3.user.comment.domain.CommentDomain;
import kr.co.sist.prj3.user.comment.service.CommentService;
import kr.co.sist.prj3.user.login.domain.LoginResultDomain;
import kr.co.sist.prj3.user.review.domain.MyReviewDomain;
import kr.co.sist.prj3.user.review.domain.ReviewBoardDomain;
import kr.co.sist.prj3.user.review.domain.ReviewLikeUserDomain;
import kr.co.sist.prj3.user.review.service.UserReviewService;
import kr.co.sist.prj3.user.review.vo.LikeVO;
import kr.co.sist.prj3.user.review.vo.MyReviewSearchVO;
import kr.co.sist.prj3.user.review.vo.ReviewModifyVO;
import kr.co.sist.prj3.user.review.vo.ReviewSearchVO;
import kr.co.sist.prj3.user.review.vo.WriteReviewVO;

@Controller
@SessionAttributes("lrDomain")
public class UserReviewController {
	
	@Autowired(required = false)
	private UserReviewService urService ;
	
	@Autowired(required = false)
	private CommentService commService ;
	
	/**
	 * �ش� ��ȭ�� ���� ���� ����Ʈ �����ֱ� + �˻����
	 * 2023.05.20
	 * @author KT
	 */
	@RequestMapping(value = "/review_list.do", method = {RequestMethod.GET,RequestMethod.POST} )
	public String showSearchReviewList(ReviewSearchVO rsVO, Model model) {
		
		String url = "";
		
		model.addAttribute("reviewList", urService.showSearchReviewList(rsVO));
		url = "/review/review_list";
		
		return url;
	}//showMovieInfo

	

	/**
	 * ���� ���� �����ֱ�
	 * @param rsVO
	 * @param model
	 */
	@ResponseBody
	@GetMapping("/review_info.do")
	public String reviewInfo(ReviewSearchVO rsVO, Model model) throws PersistenceException, SQLException {

		JSONObject jsonObj = new JSONObject();

		//��ȸ�� ���丮��Ʈ
		List<ReviewBoardDomain> reviewList = urService.showSearchReviewList(rsVO);
		JSONArray reviewArray = new JSONArray();

		jsonObj.put("reviewSize", reviewList.size());

		for (ReviewBoardDomain review : reviewList) {
			JSONObject reviewObj = new JSONObject();
			reviewObj.put("title", review.getTitle());
			reviewObj.put("nick_name", review.getNick_name());
			reviewObj.put("input_date", review.getInput_date());
			reviewObj.put("user_id", review.getUser_id());
			reviewObj.put("hits", review.getHits());
			reviewObj.put("like_total", review.getLike_total());
			reviewObj.put("rv_num", review.getRv_num());
			reviewArray.add(reviewObj);
		} // end for

		jsonObj.put("review", reviewArray);

		return jsonObj.toJSONString();

	}// review_info	
	
	
	/**
	 * ����
	 * ���� �ۼ��� ���� !����!
	 * 2023.05.22
	 * @author KT
	 */
	@GetMapping("/review_write_delete.do")
	public String review(int rv_num, int m_num, Model model) {
		
		urService.reviewDelete(rv_num);
		
		return "redirect:/main_info.do?m_num="+m_num;
	}//reviewFrm
	
	/**
	 * ����
	 * ���� �ۼ� �������� !�̵�! + !��ȸ!
	 * 2023.05.20
	 * @author KT
	 */
	@GetMapping("/review_write.do")
	public String reviewFrm(int m_num) {
		
		
		return "/review/review_write";
	}//reviewFrm 
///////////////////////////////////////////���� 1 ���ְ� ��� (����)///////////////////////////////////////////////
	/**
	 * ����
	 * ���� �ۼ� �������� !�̵�! + !��ȸ!
	 * 2023.05.20
	 * @author KT
	 */
	@PostMapping("/review_write_modify1.do")
	public String reviewFrm1(int rv_num, Model model) {
		
		model.addAttribute("review", urService.reviewWriteShow(rv_num));
		
		return "/review/review_write_modify";
	}//reviewFrm
	
	/**
	 * ����
	 * ���� !����! ���������� !����!
	 * 2023.05.20
	 * @author KT
	 */
	@PostMapping("/review_write_modify_process1.do")
	public String reviewProcess1(ReviewModifyVO rmVO, Model model) {
		
		model.addAttribute("review", urService.reviewModify(rmVO));
		
		return "redirect:/main_info.do?m_num="+rmVO.getM_num();
	}//reviewFrm
/////////////////////////////////////////���� 1 ���ְ� ��� (����)///////////////////////////////////////////////
	
///////////////////////////////////////////////////�׽�Ʈ//////////////////////////////////////////////
	/**
	 * ����
	 * ���� �ۼ� �������� !�̵�! + !��ȸ!
	 * 2023.05.20
	 * @author KT
	 */
	@PostMapping("/review_write_modify.do")
	public String reviewFrm(int rv_num, Model model) {
		
		model.addAttribute("review", urService.reviewWriteShow(rv_num));
		
		return "/review/review_write_modify";
	}//reviewFrm
	
	/**
	 * ����
	 * ���� !����! ���������� !����!
	 * 2023.05.20
	 * @author KT
	 * @throws UnsupportedEncodingException 
	 */
	@PostMapping("/review_write_modify_process.do")
	public String reviewProcess(ReviewModifyVO rmVO, Model model, String m_title, int rv_num, int m_num) throws UnsupportedEncodingException {
		
		System.out.println(rmVO.getM_num());
		System.out.println(m_title);
		System.out.println(rv_num);
		String encodedMTitle = URLEncoder.encode(m_title, "UTF-8");
		model.addAttribute("review", urService.reviewModify(rmVO));
		
		return "redirect:/review_post.do?m_num="+rmVO.getM_num()+"&m_title="+encodedMTitle+"&rv_num="+rv_num;
	}//reviewFrm
	
	
	// �ش� ���� â���� ��ȸ�� +1�� ����
	public String hitsUpProcess(int rvNum) {
	return "";
	}
/////////////////////////////////////////////////////�׽�Ʈ//////////////////////////////////////////////	
	
	/**
	 * ����
	 * ���� !���! ���������� !���! 
	 * 2023.05.20
	 * @author KT
	 */
	@PostMapping("/review_write_process.do")
	public String reviewProcess(WriteReviewVO wrVO,Model model) {
	
		urService.reviewProcess(wrVO);
		
		return "redirect:/main_info.do?m_num="+wrVO.getM_num();
	}//reviewProcessA
	
	
	/**
	 * ����
	 * ���� Ŭ���� ��������,����,���,���ƿ�(+ī��Ʈ) �����ֱ�
	 * 2023.05.21
	 * @author KT,KM
	 */
	@GetMapping("/review_post.do")
	public String showReview(LikeVO lVO, Model model, @SessionAttribute(value="lrDomain", required = false) LoginResultDomain lrDomain) {
		String user_id = "";
		
		lrDomain = (LoginResultDomain)model.getAttribute("lrDomain");
		user_id = lrDomain.getUser_id();
		lVO.setUser_id(user_id);

		// ��ȸ ��
		urService.hitsUp(lVO.getRv_num());

		// ���� ���� 
		model.addAttribute("reviewInfo", urService.showReview(lVO));

		// ��õ�� ����Ʈ
		model.addAttribute("likeUser", urService.showLikeUser(lVO.getRv_num()));
		 
   	    
//////////////////////////////// �Թ� ///////////////////////////////////////////////////////////////////////////////
		
		boolean likeStatus=false;
		int likeCnt=0;
		
		//���ƿ� ���¿� ����
		likeStatus = urService.likeStatusService(lVO);
		likeCnt = urService.likeCount(lVO.getRv_num());
		
		//���,���� ����Ʈ
		List<CommentDomain> combinedList = commService.getCommentsService(lVO.getRv_num());

		//�𵨿� ���
		model.addAttribute("likeStatus", likeStatus);
		model.addAttribute("likeCnt", likeCnt);
		model.addAttribute("comments", combinedList);
		model.addAttribute("id", user_id);
		
		return "/review/review_post";
	}//end showReview

	

	/**
	 * ����
	 * �ش� ���� â���� ���ƿ� ������ +1, �����ϸ� -1
	 * 2023.05.22
	 * @author KM
	 */
	@ResponseBody
	@GetMapping("/likeClicked.do")
	public String likeUpDown( LikeVO lVO, boolean liked) {
		
		String jsonObj="";
		
		if (liked) { //���ƿ�(����) ���¿��ٸ�
			//����Ʈ
			jsonObj = urService.likeOffService(lVO);

		} else {
			//�μ�Ʈ
			jsonObj =urService.likeOnService(lVO);
			
		} // end else
		
		return jsonObj;
	}//likeUpDown

	
	
	/**
	 * ��õ�� ����Ʈ ������Ʈ
	 * @param lVO
	 * @return
	 * @author KM
	 */
	@ResponseBody
	@GetMapping("/updateRecommendations.do")
	public String updateRecommendationsList ( LikeVO lVO ) {
		
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObjectTemp = null;
		
		//���ƿ� �� ���� ����Ʈ
		List<ReviewLikeUserDomain> list = urService.showLikeUser(lVO.getRv_num());
		
	    for (ReviewLikeUserDomain likeUser : list ) {
	    	jsonObjectTemp = new JSONObject();
	    	jsonObjectTemp.put("profile", likeUser.getProfile());
	    	jsonObjectTemp.put("nick_name", likeUser.getNick_name());
	        jsonArray.add(jsonObjectTemp);
	    }//end for
	    
	    return jsonArray.toString();
	}//updateRecommendationsList
	
	
	/**
    * ���� �� ���� �˻�(����������)
    * 2023.05.22
    * @author KM
    */
   @RequestMapping(value="/my_review.do",method= {RequestMethod.POST, RequestMethod.GET})
   public String reviewSearchProcess( MyReviewSearchVO mrsVO, Model model){
      
      if(mrsVO.getSearch()==null && mrsVO.getSearch_type()==0) {
         mrsVO.setSearch(""); //null ó��
      }//end if
             
      String user_id="";
      LoginResultDomain lrDomain = (LoginResultDomain)model.getAttribute("lrDomain");

	  user_id = lrDomain.getUser_id();
      mrsVO.setUser_id(user_id);
      
      List<MyReviewDomain> list = urService.myReviewService(mrsVO);
      
      model.addAttribute("myReviews", list) ;
      model.addAttribute("myReviewCnt", list.size()) ;
      
      return "/review/my_review";
	   }		
		
}//class
