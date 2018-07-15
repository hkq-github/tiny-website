package com.hkq.util;

/**
 * 定义了向控制器传递参数的参数名和值
 * 
 * @author hkq
 */
public class FormParam {
	/* ================= 用户相关 ================ */
	/* 
	 * login.jsp
	 * LoginController.java 
	 */
	public static final String Login_id = "id";
	public static final String Login_pass = "pass";
	public static final String Login_recordPass = "recordPass";
	
	/* 
	 * signup.jsp
	 * SignupController.java
	 */
	public static final String Signup_id = "id";
	public static final String Signup_pass1 = "pass1";
	public static final String Signup_pass2 = "pass2";
	public static final String Signup_name = "name";
	public static final String Signup_emial = "email";
	public static final String Signup_sex = "sex";
	public static final String Signup_sex_valueMale = "male";
	public static final String Signup_sex_valueFemale = "female";
	public static final String Signup_sex_valueSecret = "secret";
	public static final String Signup_intro = "intro";
	
	/* 
	 * change_password.jsp
	 * ChangePassController.java
	 */
	public static final String ChangePass_oldPass = "oldPass";
	public static final String ChangePass_newPass1 = "newPass1";
	public static final String ChangePass_newPass2 = "newPass2";
	
	/* 
	 * change_info.jsp
	 * ChangeInfoController.java
	 */
	public static final String ChangeInfo_name = "name";
	public static final String ChangeInfo_email = "email";
	public static final String ChangeInfo_intro = "intro";
	
	/* ===================== 管理员相关 =============== */
	/* 
	 * admin_login.jsp 
	 * AdminLoginController.java
	 */
	public static final String AdminLogin_id = "id";
	public static final String AdminLogin_pass = "pass";
	
	/* 
	 * admin_search.jsp
	 * AdminSearchController.java
	 */
	public static final String AdminSearch_fuzzySearch = "fuzzySearch";	// 模糊搜索
	public static final String AdminSearch_searchText = "searchText";	// 搜索框里的内容
	/*
	 * 依据什么搜索？用户名、邮箱、昵称
	 */
	public static final String AdminSearch_searchBy = "searchBy";	
	public static final String AdminSearch_searchBy_valueId = "id";
	public static final String AdminSearch_searchBy_valueEmail = "email";
	public static final String AdminSearch_searchBy_valueName = "name";
	/*
	 * 请求那页
	 */
	public static final String AdminSearch_pageNum = "pageNum";	
	
	/*
	 * AdminAllUser.java
	 */
	public static final String AdminAllUser_pageNum = "pageNum";	
}
