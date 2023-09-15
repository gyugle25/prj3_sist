package kr.co.sist.prj3.user.profile.service;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.sist.prj3.user.join.dao.JoinDAO;
import kr.co.sist.prj3.user.login.domain.LoginResultDomain;
import kr.co.sist.prj3.user.login.vo.LoginVO;
import kr.co.sist.prj3.user.profile.dao.ProfileDAO;
import kr.co.sist.prj3.user.profile.domain.MyProfileDomain;
import kr.co.sist.prj3.user.profile.vo.ProfileModifyVO;
import kr.co.sist.util.cipher.DataDecrypt;
import kr.co.sist.util.cipher.DataEncrypt;

@Component
public class ProfileService {

	@Autowired(required = false)
	private ProfileDAO pDAO;
	
	@Autowired(required = false)
	private JoinDAO jDAO;

	
	/**
	 * �г������� �˻�
	 * @param nickName
	 * @return
	 */
	public String nickDupService (String nick_name) {
		
		boolean available = false;
		JSONObject jsonObj = new JSONObject();
		 
		//�ϴ� false
		jsonObj.put("available", available);
		
		available = jDAO.selectDupNick(nick_name)==null;
		System.out.println(nick_name+"---"+available);
		
		if(available) {
			jsonObj.put("available", available);
		}//end if
		
		return jsonObj.toJSONString();
		
	}//selectChkNick

	
	
	
	/**
	 * ������, �г��� ����
	 * @param pVO
	 * @return
	 */
	public boolean profileModifyService(ProfileModifyVO pVO) {
		
		int cnt = pDAO.updateProfile(pVO);
		return cnt==1;
		
	}//updateProfile
	
	
	
	
	/**
	 * �ڱ�Ұ��� ���� (�������� ���ǿ���)
	 * @param user_id
	 * @return
	 * @throws UnsupportedEncodingException 
	 * @throws GeneralSecurityException 
	 * @throws NoSuchAlgorithmException 
	 */
/*	public String profileMsgSetService(String user_id) {
		
		String profileMsg = "";
		profileMsg = pDAO.selectProfileMsg(user_id);
			
		return profileMsg;
		
		
	}//profileSetService
*/	
	
	public MyProfileDomain profileSetService(String user_id) throws UnsupportedEncodingException, NoSuchAlgorithmException, GeneralSecurityException {
		
		MyProfileDomain profile = null;
		profile = pDAO.selectProfile(user_id);
		DataDecrypt dd=new DataDecrypt("FsRt4SfY4US0IWtK4JPJsw==");
		profile.setName(dd.decryption(profile.getName()));
		return profile;
		
	}//profileService
	
	

	// ������ ���� �� �ٽ� ����
			public LoginResultDomain resetUserInfoService(String user_id) throws UnsupportedEncodingException, GeneralSecurityException {
				
				LoginResultDomain lrDomain = null;
				
				lrDomain = pDAO.reselectUserInfo(user_id);

				if(lrDomain!=null) {
				
				DataDecrypt dd=new DataDecrypt("FsRt4SfY4US0IWtK4JPJsw==");
				lrDomain.setName(dd.decryption(lrDomain.getName()));
				
				}
				
				return lrDomain;
				
			}//resetUserInfoService
	
	
	
	
}//class
