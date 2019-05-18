package com.exam.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.exam.dao.UserDao;
import com.exam.domain.User;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    UserDao userDao;
    HttpSession session;
    public LoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		UserDao userDao = new UserDao();
		User user = new User();
		user.setid(request.getParameter("id"));
		user.setPassword(request.getParameter("password"));
		user = userDao.login(user);
		System.out.println(user);
		if(user!=null) {
			session = request.getSession();
			if(session.getAttribute("id")!=null) {
				session.removeAttribute("id");
			}
			System.out.println("登录成功！");
			session.setAttribute("user",user);
			System.out.println(session.getAttribute("user"));
			request.getRequestDispatcher("/ExamServlet").forward(request, response);
		}else {
			System.out.println("登录失败！");
			request.setAttribute("message", "账号或密码错误!登录失败！");
			request.getRequestDispatcher("index.jsp").forward(request,response);
		}
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}
}

