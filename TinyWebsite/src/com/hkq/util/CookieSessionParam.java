package com.hkq.util;

/**
 * 定义了cookie和session约定的一些常量
 * 
 * @author hkq
 *
 */

public class CookieSessionParam {
	/* ================= 添加到session的属性名和值 =================== */
	public static final String Session_self = "self";				// 保存一个User or Admin对象
	public static final String Session_role = "role";				// 角色类型
	public static final String Session_role_valueUser = "user";
	public static final String Session_role_valueAdmin = "admin";
	
	
	/* ================= Cookie相关 =================== */
	public static final String Cookie_autoLogin = "auto_login";	// 自动登录的cookie名
}
