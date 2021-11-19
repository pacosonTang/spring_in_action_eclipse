package com.swjtu.crud.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.swjtu.crud.bean.Employee;
import com.swjtu.crud.service.EmployeeService;

/**
 * 处理员工的crud操作
 * @author pacoson
 */
@Controller
public class EmployeeController {
	@Autowired
	EmployeeService empService;
	/**
	 * 查询员工数据（分页查询）
	 * @return
	 */
	@RequestMapping("/empMaps")
	public String getEmpMaps( @RequestParam(value="deptRcrdId", defaultValue="2018") String deptRcrdId
			, @RequestParam(value="pagenum", defaultValue="1") int pagenum, 
			Model model) {
		// 引入pageHelper分页插件 
		// 在查询之前只需要调用，传入页码 pagenum 以及 每页大小5
		PageHelper.startPage(pagenum, 5);
		// startpage 后边的查询就是一个分页查询
		List<Map<String, Object>> emps = empService.empMapListQry(deptRcrdId);
		// 使用pageinfo 包装查询后的结果，只需要将 pageinfo 交给页码就行了。
		// pageinfo 封装了详细的分页信息，包括查询出来的数据。 传入连续显示的页数。
		PageInfo pages = new PageInfo(emps, 5); // 连续显示的页数
		model.addAttribute("pageinfo", pages);
		
		return "emplist";
	}
	
	@RequestMapping("/emps")
	public String getEmps( @RequestParam(value="deptRcrdId", defaultValue="2018") String deptRcrdId
			, @RequestParam(value="pagenum", defaultValue="1") int pagenum, 
			Model model) {
		// 引入pageHelper分页插件 
		// 在查询之前只需要调用，传入页码 pagenum 以及 每页大小5
		PageHelper.startPage(pagenum, 5);
		// startpage 后边的查询就是一个分页查询
		List<Employee> emps = empService.empListQry(deptRcrdId);
		// 使用pageinfo 包装查询后的结果，只需要将 pageinfo 交给页码就行了。
		// pageinfo 封装了详细的分页信息，包括查询出来的数据。 传入连续显示的页数。
		PageInfo pages = new PageInfo(emps, 5); // 连续显示的页数
		model.addAttribute("pageinfo", pages);
		
		return "emplist";
	}
}
