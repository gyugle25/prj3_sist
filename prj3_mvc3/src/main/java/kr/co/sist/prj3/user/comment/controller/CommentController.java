package kr.co.sist.prj3.user.comment.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.sist.prj3.user.comment.service.CommentService;
import kr.co.sist.prj3.user.comment.vo.CommVO;
import kr.co.sist.prj3.user.comment.vo.ReplyVO;

/**
 * 댓글 컨트롤러
 * @author user
 *
 */
@Controller
public class CommentController {

	@Autowired(required = false)
	private CommentService commService;

	/**
	 * 댓글 등록 - ajax
	 * @return
	 */
	@RequestMapping(value="/add_comment.do" , method= {RequestMethod.POST,RequestMethod.GET})
	public String addCommentProcess( CommVO cVO )  {
		String resultPage="";
		int rv_num = cVO.getRv_num();
		
		if(commService.addCommentService(cVO)) {
			System.out.println("댓글 등록 성공");
			resultPage="redirect:/review_post.do?rv_num="+rv_num;
		
		}//end if
		
		return resultPage;
		
	}//addCommentProcess
	

	/**
	 * 댓글 삭제(혹은 수정)
	 * @param comNum 댓글번호
	 * @return
	 */
	@ResponseBody
	@GetMapping("delete_comment.do")
	public String removeCommentProcess( int com_num, boolean has_replies)  {
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("resultFlag", false);

		
		if(has_replies) { //대댓이 있다면
			if(commService.updateCommentService(com_num)) {
				jsonObj.put("resultFlag", true);
			}//end if
			
		}else { //대댓이 없다면
			commService.removeCommentService(com_num);
			jsonObj.put("resultFlag", true);
		}//end else
		

		return jsonObj.toJSONString();
		
		//return "redirect: review_post.do"; //리뷰 글 페이지로

	}//removeCommentProcess
	


	
	/**
	 * 대댓글 등록 - ajax
	 * @param rpVO
	 * @return
	 */
	@RequestMapping(value="/add_reply.do" , method= {RequestMethod.POST,RequestMethod.GET})
	public String addReplyProcess( ReplyVO rpVO , @RequestParam("rv_num") int rv_num)   {
		String resultPage="";

		
		if(commService.addReplyService(rpVO)) {
			System.out.println("대댓글 등록 성공");
			resultPage="redirect:/review_post.do?rv_num="+rv_num;
		}//end if
		
		return resultPage;
	
	}//addReplyProcess

	
	
	
	/**
	 * 대댓글 삭제
	 * @param replyNum
	 * @return
	 */
	@ResponseBody
	@GetMapping("delete_reply.do")
	public String removeReplyProcess( int reply_num ) {
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("resultFlag", false);

		
			if(commService.removeReplyService(reply_num)) {
				jsonObj.put("resultFlag", true);
			}//end if
		

		return jsonObj.toJSONString();
		
	}//removeReplyProcess


	
	
}//class
