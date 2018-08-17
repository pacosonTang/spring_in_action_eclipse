package com.swjtu.spring.beans.annotation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.swjtu.spring.beans.annotation.service.UserService;

@Controller
public class UserController {
	
	// 使用 @Autowired 自动装配 Bean
	@Autowired 
	private UserService userService;
	
	public void execute() {
		System.out.println("UserController execute....");
		userService.add();
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
