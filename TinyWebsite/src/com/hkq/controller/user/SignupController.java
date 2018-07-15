package com.hkq.controller.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hkq.services.UserServices;
import com.hkq.services.UserServicesImpi;
import com.hkq.util.FormParam;

/**
 * 用户注册Controller
 * 
 * @author hkq
 *
 */

public class SignupController extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
		throws ServletException, IOException {
		
		// 获取用户输入参数
		String id = req.getParameter(FormParam.Signup_id);
		String pass1 = req.getParameter(FormParam.Signup_pass1);
		String pass2 = req.getParameter(FormParam.Signup_pass2);
		String name = req.getParameter(FormParam.Signup_name);
		String email = req.getParameter(FormParam.Signup_emial);
		String sex = req.getParameter(FormParam.Signup_sex);
		String intro = req.getParameter(FormParam.Signup_intro);
		
		// 对获取的参数做简单判断和处理
		String error = "";
		if("".equals(id)) {
			error += "用户名为空；";
		}
		if(pass1 == null || pass2 == null || !pass1.equals(pass2) ) {
			error += "密码为空或不匹配；";
		}
		if("".equals(name)) {
			error += "昵称为空；";
		}
		if("".equals(email)) {
			error += "邮箱为空；";
		}
		if("".equals(intro)) {
			error += "简介为空；";
		}
		
		if(FormParam.Signup_sex_valueMale.equals(sex)) {
			sex = "男";
		} else if(FormParam.Signup_sex_valueFemale.equals(sex)) {
			sex = "女";
		} else {
			sex = "保密";
		}
		
		if(!"".equals(error)) {
			req.setAttribute("mess", "出错了！！<br/>" + error);
			req.getRequestDispatcher("/signup").forward(req, resp);
			return ;
		}
		
		// 调用业务逻辑处理相应的请求
		UserServices services = new UserServicesImpi();
		String result = services.signup(id, pass1, name, email, sex, intro);
		
		if(result == null) { // 注册成功，将页面重定向至登录主页
			req.setAttribute("mess", "注册成功，登录一下吧!");
			req.getRequestDispatcher("/login").forward(req, resp);
			return ;
		} else {
			req.setAttribute("mess", "出错了！！<br/>" + result);
			req.getRequestDispatcher("/signup").forward(req, resp);
			return ;
		}
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) 
		throws ServletException, IOException {
		this.doPost(req, resp);
	}
}
