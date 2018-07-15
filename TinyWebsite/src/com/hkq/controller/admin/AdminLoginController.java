package com.hkq.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hkq.model.Admin;
import com.hkq.services.AdminServices;
import com.hkq.services.AdminServicesImpi;
import com.hkq.util.CookieSessionParam;
import com.hkq.util.FormParam;

/**
 * 管理员登录Controller
 * 
 * @author hkq
 *
 */
public class AdminLoginController extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
		throws ServletException, IOException {
		
		String id = req.getParameter(FormParam.AdminLogin_id);
		String pass = req.getParameter(FormParam.AdminLogin_pass);
		
		if("".equals(id) || "".equals(pass)) {
			req.setAttribute("mess", "用户名、密码不能为空");
			req.getRequestDispatcher("/admin/login").forward(req, resp);
			return ;
		}
		
		AdminServices services = new AdminServicesImpi();
		String result = services.allowAdminLogin(id, pass);
		if(result == null) {
			HttpSession session = req.getSession(true);
			session.setAttribute(CookieSessionParam.Session_role, CookieSessionParam.Session_role_valueAdmin);
			Admin admin = services.findAdmin(id);
			session.setAttribute(CookieSessionParam.Session_self, admin);
			req.getRequestDispatcher("/admin/home").forward(req, resp);
			return ;
		} else {
			req.setAttribute("mess", "用户名或密码错误");
			req.getRequestDispatcher("/admin/login").forward(req, resp);
			return ;
		}
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) 
		throws ServletException, IOException {
		this.doPost(req, resp);
	}
}
