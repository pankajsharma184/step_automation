package com.codifyd.automation.stepconversion.context;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.codifyd.automation.stepconversion.util.FileConversionHandler;
import com.codifyd.automation.stepconversion.util.HandlerConstants;
import com.codifyd.automation.stepconversion.util.InputValidator;
import com.codifyd.automation.stepconversion.util.UserInputFileUtilDO;
import com.codifyd.stepxsd.DimensionListType;
import com.codifyd.stepxsd.DimensionPointLinkType;
import com.codifyd.stepxsd.DimensionPointType;
import com.codifyd.stepxsd.DimensionType;
import com.codifyd.stepxsd.NameType;
import com.codifyd.stepxsd.ObjectFactory;
import com.codifyd.stepxsd.STEPProductInformation;

public class DimensionExcelFileHandler implements FileConversionHandler {

	@Override
	public void handleFile(UserInputFileUtilDO userInput) throws Exception {

		try {
			// parse the input for errors
			InputValidator.validateExcelToXML(userInput);

			// ConfigHandler configFile = userInput.getConfigFile();
			// List<String> headerList = new ArrayList<String>();
			// for (String key : configFile.keySet())
			// headerList.add(key);

			// Read the Excel
			Map<String, String> languageValues = new HashMap<>();
			readExcelLanguageSheet(new File(userInput.getInputPath()), languageValues);
			Map<String, String> countryValues = new HashMap<>();
			readExcelCountrySheet(new File(userInput.getInputPath()), countryValues);
			
			File outputFile = new File(
					Paths.get(new File(userInput.getOutputPath()).getPath(), userInput.getFilename()).toUri());

			// Initialize object factory and add unit values
			ObjectFactory objectFactory = new ObjectFactory();
			STEPProductInformation stepProductInformation = objectFactory.createSTEPProductInformation();
			stepProductInformation.setContextID(HandlerConstants.CONTEXT1);
			stepProductInformation.setWorkspaceID(HandlerConstants.MAIN);

			DimensionListType dimensionListType = objectFactory.createDimensionListType();

			// get language dimension
			DimensionType dimensionType = objectFactory.createDimensionType();
			dimensionType.setID("Language");

			DimensionPointLinkType dimensionPointLinkType = objectFactory.createDimensionPointLinkType();
			dimensionPointLinkType.setDimensionPointID("std.lang.all");

			for (Entry<String, String> dimensionInfo : languageValues.entrySet()) {
				if (!isNullOrBlank(dimensionInfo.getKey())) {
					DimensionPointType dimensionPointType = objectFactory.createDimensionPointType();
					// set id
					dimensionPointType.setID(dimensionInfo.getKey());
					// set name
					NameType nameType = objectFactory.createNameType();
					nameType.setContent(dimensionInfo.getValue());
					dimensionPointType.getName().add(nameType);
					// link
					dimensionPointType.setDimensionPointLink(dimensionPointLinkType);
					dimensionType.getDimensionPoint().add(dimensionPointType);
				}
			}
			dimensionListType.getDimension().add(dimensionType);

			// get country dimension
			DimensionType countryDimensionType = objectFactory.createDimensionType();
			countryDimensionType.setID("Country");

			DimensionPointLinkType countryDimensionPointLinkType = objectFactory.createDimensionPointLinkType();
			countryDimensionPointLinkType.setDimensionPointID("AllCountries");

			for (Entry<String, String> dimensionInfo : countryValues.entrySet()) {
				if (!isNullOrBlank(dimensionInfo.getKey())) {
					DimensionPointType dimensionPointType = objectFactory.createDimensionPointType();
					// set id
					dimensionPointType.setID(dimensionInfo.getKey());
					// set name
					NameType nameType = objectFactory.createNameType();
					nameType.setContent(dimensionInfo.getValue());
					dimensionPointType.getName().add(nameType);
					// link
					dimensionPointType.setDimensionPointLink(countryDimensionPointLinkType);
					countryDimensionType.getDimensionPoint().add(dimensionPointType);
				}
			}
			dimensionListType.getDimension().add(countryDimensionType);

			stepProductInformation.setDimensionList(dimensionListType);

			JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(stepProductInformation, outputFile);

			System.out.println("File Generated in path : " + outputFile.getAbsolutePath());

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}

	private void readExcelCountrySheet(File inputFile, Map<String, String> countryData) throws Exception {
		try {
			List<String> columnHeader = null;
			InputStream fs = new FileInputStream(inputFile);

			// Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(fs);

			// Get first/desired sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(1);

			for (Iterator<Row> iterator = sheet.iterator(); iterator.hasNext();) {
				Row row = iterator.next();

				if (row.getRowNum() == 0) {
					columnHeader = new ArrayList<String>();
					for (Iterator<Cell> iterator2 = row.iterator(); iterator2.hasNext();) {
						Cell cell = iterator2.next();
						columnHeader.add(cell.getStringCellValue());
					}
				}

				DataFormatter df = new DataFormatter();
				if (row.getRowNum() > 0) {
					String id = null;
					String name = null;
					for (Iterator<Cell> iterator2 = row.iterator(); iterator2.hasNext();) {
						Cell cell = iterator2.next();

						if (cell.getColumnIndex() == columnHeader.indexOf("CountryID")) {
							id = (df.formatCellValue(cell));

						} else if (cell.getColumnIndex() == columnHeader.indexOf("CountryName")) {
							name = (df.formatCellValue(cell));

						}
					}
					countryData.put(id, name);
				}
			}
			workbook.close();
			fs.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}

	private void readExcelLanguageSheet(File inputFile, Map<String, String> languageData) throws Exception {
		try {
			List<String> columnHeader = null;
			InputStream fs = new FileInputStream(inputFile);

			// Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(fs);

			// Get first/desired sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(0);

			for (Iterator<Row> iterator = sheet.iterator(); iterator.hasNext();) {
				Row row = iterator.next();

				if (row.getRowNum() == 0) {
					columnHeader = new ArrayList<String>();
					for (Iterator<Cell> iterator2 = row.iterator(); iterator2.hasNext();) {
						Cell cell = iterator2.next();
						columnHeader.add(cell.getStringCellValue());
					}
				}

				DataFormatter df = new DataFormatter();
				if (row.getRowNum() > 0) {
					String id = null;
					String name = null;
					for (Iterator<Cell> iterator2 = row.iterator(); iterator2.hasNext();) {
						Cell cell = iterator2.next();

						if (cell.getColumnIndex() == columnHeader.indexOf("LanguageID")) {
							id = (df.formatCellValue(cell));

						} else if (cell.getColumnIndex() == columnHeader.indexOf("LanguageName")) {
							name = (df.formatCellValue(cell));

						}
					}
					languageData.put(id, name);
				}
			}
			workbook.close();
			fs.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}

	public static boolean isNullOrBlank(String param) {
		if (param == null || param.trim().length() == 0) {
			return true;
		}
		return false;
	}
}
