package com.codifyd.automation.stepconversion.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.codifyd.automation.stepconversion.attributelink.AttributeLinkExcelFileHandler;
import com.codifyd.automation.stepconversion.attributelink.AttributeLinkXMLFileHandler;
import com.codifyd.automation.stepconversion.attributeschema.AttributeExcelFileHandler;
import com.codifyd.automation.stepconversion.attributeschema.AttributeXMLFileHandler;
import com.codifyd.automation.stepconversion.lovschema.LovExcelFileHandler;
import com.codifyd.automation.stepconversion.lovschema.LovXMLFileHandler;
import com.codifyd.automation.stepconversion.uom.UomExcelFIleHandler;
import com.codifyd.automation.stepconversion.uom.UomXMLFileHandler;

public class SharedHelper {

	// class used to testing. Should mimic what we call from website

	public static UserInputFileUtilDO getUserInput(BufferedReader reader, String propertiesString) throws Exception {
		UserInputFileUtilDO userInputFileUtilDO = new UserInputFileUtilDO();
		try {
			System.out.print("Please enter the input file path : ");
			String inputFilePath = reader.readLine();
			while (inputFilePath == null || inputFilePath.trim().isEmpty()) {
				System.out.println("You need to specify a path!");
				inputFilePath = reader.readLine();
			}

			userInputFileUtilDO.setInputPath(inputFilePath.trim());

			System.out.print("Please enter the output file path : ");
			String outFilePath = reader.readLine();
			while (outFilePath == null || outFilePath.trim().isEmpty()) {
				System.out.println("You need to specify a path!");
				outFilePath = reader.readLine();
			}

			userInputFileUtilDO.setOutputPath(outFilePath.trim());

			System.out.print("Please enter the output Filename with extension : ");
			String outputFileName = reader.readLine();
			while (outputFileName == null || outputFileName.trim().isEmpty()) {
				System.out.println("You need to specify a name!");
				outputFileName = reader.readLine();
			}
			userInputFileUtilDO.setFilename(outputFileName.trim());

			System.out.print("Please enter the config File path if any: ");
			String propertiesFile = reader.readLine();
			userInputFileUtilDO.setPropertiesFile(propertiesFile, propertiesString);

			System.out.print("Please enter the Multi Field Seperator (Can be only \",\" \";\" \"|\"): ");
			String delimeter = reader.readLine();
			
			userInputFileUtilDO.setDelimeters(delimeter.trim());
			

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return userInputFileUtilDO;
	}

	public static void main(String[] args) throws Exception {
		InputStreamReader inStream = new InputStreamReader(System.in);
		BufferedReader reader = new BufferedReader(inStream);

		System.out.println("1. XML to Excel Converter");
		System.out.println("2. Excel to XML Converter");
		System.out.println("Please Select one of the above options");

		String option = reader.readLine();
		while (option == null || option.trim().isEmpty()) {
			System.out.println("!!Please provide an input");
			option = reader.readLine();
		}

		switch (option) {
		case "1":
			print();
			String option1 = reader.readLine();
			while (option == null || option.trim().isEmpty()) {
				System.out.println("!!Please provide an input");
				option1 = reader.readLine();
			}
			switch (option1) {
			case "1":
				AttributeXMLFileHandler convertXMLToExcel = new AttributeXMLFileHandler();
				convertXMLToExcel.handleFile(getUserInput(reader, AutomationConstants.ATTRIBUTE));
				break;

			case "2":
				AttributeLinkXMLFileHandler attributeLinkExcel = new AttributeLinkXMLFileHandler();
				attributeLinkExcel.handleFile(getUserInput(reader, AutomationConstants.ATTRIBUTELINK));
				break;

			case "3":
				LovXMLFileHandler convertXmltoExcellov = new LovXMLFileHandler();
				convertXmltoExcellov.handleFile(getUserInput(reader, AutomationConstants.LOV));
				break;

			case "4":
				UomXMLFileHandler uomxmlHandler = new UomXMLFileHandler();
				uomxmlHandler.handleFile(getUserInput(reader, AutomationConstants.UOM));
				break;

			case "5":
				// TaxonomyXMLHandler taxonomyXMLHandler = new
				// TaxonomyXMLHandler();
				// taxonomyXMLHandler.handleFile(getUserInput(reader,
				// TAXONOMY));
				break;

			default:
				System.out.println("Invalid Option. Doing Nothing.");
				break;
			}
			break;

		case "2":
			print();
			String option2 = reader.readLine();
			while (option == null || option.trim().isEmpty()) {
				System.out.println("!!Please provide an input");
				option2 = reader.readLine();
			}
			switch (option2) {
			case "1":
				AttributeExcelFileHandler attributeXMLHandler = new AttributeExcelFileHandler();
				attributeXMLHandler.handleFile(getUserInput(reader, AutomationConstants.ATTRIBUTE));
				break;

			case "2":
				AttributeLinkExcelFileHandler attributeLinkXML = new AttributeLinkExcelFileHandler();
				attributeLinkXML.handleFile(getUserInput(reader, AutomationConstants.ATTRIBUTE));
				break;

			case "3":
				LovExcelFileHandler lovXMLHandler = new LovExcelFileHandler();
				lovXMLHandler.handleFile(getUserInput(reader, AutomationConstants.ATTRIBUTE));
				break;

			case "4":
				UomExcelFIleHandler uomxmlHandler = new UomExcelFIleHandler();
				uomxmlHandler.handleFile(getUserInput(reader, AutomationConstants.ATTRIBUTE));
				break;

			case "5":
				// TaxonomyXMLHandler taxonomyXMLHandler = new
				// TaxonomyXMLHandler();
				// taxonomyXMLHandler.handleFile(getUserInput(reader, ATTRIBUTE,
				// EXCELToXML));
				break;

			default:
				System.out.println("Invalid Option. Doing Nothing.");
				break;
			}
			break;

		default:
			System.out.println("Invalid Option. Doing Nothing");
			break;
		}

	}

	static void print() {
		System.out.println("1. Attribute");
		System.out.println("2. Attribute Link");
		System.out.println("3. LOV");
		System.out.println("4. UOM");
		System.out.println("5. Schema");
		System.out.println("6. Taxonomy");
		System.out.println("Please Select one of the above options :");
	}

}
