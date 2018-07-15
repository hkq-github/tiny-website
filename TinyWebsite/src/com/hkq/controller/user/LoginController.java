package com.hkq.controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hkq.model.User;
import com.hkq.services.UserServices;
import com.hkq.services.UserServicesImpi;
import com.hkq.util.CookieSessionParam;
import com.hkq.util.FormParam;

/**
 * 用户登录Controller
 * 
 * @author hkq
 *
 */
public class LoginController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
		throws ServletException, IOException {

		// 获取用户名和密码
		String id = req.getParameter(FormParam.Login_id);
		String pass = req.getParameter(FormParam.Login_pass);
		if("".equals(id) || "".equals(pass)) {
			req.setAttribute("mess", "用户名、密码不能为空");
			req.getRequestDispatcher("/login").forward(req, resp);
			return ;
		}
		
		UserServices services = new UserServicesImpi();
		String result = services.allowUserLogin(id, pass);
		if(result != null) {
			req.setAttribute("mess", "出错了！！<br/>" + result);
			req.getRequestDispatcher("/login").forward(req, resp);
			return ;
		}
		
		User user = services.findUser(id);
		// 登录成功后，向session添加相应的信息
		HttpSession session = req.getSession(true);
		session.setAttribute(CookieSessionParam.Session_role, CookieSessionParam.Session_role_valueUser);
		session.setAttribute(CookieSessionParam.Session_self, user);
		
		// 若用户选择记住密码，则添加相应的Cookie
		String autoLogin = req.getParameter(FormParam.Login_recordPass);
		if(autoLogin != null && !autoLogin.equals("")) {
			String digest = services.getAutoLoginCookieValue(id.trim());
			if(digest != null) {
				Cookie cookie = new Cookie(CookieSessionParam.Cookie_autoLogin, digest);
				int timeInSecond = Integer.parseInt(getServletConfig().getInitParameter("auto_login_time"));
				cookie.setMaxAge(timeInSecond); 
				resp.addCookie(cookie);
			}
		}

		// 将页面重定向至用户主页
		req.getRequestDispatcher("/home").forward(req, resp);
		return ;
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) 
		throws ServletException, IOException {
		this.doPost(req, resp);
	}
}
