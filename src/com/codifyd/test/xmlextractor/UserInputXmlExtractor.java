package com.codifyd.test.xmlextractor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Paths;

import com.codifyd.automation.xmlextractor.StepDataXMLHandler;
import com.codifyd.automation.xmlextractor.XMLExtractorInputUtil;

public class UserInputXmlExtractor {

	public static void main(String[] args) throws Exception {
		try {
			InputStreamReader inStream = new InputStreamReader(System.in);
			BufferedReader reader = new BufferedReader(inStream);

			XMLExtractorInputUtil userInput = new XMLExtractorInputUtil();

			print();
			System.out.println("Please Select an Option: ");
			String option = reader.readLine();

			System.out.println("Please enter inputFile: ");
			String inputFile = reader.readLine();
			if (inputFile == null || inputFile.trim().isEmpty()) {
				inputFile = Paths.get(System.getProperty("user.home"), "Desktop", "test", "test.xml").toString();
			}
			userInput.setInputFilePath(inputFile);

			System.out.println("Enter List of IDs: ");
			String idString = reader.readLine();

			switch (Integer.parseInt(option)) {

			case 1:
				userInput.setBusinessRuleIDs(idString);
				break;

			case 2:
				userInput.setBusinessLibraryIDs(idString);
				break;

			case 3:
				userInput.setAttributeIDs(idString);
				break;

			case 4:
				userInput.setAttributeGroupIDs(idString);
				break;

			case 5:
				userInput.setLOVIDs(idString);
				break;

			case 6:
				userInput.setLOVGroupIDs(idString);
				break;

			case 7:
				userInput.setUserIDs(idString);
				break;

			case 8:
				userInput.setUserGroupIDs(idString);
				break;

			case 9:
				userInput.setActionSetIDs(idString);
				break;

			case 10:
				userInput.setKeyIDs(idString);
				break;

			case 11:
				userInput.setIntegrationEndpointIDs(idString);
				break;

			case 12:
				userInput.setImportConfigurationIDs(idString);
				break;

			case 13:
				userInput.setExportConfigurationIDs(idString);
				break;

			case 14:
				userInput.setEventQueueIDs(idString);
				break;

			case 15:
				userInput.setEventProcessorIDs(idString);
				break;

			default:
				System.out.println("Invalid Input");
				break;
			}

			StepDataXMLHandler xmlHandler = new StepDataXMLHandler();
			xmlHandler.handleFile(userInput);

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}

	private static void print() {
		System.out.println("1. BusinessRule");
		System.out.println("2. BusinessLibrary");
		System.out.println("3. Attribute");
		System.out.println("4. AttributeGroup");
		System.out.println("5. LOV");
		System.out.println("6. LOVGroup");
		System.out.println("7. User");
		System.out.println("8. UserGroup");
		System.out.println("9. ActionSetIDs");
		System.out.println("10. Key");
		System.out.println("11. IntegrationEndpoint");
		System.out.println("12. ImportConfiguration");
		System.out.println("13. ExportConfiguration");
		System.out.println("14. EventQueue");
		System.out.println("15. EventProcessor");
	}
}
