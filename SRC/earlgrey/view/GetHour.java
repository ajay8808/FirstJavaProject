/* 작성날짜: 2020년 2월 18일
 * 작성자: 임수진,김승현,김승희
 * 목적: 팀프로젝트
 * 작성환경: windows 10
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
