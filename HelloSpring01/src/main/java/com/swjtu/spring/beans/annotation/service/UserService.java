package com.swjtu.spring.beans.annotation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.swjtu.spring.beans.annotation.repository.UserRepository;

@Service
public class UserService {
	
	// @Autowired 和 @Qualifier 可以修饰属性也可以修饰 setter 方法
	// 且 @Qualifier 可以放置到入参的前面 
	
	// 方法1、
	// 使用 @Autowired 自动装配 Bean
//	@Autowired
	// @Qualifier指定使用哪个 实现类 来 装配 userRepository
//	@Qualifier("userRepositoryImpl")
	private UserRepository userRepository;
	
	public void add() {
		System.out.println("UserService add() ");
		userRepository.save();
	}

	public UserRepository getUserRepository() {
		return userRepository;
	}
	
	// 方法2、
	/*@Autowired
	@Qualifier("userRepositoryImpl")
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}*/
	
	// 方法3、@Qualifier 指定 名为 userRepositoryImpl 的bean来装配  UserRepository
	@Autowired
	public void setUserRepository(@Qualifier("userRepositoryImpl") UserRepository userRepository) {
		this.userRepository = userRepository;
	}
}
