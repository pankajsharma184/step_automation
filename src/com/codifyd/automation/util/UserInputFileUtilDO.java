package com.codifyd.automation.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.codifyd.automation.util.MyExceptions.InvalidDirectoryException;
import com.codifyd.automation.util.MyExceptions.InvalidFileException;
import com.codifyd.automation.util.MyExceptions.NullValueException;

public class UserInputFileUtilDO {
	private static String ATTRIBUTE = "Attribute";
	private static String ATTRIBUTELINK = "Attributelink";
	private static String LOV = "LOV";
	private static String UOM = "UOM";
	private static String TAXONOMY = "Taxonomy";
	private static String EXCELToXML = "ExcelToXML";

	static MyExceptions exceptions = new MyExceptions();

	String inputPath;
	String outputPath;
	String filename;
	Properties propertiesFile;
	String delimeters;

	/**
	 * @return inputPath
	 */
	public String getInputPath() {
		return inputPath;
	}

	/**
	 * @throws NullValueException
	 * @throws InvalidFileException
	 * @set inputPath
	 */
	public void setInputPath(String inputPath, String str) throws NullValueException, InvalidFileException {
		if (inputPath != null && !"".equals(inputPath.trim())) {
			if (new File(inputPath).isFile()) {
				if (str.equals(EXCELToXML) && inputPath.trim().endsWith(".xlsx")) {
					this.inputPath = inputPath;
				} else if (!str.equals(EXCELToXML) && inputPath.trim().endsWith(".xml")) {
					this.inputPath = inputPath;
				} else {
					throw exceptions.new InvalidFileException("Invalid File");
				}
			} else {
				throw exceptions.new InvalidFileException("File Not Found");
			}
		} else {
			throw exceptions.new NullValueException("Field Cannot Be Empty");
		}

	}

	/**
	 * @return outputPath
	 */
	public String getOutputPath() {
		return outputPath;
	}

	/**
	 * @throws NullValueException, InvalidDirectoryException
	 * @set outputPath
	 */
	public void setOutputPath(String outputPath) throws NullValueException, InvalidDirectoryException {
		if (outputPath != null && !"".equals(outputPath.trim())) {
			if (new File(outputPath).isDirectory()) {
				this.outputPath = outputPath;
			} else {
				throw exceptions.new InvalidDirectoryException("Directory Not Found");
			}
		} else {
			throw exceptions.new NullValueException("Field Cannot Be Empty");
		}

	}

	/**
	 * @return filename
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * @throws InvalidFileException
	 * @throws NullValueException
	 * @set filename
	 */
	public void setFilename(String filename, String str) throws InvalidFileException, NullValueException {
		if (filename != null && !"".equals(filename.trim())) {
			if ((str != null && str.equals(EXCELToXML)) && filename.trim().endsWith(".xml")) {
				this.filename = filename.trim();
			} else if ((str == null || !str.equals(EXCELToXML)) && filename.trim().endsWith(".xlsx")) {
				this.filename = filename.trim();
			} else {
				throw exceptions.new InvalidFileException("Invalid File Name");
			}
		} else {
			throw exceptions.new NullValueException("Field Cannot Be Empty");
		}

	}

	/**
	 * @return propertiesFile
	 */
	public Properties getPropertiesFile() {
		return propertiesFile;
	}

	/**
	 * @throws InvalidFileException
	 * @set propertiesFile
	 */
	@SuppressWarnings("resource")
	public void setPropertiesFile(String propertiesFile, String str) throws InvalidFileException {
		Properties properties = new Properties();
		try {
			if (propertiesFile != null && !"".equals(propertiesFile.trim())) {
				if (new File(propertiesFile.trim()).isFile()) {
					if (propertiesFile.trim().endsWith(".properties") || propertiesFile.trim().endsWith(".txt")) {
						properties.load(new FileInputStream(propertiesFile));
						this.propertiesFile = properties;
					} else {
						throw exceptions.new InvalidFileException("Invalid Properties File");
					}
				} else {
					throw exceptions.new InvalidFileException("File Not Found");
				}
			} else {
				if (str.equals(ATTRIBUTE)) {
					propertiesFile = new File("src\\resources\\attribute-config.properties").getAbsolutePath();
				} else if (str.equals(ATTRIBUTELINK)) {
					propertiesFile = new File("src\\resources\\attributelink-config.properties").getAbsolutePath();
				} else if (str.equals(LOV)) {
					propertiesFile = new File("src\\resources\\lov-Config.properties").getAbsolutePath();
				} else if (str.equals(UOM)) {
					propertiesFile = new File("src\\resources\\uom-config.properties").getAbsolutePath();
				} else if (str.equals(TAXONOMY)) {
					propertiesFile = new File("src\\resources\\taxonomy-config.properties").getAbsolutePath();
				}

				properties.load(new FileInputStream(propertiesFile));
				this.propertiesFile = properties;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return delemeters
	 */
	public String getDelimeters() {
		return delimeters;
	}

	/**
	 * @set delemeters
	 */
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
