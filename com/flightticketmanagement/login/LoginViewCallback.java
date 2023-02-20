package com.flightticketmanagement.login;

import com.flightticketmanagement.dto.User;

public interface LoginViewCallback {

	void loginSuccess(User user, boolean isAdmin);

	void loginFailed(User user);

}
