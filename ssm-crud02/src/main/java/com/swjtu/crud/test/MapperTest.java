package com.swjtu.crud.test;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 测试dao层工作
 * @author pacoson
 * 推荐使用 spring-test 即spring的单元测试，可以自动注入我们需要的组件
 * 1、导入spring-test模块
 * 2、@ContextConfiguration 指定 spring 配置文件的位置 
 * 3、直接 autowire 要使用的组件即可 
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:springmvc.xml"})
public class MapperTest {
	
}
