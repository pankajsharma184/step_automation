package com.codifyd.automation.util;

import java.io.File;

public class InputValidator {

	//add all validation methods
	public static void validateXMLToExcel(UserInputFileUtilDO input) throws InvalidInputException{
		String inputFilePath = input.getInputPath();
		if (inputFilePath != null && !inputFilePath.trim().isEmpty()){
			if (new File(inputFilePath).isFile()) {
				if (!inputFilePath.trim().endsWith(".xml")) {
					throw new InvalidInputException("Input File is not a XML file at path -  "+ inputFilePath);
				} 
			} else {
				throw new InvalidInputException("Input File Not Found at given path - "+ inputFilePath);
			}
		} else {
			throw new InvalidInputException("Input File Path is Empty");
		}
		
		String filename = input.getFilename();
		if (!filename.trim().endsWith(".xlsx")) {
			throw new InvalidInputException("Output Filename should have extension xlsx");
		} 		
		String delimeter = input.getDelimeters();
		if (delimeter!=null && !delimeter.isEmpty() && !delimeter.trim().matches(";|,|\\|")){				
			throw new InvalidInputException("The delimiter can be only \",\" \";\" \"|\": ");		
		}
		
	}
	
	public static void validateExcelToXML(UserInputFileUtilDO input) throws InvalidInputException{
		String inputFilePath = input.getInputPath();
		if (inputFilePath != null && !inputFilePath.trim().isEmpty()){
			if (new File(inputFilePath).isFile()) {
				if (!inputFilePath.trim().endsWith(".xlsx")) {
					throw new InvalidInputException("Input File is not a Excel file at path -  "+ inputFilePath);
				} 
			} else {
				throw new InvalidInputException("Input File Not Found at given path - "+ inputFilePath);
			}
		} else {
			throw new InvalidInputException("Input File Path is Empty");
		}
		
		String filename = input.getFilename();
		if (!filename.trim().endsWith(".xml")) {
			throw new InvalidInputException("Output Filename should have extension xml");
		} 		
	}
	
}
