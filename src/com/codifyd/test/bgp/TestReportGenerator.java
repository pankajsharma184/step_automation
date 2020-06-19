package com.codifyd.test.bgp;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.codifyd.automation.bgp.BGPReportInputUtil;
import com.codifyd.automation.bgp.ReportGenerator;
import com.codifyd.automation.stepconversion.util.UserInputFileUtilDO;

public class TestReportGenerator {

	public static void main(String[] args) {
		BGPReportInputUtil defaultObject = getDefaultUserInputDO();
		
		InputStreamReader inReader = new InputStreamReader(System.in);
		BufferedReader reader = new BufferedReader(inReader);
		BGPReportInputUtil userInput = new BGPReportInputUtil();
		try {

			System.out.println("Please enter the Server Host URL");
			String serverHostUrl= reader.readLine();
			if (serverHostUrl == null || serverHostUrl.trim().isEmpty()) {
				serverHostUrl = defaultObject.getInputServerPath();
			}
			userInput.setInputServerPath(serverHostUrl);

			System.out.println("Please enter the input file path : ");
			String inputFilePath = reader.readLine();
			if (inputFilePath == null || inputFilePath.trim().isEmpty()) {
				inputFilePath = defaultObject.getInputFile().getAbsolutePath();
			}
			userInput.setInputFile(new File(inputFilePath));
			
			System.out.println("Please enter the username ");
			String username = reader.readLine();
			if (username == null || username.trim().isEmpty()) {
				username = defaultObject.getUsername();
			}
			userInput.setUsername(username);
						
			System.out.println("Please enter the password ");
			String password = reader.readLine();
			if (password == null || password.trim().isEmpty()) {
				password = defaultObject.getPassword();
			}
			userInput.setPassword(password);
			
			System.out.println("Please enter the ContextID ");
			String contextID = reader.readLine();
			if (contextID == null || contextID.trim().isEmpty()) {
				contextID = defaultObject.getContextID();
			}
			userInput.setContextID(contextID);		

			ReportGenerator.generateReport(userInput);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	static BGPReportInputUtil getDefaultUserInputDO() {
		BGPReportInputUtil defaultObject = new BGPReportInputUtil();
		String inputFilePath = Paths.get(System.getProperty("user.home"), "Downloads", "bgperr", "PF Error File In UAT G&T English.txt")
				.toString();		
		defaultObject.setInputFile(new File (inputFilePath));
		defaultObject.setInputServerPath("https://grandandtoy-qa.scloud.stibo.com/");
		defaultObject.setUsername("stepsys");
		defaultObject.setPassword("stepsys");
		defaultObject.setContextID("Context1");
		return defaultObject;
	}

}
