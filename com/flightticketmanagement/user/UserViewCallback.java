package com.flightticketmanagement.user;

import java.util.ArrayList;

import com.flightticketmanagement.dto.Passenger;
import com.flightticketmanagement.dto.Ticket;
import com.flightticketmanagement.dto.User;

public interface UserViewCallback {

	void userAddedSuccess(String name,User user);

	void userAddFailed(String name);
	void viewUsers(ArrayList<Passenger> passengers);

}
