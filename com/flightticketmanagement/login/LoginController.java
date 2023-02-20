package com.flightticketmanagement.login;

import com.flightticketmanagement.dto.User;
import com.flightticketmanagement.login.LoginModel.LoginModelControllerCallBack;


public class LoginController implements LoginControllerCallback,LoginModelControllerCallBack{
	private LoginViewCallback loginView;
	private LoginModelCalback loginModel;
	public LoginController(LoginView loginView) {
		this.loginView=loginView;
		this.loginModel = new LoginModel(this);
	}
	
	@Override
	public void checkForLogin(String name, String password,boolean isAdmin) {
		loginModel.checkForLogin(name,password,isAdmin);
	}
	@Override
	public void loginSuccess(User user,boolean isAdmin) {
		loginView.loginSuccess(user,isAdmin);
	}

	public void loginFailed(User user) {
		loginView.loginFailed(user);
	}

}
