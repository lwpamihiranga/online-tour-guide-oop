package com.tourguide.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tourguide.bean.UserBean;
import com.tourguide.dao.IUserDao;
import com.tourguide.dao.UserDao;

/**
 * Servlet implementation class UserDeleteServlet
 */
@WebServlet("/UserDeleteServlet")
public class UserDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		Boolean admin = false;
		String username = request.getParameter("username");
		admin = Boolean.parseBoolean(request.getParameter("admin"));
		UserBean userBean = new UserBean();
		
		userBean.setUsername(username);
		
		IUserDao userDao = new UserDao();

		String deleteUser = userDao.deleteUser(userBean);
		
		if(deleteUser.equals("User deleted successfully!")) {
			if(admin) {
				request.setAttribute("successMessage", deleteUser);
				request.getRequestDispatcher("/view_users.jsp").forward(request, response);
			} else {
				response.sendRedirect("logout.jsp");
			}
			
		} else {
			request.setAttribute("errMessage", deleteUser);
		}		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
	}

}
