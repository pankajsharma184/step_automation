package com.codifyd.automation.stepconversion.systemsetup;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.codifyd.automation.stepconversion.util.ConfigHandler;
import com.codifyd.automation.stepconversion.util.ExcelWorkbookUtility;
import com.codifyd.automation.stepconversion.util.FileConversionHandler;
import com.codifyd.automation.stepconversion.util.HandlerConstants;
import com.codifyd.automation.stepconversion.util.InputValidator;
import com.codifyd.automation.stepconversion.util.UserInputFileUtilDO;
import com.codifyd.stepxsd.NameType;
import com.codifyd.stepxsd.ObjectFactory;
import com.codifyd.stepxsd.STEPProductInformation;
import com.codifyd.stepxsd.UserTypeLinkType;
import com.codifyd.stepxsd.UserTypeType;
import com.codifyd.stepxsd.UserTypesType;

public class ObjectTypeSchemaExcelFileHandler implements FileConversionHandler {

	@Override
	public void handleFile(UserInputFileUtilDO userInputFileUtilDO) throws Exception {
		try {

			// parse the input for errors
			InputValidator.validateExcelToXML(userInputFileUtilDO);

			ConfigHandler configFile = userInputFileUtilDO.getConfigFile();
			List<String> headerList = new ArrayList<String>();
			for (String key : configFile.keySet()) {
				headerList.add(key);
			}

			File inputFile = new File(userInputFileUtilDO.getInputPath());

			URI outputUri = Paths.get(userInputFileUtilDO.getOutputPath(), userInputFileUtilDO.getFilename()).toUri();
			File outputFile = new File(outputUri);

			Map<String, ObjectTypeSchemaExcelInfo> excelVal = new LinkedHashMap<String, ObjectTypeSchemaExcelInfo>();
			List<String> errorList = new ArrayList<String>();

			readExcel(inputFile, excelVal, headerList, errorList);

			if (!excelVal.isEmpty() && errorList.isEmpty()) {
				// Initialize object factory and add unit values
				ObjectFactory objectFactory = new ObjectFactory();
				STEPProductInformation stepProductInformation = objectFactory.createSTEPProductInformation();
				stepProductInformation.setContextID(HandlerConstants.CONTEXT1);
				stepProductInformation.setWorkspaceID(HandlerConstants.MAIN);

				UserTypesType userTypes = objectFactory.createUserTypesType();
				for (String key : excelVal.keySet()) {
					ObjectTypeSchemaExcelInfo objectInfo = excelVal.get(key);
					UserTypeType userType = objectFactory.createUserTypeType();

					String objectTypeID = objectInfo.getObjectTypeID();
					if (!ExcelWorkbookUtility.isNullOrBlank(objectTypeID)) {
						userType.setID(objectTypeID);
					}

					String objectTypeName = objectInfo.getName();
					if (!ExcelWorkbookUtility.isNullOrBlank(objectTypeName)) {
						NameType name = objectFactory.createNameType();
						name.setContent(objectTypeName);
						userType.getName().add(name);
					}

					String idPattern = objectInfo.getIdPattern();
					if (!ExcelWorkbookUtility.isNullOrBlank(idPattern)) {
						userType.setIDPattern(idPattern);
					}
					if (null != objectInfo.getParentID() && !objectInfo.getParentID().isEmpty()) {
						for (String parentID : objectInfo.getParentID()) {
							if (!ExcelWorkbookUtility.isNullOrBlank(parentID)) {
								UserTypeLinkType userTypeLink = objectFactory.createUserTypeLinkType();
								userTypeLink.setUserTypeID(parentID.trim());
								userType.getUserTypeLink().add(userTypeLink);
							}
						}
					}

					userTypes.getUserType().add(userType);

				}

				stepProductInformation.setUserTypes(userTypes);

				JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
				Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

				// output pretty printed
				jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

				jaxbMarshaller.marshal(stepProductInformation, outputFile);

				System.out.println("File Generated in path : " + outputFile.getAbsolutePath());

			} else {
				DateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");
				File file = new File(
						Paths.get(userInputFileUtilDO.getOutputPath(), "Errors" + df.format(new Date()) + ".txt")
								.toString());
				FileWriter writer = new FileWriter(file);
				BufferedWriter buffer = new BufferedWriter(writer);
				for (String errors : errorList) {
					buffer.write(errors + "\n");
				}
				buffer.close();
				writer.close();
				System.out.println("Errors Found In Excel File");
				System.out.println("Error File Generated in path : " + file.getAbsolutePath());
			}

		} catch (

		Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}

	private void readExcel(File inputFile, Map<String, ObjectTypeSchemaExcelInfo> excelVal, List<String> headerList,
			List<String> errorList) throws Exception {
		try {
			InputStream fs = new FileInputStream(inputFile);

			// Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(fs);

			// Get first/desired sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(0);

			// column headers
			List<String> columnHeaders = new ArrayList<String>();

			DataFormatter df = new DataFormatter();
			Row row0 = sheet.getRow(0);
			for (int rownum = 0; rownum <= sheet.getLastRowNum(); rownum++) {
				Row row = sheet.getRow(rownum);
				ObjectTypeSchemaExcelInfo excelInfo = new ObjectTypeSchemaExcelInfo();

				if (rownum == 0) {
					for (int cellnum = 0; cellnum < row0.getLastCellNum(); cellnum++) {
						Cell cell = row.getCell(cellnum);
						columnHeaders.add(df.formatCellValue(cell).trim());
					}
					System.out.println(columnHeaders);
				} else {
					for (int cellnum = 0; cellnum < row0.getLastCellNum(); cellnum++) {
						Cell cell = row.getCell(cellnum);

						String cellVal = df.formatCellValue(cell);
						if (ExcelWorkbookUtility.isNullOrBlank(cellVal)) {
							cellVal = "";
						}

						if (cellnum == headerList.indexOf("UserTypeID")) {
							excelInfo.setObjectTypeID(cellVal);
						} else if (cellnum == headerList.indexOf("IDPattern")) {
							excelInfo.setIdPattern(cellVal);
						} else if (cellnum == headerList.indexOf("ObjectTypeName")) {
							excelInfo.setName(cellVal);
						} else if (cellnum == headerList.indexOf("ParentObjectTypeID")) {
							Set<String> set = new HashSet<String>();
							for (String str : cellVal.split(",|;|\\|")) {
								set.add(str.trim());
							}
							excelInfo.setParentID(set);
						}

					}
//					System.out.println(excelInfo.getName() + "\t" + excelInfo.getParentID());
					if (excelVal.containsKey(excelInfo.getObjectTypeID())) {
						errorList.add("Duplicate ID Found at row : " + (rownum + 1));
					} else {
						excelVal.put(excelInfo.getObjectTypeID(), excelInfo);
					}

				}
			}
//			System.out.println("=======================================");
			workbook.close();
			fs.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}

	}

	// UserType_ID IDPattern Name ParentObjectTypeIDs
}
