package kr.co.sist.prj3.user.total_info.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.exceptions.PersistenceException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import kr.co.sist.prj3.user.login.domain.LoginResultDomain;
import kr.co.sist.prj3.user.total_info.domain.ActorDomain;
import kr.co.sist.prj3.user.total_info.domain.DirectorDomain;
import kr.co.sist.prj3.user.total_info.domain.GradeDomain;
import kr.co.sist.prj3.user.total_info.domain.MovieImgDomain;
import kr.co.sist.prj3.user.total_info.domain.ProduceDomain;
import kr.co.sist.prj3.user.total_info.service.TotalInfoService;
import kr.co.sist.prj3.user.total_info.vo.GradeVO;
import kr.co.sist.prj3.user.total_info.vo.LikeMovieVO;

@Controller
public class TotalInfoController {

	@Autowired(required = false)
	private TotalInfoService tis;

	// ù ������ �ε� �� ���⸦ �θ��� ���� ���� ������ ����Ǵ� ��ũ��Ʈ ������ ajax�� ���ؼ� �Ʒ� �ֿ����� ��θ� ź��.
	///////////////////////// ���� ����
	@GetMapping("/main_info.do")
	public String infoFrm(@RequestParam("m_num") int m_num, LikeMovieVO lmVO, Model model, HttpSession session) throws PersistenceException {
		int avgGrade = tis.searchAvgGrade(m_num); 
		System.out.println(m_num);

		// ����κ�
		model.addAttribute("movie", tis.searchCommInfo(lmVO));
		model.addAttribute("star", avgGrade);
		

		//�α��� ���� Ȯ��
	    if (session.getAttribute("lrDomain") != null) {
	        LoginResultDomain lrDomain = (LoginResultDomain)session.getAttribute("lrDomain");
	        lmVO.setUser_id(lrDomain.getUser_id());

	        boolean likeStatus = tis.movieLikeStatus(lmVO);
	        model.addAttribute("likeStatus", likeStatus);
	    } else {
	        // �α������� ���� ��� ���ƿ� ��� ������
	        model.addAttribute("likeStatus", false);
	    }

	    return "/total_info/main_info";
	}//infoFrm
	
