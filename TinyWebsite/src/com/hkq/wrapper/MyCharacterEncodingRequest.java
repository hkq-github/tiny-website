package com.hkq.wrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * 处理中文乱码包装器
 * 
 * 对于GET请求，将参数从ISO-8859-1转成页面通用的字符集；并且当参数不为空时，去除两边的空白字符 
 */
public class MyCharacterEncodingRequest extends HttpServletRequestWrapper{
	
	private HttpServletRequest request;
	
	public MyCharacterEncodingRequest(HttpServletRequest request) {
		super(request);
		this.request = request;
	}
	
	@Override
	public String getParameter(String name) {
		
		try {
			String value =  this.request.getParameter(name);
			if(value == null) {
				return null;
			}
			
			// 去除两边空白字符；如果是GET请求，转码
			if(!this.request.getMethod().equalsIgnoreCase("GET")) {
				return value.trim();
			} else { // 进行转码
				value = new String(value.getBytes("iso-8859-1"), this.request.getCharacterEncoding());
				return value.trim();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
