package com.hkq.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.hkq.util.CookieSessionParam;
import com.hkq.util.Privilege;

/**
 * 权限过滤器，若没有权限访问，则重定向到主页
 * 
 * @author hkq
 *
 */
public class PrivilegeFilter implements Filter {
	
	@Override
	public void init(FilterConfig config) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
		throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		String servletPath = req.getServletPath();
		HttpSession session = req.getSession(false);
		
		String dispatcherPath = null;
		if(session != null) {
			String role = (String)session.getAttribute(CookieSessionParam.Session_role);
			if(CookieSessionParam.Session_role_valueUser.equals(role)) {
				if(!Privilege.canVisited(servletPath, true)) {
					dispatcherPath = "/home";
				}
			} else if(CookieSessionParam.Session_role_valueAdmin.equals(role)){
				if(!Privilege.canVisited(servletPath, false)) {
					dispatcherPath = "/admin/home";
				}
			}
		}
		// else 无session，通过
		// 没有session且通过LoginFilter，则访问的是不许要登录的界面
		
		if(dispatcherPath == null) {
			chain.doFilter(request, response);
			return ;
		} else {
			req.setAttribute("mess", "出错了！！你不能访问该界面");
			req.getRequestDispatcher(dispatcherPath).forward(request, response);
			return ;
		}
	}

	@Override
	public void destroy() {
		
	}	
}
