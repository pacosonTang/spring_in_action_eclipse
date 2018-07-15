package com.swjtu.mybatis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swjtu.mybatis.bean.Employee;
import com.swjtu.mybatis.dao.EmployeeMapper;

@Service
public class EmpService {
	
	@Autowired
	private EmployeeMapper employeeMapper;
	
	public List<Employee> getEmps(String lastName) {
		lastName += "%"; 
		return employeeMapper.getEmpsByLastName(lastName);
	}
}
