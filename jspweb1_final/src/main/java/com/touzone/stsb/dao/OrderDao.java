package com.touzone.stsb.dao;

public class OrderDao extends DBManager {
	
	/**
	 * 인스턴스 생성하는 함수와 변수
	 */
	private static OrderDao instance = null;
	public static OrderDao getInstance() {
		if(instance==null) {
			instance=new OrderDao();
		}
		return instance;
	}

}
