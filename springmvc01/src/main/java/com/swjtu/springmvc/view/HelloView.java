package com.swjtu.springmvc.view;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.View;

/**
 * 自定义视图 ，需要实现 View 接口：
 * 自定义视图可以整合 excel (AbstractExcelView)， 或其他组件 
 * 
 * @author pacoson
 *
 */
@Component
public class HelloView implements View {

	@Override
	public String getContentType() {

		return "text/html";
	}

	@Override
	public void render(Map<String, ?> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.getWriter().println("hello view, time = " + new Date());
	}

}
