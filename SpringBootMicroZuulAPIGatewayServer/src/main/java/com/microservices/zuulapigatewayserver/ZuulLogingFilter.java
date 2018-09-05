package com.microservices.zuulapigatewayserver;


import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class ZuulLogingFilter extends ZuulFilter{
	
	private Logger logger = LoggerFactory.getLogger(ZuulLogingFilter.class);

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		HttpServletRequest request= RequestContext.getCurrentContext().getRequest();
		logger.info("request -> {} request uri   ->{}",request,request.getRequestURI());
		logger.info("request -> {} context path  ->{}",request,request.getContextPath());
		logger.info("request -> {} server name  ->{}",request,request.getServerName());
		logger.info("request -> {} server port  ->{}",request,request.getServerPort());
		return null;
	}
// "pre" - before execution
//"pro" - after execution
//"error" - filter error request	
	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

}
