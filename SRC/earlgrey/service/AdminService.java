/* �ۼ���¥: 2020�� 2�� 18��
 * �ۼ���: �Ӽ���,�����,�����
 * ����: ��������Ʈ
 * �ۼ�ȯ��: windows 10
 */
package earlgrey.service;

import java.sql.SQLException;
import java.util.ArrayList;

import earlgrey.vo.MemberVO;
import earlgrey.vo.TeacherVO;

public interface AdminService {

	MemberVO readMember(String email) throws SQLException;
	ArrayList<MemberVO> readAllMember() throws SQLException;
	TeacherVO readTeacher(int empno) throws SQLException;
	ArrayList<TeacherVO> readAllTeacher()throws SQLException;
	int updateMember(MemberVO member)throws SQLException;
	int insertTeacher(TeacherVO teacher)throws SQLException;
	int deleteTeahcer(int empno)throws SQLException;
	

}
