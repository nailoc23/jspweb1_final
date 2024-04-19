package com.touzone.dao;

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

}
