package com.swjtu.springmvc.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
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
	@Autowired
	private ResourceBundleMessageSource messageSource;
	
	@RequestMapping("/i18n")
	public String testI18n(Locale locale) {
		String value = messageSource.getMessage("i18n.user", null, locale);
		System.out.println(value);
		return "i18n";
	}
	
	@RequestMapping("/i18n2")
	public String testI18n2(Locale locale) {
		String value = messageSource.getMessage("i18n.password", null, locale);
		System.out.println(value);
		return "i18n2";
	}
	
	/* 文件下载 */
	@RequestMapping("/testResponseEntity")
	public ResponseEntity<byte[]> testResponseEntity(HttpSession session)
			throws IOException {

		// 把文件读入到 body 字节中
		ServletContext context = session.getServletContext();
		InputStream in = context.getResourceAsStream("springmvc/scripts/jquery-1.9.1.min.js");
		byte[] body = new byte[in.available()];
		in.read(body);

		// 响应头
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment;filename=jquery-1.9.1.min.js");

		// 响应码
		HttpStatus code = HttpStatus.OK;

		// 设置响应头和响应码
		ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(body, headers, code);
		return response;
	}

	/**
	 * 研究 springmvc 传输json数据的原理： springmvc 使用到了 HttpMessageConverter
	 * 文件上传 
	 * @param body
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/testHttpMessageConverter")
	public String testHttpMessageConverter(@RequestBody String body) {

		System.out.println(body);
		return "hello world " + new Date();
	}

	/**
	 * 测试 json
	 * 
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
