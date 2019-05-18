<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="testBean" 
     class="com.exam.domain.Exam" scope="session"/>
<HTML><body bgcolor=#DEEFF9><font size=2>
  <b> <jsp:getProperty name="testBean" property="questions"/></b>
  <br> <br><jsp:getProperty name="testBean" property="choiceA"/>
  <br> <br><jsp:getProperty name="testBean" property="choiceB"/>
  <br> <br><jsp:getProperty name="testBean" property="choiceC"/>
  <br> <br><jsp:getProperty name="testBean" property="choiceD"/> 
  
  <c:if test="${testBean.image!=null&&testBean.image.length()>=1 }">
  	<p><image src="/exam/image/${testBean.image }" width=450 height=242.25></image>
  </c:if>
  <br>
  <c:if test="${param.R!=null&&param.R.length()>=1 }">
  	<c:set value="${param.R.trim() }" target="${sessionScope.testBean}" property="answer"></c:set>
  </c:if>
   <br>目前分数： <jsp:getProperty name="testBean" property="score"/>,
   消息： <jsp:getProperty name="testBean" property="mess"/>
 <form method="post" name=form>
   选择:<input type="radio" name="R" value=A>A 
            <input type="radio" name="R" value=B>B
            <input type="radio" name="R" value=C>C
            <input type="radio" name="R" value=D>D
   <p><input type="submit" value="确认(再读取下一题之前，可反复确认)" name="submit">
 </form>
你目前给出的选择是${param.R }
 <form action="ExamServlet" method=post name=form>
   <input type= "hidden" 
        value="${testBean.id }" name ="id">
     <br><input type="submit" value="下一题" name="submit">
 </form> 
 <form action="ExamServletEnd" method=post name=form>
    <input type="submit" value="交卷" name="submit">
 </form> 
</font></body></HTML> 
