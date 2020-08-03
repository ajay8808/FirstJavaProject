/* 작성날짜: 2020년 2월 18일
 * 작성자: 임수진,김승현,김승희
 * 목적: 팀프로젝트
 * 작성환경: windows 10
 */
package earlgrey.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBClose {
	public static void close(Connection conn) {
//		try {
//			if(conn != null) conn.close();
//		}catch(SQLException ex) {
//			System.out.println(ex);
//		}
	}
	public static void close(Connection conn, Statement stmt) {
//		try {
//			if(stmt != null) stmt.close();
//			if(conn != null) conn.close();
//		}catch(SQLException ex) {
//			System.out.println(ex);
//		}
	}
	public static void close(Connection conn, Statement stmt, ResultSet rs) {
//		try {
//			if(rs != null) rs.close();
//			if(stmt != null) stmt.close();
//			if(conn != null) conn.close();
//		}catch(SQLException ex) {
//			System.out.println(ex);
//		}
	}
}
