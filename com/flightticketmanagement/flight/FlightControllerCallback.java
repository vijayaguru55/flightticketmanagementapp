package com.flightticketmanagement.flight;

import com.flightticketmanagement.dto.Flight;
import com.flightticketmanagement.dto.User;

public interface FlightControllerCallback {

	void addFlight(String companyName, String fromLocation, String destination, int price, User user);


}
