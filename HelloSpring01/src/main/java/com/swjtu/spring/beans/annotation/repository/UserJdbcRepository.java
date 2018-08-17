package com.swjtu.spring.beans.annotation.repository;

import org.springframework.stereotype.Repository;

//UserRepository 实现类2
@Repository
public class UserJdbcRepository implements UserRepository{

	@Override
	public void save() {
		System.out.println("UserJdbcRepository.save() ....");
	}
}
