<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>success</h1>
	
	time: ${requestScope.time} <br><br>
	
	names: ${requestScope.names} <br><br>
	
	request User: ${requestScope.user} <br><br>
	
	session User: ${sessionScope.user} <br><br>
	
	request "abc" User: ${requestScope.abc} <br><br>
	
	session "abc" User: ${sessionScope.abc} <br><br>
	
	request "user" User: ${requestScope.user} <br><br>
	
	session "user" User: ${sessionScope.user} <br><br>
	
	request school: ${requestScope.school} <br><br>
	
	session school: ${sessionScope.school} <br><br>
	
	<fmt:message key="i18.username"></fmt:message> <br/><br/>
	
	<fmt:message key="i18.password"></fmt:message> <br/><br/>
</body>
</html>
