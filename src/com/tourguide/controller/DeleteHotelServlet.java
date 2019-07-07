package com.tourguide.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tourguide.bean.HotelBean;
import com.tourguide.dao.HotelDao;
import com.tourguide.dao.IHotelDao;

/**
 * Servlet implementation class DeleteHotelServlet
 */
@WebServlet("/DeleteHotelServlet")
public class DeleteHotelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteHotelServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		int hotelID = Integer.parseInt(request.getParameter("hotelID"));
		
		HotelBean hotelBean = new HotelBean();
		
		hotelBean.setHotelID(hotelID);
		
		IHotelDao hotelDao = new HotelDao();
		
		String deleteHotel = hotelDao.deleteHotel(hotelBean);
		
		if(deleteHotel.equals("Hotel deleted successfully!")) {
			request.setAttribute("successMessage", deleteHotel);
			request.getRequestDispatcher("/hotels.jsp").forward(request, response);
		} else {
			request.setAttribute("errMessage", deleteHotel);
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
