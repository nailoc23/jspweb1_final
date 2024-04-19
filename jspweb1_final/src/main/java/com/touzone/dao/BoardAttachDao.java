package com.touzone.dao;

public class BoardAttachDao extends DBManager {
	
	/**
	 * 인스턴스 생성하는 함수와 변수
	 */
	private static BoardAttachDao instance = null;
	public static BoardAttachDao getInstance() {
		if(instance==null) {
			instance=new BoardAttachDao();
		}
		return instance;
	}

}
