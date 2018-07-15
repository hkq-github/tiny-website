package com.hkq.util;

public class Privilege {
	
	// 用户只能访问的路径
	private static final String[] userServletPathes = {
		"/login", "/LoginController", "/LogoutController", "/home", 
		"/change_pass", "/change_info", "/ChangePassController", "/ChangeInfoController"
	};
	// 管理员只能访问的路径
	private static final String[] adminServletPathes = {
		"/admin/login", "/admin/LoginController", "/admin/LogoutController", "/admin/home",
		"/admin/manage_user", "/admin/search", "/admin/UpdateUserController", "/admin/AllUserController", "/admin/SearchController",	 
	};
	
	/**
	 * 角色是否可以访问该servlet路径
	 * 
	 * @param servletPath 此次请求的servletPath
	 * @param isUser true为用户，false为管理员
	 * 
	 * @return 若servletPath为null或空字符串、该角色不能访问该路径时返回false
	 */
	public static boolean canVisited(String servletPath, boolean isUser) {
		if("".equals(servletPath)) {
			return true;
		}
		
		if(isUser) {
			return contains(userServletPathes, servletPath);
		} else {
			return contains(adminServletPathes, servletPath);
		}
	}
	
	/**
	 *  servletPath是否在servletPathes中
	 */
	private static boolean contains(String[] servletPathes, String servletPath) {
		for(String path : servletPathes) {
			if(path.equals(servletPath)) {
				return true;
			}
		}
		return false;
	}
}
