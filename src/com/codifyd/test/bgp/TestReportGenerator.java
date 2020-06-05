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

public class TestReportGenerator {

	public static void main(String[] args) {
		InputStreamReader inReader = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(inReader);
		BGPReportInputUtil userInput = new BGPReportInputUtil();
		try {

			System.out.println("Server Host URL");
			String server = new String();
			while (true) {
				server = br.readLine();
				if (server != null && !server.trim().isEmpty()) {
					break;
				}
			}
			userInput.setInputServerPath(server);

			System.out.println("Input File: ");
			String inputFile = new String();
			if ((inputFile = br.readLine()) == null || !inputFile.trim().isEmpty()) {
				inputFile = Paths.get(System.getProperty("user.home"), "Desktop", "test", "test.txt").toString();
			}
			userInput.setInputFile(new File(inputFile));

			DateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");
			String outputFilename = "Generated_" + userInput.getInputFile().getName() + "_" + df.format(new Date())
					+ ".xlsx";

			System.out.println("Output File Path: ");
			String outputFile = new String();
			if ((outputFile = br.readLine()) == null || !outputFile.trim().isEmpty()) {
				outputFile = Paths.get(userInput.getInputFile().getParent(), outputFilename).toString();
			}
			userInput.setInputFile(new File(inputFile));

			System.out.println("UserName: ");
			String unmae = new String();
			if ((unmae = br.readLine()) == null || !unmae.trim().isEmpty()) {
				unmae = "stepsys";
			}
			userInput.setUsername(unmae);

			System.out.println("Password: ");
			String pass = br.readLine();
			if ((pass = br.readLine()) == null || !pass.trim().isEmpty()) {
				pass = "stepsys";
			}
			userInput.setPassword(pass);

			System.out.println("Context ID: ");
			String contextID = br.readLine();
			if ((contextID = br.readLine()) == null || !contextID.trim().isEmpty()) {
				contextID = "Context1";
			}
			userInput.setContextID(contextID);

			System.out.println("WorkSpace ID: ");
			String workspace = br.readLine();
			if ((workspace = br.readLine()) == null || !workspace.trim().isEmpty()) {
				workspace = "Main";
			}
			userInput.setWorkspaceID(workspace);

			ReportGenerator.generateReport(userInput);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
