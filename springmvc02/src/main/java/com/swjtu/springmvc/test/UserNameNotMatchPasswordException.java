package com.swjtu.springmvc.test;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * 把 注解 @ResponseStatus 修饰类
 * @author pacoson
 *
 */
@ResponseStatus(value=HttpStatus.FORBIDDEN, reason="用户名和密码不匹配 from class")
public class UserNameNotMatchPasswordException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
}
