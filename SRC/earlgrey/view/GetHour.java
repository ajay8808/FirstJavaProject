/* �ۼ���¥: 2020�� 2�� 18��
 * �ۼ���: �Ӽ���,�����,�����
 * ����: ��������Ʈ
 * �ۼ�ȯ��: windows 10
 */
package earlgrey.view;

public class GetHour {	
	public static String gethour(String str) {
		
		String[] arrOfStr = str.split(" ", 2);
	
		String time = arrOfStr[1];
		String[] timesplit = time.split(":",2);
		String  hour= timesplit[0];
		return hour;
	}
}
