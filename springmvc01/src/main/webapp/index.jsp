<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<!-- 测试重定向 -->
	<a href="springmvc/testRedirect"> 测试重定向  </a>
	<br/><br/>
	
	
	<!-- 测试自定义视图  -->
	<a href="springmvc/testCustomView"> 自定义视图 </a>
	<br/><br/>
	
	<!-- 测试视图和视图解析器 -->
	<a href="springmvc/testViewAndViewResolver"> test ViewAndViewResolver</a>
	<br/><br/>
	
	<!--  
		模拟修改操作
		1. 原始数据为: 1, Tom, 123456,tom@swjtu.com,12
		2. 密码不能被修改.
		3. 表单回显, 模拟操作直接在表单填写对应的属性值
	-->
	<form action="springmvc/testModelAttribute" method="Post">
		<input type="hidden" name="id" value="1"/>
		username: <input type="text" name="username" value="Tom"/>
		<br>
		email: <input type="text" name="email" value="tom@swjtu.com"/>
		<br>
		age: <input type="text" name="age" value="12"/>
		<br>
		<input type="submit" value="Submit"/>
	</form>
	<br><br>
	
	<!-- SessionAttributes 把数据封装到 session 域中 -->
	<a href="springmvc/testSessionAttributes">testSessionAttributes</a>
	<br/><br/>
	
	<!-- testMap 基于map传递数据到前台 -->
	<a href="springmvc/testMap">testMap</a>
	<br/><br/>
	
	<!-- testMap  -->
	<a href="springmvc/testMap">testMap</a>
	<br/><br/>
	
	<!-- testModelAndView 测试模型和视图数据 -->
	<a href="springmvc/testModelAndView">testModelAndView 测试模型和视图数据</a>
	<br/><br/>
	
	<a href="springmvc/testServletApi">test servlet api（servlet原生api）</a>
	<br/><br/>
	
	<!-- Spring MVC 会按请求参数名和 POJO 属性名进行自动匹配， 
	 *  自动为该对象填充属性值。支持级联属性。 -->
	<form action="springmvc/testPojo" method="post">
		username: <input type="text" name="username"/> <br/>
		password: <input type="password" name="password"/> <br/>
		email: <input type="text" name="email"/> <br/>
		age: <input type="text" name="age"/> <br/>
		province: <input type="text" name="address.province"/> <br/>
		city: <input type="text" name="address.city"/> <br/>
		<input type="submit" value="submit"/>
	</form>
	<br/><br/>
	
	<!--使用 @CookieValue 获取 Cookie 信息   -->
	<a href="springmvc/testCookieValue">使用 @CookieValue 获取 Cookie 信息  </a>
	<br/><br/>
	
	
	<!--使用 @RequestHeader 获取请求头  -->
	<a href="springmvc/testRequestHeader?username=tang">使用 @RequestHeader 获取请求头</a>
	<br/><br/>
	
	<!--使用 @RequestParam 获取请求参数  -->
	<a href="springmvc/testRequestParam?username=tang">使用 @RequestParam 获取请求参数</a>
	<br/><br/>
	
	<!-- 更新操作 -->
	<form action="springmvc/testRest/3" method="post">
		<input type="hidden" name="_method" value="PUT">
		<input type="submit" value="test rest PUT OR UPDATE"/>
	</form>
	<br/><br/>
	
	<!-- 删除操作 -->
	<form action="springmvc/testRest/2" method="post">
		<input type="hidden" name="_method" value="DELETE">
		<input type="submit" value="test rest DELETE"/>
	</form>
	<br/><br/>
	
	<!-- 新增操作 -->
	<form action="springmvc/testRest/2" method="post">
		<input type="submit" value="test rest POST OR CREATE"/>
	</form>
	<br/><br/>
	
	<!-- 获取操作 -->
	<a href="springmvc/testRest/1">test rest get</a>	
	<br/><br/>
	
	<a href="springmvc/testPathVariable/120">testPathVariable</a>
	<br/>
	
	<a href="springmvc/testAntPath/1/2/abc">testAntPath</a>
	<br/>
	
	<a href="springmvc/testParamsAndHeaders?username=1&age=11">testParamsAndHeaders</a>
	<br/>
	
	<form action="springmvc/testMethod" method="post">
		<input type="submit" value="提交"/>
	</form> 
	<a href="springmvc/testMethod">testMethod</a><br/>
	<a href="springmvc/testRequestMapping">testRequestMapping</a><br/>
	<a href="helloworld">hello world</a> <br/>
</body>
</html>