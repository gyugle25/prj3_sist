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
 * ��� ��Ʈ�ѷ�
 * @author user
 *
 */
@Controller
public class CommentController {

	@Autowired(required = false)
	private CommentService commService;

	/**
	 * ��� ��� - ajax
	 * @return
	 */
	@RequestMapping(value="/add_comment.do" , method= {RequestMethod.POST,RequestMethod.GET})
	public String addCommentProcess( CommVO cVO )  {
		String resultPage="";
		int rv_num = cVO.getRv_num();
		
		if(commService.addCommentService(cVO)) {
			System.out.println("��� ��� ����");
			resultPage="redirect:/review_post.do?rv_num="+rv_num;
		
		}//end if
		
		return resultPage;
		
	}//addCommentProcess
	

	/**
	 * ��� ����(Ȥ�� ����)
	 * @param comNum ��۹�ȣ
	 * @return
	 */
	@ResponseBody
	@GetMapping("delete_comment.do")
	public String removeCommentProcess( int com_num, boolean has_replies)  {
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("resultFlag", false);

		
		if(has_replies) { //����� �ִٸ�
			if(commService.updateCommentService(com_num)) {
				jsonObj.put("resultFlag", true);
			}//end if
			
		}else { //����� ���ٸ�
			commService.removeCommentService(com_num);
			jsonObj.put("resultFlag", true);
		}//end else
		

		return jsonObj.toJSONString();
		
		//return "redirect: review_post.do"; //���� �� ��������

	}//removeCommentProcess
	


	
	/**
	 * ���� ��� - ajax
	 * @param rpVO
	 * @return
	 */
	@RequestMapping(value="/add_reply.do" , method= {RequestMethod.POST,RequestMethod.GET})
	public String addReplyProcess( ReplyVO rpVO , @RequestParam("rv_num") int rv_num)   {
		String resultPage="";

		
		if(commService.addReplyService(rpVO)) {
			System.out.println("���� ��� ����");
			resultPage="redirect:/review_post.do?rv_num="+rv_num;
		}//end if
		
		return resultPage;
	
	}//addReplyProcess

	
	
	
	/**
	 * ���� ����
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
