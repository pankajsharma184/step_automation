package com.codifyd.automation.util;

import java.io.File;

public class InputValidator {

	// Validate User Input For XML to Excel Conversion
	public static void validateXMLToExcel(UserInputFileUtilDO input) throws InvalidInputException {

		// Validate Input File
		String inputFilePath = input.getInputPath();
		if (inputFilePath != null && !inputFilePath.trim().isEmpty()) {
			if (new File(inputFilePath).isFile()) {
				if (!inputFilePath.trim().endsWith(".xml")) {
					throw new InvalidInputException("Input File is not a XML file at path -  " + inputFilePath);
				}
			} else {
				throw new InvalidInputException("Input File Not Found at given path - " + inputFilePath);
			}
		} else {
			throw new InvalidInputException("Input File Path is Empty");
		}

		// Validate Output Path
		String outputPath = input.getOutputPath();
		if (inputFilePath != null && !inputFilePath.trim().isEmpty()) {
			if (!(new File(inputFilePath).isDirectory())) {
				throw new InvalidInputException("Cannot Find Directory - " + outputPath);
			}
		} else {
			throw new InvalidInputException("Input File Path is Empty");
		}

		// Validate Output File Name
		String filename = input.getFilename();
		if (!filename.trim().endsWith(".xlsx")) {
			throw new InvalidInputException("Output Filename should have extension xlsx");
		}

		// Validate Delimiter
		String delimiter = input.getDelimiters();
		if (delimiter != null && !delimiter.isEmpty() && !delimiter.trim().matches(";|,|\\|")) {
			throw new InvalidInputException("The delimiter can be only \",\" \";\" \"|\": ");
		}

	}

	// Validate User Input For Excel to XML Conversion
	public static void validateExcelToXML(UserInputFileUtilDO input) throws InvalidInputException {

		// Validate Input File
		String inputFilePath = input.getInputPath();
		if (inputFilePath != null && !inputFilePath.trim().isEmpty()) {
			if (new File(inputFilePath).isFile()) {
				if (!inputFilePath.trim().endsWith(".xlsx")) {
					throw new InvalidInputException("Input File is not a Excel file at path -  " + inputFilePath);
				}
			} else {
				throw new InvalidInputException("Input File Not Found at given path - " + inputFilePath);
			}
		} else {
			throw new InvalidInputException("Input File Path is Empty");
		}

		// Validate Output Path
		String outputPath = input.getOutputPath();
		if (inputFilePath != null && !inputFilePath.trim().isEmpty()) {
			if (!(new File(inputFilePath).isDirectory())) {
				throw new InvalidInputException("Cannot Find Directory - " + outputPath);
			}
		} else {
			throw new InvalidInputException("Input File Path is Empty");
		}

		// Validate Output File Name
		String filename = input.getFilename();
		if (!filename.trim().endsWith(".xml")) {
			throw new InvalidInputException("Output Filename should have extension xml");
		}
	}

}
