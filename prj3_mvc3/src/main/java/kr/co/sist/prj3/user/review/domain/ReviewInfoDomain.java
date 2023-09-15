package kr.co.sist.prj3.user.review.domain;

/**
 * �ش� ���� ����
 * @author kt
 */
public class ReviewInfoDomain {

	private String title; //��������d
	private String m_title; //��ȭ����d
	private String nick_name; //�г���d
	private String profile; //������d
	private String input_date; //�ۼ���d
	private String profile_msg; //�ڱ�Ұ�
	private String user_id; //���̵�

	private int hits; //��ȸ��
	private int like_total; //�� ���ƿ�
	private int com_total; //�� ��ۼ�
	
	private String content; //����

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getM_title() {
		return m_title;
	}

	public void setM_title(String m_title) {
		this.m_title = m_title;
	}

	public String getNick_name() {
		return nick_name;
	}

	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String getInput_date() {
		return input_date;
	}

	public void setInput_date(String input_date) {
		this.input_date = input_date;
	}

	public String getProfile_msg() {
		return profile_msg;
	}

	public void setProfile_msg(String profile_msg) {
		this.profile_msg = profile_msg;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public int getHits() {
		return hits;
	}

	public void setHits(int hits) {
		this.hits = hits;
	}

	public int getLike_total() {
		return like_total;
	}

	public void setLike_total(int like_total) {
		this.like_total = like_total;
	}

	public int getCom_total() {
		return com_total;
	}

	public void setCom_total(int com_total) {
		this.com_total = com_total;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "ReviewInfoDomain [title=" + title + ", m_title=" + m_title + ", nick_name=" + nick_name + ", profile="
				+ profile + ", input_date=" + input_date + ", profile_msg=" + profile_msg + ", user_id=" + user_id
				+ ", hits=" + hits + ", like_total=" + like_total + ", com_total=" + com_total + ", content=" + content
				+ "]";
	}

	
	
}//class
