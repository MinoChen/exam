package com.exam.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.exam.domain.Exam;
import com.exam.utils.ConnectionMySQL;

public class ExamServlet extends HttpServlet{
	public void init(ServletConfig config) throws ServletException{
	      super.init(config);
}
	public void doPost(HttpServletRequest request,HttpServletResponse response)
            throws ServletException,IOException{
		Exam testBean=null;
		HttpSession session=request.getSession(true);
   try{  
	   testBean=(Exam)session.getAttribute("testBean");
       if(testBean==null){
             testBean=new Exam(); //创建Javabean对象
             session.setAttribute("testBean",testBean);
         }
   }
   catch(Exception exp){
         testBean=new Exam();  //创建Javabean对象
         session.setAttribute("testBean",testBean);
   } 
  try{  
	  Class.forName(ConnectionMySQL.DRIVERNAME);
  }
  catch(Exception e){
	  e.printStackTrace();
  } 
  String id=request.getParameter("id");
  testBean.setId(id); 
  int testAmount = testBean.getTestAmount();   //考题数量
  Connection connection;
  PreparedStatement pstmt; 
  ResultSet rs;
  String sql;
  try{ 
       connection=DriverManager.getConnection(ConnectionMySQL.DBURL,ConnectionMySQL.USERNAME,ConnectionMySQL.PASSWORD);
       sql = "SELECT * FROM test";
       pstmt=connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
       rs=pstmt.executeQuery();
       rs.last();    
       int recordAmount=rs.getRow();  //得到记录数
       testAmount = Math.min(recordAmount,testAmount);
	LinkedList<Integer> list=(LinkedList<Integer>)session.getAttribute("list");
       if(list==null||list.size()==0){
          list = new LinkedList<Integer>();
          for(int i=1;i<=recordAmount;i++) {
            list.add(i);
          }
          session.setAttribute("list",list); 
       }
       int m= -1;
       int index=-1;
       if(list.size()>=1) {
          m= (int)(Math.random()*list.size());//获取随机数
          index=list.get(m);
          list.remove(m);
          session.setAttribute("list",list); 
          int tihao=testBean.getNumber();
          if(tihao<testAmount) {
              //首先判断上一题是否正确，给出分数：
             String studentAnswer=testBean.getAnswer();
             if(studentAnswer!=null&&studentAnswer.length()>=1) {
                if(studentAnswer.equalsIgnoreCase(testBean.getCorrectAnswer())){
                   float score= testBean.getScore();
                   score = score+25;
                  testBean.setScore(score);
                }
             }
             //随机抽取下一题目：
             tihao++;
             testBean.setNumber(tihao); //题号
             rs.absolute(index); //随机抽取题目
             testBean.setQuestions(rs.getString(2));//题目内容
             testBean.setChoiceA(rs.getString(3));  //题目的选择a
             testBean.setChoiceB(rs.getString(4));  //题目的选择b
             testBean.setChoiceC(rs.getString(5));  //题目的选择c
             testBean.setChoiceD(rs.getString(6));  //题目的选择d
             testBean.setImage(rs.getString(7));  //题目的示意图名称
             testBean.setCorrectAnswer(rs.getString(8).trim());//题目的答案
             testBean.setMess("现在是第"+tihao+"题");
             connection.close(); 
         }
         else {
             testBean.setMess("答题结束，单击交卷查看分数");
             String studentAnswer=testBean.getAnswer(); //判断最后一题
             if(studentAnswer!=null&&studentAnswer.length()>=1) {
                if(studentAnswer.equalsIgnoreCase(testBean.getCorrectAnswer())){
                   float score = testBean.getScore();
                   score = score+25;
                  testBean.setScore(score);
                }
             }
             testBean.setAnswer(null);
             testBean.setNumber(0);
             testBean.setQuestions(null);
             testBean.setChoiceA(null);  
             testBean.setChoiceB(null);  
             testBean.setChoiceC(null);  
             testBean.setChoiceD(null);  
             testBean.setImage(null); 
         }
       }
       else {
          testBean.setMess("没有抽到题目");
       }
       response.sendRedirect("examination.jsp");
  }
  catch(SQLException e){
	  e.printStackTrace();
  }  
}
public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
    	doPost(request,response);
	}

}


