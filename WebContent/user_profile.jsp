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
<title>User Profile Page</title>
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
<h2 style="width: 17%; margin-left: auto; margin-right: auto;">User Profile Page</h2>
<div class="container">
<%
String username = request.getParameter("username");
Connection con = null;
PreparedStatement preparedStatement = null;

try {
	con = DBConnection.createConnection();
	String query = "SELECT * FROM users WHERE username=?";
	preparedStatement = con.prepareStatement(query);
	preparedStatement.setString(1, username);
	
	ResultSet resultSet = preparedStatement.executeQuery();
	
	while(resultSet.next()) {
%>
	<div class="card" style="width: 40%; margin-left: auto; margin-right: auto;">
		<img src="getImageUser.jsp?username=<%= resultSet.getString("username") %>" class="card-img-top img-thumbnail" >
		<div class="card-body">
			<p class="card-text">Username	: <%= resultSet.getString("username") %></p>
			<p class="card-text">First Name	: <%= resultSet.getString("firstName") %></p>		
			<p class="card-text">Last Name	: <%= resultSet.getString("lastName") %></p>		
			<p class="card-text">email		: <%= resultSet.getString("email") %></p>
			<a href="update_user.jsp?username=<%= resultSet.getString("username") %>">Edit</a>
			<a href="UserDeleteServlet?username=<%= resultSet.getString("username") %>">Delete</a>		
		</div>
	</div>	
<%		
	}
} catch (SQLException e) {
	e.printStackTrace();
} finally {
	preparedStatement.close();
	con.close();
}
%>	
</div>

<jsp:include page="/WEB-INF/bootstrap/bootstrap_footer.jsp"></jsp:include>
</body>
</html>