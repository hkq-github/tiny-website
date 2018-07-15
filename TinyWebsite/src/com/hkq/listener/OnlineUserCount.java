package com.hkq.listener;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Session监听器，统计在线人数
 * 
 * @author hkq
 *
 */

public class OnlineUserCount implements HttpSessionListener {
	
	@Override
	public void sessionCreated(HttpSessionEvent event) {
		ServletContext context = event.getSession().getServletContext();
		Object count = context.getAttribute("onlineCount");
		if(count == null) {
			context.setAttribute("onlineCount", 1);
		} else {
			context.setAttribute("onlineCount", (int)count + 1);
		}
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		ServletContext context = event.getSession().getServletContext();
		
		Object count = context.getAttribute("onlineCount");
		if(count == null) {
			context.setAttribute("onlineCount", 1);
		} else {
			int newCount = (int)count - 1 > 0 ? (int)count - 1 : 1;
			context.setAttribute("onlineCount", newCount);
		}
	}
}
