<%@page import="java.io.OutputStream"%>
<%@page import="java.sql.Blob"%>
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
<title>Get User Image</title>
</head>
<body>
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
			Blob blob = resultSet.getBlob("profilePicture");
			byte byteArray[] = blob.getBytes(1, (int) blob.length());
			response.setContentType("image/gif");
			OutputStream os = response.getOutputStream();
			os.write(byteArray);
			os.flush();
			os.close();
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
%>
</body>
</html>