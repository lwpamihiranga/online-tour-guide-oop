package com.tourguide.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.tourguide.bean.LoginBean;
import com.tourguide.bean.UserBean;
import com.tourguide.util.DBConnection;

public class UserDao implements IUserDao {

	/* (non-Javadoc)
	 * @see com.tourguide.dao.IUserDao#registerUser(com.tourguide.bean.UserBean)
	 */
	@Override
	public String registerUser(UserBean userBean) {
		String firstName = userBean.getFirstName();
		String lastName = userBean.getLastName();
		String email = userBean.getEmail();
		String username = userBean.getUsername();
		String password = userBean.getPassword();
		InputStream inputStream = userBean.getInputStream();
		
		Connection con = null;
		PreparedStatement preparedStatement = null;
		
		try {
			con = DBConnection.createConnection();
			String query = "INSERT INTO users(username, firstName, lastName, email, password, profilePicture) VALUES(?, ?, ?, ?, ?, ?)";
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, firstName);
			preparedStatement.setString(3, lastName);
			preparedStatement.setString(4, email);
			preparedStatement.setString(5, password);
			
			if(inputStream != null) {
				preparedStatement.setBlob(6,inputStream);
			}
			
			int i = preparedStatement.executeUpdate();
			
			if(i != 0)
				return "Registered successfully!";
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "Something went wrong!";
	}
	
	/* (non-Javadoc)
	 * @see com.tourguide.dao.IUserDao#authenticateUser(com.tourguide.bean.LoginBean)
	 */
	@Override
	public String authenticateUser(LoginBean loginBean) {
		String username = loginBean.getUsername();
		String password = loginBean.getPassword();
		
		Connection con = null;
		Statement statement = null;
		ResultSet  resultSet = null;
		
		String dbUsername = "";
		String dbPassword = "";
		
		try {
			con = DBConnection.createConnection();
			statement = con.createStatement();
			resultSet = statement.executeQuery("SELECT username, password FROM users");
			
			while(resultSet.next()) {
				dbUsername = resultSet.getString("username");
				dbPassword = resultSet.getString("password");
				
				if(username.equals(dbUsername) && password.equals(dbPassword)) {
					return "SUCCESS LOGIN";
				}
			}
			
			statement.close();
			con.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return "Wrong username or password!";
	}
	
	/* (non-Javadoc)
	 * @see com.tourguide.dao.IUserDao#updateUser(com.tourguide.bean.UserBean)
	 */
	@Override
	public String updateUser(UserBean userBean) {
		String firstName = userBean.getFirstName();
		String lastName = userBean.getLastName();
		String email = userBean.getEmail();
		String username = userBean.getUsername();
		String password = userBean.getPassword();
		InputStream inputStream = userBean.getInputStream();
		
		Connection con = null;
		PreparedStatement preparedStatement = null;
		
		try {
			con = DBConnection.createConnection();
			String query = "UPDATE users SET firstName=?, lastName=?, email=?, password=?, profilePicture=? WHERE username=?";
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, firstName);
			preparedStatement.setString(2, lastName);
			preparedStatement.setString(3, email);
			preparedStatement.setString(4, password);
			preparedStatement.setBlob(5, inputStream);
			preparedStatement.setString(6, username);
			
			int i = preparedStatement.executeUpdate();
			
			if(i != 0)
				return "User profile updated successfully!";
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "Something went wrong!";
	}
	
	/* (non-Javadoc)
	 * @see com.tourguide.dao.IUserDao#deleteUser(com.tourguide.bean.UserBean)
	 */
	@Override
	public String deleteUser(UserBean userBean) {
		String username = userBean.getUsername();
		
		Connection con = null;
		PreparedStatement preparedStatement = null;
		
		try {
			con = DBConnection.createConnection();
			String query = "DELETE FROM users WHERE username=?";
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, username);
			
			int i = preparedStatement.executeUpdate();
			
			if(i != 0) {
				return "User deleted successfully!";
			} 
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return "Something went wrong!";
	}
}
