package com.exam.service;

import com.exam.dao.UserDao;
import com.exam.domain.User;

public class UserService {
	UserDao userDao = new UserDao();
	public boolean addUser(User user) {
		if(!userDao.isExist(user.getid())) {
			return userDao.addUser(user);
		}else {
			System.out.println("用户已存在！");
			return false;
		}
		
	}
}
