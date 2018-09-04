package com.swjtu.springmvc.handler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @RequestMapping 既可以修饰类也可以修饰方法 
 */ 
@RequestMapping("/springmvc")
@Controller
public class SpringMvcTest {
	private final static String SUCCESS = "success";
	
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
