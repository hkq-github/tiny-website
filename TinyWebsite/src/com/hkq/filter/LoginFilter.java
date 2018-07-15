package com.hkq.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hkq.model.User;
import com.hkq.services.UserServices;
import com.hkq.services.UserServicesImpi;
import com.hkq.util.CookieSessionParam;

/**
 * 登录过滤器<br/>
 * 对于所有页面<br/>
 * 1. 如果已经登录（管理员or用户），则通过<br/>
 * 2. 如果有自动登录的Cookie，验证合法性。若合法则登录并通过，否则删除该Cookie<br/>
 * 3. 如果访问的servle界面不需要登录，则通过
 * 4. 否则，重定向到登录界面
 * 
 * @author hkq
 */

public class LoginFilter implements Filter {
	// 不需要登录就可以访问的Servlet路径
	public static final String[] unLoginServletPathes = { 
		"/login",  "/LoginController",
		"/admin/login",  "/admin/LoginController", 
		"/signup","/SignupController"
	};

	@Override
	public void init(FilterConfig config) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
		throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		HttpSession session = req.getSession(false);
		String servletPath = req.getServletPath();
			
		// 如果已经登录(有相应的session)，则通过
		if( session != null && 
			(CookieSessionParam.Session_role_valueUser.equals(session.getAttribute(CookieSessionParam.Session_role)) ||
			 CookieSessionParam.Session_role_valueAdmin.equals(session.getAttribute(CookieSessionParam.Session_role)))
		 ) {
			chain.doFilter(request, response);
			return ;
		}
		
		// 用户自动登录
		Cookie[] cookies = req.getCookies();
		Cookie cookie = null;
		if (cookies != null) {
			for (Cookie c : cookies) {
				if (CookieSessionParam.Cookie_autoLogin.equals(c.getName())) {
					cookie = c;
					break;
				}
			}
		}
		// 判断Cookie的信息是否合法
		if(cookie != null) {
			UserServices services = new UserServicesImpi();
			String id = services.allowAutoLogin(cookie.getValue());
			if (id != null) { // Cookie合法，添加相应的session，并通过
				User user = services.findUser(id);
				session = req.getSession(true);
				session.setAttribute(CookieSessionParam.Session_role, CookieSessionParam.Session_role_valueUser);
				session.setAttribute(CookieSessionParam.Session_self, user);
				chain.doFilter(request, response);
				return ;
			} else { // Cookie不合法，删除该Cookie
				cookie.setMaxAge(0);
				resp.addCookie(cookie);
			}	
		}
		
		// 如果请求的路径不需要登录，则通过
		for (String path : unLoginServletPathes) {
			if (path.equals(servletPath)) {
				chain.doFilter(request, response);
				return;
			}
		}
		
		// 重定向到登录界面
		resp.sendRedirect(request.getServletContext().getContextPath() + "/login");
		return ;
	}

	@Override
	public void destroy() {
		
	}	
}
