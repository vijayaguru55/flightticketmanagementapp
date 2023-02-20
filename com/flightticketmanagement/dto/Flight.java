package com.flightticketmanagement.dto;

import java.time.LocalDate;

public class Flight {
	private String flightId;
	private String companyName;
	private int totalSeats = 180;
	private int avalableSeats = 150;
	private int tatkalSeats = 30;
	private int ticketPrice;
	private int id = 2015;
	private String currentStartLocation;
	private String destination;
	private LocalDate departureDate;
	private String departuretime;
	private boolean isTatkal =false;

	public boolean isTatkal() {
		return isTatkal;
	}
	
	
	public void setTatkal(boolean isTatkal) {
		this.isTatkal = isTatkal;
		if(isTatkal) {
			this.ticketPrice*=2;
			if(avalableSeats>0)avalableSeats+=tatkalSeats;
		}
		
	}

	public Flight() {
		id++;
	}

	public int getTatkalSeats() {
		return tatkalSeats;
	}

	public void setTatkalSeats(int tatkalSeats) {
		this.tatkalSeats = tatkalSeats;
	}

	public int getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(int ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	public String getDeparturetime() {
		return departuretime;
	}

	public void setDeparturetime(String departuretime) {
		this.departuretime = departuretime;
	}

	public int getTotalSeats() {
		return totalSeats;
	}

	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}

	public int getAvalableSeats() {
		return avalableSeats;
	}

	public void setAvalableSeats(int avalableSeats) {
		this.avalableSeats = avalableSeats;
	}

	public String getCurrentStartLocation() {
		return currentStartLocation;
	}

	public void setCurrentStartLocation(String currentStartLocation) {
		this.currentStartLocation = currentStartLocation;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getFlightId() {
		return flightId;
	}

	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public LocalDate getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(LocalDate departureDate) {
		this.departureDate = departureDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


}
