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
<title>Update User</title>
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
  <form action="UserUpdateServlet" method="POST" class="container" name="update_form" enctype="multipart/form-data" onsubmit="return validate()">
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
    <div class="form-group">
      <label for="fnameInput">First Name</label>
      <input type="text" class="form-control" name="firstName" id="fnameInput" placeholder="First Name" value="<%= resultSet.getString("firstName") %>">
    </div>
    <div class="form-group">
      <label for="lnameInput">Last Name</label>
      <input type="text" class="form-control" name="lastName" id="lnameInput" placeholder="Last Name" value="<%= resultSet.getString("lastName") %>">
    </div>
    <div class="form-group">
      <label for="email">Email address</label>
      <input type="email" class="form-control" name="email" id="email" placeholder="email" value="<%= resultSet.getString("email") %>">
    </div>
    <div class="form-group">
      <label for="username">Username</label>
      <input type="text" class="form-control" name="username" id="username" placeholder="username" value="<%= resultSet.getString("username") %>" readonly>
    </div>    
    <div class="form-group">
      <label for="password">Password</label>
      <input type="password" class="form-control" name="password" id="password" placeholder="Password" value="<%= resultSet.getString("password") %>">
    </div>
    <div class="form-group">
        <label for="confirmPassword">Confirm Password</label>
        <input type="password" class="form-control" name="confirmPassword" id="confirmPassword" placeholder="Confirm Password" value="<%= resultSet.getString("password") %>">
    </div>
    <div class="form-group">
        <label for="image">Profile Picture</label>
     	<input type="file" name="image" class="form-control-file" id="image">
     </div>
    	<%	  
	  }
  } catch (SQLException e) {
	  e.printStackTrace();
  }
  	%>
    <button type="submit" class="btn btn-primary">Update User</button> 
  </form>
</div>

<jsp:include page="/WEB-INF/bootstrap/bootstrap_footer.jsp"></jsp:include>
</body>
</html>