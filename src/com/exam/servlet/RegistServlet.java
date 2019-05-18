package com.exam.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.exam.domain.User;
import com.exam.service.UserService;

public class RegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public RegistServlet() {
        super();
    }
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		User user = new User(id,password);
		UserService userservice = new UserService();
		boolean result = userservice.addUser(user);
		if(!result) {
			request.setAttribute("message", "账号" + id + "已存在, 请重新选择!");
			System.out.println("账号已存在!注册失败！");
			request.getRequestDispatcher("/regist.jsp").forward(request, response);
		}else {
			System.out.println("注册成功！");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
