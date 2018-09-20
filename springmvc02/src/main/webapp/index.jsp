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
	
	<!-- 测试把文件内容转换为 json 字符串  --> 
	<form action="springmvc/testHttpMessageConverter" method="post" enctype="multipart/form-data">
		file: <input type="file" name="file" /> <br>
		desc: <input type="text" name="desc" /> <br>
		<input type="submit" name="submit" />
	</form>
	<br>
	
	<!-- 文件下载的荔枝 -->
	<a href="springmvc/testResponseEntity">test Response Entity</a> <br>
	
	<!-- 
		关于国际化
		1. 在页面上能够根据浏览器语言设置的情况对文本（不是内容），时间， 数值进行本地化处理；
		2. 可以在bean 中获取国际化资源文件 Locale 对应的消息
		3. 可以通过超链接切换 Locale ， 而不再依赖于浏览器的语言设置情况
		
		解决方法：
		1. 使用 jstl的fmt 的标签
		2. 在  bean 中注入  ResourceBundleMessageSource 实例，使用其对应的
		getMessage 方法即可
		3. 配置LocaleResolver 和 LocaleChangeInterceptor
	-->
	
	<br><br>
	<a href="i18n">i18n page</a>
	
	
	
</body>
</html>