	@ResponseBody
	@GetMapping("/add_like.do")
	public String addLike(LikeMovieVO lmVO, boolean liked, Model model, HttpSession session) {

		String jsonObj = "";
		
		if (session.getAttribute("lrDomain") != null) {
	        LoginResultDomain lrDomain = (LoginResultDomain) session.getAttribute("lrDomain");

	        // ���ƿ� �߰� �۾� ����
			if (liked) {
				lmVO.setUser_id(lrDomain.getUser_id());
				System.out.println("insert");
				jsonObj = tis.addLikeMovie(lmVO);
			} else {
				lmVO.setUser_id(lrDomain.getUser_id());
				System.out.println("delete");
				jsonObj = tis.removeLikeMovie(lmVO);
			} // end else
	    } 

		return jsonObj;
	}// addLike

//////////////////////////////////////   �ֿ�����   ///////////////////////////////////////////
	@ResponseBody
	@GetMapping("/detail_info.do")
	public String detailInfo(int m_num, Model model) throws PersistenceException, SQLException {

		JSONObject jsonObj = new JSONObject();

		///// �ٰŸ�
		String summary = tis.summaryInfo(m_num);
		jsonObj.put("summary", summary);

		////// ����
		List<DirectorDomain> directorList = tis.directorInfo(m_num);
		JSONArray directorArray = new JSONArray();

		for (DirectorDomain director : directorList) {
			JSONObject directorObj = new JSONObject();
			directorObj.put("d_img", director.getD_img());
			directorObj.put("d_name", director.getD_name());
			directorArray.add(directorObj);
		} // end for

		jsonObj.put("director", directorArray);

		// ���
		List<ActorDomain> actorList = tis.actorInfo(m_num);
		JSONArray actorArray = new JSONArray();

		for (ActorDomain actor : actorList) {
			JSONObject actorObj = new JSONObject();
			actorObj.put("a_img", actor.getA_img());
			actorObj.put("a_name", actor.getA_name());
			actorArray.add(actorObj);
		} // end for
		jsonObj.put("actor", actorArray);
		
		// ��ȭ �̹���
		List<MovieImgDomain> movieImgList = tis.movieImg(m_num);
        JSONArray movieImgArray = new JSONArray();
        System.out.println("==============="+movieImgList);
        for (MovieImgDomain mImg : movieImgList) {
        	JSONObject movieImgObj = new JSONObject(); 
        	movieImgObj.put("m_img", mImg.getM_img());
        	movieImgArray.add(movieImgObj);
        }//end for
        jsonObj.put("movieImg", movieImgArray);
		// ���⼭ �ٰŸ�, ����/���list�� ��� ���� jsonObj.toJSONString();�� ��ȯ
		return jsonObj.toJSONString();
	}// infoFrm

//////////////////////////////////////////   ����/�⿬��   ////////////////////////////////////////////
	@ResponseBody
	@GetMapping("/produce_info.do")
	public String CrewFrm(int m_num, Model model) throws PersistenceException {

		JSONObject jsonObj = new JSONObject();

		// ����
		List<DirectorDomain> directorList = tis.directorInfo(m_num);
		JSONArray directorArray = new JSONArray();

		for (DirectorDomain director : directorList) {
			JSONObject directorObj = new JSONObject();
			directorObj.put("d_img", director.getD_img());
			directorObj.put("d_name", director.getD_name());
			directorObj.put("d_eng", director.getD_eng());
			directorArray.add(directorObj);
		} // end for

		jsonObj.put("director", directorArray);

        //���
        List<ActorDomain> actorList = tis.actorInfo(m_num);
        JSONArray actorArray = new JSONArray();
        
        for (ActorDomain actor : actorList) {
        	JSONObject actorObj = new JSONObject(); 
        	actorObj.put("a_img", actor.getA_img());
        	actorObj.put("a_name", actor.getA_name());
        	actorObj.put("a_eng", actor.getA_eng());
        	actorObj.put("role", actor.getRole());
        	actorArray.add(actorObj);
        }//end for
        
        jsonObj.put("actor", actorArray);

		//������/��ȭ��
		ProduceDomain produce = tis.searchProduce(m_num);
		
		jsonObj.put("origin", produce.getOrigin());
		jsonObj.put("script", produce.getScript());
		jsonObj.put("produce", produce.getProduce());
		jsonObj.put("ration", produce.getRation());
		jsonObj.put("imports", produce.getImports());
        jsonObj.put("actor", actorArray);
		
			// ����,���,������/��ȭ�� �� ��� ���� jsonObj.toJSONString();�� ��ȯ
			return jsonObj.toJSONString();
	}// CrewFrm
	
	/////////////////////////////////   ����    ////////////////////////////////////////////
	@ResponseBody
	@GetMapping("/grade_info.do")
	public String gradeFrm(int m_num, Model model) throws PersistenceException {

		JSONObject jsonObj = new JSONObject();
		
		//�������	
		int avgGrade = tis.searchAvgGrade(m_num); 
		jsonObj.put("avgGrade", avgGrade);
		
		
		//�� ��
		int cntGrade = tis.cntGrade(m_num);
		jsonObj.put("cntGrade", cntGrade);	
		
		// ��ϵ� ���� ����Ʈ select
		List<GradeDomain> gradeList = tis.searchGrade(m_num);
		JSONArray gradeArray = new JSONArray();
		
		jsonObj.put("gradeSize", gradeList.size());

		for (GradeDomain grade : gradeList) {
		    JSONObject gradeObj = new JSONObject();
		    gradeObj.put("m_num", grade.getM_num());
		    gradeObj.put("g_num", grade.getG_num());
		    gradeObj.put("m_grade", grade.getM_grade());
		    gradeObj.put("comments", grade.getComments());
		    gradeObj.put("nick_name", grade.getNick_name());
		    gradeObj.put("input_date", grade.getInput_date());
		    gradeObj.put("user_id", grade.getUser_id());
		    gradeArray.add(gradeObj);
		}// end for

		jsonObj.put("grade", gradeArray);
		System.out.println(gradeArray);
		
		return jsonObj.toJSONString();
	}//gradeFrm

