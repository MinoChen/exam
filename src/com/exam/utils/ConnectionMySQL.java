package com.exam.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionMySQL {
	public static final String DRIVERNAME = "com.mysql.cj.jdbc.Driver";
	public static final String DBURL = "jdbc:mysql://127.0.0.1/exam?characterEncoding=utf8&useSSL=false&serverTimezone=UTC";
	public static final String USERNAME = "root";
	public static final String PASSWORD = "root";
	public static Connection connection = null;
	public static PreparedStatement pstmt = null ;
	public static ResultSet rs = null ; 
	
	static {
		try {
			Class.forName(DRIVERNAME);
			System.out.println("数据库驱动加载成功！");
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("数据库驱动加载失败！");
		}
	}
	public static Connection getConnection() throws SQLException {
		if(connection==null) {
			connection = DriverManager.getConnection(DBURL,USERNAME,PASSWORD);
			return connection;
		}
		return connection;
	}
	//增加用户
	public static PreparedStatement createPreParedStatement(String sql,Object[] params) throws ClassNotFoundException, SQLException {
		  pstmt = getConnection().prepareStatement(sql) ;
		  if(params!=null ) {
			  for(int i=0;i<params.length;i++) {
				  pstmt.setObject(i+1, params[i]);
			  }
		  }
		  return pstmt;
	}
	//通用的增删改
	public static boolean executeUpdate(String sql,Object[] params) {
		try {  
			  pstmt = createPreParedStatement(sql,params);
			  int count = pstmt.executeUpdate();
			  if(count>=0) {
				  return true ;
			  }
			  else {
				  return false ;
			  }
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			  return false ;
		} catch (SQLException e) {
			e.printStackTrace();
			  return false ;
		}catch (Exception e) {
			e.printStackTrace();
			return false ;
		}
	}
		
}
