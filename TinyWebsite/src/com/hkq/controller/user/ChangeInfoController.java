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
 * 修改用户信息Controller
 * 
 * @author hkq
 */

public class ChangeInfoController extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
		throws ServletException, IOException {

		User user = (User) req.getSession().getAttribute(CookieSessionParam.Session_self);
		
		// 获取用户参数
		String name = req.getParameter(FormParam.ChangeInfo_name);
		String email = req.getParameter(FormParam.ChangeInfo_email);
		String intro = req.getParameter(FormParam.ChangeInfo_intro);
			
		if((user.getName().equals(name) && user.getEmail().equals(email) && user.getIntro().equals(intro)) ){
			req.setAttribute("mess", "未进行任何修改");
			req.getRequestDispatcher("/home").forward(req, resp);
			return ;
		}
		
		UserServices services = new UserServicesImpi();
		String result = services.changeUserInfo(user.getId(), name, email, intro);
		if(result == null) {
			// 更新session中的User对象
			user = services.findUser(user.getId());
			req.getSession().setAttribute(CookieSessionParam.Session_self, user);
			req.setAttribute("mess", "修改成功");
			req.getRequestDispatcher("/home").forward(req, resp);
			return ;
		} else {
			req.setAttribute("mess", "修改失败!!" + result);
			req.getRequestDispatcher("/change_info").forward(req, resp);
			return ;
		}
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) 
		throws ServletException, IOException {
		this.doPost(req, resp);
	}
}
