package kr.co.sist.prj3.user.mypage.service;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import kr.co.sist.prj3.user.mypage.dao.UserDAO;
import kr.co.sist.prj3.user.mypage.domain.ModifyInfoDomain;
import kr.co.sist.prj3.user.mypage.vo.ModifyInfoVO;
import kr.co.sist.util.cipher.DataDecrypt;
import kr.co.sist.util.cipher.DataEncrypt;

@Component
public class UserService {
	
	@Autowired(required=false)
	private UserDAO uDAO;
	
	@PostMapping()//��й�ȣ Ȯ��
	public boolean checkPassService(String user_id,String pass) throws GeneralSecurityException {
		
		pass=DataEncrypt.messageDigest("MD5", pass);//�Է��� pass�� ��ȣȭ
		
		boolean checkFlag=uDAO.passProcess(user_id,pass);
		
		
		return checkFlag;
	}//checkPassService
	
	
	@PostMapping()//ȸ������ select
	public ModifyInfoDomain showMyInfo(String user_id) throws UnsupportedEncodingException, GeneralSecurityException, GeneralSecurityException {
		
		//��ȣȭ�� ���� ��ȣȭ
		DataDecrypt dd=new DataDecrypt("FsRt4SfY4US0IWtK4JPJsw==");//���� ����� Ű ���߿� ������.
		
		ModifyInfoDomain mid=uDAO.selectMyInfo(user_id);
		
		mid.setName(dd.decryption(mid.getName()));
		mid.setEmail(dd.decryption(mid.getEmail()));
		mid.setTel(dd.decryption(mid.getTel()));
		mid.setAddr(dd.decryption(mid.getAddr()));
		
		return mid;
	}//checkPassService
	
	
	@PostMapping()//ȸ����������
	public int modifyInfo(ModifyInfoVO miVO) throws UnsupportedEncodingException, NoSuchAlgorithmException, GeneralSecurityException {
		
		DataEncrypt de=new DataEncrypt("FsRt4SfY4US0IWtK4JPJsw==");
		
		miVO.setTel(de.encryption(miVO.getTel()));
		miVO.setAddr(de.encryption(miVO.getAddr()));
		miVO.setEmail(de.encryption(miVO.getEmail()));
		
		
		int cnt=uDAO.updateInfo(miVO);
		
		return cnt;
	}//checkPassService
	
	
	@PostMapping()//ȸ��Ż��
	public int quitMember(String user_id) {
		int cnt=0;
		
		cnt=uDAO.updateQuitMember(user_id);
		
		return cnt;
	}//checkPassService
	
	
	
	

}//class
