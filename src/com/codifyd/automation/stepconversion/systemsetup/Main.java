package com.codifyd.automation.stepconversion.systemsetup;

import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.codifyd.automation.stepconversion.util.UserInputFileUtilDO;

public class Main {

	public static void main(String[] args) {
		try {
			UserInputFileUtilDO userInput = new UserInputFileUtilDO();

			String dirPath = Paths.get(System.getProperty("user.home"), "Downloads", "stepConversion").toString();

			DateFormat date = new SimpleDateFormat("yyMMddhhmmss");

			String configFile = Paths.get(dirPath, "objectTypeSchema-Config.properties").toString();
			userInput.setConfigFile(configFile, null);

			String inputPath = Paths.get(dirPath, "objecttype.xlsx").toString();
			userInput.setInputPath(inputPath);

			userInput.setOutputPath(dirPath.toString());

			String filename = "Converted_" + date.format(new Date()) + "_.xml";
			userInput.setFilename(filename);

			ObjectTypeSchemaExcelFileHandler handler = new ObjectTypeSchemaExcelFileHandler();
			handler.handleFile(userInput);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
