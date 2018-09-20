package com.swjtu.springmvc.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class SecondInterceptor implements HandlerInterceptor {
	
	/**
	 * 该方法在目标方法之前被调用
	 * ， 若返回值为 true， 则继续调用后续的拦截器和目标方法， 
	 * 若返回值为false， 则不会再调用 后续的拦截器和目标方法；
	 * 
	 * 可以考虑做权限， 或者是日志 或者事务（开启事务）
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		System.out.println("second preHandle");
		
		return true;
	}
	
	/**
	 * 调用目标方法之后， 但渲染视图之前被调用； 
	 * 
	 * 可以对 请求域中的属性或视图做修改；
	 * 
	 */
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("second postHandle");
	}
	
	/**
	 * 渲染视图之后被调用  ；
	 * 
	 * 释放资源 
	 */
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("second afterCompletion"); 
	}
}
