package com.hkq.model;

/**
 * Domain领域模型类，与user表相对应
 * 
 * @author hkq
 */

public class User {
	private String id;
	private String salt;
	private String pass;
	private boolean freeze;
	private String name;
	private String email;
	private String sex;
	private String intro;
	
	public User() {
		
	}
	
	public User(String id, String salt, String pass, boolean freeze, String name, String email, String sex, String intro) {
		this(id, salt, pass, freeze, name, email);
		this.sex = sex;
		this.intro = intro;
	}

	public User(String id, String salt, String pass, boolean freeze, String name, String email) {
		super();
		this.id = id;
		this.salt = salt;
		this.pass = pass;
		this.freeze = freeze;
		this.name = name;
		this.email = email;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public boolean isFreeze() {
		return freeze;
	}

	public void setFreeze(boolean freeze) {
		this.freeze = freeze;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "[id=" + id + ", salt=" + salt + ", pass=" + pass + ", freeze=" + freeze + ", name=" + name
				+ ", email=" + email + ", sex=" + sex + ", intro=" + intro + "]";
	}
	
	
}
