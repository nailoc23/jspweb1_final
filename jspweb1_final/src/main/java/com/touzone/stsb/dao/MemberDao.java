package com.touzone.stsb.dao;

import java.sql.SQLException;

public class MemberDao extends DBManager {
	
	/**
	 * 인스턴스 생성하는 함수와 변수
	 */
	private static MemberDao instance = null;
	public static MemberDao getInstance() {
		if(instance==null) {
			instance=new MemberDao();
		}
		return instance;
	}
	
	/**
	 * 디비를 접속해서 id pw 가 존재하는지 체크하는 함수
	 * @param id
	 * @param pw
	 * @return
	 */
	public int selectEmpByIdPw(String id, String pw) {
		
		int result=0;
		conn = getConnect();
		try {
			String sql = "SELECT count(*) as cnt FROM members WHERE memid=? and password=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,id);
			pstmt.setString(2,pw);
			// rs : resultset
			rs = pstmt.executeQuery();
			while(rs.next()) {
				result = rs.getInt("cnt");
			}
		}catch(SQLException se) {
			System.out.println("selectEmpByIdPw Error:");
		}finally {
			DBClose();
		}
		 
		return result;
	}

}
