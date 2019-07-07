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

import com.tourguide.bean.DestinationBean;
import com.tourguide.dao.DestinationDao;
import com.tourguide.dao.IDestinationDao;

/**
 * Servlet implementation class AddDestinationServlet
 */
@WebServlet("/AddDestinationServlet")
@MultipartConfig(maxFileSize = 16177215)
public class AddDestinationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddDestinationServlet() {
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
		
		String destinationName = request.getParameter("destinationName");
		int rating = Integer.parseInt(request.getParameter("rating"));
		String description = request.getParameter("description");
		Part filePart = request.getPart("image");
		
		InputStream inputStream = null;
		
		if(filePart != null) {
			inputStream = filePart.getInputStream();
		}
		
		DestinationBean destinationBean = new DestinationBean();
		
		destinationBean.setDestinationName(destinationName);
		destinationBean.setRating(rating);
		destinationBean.setDescription(description);
		destinationBean.setInputStream(inputStream);
		
		IDestinationDao destinationDao = new DestinationDao();
		
		String addDestination = destinationDao.addDestination(destinationBean);
		
		if(addDestination.equals("Destination added successfully!")) {
			request.setAttribute("successMessage", addDestination);
			request.getRequestDispatcher("/destinations.jsp").forward(request, response);
		} else {
			request.setAttribute("errMessage", addDestination);
		}
	}

}
