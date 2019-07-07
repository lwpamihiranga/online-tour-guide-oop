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
<title>Update Hotel</title>
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
        <form action="UpdateHotelServlet" method="POST" name="update_hotel_form" enctype="multipart/form-data">
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
            <div class="form-group">
            	<input type="hidden" class="form-control" name="hotelID" value="<%= resultSet.getString("hotelID")%>">
            </div>
            <div class="form-group">
                <label for="hotelName">Hotel Name</label>
                <input type="text" class="form-control" name="hotelName" id="hotelName" placeholder="Hotel Name" value="<%= resultSet.getString("hotelName")%>">
            </div>
            <div class="form-group">
                <label for="location">Location</label>
                <input type="text" class="form-control" name="location" id="location" placeholder="Location" value="<%= resultSet.getString("hotelLocation")%>">
            </div>
            <div class="form-group">
                <label for="rating">Rating</label>
                <% int rating = Integer.parseInt(resultSet.getString("hotelRating")); %>
                <select class="form-control" name="rating" id="rating">
                    <option <% if(rating == 1) { out.write("selected"); } %>>1</option>
                    <option <% if(rating == 2) { out.write("selected"); } %>>2</option>
                    <option <% if(rating == 3) { out.write("selected"); } %>>3</option>
                    <option <% if(rating == 4) { out.write("selected"); } %>>4</option>
                    <option <% if(rating == 5) { out.write("selected"); } %>>5</option>
                  </select>
            </div>
            <div class="form-group">
                <label for="description">Description</label>
                <textarea class="form-control" name="description" id="description" rows="5" placeholder="Add hotel description"><%= resultSet.getString("hotelDescription")%></textarea>
            </div>
            <div class="form-group">
                <label for="image">Image</label>
                <input type="file" name="image" class="form-control-file" id="image">
            </div>
            		 <%
			}
			
			preparedStatement.close();
            con.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
        %>
            <button type="submit" class="btn btn-primary">Update Hotel</button>
        </form>
    </div>

<jsp:include page="/WEB-INF/bootstrap/bootstrap_footer.jsp"></jsp:include>
</body>
</html>