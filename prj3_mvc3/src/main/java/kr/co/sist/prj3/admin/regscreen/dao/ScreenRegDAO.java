package kr.co.sist.prj3.admin.regscreen.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import kr.co.sist.prj3.MyBatisHandler;
import kr.co.sist.prj3.admin.regscreen.domain.ScreenInfoDomain;
import kr.co.sist.prj3.admin.regscreen.vo.ScreenInfoVO;

/**
 * ��������� DAO
 * @author user
 */
@Component
public class ScreenRegDAO {
	
	//����������ȭ�� select
	public ScreenInfoDomain selectOneMovie(int m_num) {
		ScreenInfoDomain sid=new ScreenInfoDomain();
		
		SqlSession ss=MyBatisHandler.getInstance().getMyBatisHandler(false);
		
		sid=ss.selectOne("kr.co.sist.prj3.screenInfo.selectOneMovie", m_num);
		if(ss!=null) {ss.close();}//end if
		
		return sid;
	}//selectOneMovie
	
	
	//�󿵵�� - �󿵽ð� update
	public int updateScreen(ScreenInfoVO siVO) {
		int cnt=0;
		SqlSession ss=MyBatisHandler.getInstance().getMyBatisHandler(false);
		
		cnt=ss.update("kr.co.sist.prj3.screenInfo.updateScreen",siVO);
		

		if(cnt==1) {ss.commit();}//end if
		
		if(ss!=null) {ss.close();}//end if
		
		return cnt;
	}//updateScreen

	//public static void main(String[] args) {
	//	System.out.println(new ScreenRegDAO().selectOneMovie(8));
	//}
}//class
