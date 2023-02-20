package com.flightticketmanagement.user;

import com.flightticketmanagement.dto.Credential;
import com.flightticketmanagement.dto.User;

import com.flightticketmanagement.repository.FlightmanagementDatabase;

public class UserModel implements userModelCallback {

	private UserModelControllerCallback userController;

	public UserModel(UserModelControllerCallback userController) {
		this.userController = userController;
	}
	private User createNewUser(String name, String email, byte age, long mobileNo, String password) {
		User user = new User();
		user.setEmailId(email);
		user.setAge(age);
		user.setMobuleNumber(mobileNo);
		user.setPossword(password);
		user.setName(name);
		user.setUserId(String.valueOf(mobileNo / age));
		return user;
	}
	public void createUser(String name, String email, byte age, long mobileNo, String password) {
		FlightmanagementDatabase database = FlightmanagementDatabase.getInstance();
		if (database.isExistUser(name,password)) {
			userController.userAddFailed(name);
		} else {
			
			User user = createNewUser(name, email, age, mobileNo, password);
			database.addUser(user);
			Credential credential = new Credential();
			credential.setName(name);
			credential.setPassword(password);
			database.updateCredential(credential);
			userController.userAddedSuccess(name, user);
		}
	}

	interface UserModelControllerCallback {
		void userAddedSuccess(String name, User user);

		void userAddFailed(String name);

	}
}
