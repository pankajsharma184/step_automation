package com.codifyd.automation.bgp;

import static com.codifyd.automation.bgp.JSONHandler.getErrorList;
import static com.codifyd.automation.bgp.JSONHandler.jsonHandler;
import static com.codifyd.automation.bgp.Util.getURLs;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

public class Main {
	public static void main(String[] args) throws IOException, ParseException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int flag = 0;

		// Get File Input
		String input = "";
		while (true) {
			System.out.println("Enter the BGP Process List File Path: ");
			input = br.readLine();
			if (getFileExtension(input).trim().equals("txt") || getFileExtension(input).trim().equals("properties")) {
				if (new File(input).isFile()) {
					flag = 0;
					break;
				} else {
					System.out.println("Invalid Input Paht : File Not Found");
				}
			}

			flag++;
			if (flag >= 3) {
				System.out.println("\n======!!!!Too Many Failed Attempts : Terminating The Program!!!!======");
				System.exit(0);
			}
		}

		// Get Output Path
		String outputPath = "";
		while (true) {
			System.out.println("Enter the Output File Path: ");
			outputPath = br.readLine();
			if (!outputPath.equals("") && new File(outputPath).isDirectory()) {
				flag = 0;
				break;
			} else {
				System.out.println("Invalid Input Paht : No Such Directory Found");
			}
			flag++;
			if (flag >= 3) {
				System.out.println("======!!!!Too Many Failed Attempts : Terminating The Program!!!!======");
				System.exit(0);
			}
		}

		// Get Output File Name
		String outputFileName = "";
		while (true) {
			System.out.println("Enter the Output File Name with \".xlsx\" extension : ");
			outputFileName = br.readLine();
			if (getFileExtension(outputFileName).trim().equals("xlsx")) {
				flag = 0;
				break;
			}
			flag++;
			if (flag >= 3) {
				System.out.println("\n======!!!!Too Many Failed Attempts : Terminating The Program!!!!======");
				System.exit(0);
			}
		}

		// Get Host Url
		String urlText = "";
		while (true) {
			System.out.println("Enter the host URL");
			System.out.println("URL Pattern : \'https://[application server]/\'");
			urlText = br.readLine();
			if (urlText.trim().startsWith("https:")) {
				flag = 0;
				break;
			} else {
				System.out.println(urlText.trim().startsWith("http") && urlText.trim().endsWith("=Main"));
				System.out.println("Invalid Input : URL Pattern Mismatch");
			}
			flag++;
			if (flag >= 3) {
				System.out.println("\n======!!!!Too Many Failed Attempts : Terminating The Program!!!!======");
				System.exit(0);
			}
		}

		

		List<JSONObject> list = getErrorList(getURLs(input, urlText), outputPath);
		jsonHandler(list, outputFileName,outputPath);

		br.close();
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

//https://grandandtoy-dev.scloud.stibo.com/restapiv2/background-processes/BGP_136302/execution-report?context=Context1&workspace=Main