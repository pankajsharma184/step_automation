package com.codifyd.automation.uom;

import static com.codifyd.automation.uom.UomXMLReader.familyHandler;
import static com.codifyd.automation.uom.UomXMLReader.unitHandler;
import static com.codifyd.automation.util.ErrorLog.getErrorLog;

import static com.codifyd.automation.uom.UomXMLReader.getMetaDataValue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.codifyd.automation.util.UserInputFileUtilDO;
import com.codifyd.stepxsd.STEPProductInformation;
import com.codifyd.stepxsd.UnitFamilyType;
import com.codifyd.stepxsd.UnitType;
import com.codifyd.stepxsd.ValueType;

public class UomExcelHandler {
	public void handleFile(UserInputFileUtilDO userInputFileUtilDO) throws Exception {

//		Map<String, AttributeXMLInfo> inputValues = new HashMap();
		File inputFile = new File(userInputFileUtilDO.getInputPath());
		File outputFile = new File(userInputFileUtilDO.getOutputPath() + "\\" + userInputFileUtilDO.getFilename());
		Properties properties = userInputFileUtilDO.getPropertiesFile();
		String delimeter = userInputFileUtilDO.getDelimeters();
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(STEPProductInformation.class);
			Unmarshaller jaxbUnMarshaller = jaxbContext.createUnmarshaller();
			STEPProductInformation objectFactory = (STEPProductInformation) jaxbUnMarshaller.unmarshal(inputFile);

			writeExcel(objectFactory, outputFile, properties, delimeter);
		} catch (Exception e) {
			e.printStackTrace();
			String path = inputFile.getParentFile().getPath().toString();
			getErrorLog(path, e);
			throw new Exception("Error.lo File Generated At : " + path);
		}
	}

	@SuppressWarnings("deprecation")
	private static void writeExcel(STEPProductInformation objectFactory, File outputFile, Properties properties,
			String delim) throws FileNotFoundException, IOException {

		// Create blank workbook
		XSSFWorkbook workbook = new XSSFWorkbook();
		// Create a blank sheet
		XSSFSheet spreadsheet = workbook.createSheet("Attribute Info");
		// Create row object
		XSSFRow row;
		// Excel cell color
		XSSFCellStyle cellStyle = null;

		/*
		 * // Create Property Object and Load Properties File Properties properties =
		 * new Properties(); properties.load(inStream);
		 */

		// Create Header List From Properties File
		TreeMap<Integer, String> propertyMap = new TreeMap<Integer, String>();
		for (String key : properties.stringPropertyNames()) {
			propertyMap.put(Integer.parseInt(key), properties.getProperty(key));
		}

		ArrayList<String> headerList = new ArrayList<String>();
		for (Entry<Integer, String> ent : propertyMap.entrySet()) {
			headerList.add(ent.getValue().toString());
		}

		// Metadata Header List
		HashSet<String> metaHeader = new HashSet<String>();
		List<Object> unitList = objectFactory.getUnitList().getUnitFamilyOrUnit();
		for (Object object1 : unitList) {
			if (object1 instanceof UnitFamilyType) {
				List<UnitType> unitType = ((UnitFamilyType) object1).getUnit();
				for (UnitType unit : unitType) {
					if (unit.getMetaData() != null && unit.getMetaData().getValueOrMultiValueOrValueGroup() != null) {
						for (Object value : unit.getMetaData().getValueOrMultiValueOrValueGroup()) {
							metaHeader.add(((ValueType) value).getAttributeID());
						}
					}
				}
			} else if (object1 instanceof UnitType) {
				if (((UnitType) object1).getMetaData() != null
						&& ((UnitType) object1).getMetaData().getValueOrMultiValueOrValueGroup() != null) {
					for (Object value : ((UnitType) object1).getMetaData().getValueOrMultiValueOrValueGroup()) {
						metaHeader.add(((ValueType) value).getAttributeID());
					}
				}
			}
		}

		// Add metadata header list into headerList
		headerList.addAll(metaHeader);

		// Create metadata map
		TreeMap<String, Map<String, String>> metadataMap = new TreeMap<String, Map<String, String>>();
		int i = 0;

		// Create UomMap Store Uom Info
		TreeMap<Integer, List<String>> uomMap = new TreeMap<Integer, List<String>>();
		uomMap.put(i, headerList);

		List<Object> unitListType = objectFactory.getUnitList().getUnitFamilyOrUnit();
		Object[] args = new Object[] { i, (List<Object>) unitListType, headerList, uomMap, metadataMap, delim };
		i = familyHandler(args);
		System.out.println(i);
		Object[] args2 = new Object[] { i, (List<Object>) unitListType, headerList, uomMap, metadataMap, delim, "",
				"" };
		i = unitHandler(args2);
		System.out.println(i);

		for (Entry<Integer, List<String>> entrySet : uomMap.entrySet()) {
			Integer rowNum = entrySet.getKey();
			if (rowNum > 0) {
				List<String> obj = entrySet.getValue();
				String key1 = (String) obj.get(0) + obj.get(2);
//				String key2 = (String) entrySet.getValue().get(0);
				if (metadataMap.containsKey(key1)) {
					Map<String, String> metadataValues = metadataMap.get(key1);
					List<String> uomData = entrySet.getValue();
					getMetaDataValue(metadataValues, uomData, headerList);

//				} else if (metadataMap.containsKey(key2)) {
//					Map<String, String> metadataValues = metadataMap.get(key1);
//					List<String> uomData = entrySet.getValue();
//					getMetaDataValue(metadataValues, uomData, headerList);
				}
			}
		}

		Set<Integer> keyid = uomMap.keySet();
		int rowid = 0;
		for (Integer key : keyid) {
			row = spreadsheet.createRow(rowid++);
			List<String> objArr = uomMap.get(key);
			int cellid = 0;
			for (String obj : objArr) {
				Cell cell = row.createCell(cellid++);
				cell.setCellValue(obj);
				if (rowid == 1 && cell.getColumnIndex() < propertyMap.size()) {
					cellStyle = workbook.createCellStyle();
					cellStyle.setFillForegroundColor(new XSSFColor(new java.awt.Color(50, 120, 180)));
					cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
					cell.setCellStyle(cellStyle);
				} else if (rowid == 1 && cell.getColumnIndex() >= propertyMap.size()) {
					cellStyle = workbook.createCellStyle();
					cellStyle = workbook.createCellStyle();
					cellStyle.setFillForegroundColor(new XSSFColor(new java.awt.Color(155, 195, 230)));
					cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
					cell.setCellStyle(cellStyle);
				}

			}
		}

		// Write the workbook in file system
		FileOutputStream out = new FileOutputStream(outputFile);
		workbook.write(out);
		out.close();
		workbook.close();
		System.out.println("File Generated in path : " + outputFile);

	}
}
