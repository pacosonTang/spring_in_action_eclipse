package com.swjtu.crud.service;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swjtu.crud.bean.Employee;
import com.swjtu.crud.dao.EmployeeMapper;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeMapper mapper;
	
	/** 根据部门记录编号查询员工列表  */
	public List<Employee> empListQry(String deptRcrdId) {
		return mapper.empListQry(deptRcrdId);
	}
	
	/** 根据部门记录编号查询员工列表，返回结果是Map  */
	public List<Map<String, Object>> empMapListQry(String deptRcrdId) {
		return mapper.empMapListQry(deptRcrdId);
	}
}
