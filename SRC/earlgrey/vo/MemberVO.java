/* 작성날짜: 2020년 2월 18일
 * 작성자: 임수진,김승현,김승희
 * 목적: 팀프로젝트
 * 작성환경: windows 10
 */
package earlgrey.vo;

public class MemberVO {
	private String email;
	private String name;
	private String pwd;
	private String phone;

	public MemberVO(String email, String name, String pwd, String phone) {

	this.email = email;
	this.name= name;
	this.pwd = pwd;
	this.phone = phone;
	}
	public MemberVO(String email, String name, String phone) {

		this.email = email;
		this.name= name;
		this.phone = phone;
		}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return String.format("이메일=%s, 이름=%s, 전화번호=%s", email, name, phone);
	}

	

	

}
