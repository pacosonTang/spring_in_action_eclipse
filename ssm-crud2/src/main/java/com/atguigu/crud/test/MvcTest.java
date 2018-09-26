package com.atguigu.crud.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import com.atguigu.crud.bean.Employee;
import com.github.pagehelper.PageInfo;

/**
 * 使用spring测试模块提供的测试请求功能， 测试crud请求的正确性
 * @author pacoson
 *
 */
@RunWith(SpringJUnit4ClassRunner.class) // 指定使用哪一个单元测试来测试
@WebAppConfiguration // web的ioc容器
@ContextConfiguration(locations={"classpath:applicationContext.xml", "file:src/main/webapp/WEB-INF/dispatcherServlet-servlet.xml"})
public class MvcTest {
	// 传入springmvc的ioc
	@Autowired
	WebApplicationContext contex;
	// 虚拟mvc请求，获取处理结果
	MockMvc mockMvc;
	
	@Before
	public void initMockMvc() {
		mockMvc = MockMvcBuilders.webAppContextSetup(contex).build();
	}
	
	@Test
	public void testPage2() throws Exception {
		// 模拟请求并拿到返回值
		Map<String, String> params = new HashMap<String, String>();
		params.put("pn", "1");
		params.put("dept_rcrd_id", "2018");
		LinkedMultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.setAll(params);
		
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/empMaps").param("pagenum", "2"))
		.andReturn();
		
		// 请求成功后，请求域中会有 pageinfo， 可以取出 pageinfo 进行验证
		MockHttpServletRequest request = result.getRequest();
		PageInfo<Map<String, Object>> pageinfo = (PageInfo<Map<String, Object>>) request.getAttribute("pageinfo");
		System.out.println("当前页码：" + pageinfo.getPageNum());
		System.out.println("总页码：" + pageinfo.getPages());
		System.out.println("总记录数：" + pageinfo.getTotal());
		System.out.println("在页面需要连续显示的页码：");
		int[] nums = pageinfo.getNavigatepageNums();
		for (int i: nums) {
			System.out.print(" " + i);
		}
		
		// 获取员工数据
		List<Map<String, Object>> list = pageinfo.getList();
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> temp = list.get(i);
			Set<String> keys = temp.keySet();
			for(String key : keys) {
				System.out.print(key + " : " + temp.get(key) + ", ");
			}
			System.out.println();
		}
	}
	
//	@Test 
	public void testPage1() throws Exception {
		// 模拟请求并拿到返回值
		Map<String, String> params = new HashMap<String, String>();
		params.put("pn", "1");
		params.put("dept_rcrd_id", "2018");
		LinkedMultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.setAll(params);
		
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/emps").param("pagenum", "1"))
		.andReturn();
		
		// 请求成功后，请求域中会有 pageinfo， 可以取出 pageinfo 进行验证
		MockHttpServletRequest request = result.getRequest();
		PageInfo<Employee> pageinfo = (PageInfo<Employee>)request.getAttribute("pageinfo");
		System.out.println("当前页码：" + pageinfo.getPageNum());
		System.out.println("总页码：" + pageinfo.getPages());
		System.out.println("总记录数：" + pageinfo.getTotal());
		System.out.println("在页面需要连续显示的页码：");
		int[] nums = pageinfo.getNavigatepageNums();
		for (int i: nums) {
			System.out.print(" " + i);
		}
		
		// 获取员工数据
		List<Employee> list = pageinfo.getList();
		for (Employee e : list) {
			System.out.println(e);
		}
	}
}



