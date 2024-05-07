package com.touzone.stsb.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.touzone.stsb.vo.BoardVo;

public class BoardDao extends DBManager {
	
	/**
	 * 인스턴스 생성하는 함수와 변수
	 */
	private static BoardDao instance = null;
	public static BoardDao getInstance() {
		if(instance==null) {
			instance=new BoardDao();
		}
		return instance;
	}
	
	/**
	 * 게시판 목록들을 가져오기
	 * @return
	 */
	public List<BoardVo> selectBoardList() {
		
		List<BoardVo> list = new ArrayList<BoardVo>();
		
		conn=getConnect();
		
		try {
			stmt = conn.createStatement();
			String sql = "SELECT num,subject, name, DATE_FORMAT(regdate, '%Y-%m-%d') as regdate, hit FROM BOARDS ORDER BY num DESC";
			// rs : ResultSet 
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				BoardVo tempVo = new BoardVo();
				tempVo.setNum(rs.getInt("num"));
				tempVo.setSubject(rs.getString("subject"));
				tempVo.setName(rs.getString("name"));
				tempVo.setRegdate(rs.getString("regdate"));
				tempVo.setHit(rs.getInt("hit"));
				list.add(tempVo);
			}
		}catch(SQLException se) {
			System.out.println("selectBoardList error:" + se.getMessage() );
		}finally {
			DBClose();
		}
				
		return list;
	}

}
