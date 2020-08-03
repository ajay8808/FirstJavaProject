/* 작성날짜: 2020년 2월 18일
 * 작성자: 임수진,김승현,김승희
 * 목적: 팀프로젝트
 * 작성환경: windows 10
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
		System.out.println("*****회원가입페이지******");
		String email = null;
		while (true) {
			System.out.print("이메일 : ");
			email = this.scan.next();
			if(isEmail(email)){
				break;
			}
			System.out.println("이메일 형식이 잘못되었습니다.");
		}
		System.out.print("비밀번호 : ");
		String passwd = this.scan.next();
		System.out.print("비밀번호 확인: ");
		String passwd2 = this.scan.next();
		System.out.print("이름 : ");
		String name = this.scan.next();
		System.out.print("전화번호 : ");
		String phone = this.scan.next();
		
		MemberVO member = new MemberVO(email, passwd, name, phone);
		int row = this.service.register(member);
		if(row == 1) System.out.println("회원 가입 성공");
		else System.out.println("회원 가입 실패");
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
