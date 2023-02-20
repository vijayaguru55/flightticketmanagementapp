package com.flightticketmanagement.flight;

import java.util.Scanner;

import com.flightticketmanagement.dto.User;

import com.flightticketmanagement.login.LoginView;


public class FlightView implements FlightViewCallback {
	Scanner scanner = new Scanner(System.in);
	private FlightControllerCallback controllerCallback;

	public FlightView() {
		this.controllerCallback = new FlightController(this);

	}

	public void create(User user) {
		menu(user);
	}

	private void menu(User user) {
		System.out.println("\n1.Add new Flight\n2.Tickets Menu\n3.Logout");
		byte option = scanner.nextByte();
		switch (option) {
		case 1:
			addFlight(user);
			break;
		case 2: {
			LoginView loginView = new LoginView();
			loginView.menu(user);
		}
			break;
		case 3: {
			LoginView loginView = new LoginView();
			loginView.create();
		}
		default: {
			System.out.println("Invalid option");
		}
			break;
		}
	}

	private void addFlight(User user) {
		System.out.println("Enter Company Name:");
		String companyName = scanner.next();
		System.out.println("Enter from location:");
		String fromLocation = scanner.next();
		System.out.println("Enter destinaton:");
		String destination = scanner.next();
		System.out.println("Enter ticket Price:");
		int price = scanner.nextInt();
		
		controllerCallback.addFlight(companyName, fromLocation, destination, price, user);
	}

	@Override
	public void fligthAddeSucces(String fligthId, User user) {
		System.out.println("Flight added success flight Id is :" + fligthId);
		menu(user);
	}

	


}
