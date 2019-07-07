package com.tourguide.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.tourguide.bean.RestaurantBean;
import com.tourguide.util.DBConnection;

public class RestaurantDao implements IRestaurantDao {
	/* (non-Javadoc)
	 * @see com.tourguide.dao.IRestaurantDao#addRestaurant(com.tourguide.bean.RestaurantBean)
	 */
	@Override
	public String addRestaurant(RestaurantBean restaurantBean) {
		String restaurantName = restaurantBean.getRestaurantName();
		String location = restaurantBean.getLocation();
		String cuisineType = restaurantBean.getCuisineType();
		int rating = restaurantBean.getRating();
		String description = restaurantBean.getDescription();
		InputStream inputStream = restaurantBean.getInputStream();
		
		Connection con = null;
		PreparedStatement preparedStatement = null;
		
		try {
			con = DBConnection.createConnection();
			String query = "INSERT INTO restaurants(restaurantName, restaurantLocation, cuisineType, restaurantRating, restaurantDescription, restaurantImage) VALUES(?, ?, ?, ?, ?, ?)";
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, restaurantName);
			preparedStatement.setString(2, location);
			preparedStatement.setString(3, cuisineType);
			preparedStatement.setInt(4, rating);
			preparedStatement.setString(5, description);
			
			if(inputStream != null) {
				preparedStatement.setBlob(6, inputStream);
			}
			
			int i = preparedStatement.executeUpdate();
			
			if(i != 0) {
				return "Restaurant added successfully!";
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "Something went wrong!";
	}
	
	/* (non-Javadoc)
	 * @see com.tourguide.dao.IRestaurantDao#updateRestaurant(com.tourguide.bean.RestaurantBean)
	 */
	@Override
	public String updateRestaurant(RestaurantBean restaurantBean) {
		int restaurantID = restaurantBean.getRestaurantID();
		String restaurantName = restaurantBean.getRestaurantName();
		String location = restaurantBean.getLocation();
		String cuisineType = restaurantBean.getCuisineType();
		int rating = restaurantBean.getRating();
		String description = restaurantBean.getDescription();
		InputStream inputStream = restaurantBean.getInputStream();
		
		Connection con = null;
		PreparedStatement preparedStatement = null;
		
		try {
			con = DBConnection.createConnection();
			String query = "UPDATE restaurants SET restaurantName=?, restaurantLocation=?, cuisineType=?, restaurantRating=?, restaurantDescription=?, restaurantImage=? WHERE restaurantID=?";
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, restaurantName);
			preparedStatement.setString(2, location);
			preparedStatement.setString(3, cuisineType);
			preparedStatement.setInt(4, rating);
			preparedStatement.setString(5, description);
			
			if(inputStream != null) {
				preparedStatement.setBlob(6, inputStream);
			}
			
			preparedStatement.setInt(7, restaurantID);
			
			int i = preparedStatement.executeUpdate();
			
			if(i != 0) {
				return "Restaurant updated successfully!";
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "Something went wrong!";
	}
	
	/* (non-Javadoc)
	 * @see com.tourguide.dao.IRestaurantDao#deleteRestaurant(com.tourguide.bean.RestaurantBean)
	 */
	@Override
	public String deleteRestaurant(RestaurantBean restaurantBean) {
		int restaurantID = restaurantBean.getRestaurantID();
		
		Connection con = null;
		PreparedStatement preparedStatement = null;
		
		try {
			con = DBConnection.createConnection();
			String query = "DELETE FROM restaurants WHERE restaurantID=?";
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1, restaurantID);
			
			int i = preparedStatement.executeUpdate();
			
			if(i != 0) {
				return "Restaurant deleted successfully!";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "Something went wrong!";			
	}
}
