package kr.co.sist.prj3.user.review.vo;

/**
 * ���� ����
 * @author KT
 */
public class ReviewModifyVO {
	
	private String user_id; // ���̵�
	private String title; // ����
	private int rv_num; // �̷¼���ȣ
	private String content; // ����
	private int m_num; // ��ȭ��ȣ
	private String m_title; //��ȭ����
	
	
	public String getM_title() {
		return m_title;
	}
	public void setM_title(String m_title) {
		this.m_title = m_title;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getRv_num() {
		return rv_num;
	}
	public void setRv_num(int rv_num) {
		this.rv_num = rv_num;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getM_num() {
		return m_num;
	}
	public void setM_num(int m_num) {
		this.m_num = m_num;
	}
	@Override
	public String toString() {
		return "ReviewModifyVO [user_id=" + user_id + ", title=" + title + ", rv_num=" + rv_num + ", content=" + content
				+ ", m_num=" + m_num + ", m_title=" + m_title + "]";
	}

	
	
	
	
	
	
}//ReviewModifyVO
