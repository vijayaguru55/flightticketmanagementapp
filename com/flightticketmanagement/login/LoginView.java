package com.flightticketmanagement.login;

import java.util.Scanner;

import com.flightticketmanagement.dto.User;
import com.flightticketmanagement.flight.FlightView;
import com.flightticketmanagement.ticket.TicketView;
import com.flightticketmanagement.user.UserView;



public class LoginView implements LoginViewCallback {
	private LoginControllerCallback loginController;
	Scanner scanner = new Scanner(System.in);

	public LoginView() {
		loginController = new LoginController(this);
	}

	public static void main(String[] args) {
		LoginView loginView = new LoginView();
		loginView.create();
	}

	public void create() {
		System.out.println("1.SignUp\n2.UserLogin\n3.AdminLogin\n4.Exit");
		int option = scanner.nextInt();
		switch (option) {
		case 1: {
			UserView userView = new UserView();
			userView.create();
		}
			break;
		case 2:
			login(false);
			break;
		case 3:
			login(true);
			break;
		case 4: {
			System.out.println("Thank you.......");
			System.exit(0);
		}
			break;
		}
	}

	public void login(boolean isAdmin) {
		System.out.println("Enter User Name :");
		String name = scanner.next();
		System.out.println("Enter password :");
		String password = scanner.next();
		if (isAdmin)
			loginController.checkForLogin(name, password, isAdmin);
		else {
			loginController.checkForLogin(name, password, isAdmin);
		}
	}

	public void menu(User user) {
		System.out.println("-------------------------------------------------------------------------");
		System.out.println("1.Book Tciket\n2.CancelTicket\n3.LogOut");
		byte option = scanner.nextByte();
		switch (option) {
		case 1: {
			TicketView ticketView = new TicketView();
			ticketView.book(user);
		}
			break;
		case 2: {
			TicketView ticketView = new TicketView();
			ticketView.cancelticket(user);
		}
			break;
		case 3: {
			LoginView loginView = new LoginView();
			loginView.create();
		}
			break;
		default:
			System.out.println("Invalid Option");
			break;
		}
	}

	@Override
	public void loginSuccess(User user, boolean isAdmin) {
		System.out.println("Wlcome back <-" + user.getName() + "->...!");
		if (isAdmin) {
			FlightView flightView = new FlightView();
			flightView.create(user);
		} else
			menu(user);
	}

	public void loginFailed(User user) {
		System.out.println("Invalid Credentials");
		create();
	}

}
