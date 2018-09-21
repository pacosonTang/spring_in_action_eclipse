package com.swjtu.springmvc.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.swjtu.springmvc.service.UserService;

@Controller
@RequestMapping("/springmvc")
public class MySpringMvcTest {

	@Autowired
	private UserService userService;
	
	public MySpringMvcTest() {
		System.out.println("MySpringMvcTest  constructor."); 
	}
	
	@RequestMapping(value="/helloworld")
	public String helloworld() {
		
		System.out.println("userService = " + userService);
		System.out.println("success!! "); 
		return "success";
	}

}
