package com.flightticketmanagement.ticket;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;

import com.flightticketmanagement.dto.Flight;
import com.flightticketmanagement.dto.Passenger;
import com.flightticketmanagement.dto.Ticket;
import com.flightticketmanagement.dto.User;
import com.flightticketmanagement.ticket.TicketModel.TicketModelControllerCallback;


public class TicketController implements TicketControllerCallback,TicketModelControllerCallback {
	private TicketViewCallaback viewCallaback;
	private TicketModelCallback modelCallback;

	public TicketController(TicketViewCallaback callaback) {
		this.viewCallaback = callaback;
		this.modelCallback = new TicketModel(this);
	}

	@Override
	public void bookticket(User user, String from, String to, String date, byte ticketsCoutn,
			ArrayList<Passenger> passengers,Flight flight) {
		modelCallback.bookTicket(user, from, to, date, ticketsCoutn, passengers,flight);
	}

	@Override
	public void bookingSuccess(ArrayList<Ticket> tickets,User user) {
		viewCallaback.bookingSucces(tickets,user);

	}

	@Override
	public void bookingFailed(User user) {
		viewCallaback.bookingFailed(user);
	}

	@Override
	public void cancelticket(String ticketId, User user) {
		modelCallback.cancelTicket(ticketId, user);
		
	}

	

	@Override
	public void cancellationFailed(String message,User user) {
		viewCallaback.cancellationFailed(message,user);
	}

	@Override
	public void cancellationSuccess(User user) {
		viewCallaback.cancellationSuccess(user);
	}

	@Override
	public boolean isPaymentDone() {
		
		return viewCallaback.isPaymentDone();
	}

	@Override
	public void paymentFailed(User user) {
		viewCallaback.paymentFailed(user);
	}

	@Override
	public Flight chooseFlight(ArrayList<Flight> flightsAvail,boolean isTatkal) {
		return viewCallaback.chooseFlight(flightsAvail,isTatkal);
	}

	@Override
	public Flight findFlight(LocalDate date, String from, String to,boolean isTatkal) {
		return modelCallback.findFlight(date, from, to,isTatkal);
	}

	
	

	


}
