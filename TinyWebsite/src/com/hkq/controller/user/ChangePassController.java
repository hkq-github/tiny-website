package com.hkq.controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hkq.model.User;
import com.hkq.services.UserServices;
import com.hkq.services.UserServicesImpi;
import com.hkq.util.CookieSessionParam;
import com.hkq.util.FormParam;

/**
 * 用户修改密码Controller
 * 
 * @author hkq
 *
 */

public class ChangePassController extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
		throws ServletException, IOException {
	
		// 获取用户传来的参数
		String oldPass = req.getParameter(FormParam.ChangePass_oldPass);
		String newPass1 = req.getParameter(FormParam.ChangePass_newPass1);
		String newPass2 = req.getParameter(FormParam.ChangePass_newPass2);
		
		
		// 对参数做简单判断
		if("".equals(oldPass) || "".equals(newPass1) || "".equals(newPass2)) {
			req.setAttribute("mess", "当前密码、新密码、确认密码不能为空");
			req.getRequestDispatcher("/change_pass").forward(req, resp);
			return ;
		}
		
		if(!newPass1.equals(newPass2)) {
			req.setAttribute("mess", "新密码、确认密码必须相同");
			req.getRequestDispatcher("/change_pass").forward(req, resp);
			return ;
		}
		
		if(oldPass.equals(newPass1)) {
			req.setAttribute("mess", "当前密码与新密码相同，修改失败");
			req.getRequestDispatcher("/change_pass").forward(req, resp);
			return ;
		}
		
		User user = (User) req.getSession(false).getAttribute(CookieSessionParam.Session_self);
		UserServices services = new UserServicesImpi();
		String result = services.changeUserPassword(user.getId(), oldPass, newPass1);
		
		if(result != null) { // 修改失败
			req.setAttribute("mess", result);
			req.getRequestDispatcher("/change_pass").forward(req, resp);
			return ;
		} else {
			req.setAttribute("mess", "修改密码成功");
			req.getRequestDispatcher("/home").forward(req, resp);
			return ;
		}
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) 
		throws ServletException, IOException {
		this.doPost(req, resp);
	}
}
