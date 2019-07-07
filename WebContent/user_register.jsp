<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<jsp:include page="/WEB-INF/bootstrap/bootstrap_header.jsp"></jsp:include>
<title>User Registration Page</title>
<script>
 	function validate() {
 		var firstName = document.registration_form.firstName.value;
    var lastName = document.registration_form.lastName.value;
    var email = document.registration_form.email.value;
    var username = document.registration_form.username.value;
    var password = document.registration_form.password.value;
    var confirmPassword = document.registration_form.confirmPassword.value;
 		
 		if(firstName == null || firstName == "") {
      alert("Firstname can't be empty!");
 		  return false;
 		} else if(lastName == null || lastName == "") {
      alert("Lastname can't be empty!");
 		  return false;
    } else if(email == null || email == "") {
      alert("Email can't be empty!");
      return false;
    } else if(username == null || username == "") {
      alert("Username can't be empty!");
      return false;
    } else if(password == null || password == "") {
      alert("Password can't be empty!");
      return false;
    } else if(confirmPassword == null || confirmPassword == "") {
      alert("Confirm Password can't be empty!");
      return false;
    } else if(password !== confirmPassword) {
      alert("Confirm Password should match the Password!");
      return false;
    }
 	}
</script>
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
  <form action="UserRegisterServlet" method="POST" class="container" name="registration_form" enctype="multipart/form-data" onsubmit="return validate()">
    <div class="form-group">
      <label for="fnameInput">First Name</label>
      <input type="text" class="form-control" name="firstName" id="fnameInput" placeholder="First Name">
    </div>
    <div class="form-group">
      <label for="lnameInput">Last Name</label>
      <input type="text" class="form-control" name="lastName" id="lnameInput" placeholder="Last Name">
    </div>
    <div class="form-group">
      <label for="email">Email address</label>
      <input type="email" class="form-control" name="email" id="email" placeholder="email">
    </div>
    <div class="form-group">
      <label for="username">Username</label>
      <input type="text" class="form-control" name="username" id="username" placeholder="username">
    </div>    
    <div class="form-group">
      <label for="password">Password</label>
      <input type="password" class="form-control" name="password" id="password" placeholder="Password">
    </div>
    <div class="form-group">
        <label for="confirmPassword">Confirm Password</label>
        <input type="password" class="form-control" name="confirmPassword" id="confirmPassword" placeholder="Confirm Password">
    </div>
    <div class="form-group">
        <label for="image">Profile Picture</label>
     	<input type="file" name="image" class="form-control-file" id="image">
     </div>
    <button type="submit" class="btn btn-primary">Register</button> 
  </form>
</div>

<jsp:include page="/WEB-INF/bootstrap/bootstrap_footer.jsp"></jsp:include>
</body>
</html>