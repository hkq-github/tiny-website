package com.hkq.model;

/**
 * Domain领域模型类，与admin表相对应
 * 
 * @author hkq
 */

public class Admin {
	private String id;
	private String pass;
	
	public Admin(String id, String pass) {
		super();
		this.id = id; 
		this.pass = pass;
	}

	public String getId() {
		return id;
	}

	public String getPass() {
		return pass;
	}
}
