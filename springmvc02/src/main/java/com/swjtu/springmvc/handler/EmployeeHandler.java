package com.swjtu.springmvc.handler;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.swjtu.springmvc.dao.EmployeeDao;

@RequestMapping("/springmvc")
@Controller
public class EmployeeHandler { 
	
	private static final String LIST = "list";
	@Autowired
	private EmployeeDao employeeDao;
	
	
	
	@RequestMapping("/emps")
	public String list(Map<String, Object> map) {

		System.out.println("list");
		map.put("emps", employeeDao.getAll());
		return LIST;
	}
}
