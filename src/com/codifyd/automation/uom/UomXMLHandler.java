package com.codifyd.automation.uom;

import static com.codifyd.automation.uom.UomXMLWriter.familyHandler;
import static com.codifyd.automation.uom.UomXMLWriter.unitHandler;

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

import com.codifyd.automation.util.UserInputFileUtilDO;
import com.codifyd.stepxsd.ObjectFactory;
import com.codifyd.stepxsd.STEPProductInformation;
import com.codifyd.stepxsd.UnitFamilyType;
import com.codifyd.stepxsd.UnitListType;
import com.codifyd.stepxsd.UnitType;

public class UomXMLHandler {
	private static String MAIN = "Main";
	private static String CONTEXT1 = "Context1";

	public void handleFile(UserInputFileUtilDO userInput) {

		try {
			// Read the Excel and build the UOM Objects
			TreeMap<String, ArrayList<UomInfo>> excelinfo = new TreeMap<String, ArrayList<UomInfo>>();
			readExcel(new File(userInput.getInputPath()), excelinfo);

			// System.out.println(excelValues.size());

			File file = new File(userInput.getOutputPath() + "\\" + userInput.getFilename());

			// Initialize object factory and add unit values
			ObjectFactory objectFactory = new ObjectFactory();
			STEPProductInformation stepProductInformation = objectFactory.createSTEPProductInformation();
			stepProductInformation.setContextID(CONTEXT1);
			stepProductInformation.setWorkspaceID(MAIN);

			UnitListType unitListType = objectFactory.createUnitListType();
			List<Object> unitList = unitListType.getUnitFamilyOrUnit();
			for (String familyID : excelinfo.keySet()) {
				if (!familyID.equals("NoFamily")) {
					UnitFamilyType familyType = familyHandler(objectFactory, excelinfo.get(familyID), familyID);
					unitList.add(familyType);
				} else {
					for (UomInfo unitInfo : excelinfo.get(familyID)) {
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
			// jaxbMarshaller.marshal(stepProductInformation, System.out);

			System.out.println("File Generated in path : " + file.getAbsolutePath());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void readExcel(File file, TreeMap<String, ArrayList<UomInfo>> excelinfo) {
		try {
			List<String> headerList = null;
			InputStream fs = new FileInputStream(file);

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
					ArrayList<UomInfo> list = new ArrayList<UomInfo>();
					UomInfo uomInfo = new UomInfo();
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
			System.out.println(e);
		}

	}
}
