package com.codifyd.automation.stepconversion.uom;

import static com.codifyd.automation.stepconversion.uom.UomXMLWriter.familyHandler;
import static com.codifyd.automation.stepconversion.uom.UomXMLWriter.unitHandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.TreeMap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.codifyd.automation.stepconversion.util.ConfigHandler;
import com.codifyd.automation.stepconversion.util.HandlerConstants;
import com.codifyd.automation.stepconversion.util.InputValidator;
import com.codifyd.automation.stepconversion.util.UserInputFileUtilDO;
import com.codifyd.stepxsd.ObjectFactory;
import com.codifyd.stepxsd.STEPProductInformation;
import com.codifyd.stepxsd.UnitFamilyType;
import com.codifyd.stepxsd.UnitListType;
import com.codifyd.stepxsd.UnitType;

public class UomExcelFileHandler {

	public void handleFile(UserInputFileUtilDO userInput) throws Exception {

		try {

			// parse the input for errors
			InputValidator.validateExcelToXML(userInput);

			ConfigHandler configFile = userInput.getConfigFile();
			List<String> headerList = new ArrayList<String>();
			for (String key : configFile.keySet())
				headerList.add(key);

			// Read the Excel and build the UOM Objects
			TreeMap<String, ArrayList<UomExcelInfo>> excelinfo = new TreeMap<String, ArrayList<UomExcelInfo>>();
			readExcel(new File(userInput.getInputPath()), excelinfo, headerList);

			File outputFile = new File(
					Paths.get(new File(userInput.getOutputPath()).getPath(), userInput.getFilename()).toUri());

			// Initialize object factory and add unit values
			ObjectFactory objectFactory = new ObjectFactory();
			STEPProductInformation stepProductInformation = objectFactory.createSTEPProductInformation();
			stepProductInformation.setContextID(HandlerConstants.CONTEXT1);
			stepProductInformation.setWorkspaceID(HandlerConstants.MAIN);

			UnitListType unitListType = objectFactory.createUnitListType();
			List<Object> unitList = unitListType.getUnitFamilyOrUnit();
			for (String familyID : excelinfo.keySet()) {
				if (!familyID.equals("NoFamily")) {
					UnitFamilyType familyType = familyHandler(objectFactory, excelinfo.get(familyID), familyID);
					unitList.add(familyType);
				} else {
					for (UomExcelInfo unitInfo : excelinfo.get(familyID)) {
						UnitType unit = unitHandler(objectFactory, unitInfo);
						unitList.add(unit);
					}
				}
			}

			stepProductInformation.setUnitList(unitListType);
			JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(stepProductInformation, outputFile);

			System.out.println("File Generated in path : " + outputFile.getAbsolutePath());

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	private void readExcel(File inputFile, TreeMap<String, ArrayList<UomExcelInfo>> excelinfo, List<String> headerList)
			throws Exception {
		try {
			List<String> columnHeader = null;
			InputStream fs = new FileInputStream(inputFile);

			// Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(fs);

			// Get first/desired sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(0);

			for (Iterator<Row> iterator = sheet.iterator(); iterator.hasNext();) {
				Row row = (Row) iterator.next();
				DataFormatter df = new DataFormatter();
				if (row.getRowNum() == 0) {
					columnHeader = new ArrayList<String>();
					for (Iterator<Cell> iterator2 = row.iterator(); iterator2.hasNext();) {
						Cell cell = iterator2.next();
						columnHeader.add(df.formatCellValue(cell));
					}
				} else {
					ArrayList<UomExcelInfo> list = new ArrayList<UomExcelInfo>();
					UomExcelInfo uomInfo = new UomExcelInfo();
					String familyID = df.formatCellValue(row.getCell(headerList.indexOf("Unit_Group_ID")));
					if (familyID.equals("")) {
						familyID = "NoFamily";
					}
					for (Iterator<Cell> iterator2 = row.iterator(); iterator2.hasNext();) {
						Cell cell = (Cell) iterator2.next();
						if (cell.getColumnIndex() == headerList.indexOf("Unit_ID")) {
							uomInfo.setId(df.formatCellValue(cell));

						} else if (cell.getColumnIndex() == headerList.indexOf("Unit_Name")) {
							uomInfo.setName(df.formatCellValue(cell));

						} else if (cell.getColumnIndex() == headerList.indexOf("Unit_Group_ID")) {
							uomInfo.setParentId(df.formatCellValue(cell));

						} else if (cell.getColumnIndex() == headerList.indexOf("Unit_Group_Name")) {
							uomInfo.setParentName(df.formatCellValue(cell));

						} else if (cell.getColumnIndex() == headerList.indexOf("Referenced")) {
							uomInfo.setReferenced(df.formatCellValue(cell));

						} else if (cell.getColumnIndex() == headerList.indexOf("Unit_Conversion_BaseUNIT_ID")) {
							uomInfo.setBaseUnitId(df.formatCellValue(cell));

						} else if (cell.getColumnIndex() == headerList.indexOf("Unit_Conversion_Factor")) {
							uomInfo.setFactor(df.formatCellValue(cell));

						} else if (cell.getColumnIndex() == headerList.indexOf("Unit_Conversion_Offset")) {
							uomInfo.setOffset(df.formatCellValue(cell));

						} else if (cell.getColumnIndex() > 7 && cell.getColumnIndex() < columnHeader.size()) {
							LinkedHashMap<String, String> map = uomInfo.getUomMetadata();
							if (map == null) {
								map = uomInfo.createUomMetadatMap();
							}
							map.put(columnHeader.get(cell.getColumnIndex()), df.formatCellValue(cell));
							uomInfo.setUomMetadata(map);
						}
					}
					list.add(uomInfo);
					if (excelinfo.containsKey(familyID)) {
						excelinfo.get(familyID).add(uomInfo);
					} else {
						excelinfo.put(familyID, list);
					}

				}

			}
			workbook.close();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	}
}
