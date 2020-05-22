package com.codifyd.automation.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

//pojo class for user input, will only set defaults
public class UserInputFileUtilDO {

	private String inputPath;
	private String outputPath;
	private String filename;
	private Properties propertiesFile;
	private String delimeters;
	
	public String getInputPath() {
		return inputPath;
	}
	public void setInputPath(String inputPath) {
		this.inputPath = inputPath;
	}

	public String getOutputPath() {
		return outputPath;
	}
	public void setOutputPath(String outputPath) {
		this.outputPath = outputPath;
	}

	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}

	public Properties getPropertiesFile() {
		return propertiesFile;
	}
	public void setPropertiesFile(Properties propertiesFile) {
		this.propertiesFile = propertiesFile;
	}

	/**
	 * @throws InvalidFileException
	 * 
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @set propertiesFile
	 */
	public void setPropertiesFile(String propertiesFile, String str)
			throws FileNotFoundException, IOException {
		ClassLoader classLoader = this.getClass().getClassLoader();
		Properties properties = new Properties();
		if (propertiesFile != null && !"".equals(propertiesFile.trim())) {
			properties.load(new FileInputStream(propertiesFile));
				
		} else {
			InputStream inStream = null;
			if (str.equals(AutomationConstants.ATTRIBUTE)) {
				inStream = classLoader.getResourceAsStream("resources/Attribute-Config.properties");
//					propertiesFile = new File("src\\resources\\attribute-config.properties").getAbsolutePath();
			} else if (str.equals(AutomationConstants.ATTRIBUTELINK)) {
				inStream = classLoader.getResourceAsStream("resources/AttributeLink-Config.properties");
//					propertiesFile = new File("src\\resources\\attributelink-config.properties").getAbsolutePath();
			} else if (str.equals(AutomationConstants.LOV)) {
				inStream = classLoader.getResourceAsStream("resources/LOV-Config.properties");
//					propertiesFile = new File("src\\resources\\lov-Config.properties").getAbsolutePath();
			} else if (str.equals(AutomationConstants.UOM)) {
				inStream = classLoader.getResourceAsStream("resources/UOM-Config.properties");
//					propertiesFile = new File("src\\resources\\uom-config.properties").getAbsolutePath();
			} else if (str.equals(AutomationConstants.TAXONOMY)) {
				inStream = classLoader.getResourceAsStream("resources/Taxonomy-Config.properties");
//					propertiesFile = new File("src\\resources\\taxonomy-config.properties").getAbsolutePath();
			}

			if (inStream != null) {
				properties.load(inStream);
//					this.propertiesFile.load(inStream);
			}
//				
			this.propertiesFile = properties;
		}
	}

	

	public String getDelimeters() {
		return delimeters;
	}
	
	public void setDelimeters(String delimeters) {
		if (delimeters != null && delimeters.trim().equals("")) {
			if (delimeters.trim() == ";" || delimeters.trim() == "," || delimeters.trim() == "\\|") {
				this.delimeters = delimeters.trim();
			} else {
				System.out.println("Invalid Delimeter : Setting Default Delimeter \';\'");
				this.delimeters = ";";
			}
		} else {
			this.delimeters = ";";
		}
	}

	

}
