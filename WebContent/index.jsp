<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Online Tour Guide</title>
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
    
    <div style="z-index: -1;
                width: 100%;
                height: 100vh;
                background: url(https://images.pexels.com/photos/531880/pexels-photo-531880.jpeg?cs=srgb&dl=background-blur-clean-531880.jpg&fm=jpg) top center;
                background-size: cover;
                position: relative; position: absolute;
                bottom: 0;
                top: 0;
                left: 0;
                right: 0;
                display: flex;
                justify-content: center;
                align-items: center;
                flex-direction: column;
  				text-align: center;">
        <h1>Welcome to Online Tour Guide</h1>
        <h2>The best place to plan and explore all about touring</h2>
      </div>
    
<jsp:include page="/WEB-INF/bootstrap/bootstrap_footer.jsp"></jsp:include>
</body>
</html>