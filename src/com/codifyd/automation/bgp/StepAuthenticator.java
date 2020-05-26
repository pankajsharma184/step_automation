package com.codifyd.automation.bgp;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

class StepAuthenticator extends Authenticator {
	String username;
	String password;
	
	public StepAuthenticator(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	/**
	 * Called when password authorization is needed.
	 * 
	 * @return The PasswordAuthentication collected from the user, or null if none
	 *         is provided.
	 */

	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(username, password.toCharArray());
//		return new PasswordAuthentication("stepsys", "stepsys".toCharArray());
	}

}