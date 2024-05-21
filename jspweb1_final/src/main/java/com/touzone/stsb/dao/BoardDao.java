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
	
	/**
	 * 글쓰기 함수
	 * @param boardVo
	 * @return int
	 */
	public int insertBoard(BoardVo boardVo) {
		int result = 0;
		
		conn = getConnect();
		
		// AUTO_INCREMENT 로 들어간 가장 최근의 숫자
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("INSERT INTO boards \n"
					+ "(num, botype, group_num, name, subject, content, regdate) values \n"
					+ "(( SELECT MAX(num)+1 FROM BO_SERVICE as num ), 'notice', num, ?, ?, ?,now() )");
			String sql = sb.toString();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardVo.getName());
			pstmt.setString(2, boardVo.getSubject());
			pstmt.setString(3, boardVo.getContent());
			result = pstmt.executeUpdate();
		}catch(SQLException se) {
			System.out.println("insertBoard error:");
		}finally {
			DBClose();
		}
		return result; // 성공하면 1이상값
	}

}
