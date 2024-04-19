package com.touzone.dao;

public class ProductImageDao extends DBManager {
	
	/**
	 * 인스턴스 생성하는 함수와 변수
	 */
	private static ProductImageDao instance = null;
	public static ProductImageDao getInstance() {
		if(instance==null) {
			instance=new ProductImageDao();
		}
		return instance;
	}

}
