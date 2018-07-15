package com.hkq.util;

/**
 * 对用户信息进行验证
 * 
 * @author hkq
 */

public class ValidateUserInfo {
	/**
	 * 验证邮箱正则表达式
	 */
	private static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
	
	/**
	 * 对所有信息进行验证
	 * 
	 * @return 合法返回null，否则返回错误信息
	 */
	public static String validate(String id, String pass, String name, String email, String sex, String intro) {
		StringBuilder sb = new StringBuilder();
		String temp;
		if((temp = validateID(id)) != null) {
			sb.append(temp + "；");
		}
		if((temp = validatePassword(pass)) != null) {
			sb.append(temp + "；");
		}
		if((temp = validateName(name)) != null) {
			sb.append(temp + "；");
		}
		if((temp = validateEmail(email)) != null) {
			sb.append(temp + "；");
		}
		if((temp = validateSex(sex)) != null) {
			sb.append(temp + "；");
		}
		if((temp = validateIntro(intro)) != null) {
			sb.append(temp + "；");
		}
		
		
		String result = sb.toString();
		if("".equals(result)) {
			return null;
		} else {
			return result;
		}
	}
	
	/**
	 * 检验用户名是否合法<br/>
	 * 用户名：1到10（含）个字符。由数字、英文字母、连字符 构成
	 * 
	 * @return 合法返回null。当id为null或不符合要求时返回错误信息
	 */
	public static String validateID(String id) {
		if(id == null || "".equals(id)) {
			return "用户名为空";
		}
		
		if(id.length() > 10) {
			return "用户名为1到10个字符";
		}
		
		for(int i = 0; i < id.length(); i++) {
			char c = id.charAt(i);
			if(!(c == '-' || Character.isLetter(c) || Character.isDigit(c))) {
				return "用户名包含非法字符，应由数字、英文字母、连字符 构成";
			}
		}
		
		return null;
	}
	
	/**
	 * 判断昵称是否合法<br/>
	 * 昵称：1到10（含）个字符。
	 * 
	 * @return 合法返回null。当name为null或不符合要求时返回错误信息
	 */
	public static String validateName(String name) {
		if(name == null || "".equals(name) || name.length() > 10) {
			return "昵称为1到10个字符";
		}
		return null;
	}
	
	/**
	 * 判断密码是否合法<br/>
	 * 
	 * 1. 由英文字母、数字0-9 、特殊符号! @ # $ % ^ & * ( ) [ ] { } - _ = + 构成<br/>
	 * 2. 8到16位<br/>
	 * 3. 至少包含一个字母、一个数字、一个特殊符号<br/>
	 * 
	 * @return 合法返回null。当password为null或不符合要求时返回错误信息
	 */
	public static String validatePassword(String password) {
		if(password == null || "".equals(password)) {
			return "密码为空";
		}
		if(password.length() < 8 || password.length() > 16) {
			return "密码为8到16位";
		}
		
		String symbol = "!@#$%^&*()[]{}-_=+";
		boolean haveLetter = false, haveDigit = false, haveSymbol = false;
		for(int i = 0; i < password.length(); i++) {
			char c = password.charAt(i);
			if(Character.isLetter(c)) {
				haveLetter = true;
			} else if(Character.isDigit(c)) {
				haveDigit = true;
			} else if(symbol.indexOf(c) != -1) {
				haveSymbol = true;
			} else {
				return "密码包含非法字符" + c;
			}
		}
		
		if(haveLetter && haveDigit && haveSymbol) {
			return null;
		} else {
			return "密码至少包含一个字母、一个数字、一个特殊符号";
		}
	}
	
	/**
	 * 判断邮箱是否合法<br/>
	 * 邮箱为60个字符之内
	 * 
	 * @return 合法返回null。当email为null或不符合要求时返回错误信息
	 */
	public static String validateEmail(String email) {
		if(email == null || "".equals(email)) {
			return "邮箱为空";
		}
		if(email.length() > 60) {
			return "邮箱最多为60个字符";
		}
		if(email.matches(REGEX_EMAIL)) {
			return null;
		} else {
			return "邮箱格式不正确";
		}
	}
	
	/**
	 * 判断个人简介是否合法<br/>
	 * 简介：256（含）个字符以内
	 * 
	 * @return 合法返回null。当intro为null或不符合要求时返回错误信息
	 */
	public static String validateIntro(String intro) {
		if(intro == null || "".equals(intro)) {
			return "简介为空";
		}
		if(intro.length() > 256) {
			return "简介为1到256个字符";
		}
		return null;
	}
	
	/**
	 * 性别只能是男、女或保密
	 */
	public static String validateSex(String sex) {
		if(sex == null || !("男".equals(sex) || "女".equals(sex) || "保密".equals(sex))) {
			return "请选择性别";
		}
		return null;
	}
}
