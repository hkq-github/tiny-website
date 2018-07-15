package com.hkq.controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hkq.util.CookieSessionParam;

/**
 * 用户注销Controller
 * 
 * @author hkq
 */

public class LogoutController extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
		throws ServletException, IOException {
		
		// 设置session过期
		HttpSession session = req.getSession(false);
		if(session != null) {
			session.invalidate();
		}
		
		// 将自动登录相关的Cookie设置过期
		Cookie cookie = new Cookie(CookieSessionParam.Cookie_autoLogin, "every-thing-is-ok");
		cookie.setMaxAge(0);
		resp.addCookie(cookie);
		
		// 将页面重定向置登录界面
		resp.sendRedirect(getServletContext().getContextPath() + "/login");
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) 
		throws ServletException, IOException {
		this.doPost(req, resp);
	}
}
