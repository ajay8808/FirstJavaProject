/* �ۼ���¥: 2020�� 2�� 18��
 * �ۼ���: �Ӽ���,�����,�����
 * ����: ��������Ʈ
 * �ۼ�ȯ��: windows 10
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
