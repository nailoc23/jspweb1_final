package com.touzone.dao;

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

}
