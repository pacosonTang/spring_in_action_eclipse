<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<br/><br/>
	<!-- 更新操作 -->
	<form action="springmvc/testRest/3" method="post">
		<input type="hidden" name="_method" value="PUT">
		<input type="submit" value="test rest put"/>
	</form>
	<br/><br/>
	
	<!-- 删除操作 -->
	<form action="springmvc/testRest/2" method="post">
		<input type="hidden" name="_method" value="DELETE">
		<input type="submit" value="test rest delete"/>
	</form>
	<br/><br/>
	
	<a href="/springmvc/testRest/1">test rest get</a>	
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