package com.swjtu.springmvc.handler;

import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.swjtu.springmvc.entity.User;

/**
 * @RequestMapping 既可以修饰类也可以修饰方法 
 */ 
@SessionAttributes(value={"user"}, types={User.class, String.class})
// @SessionAttributes 修饰的属性 既会放到request 请求域里面， 也会放到session 域里边

@RequestMapping("/springmvc")
@Controller
public class SpringMvcTest {
	
	private final static String SUCCESS = "success";
	

	/**
	 * @SessionAttributes 除了可以通过属性名指定需要放到会话中的属性外(实际上使用的是 value 属性值),
	 * 还可以通过模型属性的对象类型指定哪些模型属性需要放到会话中(实际上使用的是 types 属性值)
	 * 
	 * 注意: 该注解只能放在类的上面. 而不能修饰放方法. 
	 * @param map
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/testSessionAttributes")
	public String testSessionAttributes(Map<String, Object> map) throws IOException {
		
		User user = new User("1", "2", "3", "4");
		map.put("user", user);
		map.put("school", "swjtu");
		return SUCCESS; 
	}
	
	/**
	 * 目标方法可以添加 Map 类型(实际上也可以是 Model 类型或 ModelMap 类型)的参数. 
	 * @param map
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/testMap")
	public String testMap(Map<String, Object> map) throws IOException {
		System.out.println(map.getClass().getName());
		map.put("names", Arrays.asList("A", "B", "C"));
		return SUCCESS; 
	}
	
	/** 
	 * 目标方法的返回值可以是 ModelAndView 类型。 
	 * 其中可以包含视图和模型信息, 
	 * SpringMVC 会把 ModelAndView 的 model 中数据放入到 request 域对象中。 
	 * 
	 */
	@RequestMapping(value="/testModelAndView")
	public ModelAndView testModelAndView() throws IOException {
		String viewName = SUCCESS;
		ModelAndView mav = new ModelAndView(viewName);
		// 添加模型数据到 ModelAndView 中；
		mav.addObject("time", new Date());
		return mav;
	}
	
	/** 
	 * servlet原生api:
	 * 
	 * 可以使用 Serlvet 原生的 API 作为目标方法的参数 具体支持以下类型:
	 * 
	 * HttpServletRequest 
	 * HttpServletResponse 
	 * HttpSession
	 * java.security.Principal 
	 * Locale InputStream 
	 * OutputStream 
	 * Reader 
	 * Writer
	 * @throws IOException 
	 */
	@RequestMapping(value="/testServletApi")
	public void testServletApi(HttpServletRequest request, 
			HttpServletResponse response, Writer out) throws IOException {
		System.out.println("testServletApi(): HttpServletRequest = " + request 
				+ ", \n HttpServletResponse" + response);
		out.write("hello springmvc"); 
	}
	
	/** 
	 *  Spring MVC 会按请求参数名和 POJO 属性名进行自动匹配， 
	 *  自动为该对象填充属性值。支持级联属性。
	 * 如：dept.deptId、dept.address.tel 等
	 */
	@RequestMapping(value="/testPojo")
	public String testPojo(User user) {
		System.out.println("testPojo(): " + user);
		return SUCCESS;
	}
	
	/** 
	 * 使用 @CookieValue 获取 Cookie 信息 ， 
	 * 仅了解 
	 * @return
	 */
	@RequestMapping(value="/testCookieValue")
	public String testCookieValue(
			@CookieValue(value="JSESSIONID") String JSESSIONID) {
		System.out.println("testCookieValue : JSESSIONID = " + JSESSIONID);
		return SUCCESS;
	}
	
	/** 
	 * 使用 @RequestHeader 获取请求头，用法同 RequestParam 
	 * 了解：使用机会较少 
	 * @return
	 */
	@RequestMapping(value="/testRequestHeader")
	public String testRequestHeader(
			@RequestHeader(value="Accept-Language") String acceptLang) {
		System.out.println("testRequestHeader: AcceptLang = " + acceptLang);
		return SUCCESS;
	}
	
