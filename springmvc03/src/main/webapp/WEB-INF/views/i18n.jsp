<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>i18n</title>

</head>
<body>
		<fmt:message key="i18n.user"></fmt:message>
		<br/>
		<br/>
		
		<a href="${pageContext.request.contextPath }/springmvc/i18n2?locale=zh_CH">i18n2 page 中文</a>
		
		<a href="${pageContext.request.contextPath }/springmvc/i18n2?locale=en_US">i18n2 page 英文</a>
</body>
</html>
