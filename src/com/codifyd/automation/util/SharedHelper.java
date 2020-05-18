package com.codifyd.automation.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import com.codifyd.automation.attribute.AttributeExcelHandler;
import com.codifyd.automation.attribute.AttributeXMLHandler;
import com.codifyd.automation.attributelink.AttributeLinkExcelHandler;
import com.codifyd.automation.attributelink.AttributeLinkXMLHandler;
import com.codifyd.automation.lov.LOVExcelHandler;
import com.codifyd.automation.lov.LovXMLHandler;
import com.codifyd.automation.uom.UOMExcelHandler;

public class SharedHelper {

	private static String ATTRIBUTE = "Attribute";
	private static String ATTRIBUTELINK = "Attributelink";
	private static String LOV = "LOV";
	private static String UOM = "UOM";
//	private static String SCHEMA = "Schema";
	private static String TAXONOMY = "Taxonomy";
	private static String EXCELToXML = "ExcelToXML";

	private Properties getFileFromResources(String fileName) {

		InputStream resource = getClass().getResourceAsStream(fileName);

		if (resource == null) {
			throw new IllegalArgumentException("file is not found!");
		} else {
			Properties prop = new Properties();
			try {
				prop.load(resource);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return prop;
		}

	}

	@SuppressWarnings("unused")
	public static UserInputFileUtilDO getUserInput(BufferedReader reader, String str) throws IOException {

		UserInputFileUtilDO userInputFileUtilDO = new UserInputFileUtilDO();
		System.out.print("Please enter the input file path : ");
		String inputFilePath = reader.readLine();
		while (inputFilePath == null || inputFilePath.trim().isEmpty()) {
			System.out.println("You need to specify a path!");
			inputFilePath = reader.readLine();
		}

		System.out.print("Please enter the output file path : ");
		String outFilePath = reader.readLine();
		while (outFilePath == null || outFilePath.trim().isEmpty()) {
			System.out.println("You need to specify a path!");
			outFilePath = reader.readLine();
		}

		System.out.print("Please enter the output Filename with extension : ");
		String outputFileName = reader.readLine();
		while (outputFileName == null || outputFileName.trim().isEmpty()) {
			System.out.println("You need to specify a name!");
			outputFileName = reader.readLine();
		}

		if (!str.equals(EXCELToXML)) {
			System.out.print("Please enter the config File path if any: ");
			String propertiesFile = reader.readLine();
			SharedHelper sh = new SharedHelper();
			File propsFile = new File(propertiesFile);
			Properties prop = new Properties();
			if (propertiesFile == null || propertiesFile.trim().isEmpty()) {
				// Properties file selection part
				if (str.equals(ATTRIBUTE)) {
					prop = sh.getFileFromResources("/resources/attribute-config.properties");
				} else if (str.equals(ATTRIBUTELINK)) {
					prop = sh.getFileFromResources("/resources/attributelink-config.properties");
				} else if (str.equals(LOV)) {
					prop = sh.getFileFromResources("/resources/lov-config.properties");
				} else if (str.equals(UOM)) {
					prop = sh.getFileFromResources("/resources/uom-config.properties");
				} else {
					prop = sh.getFileFromResources("/resources/taxonomy-config.properties");
				}
			} else {
				FileInputStream inputStream = new FileInputStream(propsFile);
				prop.load(inputStream);
			}
			userInputFileUtilDO.setPropertiesFile(prop);
		}

		System.out.print("Please enter the Multi Field Seperator (Can be only \",\" \";\" \"|\"): ");
		String delimeter = reader.readLine();
		while (!(delimeter.trim().equals(";") || delimeter.trim().equals(",") || delimeter.trim().equals("|")
				|| delimeter.trim().isEmpty())) {
			System.out.print("The Multi Field Seperator Can be only \",\" \";\" \"|\": ");
			delimeter = reader.readLine();
		}
		if (delimeter == null || delimeter.trim().isEmpty()) {
			delimeter = ";";
		}

		userInputFileUtilDO.setInputPath(inputFilePath.trim());
		userInputFileUtilDO.setOutputPath(outFilePath.trim());
		userInputFileUtilDO.setFilename(outputFileName.trim());
		userInputFileUtilDO.setDelimeters(delimeter.trim());
		return userInputFileUtilDO;

	}

	public static void main(String[] args) throws IOException {

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
				AttributeExcelHandler convertXMLToExcel = new AttributeExcelHandler();
				convertXMLToExcel.handleFile(getUserInput(reader, ATTRIBUTE));
				break;

			case "2":
				AttributeLinkExcelHandler attributeLinkExcel = new AttributeLinkExcelHandler();
				attributeLinkExcel.handleFile(getUserInput(reader, ATTRIBUTELINK));
				break;

			case "3":
				LOVExcelHandler convertXmltoExcellov = new LOVExcelHandler();
				convertXmltoExcellov.handleFile(getUserInput(reader, LOV));
				break;

			case "4":
				UOMExcelHandler uomxmlHandler = new UOMExcelHandler();
				uomxmlHandler.handleFile(getUserInput(reader, UOM));
				break;

			case "5":
				// SchemaXMLHandler schemaXMLHandler = new SchemaXMLHandler();
				// schemaXMLHandler.handleFile(getUserInput());
				break;

			case "6":
				// TaxonomyXMLHandler taxonomyXMLHandler = new TaxonomyXMLHandler();
				// taxonomyXMLHandler.handleFile(getUserInput());
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
				AttributeXMLHandler attributeXMLHandler = new AttributeXMLHandler();
				attributeXMLHandler.handleFile(getUserInput(reader, EXCELToXML));
				break;

			case "2":
				AttributeLinkXMLHandler attributeLinkXML = new AttributeLinkXMLHandler();
				attributeLinkXML.handleFile(getUserInput(reader, EXCELToXML));
				break;

			case "3":
				LovXMLHandler lovXMLHandler = new LovXMLHandler();
				lovXMLHandler.handleFile(getUserInput(reader, EXCELToXML));
				break;

			case "4":
				// UOMXMLHandler uomxmlHandler = new UOMXMLHandler();
				// uomxmlHandler.handleFile(getUserInput());
				break;

			case "5":
				// SchemaXMLHandler schemaXMLHandler = new SchemaXMLHandler();
				// schemaXMLHandler.handleFile(getUserInput());
				break;

			case "6":
				// TaxonomyXMLHandler taxonomyXMLHandler = new TaxonomyXMLHandler();
				// taxonomyXMLHandler.handleFile(getUserInput());
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
