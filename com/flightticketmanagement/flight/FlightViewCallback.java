package com.flightticketmanagement.flight;

import java.util.ArrayList;

import com.flightticketmanagement.dto.Passenger;
import com.flightticketmanagement.dto.Ticket;
import com.flightticketmanagement.dto.User;

public interface FlightViewCallback {

	void fligthAddeSucces(String flightId, User user);

}
