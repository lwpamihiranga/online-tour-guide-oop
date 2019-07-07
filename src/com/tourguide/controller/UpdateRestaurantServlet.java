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

import com.tourguide.bean.RestaurantBean;
import com.tourguide.dao.IRestaurantDao;
import com.tourguide.dao.RestaurantDao;

/**
 * Servlet implementation class UpdateRestaurantServlet
 */
@WebServlet("/UpdateRestaurantServlet")
@MultipartConfig
public class UpdateRestaurantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateRestaurantServlet() {
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
		//doGet(request, response);
		
		int restaurantID = Integer.parseInt(request.getParameter("restaurantID"));
		String restaurantName = request.getParameter("restaurantName");
		String location = request.getParameter("location");
		String cuisineType = request.getParameter("cuisineType");
		int rating = Integer.parseInt(request.getParameter("rating"));
		String description = request.getParameter("description");
		Part filePart = request.getPart("image");
		
		InputStream inputStream = null;
		
		if(filePart != null) {
			inputStream = filePart.getInputStream();
		}
		
		RestaurantBean restaurantBean = new RestaurantBean();
		
		restaurantBean.setRestaurantID(restaurantID);
		restaurantBean.setRestaurantName(restaurantName);
		restaurantBean.setLocation(location);
		restaurantBean.setCuisineType(cuisineType);
		restaurantBean.setRating(rating);
		restaurantBean.setDescription(description);
		restaurantBean.setInputStream(inputStream);
		
		IRestaurantDao restaurantDao = new RestaurantDao();
		
		String updateRestaurant =  restaurantDao.updateRestaurant(restaurantBean);
		
		if(updateRestaurant.equals("Restaurant updated successfully!")) {
			request.setAttribute("successMessage", updateRestaurant);
			request.getRequestDispatcher("/restaurants.jsp").forward(request, response);
		} else {
			request.setAttribute("errMessage", updateRestaurant);
		}		
	}
}
