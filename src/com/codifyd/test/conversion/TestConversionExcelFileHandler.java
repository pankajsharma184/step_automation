package com.codifyd.test.conversion;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.codifyd.automation.stepconversion.attributelink.AttributeLinkExcelFileHandler;
import com.codifyd.automation.stepconversion.attributeschema.AttributeExcelFileHandler;
import com.codifyd.automation.stepconversion.lovschema.LovExcelFileHandler;
import com.codifyd.automation.stepconversion.uom.UomExcelFIleHandler;
import com.codifyd.automation.stepconversion.util.AutomationConstants;
import com.codifyd.automation.stepconversion.util.UserInputFileUtilDO;
import com.codifyd.test.util.UserInputUtil;

public class TestConversionExcelFileHandler {
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
				AttributeExcelFileHandler attributeExcelFileHandler = new AttributeExcelFileHandler();
				attributeExcelFileHandler
						.handleFile(UserInputUtil.getUserInput(AutomationConstants.ATTRIBUTE, defaultObject));
				break;

			case 2:
				AttributeLinkExcelFileHandler attributeLinkExcelFileHandler = new AttributeLinkExcelFileHandler();
				attributeLinkExcelFileHandler
						.handleFile(UserInputUtil.getUserInput(AutomationConstants.ATTRIBUTELINK, defaultObject));
				break;

			case 3:
				LovExcelFileHandler lovExcelFileHandler = new LovExcelFileHandler();
				lovExcelFileHandler.handleFile(UserInputUtil.getUserInput(AutomationConstants.LOV, defaultObject));
				break;

			case 4:
				UomExcelFIleHandler uomExcelFIleHandler = new UomExcelFIleHandler();
				uomExcelFIleHandler.handleFile(UserInputUtil.getUserInput(AutomationConstants.UOM, defaultObject));
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
		String inputFilePath = "C:\\Users\\Aks\\Downloads\\test\\test.xlsx";

		File tmpFile = new File(inputFilePath);
		String ouputDir = tmpFile.getParentFile().getAbsolutePath();

		String fileName = tmpFile.getName();
		int pos = fileName.lastIndexOf(".");
		if (pos > 0 && pos < (fileName.length() - 1)) {
			// If '.' is not the first or last character.
			fileName = fileName.substring(0, pos);
		}
		DateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");
		String outputFilename = "Converted_" + fileName + "_" + df.format(new Date()) + ".xml";

		defaultObject.setInputPath(inputFilePath);
		defaultObject.setOutputPath(ouputDir);
		defaultObject.setFilename(outputFilename);

		return defaultObject;
	}

}
