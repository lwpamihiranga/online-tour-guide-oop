<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.tourguide.util.DBConnection"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Users</title>
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
	<%
	if(request.getAttribute("errMessage") != null) {
	%>
	<div class="alert alert-danger container" role="alert" id="errMessage">
	<%= 
	request.getAttribute("errMessage") 
	%>
	</div>
	<%
	}
	%>	
	
	<%
	if(request.getAttribute("successMessage") != null) {
	%>
	<div class="alert alert-success container" role="alert" id="successMessage">
	<%= request.getAttribute("successMessage") %>
	</div>
	<%
	}
	%>	
<div class="container">
	<table class="table">
		<thead class="thead-dark">
			<tr>
				<th scope="col">#</th>
				<th scope="col">Username</th>
				<th scope="col">First Name</th>
				<th scope="col">Last Name</th>
				<th scope="col">E mail</th>
			</tr>
		</thead>
		<tbody>
		<%
		Connection con = null;
		PreparedStatement preparedStatement = null;
		int rowNumber = 0;
		try {
			con = DBConnection.createConnection();
			String query = "SELECT * FROM users";
			preparedStatement = con.prepareStatement(query);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
		%>
			<tr>
				<th scope="row"><%= rowNumber++ %></th>
				<td><%= resultSet.getString("username") %></td>
				<td><%= resultSet.getString("firstName") %></td>
				<td><%= resultSet.getString("lastName") %></td>
				<td><%= resultSet.getString("email") %></td>
				<%
				if(!(resultSet.getString("username").equals("admin"))) {
				%>
				<td><a href="UserDeleteServlet?username=<%= resultSet.getString("username") %>&admin=true" class="btn btn-primary">Delete</a></td>
				<%
				}
				%>
			</tr>
		<%
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		%>
		</tbody>
	</table>
</div>
<jsp:include page="/WEB-INF/bootstrap/bootstrap_footer.jsp"></jsp:include>
</body>
</html>