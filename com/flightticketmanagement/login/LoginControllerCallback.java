package com.flightticketmanagement.login;

public interface LoginControllerCallback {

	void checkForLogin(String name, String password, boolean isAdmin);

}
