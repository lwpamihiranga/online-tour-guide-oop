package com.tourguide.bean;

import java.io.InputStream;

public class RestaurantBean {
	private int restaurantID;
	private String restaurantName;
	private String location;
	private String cuisineType;
	private int rating;
	private String description;
	private InputStream inputStream;
	/**
	 * @return the restaurantID
	 */
	public int getRestaurantID() {
		return restaurantID;
	}
	/**
	 * @param restaurantID the restaurantID to set
	 */
	public void setRestaurantID(int restaurantID) {
		this.restaurantID = restaurantID;
	}
	/**
	 * @return the restaurantName
	 */
	public String getRestaurantName() {
		return restaurantName;
	}
	/**
	 * @param restaurantName the restaurantName to set
	 */
	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}
	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}
	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	/**
	 * @return the cuisineType
	 */
	public String getCuisineType() {
		return cuisineType;
	}
	/**
	 * @param cuisineType the cuisineType to set
	 */
	public void setCuisineType(String cuisineType) {
		this.cuisineType = cuisineType;
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
