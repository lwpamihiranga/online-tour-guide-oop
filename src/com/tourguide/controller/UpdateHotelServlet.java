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

import com.tourguide.bean.HotelBean;
import com.tourguide.dao.HotelDao;
import com.tourguide.dao.IHotelDao;

/**
 * Servlet implementation class UpdateHotelServlet
 */
@WebServlet("/UpdateHotelServlet")
@MultipartConfig
public class UpdateHotelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateHotelServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		
		int hotelID = Integer.parseInt(request.getParameter("hotelID"));
		String hotelName = request.getParameter("hotelName");
		String location = request.getParameter("location");
		int rating = Integer.parseInt(request.getParameter("rating"));
		String description = request.getParameter("description");
		Part filePart = request.getPart("image");
		
		InputStream inputStream = null;
		
		if(filePart != null) {
			inputStream = filePart.getInputStream();
		}
		
		HotelBean hotelBean = new HotelBean();
		
		hotelBean.setHotelID(hotelID);
		hotelBean.setHotelName(hotelName);
		hotelBean.setLocation(location);
		hotelBean.setRating(rating);
		hotelBean.setDescription(description);
		hotelBean.setInputStream(inputStream);
		
		IHotelDao hotelDao = new HotelDao();
		
		String updateHotel = hotelDao.updateHotel(hotelBean);
		
		if(updateHotel.equals("Hotel updated successfully!")) {
			request.setAttribute("successMessage", updateHotel);
			request.getRequestDispatcher("/hotels.jsp").forward(request, response);
		} else {
			request.setAttribute("errMessage", updateHotel);
		}
	}

}
