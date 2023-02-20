package com.flightticketmanagement.flight;

import java.awt.Dialog.ModalExclusionType;
import java.util.ArrayList;

import com.flightticketmanagement.dto.Flight;
import com.flightticketmanagement.dto.Passenger;
import com.flightticketmanagement.dto.Ticket;
import com.flightticketmanagement.dto.User;

import com.flightticketmanagement.repository.*;

public class FlightModel implements FlightModelCallback{
	private FlightModelControllerCallbak controllerCallbak;
	public FlightModel(FlightController flight) {
		this.controllerCallbak = flight;
	}
	interface FlightModelControllerCallbak{

		void flightAddedSuccess(String string,User user);
		
	}
	@Override
	public void addFlight(Flight flight,User user) {
		FlightmanagementDatabase database = FlightmanagementDatabase.getInstance();
		database.addFlight(flight);
		controllerCallbak.flightAddedSuccess(flight.getFlightId(),user);
	}

}
