package com.touzone.stsb.dao;

import java.sql.SQLException;

import com.touzone.stsb.vo.MemberVo;

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
	public int selectMemberByIdPw(String id, String pw) {
		
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
	
	public int insertMember(MemberVo membervo) {
		int result=0; // 삽입결과 1이상: 성공
		
		conn = getConnect();
		
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("INSERT INTO members (memid, name, email, password, phone, regdate) \n");
			sb.append(" VALUES (?, ?, ?, ?, ?, now())" );
			String sql = sb.toString();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,  membervo.getMemid());
			pstmt.setString(2,  membervo.getName());
			pstmt.setString(3,  membervo.getEmail());
			
			pstmt.setString(4,  membervo.getPassword());
			pstmt.setString(5,  membervo.getPhone());
			
			result = pstmt.executeUpdate();
		}catch(SQLException se) {
			System.out.println("insertMember Error:" + se.getMessage());
		}finally {
			DBClose();
		}
		
		return result;
	}
	
	/**
	 * member 테이블의 status 를 withdrawn 로 변경하여 탈퇴 처리함
	 * @param id
	 * @return
	 */
	public int deleteMember(String id) {
		
		int result=0; // 삽입결과 1이상: 성공
		
		conn = getConnect();
		
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("UPDATE members SET status='withdrawn' \n");
			sb.append(" WHERE memid=?" );
			String sql = sb.toString();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,  id);
			result = pstmt.executeUpdate();
		}catch(SQLException se) {
			System.out.println("deleteMember Error:" + se.getMessage());
		}finally {
			DBClose();
		}
		
		return result;
	}
	
	/**
	 * 디비를 접속해서 id pw 가 존재하는지 체크하는 함수
	 * @param id
	 * @param pw
	 * @return
	 */
	public MemberVo selectMemberOneById(String id) {
		
		MemberVo result= new MemberVo();
		conn = getConnect();
		try {
			String sql = "SELECT * FROM members WHERE memid=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,id);			
			// rs : resultset
			rs = pstmt.executeQuery();
			while(rs.next()) {
				result.setName( rs.getString("name") );
				result.setEmail(rs.getString("email"));
				result.setPhone(rs.getString("phone"));
			}
		}catch(SQLException se) {
			System.out.println("selectMemberOneById Error:" + se.getMessage() );
		}finally {
			DBClose();
		}
		 
		return result;
	}
	
	public int updateMemberById(MemberVo membervo) {

		int result=0; // 수정결과 1이상: 성공
		
		conn = getConnect();
		
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("UPDATE members SET name=? , \n");
			sb.append("               email = ? , \n");
			sb.append("               phone = ? , \n");
			sb.append(" WHERE memid=?" );
			String sql = sb.toString();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,  membervo.getName() );
			pstmt.setString(2,  membervo.getEmail() );
			pstmt.setString(3,  membervo.getPhone() );
			pstmt.setString(4,  membervo.getMemid() );
			result = pstmt.executeUpdate();
		}catch(SQLException se) {
			System.out.println("updateMemberById Error:" + se.getMessage());
		}finally {
			DBClose();
		}
		
		return result;
	}
}


