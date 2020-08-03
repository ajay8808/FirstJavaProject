/* 작성날짜: 2020년 2월 18일
 * 작성자: 임수진,김승현,김승희
 * 목적: 팀프로젝트
 * 작성환경: windows 10
 */
package earlgrey.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import earlgrey.vo.Lecture;
import earlgrey.vo.MemberVO;
import earlgrey.vo.TeacherVO;


public interface AdminDao {

	MemberVO selectMember(String email) throws SQLException;		
	TeacherVO selectTeacher(int empno) throws SQLException;
	int updateMember(MemberVO member) throws SQLException;
	int insertTeacher(TeacherVO teacher) throws SQLException;
	ArrayList<MemberVO> searchAllMember()throws SQLException;
	ArrayList<TeacherVO> searchAllTeacher()throws SQLException;
//	Lecture selectLecture(int lectureid) throws SQLException;
//	int insertLecture(Lecture lecture) throws SQLException;
//	int updateLecture(String empno) throws SQLException;
//	int deleteLecture(String empno) throws SQLException;
//	
	int deleteTeacher(int empno) throws SQLException;

	
	
}
