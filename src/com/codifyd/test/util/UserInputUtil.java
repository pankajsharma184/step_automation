package com.codifyd.test.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Scanner;

import com.codifyd.automation.stepconversion.util.UserInputFileUtilDO;

public class UserInputUtil {
	
	public static Integer getUserSelectedOptionFromMap(Map<Integer, String> options) throws IOException {		
		Scanner userInput = new Scanner( System.in );		
		System.out.println("Please enter the kind of get sample files ");
		options.forEach((k,v)->System.out.println( k +" : " + v));
		Integer choice = userInput.nextInt();	
		userInput.close();
		return choice;
	}
	
	
	public static UserInputFileUtilDO getUserInput(String propertiesString,UserInputFileUtilDO defaultObject) throws IOException {
		UserInputFileUtilDO userInputFileUtilDO = new UserInputFileUtilDO();
		InputStreamReader inStream = new InputStreamReader(System.in);
		BufferedReader reader = new BufferedReader(inStream);
		
			System.out.print("Please enter the input file path : ");
			String inputFilePath = reader.readLine();
			if(inputFilePath == null || inputFilePath.trim().isEmpty()){
				inputFilePath=defaultObject.getInputPath();
			}
			while (inputFilePath == null || inputFilePath.trim().isEmpty()) {
				System.out.println("Input File Path is mandatory");
				inputFilePath = reader.readLine();
			}
			userInputFileUtilDO.setInputPath(inputFilePath.trim());

			System.out.print("Please enter the output file path : ");
			String outFilePath = reader.readLine();
			if(outFilePath == null || outFilePath.trim().isEmpty()) {	
				outFilePath = defaultObject.getOutputPath();
				System.out.println("Using default output path -"+outFilePath);				
			}
			userInputFileUtilDO.setOutputPath(outFilePath.trim());

			System.out.print("Please enter the output Filename with extension : ");
			String outputFileName = reader.readLine();
			if(outputFileName == null || outputFileName.trim().isEmpty()) {
				outputFileName = defaultObject.getFilename();
				System.out.println("Using default output Filename -"+outputFileName);				
			}
			userInputFileUtilDO.setFilename(outputFileName.trim());

			System.out.print("Please enter the config File path if any: ");
			String propertiesFile = reader.readLine();
			userInputFileUtilDO.setPropertiesFile(propertiesFile, propertiesString);

			System.out.print("Please enter the Multi Field Seperator (Can be only \",\" \";\" \"|\"): ");
			String delimeter = reader.readLine();			
			userInputFileUtilDO.setDelimeters(delimeter.trim());
			

		
		return userInputFileUtilDO;
	}

}
