package com.flightticketmanagement.ticket;

import java.sql.DatabaseMetaData;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Map;

import com.flightticketmanagement.dto.Flight;
import com.flightticketmanagement.dto.Passenger;
import com.flightticketmanagement.dto.Ticket;
import com.flightticketmanagement.dto.User;

import com.flightticketmanagement.repository.*;

public class TicketModel implements TicketModelCallback {
	private TicketModelControllerCallback controllerCallback;

	public TicketModel(TicketModelControllerCallback callback) {
		this.controllerCallback = callback;
	}
	
	private ArrayList<Ticket> bookTicket(User user, Flight selectFlight, String from, String to, String date,
			byte ticketsCount, ArrayList<Passenger> passengers) {

		ArrayList<Ticket> tickets = new ArrayList<Ticket>();
		
		
		for (Passenger passenger : passengers) {
			String name = passenger.getName();
			byte age = passenger.getAge();
			String gender = passenger.getGender();
			Ticket ticket = new Ticket();
			ticket.setFlightName(selectFlight.getCompanyName());
			ticket.setFrom(from);
			ticket.setTo(to);
			ticket.setTicketId(name.substring(0, name.length() / 2).toUpperCase()
					+ selectFlight.getCompanyName().substring(0, 5).toUpperCase());
			ticket.setUser(user);
			ticket.setPassengerAge(age);
			ticket.setPassengerName(name);
			ticket.setPassengerGender(gender);
			ticket.setDate(LocalDate.parse(date));
			ticket.setTime(selectFlight.getDeparturetime());
			tickets.add(ticket);
			ticket.setFlightId(selectFlight.getFlightId());
			selectFlight.setAvalableSeats(selectFlight.getAvalableSeats()-ticketsCount);
		}
		return tickets;
	}
	
	
	@Override
	public void bookTicket(User user, String from, String to, String date, byte ticketsCount,
			ArrayList<Passenger> passengers, Flight flight) {
		if(flight.getAvalableSeats()<ticketsCount) {
			controllerCallback.bookingFailed(user);
		}
		
		FlightmanagementDatabase flightmanagementDatabase = FlightmanagementDatabase.getInstance();
		ArrayList<Ticket> tickets = bookTicket(user, flight, from, to, date, ticketsCount, passengers);
		flightmanagementDatabase.updateBooking(user,tickets);
		if (controllerCallback.isPaymentDone())
			controllerCallback.bookingSuccess(tickets, user);
		else {
			controllerCallback.paymentFailed(user);
		}
	}

	@Override
	public void cancelTicket(String ticketId, User user) {
		FlightmanagementDatabase database = FlightmanagementDatabase.getInstance();
		if (database.isBookedTicket(ticketId, user)) {
			database.cancelTicket(ticketId, user);
			controllerCallback.cancellationSuccess(user);
		} else {
			controllerCallback.cancellationFailed("\nInvalid ticketId\n", user);
		}
	}

	@Override
	public Flight findFlight(LocalDate date, String from, String to,boolean isTatkal) {

		FlightmanagementDatabase flightmanagementDatabase = FlightmanagementDatabase.getInstance();
		flightmanagementDatabase.assignFlights(new ArrayList<Flight>());
		ArrayList<Flight> flightsAvail = flightmanagementDatabase.isTicketsAvail(from, to, date);
		if (flightsAvail != null) {
			Flight selectedFlight = controllerCallback.chooseFlight(flightsAvail,isTatkal);
			return selectedFlight;
		}
		return null;
	}

	interface TicketModelControllerCallback {
		void bookingSuccess(ArrayList<Ticket> tickets, User user);

		Flight chooseFlight(ArrayList<Flight> flightsAvail,boolean isTatkal);

		void paymentFailed(User user);

		boolean isPaymentDone();

		void bookingFailed(User user);

		void cancellationSuccess(User user);

		void cancellationFailed(String string, User user);

	}

}
