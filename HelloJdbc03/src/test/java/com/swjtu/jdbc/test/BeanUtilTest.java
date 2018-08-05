package com.swjtu.jdbc.test;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

import com.swjtu.jdbc.bean.User;

/*java bean的属性处理工具类： （源自 apache 基金会）
commons-beanutils jar 包 工具；*/
/**
 * @author pacoson
 */
public class BeanUtilTest {
	
	/*测试commons-beanutils 中的两个方法 getProperty 方法；*/
	@Test
	public void testGetProperty() {
		Object object = new User();
		System.out.println(object);
		
		try {
			BeanUtils.setProperty(object, "userName", "张三"); // setProperty 设置属性
			Object value = BeanUtils.getProperty(object, "userName"); // getProperty 获取属性
			System.out.println(value);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	/*测试commons-beanutils 中的两个方法 setProperty 方法；*/
	@Test
	public void testSetProperty() {
		Object object = new User();
		System.out.println(object);
		
		try {
			BeanUtils.setProperty(object, "userName", "张三");
			System.out.println(object);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