	/////////////////////////////////   ����    ////////////////////////////////////////////
	@ResponseBody
	@GetMapping("/gradeOld_info.do")
	public String gradeOldFrm(int m_num, Model model) throws PersistenceException {
		
		JSONObject jsonObj = new JSONObject();
		
		//�������	
		int avgGrade = tis.searchAvgGrade(m_num); 
		jsonObj.put("avgGrade", avgGrade);
		
		
		//�� ��
		int cntGrade = tis.cntGrade(m_num);
		jsonObj.put("cntGrade", cntGrade);	
		
		// ��ϵ� ���� ����Ʈ select
		List<GradeDomain> gradeList = tis.searchGradeOld(m_num);
		JSONArray gradeArray = new JSONArray();
		
		jsonObj.put("gradeSize", gradeList.size());
		
		for (GradeDomain grade : gradeList) {
			JSONObject gradeObj = new JSONObject();
			gradeObj.put("m_num", grade.getM_num());
			gradeObj.put("g_num", grade.getG_num());
			gradeObj.put("m_grade", grade.getM_grade());
			gradeObj.put("comments", grade.getComments());
			gradeObj.put("nick_name", grade.getNick_name());
			gradeObj.put("input_date", grade.getInput_date());
			gradeObj.put("user_id", grade.getUser_id());
			gradeArray.add(gradeObj);
		}// end for
		
		jsonObj.put("grade", gradeArray);
		System.out.println(gradeArray);
		
		return jsonObj.toJSONString();
	}//gradeFrm

	/////////////////////����, ������ �μ�Ʈ
	@RequestMapping(value = "/insertGrade_info.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String insertGradeFrm(@RequestParam("m_num") Integer m_num, GradeVO gVO, LikeMovieVO lmVO, Model model,
			HttpSession session) throws PersistenceException {

		//����, ������ ���
		if (session.getAttribute("lrDomain") != null) {
			//lrDomain�� �α��� ���� ���
			LoginResultDomain lrDomain = (LoginResultDomain) session.getAttribute("lrDomain");

			gVO.setUser_id(lrDomain.getUser_id());
			gVO.setNick_name(lrDomain.getNick_name());
			System.out.println(gVO);
			tis.addGrade(gVO);

		} // end if

		// ����κ�
		model.addAttribute("movie", tis.searchCommInfo(lmVO));

		//�α��� ���� Ȯ��
		if (session.getAttribute("lrDomain") != null) {
			LoginResultDomain lrDomain = (LoginResultDomain) session.getAttribute("lrDomain");
			lmVO.setUser_id(lrDomain.getUser_id());

			boolean likeStatus = tis.movieLikeStatus(lmVO);
			model.addAttribute("likeStatus", likeStatus);
		} else {
			// �α������� ���� ��� ���ƿ� ��� ������
			model.addAttribute("likeStatus", false);
		} // end else

		return "/total_info/main_info";
	}// insertGradeFrm

	@PostMapping("/delete_grade.do")
	public String deleteGrade(@RequestParam("m_num") int m_num, @RequestParam("g_num") int g_num, @RequestParam("user_id") String user_id, Model model, HttpSession session) {
	    
	    GradeVO gVO = new GradeVO();
	    
	    gVO.setM_num(m_num);
	    gVO.setG_num(g_num);
	    gVO.setUser_id(user_id);
	    
	    tis.removeGrade(gVO);
	    
	    return "redirect:/main_info.do?m_num="+m_num;
	}
	
	
	
	
}// class
