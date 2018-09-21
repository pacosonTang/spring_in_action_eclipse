package com.swjtu.springmvc.test;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * 自定义 异常处理器
 * @author pacoson
 *
 */
@ControllerAdvice
public class ExceptionHandlerBase {
	
	@ExceptionHandler(ArithmeticException.class)
	public ModelAndView testHandleArithmeticException(Exception ex) {
		
		System.out.println("------->出异常了， " + ex);
		ModelAndView view = new ModelAndView("error");
		view.addObject("ex", ex);
		return view;
	}
}
