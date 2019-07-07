package com.tourguide.dao;

import com.tourguide.bean.HotelBean;

public interface IHotelDao {

	String addHotel(HotelBean hotelBean);

	String updateHotel(HotelBean hotelBean);

	String deleteHotel(HotelBean hotelBean);

}