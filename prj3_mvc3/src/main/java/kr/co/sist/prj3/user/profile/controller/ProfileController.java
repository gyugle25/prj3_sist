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
 * ������ ��Ʈ�ѷ�
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
	 * ������ ���� �� �����ֱ�
	 * @param model
	 * @return
	 * @throws UnsupportedEncodingException 
	 * @throws GeneralSecurityException 
	 * @throws NoSuchAlgorithmException 
	 */
	@RequestMapping(value="/my_profile.do", method= {RequestMethod.POST, RequestMethod.GET})
	public String profileFrm( Model model ) throws UnsupportedEncodingException, NoSuchAlgorithmException, GeneralSecurityException {
		
		//���ǿ��� id ������
		LoginResultDomain lrDomain=(LoginResultDomain)model.getAttribute("lrDomain");
		String user_id=lrDomain.getUser_id();
		
		model.addAttribute("profile", pService.profileSetService(user_id));
		
		return "/profile/my_profile";
		
	}//profileFrm

	
	/**
	 * �г��� �ߺ�Ȯ�� - ajax�� �������� ok
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
	 * ������ ����(update)
	 * @param model
	 * @param request
	 * @return
	 * @throws GeneralSecurityException 
	 */
	@PostMapping("/modify_profile.do")
	public String profileModifyProcess(HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) throws GeneralSecurityException {

		String movePage="";
		LoginResultDomain lrDomain=null;
		

		// 1.���� ���� ��� ���
		//File saveDir = new File("C:/Users/user/git/prj3_mvc/prj3_mvc/src/main/webapp/upload");
		//File saveDir = new File("E:/dev/workspace_spring/prj3_mvc3/src/main/webapp/upload");
		File saveDir = new File("C:/Users/Kyum/git/prj3_sist");
		
		//�����ִ�ũ��,�̸�����
		int maxFileSize = 1024 * 1024 * 10; // 10MB
		int maxFileNameLength = 30;
		long fileSize = 0;
		String originalFileName = "";

		try {
			MultipartRequest mr = new MultipartRequest(request, saveDir.getAbsolutePath(), maxFileSize, "UTF-8",
					new DefaultFileRenamePolicy());

			File uploadFile = mr.getFile("profile_upload_file");
			
			if ( uploadFile != null) { //���ε� ������ ������
                originalFileName = mr.getOriginalFileName("profile_upload_file");
                fileSize = uploadFile.length(); //���� ũ�⸦ ����Ʈ ������ ��ȯ

                
                // ���� Ȯ���� �˻�
                String fileExtension = originalFileName.substring(originalFileName.lastIndexOf(".") + 1).toLowerCase();
                if (!fileExtension.equals("png") && !fileExtension.equals("jpg") && !fileExtension.equals("gif") && !fileExtension.equals("bmp")) {
                	String errorMessage = "�ùٸ� ���� ������ �ƴմϴ�. jpg, gif, bmp, png ������ ���ϸ� ��� �����մϴ�.";
                    
                    // ���� �������� ����Ʈ�ϰ� alert �޽����� ����ڿ��� ������
                    // �����̷�Ʈ�� �Ŀ��� �����Ͱ� �����Ǹ�, �� ���� ����� �� ���� (�� �� ��ȸ�Ǹ� ������ ������)
                    redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
                    return "redirect:/my_profile.do";
                }
                
                // ���ϸ�� ���� ũ�� ���� üũ
                if (originalFileName.length() > maxFileNameLength || fileSize > maxFileSize) {
                	
                	// ���� �������� ����Ʈ�ϰ� alert �޽����� ����ڿ��� ������
                    String errorMessage = "���ϸ��� �ʹ� ��ų� ���� ũ�Ⱑ �ʹ� Ů�ϴ�. �ִ� "+maxFileNameLength+"���� " + (maxFileSize / (1024 * 1024)) + "MB���� ���˴ϴ�.";
                    redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
                    return "redirect:/my_profile.do"; //�� �������� �̵�
                }//end if
            }//end if
		
		    
			
			ProfileModifyVO pVO = new ProfileModifyVO();
			pVO.setUser_id(mr.getParameter("user_id"));
			pVO.setProfile_msg(mr.getParameter("profile_msg"));
			pVO.setNick_name(mr.getParameter("nick_name"));
			pVO.setProfile(originalFileName); // ���� ���ε带 ���ߴٸ� ""

			
			// update ����
			if (pService.profileModifyService(pVO)) {// �����ߴٸ� ���� ���� �����Ͽ� ����
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
