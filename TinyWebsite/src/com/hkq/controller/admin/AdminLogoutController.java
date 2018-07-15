package com.hkq.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 管理员注销Controller
 * 
 * @author hkq
 *
 */
public class AdminLogoutController extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
		throws ServletException, IOException {
		
		// 删除相应的session
		HttpSession session = req.getSession(false);
		if(session != null) {
			session.invalidate();
		}
		
		resp.sendRedirect(getServletContext().getContextPath() + "/admin/login");
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) 
		throws ServletException, IOException {
		this.doPost(req, resp);
	}
}
