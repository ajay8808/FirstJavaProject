/* �ۼ���¥: 2020�� 2�� 18��
 * �ۼ���: �Ӽ���,�����,�����
 * ����: ��������Ʈ
 * �ۼ�ȯ��: windows 10
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
		return String.format("���ǹ�ȣ=%s, ������ȣ=%s, �ð�=%s, ���� ����=%s]", lectureid, empno,
				lectureTime, capacity);
	}
}