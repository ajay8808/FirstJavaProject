/* 작성날짜: 2020년 2월 18일
 * 작성자: 임수진,김승현,김승희
 * 목적: 팀프로젝트
 * 작성환경: windows 10
 */
package earlgrey.service;


import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import earlgrey.vo.Lecture;
import earlgrey.vo.MemberVO;
import earlgrey.vo.ReserveVO;

public interface MemberMgmtService {
	int register(MemberVO member);
	int login(String userid, String passwd);
	int deleteReservation(int recordid);
	int reserve(ReserveVO reservation);
	ArrayList<Lecture> showlectures(String teachername, Timestamp time);
	ArrayList<ReserveVO> list(String email) throws SQLException;
	int isReservation(ReserveVO reservation);
}
