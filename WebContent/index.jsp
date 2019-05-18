<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="testBean" class="com.exam.domain.Exam" scope="session"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body bgcolor=#EAFFCF>
<jsp:setProperty name="testBean" property="score" value="0"/>
<jsp:setProperty name="testBean" property="number" value="0"/>

<c:if test="${requestScope.message != null }">
		<h3><font color="red">${requestScope.message }</font></h3>
</c:if>

<h4>考题数量是：<jsp:getProperty name="testBean" property="testAmount"/>

<form action="/exam/LoginServlet"  method="post" onsubmit="return check()">
<div align="left" >
	<font style="color: #ff0000;">${msg}</font>
</div>
	输入考号<input type="text" name="id" id="id" value ="${param.id }" /><p>
	输入密码<input type="password" name="password" id="password" value ="${param.password }" /><p>
   <input type=submit name="sub" value="开始考试">
   <a href="regist.jsp" style="text-decoration: none;">去注册</a>
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
		if(password.length<1){
			alert("请输入密码！");
			return false;
		}
		return true;
	}
	$(document).ready(function(){
	});

</script>
</body>
</html> 
