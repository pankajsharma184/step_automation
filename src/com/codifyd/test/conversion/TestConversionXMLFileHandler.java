package com.codifyd.test.conversion;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.codifyd.automation.stepconversion.attributelink.AttributeLinkXMLFileHandler;
import com.codifyd.automation.stepconversion.attributeschema.AttributeXMLFileHandler;
import com.codifyd.automation.stepconversion.lovschema.LovXMLFileHandler;
import com.codifyd.automation.stepconversion.uom.UomXMLFileHandler;
import com.codifyd.automation.stepconversion.util.UserInputFileUtilDO;
import com.codifyd.automation.util.AutomationConstants;
import com.codifyd.test.util.UserInputUtil;

public class TestConversionXMLFileHandler {

	public static void main(String[] args) {

		UserInputFileUtilDO defaultObject = getDefaultUserInputDO();

		Map<Integer, String> options = new HashMap<>();
		options.put(1, "AttributeSchema");
		options.put(2, "AttributeLinkSchema");
		options.put(3, "LOV");
		options.put(4, "UOM");

		try {
			Integer choice = UserInputUtil.getUserSelectedOptionFromMap(options);
			switch (choice) {
			case 1:
				AttributeXMLFileHandler attributeXMLFileHandler = new AttributeXMLFileHandler();
				attributeXMLFileHandler
						.handleFile(UserInputUtil.getUserInput(AutomationConstants.ATTRIBUTE, defaultObject));
				break;

			case 2:
				AttributeLinkXMLFileHandler attributeLinkXMLFileHandler = new AttributeLinkXMLFileHandler();
				attributeLinkXMLFileHandler
						.handleFile(UserInputUtil.getUserInput(AutomationConstants.ATTRIBUTELINK, defaultObject));
				break;

			case 3:
				LovXMLFileHandler lovXMLFileHandler = new LovXMLFileHandler();
				lovXMLFileHandler.handleFile(UserInputUtil.getUserInput(AutomationConstants.LOV, defaultObject));
				break;

			case 4:
				UomXMLFileHandler uomXMLFIleHandler = new UomXMLFileHandler();
				uomXMLFIleHandler.handleFile(UserInputUtil.getUserInput(AutomationConstants.UOM, defaultObject));
				break;
			default:
				break;

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	static UserInputFileUtilDO getDefaultUserInputDO() {
		UserInputFileUtilDO defaultObject = new UserInputFileUtilDO();

		String inputFilePath = Paths.get(System.getProperty("user.home"), "Desktop", "test", "test.xml").toString();

		File tmpFile = new File(inputFilePath);
		String ouputDir = tmpFile.getParentFile().getAbsolutePath();

		String fileName = tmpFile.getName();
		if (!fileName.trim().startsWith("\\.")) {
			fileName = fileName.split("\\.")[0];
		} else {
			fileName = fileName.split("\\.")[1];
		}
//		int pos = fileName.lastIndexOf(".");
//		if (pos > 0 && pos < (fileName.length() - 1)) {
//			// If '.' is not the first or last character.
//			fileName = fileName.substring(0, pos);
//		}
		DateFormat df = new SimpleDateFormat("yyyyMMdd_hhmmss");
		String outputFilename = "Converted_" + fileName + "_" + df.format(new Date()) + ".xlsx";

		defaultObject.setInputPath(inputFilePath);
		defaultObject.setOutputPath(ouputDir);
		defaultObject.setFilename(outputFilename);

		return defaultObject;
	}
}
