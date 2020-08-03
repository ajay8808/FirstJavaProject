/* 작성날짜: 2020년 2월 18일
 * 작성자: 임수진,김승현,김승희
 * 목적: 팀프로젝트
 * 작성환경: windows 10
 */
package earlgrey.vo;

public class TeacherVO {
	private int empno;
	private  String name;
	private String phone;

	public TeacherVO(int empno, String name, String phone) {
		this.empno = empno;
		this.name = name;
		this.phone = phone;
	}

	public int getEmpno() {
		return empno;
	}

	public void setEmpno(int empno) {
		this.empno = empno;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return String.format("사번=%s, 이름=%s, 전화번호=%s", empno, name, phone);
	}
	

}
