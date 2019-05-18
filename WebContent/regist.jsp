<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>注册页面</title>
</head>
<body>

<c:if test="${requestScope.message != null }">
		<h3><font color="red">${requestScope.message }</font></h3>
</c:if>

	<h2>注册页面</h2>
	<form action="/exam/RegistServlet" method="post" onsubmit="return check()">
		考号：<input type="text" name="id" id="id" value="${param.id }"/><p>
		密码：<input type="password" name="password" id="password" value="${param.password }"/><p>
		<input type="submit" value="注册"/>
		<a href="index.jsp" style="text-decoration: none;">去登录</a>
	</form>
<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
<script type="text/javascript">
	function check(){
		var id = $("#id").val();
		var password = $("#password").val();
		if(id.length<1){
			alert("请先输入考号！");
			return false;
		}
		if(!(id.length>0&&id.length<9)){
			alert("考号长度必须是1~8位！");
			return false;
		}
		if(password.length<1){
			alert("请输入密码！");
			return false;
		}
		if(!(password.length>0&&password.length<9)){
			alert("密码长度必须是1~8位！");
			return false;
		}
		return true;
	}

	$(document).ready(function(){
	});

</script>
</body>
</html>