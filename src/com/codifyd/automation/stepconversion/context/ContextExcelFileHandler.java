package com.codifyd.automation.stepconversion.context;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.codifyd.automation.stepconversion.util.FileConversionHandler;
import com.codifyd.automation.stepconversion.util.HandlerConstants;
import com.codifyd.automation.stepconversion.util.InputValidator;
import com.codifyd.automation.stepconversion.util.UserInputFileUtilDO;
import com.codifyd.stepxsd.ContextListType;
import com.codifyd.stepxsd.ContextType;
import com.codifyd.stepxsd.DimensionPointLinkType;
import com.codifyd.stepxsd.NameType;
import com.codifyd.stepxsd.ObjectFactory;
import com.codifyd.stepxsd.STEPProductInformation;

public class ContextExcelFileHandler implements FileConversionHandler {

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
			ArrayList<ContextExcelInfo> excelValues = new ArrayList<>();
			readExcel(new File(userInput.getInputPath()), excelValues);

			File outputFile = new File(
					Paths.get(new File(userInput.getOutputPath()).getPath(), userInput.getFilename()).toUri());

			// Initialize object factory and add unit values
			ObjectFactory objectFactory = new ObjectFactory();
			STEPProductInformation stepProductInformation = objectFactory.createSTEPProductInformation();
			stepProductInformation.setContextID(HandlerConstants.CONTEXT1);
			stepProductInformation.setWorkspaceID(HandlerConstants.MAIN);

			ContextListType contextListType = objectFactory.createContextListType();

			for (ContextExcelInfo contextInfo : excelValues) {
				if (!isNullOrBlank(contextInfo.getContextID())) {
					ContextType contextType = objectFactory.createContextType();
					// set id
					contextType.setID(contextInfo.getContextID());
					// set name
					NameType nameType = objectFactory.createNameType();
					nameType.setContent(contextInfo.getContextName());
					contextType.getName().add(nameType);
					// link
					DimensionPointLinkType langDimensionPointLinkType = objectFactory.createDimensionPointLinkType();
					langDimensionPointLinkType.setDimensionPointID(contextInfo.getLanguageID());
					contextType.getDimensionPointLink().add(langDimensionPointLinkType);

					DimensionPointLinkType countryDimensionPointLinkType = objectFactory.createDimensionPointLinkType();
					countryDimensionPointLinkType.setDimensionPointID(contextInfo.getCountryID());
					contextType.getDimensionPointLink().add(countryDimensionPointLinkType);

					contextListType.getContext().add(contextType);
				}
			}

			stepProductInformation.setContextList(contextListType);

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

	private void readExcel(File inputFile, ArrayList<ContextExcelInfo> excelValues) throws Exception {
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
					ContextExcelInfo contextExcelInfo = new ContextExcelInfo();
					for (Iterator<Cell> iterator2 = row.iterator(); iterator2.hasNext();) {
						Cell cell = iterator2.next();

						if (cell.getColumnIndex() == columnHeader.indexOf("CONTEXT_ID")) {
							String cellValue = null;
							if(cell.getCellTypeEnum()==CellType.STRING){			
								cellValue = df.formatCellValue(cell);
							}	else if(cell.getCellTypeEnum()==CellType.FORMULA){	
								switch(cell.getCachedFormulaResultType()) {
								case Cell.CELL_TYPE_NUMERIC:
									cellValue = String.valueOf(cell.getNumericCellValue());
									break;
								case Cell.CELL_TYPE_STRING:
									cellValue = String.valueOf(cell.getRichStringCellValue());              
									break;
								}
							}
							
							contextExcelInfo.setContextID(cellValue);

						} else if (cell.getColumnIndex() == columnHeader.indexOf("CONTEXT_NAME")) {
							contextExcelInfo.setContextName(df.formatCellValue(cell));

						} else if (cell.getColumnIndex() == columnHeader.indexOf("COUNTRY_ID")) {
							contextExcelInfo.setCountryID(df.formatCellValue(cell));

						} else if (cell.getColumnIndex() == columnHeader.indexOf("LANGUAGE_ID")) {
							contextExcelInfo.setLanguageID(df.formatCellValue(cell));

						}

					}
					excelValues.add(contextExcelInfo);
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
