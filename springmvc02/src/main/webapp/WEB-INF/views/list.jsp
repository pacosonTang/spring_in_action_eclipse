<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>员工列表</title>
</head>
<body>
	<h1>员工列表</h1>
	<c:if test="${empty requestScope.emps }">
		没有任何员工信息
	</c:if>
	<c:if test="${!empty requestScope.emps }">
		<table border="1" cellpadding="10" cellspacing="0">
			<tr>
				<th>id</th>
				<th>lastname</th>
				<th>email</th>
				<th>gender</th>
				<th>department</th>
				<th>edit</th>
				<th>delete</th>
			</tr>
			<c:forEach items="${requestScope.emps }" var="emp">
				<tr>
					<td>${emp.id }</td>
					<td>${emp.lastName }</td>
					<td>${emp.email }</td>
					<td>${emp.gender }</td>
					<td>${emp.department.departmentName }</td>
					<td><a href="springmvc/edit/${emp.id }">edit</a></td>
					<td><a href="springmvc/delete/${emp.id }">delete</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>
