package com.flightticketmanagement.ticket;

import java.util.ArrayList;

import com.flightticketmanagement.dto.Flight;
import com.flightticketmanagement.dto.Ticket;
import com.flightticketmanagement.dto.User;

public interface TicketViewCallaback {

	void bookingSucces(ArrayList<Ticket> tickets,User user);

	void bookingFailed(User user);

	void cancellationSuccess(User user);

	void cancellationFailed(String message,User user);

	boolean isPaymentDone();

	void paymentFailed(User user);

	Flight chooseFlight(ArrayList<Flight> flightsAvail,boolean isTatkal);

}
