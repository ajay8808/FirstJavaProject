/* 작성날짜: 2020년 2월 18일
 * 작성자: 임수진,김승현,김승희
 * 목적: 팀프로젝트
 * 작성환경: windows 10
 */
package earlgrey.vo;

import java.sql.Timestamp;
import java.util.Date;

public class Lecture {
	private int lectureid;
	private int empno;
	private Timestamp lectureTime;
	private int capacity;

	public Lecture(int empno, Timestamp lectureTime, int capacity) {
		super();
		this.empno = empno;
		this.lectureTime = lectureTime;
		this.capacity = capacity;
	}
	public int getLectureid() {
		return lectureid;
	}
	public void setLectureid(int lectureid) {
		this.lectureid = lectureid;
	}
	public int getEmpno() {
		return empno;
	}
	public void setEmpno(int empno) {
		this.empno = empno;
	}
	public Timestamp getLectureTime() {
		return lectureTime;
	}
	public void setLectureTime(Timestamp lectureTime) {
		this.lectureTime = lectureTime;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	@Override
	public String toString() {
		return String.format("강의번호=%s, 직원번호=%s, 시간=%s, 남은 정원=%s]", lectureid, empno,
				lectureTime, capacity);
	}
}