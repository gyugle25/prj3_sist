package kr.co.sist.prj3.user.comment.domain;

/**
 * ���� ������<br>
 * ��۹�ȣ, ���۹�ȣ, �����ʸ�, �г���, ���۳���, �ۼ���
 * @author user
 *
 */
public class ReplyDomain {

	private int com_num, rp_num;
	private String profile, user_id, nick_name, content, input_date ;
	
	
	public int getCom_num() {
		return com_num;
	}
	public void setCom_num(int com_num) {
		this.com_num = com_num;
	}
	public int getRp_num() {
		return rp_num;
	}
	public void setRp_num(int rp_num) {
		this.rp_num = rp_num;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public String getNick_name() {
		return nick_name;
	}
	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getInput_date() {
		return input_date;
	}
	public void setInput_date(String input_date) {
		this.input_date = input_date;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	@Override
	public String toString() {
		return "ReplyDomain [com_num=" + com_num + ", rp_num=" + rp_num + ", profile=" + profile + ", user_id="
				+ user_id + ", nick_name=" + nick_name + ", content=" + content + ", input_date=" + input_date + "]";
	}
	
	
	
}//class
