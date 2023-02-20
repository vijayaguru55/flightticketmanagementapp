package com.flightticketmanagement.dto;

import java.time.LocalDate;

public class Ticket extends Flight {
	private String ticketId;
	private String from;
	private String to;
	private String passengerName;
	private byte passengerAge;
	private String passengerGender;
	private int id=101;
	private String flightName;
	private LocalDate date;
	private String time;
	private User user;
	private String classType;
	private String seatNo;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getUserName() {
		return userName;
	}
	public void setUserName(User userName) {
		this.userName = userName;
	}

	private User userName;
	public Ticket() {
		id++;
	}
	public String getClassType() {
		return classType;
	}

	public void setClassType(String classType) {
		this.classType = classType;
	}

	public String getSeatNo() {
		return seatNo;
	}

	public void setSeatNo(String seatNo) {
		this.seatNo = seatNo;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public String getPassengerGender() {
		return passengerGender;
	}

	public void setPassengerGender(String passengerGender) {
		this.passengerGender = passengerGender;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getTicketId() {
		return ticketId;
	}

	public void setTicketId(String ticketId) {
		this.ticketId = ticketId+String.valueOf(id);
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getPassengerName() {
		return passengerName;
	}

	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}

	public byte getPassengerAge() {
		return passengerAge;
	}

	public void setPassengerAge(byte passengerAge) {
		this.passengerAge = passengerAge;
	}

	public String getFlightName() {
		return flightName;
	}

	public void setFlightName(String flightName) {
		this.flightName = flightName;
	}

}
