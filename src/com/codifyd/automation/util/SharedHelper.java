package com.codifyd.automation.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.codifyd.automation.attribute.AttributeExcelHandler;
import com.codifyd.automation.attribute.AttributeXMLHandler;
import com.codifyd.automation.attributelink.AttributeLinkExcelHandler;
import com.codifyd.automation.attributelink.AttributeLinkXMLHandler;
import com.codifyd.automation.lov.LOVExcelHandler;
import com.codifyd.automation.lov.LovXMLHandler;
import com.codifyd.automation.uom.UomExcelHandler;
import com.codifyd.automation.uom.UomXMLHandler;

public class SharedHelper {

	private static String ATTRIBUTE = "Attribute";
	private static String ATTRIBUTELINK = "Attributelink";
	private static String LOV = "LOV";
	private static String UOM = "UOM";
	private static String TAXONOMY = "Taxonomy";
	private static String EXCELToXML = "ExcelToXML";
	private static String XMLToEXCEL = "XMLToExcel";

//	@SuppressWarnings("unused")
	public static UserInputFileUtilDO getUserInput(BufferedReader reader, String str, String str2) throws IOException {
		UserInputFileUtilDO userInputFileUtilDO = new UserInputFileUtilDO();
		try {
			System.out.print("Please enter the input file path : ");
			String inputFilePath = reader.readLine();
			while (inputFilePath == null || inputFilePath.trim().isEmpty()) {
				System.out.println("You need to specify a path!");
				inputFilePath = reader.readLine();
			}

			userInputFileUtilDO.setInputPath(inputFilePath.trim(), str2);

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
			userInputFileUtilDO.setFilename(outputFileName.trim(), str2);

			if (!str.equals(EXCELToXML)) {
				System.out.print("Please enter the config File path if any: ");
				String propertiesFile = reader.readLine();
				/*
				 * if (propertiesFile == null || ((String) propertiesFile).trim().isEmpty()) {
				 * // Properties file selection part if (str.equals(ATTRIBUTE)) { propertiesFile
				 * = ("resources/Attribute-Config.properties"); } else if
				 * (str.equals(ATTRIBUTELINK)) { propertiesFile =
				 * ("resources/AttributeLink-Config.properties"); } else if (str.equals(LOV)) {
				 * propertiesFile = ("resources/LOV-Config.properties"); } else if
				 * (str.equals(UOM)) { propertiesFile = ("resources/UOM-Config.properties"); }
				 * else if (str.equals(TAXONOMY)) { propertiesFile =
				 * ("resources/Taxonomy-Config.properties"); } }
				 */
				userInputFileUtilDO.setPropertiesFile(propertiesFile, str);

				System.out.print("Please enter the Multi Field Seperator (Can be only \",\" \";\" \"|\"): ");
				String delimeter = reader.readLine();
				while (!delimeter.trim().matches(";|,|\\|") && !delimeter.trim().equals("") && null != delimeter) {
					System.out.print("The Multi Field Seperator Can be only \",\" \";\" \"|\": ");
					delimeter = reader.readLine();
				}
				userInputFileUtilDO.setDelimeters(delimeter.trim());
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
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
				convertXMLToExcel.handleFile(getUserInput(reader, ATTRIBUTE, XMLToEXCEL));
				break;

			case "2":
				AttributeLinkExcelHandler attributeLinkExcel = new AttributeLinkExcelHandler();
				attributeLinkExcel.handleFile(getUserInput(reader, ATTRIBUTELINK, XMLToEXCEL));
				break;

			case "3":
				LOVExcelHandler convertXmltoExcellov = new LOVExcelHandler();
				convertXmltoExcellov.handleFile(getUserInput(reader, LOV, XMLToEXCEL));
				break;

			case "4":
				UomExcelHandler uomxmlHandler = new UomExcelHandler();
				uomxmlHandler.handleFile(getUserInput(reader, UOM, XMLToEXCEL));
				break;

			case "5":
				// TaxonomyXMLHandler taxonomyXMLHandler = new TaxonomyXMLHandler();
				// taxonomyXMLHandler.handleFile(getUserInput(reader, TAXONOMY));
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
				attributeXMLHandler.handleFile(getUserInput(reader, ATTRIBUTE, EXCELToXML));
				break;

			case "2":
				AttributeLinkXMLHandler attributeLinkXML = new AttributeLinkXMLHandler();
				attributeLinkXML.handleFile(getUserInput(reader, ATTRIBUTE, EXCELToXML));
				break;

			case "3":
				LovXMLHandler lovXMLHandler = new LovXMLHandler();
				lovXMLHandler.handleFile(getUserInput(reader, ATTRIBUTE, EXCELToXML));
				break;

			case "4":
				UomXMLHandler uomxmlHandler = new UomXMLHandler();
				uomxmlHandler.handleFile(getUserInput(reader, ATTRIBUTE, EXCELToXML));
				break;

			case "5":
//				TaxonomyXMLHandler taxonomyXMLHandler = new TaxonomyXMLHandler();
//				taxonomyXMLHandler.handleFile(getUserInput(reader, ATTRIBUTE, EXCELToXML));
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
