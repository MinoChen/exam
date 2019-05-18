package com.exam.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.exam.domain.Exam;
import com.exam.utils.ConnectionMySQL;

public class ExamServletEnd extends HttpServlet{
	public void init(ServletConfig config) throws ServletException{
	      super.init(config);
	   }
	public void doPost(HttpServletRequest request,HttpServletResponse response)
             throws ServletException,IOException{
	Exam testBean=null;
	HttpSession session=request.getSession(true);
    try{  
    	testBean=(Exam)session.getAttribute("testBean");
    }
    catch(Exception exp){
       response.sendRedirect("index.jsp");   
    } 
    try{  
    	Class.forName(ConnectionMySQL.DRIVERNAME);
    }
    catch(Exception e){
    	e.printStackTrace();
    } 
    Connection connection;
    PreparedStatement pstmt; 
    String id=testBean.getId();
    float score = (float) testBean.getScore();
    String sql = "INSERT INTO student VALUES"+"("+"'"+id+"',"+score+")";
    try{ 
    	connection=DriverManager.getConnection(ConnectionMySQL.DBURL,ConnectionMySQL.USERNAME,ConnectionMySQL.PASSWORD);
        pstmt=connection.prepareStatement(sql);
        pstmt.executeUpdate();
        notify(request,response,"考号:"+id+"<p>"+"最后得分:"+score+"分");
        session.invalidate();              //销毁用户的session对象
    }
    catch(SQLException exp){
    	exp.printStackTrace();
    }   
 }
 public  void  doGet(HttpServletRequest request,HttpServletResponse response)
         throws ServletException,IOException{
     doPost(request,response);
 }
 public void notify(HttpServletRequest request,HttpServletResponse response,
                    String backNews) {
      try {
       PrintWriter out=response.getWriter();
       out.println("<html><body>");
       out.println("<h2>"+backNews+"</h2>") ;
       out.println("</body></html>");
      }
      catch(IOException exp){
    	  exp.printStackTrace();
      } 
  }
}
