package kr.co.sist.prj3.user.profile.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.co.sist.prj3.user.join.service.JoinService;
import kr.co.sist.prj3.user.login.domain.LoginResultDomain;
import kr.co.sist.prj3.user.profile.service.ProfileService;
import kr.co.sist.prj3.user.profile.vo.ProfileModifyVO;

/**
 * 프로필 컨트롤러
 * @author user
 *
 */ 

@Controller
@SessionAttributes({"lrDomain","rv_num"})
public class ProfileController {

	@Autowired(required = false)
	private ProfileService pService;
	
	@Autowired(required = false)
	private JoinService jService;
	
	/**
	 * 프로필 관리 폼 보여주기
	 * @param model
	 * @return
	 * @throws UnsupportedEncodingException 
	 * @throws GeneralSecurityException 
	 * @throws NoSuchAlgorithmException 
	 */
	@RequestMapping(value="/my_profile.do", method= {RequestMethod.POST, RequestMethod.GET})
	public String profileFrm( Model model ) throws UnsupportedEncodingException, NoSuchAlgorithmException, GeneralSecurityException {
		
		//세션에서 id 꺼내기
		LoginResultDomain lrDomain=(LoginResultDomain)model.getAttribute("lrDomain");
		String user_id=lrDomain.getUser_id();
		
		model.addAttribute("profile", pService.profileSetService(user_id));
		
		return "/profile/my_profile";
		
	}//profileFrm

	
	/**
	 * 닉네임 중복확인 - ajax로 가능할지 ok
	 * @param nickName
	 * @return
	 * @throws GeneralSecurityException 
	 * @throws UnsupportedEncodingException 
	 * @throws NoSuchAlgorithmException 
	 */
	@PostMapping("/nick_dup.do")
	@ResponseBody
	public String nickDupProcess( String nick_name) throws NoSuchAlgorithmException, UnsupportedEncodingException, GeneralSecurityException {
		String jsonObj="";
		jsonObj =jService.nickDupService(nick_name);
		return jsonObj;
		
	}//nickDupProcess
	
	
	 
	/**
	 * 프로필 수정(update)
	 * @param model
	 * @param request
	 * @return
	 * @throws GeneralSecurityException 
	 */
	@PostMapping("/modify_profile.do")
	public String profileModifyProcess(HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) throws GeneralSecurityException {

		String movePage="";
		LoginResultDomain lrDomain=null;
		

		// 1.파일 저장 경로 얻기
		//File saveDir = new File("C:/Users/user/git/prj3_mvc/prj3_mvc/src/main/webapp/upload");
		//File saveDir = new File("E:/dev/workspace_spring/prj3_mvc3/src/main/webapp/upload");
		File saveDir = new File("C:/Users/Kyum/git/prj3_sist");
		
		//파일최대크기,이름길이
		int maxFileSize = 1024 * 1024 * 10; // 10MB
		int maxFileNameLength = 30;
		long fileSize = 0;
		String originalFileName = "";

		try {
			MultipartRequest mr = new MultipartRequest(request, saveDir.getAbsolutePath(), maxFileSize, "UTF-8",
					new DefaultFileRenamePolicy());

			File uploadFile = mr.getFile("profile_upload_file");
			
			if ( uploadFile != null) { //업로드 파일이 있으면
                originalFileName = mr.getOriginalFileName("profile_upload_file");
                fileSize = uploadFile.length(); //파일 크기를 바이트 단위로 반환

                
                // 파일 확장자 검사
                String fileExtension = originalFileName.substring(originalFileName.lastIndexOf(".") + 1).toLowerCase();
                if (!fileExtension.equals("png") && !fileExtension.equals("jpg") && !fileExtension.equals("gif") && !fileExtension.equals("bmp")) {
                	String errorMessage = "올바른 파일 형식이 아닙니다. jpg, gif, bmp, png 형식의 파일만 등록 가능합니다.";
                    
                    // 기존 페이지로 리디렉트하고 alert 메시지를 사용자에게 보여줌
                    // 리다이렉트된 후에도 데이터가 유지되며, 한 번만 사용할 수 있음 (한 번 조회되면 데이터 삭제됨)
                    redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
                    return "redirect:/my_profile.do";
                }
                
                // 파일명과 파일 크기 제한 체크
                if (originalFileName.length() > maxFileNameLength || fileSize > maxFileSize) {
                	
                	// 기존 페이지로 리디렉트하고 alert 메시지를 사용자에게 보여줌
                    String errorMessage = "파일명이 너무 길거나 파일 크기가 너무 큽니다. 최대 "+maxFileNameLength+"자의 " + (maxFileSize / (1024 * 1024)) + "MB까지 허용됩니다.";
                    redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
                    return "redirect:/my_profile.do"; //전 페이지로 이동
                }//end if
            }//end if
		
		    
			
			ProfileModifyVO pVO = new ProfileModifyVO();
			pVO.setUser_id(mr.getParameter("user_id"));
			pVO.setProfile_msg(mr.getParameter("profile_msg"));
			pVO.setNick_name(mr.getParameter("nick_name"));
			pVO.setProfile(originalFileName); // 파일 업로드를 안했다면 ""

			
			// update 수행
			if (pService.profileModifyService(pVO)) {// 성공했다면 세션 정보 갱신하여 저장
				lrDomain = pService.resetUserInfoService(pVO.getUser_id());
				model.addAttribute("lrDomain", lrDomain);
					
				movePage = "redirect:/mypage.do";
			} // end if
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} // end catch

			return movePage;
		
	}// profileModifyProcess
	
	
}//class
