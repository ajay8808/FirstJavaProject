/* �ۼ���¥: 2020�� 2�� 18��
 * �ۼ���: �Ӽ���,�����,�����
 * ����: ��������Ʈ
 * �ۼ�ȯ��: windows 10
 */
package earlgrey.view;

import java.util.Scanner;
import java.util.regex.Pattern;

import earlgrey.service.MemberMgmtService;
import earlgrey.service.MemberMgmtServiceImpl;
import earlgrey.vo.MemberVO;



public class Register {
	private Scanner scan;
	private MemberMgmtService service;
	
	public Register() {
		this.scan = new Scanner(System.in);
		this.service = new MemberMgmtServiceImpl();
	}
	
	public void register() {
		boolean flag = false;
		System.out.println("*****ȸ������������******");
		String email = null;
		while (true) {
			System.out.print("�̸��� : ");
			email = this.scan.next();
			if(isEmail(email)){
				break;
			}
			System.out.println("�̸��� ������ �߸��Ǿ����ϴ�.");
		}
		System.out.print("��й�ȣ : ");
		String passwd = this.scan.next();
		System.out.print("��й�ȣ Ȯ��: ");
		String passwd2 = this.scan.next();
		System.out.print("�̸� : ");
		String name = this.scan.next();
		System.out.print("��ȭ��ȣ : ");
		String phone = this.scan.next();
		
		MemberVO member = new MemberVO(email, passwd, name, phone);
		int row = this.service.register(member);
		if(row == 1) System.out.println("ȸ�� ���� ����");
		else System.out.println("ȸ�� ���� ����");
	}
	public static boolean isEmail(String email) {
		if (email==null) return false;
		boolean b = Pattern.matches(
				"[\\w\\~\\-\\.]+@[\\w\\~\\-]+(\\.[\\w\\~\\-]+)+",
				email.trim());
		return b;
	}

//	 private MemberVO FindById(String id) {
//	        MemberVO memberDTO;
//	            if(memberDTO.getEmail().equals(id)) {
//	                return memberDTO;
//	            }
//	        }
//	        return null;
//	    }
//	 private boolean idCheck(String email) {
//	        boolean check = true;
//	        MemberVO member = FindById(email);
//	        if(member == null)
//	            check = false;
//	        return check;
//	    }
}
