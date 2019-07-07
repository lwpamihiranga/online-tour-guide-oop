package com.tourguide.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tourguide.bean.RestaurantBean;
import com.tourguide.dao.IRestaurantDao;
import com.tourguide.dao.RestaurantDao;

/**
 * Servlet implementation class DeleteRestaurantServlet
 */
@WebServlet("/DeleteRestaurantServlet")
public class DeleteRestaurantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteRestaurantServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		int restaurantID = Integer.parseInt(request.getParameter("restaurantID"));
		
		RestaurantBean restaurantBean = new RestaurantBean();
		
		restaurantBean.setRestaurantID(restaurantID);
		
		IRestaurantDao restaurantDao = new RestaurantDao();
		
		String deleteRestaurant = restaurantDao.deleteRestaurant(restaurantBean);
		
		if(deleteRestaurant.equals("Restaurant deleted successfully!")) {
			request.setAttribute("successMessage", deleteRestaurant);
			request.getRequestDispatcher("/restaurants.jsp").forward(request, response);
		} else {
			request.setAttribute("errMessage", deleteRestaurant);
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
