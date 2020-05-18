package com.codifyd.automation.util;

import java.io.File;
import java.util.Properties;

public class UserInputFileUtilDO {
	String inputPath;
	String outputPath;
	String filename;
	Properties propertiesFile;
	String delimeters;
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
	public String getDelimeters() {
		return delimeters;
	}
	public void setDelimeters(String delimeters) {
		this.delimeters = delimeters;
	}
	
	

}
