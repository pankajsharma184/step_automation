package com.codifyd.automation.stepconversion.util;

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
	private ConfigHandler configFile;
	private String delimiters;
	private Properties propertiesFile;

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

	public ConfigHandler getConfigFile() {
		return configFile;
	}

	public void setConfigFile(ConfigHandler configFile) {
		this.configFile = configFile;
	}

	/**
	 * @throws InvalidFileException
	 * 
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @set propertiesFile
	 */
	public void setConfigFile(String propertiesFile, String str) throws FileNotFoundException, IOException {
		ClassLoader classLoader = getClass().getClassLoader();
		ConfigHandler config = new ConfigHandler();
		if (propertiesFile != null && !"".equals(propertiesFile.trim())) {
			config.load(new FileInputStream(propertiesFile));

		} else {
			InputStream inStream = null;
			if (str.equals(AutomationConstants.ATTRIBUTE)) {
				inStream = classLoader.getResourceAsStream("resources/Attribute-Config.properties");
			} else if (str.equals(AutomationConstants.ATTRIBUTELINK)) {
				inStream = classLoader.getResourceAsStream("resources/AttributeLink-Config.properties");
			} else if (str.equals(AutomationConstants.LOV)) {
				inStream = classLoader.getResourceAsStream("resources/LOV-Config.properties");
			} else if (str.equals(AutomationConstants.UOM)) {
				inStream = classLoader.getResourceAsStream("resources/UOM-Config.properties");
			} else if (str.equals(AutomationConstants.TAXONOMY)) {
				inStream = classLoader.getResourceAsStream("resources/Taxonomy-Config.properties");
			} else if (str.equals(AutomationConstants.MIL_CONFIG)) {
				inStream = classLoader.getResourceAsStream("resources/stepTomilconversion-config.properties");
			}

			if (inStream != null) {
				config.load(inStream);
			}
		}
		if (config != null) {
			this.configFile = config;
		}
	}

	public String getDelimiters() {
		return delimiters;
	}

	public void setDelimeters(String delimeters) {
		if (delimeters != null && delimeters.trim().equals("")) {
			if (delimeters.trim().matches(";|,|\\|")) {
				this.delimiters = delimeters.trim();
			} else {
				System.out.println("Invalid Delimeter : Setting Default Delimeter \';\'");
				this.delimiters = ";";
			}
		} else {
			this.delimiters = ";";
		}
	}

	/**
	 * @return the properties
	 */
	public Properties getPropertiesFile() {
		return propertiesFile;
	}

	/**
	 * @param properties the properties to set
	 */
	public void setPropertiesFile(Properties properties) {
		this.propertiesFile = properties;
	}

	/**
	 * @throws InvalidFileException
	 * 
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @set propertiesFile
	 */
	public void setPropertiesFile(String properties, String str) throws FileNotFoundException, IOException {
		ClassLoader classLoader = getClass().getClassLoader();
		Properties property = new Properties();
		if (properties != null && !"".equals(properties.trim())) {
			property.load(new FileInputStream(properties));

		} else {
			InputStream inStream = null;
			if (str.equals(AutomationConstants.ATTRIBUTE)) {
				inStream = classLoader.getResourceAsStream("resources/Attribute-Config.properties");
			} else if (str.equals(AutomationConstants.ATTRIBUTELINK)) {
				inStream = classLoader.getResourceAsStream("resources/AttributeLink-Config.properties");
			} else if (str.equals(AutomationConstants.LOV)) {
				inStream = classLoader.getResourceAsStream("resources/LOV-Config.properties");
			} else if (str.equals(AutomationConstants.UOM)) {
				inStream = classLoader.getResourceAsStream("resources/UOM-Config.properties");
			} else if (str.equals(AutomationConstants.TAXONOMY)) {
				inStream = classLoader.getResourceAsStream("resources/Taxonomy-Config.properties");
			}

			if (inStream != null) {
				property.load(inStream);
			}
		}
		if (property != null) {
			this.propertiesFile = property;
		}
	}

}
