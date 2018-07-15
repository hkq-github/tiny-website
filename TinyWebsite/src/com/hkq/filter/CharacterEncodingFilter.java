package com.hkq.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.hkq.wrapper.MyCharacterEncodingRequest;

/**
 * 替换HttpServletRequest为自定义包装器MyCharacterEncodingRequest
 * 
 * @author hkq
 *
 */
public class CharacterEncodingFilter implements Filter {

	private static String defaultCharset = "UTF-8";
	
	@Override
	public void init(FilterConfig config) throws ServletException {
		String charset = config.getInitParameter("charset");
		if(!"".equals(charset)) {
			defaultCharset = charset;
		}
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		request.setCharacterEncoding(defaultCharset);
		response.setCharacterEncoding(defaultCharset);
		response.setContentType("text/html;charset=" + defaultCharset);
		
		// 替换request对象
		MyCharacterEncodingRequest myRequest = new MyCharacterEncodingRequest((HttpServletRequest)request);
		chain.doFilter(myRequest, response);	
	}

	@Override
	public void destroy() {
		
	}

}
