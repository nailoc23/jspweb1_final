package com.touzone.stsb.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// 디비 접속을 관리하는 클래스
public class DBManager {
	
	// 접속 정보 작성
	static String url = "jdbc:mysql://192.168.0.197:3306/javadb3"; // 
	static String user = "team3";
	static String password = "123456";
	
	static Connection conn = null; // 0, 1(어떤값)
	static Statement stmt = null; // sql을 실행하기 위한 객체
	static PreparedStatement pstmt = null; // sql을 실행하기 위한 객체
	static ResultSet rs = null; // select 하고 나면 결과목록을 담는 동적배열
	// int rst // insert, update, delete 결과 성공 1 반대 0 

	// JDBC 가져오기
	static {
		try {
			// 
			Class.forName("com.mysql.jdbc.Driver");  // 사용하는 jdbc 이름 
			System.out.println("JDBC Load Success");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	// mysql connect
	static Connection getConnect() {
		Connection tempconn = null;
		if(conn == null) {
			try {
			tempconn = DriverManager.getConnection(url, user, password);
			  System.out.println("DB Conn Success");
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return tempconn;
	}
	
	// DB close
	protected void DBClose() {
		if(conn!=null) {try{conn.close(); conn=null;} 
			catch (SQLException e) {e.printStackTrace();}
		}
		if(stmt!=null) {try{stmt.close(); stmt=null;} 
			catch (SQLException e) {e.printStackTrace();}
		}
		if(pstmt!=null) {try{pstmt.close(); pstmt=null;} 
		catch (SQLException e) {e.printStackTrace();}
		}
		if(rs!=null) {try{rs.close(); rs=null;} 
		catch (SQLException e) {e.printStackTrace();}
		}
		
		//System.out.println("DB Close Success");
				
	}
}
