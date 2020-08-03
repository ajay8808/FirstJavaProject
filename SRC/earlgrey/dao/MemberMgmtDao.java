/* 작성날짜: 2020년 2월 18일
 * 작성자: 임수진,김승현,김승희
 * 목적: 팀프로젝트
 * 작성환경: windows 10
 */
package earlgrey.dao;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import earlgrey.vo.Lecture;
import earlgrey.vo.MemberVO;
import earlgrey.vo.ReserveVO;

public interface MemberMgmtDao {
	int register(MemberVO member) throws SQLException;
	int login(String userid, String passwd) throws SQLException;
//	int insertReservation(String teachername,String date,String hour) throws SQLException;
//	MemberVO selectReservation(String email) throws SQLException;
	int deleteReservation(int recordid) throws SQLException;
	ArrayList<ReserveVO> list(String email) throws SQLException;
	int reserve(ReserveVO reservation) throws SQLException;
	ArrayList<Lecture> lectureOfTeacherDate(String teachername, Timestamp time) throws SQLException;
	int isReservation(ReserveVO reserve);
}
