package kr.co.sist.prj3.admin.member.service;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import kr.co.sist.prj3.admin.member.dao.MemberMgtDAO;
import kr.co.sist.prj3.admin.member.domain.MemberBrdDomain;
import kr.co.sist.prj3.admin.member.domain.MemberInfoDomain;
import kr.co.sist.util.cipher.DataDecrypt;
import kr.co.sist.util.cipher.DataEncrypt;

@Component
public class MemberMgtService {

	@Autowired(required = false)
	private MemberMgtDAO mmDAO;
	
	/**
	 * ���������� �˻� 
	 * @param name
	 * @return
	 * @throws UnsupportedEncodingException 
	 * @throws GeneralSecurityException 
	 * @throws  
	 */
	public List<MemberBrdDomain> memberSearchService(String name) throws UnsupportedEncodingException, GeneralSecurityException  {
		
		List<MemberBrdDomain> list = null;
		
		DataEncrypt de=new DataEncrypt("FsRt4SfY4US0IWtK4JPJsw==");
	
		 
		 if(!name.isEmpty()) {
			 name = de.encryption(name);
		 }//end if
		 
		 
		list = mmDAO.selectMembers(name);
		
		DataDecrypt dd = new DataDecrypt("FsRt4SfY4US0IWtK4JPJsw==");
		
		for(MemberBrdDomain mbd : list) {
			//�̸�, �ּ�, ��ȭ��ȣ ��ȣȭ�ؼ� �ٽ� ��������
			mbd.setName(dd.decryption(mbd.getName()));
			mbd.setAddr(dd.decryption(mbd.getAddr()));
			mbd.setTel(dd.decryption(mbd.getTel()));
		}//end for
		
		return list;
	}//memberSearchService

	/**
	 * id�� �˻�
	 * @param userId
	 * @return
	 * @throws UnsupportedEncodingException 
	 * @throws GeneralSecurityException 
	 * @throws NoSuchAlgorithmException 
	 */
	public MemberInfoDomain memberInfoService(String userId) throws UnsupportedEncodingException, NoSuchAlgorithmException, GeneralSecurityException {
		
		MemberInfoDomain mid=null;
		mid = mmDAO.selectMemberInfo(userId);
		
		
		DataDecrypt dd = new DataDecrypt("FsRt4SfY4US0IWtK4JPJsw==");
		
		mid.setName(dd.decryption(mid.getName()));
		mid.setAddr(dd.decryption(mid.getAddr()));
		mid.setTel(dd.decryption(mid.getTel()));
		mid.setEmail(dd.decryption(mid.getEmail()));
		
		
		return mid;
	}//memberInfoService

	
	/**
	 * id�� ����(update)
	 * @param userId
	 * @return
	 */
	public String memberRemoveService(String user_id) {
		
		JSONObject jsonObj = new JSONObject();
		Boolean success = false;
		
		int cnt = mmDAO.updateDeleteMember(user_id);
		
		//�⺻
		jsonObj.put("success",false);
		
		
		if(cnt==1) {//���� ������			
			jsonObj.put("success",true);
		}//end if
		
			return jsonObj.toJSONString();
		
		
	}//meberRemoveService
	
	
}//class
