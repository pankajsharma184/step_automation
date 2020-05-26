package com.codifyd.automation.bgp;

import java.io.Console;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.LinkedList;
import java.util.List;

class MyAuthenticator extends Authenticator {
	/**
	 * Called when password authorization is needed.
	 * 
	 * @return The PasswordAuthentication collected from the user, or null if none
	 *         is provided.
	 */

	protected PasswordAuthentication getPasswordAuthentication() {
		// Get Credentials
		List<Object> credentials = getUserPassword();
		String uName = (String) credentials.get(0);
		char[] pwd = (char[]) credentials.get(1);
		return new PasswordAuthentication(uName, pwd);
//		return new PasswordAuthentication("stepsys", "stepsys".toCharArray());
	}

	private static List<Object> getUserPassword() {
		List<Object> credentials = new LinkedList<Object>();

		Console cons;
		if ((cons = System.console()) != null) {
			int i = 0;
			while (true) {
				System.out.println("Please Enter Username And Password");
				String userID = cons.readLine("Username: ");
				char[] password = cons.readPassword("Password: ");
				if (!userID.trim().equals("") && !password.toString().trim().equals("")) {
					credentials.add(userID);
					credentials.add(password);
					break;
				} else {
					System.out.println("!!!Username and Password Cannot Be Blank");
				}
				i++;
				if (i >= 3) {
					System.out.println("\n======!!!!Too Many Failed Attempts : Terminating The Program!!!!======");
					System.exit(0);
				}
			}

		} else {
			credentials.add("stepsys");
			credentials.add("stepsys".toCharArray());
		}
		return credentials;
	}

}