package com.flightticketmanagement.flight;

import java.util.ArrayList;

import com.flightticketmanagement.dto.Flight;
import com.flightticketmanagement.dto.Passenger;
import com.flightticketmanagement.dto.Ticket;
import com.flightticketmanagement.dto.User;
import com.flightticketmanagement.flight.FlightModel.FlightModelControllerCallbak;

public class FlightController implements FlightControllerCallback, FlightModelControllerCallbak {
	private FlightViewCallback viewCallback;
	private FlightModelCallback modelCallback;

	public FlightController(FlightView view) {
		this.viewCallback = view;
		this.modelCallback = new FlightModel(this);

	}

	@Override
	public void addFlight(String companyName, String fromLocation, String destination, int price,User user) {
		Flight flight = new Flight();
		flight.setCompanyName(companyName);
		flight.setDestination(destination);
		flight.setCurrentStartLocation(fromLocation);
		flight.setTicketPrice(price);
		flight.setFlightId(String.valueOf(flight.getId())+companyName.substring(0,5).toUpperCase());
		modelCallback.addFlight(flight,user);
	}

	@Override
	public void flightAddedSuccess(String flightId,User user) {
		viewCallback.fligthAddeSucces(flightId,user);
	}




}
