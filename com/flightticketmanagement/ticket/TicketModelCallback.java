package com.flightticketmanagement.ticket;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;

import com.flightticketmanagement.dto.Flight;
import com.flightticketmanagement.dto.Passenger;
import com.flightticketmanagement.dto.User;

public interface TicketModelCallback {

	void bookTicket(User user, String from, String to, String date, byte ticketsCount, ArrayList<Passenger> passengers,Flight flight);

	void cancelTicket(String ticketId, User user);


	Flight findFlight(LocalDate date, String from, String to, boolean isTatkal);


}
