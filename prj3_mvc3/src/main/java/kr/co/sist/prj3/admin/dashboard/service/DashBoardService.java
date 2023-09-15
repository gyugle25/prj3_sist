package kr.co.sist.prj3.admin.dashboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.sist.prj3.admin.dashboard.dao.DashBoardDAO;
import kr.co.sist.prj3.admin.dashboard.domain.DashBoardDomain;

@Component
public class DashBoardService {

	@Autowired(required = false)
	private DashBoardDAO dbDAO;
	
	/**
	 * ���� ȸ�� ����
	 * @author KT
	 */
	public int cntJoinService() {
		
		int result=0;
		result = dbDAO.selectCntJoin();
		return result;
		
	}//cntJoinService

	
	/**
	 * ���� ȸ�� Ż��
	 * @author KT
	 */
	public int cntQuitService( ) {
		
		int result=0;
		result = dbDAO.selectCntQuit();
		return result;
	}//cntQuitService

	
	

	/**
	 * ���� ��ϵ� ��ȭ
	 * @author KT
	 */
	public int cntMoviesService( ) {
		int result=0;
		result = dbDAO.selectCntMovies();
		return result;
	}//cntMoviesService

	
	/**
	 * ���� �� ���� ��ȭ
	 * @author KT
	 */
	public int cntOnGoingService ( ) {
		int result=0;
		result = dbDAO.selectCntOnGoing();
		return result;
	}//cntOnGoingService

	
	
	/**
	 * ���� ������ 1�� ��ȭ
	 * @author KT
	 */
	public DashBoardDomain topReservationService ( ) {
		DashBoardDomain result = new DashBoardDomain();
		result = dbDAO.selectTopRate();
		return result;
	}//topReservationService

	
	/**
	 * �� ȸ����
	 * @return
	 */
	public int cntTotalUsersService( ) {
		int result=0;
		result = dbDAO.selectCntTotalUsers();
		return result;
	}//cntTotalUsersService

	
	
	/**
	 * �� ��ȭ��
	 * @return
	 */
	public int cntTotalMoviesService ( ) {
		int result=0;
		result = dbDAO.selectCntTotalMovies();
		return result;
	}//cntTotalMoviesService

	
	
	
	/**
	 * �� �����
	 * @return
	 */
	public int cntTotalReviewsService ( ) {
		int result=0;
		result = dbDAO.selectCntTotalReviews();
		return result;
	}//cntTotalReviewsService

	
	/**
	 * �� ������
	 * @return
	 */
	public int cntTotalGradesService ( ) {
		int result=0;
		result = dbDAO.selectCntTotalGrades();
		return result;
	}//cntTotalGradesService
	
	
	
}//class
