package com.flightticketmanagement.user;

import com.flightticketmanagement.dto.User;

public interface UserControllerCallback {

	void createuser(String name, String email, byte age, long mobileNo, String password);

}
