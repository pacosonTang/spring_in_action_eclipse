package com.atguigu.crud.test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

public class MBGTest {
	private static String path = System.getProperty("user.dir");
//	E:\bench-cluster\spring_in_action_eclipse\ssm-crud\WebContent\
	private static String filepath = path + File.separator + "src" + File.separator + "main"
			+ File.separator + "webapp";
	
	public static void main(String[] args) {
		List<String> warnings = new ArrayList<String>();
		boolean overwrite = true;
		File configFile = new File(filepath + "mybatis-generator.xml");
		ConfigurationParser cp = new ConfigurationParser(warnings);
		Configuration config;
		
		try {
			config = cp.parseConfiguration(configFile);
			DefaultShellCallback callback = new DefaultShellCallback(overwrite);
			MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
			myBatisGenerator.generate(null);
			System.out.println("bingo!!");
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
