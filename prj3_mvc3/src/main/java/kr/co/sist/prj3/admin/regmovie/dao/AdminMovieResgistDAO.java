package kr.co.sist.prj3.admin.regmovie.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.sist.prj3.MyBatisHandler;
import kr.co.sist.prj3.admin.regmovie.vo.AdminActorVO;
import kr.co.sist.prj3.admin.regmovie.vo.AdminDirectVO;
import kr.co.sist.prj3.admin.regmovie.vo.AdminMovieVO;

/**
 * ��ȭ��� DAO
 * @author user
 */
@Component
public class AdminMovieResgistDAO {
	
	public int sequenceMnum() {
		int m_num=0;
		
		SqlSession ss=MyBatisHandler.getInstance().getMyBatisHandler(false);
				
		m_num=ss.selectOne("kr.co.sist.prj3.registMapper.selectMovieKey");
		
		if(ss!=null) {ss.close();}//end if
		
		return m_num;
	}//sequenceMnum
	
	
	//��ȭ�������
	public void insertMovieInfo(AdminMovieVO amVO) {
		
		//1.MyBatisHandler���
		SqlSession ss=MyBatisHandler.getInstance().getMyBatisHandler(false);
		
		//2.Handler���
		int cnt=ss.insert("kr.co.sist.prj3.registMapper.insertMovie",amVO);
		
		//3. transaction�Ϸ�
		if( cnt==1) {
			ss.commit();
		}
		
		if(ss!=null) {ss.close();}
		
		
	}//insertMovieInfo
	
	
	//�����������
	public void insertDirectorInfo(AdminDirectVO[] adVO) {
		
		//1.MyBatisHandler���
		SqlSession ss=MyBatisHandler.getInstance().getMyBatisHandler(false);
		
		//2.Handler���
		for(int i=0; i<adVO.length; i++) {
			
			int cnt=ss.insert("kr.co.sist.prj3.registMapper.insertDirector",adVO[i]);
			
			//3. transaction�Ϸ�
			if( cnt==1) {
				ss.commit();
			}//end if
			
		}//end for
				
		if(ss!=null) {ss.close();}
		
	}//insertMovieInfo
	
	
	//����������
	public void insertActorInfo(AdminActorVO[] aaVO) {
		
		//1.MyBatisHandler���
		SqlSession ss=MyBatisHandler.getInstance().getMyBatisHandler(false);
			
		//2.Handler���
		for(int i=0; i<aaVO.length; i++) {
					
			int cnt=ss.insert("kr.co.sist.prj3.registMapper.insertActor",aaVO[i]);
					
			//3. transaction�Ϸ�
			if( cnt==1) {
				ss.commit();
			}//end if
					
		}//end for
						
		if(ss!=null) {ss.close();}
		
	}//insertMovieInfo
	
	
	//������������
	public int deleteDirector(int dNum) {
		int num=0;
		
		return num;
	}//deleteDirector
	
	
	//�����������
	public int deleteActor(int aNum) {
		int num=0;
		
		return num;
	}//deleteDirector
	

}//class
