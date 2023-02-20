package com.flightticketmanagement.login;

public interface LoginModelCalback {
	void checkForLogin(String name, String password, boolean isAdmin);
}
