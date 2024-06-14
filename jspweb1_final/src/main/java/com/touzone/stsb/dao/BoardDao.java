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
	 * 게시판 총갯수 가져오기
	 */
	public int selectBoardTotal() {
		int result= 0;
		
		conn=getConnect();
		
		try {
			
			stmt = conn.createStatement();
			String sql = "SELECT count(*) as cnt FROM BOARDS";
			// rs : ResultSet 
			rs = stmt.executeQuery(sql);
			while(rs.next()) {				
				result = rs.getInt("cnt");	
			}
		}catch(SQLException se) {
			System.out.println("selectBoardTotal error:" + se.getMessage() );
		}finally {
			DBClose();
		}
				
		return result;
	}
	
	/**
	 * 게시판 목록들을 가져오기
	 * @return
	 */
	public List<BoardVo> selectBoardList(String searchKey, int pnum) {
		
		List<BoardVo> list = new ArrayList<BoardVo>();
		
		conn=getConnect();
		
		try {
			//stmt = conn.createStatement();
			String sql = "SELECT num,subject, name, DATE_FORMAT(regdate, '%Y-%m-%d') as regdate, hit FROM BOARDS WHERE subject like ? ORDER BY num DESC limit ?,10";
			// 
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+searchKey+"%");
			pstmt.setInt(2, (pnum-1)*10);
			// rs : ResultSet 
			rs = pstmt.executeQuery();
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
					+ "(num, botype, group_num, name, subject, content, email, regdate) values \n"
					+ "(( SELECT MAX(num)+1 FROM boards as num ), 'notice', num, ?, ?, ?,?,now() )");
			String sql = sb.toString();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardVo.getName());
			pstmt.setString(2, boardVo.getSubject());
			pstmt.setString(3, boardVo.getContent());
			pstmt.setString(4, boardVo.getEmail());
			result = pstmt.executeUpdate();
		}catch(SQLException se) {
			System.out.println("insertBoard error:" + se.getMessage());
		}finally {
			DBClose();
		}
		return result; // 성공하면 1이상값
	}
	
	/**
	 * 게시물을 번호로 읽는 함수
	 */
	public BoardVo selectBoardById(int num) {
		BoardVo result = new BoardVo();
		
		conn = getConnect();
		
		try {
			String sql = "SELECT * FROM boards WHERE num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			// rs : resultset
			rs = pstmt.executeQuery();
			while(rs.next()) {
				result.setNum( rs.getInt("num") ); 
				result.setSubject( rs.getString("subject") );
				result.setEmail( rs.getString("email"));
				result.setName( rs.getString("name"));
				result.setContent( rs.getString("content"));
				result.setRegdate( rs.getString("regdate"));
				result.setHit( rs.getInt("hit"));
			}
		}catch(SQLException se) {
			System.out.println("selectBoardById Error:");
		}finally { // 이게 빠지면 첫번째 디비접속성공 그다음은 에러가 난다
			DBClose();
		}
		
		return result;
		
	}
	
	public int updateBoard(BoardVo boardVo) {
		int result=0; // 삽입결과 1이상: 성공
		
		conn = getConnect();
		
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("UPDATE boards SET \n"
					+ "subject=?, \n"
					+ "name=?, email=?, \n"
					+ "content=? \n"
			        + " WHERE num=?" );
			String sql = sb.toString();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,  boardVo.getSubject());
			pstmt.setString(2,  boardVo.getName());
			
			pstmt.setString(3,  boardVo.getEmail());
			pstmt.setString(4,  boardVo.getContent());
			
			pstmt.setInt(5,  boardVo.getNum());
			
			result = pstmt.executeUpdate();
		}catch(SQLException se) {
			System.out.println("updateBoard Error:" + se.getMessage() );
		}finally {
			DBClose();
		}
		
		return result;
	}
	
	public int deleteBoardById(String num) {
		int result=0;
		
		conn = getConnect();
		
		try {
			String sql="DELETE FROM boards WHERE num="+num;
			stmt = conn.createStatement();
			result = stmt.executeUpdate(sql);
		}catch(SQLException se) {
			System.out.println("deleteBoardById Error:");
		}finally {
			DBClose();
		}
		
		return result;
	}

}
