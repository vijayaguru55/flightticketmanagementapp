package com.flightticketmanagement.ticket;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import com.flightticketmanagement.dto.Flight;
import com.flightticketmanagement.dto.Passenger;
import com.flightticketmanagement.dto.Ticket;
import com.flightticketmanagement.dto.User;

import com.flightticketmanagement.login.LoginView;


public class TicketView implements TicketViewCallaback {
	Scanner scanner = new Scanner(System.in);
	private TicketControllerCallback callback;

	public TicketView() {
		this.callback = new TicketController(this);
	}

	public void book(User user) {
		System.out.println("Select from location");

		selctLocation();
		byte option = scanner.nextByte();
		String from = selectedLocation(option);

		System.out.println("Select to location");
		selctLocation();
		option = scanner.nextByte();
		String to = selectedLocation(option);
		if (from.toLowerCase().equalsIgnoreCase(to)) {
			System.out.println("invalid destination");
			selctLocation();
			option = scanner.nextByte();
			to = selectedLocation(option);
		}
		System.out.println("Enter departure Date as(yyyy-mm-dd)");
		String date = scanner.next();
		LocalDate current = LocalDate.now();
		LocalDate entered = LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
		Period difference = Period.between(current, entered);
		boolean istatkal = false;
		while (difference.getYears() < 0 || difference.getMonths() < 0 || difference.getDays() < 0) {

			System.out.println("Invalid date , Please enter valid date");
			System.out.println("Enter   Date as(yyyy-mm-dd)");
			difference = difference.between(current, entered = entered.parse(date = scanner.next()));

		}
		if (difference.getYears() == 0 && difference.getMonths() == 0 && difference.getDays() <= 1) {
			
			istatkal = true;

		}
		Flight selectedFlight = callback.findFlight(entered, from, to, istatkal);
		if (selectedFlight != null) {

			System.out.println("Enter Number of tickets");
			byte ticketsCount = scanner.nextByte();
			ArrayList<Passenger> passengers = getPassengerDetails(ticketsCount);

			if (from != null && to != null)
				callback.bookticket(user, from, to, date, ticketsCount, passengers, selectedFlight);
		} else {
			bookingFailed(user);
		}
	}

	public void cancelticket(User user) {
		System.out.println("Enter ticketId");
		String ticketId = scanner.next();
		callback.cancelticket(ticketId, user);

	}

	private ArrayList<Passenger> getPassengerDetails(byte ticketsCoutn) {
		System.out.println("Enter passengers details");
		ArrayList<Passenger> passengers = new ArrayList<Passenger>();
		for (int count = 1; count <= ticketsCoutn; count++) {
			Passenger passenger = new Passenger();
			System.out.println(count + ".Passenger Name :");
			passenger.setName(scanner.next());
			System.out.println("Enter " + passenger.getName() + "'s Age");
			passenger.setAge(scanner.nextByte());
			System.out.println("Enter " + passenger.getName() + "'s Gender");
			passenger.setGender(scanner.next());
			passengers.add(passenger);
		}
		return passengers;
	}

	private String selectedLocation(byte option) {
		switch (option) {
		case 1:
			return (String) "Chennai";
		case 2:
			return (String) "Trichirappalli";

		case 3:
			return (String) "Bangalore";

		case 4:
			return (String) "Trivandram";

		default:
			System.out.println("Invalid location");
		}
		return null;

	}

	private void selctLocation() {
		System.out.println("1.Chennai\n2.Trichirappalli\n3.Bangalore\n4.Trivandram");
	}

	@Override
	public void bookingSucces(ArrayList<Ticket> tickets, User user) {
		System.out.println(tickets.size() + " Tickets are booked Sucessfuly");
		viewTickets(tickets);
		LoginView loginView = new LoginView();
		loginView.loginSuccess(user,false);

	}

	private void viewTickets(ArrayList<Ticket> tickets) {
		for (Ticket ticket : tickets) {
			viewTicket(ticket);
		}
	}

	private void viewTicket(Ticket ticket) {
		System.out.println("-------------------------------------------------------------------------");
		System.out.println("      Welcome to " + ticket.getFlightName());
		System.out.println("Passenger :" + ticket.getPassengerName() + " " + ticket.getPassengerAge() + "Years old \t"
				+ ticket.getPassengerGender());
		System.out.println("From      :" + ticket.getFrom() + "       	To 	 :" + ticket.getTo());
		System.out.println("Date      :" + ticket.getDate() + "       	Time :" + ticket.getTime());
		System.out.println("Ticket Id :" + ticket.getTicketId());
		System.out.println("Flight id :" + ticket.getFlightId());

	}

	@Override
	public void bookingFailed(User user) {
		System.out.println("No Flights are Available or Seats are filled");
		LoginView loginView = new LoginView();
		loginView.loginSuccess(user,false);
	}

	@Override
	public void cancellationSuccess(User user) {
		System.out.println("Ticket canceled");
		LoginView loginView = new LoginView();
		loginView.loginSuccess(user,false);

	}

	@Override
	public void cancellationFailed(String message, User user) {
		System.out.println("Cancellation Failed");
		System.out.println(message);
		LoginView loginView = new LoginView();
		loginView.loginSuccess(user,false);

	}

	@Override
	public boolean isPaymentDone() {
		System.out.println("1.proceed Payment\n2.Cancel");
		byte option = scanner.nextByte();
		return option == 1 ? true : false;
	}

	@Override
	public void paymentFailed(User user) {
		System.out.println("Booking Canceled due to PaymentFailed");
		LoginView loginView = new LoginView();
		loginView.loginSuccess(user,false);
	}

	@Override
	public Flight chooseFlight(ArrayList<Flight> flightsAvail, boolean isTatkal) {
	
		System.out.println("Available flights Are");
		int count = 1;
		for (Flight flight : flightsAvail) {
			if (isTatkal)
				flight.setTatkal(true);
			System.out.println("-----------------------------------------");
			System.out.println("Press " + count++ + " to select flight");
			System.out.println("\t" + flight.getCompanyName() + "\t");
			System.out.println("Date :" + flight.getDepartureDate());
			System.out.println("Time :" + flight.getDeparturetime());
			System.out.println("Price :" + flight.getTicketPrice());
		}
		int option = scanner.nextInt();
		return flightsAvail.get(option - 1);
	}
}
