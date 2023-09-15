package kr.co.sist.prj3.user.join.service;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.sist.prj3.user.join.dao.JoinDAO;
import kr.co.sist.prj3.user.join.vo.UserVO;
import kr.co.sist.util.cipher.DataEncrypt;


@Component
public class JoinService {

	
	@Autowired
	private JoinDAO jDAO;
	
	
	//ȸ�� ����
	public boolean joinService ( UserVO uVO ) throws UnsupportedEncodingException, GeneralSecurityException {
		int cnt=0;

		// ��й�ȣ SHA������ �ܹ��� ��ȣȭ ����
		uVO.setPass(DataEncrypt.messageDigest("MD5", uVO.getPass())); // set�� ������ get���� �Ϲ� ����� �ް� �Ϲ��� ��ȣȭ�� �� �� �ٽ� set�Ѵ�.
		
		
		//�׿��� ���� ��ȣȭ ������ ����� ��ȣȭ ����
		DataEncrypt de=new DataEncrypt("FsRt4SfY4US0IWtK4JPJsw==");//���� ����� Ű ���߿� ������. 
		uVO.setName(de.encryption(uVO.getName()));
		uVO.setEmail(de.encryption(uVO.getEmail()));
		uVO.setTel(de.encryption(uVO.getTel()));
		uVO.setAddr(de.encryption(uVO.getAddr())); 

		
		cnt = jDAO.insertUser(uVO);
		return cnt==1;
	}//joinService

	
	
	//id �ߺ�Ȯ�� 
	public String idDupService( String user_id ) throws NoSuchAlgorithmException, UnsupportedEncodingException, GeneralSecurityException {
		boolean available = false;
		JSONObject jsonObj = new JSONObject();
		
		//�ϴ� false
		jsonObj.put("available", available);
		
		
		available = jDAO.selectDupId(user_id)==null;
		
		
		if(available) {
			jsonObj.put("available", available);
		}//end if
		
		return jsonObj.toJSONString();
	
	}//idDupService

	
	
	
	
	//�г��� �ߺ�Ȯ��
	public String nickDupService( String nick_name ) throws NoSuchAlgorithmException, UnsupportedEncodingException, GeneralSecurityException {
		boolean available = false;
		JSONObject jsonObj = new JSONObject();
		
		//�ϴ� false
		jsonObj.put("available", available);
		
		
		available = jDAO.selectDupNick(nick_name)==null;
		
		
		if(available) {
			jsonObj.put("available", available);
		}//end if
		
		return jsonObj.toJSONString();
		
	}//nickDupService
	
	
		//�̸��� �ߺ�Ȯ��
		public String emailDupService( String email ) throws NoSuchAlgorithmException, UnsupportedEncodingException, GeneralSecurityException {
			boolean available = false;
			JSONObject jsonObj = new JSONObject();
			
			//�ϴ� false
			jsonObj.put("available", available);
			
			
			DataEncrypt de=new DataEncrypt("FsRt4SfY4US0IWtK4JPJsw==");//���� ����� Ű ���߿� ������. 
			//��ȣȭ�ؼ� �����ϱ�
			available = jDAO.selectDupEmail(de.encryption(email))==null;
			
			if(available) {
				jsonObj.put("available", available);
			}//end if
			
			return jsonObj.toJSONString();
			
		}//nickDupService


	
	
}//class