	/** 
	 * 使用 @RequestParam 获取请求参数:
	 * @RequestParam 来映射请求参数. 
	 *  value 值即请求参数的参数名, 
	 *  required 该参数是否必须. 默认为 true
	 *  , defaultValue 请求参数的默认值 
	 * @return
	 */
	@RequestMapping(value="/testRequestParam")
	public String testRequestParam(
			@RequestParam(value="username") String username
			, @RequestParam(value="age", required=false, defaultValue="0") Integer age) {
		System.out.println("testRequestParam： username = " + username);
		System.out.println("testRequestParam： age = " + age); 
		return SUCCESS;
	}
	
	/**
	 * rest 风格url。
	 * 以 crud为例：
	 * 新增： /order POST
	 * 修改：/order/1 PUT
	 * 获取：/order/1 GET
	 * 删除：/order/1  DELETE
	 * 
	 * 如何发送 PUT 请求 和 DELETE 请求呢？
	 * 1、需要配置 filter hiddenHttpMethodFilter
	 * 2、需要发送 POST 请求
	 * 3、需要在发送 POST 请求时， 携带一个 隐藏域 name="_method"，
	 * 值 value="DELETE" 或 value="PUT"
	 * 
	 *  在 springmvc 中的 目标方法中如何得到 id 呢？
	 *  使用 @PathVariable 注解。
	 */
	
	/**
	 * Rest 风格编程：更新
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/testRest/{id}", method=RequestMethod.PUT)
	public String testRestPut(@PathVariable("id") Integer id) {
		System.out.println("testRestPut(), id = " + id);
		return SUCCESS;
	}
	/**
	 * Rest 风格编程：删除
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/testRest/{id}", method=RequestMethod.DELETE)
	public String testRestDelete(@PathVariable("id") Integer id) {
		System.out.println("testRestDelete(), id = " + id);
		return SUCCESS;
	}
	/**
	 * Rest 风格编程：新增
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/testRest/{id}", method=RequestMethod.POST)
	public String testRestPost(@PathVariable("id") Integer id) {
		System.out.println("testRestPost(), id = " + id);
		return SUCCESS;
	}
	/**
	 * Rest 风格编程：查询
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/testRest/{id}", method=RequestMethod.GET)
	public String testRestGet(@PathVariable("id") Integer id) {
		System.out.println("testRest(), id = " + id);
		return SUCCESS;
	}
	
	/**
	 * @PathVariable 可以来映射 URL 中的占位符到目标方法的参数中.
	 * @param id
	 * @return
	 */
	@RequestMapping("/testPathVariable/{id}")
	public String testPathVariable(@PathVariable("id") Integer id) {
		System.out.println("testPathVariable(), id = " + id);
		return SUCCESS;
	}
	
	@RequestMapping("/testAntPath/**/abc")
	public String testAntPath() {
		System.out.println("testAntPath");
		return SUCCESS;
	}
	/**
	 * 使用参数和请求头进行映射，不常用，仅了解 
	 * @return
	 */
	@RequestMapping(
			value="/testParamsAndHeaders"
			, params={"username", "age!=10"}
			, headers={"Accept-Language: zh-CN,zh;q=0.8"}
	)
	public String testParamsAndHeaders() {
		System.out.println("SpringMvcTest.testParamsAndHeaders() ");
		return SUCCESS;
	}
	
	/**
	 * 使用 method 属性来指定请求方式 
	 * @return
	 */
	@RequestMapping(value="/testMethod", method=RequestMethod.POST)
	public String testMethod() {
		System.out.println("SpringMvcTest.testMethod() ");
		return SUCCESS;
	}
	
	@RequestMapping("/testRequestMapping")
	public String hello() {
		System.out.println("SpringMvcTest.hello() ");
		return SUCCESS;
	}
}
