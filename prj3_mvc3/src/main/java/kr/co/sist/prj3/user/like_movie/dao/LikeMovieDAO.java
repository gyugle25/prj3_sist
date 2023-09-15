package kr.co.sist.prj3.user.like_movie.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import kr.co.sist.prj3.MyBatisHandler;
import kr.co.sist.prj3.user.like_movie.domain.MyGradeDomain;
import kr.co.sist.prj3.user.like_movie.domain.MyMovieDomain;
import kr.co.sist.prj3.user.like_movie.vo.SelectTypeVO;

@Component
public class LikeMovieDAO {

	// ���Ǵ� ��ȭ
	public List<MyMovieDomain> selectMyMovie(SelectTypeVO stVO) {
		List<MyMovieDomain> list = new ArrayList<MyMovieDomain>();
		SqlSession ss = MyBatisHandler.getInstance().getMyBatisHandler(false);
		
		if(stVO.getSelect_type() == null) {
			list = ss.selectList("selectMyMovie", stVO);
		}else {
			list = ss.selectList("selectType", stVO);
		}// end else
		
		if(ss != null ) { ss.close(); }// end if
		
		return list;
	}// selectMyMovie
	
	public int selectCntMovie(SelectTypeVO stVO) {
		int cnt = 0;
		
		SqlSession ss = MyBatisHandler.getInstance().getMyBatisHandler(false);
		cnt = ss.selectOne("selectCntMovie", stVO);
		if(ss != null) { ss.close(); }// end if
		
		return cnt;
	}// selectCntMovie
	
	public int deleteMyMovie(SelectTypeVO stVO) {
		int cnt = 0;
		
		SqlSession ss = MyBatisHandler.getInstance().getMyBatisHandler(false);
		cnt = ss.delete("deleteMyMovie", stVO);
		
		if(cnt == 1) {
			System.out.println("���� ���� => commit");
			ss.commit();
		}else {
			System.out.println("�ѹ�");
			ss.rollback();
		}// end else
		
		if(ss != null) { ss.close(); }// end if
		
		return cnt;
	}// deleteMyMovie
	
	
	
	
	// ���� �� ����
	public List<MyGradeDomain> selectMyGrade(String id) {
		List<MyGradeDomain> list = new ArrayList<MyGradeDomain>();
		
		SqlSession ss = MyBatisHandler.getInstance().getMyBatisHandler(false);
		list = ss.selectList("myGrade", id);
		
		if (ss != null) {
			ss.close();
		} // end if
		
		return list;
	}// selectMyGrade
	
	public int selectCntGrade(String id) {
		int cnt = 0;
		
		SqlSession ss = MyBatisHandler.getInstance().getMyBatisHandler(false);
		cnt = ss.selectOne("gradeCnt", id);
		if(ss != null) { ss.close(); }// end if
		
		return cnt;
	}// selectCntGrade
	
	public int deleteMyGrade(String id) {
		return 0;
	}// deleteMyGrade
	
}
