package kr.co.sist.prj3.admin.dashboard.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import kr.co.sist.prj3.MyBatisHandler;
import kr.co.sist.prj3.admin.dashboard.domain.DashBoardDomain;

@Component
public class DashBoardDAO {

	/**
	 * ���� ȸ�� ����
	 * @return KT
	 */
	public int selectCntJoin() {
		
		int result=0;
		
		//1.MyBatis �ڵ鷯 ���
		SqlSession ss =MyBatisHandler.getInstance().getMyBatisHandler(false);
		
		//2. Handler�� ����ϱ�
		result = ss.selectOne("selectJoinCnt");		
		
		//3. transaction �Ϸ��ϱ�
				
		//4. ���� ����
		if(ss!=null) {ss.close();}//end if
				
		return result;
		
	}//selectCntJoin

	
	/**
	 * ���� ȸ�� Ż��
	 * @return KT
	 */
	public int selectCntQuit( ) {
		
		int result=0;
		
		//1.MyBatis �ڵ鷯 ���
		SqlSession ss =MyBatisHandler.getInstance().getMyBatisHandler(false);
		
		//2. Handler�� ����ϱ�
		result = ss.selectOne("selectQuitCnt");		
		
		//3. transaction �Ϸ��ϱ�
				
		//4. ���� ����
		if(ss!=null) {ss.close();}//end if
				
		return result;
	}//selectCntQuit

	
	

	/**
	 * ���� ��ϵ� ��ȭ
	 * @author KT
	 */
	public int selectCntMovies ( ) {
		int result=0;
		
		//1.MyBatis �ڵ鷯 ���
		SqlSession ss =MyBatisHandler.getInstance().getMyBatisHandler(false);
		
		//2. Handler�� ����ϱ�
		result = ss.selectOne("selectMovieCnt");		
		
		//3. transaction �Ϸ��ϱ�
				
		//4. ���� ����
		if(ss!=null) {ss.close();}//end if
		
		return result;
	}//selectCntMovies

	
	/**
	 * ���� �� ���� ��ȭ
	 * @author KT
	 */
	public int selectCntOnGoing ( ) {
		int result=0;
		
		//1.MyBatis �ڵ鷯 ���
		SqlSession ss =MyBatisHandler.getInstance().getMyBatisHandler(false);
		
		//2. Handler�� ����ϱ�
		result = ss.selectOne("selectCntOnGoing");		
		
		//3. transaction �Ϸ��ϱ�
				
		//4. ���� ����
		if(ss!=null) {ss.close();}//end if
		
		return result;
	}//selectCntOnGoing

	/**
	 * ���� �� ���� ��ȭ
	 * @author KT
	 */
	public DashBoardDomain selectTopRate ( ) {
		DashBoardDomain result = new DashBoardDomain();
		
		//1.MyBatis �ڵ鷯 ���
		SqlSession ss =MyBatisHandler.getInstance().getMyBatisHandler(false);
		
		//2. Handler�� ����ϱ�
		result = ss.selectOne("selectRateTop");		
		
		//3. transaction �Ϸ��ϱ�
		
		//4. ���� ����
		if(ss!=null) {ss.close();}//end if
		
		return result;
	}//selectCntOnGoing

	
	
	/**
	 * ���� ������ 1�� ��ȭ
	 * @author KM
	 */
	public String selectTopReservation  ( ) {
		String result="";
		return result;
	}//selectCntTopReservation

	
	/**
	 * �� ȸ����
	 * @author KM
	 */
	public int selectCntTotalUsers( ) {
		int result = 0;

		// 1.MyBatis �ڵ鷯 ���
		SqlSession ss = MyBatisHandler.getInstance().getMyBatisHandler(false);

		// 2. Handler�� ����ϱ�
		result = ss.selectOne("selectTotalCntMember");

		// 4. ���� ����
		if (ss != null) {
			ss.close();
		} // end if

		return result;
	}//selectCntTotalUsers

	
	
	/**
	 * �� ��ȭ��
	 * @author KM
	 */
	public int selectCntTotalMovies  ( ) {
		int result = 0;

		// 1.MyBatis �ڵ鷯 ���
		SqlSession ss = MyBatisHandler.getInstance().getMyBatisHandler(false);

		// 2. Handler�� ����ϱ�
		result = ss.selectOne("selectTotalCntMovie");

		// 4. ���� ����
		if (ss != null) {
			ss.close();
		} // end if

		return result;
	}//selectCntTotalMovies

	
	
	
	/**
	 * �� �����
	 * @author KM
	 */
	public int selectCntTotalReviews  ( ) {
		int result = 0;

		// 1.MyBatis �ڵ鷯 ���
		SqlSession ss = MyBatisHandler.getInstance().getMyBatisHandler(false);

		// 2. Handler�� ����ϱ�
		result = ss.selectOne("selectTotalCntReview");

		// 4. ���� ����
		if (ss != null) {
			ss.close();
		} // end if

		return result;
	}//selectCntTotalReviews

	
	/**
	 * �� ������
	 * @author KM
	 */
	public int selectCntTotalGrades  ( ) {
		int result = 0;

		// 1.MyBatis �ڵ鷯 ���
		SqlSession ss = MyBatisHandler.getInstance().getMyBatisHandler(false);

		// 2. Handler�� ����ϱ�
		result = ss.selectOne("selectTotalCntGrade");

		// 4. ���� ����
		if (ss != null) {
			ss.close();
		} // end if

		return result;
	}//selectCntTotalGrades
	
	
	
	
}//class
