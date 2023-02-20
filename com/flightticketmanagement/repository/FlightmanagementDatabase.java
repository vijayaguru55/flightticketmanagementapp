package com.flightticketmanagement.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.jws.soap.SOAPBinding.Use;

import com.flightticketmanagement.dto.Admin;
import com.flightticketmanagement.dto.Credential;
import com.flightticketmanagement.dto.Flight;
import com.flightticketmanagement.dto.Passenger;
import com.flightticketmanagement.dto.Ticket;
import com.flightticketmanagement.dto.User;

public class FlightmanagementDatabase {
	private static FlightmanagementDatabase flightmanagementDatabase;

	private ArrayList<User> users = new ArrayList<User>();
	private ArrayList<Admin> admins = new ArrayList<Admin>();
	private ArrayList<Flight> fligths = new ArrayList<Flight>();
	private Map<User, ArrayList<Ticket>> tickets = new HashMap<User, ArrayList<Ticket>>();
	private Map<Flight, ArrayList<Passenger>> passengers = new HashMap<Flight, ArrayList<Passenger>>();
	private ArrayList<Credential> credentials = new ArrayList<Credential>();
	private FlightmanagementDatabase() {

	}
	
	//This addAdmin method is only for initial admin credentials
	private Admin addAdmin(String name, String password, long mobileNo, String email) {
		Admin admin = new Admin();
		admin.setUserId(String.valueOf(mobileNo / 2345));
		admin.setEmailId(email);
		admin.setName(name);
		admin.setMobuleNumber(mobileNo);
		admin.setPossword(password);
		return admin;
	}

	public void setAdmin() {
		this.admins.add(addAdmin("Vijayaguru", "GuruAsAdmin", 890980880, "vijayAdmin@airIndia"));
	}
	
	public void assignFlights(ArrayList<Flight> flightList) {

		Flight flight = new Flight();
		flight.setCompanyName("AirIndia");
		flight.setCurrentStartLocation("Chennai");
		flight.setDestination("Bangalore");
		flight.setTicketPrice(10000);
		flight.setDeparturetime("09.45PM");
		flight.setDepartureDate(LocalDate.parse("2023-02-22"));
		flight.setFlightId("AIRIN2304");
		flightList.add(flight);

		flight = new Flight();
		flight.setCompanyName("AirIndia");
		flight.setCurrentStartLocation("Chennai");
		flight.setDestination("Trichirappalli");
		flight.setDepartureDate(LocalDate.parse("2023-02-20"));
		flight.setDeparturetime("10.45PM");
		flight.setFlightId("AIRIN2305");
		flight.setTicketPrice(5000);
		flightList.add(flight);
		this.fligths = flightList;

		flight = new Flight();
		flight.setCompanyName("AirIndia");
		flight.setCurrentStartLocation("Chennai");
		flight.setDestination("Trivandram");
		flight.setTicketPrice(7000);
		flight.setDeparturetime("07.55PM");
		flight.setDepartureDate(LocalDate.parse("2023-02-21"));
		flight.setFlightId("AIRIN2306");
		flightList.add(flight);

		flight = new Flight();
		flight.setCompanyName("AirIndia");
		flight.setCurrentStartLocation("Trivandram");
		flight.setDestination("Chennai");
		flight.setTicketPrice(7000);
		flight.setDeparturetime("07.55PM");
		flight.setDepartureDate(LocalDate.parse("2023-02-22"));
		flight.setFlightId("AIRIN2307");
		flightList.add(flight);

		flight = new Flight();
		flight.setCompanyName("AirIndia");
		flight.setCurrentStartLocation("Chennai");
		flight.setDestination("Trichirappalli");
		flight.setTicketPrice(7000);
		flight.setDeparturetime("10.55AM");
		flight.setDepartureDate(LocalDate.parse("2023-02-20"));
		flight.setFlightId("AIRIN2308");
		flightList.add(flight);

		flight = new Flight();
		flight.setCompanyName("AirIndia");
		flight.setCurrentStartLocation("Chennai");
		flight.setDestination("Bangalore");
		flight.setTicketPrice(7000);
		flight.setDeparturetime("11.55PM");
		flight.setDepartureDate(LocalDate.parse("2023-02-20"));
		flight.setFlightId("AIRIN2309");
		flightList.add(flight);
	}

	public static FlightmanagementDatabase getInstance() {

		if (flightmanagementDatabase == null) {
			flightmanagementDatabase = new FlightmanagementDatabase();
		}
		return flightmanagementDatabase;

	}

	public User addUser(User user) {
		users.add(user);
		return user;
	}

	public boolean isExistUser(String name,String password) {
		for(Credential credential:this.credentials) {
			if(credential.getName().equals(name)&& credential.getPassword().equals(password))
				return true;
		}
		return false;
	}

	public ArrayList<Flight> isTicketsAvail(String from, String to, LocalDate date) {
		ArrayList<Flight> flights = new ArrayList<Flight>();
		for (Flight flight : fligths) {

			if (flight.getDepartureDate().equals(date) && flight.getCurrentStartLocation().equals(from)
					&& flight.getDestination().equals(to)) {
				flights.add(flight);
			}
		}
		if (flights.size() >= 1)
			return flights;
		return null;
	}

	

	public User checkCredentials(String name, String password) {
		for (User user : users) {
			if (user != null && user.getPossword().equals(password) && user.getName().equals(name)) {
				return user;
			}
		}
		return null;
	}

	public boolean isBookedTicket(String ticketId, User user) {
		if (tickets.containsKey(user)) {
			ArrayList<Ticket> tickets = this.tickets.get(user);
			for (Ticket ticket : tickets) {
				if (ticket.getTicketId().equals(ticketId))
				return true;
			}
		}
		return false;
	}

	public Admin adminCredial(String name, String password) {
		setAdmin();
		for (Admin admin : admins) {
			if (admin != null && admin.getPossword().equals(password) && admin.getName().equals(name)) {
				return admin;
			}
		}
		return null;
	}

	public void cancelTicket(String ticketId, User user) {
		ArrayList<Ticket> tickets = this.tickets.get(user);
		for (Ticket ticket : tickets) {
			if (ticket.getTicketId().equals(ticketId)) {
				this.tickets.get(user).remove(ticket);
				return;
			}
		}
	}

	public void addFlight(Flight flight) {
		this.fligths.add(flight);
	}

	public boolean isValidFlight(String flightId) {
		for (Flight flight : this.fligths) {
			if (flight.getFlightId().equals(flightId))
				return true;
		}
		return false;
	}

	public Flight getFlight(String flightId) {
		for (Flight flight : this.fligths) {
			if (flight.getFlightId().equals(flight))
				return flight;
		}
		return null;
	}

	public void updateBooking(User user, ArrayList<Ticket> tickets) {
		if(this.tickets.containsKey(user)) {
			ArrayList<Ticket> ticketsOf = this.tickets.get(user);
			tickets.addAll(tickets);
			this.tickets.put(user,ticketsOf);
		}else {
			this.tickets.put(user,tickets);
		}
	}

	public void updateCredential(Credential credential) {
		this.credentials.add(credential);
	}

}
