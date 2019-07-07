package com.tourguide.bean;

import java.io.InputStream;

public class DestinationBean {
	private int destinationID;
	private String destinationName;
	private int rating;
	private String description;
	private InputStream inputStream;
	/**
	 * @return the destinationID
	 */
	public int getDestinationID() {
		return destinationID;
	}
	/**
	 * @param destinationID the destinationID to set
	 */
	public void setDestinationID(int destinationID) {
		this.destinationID = destinationID;
	}
	/**
	 * @return the destinationName
	 */
	public String getDestinationName() {
		return destinationName;
	}
	/**
	 * @param destinationName the destinationName to set
	 */
	public void setDestinationName(String destinationName) {
		this.destinationName = destinationName;
	}
	/**
	 * @return the rating
	 */
	public int getRating() {
		return rating;
	}
	/**
	 * @param rating the rating to set
	 */
	public void setRating(int rating) {
		this.rating = rating;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the inputStream
	 */
	public InputStream getInputStream() {
		return inputStream;
	}
	/**
	 * @param inputStream the inputStream to set
	 */
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

}
