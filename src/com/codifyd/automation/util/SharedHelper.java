package com.codifyd.automation.util;

import java.util.Scanner;

import com.codifyd.automation.attribute.excel.AttributeXMLHandler;


public class SharedHelper {

	
	public static UserInputFileUtilDO getUserInput(){
		UserInputFileUtilDO userInputFileUtilDO = new UserInputFileUtilDO();
		Scanner scanner = new Scanner(System.in);
		System.out.print("Please enter the input file path : ");
		String inputFilePath = scanner.next();

		while (inputFilePath == null || inputFilePath.trim().isEmpty()) {
			System.out.println("You need to specify a path!");
			inputFilePath = scanner.next();
		}
		
		System.out.print("Please enter the output file path : ");
		String outFilePath = scanner.next();
		
		while (outFilePath == null || outFilePath.trim().isEmpty()) {
			System.out.println("You need to specify a path!");
			outFilePath = scanner.next();
		}
		
		System.out.print("Please enter the output Filename with extension(*.xml) : ");
		String outputFileName = scanner.next();
		while (outputFileName == null || outputFileName.trim().isEmpty()) {
			System.out.println("You need to specify a name!");
			outputFileName = scanner.next();
		}
		
		scanner.close();
		
		userInputFileUtilDO.setInputPath(inputFilePath);
		userInputFileUtilDO.setOutputPath(outFilePath);
		userInputFileUtilDO.setFilename(outputFileName);
		return userInputFileUtilDO;
	
	}
	
	public static void main(String[] args) {
		System.out.println("Excel to XML Converter");
		System.out.println("1. Attribute");
		System.out.println("2. LOV");
		System.out.println("3. UOM");
		System.out.println("4. Schema");
		System.out.println("5. Taxonomy");
		System.out.println("Please Select one of the above options :");
		
		Scanner scanner = new Scanner(System.in);
		String option = scanner.next();
		switch(option){
		case "1":
			AttributeXMLHandler attributeXMLHandler = new AttributeXMLHandler();
			attributeXMLHandler.handleFile(getUserInput());
			break;
			
		case "2":
//			LOVXMLHandler lovxmlHandler = new LOVXMLHandler();
//			lovxmlHandler.handleFile(getUserInput());
			break;
			
		case "3":
//			UOMXMLHandler uomxmlHandler = new UOMXMLHandler();
//			uomxmlHandler.handleFile(getUserInput());
			break;
		
		case "4":
//			SchemaXMLHandler schemaXMLHandler = new SchemaXMLHandler();
//			schemaXMLHandler.handleFile(getUserInput());
			break;
			
		case "5":
//			TaxonomyXMLHandler taxonomyXMLHandler = new TaxonomyXMLHandler();
//			taxonomyXMLHandler.handleFile(getUserInput());
			break;
			
		default:
			System.out.println("Invalid Option. Doing Nothing.");
			break;
		}
		
		scanner.close();
		
		
		//LOVXMLHandler.handleFile(getUserInput());
	}
}
