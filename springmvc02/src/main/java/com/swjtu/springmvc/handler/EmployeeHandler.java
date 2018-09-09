package com.swjtu.springmvc.handler;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.swjtu.springmvc.dao.DepartmentDao;
import com.swjtu.springmvc.dao.EmployeeDao;
import com.swjtu.springmvc.entity.Employee;

@RequestMapping("/springmvc")
@Controller
public class EmployeeHandler { 
	
	private static final String LIST = "list";
	@Autowired 
	private EmployeeDao employeeDao;
	@Autowired
	private DepartmentDao departmentDao;
	
	/**
	 * 更新员工前获取员工信息
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/emp/{id}", method=RequestMethod.GET)
	public String input(@PathVariable("id") Integer id
			, Map<String, Object> map) {
		
		System.out.println("input method for get emp info by id");
		map.put("employee", employeeDao.get(id));
		map.put("depts", departmentDao.getDepartments());
		return "input";
	}
	
	/**
	 * 删除员工
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/emp/{id}", method=RequestMethod.DELETE)
	public String delete(@PathVariable("id") Integer id) {
		
		System.out.println("delete");
		employeeDao.delete(id); 
		return "redirect:/springmvc/emps";
	}
	
	/**
	 * 保存添加的员工信息
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String save(Employee employee) {
		
		System.out.println("save");
		employeeDao.save(employee);
		return "redirect:/springmvc/emps";
	}
	
	/**
	 * 在添加员工页面 获取 部门列表
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/emp", method=RequestMethod.GET)
	public String input(Map<String, Object> map) {

		System.out.println("input");
		map.put("depts", departmentDao.getDepartments());
		map.put("employee", new Employee());
		return "input";
	}
	
	/**
	 * 获取 员工列表
	 * @param map
	 * @return
	 */
	@RequestMapping("/emps")
	public String list(Map<String, Object> map) {

		System.out.println("list");
		map.put("emps", employeeDao.getAll());
		return LIST;
	}
}
