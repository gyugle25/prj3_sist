package kr.co.sist.prj3.user.review.vo;

/**
 * ���ƿ� �߰�, ����
 * @author kt
 */
public class LikeVO {
	
	private int rv_num; //�����ȣ
	private String user_id; //���̵�
	
	public int getRv_num() {
		return rv_num;
	}
	public void setRv_num(int rv_num) {
		this.rv_num = rv_num;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	@Override
	public String toString() {
		return "LikeVO [rv_num=" + rv_num + ", user_id=" + user_id + "]";
	}
	
	
	
}//LikeVO