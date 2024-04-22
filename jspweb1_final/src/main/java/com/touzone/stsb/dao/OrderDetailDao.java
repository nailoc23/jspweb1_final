package com.touzone.stsb.dao;

public class OrderDetailDao extends DBManager {
	
	/**
	 * 인스턴스 생성하는 함수와 변수
	 */
	private static OrderDetailDao instance = null;
	public static OrderDetailDao getInstance() {
		if(instance==null) {
			instance=new OrderDetailDao();
		}
		return instance;
	}

}
