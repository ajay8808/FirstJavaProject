/* 작성날짜: 2020년 2월 18일
 * 작성자: 임수진,김승현,김승희
 * 목적: 팀프로젝트
 * 작성환경: windows 10
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