package com.tourguide.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tourguide.bean.LoginBean;
import com.tourguide.dao.IUserDao;
import com.tourguide.dao.UserDao;

/**
 * Servlet implementation class UserLoginServlet
 */
@WebServlet("/UserLoginServlet")
public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		LoginBean loginBean = new LoginBean();
		
		loginBean.setUsername(username);
		loginBean.setPassword(password);
		
		IUserDao userDao = new UserDao();
		
		String userValidate = userDao.authenticateUser(loginBean);
		
		if(userValidate.equals("SUCCESS LOGIN")) {
			HttpSession session = request.getSession();
			session.setAttribute("login", loginBean.getUsername());
			request.setAttribute("successMessage", userValidate);
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
		} else {
			request.setAttribute("errMessage", userValidate);
			RequestDispatcher rd = request.getRequestDispatcher("user_login.jsp");
			rd.include(request, response);
		}
	}

}
