package com.flightticketmanagement.ticket;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;

import com.flightticketmanagement.dto.Flight;
import com.flightticketmanagement.dto.Passenger;
import com.flightticketmanagement.dto.Ticket;
import com.flightticketmanagement.dto.User;

public interface TicketControllerCallback {

	void bookticket(User user, String from, String to, String date, byte ticketsCoutn, ArrayList<Passenger> passengers,Flight flight);

	void cancelticket(String ticketId, User user);

	Flight findFlight(LocalDate entered, String from, String to, boolean istatkal);

}
