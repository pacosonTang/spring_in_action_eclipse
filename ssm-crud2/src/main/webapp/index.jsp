<%@page contentType="text/html" pageEncoding="UTF-8"%> 
 <%
 	String basePath = request.getContextPath();
 %>  
<html>
<head>
	<meta charset="utf-8">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>Bootstrap 101 Template</title>
    <!-- Bootstrap -->
    <link href="<%=basePath %>/static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<h1>你好，世界！ 唐荣！</h1> 
    <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
    <script src="<%=basePath %>/static/js/jquery.min.js"></script>
    <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
    <script src="<%=basePath %>/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</body>
</html>
