package com.testtask.spring.filter;

import java.io.IOException;

import javax.faces.context.FacesContext;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.testtask.spring.utils.SessionUtils;

@WebFilter(filterName="AuthFilter", urlPatterns= {"*.xhtml"})
public class AuthorizationFilter implements Filter{
	
	public AuthorizationFilter() {}
	
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filter)
			throws IOException, ServletException {
		try {
			HttpServletRequest req = (HttpServletRequest) request;
			
			HttpServletResponse res = (HttpServletResponse) response;
			
			HttpSession session = req.getSession(false);
			String reqUri = req.getRequestURI();
					
			if(reqUri.indexOf("/index.xhtml") >= 0 || (session != null && session.getAttribute("username") != null)
													|| (reqUri.indexOf("/public/") >= 0)
													|| reqUri.contains("javax.faces.resource")) {
				filter.doFilter(request, response);
			}else {

				res.sendRedirect(req.getContextPath() + "/index.xhtml");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void destroy() {
		
	}

}
