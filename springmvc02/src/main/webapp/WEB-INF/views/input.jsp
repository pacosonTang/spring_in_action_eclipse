<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加员工</title>
</head>
<body>

	<form action="${pageContext.request.contextPath }/springmvc/testConverter" method="POST">
		<!-- lastName, email, gender, department.id 如 gg, gg@baidu.com， 0， 111-->
		employee: <input type="text" name="employee"/>
		<input type="submit" value="Submit"/>
	</form>
	
	<br><br>
	<h1>添加员工</h1>
	
	<!--  
		1. WHY 使用 form 标签呢 ?
		可以更快速的开发出表单页面, 而且可以更方便的进行表单值的回显
		2. 注意:
		可以通过 modelAttribute 属性指定绑定的模型属性,
		若没有指定该属性，则默认从 request 域对象中读取 command 的表单 bean
		如果该属性值也不存在，则会发生错误。
	-->
	<form:form action="${pageContext.request.contextPath }/springmvc/save" method="POST" modelAttribute="emp">
		
		<form:errors path="*"></form:errors>
		
		<!-- path属性对应html 表单的name属性 -->
		<!-- 修改emp时， lastname 不能被显示 -->
		<c:if test="${emp.id == null }">
			lastname ： <form:input path="lastName" />
			<form:errors path="lastName"></form:errors>
		</c:if>
		<c:if test="${emp.id != null} ">
			<form:hidden path="id"/>
			<input type="hidden" name="_method" value="PUT" />
			<%-- 对于 _method 不能使用 form:hidden 标签, 因为 modelAttribute 对应的 bean 中没有 _method 这个属性 --%>
			<%-- 
			<form:hidden path="_method" value="PUT"/>
			--%>
		</c:if> 
		<br>
		email： <form:input path="email"/>
		<form:errors path="email"></form:errors> 
		<br>
		<%
			Map<String, String> genders = new HashMap<String, String>();
			genders.put("0", "male");
			genders.put("1", "female");
			request.setAttribute("genders", genders);
		%>
		gender：<form:radiobuttons path="gender" items="${genders }"/>
		<br>
		department： <form:select path="department.id" 
			items="${depts }" itemLabel="departmentName" itemValue="id"></form:select> 
			<br>
			<!--  
				问题1、数据类型转换；
				问题2、数据类型格式化；
				问题3、数据校验问题；
				3.1） 如何校验？ 注解？
				3.1.1） 使用 JSR 303验证 标准；
				3.1.2） 加入 hibernate validator 验证框架 的jar包；
				3.1.3） 在 springmvc 配置文件中添加  <mvc:annotation-driven />
				3.1.4) 需要在bean的属性上 添加对应的注解；
				3.1.5） 在目标方法bean类型的前面添加 @Valid 注解；
				 
				3.2）验证出错 转向到 哪一个页面？
				3.3）错误消息？ 如何显示， 如何把错误消息进行国际化；
				
			-->
			birth:<form:input path="birth" /><br>
			<form:errors path="birth"></form:errors>
			salary:<form:input path="salary" /><br>
		<input type="submit" value="提交">
	</form:form>
	<br><br>
	
	
</body>
</html>
