package kr.co.sist.prj3.user.join.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import kr.co.sist.prj3.MyBatisHandler;
import kr.co.sist.prj3.user.join.vo.UserVO;


@Component
public class JoinDAO {

	//ȸ�� ���� 
	public int insertUser( UserVO uVO) {
		int cnt = 0;
		
		//1.MyBatis �ڵ鷯 ���
		SqlSession ss =MyBatisHandler.getInstance().getMyBatisHandler(false);
		
		//2. Handler�� ����ϱ�
		cnt = ss.insert("insertJoin", uVO);		
		
		//3. transaction �Ϸ��ϱ�
				if(cnt==1) {
					ss.commit();
				}
				
		//4. ���� ����
				if(ss!=null) {ss.close();}//end if
				
		return cnt;
	}//insertUser

	
	
	
	//id �ߺ�Ȯ�� 
	public String selectDupId( String user_id ) {
		
		String selectedId="";
		
		//1.MyBatis �ڵ鷯 ���
		SqlSession ss = MyBatisHandler.getInstance().getMyBatisHandler(false);
		
		//2.�ڵ鷯 ����ϱ� - ������ �����ϱ�
		selectedId = ss.selectOne("selectIdDup", user_id);
		
		//3.��ȸ��� ó��
		//4.MyBatis Handler �ݱ�
		if(ss!=null) {ss.close();}
		
		return selectedId;
		
	}

	
	//�г��� �ߺ�Ȯ��
	public String selectDupNick( String nick_name ) {
		String selectedNick="";
		
		//1.MyBatis �ڵ鷯 ���
		SqlSession ss = MyBatisHandler.getInstance().getMyBatisHandler(false);
		
		//2.�ڵ鷯 ����ϱ� - ������ �����ϱ�
		selectedNick = ss.selectOne("selectNickDup", nick_name);
		
		//3.��ȸ��� ó��
		//4.MyBatis Handler �ݱ�
		if(ss!=null) {ss.close();}
		
		return selectedNick;
	
	}


	
		//�̸��� �ߺ�Ȯ��
		public String selectDupEmail( String email ) {
			String selectedEmail="";
			
			//1.MyBatis �ڵ鷯 ���
			SqlSession ss = MyBatisHandler.getInstance().getMyBatisHandler(false);
			
			//2.�ڵ鷯 ����ϱ� - ������ �����ϱ�
			selectedEmail = ss.selectOne("selectEmailDup", email);
			
			//3.��ȸ��� ó��
			//4.MyBatis Handler �ݱ�
			if(ss!=null) {ss.close();}
			
			return selectedEmail;
		
		}
	
	
	
}//class
