package com.hkq.controller.admin;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hkq.services.AdminServices;
import com.hkq.services.AdminServicesImpi;
import com.hkq.util.FormParam;
import com.hkq.util.UserPaging;

/**
 * 显示所有用户Controller
 * 
 * @author hkq
 *
 */
public class AdminAllUserController extends HttpServlet {
	
	private int pageSize;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		// 获取分页大小
		String pageSizeStr = config.getInitParameter("pageSize");
		pageSize = Integer.parseInt(pageSizeStr);
		if(pageSize < 1) {
			throw new RuntimeException("分页大小pageSize不能小于1");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
		throws ServletException, IOException {
		
		String pageNumStr = req.getParameter(FormParam.AdminAllUser_pageNum);
		int pageNum = 1;
		try {
			if(!"".equals(pageNumStr)) {
				pageNum = Integer.parseInt(pageNumStr);
			}
		} catch (Exception e) {
			
		}
		
		AdminServices services = new AdminServicesImpi();
		UserPaging page = services.searchAllUser(pageNum, pageSize);
		
		req.setAttribute("page", page);
		req.setAttribute("uri", "/admin/AllUserController?");
		req.getRequestDispatcher("/admin/manage_user").forward(req, resp);
		return ;
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) 
		throws ServletException, IOException {
		this.doPost(req, resp);
	}
}