/* �ۼ���¥: 2020�� 2�� 18��
 * �ۼ���: �Ӽ���,�����,�����
 * ����: ��������Ʈ
 * �ۼ�ȯ��: windows 10
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
