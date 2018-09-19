package com.swjtu.springmvc.test;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.swjtu.springmvc.dao.EmployeeDao;
import com.swjtu.springmvc.entity.Employee;

@Controller
@RequestMapping("/springmvc")
public class MySpringMvcTest {
	
	@Autowired
	private EmployeeDao employeeDao;
	
	
	/**
	 * 测试 json 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/testJson")
	public Collection<Employee> testJson() {
		
		return employeeDao.getAll();
	}
	
	@RequestMapping("/testConverter")
	public String testConverter(@RequestParam("employee") Employee employee) {
		
		System.out.println("testConverter() employeeDao.save(employee); ");
		employeeDao.save(employee);
		return "redirect:/springmvc/emps";
	}
	
}
