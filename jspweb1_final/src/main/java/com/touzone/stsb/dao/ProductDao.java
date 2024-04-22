package com.touzone.stsb.dao;

public class ProductDao extends DBManager {
	
	/**
	 * 인스턴스 생성하는 함수와 변수
	 */
	private static ProductDao instance = null;
	public static ProductDao getInstance() {
		if(instance==null) {
			instance=new ProductDao();
		}
		return instance;
	}

}
