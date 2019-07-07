package com.tourguide.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.tourguide.bean.UserBean;
import com.tourguide.dao.IUserDao;
import com.tourguide.dao.UserDao;

/**
 * Servlet implementation class UserRegisterServlet
 */
@WebServlet("/UserRegisterServlet")
@MultipartConfig(maxFileSize = 16177215)
public class UserRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserRegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Part filePart = request.getPart("image");
		
		InputStream inputStream = null;
		
		if(filePart != null) {
			inputStream = filePart.getInputStream();
		}
		
		UserBean userBean = new UserBean();
		
		userBean.setFirstName(firstName);
		userBean.setLastName(lastName);
		userBean.setEmail(email);
		userBean.setUsername(username);
		userBean.setPassword(password);
		userBean.setInputStream(inputStream);
		
		IUserDao userDao = new UserDao();
		
		String userRegistered = userDao.registerUser(userBean);
				
		if(userRegistered.equals("Registered successfully!")) {
			request.setAttribute("name", firstName);
			request.setAttribute("successMessage", userRegistered);
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		} else {
			request.setAttribute("errorMessage", userRegistered);
			request.getRequestDispatcher("/user_register.jsp").forward(request, response);
		}
	}
}
