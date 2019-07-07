package com.tourguide.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.tourguide.bean.HotelBean;
import com.tourguide.util.DBConnection;

public class HotelDao implements IHotelDao {
	
	/* (non-Javadoc)
	 * @see com.tourguide.dao.IHotelDao#addHotel(com.tourguide.bean.HotelBean)
	 */
	@Override
	public String addHotel(HotelBean hotelBean) {
		String hotelName = hotelBean.getHotelName(); 
		String location = hotelBean.getLocation();
		int rating = hotelBean.getRating();
		String description = hotelBean.getDescription();
		InputStream inputStream = hotelBean.getInputStream();
		
		Connection con = null;
		PreparedStatement preparedStatement = null;
		
		try {
			con = DBConnection.createConnection();
			String query = "INSERT INTO hotels(hotelName, hotelLocation, hotelRating, hotelDescription, hotelImage) VALUES(?, ?, ?, ?, ?)";
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, hotelName);
			preparedStatement.setString(2, location);
			preparedStatement.setInt(3, rating);
			preparedStatement.setString(4, description);
			
			if(inputStream != null) {
				preparedStatement.setBlob(5, inputStream);
			}
			
			int i = preparedStatement.executeUpdate();
			
			if(i != 0) {
				return "Hotel added successfully!";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "Something went wrong when adding hotel!";
	}
	
	/* (non-Javadoc)
	 * @see com.tourguide.dao.IHotelDao#updateHotel(com.tourguide.bean.HotelBean)
	 */
	@Override
	public String updateHotel(HotelBean hotelBean) {
		int hotelID = hotelBean.getHotelID();
		String hotelName = hotelBean.getHotelName(); 
		String location = hotelBean.getLocation();
		int rating = hotelBean.getRating();
		String description = hotelBean.getDescription();
		InputStream inputStream = hotelBean.getInputStream();
		
		Connection con = null;
		PreparedStatement preparedStatement = null;
		
		try {
			con = DBConnection.createConnection();
			String query = "UPDATE hotels SET hotelName=?, hotelLocation=?, hotelRating=?, hotelDescription=?, hotelImage=? WHERE hotelID=?";
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, hotelName);
			preparedStatement.setString(2, location);
			preparedStatement.setInt(3, rating);
			preparedStatement.setString(4, description);
			
			if(inputStream != null) {
				preparedStatement.setBlob(5, inputStream);
			}
			
			preparedStatement.setInt(6, hotelID);
			
			int i = preparedStatement.executeUpdate();
			
			if(i != 0) {
				return "Hotel updated successfully!";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "Something went wrong!";
	}
	
	/* (non-Javadoc)
	 * @see com.tourguide.dao.IHotelDao#deleteHotel(com.tourguide.bean.HotelBean)
	 */
	@Override
	public String deleteHotel(HotelBean hotelBean) {
		int hotelID = hotelBean.getHotelID();
		
		Connection con = null;
		PreparedStatement preparedStatement = null;
		
		try {
			con = DBConnection.createConnection();
			String query = "DELETE FROM hotels WHERE hotelID=?";
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1, hotelID);

			int i = preparedStatement.executeUpdate();
			
			if(i != 0) {
				return "Hotel deleted successfully!";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "Something went wrong!";
	}
}
