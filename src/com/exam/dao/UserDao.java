package com.exam.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.exam.domain.User;
import com.exam.utils.ConnectionMySQL;

public class UserDao {
	static Connection connection = null;
	static PreparedStatement pstmt = null;
	static ResultSet rs = null;
	
	public User login(User user) {
		try {
			connection = ConnectionMySQL.getConnection();
			String sql = "select * from user where id='"+user.getid()+"'and password='"+user.getPassword()+"'";
			pstmt = connection.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return user;
			}else {
				return null;
			}
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	//判断账户是否存在
	public boolean isExist(String id) {
		return false;
	}
	//添加用户
	public boolean addUser(User user){
				String sql ="insert into user(id,password) values(?,?) " ;
				Object[] params = {user.getid(),user.getPassword()};
				return ConnectionMySQL.executeUpdate(sql, params);
		}
	}
	
