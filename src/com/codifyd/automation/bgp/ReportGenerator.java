package com.codifyd.automation.bgp;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Set;

import org.json.simple.JSONObject;

public class ReportGenerator {
	public static void generateReport(BGPReportInputUtil userinput) throws Exception {

		// Get File Input

		File inputFile = userinput.getInputFile();
		String inputFilePath = inputFile.getAbsolutePath();
		if (getFileExtension(inputFilePath).trim().equals("txt")) {
			if (!new File(inputFilePath).isFile()) {
				throw new Exception("Input file not found. Given value - " + inputFilePath);
			}
		} else {
			throw new Exception("Input file is not a text file. Given value - " + inputFilePath);
		}

		// Get Output Path
		File outputFile = userinput.getOutputFile();

		// Get Host Url
		String urlText = userinput.getInputServerPath();

		if (!(urlText.trim().startsWith("https:") || urlText.trim().startsWith("http"))) {
			throw new Exception("Input Server path is invalid. Given value - " + urlText);
		}

		Set<String> urls = BGPRestAPI.getValidURLsFromInput(inputFile, urlText, userinput.getContextID(),
				userinput.getWorkspaceID());
		List<JSONObject> list = JSONHandler.getErrorList(urls,
				new StepAuthenticator(userinput.getUsername(), userinput.getPassword()));
		JSONHandler.writeJSONtoExcel(list, outputFile);
	}

	private static String getFileExtension(String file) {
		int index = file.lastIndexOf(".");
		String extension = "";
		if (index >= 0) {
			extension = file.substring(index + 1);
		}
		return extension;
	}

}

// https://grandandtoy-dev.scloud.stibo.com/restapiv2/background-processes/BGP_136302/execution-report?context=Context1&workspace=Main