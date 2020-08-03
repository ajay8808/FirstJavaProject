/* 작성날짜: 2020년 2월 18일
 * 작성자: 임수진,김승현,김승희
 * 목적: 팀프로젝트
 * 작성환경: windows 10
 */
package earlgrey.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
	private static File file;

	public static Connection getConnection(String filename) {
		file = new File(filename);
		return ConnectionFactory.INSTANCE;
	}

	private static class ConnectionFactory{
		public static final Connection INSTANCE = new DBConnection().getConnection();
	}

	private DBConnection() {}
	public Connection getConnection() {
		Connection conn = null;
		try {
			Properties info = new Properties();
			info.load(new FileInputStream(file));
			Class.forName(info.getProperty("db.driver"));
			conn = DriverManager.getConnection(
					info.getProperty("db.url"), info.getProperty("db.user"),
					info.getProperty("db.password"));
		}catch(IOException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}
