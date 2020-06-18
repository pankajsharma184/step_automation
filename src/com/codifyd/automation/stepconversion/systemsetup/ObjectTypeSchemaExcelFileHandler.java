package com.codifyd.automation.stepconversion.systemsetup;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
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
			for (String key : configFile.keySet())
				headerList.add(key);

			System.out.println(configFile);
			File inputFile = new File(userInputFileUtilDO.getInputPath());

			URI outputUri = Paths.get(userInputFileUtilDO.getOutputPath(), userInputFileUtilDO.getFilename()).toUri();
			File outputFile = new File(outputUri);

			List<ObjectTypeSchemaExcelInfo> excelVal = new ArrayList<ObjectTypeSchemaExcelInfo>();

			readExcel(inputFile, excelVal, configFile);

			if (!excelVal.isEmpty()) {
				// Initialize object factory and add unit values
				ObjectFactory objectFactory = new ObjectFactory();
				STEPProductInformation stepProductInformation = objectFactory.createSTEPProductInformation();
				stepProductInformation.setContextID(HandlerConstants.CONTEXT1);
				stepProductInformation.setWorkspaceID(HandlerConstants.MAIN);

				UserTypesType userTypes = objectFactory.createUserTypesType();
				for (ObjectTypeSchemaExcelInfo objectInfo : excelVal) {
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
					System.out.println(objectTypeID + "\t" + objectTypeName + "\t" + idPattern);
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

			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}

	private void readExcel(File inputFile, List<ObjectTypeSchemaExcelInfo> excelVal, ConfigHandler configFile)
			throws Exception {
		try {
			InputStream fs = new FileInputStream(inputFile);

			// Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(fs);

			// Get first/desired sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(0);

			// column headers
			List<String> columnHeaders = new ArrayList<String>();

			DataFormatter df = new DataFormatter();

			for (Iterator<Row> rowItr = sheet.iterator(); rowItr.hasNext();) {
				Row row = rowItr.next();
				int rownum = row.getRowNum();
				ObjectTypeSchemaExcelInfo excelInfo = new ObjectTypeSchemaExcelInfo();
				List<ObjectTypeSchemaExcelInfo> list = new ArrayList<ObjectTypeSchemaExcelInfo>();

				if (rownum == 0) {
					for (Iterator<Cell> cellItr = row.iterator(); cellItr.hasNext();) {
						Cell cell = cellItr.next();
						columnHeaders.add(df.formatCellValue(cell).trim());
					}
					System.out.println(columnHeaders);
				} else {
					for (Iterator<Cell> cellItr = row.iterator(); cellItr.hasNext();) {
						Cell cell = cellItr.next();
						int cellnum = cell.getColumnIndex();

						String cellVal = df.formatCellValue(cell);

						System.out.println(cellVal);

						if (cellnum == columnHeaders.indexOf(configFile.get("UserTypeID"))) {
							excelInfo.setObjectTypeID(cellVal);
						} else if (cellnum == columnHeaders.indexOf(configFile.get("IDPattern"))) {
							excelInfo.setIdPattern(cellVal);
						} else if (cellnum == columnHeaders.indexOf(configFile.get("Name"))) {
							excelInfo.setName(cellVal);
						} else if (cellnum == columnHeaders.indexOf(configFile.get("ParentID"))) {
							Set<String> set = new HashSet<String>();
							set.addAll(Arrays.asList(cellVal.split(",|;|\\|")));
							excelInfo.setParentID(set);
						}
					}
					excelVal.add(excelInfo);
				}
			}

			workbook.close();
			fs.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}

	}

	// UserType_ID IDPattern Name ParentObjectTypeIDs
}
