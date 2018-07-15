package com.hkq.controller.admin;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.hkq.services.AdminServices;
import com.hkq.services.AdminServicesImpi;

/**
 * 管理员更新用户Controller
 * 
 * type = delete|freeze|recover|resetcode
 * 
 * 已经禁用删除用户功能
 * 
 * @author hkq
 */
public class AdminUpdateUserController extends HttpServlet {
	
	private static String defaultResetPass = "111111"; /* 默认重置密码 */
	private static boolean deleteOn = true;	/* 删除用户功能是否打开 */
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		String resetPass = config.getInitParameter("resetPass");
		if(!"".equals(resetPass)) {
			defaultResetPass = resetPass;
		}
		
		String delete = config.getInitParameter("deleteOn");
		if("false".equals(delete)) {
			deleteOn = false;
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
		throws ServletException, IOException {
		
		String id = req.getParameter("id");
		String type = req.getParameter("type");
		if("".equals(id) || !("delete".equals(type) || "freeze".equals(type) || "recover".equals(type) || "resetcode".equals(type))) {
			req.setAttribute("mess", "操作失败，请刷新后重试");
			req.getRequestDispatcher("/admin/home").forward(req, resp);
			return ;
		}
		
		AdminServices services = new AdminServicesImpi();
		String result;
		if("delete".equals(type)) {
			if(deleteOn) {
				result = services.deleteUser(id);
			} else {
				result = "操作失败，该功能已被禁用";
			}
		} else if("freeze".equals(type)) {
			result = services.freezeUser(id);
		} else if("recover".equals(type)) {
			result = services.recoverUser(id);
		} else if("resetcode".equals(type)){
			result = services.resetUserPassword(id, defaultResetPass);
		} else {
			result="出错了！！！请重试";
		}
		
		
		if(result == null) {
			req.setAttribute("mess", "操作成功");
		} else {
			req.setAttribute("mess", result);
		}
		
		req.getRequestDispatcher("/admin/home").forward(req, resp);
		return ;
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) 
		throws ServletException, IOException {
		this.doPost(req, resp);
	}
}
