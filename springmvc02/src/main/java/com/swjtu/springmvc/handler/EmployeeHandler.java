package com.swjtu.springmvc.handler;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	@ModelAttribute
	public void getEmployee(@RequestParam(value="id", required=false) Integer id
			, Map<String, Object> map) {
		if (id != null) {
			map.put("emp", employeeDao.get(id));
		}
	} 
	/**
	 * 更新员工
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/save", method=RequestMethod.PUT)
	public String update(@ModelAttribute("emp") Employee employee) {
		
		System.out.println("update");
		employeeDao.save(employee);
		return "redirect:/emps";
	}
	
	/**
	 * 更新员工前获取员工信息
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/emp/{id}", method=RequestMethod.GET)
	public String input(@PathVariable("id") Integer id
			, Map<String, Object> map) {
		
		System.out.println("input method for get emp info by id");
		map.put("emp", employeeDao.get(id));
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
	public String save(Employee employee, BindingResult result) {
		
		if (result.getErrorCount() > 0) {
			System.out.println("出错了");
			
			for(FieldError error : result.getFieldErrors()) {
				System.out.println(error.getField() + " : " + error.getDefaultMessage());
			} 
		}
		System.out.println("save " + employee);
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
		map.put("emp", new Employee());
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
	
	/**
	 * 由 @InitBinder 标识的方法• ，可以对 WebDataBinder 对
		象进行初始化。WebDataBinder 是 DataBinder 的子类，用
		于完成由表单字段到 JavaBean 属性的绑定
		@InitBinder方法不能有返回值，它必须声明为void• 。
		@InitBinder方法的参数通常是是 WebDataBinder
	 * @param binder 
	 */
	/*@InitBinder
	public void initBinder(WebDataBinder binder) {
		// 不对 lastName 赋值 
		binder.setDisallowedFields("lastName");
	}*/
}
