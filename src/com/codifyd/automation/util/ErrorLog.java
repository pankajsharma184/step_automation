package com.codifyd.automation.util;

import java.io.File;
import java.io.PrintStream;

public class ErrorLog {

	public static void getErrorLog(String output, Exception e) {
		try {
			File file = new File(output + "\\Error.log");
			PrintStream ps = new PrintStream(file);
			System.out.println(e.getCause());
			e.printStackTrace(ps);

		} catch (Exception e2) {
			e2.getCause();
		}

	}

}
