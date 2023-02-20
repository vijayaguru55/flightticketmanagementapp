package com.flightticketmanagement.user;

import java.util.ArrayList;
import java.util.Scanner;

import com.flightticketmanagement.dto.Passenger;
import com.flightticketmanagement.dto.User;

import com.flightticketmanagement.login.LoginView;

public class UserView implements UserViewCallback {
	Scanner scanner = new Scanner(System.in);
	UserControllerCallback userController;

	public UserView() {
		this.userController = new UserController(this);
	}

	public void create() {
		createUser();
	}
	
	private void createUser() {
		System.out.println("Enter Name");
		String name = scanner.nextLine();
		System.out.println("Enter emailId");
		String email = scanner.nextLine();
		System.out.println("Enter Age");
		byte age = scanner.nextByte();
		System.out.println("Enter mobile Number");
		long mobileNo = scanner.nextLong();
		System.out.println("Set password");
		String password = scanner.next();
		userController.createuser(name, email, age, mobileNo, password);
	}

	@Override
	public void userAddedSuccess(String name, User user) {
		System.out.println("User added SuccessFully \n UserName :" + name);
		LoginView loginView = new LoginView();
		loginView.loginSuccess(user, false);
	}

	public void userAddFailed(String name) {
		System.out.println("User named " + name + " aready exist");
		createUser();  
	}

	@Override
	public void viewUsers(ArrayList<Passenger> passengers) {
		for (Passenger passenger : passengers) {
			viewUser(passenger);
			System.out.println("---------------------------------------");
		}
	}

	private void viewUser(Passenger passenger) {
		System.out.println("Name :" + passenger.getName() + " " + passenger.getAge() + " Years old "
				+ passenger.getGender());
	}

}
