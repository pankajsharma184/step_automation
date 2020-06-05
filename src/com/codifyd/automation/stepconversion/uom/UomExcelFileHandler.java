package com.codifyd.automation.stepconversion.uom;

import static com.codifyd.automation.stepconversion.uom.UomXMLWriter.familyHandler;
import static com.codifyd.automation.stepconversion.uom.UomXMLWriter.unitHandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
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

import com.codifyd.automation.stepconversion.util.HandlerConstants;
import com.codifyd.automation.stepconversion.util.UserInputFileUtilDO;
import com.codifyd.stepxsd.ObjectFactory;
import com.codifyd.stepxsd.STEPProductInformation;
import com.codifyd.stepxsd.UnitFamilyType;
import com.codifyd.stepxsd.UnitListType;
import com.codifyd.stepxsd.UnitType;

public class UomExcelFileHandler {

	public void handleFile(UserInputFileUtilDO userInput) throws Exception {

		try {
			// Read the Excel and build the UOM Objects
			TreeMap<String, ArrayList<UomExcelInfo>> excelinfo = new TreeMap<String, ArrayList<UomExcelInfo>>();
			readExcel(new File(userInput.getInputPath()), excelinfo);

			File file = new File(userInput.getOutputPath() + "\\" + userInput.getFilename());

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

			jaxbMarshaller.marshal(stepProductInformation, file);

			System.out.println("File Generated in path : " + file.getAbsolutePath());

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	private void readExcel(File inputFile, TreeMap<String, ArrayList<UomExcelInfo>> excelinfo) throws Exception {
		try {
			List<String> headerList = null;
			InputStream fs = new FileInputStream(inputFile);

			// Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(fs);

			// Get first/desired sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(0);

			for (Iterator<Row> iterator = sheet.iterator(); iterator.hasNext();) {
				Row row = (Row) iterator.next();
				DataFormatter df = new DataFormatter();
				if (row.getRowNum() == 0) {
					headerList = new ArrayList<String>();
					for (Iterator<Cell> iterator2 = row.iterator(); iterator2.hasNext();) {
						Cell cell = iterator2.next();
						headerList.add(df.formatCellValue(cell));
					}
				} else {
					ArrayList<UomExcelInfo> list = new ArrayList<UomExcelInfo>();
					UomExcelInfo uomInfo = new UomExcelInfo();
					String familyID = df.formatCellValue(row.getCell(2));
					if (familyID.equals("")) {
						familyID = "NoFamily";
					}
					for (Iterator<Cell> iterator2 = row.iterator(); iterator2.hasNext();) {
						Cell cell = (Cell) iterator2.next();
						if (cell.getColumnIndex() == 0) {
							uomInfo.setId(df.formatCellValue(cell));
						} else if (cell.getColumnIndex() == 1) {
							uomInfo.setName(df.formatCellValue(cell));
						} else if (cell.getColumnIndex() == 2) {
							uomInfo.setParentId(df.formatCellValue(cell));
						} else if (cell.getColumnIndex() == 3) {
							uomInfo.setParentName(df.formatCellValue(cell));
						} else if (cell.getColumnIndex() == 4) {
							uomInfo.setReferenced(df.formatCellValue(cell));
						} else if (cell.getColumnIndex() == 5) {
							uomInfo.setBaseUnitId(df.formatCellValue(cell));
						} else if (cell.getColumnIndex() == 6) {
							uomInfo.setFactor(df.formatCellValue(cell));
						} else if (cell.getColumnIndex() == 7) {
							uomInfo.setOffset(df.formatCellValue(cell));
						} else if (cell.getColumnIndex() > 7 && cell.getColumnIndex() < headerList.size()) {
							LinkedHashMap<String, String> map = uomInfo.getUomMetadata();
							if (map == null) {
								map = uomInfo.createUomMetadatMap();
							}
							map.put(headerList.get(cell.getColumnIndex()), df.formatCellValue(cell));
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
