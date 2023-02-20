package com.flightticketmanagement.user;

import com.flightticketmanagement.dto.User;
import com.flightticketmanagement.user.UserModel.UserModelControllerCallback;


public class UserController implements UserControllerCallback,UserModelControllerCallback {
	private UserViewCallback userView;
	private userModelCallback model;

	public UserController(UserViewCallback userView) {
		this.userView = userView;
		this.model = new UserModel(this);
	}

	public void createuser(String name, String email, byte age, long mobileNo, String password) {
		model.createUser(name, email, age, mobileNo, password);
	}

	@Override
	public void userAddedSuccess(String name, User user) {
		userView.userAddedSuccess(name, user);
	}

	@Override
	public void userAddFailed(String name) {
		userView.userAddFailed(name);

	}

	


}
