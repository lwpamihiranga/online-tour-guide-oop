package com.tourguide.dao;

import com.tourguide.bean.RestaurantBean;

public interface IRestaurantDao {

	String addRestaurant(RestaurantBean restaurantBean);

	String updateRestaurant(RestaurantBean restaurantBean);

	String deleteRestaurant(RestaurantBean restaurantBean);

}