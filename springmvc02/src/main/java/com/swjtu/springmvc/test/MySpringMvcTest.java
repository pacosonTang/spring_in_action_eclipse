package com.swjtu.springmvc.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.swjtu.springmvc.dao.EmployeeDao;
import com.swjtu.springmvc.entity.Employee;

@Controller
@RequestMapping("/springmvc")
public class MySpringMvcTest {

	@Autowired
	private EmployeeDao employeeDao;
	@Autowired
	private ResourceBundleMessageSource messageSource;
	
	
	/*@ExceptionHandler(RuntimeException.class)
	public ModelAndView testHandleArithmeticException2(Exception ex) {
		
		System.out.println("【出异常了】， " + ex);
		ModelAndView view = new ModelAndView("error");
		view.addObject("ex", ex);
		return view;
	}*/
	
	/**
	 * 数学异常  ArithmeticException  处理方法
	 * 
	 * 【细节】
	 * 1、在  @ExceptionHandler 方法的入参中可以加入 Exception
	 * 类型的参数， 该参数对应发送的异常对象；
	 * 2、@ExceptionHandler 方法的入参中不能传入map， 若希望吧异常信息传导页面上， 
	 * 则需要使用 ModelAndBiew 作为返回值；
	 * 3、 标记的异常有优先级的问题： ArithmeticException 和  RuntimeException 
	 * 4、@ControllerAdvice： 如果在 当前handler中 找不到  @ExceptionHandler 方法来处理异常；
	 * 则将去 @ControllerAdvice 标记的类中 查找 @ExceptionHandler 标记的方法来处理异常；
	 * 
	 * @param ex
	 * @return
	 */
	/*@ExceptionHandler(ArithmeticException.class)
	public ModelAndView testHandleArithmeticException(Exception ex) {
		
		System.out.println("出异常了， " + ex);
		ModelAndView view = new ModelAndView("error");
		view.addObject("ex", ex);
		return view;
	}*/
	
	/**
	 * 测试 spring 抛出异常
	 * @param i
	 * @return
	 */
	@RequestMapping("/testExceptionHandlerExceptionResolver")
	public String testExceptionHandlerExceptionResolver(@RequestParam("i") int i) {
		
		System.out.println("10/i = " + 10/i);
		return "success";
	}
	
	/**
	 * 测试文件上传 
	 * @param desc
	 * @param file
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/testFileUpload")
	public String testFileUpload(@RequestParam("desc") String desc, 
			@RequestParam("file") MultipartFile file) throws IOException {
		
		System.out.println("desc = " + desc 
				+ ", \n file = " + file.getOriginalFilename() );
		System.out.println("inputStream = " + file.getInputStream()); 
		return "success";
	}
	
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
