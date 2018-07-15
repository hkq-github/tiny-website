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
 * 搜索用户Controller
 * 
 * @author hkq
 *
 */

public class AdminSearchController extends HttpServlet {
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
		
		// 获取参数并对参数进行处理
		String searchText = req.getParameter(FormParam.AdminSearch_searchText);
		if("".equals(searchText)) {
			req.setAttribute("mess", "请输入搜索内容");
			req.getRequestDispatcher("/admin/search").forward(req, resp);
			return ;
		}
		
		String searchByStr = req.getParameter(FormParam.AdminSearch_searchBy);
		int searchBy;
		if(FormParam.AdminSearch_searchBy_valueId.equals(searchByStr)) {
			searchBy = AdminServices.SEARCH_BY_ID;
		} else if(FormParam.AdminSearch_searchBy_valueEmail.equals(searchByStr)) {
			searchBy = AdminServices.SEARCH_BY_EMAIL;
		} else if(FormParam.AdminSearch_searchBy_valueName.equals(searchByStr)) {
			searchBy = AdminServices.SEARCH_BY_NAME;
		} else {
			req.setAttribute("mess", "出错了，请重试！！！");
			req.getRequestDispatcher("/admin/search").forward(req, resp);
			return ;
		}
		
		String fuzzySearch = req.getParameter(FormParam.AdminSearch_fuzzySearch);
		boolean isFuzzySearch = !(fuzzySearch == null || "".equals(fuzzySearch));
		
		String pageNumStr = req.getParameter(FormParam.AdminSearch_pageNum);
		int pageNum = 1;
		try {
			if(!"".equals(pageNumStr)) {
				pageNum = Integer.parseInt(pageNumStr);
			}
		} catch (Exception e) {
		
		}
		 
		// 生成uri
		StringBuilder uri =  new StringBuilder();
		uri.append("/admin/SearchController?");
		uri.append(FormParam.AdminSearch_searchText + "=" + searchText);
		uri.append("&" + FormParam.AdminSearch_searchBy + "=" + searchByStr);
		if(isFuzzySearch) {
			uri.append("&" + FormParam.AdminSearch_fuzzySearch + "=every_thing_is_ok");
		}
		
		AdminServices services = new AdminServicesImpi();
		UserPaging page = services.searchUser(searchBy, searchText, isFuzzySearch, pageNum, pageSize);
		req.setAttribute("uri", uri.toString());
		req.setAttribute("page", page);
		req.getRequestDispatcher("/admin/manage_user").forward(req, resp);
		return ;
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) 
		throws ServletException, IOException {
		this.doPost(req, resp);
	}
}
