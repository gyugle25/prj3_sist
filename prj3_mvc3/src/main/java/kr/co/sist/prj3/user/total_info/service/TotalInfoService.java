package kr.co.sist.prj3.user.total_info.service;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import kr.co.sist.prj3.user.total_info.dao.TotalInfoDAO;
import kr.co.sist.prj3.user.total_info.domain.ActorDomain;
import kr.co.sist.prj3.user.total_info.domain.CommInfoDomain;
import kr.co.sist.prj3.user.total_info.domain.DirectorDomain;
import kr.co.sist.prj3.user.total_info.domain.GradeDomain;
import kr.co.sist.prj3.user.total_info.domain.MovieImgDomain;
import kr.co.sist.prj3.user.total_info.domain.ProduceDomain;
import kr.co.sist.prj3.user.total_info.vo.GradeVO;
import kr.co.sist.prj3.user.total_info.vo.LikeGradeVO;
import kr.co.sist.prj3.user.total_info.vo.LikeMovieVO;

@Component
public class TotalInfoService {

	@Autowired(required = false)
	private TotalInfoDAO tiDAO;

	// ���� ����
	public CommInfoDomain searchCommInfo(LikeMovieVO lmVO) {
		CommInfoDomain cid = tiDAO.selectcommInfo(lmVO);

		return cid;
	}// searchCommInfo

	// ���ƿ� ����
	public boolean movieLikeStatus(LikeMovieVO lmVO) {
		
		String result="";
		boolean likeStatus = false;
		
	    result = tiDAO.selectLikeMovie(lmVO);

	    if(result != null && !result.isEmpty()) { //���ƿ� ���ڵ尡 ��ȸ�ȴٸ�
			likeStatus = true;
		}//end if
	    
	    return likeStatus;
	}// movieLikeStatus
			
	// ���ƿ� �߰�
	public String addLikeMovie(LikeMovieVO lmVO) {
		boolean resultFlag=false;
		JSONObject jsonObj = new JSONObject();
		
	    int cnt = tiDAO.insertLikeMovie(lmVO);
	    
	    if(cnt==1) {
			jsonObj.put("resultFlag", resultFlag);
		}//end if
		
		return jsonObj.toJSONString();
	}
			
	// ���ƿ� ����
	public String removeLikeMovie(LikeMovieVO lmVO) {
		boolean resultFlag=false;
		JSONObject jsonObj = new JSONObject();
		
		int cnt = tiDAO.deleteLikeMovie(lmVO);
		
		if(cnt==1) {
			jsonObj.put("resultFlag", resultFlag);
		}//end if
		
		return jsonObj.toJSONString();
	}// removeLikeMove

	// �ٰŸ�
	public String summaryInfo(int m_num) {
		String summary = "";
		try {
			summary = tiDAO.selectSummary(m_num);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return summary;
	}// summaryInfo

	// ���� (�ֿ�����, �⿬/����)
	public List<DirectorDomain> directorInfo(int m_num) {

		List<DirectorDomain> list = tiDAO.selectDirector(m_num);

		return list;
	}// directorInfo

	// ��� (�ֿ�����, �⿬/����)
	public List<ActorDomain> actorInfo(int m_num) {
		List<ActorDomain> list = tiDAO.selectActor(m_num);

		return list;
	}// actorInfo
	
	// ��ȭ ���� 
	public List<MovieImgDomain> movieImg(int m_num) {
		List<MovieImgDomain> list = tiDAO.selectMovieImg(m_num);
		return list;
	}// movieImg
	
	// ���ۻ�
	public ProduceDomain searchProduce(int m_num) {
		ProduceDomain pd = tiDAO.selectProduce(m_num);
		
		return pd;
	}// searchProduce

	///////////////////////////////////// ���� ///////////////
	//��� ����
	public int searchAvgGrade(int m_num) {
		int avgGrade=0;
		avgGrade = tiDAO.selectAvgGrade(m_num);
		
		return avgGrade;
	}// avgGradeUser
	
	//�� ��
	public int cntGrade(int m_num) {
		int cntGrade=0;
		cntGrade = tiDAO.selectCntGrade(m_num);
		
		return cntGrade;
	}// avgGradeUser
	
	
	// ����
	public List<GradeDomain> searchGrade(int m_num) {
		List<GradeDomain> list = new ArrayList<GradeDomain>();

		list = tiDAO.selectGrade(m_num);
		
		return list;
	}// searchGrade

	// ����
	public List<GradeDomain> searchGradeOld(int m_num) {
		List<GradeDomain> list = new ArrayList<GradeDomain>();
		
		list = tiDAO.selectGradeOld(m_num);
		
		return list;
	}// searchGradeOld

	// ����, ������ ���
		public boolean addGrade(GradeVO gVO) {
			
			boolean success=false;
			
			success = tiDAO.insertGrade(gVO)==1;
			
			return success;
		}// addGrade

	public boolean removeGrade(GradeVO gVO) {
		int cnt = tiDAO.deleteGrade(gVO);
		
		return cnt == 1;
	}// removeGrade


	public void addLikeGrade(LikeGradeVO lgVO) {

	}// addLikeGrade

	public int removeLikeGrade(LikeGradeVO lgVO) {
		return 0;
	}// removeLikeGrade

}// class
