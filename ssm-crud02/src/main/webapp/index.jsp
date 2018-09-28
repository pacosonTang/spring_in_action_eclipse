<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

	<!-- 引入Bootstrap样式 -->
    <link href="${pageContext.request.contextPath }/static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	
	<!-- Standard button -->
	<button type="button" class="btn btn-default">（默认样式）Default</button>
	
	<!-- Provides extra visual weight and identifies the primary action in a set of buttons -->
	<button type="button" class="btn btn-primary">（首选项）Primary</button>
	
	<!-- Indicates a successful or positive action -->
	<button type="button" class="btn btn-success">（成功）Success</button>
	
	<!-- Contextual button for informational alert messages -->
	<button type="button" class="btn btn-info">（一般信息）Info</button>
	
	<!-- Indicates caution should be taken with this action -->
	<button type="button" class="btn btn-warning">（警告）Warning</button>
	
	<!-- Indicates a dangerous or potentially negative action -->
	<button type="button" class="btn btn-danger">（危险）Danger</button>
	
	<!-- Deemphasize a button by making it look like a link while maintaining button behavior -->
	<button type="button" class="btn btn-link">（链接）Link</button>
	
	<!-- 引入jquery -->
    <script type="text/javascript" src="${pageContext.request.contextPath }/static/js/jquery-3.3.1.js"></script>
	<!-- 引入 bootstrap js -->
    <script type="text/javascript" src="${pageContext.request.contextPath }/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>  
</body>
</html>