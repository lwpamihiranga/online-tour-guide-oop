<%@page import="java.sql.SQLException"%>
<%@page import="com.tourguide.util.DBConnection"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Display Hotel</title>
<jsp:include page="/WEB-INF/bootstrap/bootstrap_header.jsp"></jsp:include>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <ul class="navbar-nav mr-auto">
        <li class="nav-item active">
            <a class="nav-link" href="index.jsp">Home</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="hotels.jsp">Hotels</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="destinations.jsp">Destinations</a>
        </li>
        <li class="nav-item">
        	<a class="nav-link" href="restaurants.jsp">Restaurants</a>
        </li>
        <%
        if(!(session.getAttribute("login") == null || session.getAttribute("login") == "")) {
        	if(session.getAttribute("login").equals("admin")) {
        %>
        <li class="nav-item">
        	<a class="nav-link" href="view_users.jsp">View Users</a>
        </li>
        <%
        	}
        }
        %>
    </ul>
    <ul class="navbar-nav justify-content-end">
    <%
    if(session.getAttribute("login") == null || session.getAttribute("login") == "") {
    %>
        <li>
            <a class="nav-link active" href="user_login.jsp">Login</a>
        </li>
        <li>
            <a class="nav-link active" href="user_register.jsp">Register</a>
        </li>
     <%
     } else {
      %>
      	<li class="nav-link active">
      		You are Logged In as <%= session.getAttribute("login") %>
      	</li>
      	<%
        if(!(session.getAttribute("login") == null || session.getAttribute("login") == "")) {
        	if(!(session.getAttribute("login").equals("admin"))) {
        %>
      	<li>
      		<a href="user_profile.jsp?username=<%= session.getAttribute("login") %>" class="nav-link active">My Profile</a>
      	</li>
        <%
        	}
        }
        %>      	
      	<li>
            <a class="nav-link active btn btn-secondary" href="logout.jsp">Logout</a>
        </li>
      <%
     }
      %>	
    </ul>
</nav>
	<div class="container">
	<%
	int hotelID = Integer.parseInt(request.getParameter("hotelID"));
   	Connection con = null;
	PreparedStatement preparedStatement = null;
		
	try {
		con = DBConnection.createConnection();
		String query = "SELECT * FROM hotels WHERE hotelID=?";
		preparedStatement = con.prepareStatement(query);
		preparedStatement.setInt(1, hotelID);
			
		ResultSet resultSet = preparedStatement.executeQuery();
			
		while(resultSet.next()) {	
    %> 
 		<div class="card">
            
                <img src="getImageHotel.jsp?hotelID=<%= resultSet.getInt("hotelID") %>" class="card-img-top" alt="">
                <div class="card-body">
                    <h5 class="card-title"><%= resultSet.getString("hotelName")%></h5>
                    <p class="card-text">Location: <%= resultSet.getString("hotelLocation")%></p>
                    <p class="card-text">Rating: <%= resultSet.getString("hotelRating")%></p>
                    <p class="card-text"><%= resultSet.getString("hotelDescription")%></p>
                   	<%
                    if(session.getAttribute("login").equals("admin")) {
                    %>
                    <a href="update_hotel.jsp?hotelID=<%= resultSet.getInt("hotelID") %>">Edit</a>
                    <a href="DeleteHotelServlet?hotelID=<%= resultSet.getInt("hotelID") %>">Delete</a>
                    <%
                    }
                    %>
                </div>
            </div>
    <%
		}
			
		preparedStatement.close();
        con.close();
	} catch(SQLException e) {
		e.printStackTrace();
	}
    %>
    </div>
<jsp:include page="/WEB-INF/bootstrap/bootstrap_footer.jsp"></jsp:include>
</body>
</html>