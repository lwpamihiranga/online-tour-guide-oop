package com.tourguide.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.tourguide.bean.DestinationBean;
import com.tourguide.util.DBConnection;

public class DestinationDao implements IDestinationDao {
	
	/* (non-Javadoc)
	 * @see com.tourguide.dao.IDestinationDao#addDestination(com.tourguide.bean.DestinationBean)
	 */
	@Override
	public String addDestination(DestinationBean destinationBean) {
		String destinationName = destinationBean.getDestinationName();
		int rating = destinationBean.getRating();
		String description = destinationBean.getDescription();
		InputStream inputStream = destinationBean.getInputStream();
		
		Connection con = null;
		PreparedStatement preparedStatement = null;
		
		try {
			con = DBConnection.createConnection();
			String query = "INSERT INTO destinations(destinationName, destinationRating, destinationDescription, destinationImage) VALUES(?, ?, ?, ?)";
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, destinationName);
			preparedStatement.setInt(2, rating);
			preparedStatement.setString(3, description);
			
			if(inputStream != null) {
				preparedStatement.setBlob(4, inputStream);
			}
			
			int i = preparedStatement.executeUpdate();
			
			if(i != 0) {
				return "Destination added successfully!";
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "Something went wrong!";
	}

	/* (non-Javadoc)
	 * @see com.tourguide.dao.IDestinationDao#updateDestination(com.tourguide.bean.DestinationBean)
	 */
	@Override
	public String updateDestination(DestinationBean destinationBean) {
		int destinationID = destinationBean.getDestinationID();
		String destinationName = destinationBean.getDestinationName();
		int rating = destinationBean.getRating();
		String description = destinationBean.getDescription();
		InputStream inputStream = destinationBean.getInputStream();
		
		Connection con = null;
		PreparedStatement preparedStatement = null;
		
		try {
			con = DBConnection.createConnection();
			String query = "UPDATE destinations SET destinationName=?, destinationRating=?, destinationDescription=?, destinationImage=? WHERE destinationID=?";
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, destinationName);
			preparedStatement.setInt(2, rating);
			preparedStatement.setString(3, description);
			
			if(inputStream != null) {
				preparedStatement.setBlob(4, inputStream);
			}
			
			preparedStatement.setInt(5, destinationID);
			
			int i = preparedStatement.executeUpdate();
			
			if(i != 0) {
				return "Destination updated successfully!";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "Something went wrong!";
	}	
	
	/* (non-Javadoc)
	 * @see com.tourguide.dao.IDestinationDao#deleteDestination(com.tourguide.bean.DestinationBean)
	 */
	@Override
	public String deleteDestination(DestinationBean destinationBean) {
		int destinationID = destinationBean.getDestinationID();
		
		Connection con = null;
		PreparedStatement preparedStatement = null;
		
		try {
			con = DBConnection.createConnection();
			String query = "DELETE FROM destinations WHERE destinationID=?";
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1, destinationID);
			
			int i = preparedStatement.executeUpdate();
			
			if(i != 0) {
				return "Destination deleted succcessfully!";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "Something went wrong!";
	}	
}
