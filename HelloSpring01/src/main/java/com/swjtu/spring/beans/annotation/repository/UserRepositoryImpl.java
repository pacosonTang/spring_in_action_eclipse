package com.swjtu.spring.beans.annotation.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.swjtu.spring.beans.annotation.TestObject;

// UserRepository 实现类1
@Repository
public class UserRepositoryImpl implements UserRepository {
	
	// 使用 @Autowired 自动装配 Bean
	// required=false 表示 不是必须存在或必须装配的，可以为null
	@Autowired(required=false)
	private TestObject testObject;
	
	@Override
	public void save() {
		System.out.println("UserRepositoryImpl.save() "); 
		System.out.println(testObject);
	}
}
