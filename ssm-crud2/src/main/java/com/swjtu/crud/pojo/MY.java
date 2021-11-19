package com.swjtu.crud.bean;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mchange.v2.c3p0.cfg.C3P0Config;

public class MY {
	Controller c1;
	
	InternalResourceViewResolver a;
	
	SqlSessionFactoryBean s;
	
	MapperScannerConfigurer c;
	
	DataSourceTransactionManager m;
}
