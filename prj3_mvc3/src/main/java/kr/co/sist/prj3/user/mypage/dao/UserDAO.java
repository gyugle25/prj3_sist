package kr.co.sist.prj3.user.mypage.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import kr.co.sist.prj3.MyBatisHandler;
import kr.co.sist.prj3.admin.regscreen.domain.ScreenInfoDomain;
import kr.co.sist.prj3.user.mypage.domain.ModifyInfoDomain;
import kr.co.sist.prj3.user.mypage.vo.ModifyInfoVO;

@Component
public class UserDAO {
	
	
	public boolean passProcess(String user_id,String pass) {
		boolean isPasswordCorrect = false;

	    // 1. MyBatisHandler ���
	    SqlSession ss = MyBatisHandler.getInstance().getMyBatisHandler(false);
	    
	    //Map<String, String> map=new HashMap<String, String>();
	    //map.put("user_id", user_id);
		
	    // 2. Handler ���
		String selectPass = ss.selectOne("kr.co.sist.prj3.userMapper.selectUserInfo", user_id);
		
	    // 3. �����ͺ��̽��� ����� ��й�ȣ�� �Էµ� ��й�ȣ ��
	    if (selectPass != null && selectPass.equals(pass)) {
	        isPasswordCorrect = true;
	    }//end if

	    if (ss != null) { ss.close(); }//end if
	    
	    return isPasswordCorrect;
	}//passProcess
	
	//ȸ���������� �� select
	public ModifyInfoDomain selectMyInfo(String user_id) {
		ModifyInfoDomain mid=new ModifyInfoDomain();
		
		SqlSession ss=MyBatisHandler.getInstance().getMyBatisHandler(false);
		
		mid=ss.selectOne("kr.co.sist.prj3.userMapper.selectMemberInfo", user_id);
		
		if(ss!=null) {ss.close();}//end if
		
		
		return mid;
	}//selectMyInfo
	
	
	//ȸ������ ����
	public int updateInfo(ModifyInfoVO miVO) {
		int cnt=0;
		SqlSession ss=MyBatisHandler.getInstance().getMyBatisHandler(false);
		
		cnt=ss.update("kr.co.sist.prj3.userMapper.updateMemberInfo",miVO);
		

		if(cnt==1) {ss.commit();}//end if
		
		if(ss!=null) {ss.close();}//end if
		
		return cnt;
	}//updateInfo
	
	
	//ȸ��Ż��
	public int updateQuitMember(String user_id) {
		int cnt=0;
		
		// 1.MyBatis �ڵ鷯 ���
		SqlSession ss = MyBatisHandler.getInstance().getMyBatisHandler(false);

		// 2. Handler�� ����ϱ�
		cnt = ss.update("kr.co.sist.prj3.userMapper.updateQuitMember", user_id);
				
		if(cnt==1) {ss.commit();}
				
		// 4. ���� ����
		if (ss != null) {
			ss.close();
		} // end if
		
		return cnt;
	}//updateQuitMember

}//class
