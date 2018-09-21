package com.swjtu.springmvc.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/springmvc")
public class MySpringMvcTest {

	@RequestMapping(value="/helloworld")
	public String helloworld() {
		
		System.out.println("success!! "); 
		return "success";
	}

}
