package com.tourguide.dao;

import com.tourguide.bean.LoginBean;
import com.tourguide.bean.UserBean;

public interface IUserDao {

	String registerUser(UserBean userBean);

	String authenticateUser(LoginBean loginBean);

	String updateUser(UserBean userBean);

	String deleteUser(UserBean userBean);

}