package com.tourguide.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tourguide.bean.DestinationBean;
import com.tourguide.dao.DestinationDao;
import com.tourguide.dao.IDestinationDao;

/**
 * Servlet implementation class DeleteDestinationServlet
 */
@WebServlet("/DeleteDestinationServlet")
public class DeleteDestinationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteDestinationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		int destinationID = Integer.parseInt(request.getParameter("destinationID"));
		
		DestinationBean destinationBean = new DestinationBean();
		
		destinationBean.setDestinationID(destinationID);
		
		IDestinationDao destinationDao = new DestinationDao();
		
		String deleteDestination = destinationDao.deleteDestination(destinationBean);
		
		if(deleteDestination.equals("Destination deleted succcessfully!")) {
			request.setAttribute("successMessage", deleteDestination);
			request.getRequestDispatcher("/destinations.jsp").forward(request, response);
		} else {
			request.setAttribute("errMessage", deleteDestination);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
