<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户登录</title>
</head>
<body>
	<h1>用户登录</h1>
	<hr/>
	<form action="${pageContext.request.contextPath}/CoreServlet" method="post">
		FromUserName：<input type="text" name="FromUserName"><br/>
		ToUserName：<input type="text" name="ToUserName"><br/>
		MsgType：<input type="text" name="MsgType"><br/>
		<input type="submit" value="登录">
	</form> 
</body>
</html>