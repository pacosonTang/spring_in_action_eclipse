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
	<h1>添加员工</h1>
	
	<!--  
		1. WHY 使用 form 标签呢 ?
		可以更快速的开发出表单页面, 而且可以更方便的进行表单值的回显
		2. 注意:
		可以通过 modelAttribute 属性指定绑定的模型属性,
		若没有指定该属性，则默认从 request 域对象中读取 command 的表单 bean
		如果该属性值也不存在，则会发生错误。
	-->
	<form:form action="save" method="post" modelAttribute="employee">
		<!-- path属性对应html 表单的name属性 -->
		lastname ： <form:input path="lastName" /> 
		<br>
		email： <form:input path="email"/> 
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
		<input type="submit" value="提交">
	</form:form>
	<br><br>
	
	
</body>
</html>
