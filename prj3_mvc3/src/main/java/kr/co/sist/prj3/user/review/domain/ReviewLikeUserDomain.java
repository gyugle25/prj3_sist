package kr.co.sist.prj3.user.review.domain;

public class ReviewLikeUserDomain {

	private String user_id; // ���̵�
	private String nick_name; // �г���
	private String profile; // �����ʻ���
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
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
	@Override
	public String toString() {
		return "ReviewLikeUserDomain [user_id=" + user_id + ", nick_name=" + nick_name + ", profile=" + profile + "]";
	}
	
	
	
}//class
