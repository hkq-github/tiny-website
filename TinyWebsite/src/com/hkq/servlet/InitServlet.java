package com.hkq.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * 项目启动前和项目结束后执行的一些初始化、收尾工作
 * 
 * @author hkq
 *
 */
public class InitServlet extends HttpServlet {
	
	/** 
	 * 定义初始化操作
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		// do nothing
		
	}
	
	/** 
	 * 定义收尾操作
	 */
	@Override
	public void destroy() {
		super.destroy();
		// do nothing
	}
}
