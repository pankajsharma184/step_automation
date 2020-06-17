package com.codifyd.automation.stepconversion.user;

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
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.codifyd.automation.stepconversion.util.FileConversionHandler;
import com.codifyd.automation.stepconversion.util.HandlerConstants;
import com.codifyd.automation.stepconversion.util.InputValidator;
import com.codifyd.automation.stepconversion.util.UserInputFileUtilDO;
import com.codifyd.stepxsd.NameType;
import com.codifyd.stepxsd.ObjectFactory;
import com.codifyd.stepxsd.STEPProductInformation;
import com.codifyd.stepxsd.UserGroupLinkType;
import com.codifyd.stepxsd.UserGroupListType;
import com.codifyd.stepxsd.UserGroupType;
import com.codifyd.stepxsd.UserListType;
import com.codifyd.stepxsd.UserType;

public class UserGroupExcelFileHandler implements FileConversionHandler {

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
			ArrayList<UserGroupExcelInfo> excelValues = new ArrayList<>();
			readExcel(new File(userInput.getInputPath()), excelValues);

			File outputFile = new File(
					Paths.get(new File(userInput.getOutputPath()).getPath(), userInput.getFilename()).toUri());

			// Initialize object factory and add unit values
			ObjectFactory objectFactory = new ObjectFactory();
			STEPProductInformation stepProductInformation = objectFactory.createSTEPProductInformation();
			stepProductInformation.setContextID(HandlerConstants.CONTEXT1);
			stepProductInformation.setWorkspaceID(HandlerConstants.MAIN);

			UserGroupListType userGroupListType = objectFactory.createUserGroupListType();
			
			for (UserGroupExcelInfo userGroupInfo : excelValues) {
				UserGroupType userGroupType = objectFactory.createUserGroupType();
				userGroupType.setID(userGroupInfo.getUserGroupID());
				
				NameType nameType = objectFactory.createNameType();
				nameType.setContent(userGroupInfo.getUserGroupName());
				userGroupType.getName().add(nameType);
				
				userGroupType.setParentID(userGroupInfo.getUserParentID());			
				userGroupListType.getUserGroup().add(userGroupType);				
			}
			
			

			stepProductInformation.setUserGroupList(userGroupListType);

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

	private void readExcel(File inputFile, ArrayList<UserGroupExcelInfo> excelValues) throws Exception {
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
					UserGroupExcelInfo userGroupExcelInfo = new UserGroupExcelInfo();
					for (Iterator<Cell> iterator2 = row.iterator(); iterator2.hasNext();) {
						Cell cell = iterator2.next();
						
						if (cell.getColumnIndex() == columnHeader.indexOf("UserGroup_ID")) {
							userGroupExcelInfo.setUserGroupID(df.formatCellValue(cell));
						} else if (cell.getColumnIndex() == columnHeader.indexOf("UserGroup_Name")) {
							userGroupExcelInfo.setUserGroupName(df.formatCellValue(cell));
						} else if (cell.getColumnIndex() == columnHeader.indexOf("UserGroup_ParentID")) {
							userGroupExcelInfo.setUserParentID(df.formatCellValue(cell));
						} 
					}
					excelValues.add(userGroupExcelInfo);
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
