/* �ۼ���¥: 2020�� 2�� 18��
 * �ۼ���: �Ӽ���,�����,�����
 * ����: ��������Ʈ
 * �ۼ�ȯ��: windows 10
 */
package earlgrey.main;

import java.sql.SQLException;

import earlgrey.view.MemberUI;

public class MainClass {
	public static void main(String[] args) throws SQLException {
		MemberUI ui = new MemberUI();
		ui.init();
	}
}