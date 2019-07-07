package com.tourguide.dao;

import com.tourguide.bean.DestinationBean;

public interface IDestinationDao {

	String addDestination(DestinationBean destinationBean);

	String updateDestination(DestinationBean destinationBean);

	String deleteDestination(DestinationBean destinationBean);

}