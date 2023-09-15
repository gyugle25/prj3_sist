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
	 * ���������� �˻�
	 * @param name
	 * @return
	 */
	public List<MemberBrdDomain> selectMembers(String name) {
		List<MemberBrdDomain> list = null;
		SqlSession ss = null;
		
		try {
		// 1.MyBatis �ڵ鷯 ���
		ss = MyBatisHandler.getInstance().getMyBatisHandler(false);

		// 2. Handler�� ����ϱ�
		list = ss.selectList("selectMembers", name);

		}catch(Exception e) {
			
			// DB ���� ���� �Ǵ� SQL ���� ó��
	        e.printStackTrace();
			
		}finally {
		
		// 4. ���� ����
		if (ss != null) {
			ss.close();
		} // end if
		
	}//end finally
		
		return list;
		
	}//selectMember

	
	/**
	 * id�� �˻�
	 * @param userId
	 * @return
	 */
	public MemberInfoDomain selectMemberInfo(String userId) {
		MemberInfoDomain mid = null;

		// 1.MyBatis �ڵ鷯 ���
		SqlSession ss = MyBatisHandler.getInstance().getMyBatisHandler(false);

		// 2. Handler�� ����ϱ�
		mid = ss.selectOne("selectMemInfo", userId);

		// 4. ���� ����
		if (ss != null) {
			ss.close();
		} // end if

		return mid;

	}// selectMemberInfo

	
	/**
	 * id�� �˻��Ͽ� n/a�� ����
	 * @param userId
	 * @return
	 */
	public int updateDeleteMember(String user_id) {

		int cnt = 0;

		// 1.MyBatis �ڵ鷯 ���
		SqlSession ss = MyBatisHandler.getInstance().getMyBatisHandler(false);

		// 2. Handler�� ����ϱ�
		cnt = ss.update("updateDelUser", user_id);
		
		if(cnt==1) {ss.commit();}
		
		// 4. ���� ����
		if (ss != null) {
			ss.close();
		} // end if

		return cnt;

		
	}//updateDeleteMember
	
	
	
}//class
