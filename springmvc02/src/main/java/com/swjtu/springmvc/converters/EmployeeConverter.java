package com.swjtu.springmvc.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.swjtu.springmvc.entity.Department;
import com.swjtu.springmvc.entity.Employee;

/**
 * @author pacoson
 * 类型转换器-把字符串转换为 Employee 类型  
 */
@Component
public class EmployeeConverter implements Converter<String, Employee> {

	@Override
	public Employee convert(String source) {
		if (source != null) {
			String[] values = source.split("-"); //
			if (values != null && values.length == 4) {
				String lastName = values[0];
				String email = values[1];
				Integer gender = Integer.parseInt(values[2]);
				Integer deptId = Integer.parseInt(values[3]);
				Department dept = new Department();
				dept.setId(deptId);
				dept.setDepartmentName("dept-" + deptId);
				
				Employee emp = new Employee(null, lastName, email, gender, dept);
				return emp;
			}
		}
		return null;
	}

}
