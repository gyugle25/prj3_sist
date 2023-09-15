package kr.co.sist.prj3.admin.member.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import kr.co.sist.prj3.MyBatisHandler;
import kr.co.sist.prj3.admin.member.domain.MemberBrdDomain;
import kr.co.sist.prj3.admin.member.domain.MemberInfoDomain;

@Component
public class MemberMgtDAO {

	
	/**
	 * 유저명으로 검색
	 * @param name
	 * @return
	 */
	public List<MemberBrdDomain> selectMembers(String name) {
		List<MemberBrdDomain> list = null;
		SqlSession ss = null;
		
		try {
		// 1.MyBatis 핸들러 얻기
		ss = MyBatisHandler.getInstance().getMyBatisHandler(false);

		// 2. Handler를 사용하기
		list = ss.selectList("selectMembers", name);

		}catch(Exception e) {
			
			// DB 연결 오류 또는 SQL 예외 처리
	        e.printStackTrace();
			
		}finally {
		
		// 4. 연결 끊기
		if (ss != null) {
			ss.close();
		} // end if
		
	}//end finally
		
		return list;
		
	}//selectMember

	
	/**
	 * id로 검색
	 * @param userId
	 * @return
	 */
	public MemberInfoDomain selectMemberInfo(String userId) {
		MemberInfoDomain mid = null;

		// 1.MyBatis 핸들러 얻기
		SqlSession ss = MyBatisHandler.getInstance().getMyBatisHandler(false);

		// 2. Handler를 사용하기
		mid = ss.selectOne("selectMemInfo", userId);

		// 4. 연결 끊기
		if (ss != null) {
			ss.close();
		} // end if

		return mid;

	}// selectMemberInfo

	
	/**
	 * id로 검색하여 n/a로 수정
	 * @param userId
	 * @return
	 */
	public int updateDeleteMember(String user_id) {

		int cnt = 0;

		// 1.MyBatis 핸들러 얻기
		SqlSession ss = MyBatisHandler.getInstance().getMyBatisHandler(false);

		// 2. Handler를 사용하기
		cnt = ss.update("updateDelUser", user_id);
		
		if(cnt==1) {ss.commit();}
		
		// 4. 연결 끊기
		if (ss != null) {
			ss.close();
		} // end if

		return cnt;

		
	}//updateDeleteMember
	
	
	
}//class
