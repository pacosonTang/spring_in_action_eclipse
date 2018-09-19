<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="springmvc/scripts/jquery-1.9.1.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("#testJson").click(function(){
			var url = this.href;
			var args = {};
			$.post(url, args, function(data){
				for (var i=0; i<data.length; i++) {
					var id = data[i].id;
					var lastName = data[i].lastName;
					alert(id + ", " + lastName);
				}
			}) 
			return false;
		});
	});
</script>
</head>
<body>
	
	<!-- 创建用户 -->
	<a href="springmvc/emp"> 创建用户 </a>
	<br/><br/>
	
	<!-- 获取所有员工列表 -->
	<a href="springmvc/emps"> 员工列表 </a>
	<br/><br/>
	
	<!-- 测试json  -->
	<a href="springmvc/testJson" id="testJson"> 测试 json </a>
	<br/><br/>
	
</body>
</html>