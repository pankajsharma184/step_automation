package com.codifyd.test.conversion;

import java.io.File;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.codifyd.automation.stepconversion.mil.PIMDataXMLHandler;
import com.codifyd.automation.stepconversion.util.AutomationConstants;
import com.codifyd.automation.stepconversion.util.UserInputFileUtilDO;
import com.codifyd.test.util.UserInputUtil;

public class TestMilConversionXMLFileHandler {

	public static void main(String[] args) {

		UserInputFileUtilDO defaultObject = getDefaultUserInputDO();

		try {

			PIMDataXMLHandler pimDataXMLHandler = new PIMDataXMLHandler();
			pimDataXMLHandler.handleFile(UserInputUtil.getUserInput(AutomationConstants.MIL_CONFIG, defaultObject),
					false);

		} catch (Exception e) {
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
		// int pos = fileName.lastIndexOf(".");
		// if (pos > 0 && pos < (fileName.length() - 1)) {
		// // If '.' is not the first or last character.
		// fileName = fileName.substring(0, pos);
		// }
		DateFormat df = new SimpleDateFormat("yyyyMMdd_hhmmss");
		String outputFilename = "Converted_" + fileName + "_" + df.format(new Date()) + ".xml";

		defaultObject.setInputPath(inputFilePath);
		defaultObject.setOutputPath(ouputDir);
		defaultObject.setFilename(outputFilename);

		return defaultObject;
	}
}
