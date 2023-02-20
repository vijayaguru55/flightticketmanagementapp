package com.flightticketmanagement.login;

import com.flightticketmanagement.dto.Admin;
import com.flightticketmanagement.dto.User;

import com.flightticketmanagement.repository.*;

public class LoginModel implements LoginModelCalback {
	private LoginModelControllerCallBack loginController;

	public LoginModel(LoginModelControllerCallBack loginController) {
		this.loginController = loginController;
	}

	@Override
	public void checkForLogin(String name, String password, boolean isAdmin) {
		FlightmanagementDatabase database = FlightmanagementDatabase.getInstance();
		if (isAdmin) {
			Admin admin = database.adminCredial(name, password);
			if (admin != null) {
				loginController.loginSuccess((User) admin,true);
			} else {
				loginController.loginFailed((User) admin);
			}
		} else {
			User user = database.checkCredentials(name, password);
			if (user != null) {
				loginController.loginSuccess(user,false);
			} else {
				loginController.loginFailed(user);
			}
		}

	}

	interface LoginModelControllerCallBack {
		void loginSuccess(User user, boolean isAdmin);

		void loginFailed(User admin);
	}

}
