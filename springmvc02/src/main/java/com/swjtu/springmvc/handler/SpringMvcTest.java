package com.swjtu.springmvc.handler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * @RequestMapping 既可以修饰类也可以修饰方法 
 */ 
@SessionAttributes(value={"user"}, types={String.class})
// @SessionAttributes 修饰的属性 既会放到request 请求域里面， 也会放到session 域里边

@RequestMapping("/springmvc")
@Controller
public class SpringMvcTest {
	
	private final static String SUCCESS = "success";
	
	
}
