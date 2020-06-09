package com.codifyd.automation.stepconversion.uom;

import static com.codifyd.automation.stepconversion.uom.UomXMLReader.familyHandler;
import static com.codifyd.automation.stepconversion.uom.UomXMLReader.getMetaDataValue;
import static com.codifyd.automation.stepconversion.uom.UomXMLReader.unitHandler;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.codifyd.automation.stepconversion.util.ConfigHandler;
import com.codifyd.automation.stepconversion.util.UserInputFileUtilDO;
import com.codifyd.automation.stepconversion.util.ExcelWorkbookUtility;
import com.codifyd.stepxsd.STEPProductInformation;
import com.codifyd.stepxsd.UnitFamilyType;
import com.codifyd.stepxsd.UnitType;
import com.codifyd.stepxsd.ValueType;

public class UomXMLFileHandler {
	public void handleFile(UserInputFileUtilDO userInputFileUtilDO) throws Exception {

		File inputFile = new File(userInputFileUtilDO.getInputPath());
		File outputFile = new File(
				Paths.get(new File(userInputFileUtilDO.getOutputPath()).getPath(), userInputFileUtilDO.getFilename())
						.toUri());
		ConfigHandler configFile = userInputFileUtilDO.getConfigFile();
		String delimeter = userInputFileUtilDO.getDelimiters();
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(STEPProductInformation.class);
			Unmarshaller jaxbUnMarshaller = jaxbContext.createUnmarshaller();
			STEPProductInformation objectFactory = (STEPProductInformation) jaxbUnMarshaller.unmarshal(inputFile);

			writeExcel(objectFactory, outputFile, configFile, delimeter);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	private static void writeExcel(STEPProductInformation objectFactory, File outputFile, ConfigHandler configFile,
			String delim) throws Exception {
		try {

			// Create blank workbook
			XSSFWorkbook workbook = new XSSFWorkbook();
			// Create a blank sheet
			XSSFSheet spreadsheet = workbook.createSheet("UOMInfo");
			// Create row object
			XSSFRow row;
			// Excel cell color
			XSSFCellStyle cellStyle = null;

			/*
			 * // Create Property Object and Load Properties File Properties properties =
			 * new Properties(); properties.load(inStream);
			 */

			// Create Header List From Properties File
			LinkedList<String> headerList1 = new LinkedList<String>();
			LinkedList<String> headerList2 = new LinkedList<String>();
			System.out.println(configFile);
			for (String key : configFile.keySet()) {
				headerList1.add(configFile.get(key));
				headerList2.add(key);
			}

			// Metadata Header List
			HashSet<String> metaHeader = new HashSet<String>();
			List<Object> unitList = objectFactory.getUnitList().getUnitFamilyOrUnit();
			for (Object object1 : unitList) {
				if (object1 instanceof UnitFamilyType) {
					List<UnitType> unitType = ((UnitFamilyType) object1).getUnit();
					for (UnitType unit : unitType) {
						if (unit.getMetaData() != null
								&& unit.getMetaData().getValueOrMultiValueOrValueGroup() != null) {
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
			headerList1.addAll(metaHeader);

			// Create metadata map
			TreeMap<String, Map<String, String>> metadataMap = new TreeMap<String, Map<String, String>>();
			int i = 0;

			// Create UomMap Store Uom Info
			TreeMap<Integer, List<String>> uomMap = new TreeMap<Integer, List<String>>();
			uomMap.put(i, headerList1);

			List<Object> unitListType = objectFactory.getUnitList().getUnitFamilyOrUnit();
			Object[] args = new Object[] { i, (List<Object>) unitListType, headerList1, headerList2, uomMap,
					metadataMap, delim };
			i = familyHandler(args);
			Object[] args2 = new Object[] { i, (List<Object>) unitListType, headerList1, headerList2, uomMap,
					metadataMap, delim, "", "" };
			i = unitHandler(args2);

			for (Entry<Integer, List<String>> entrySet : uomMap.entrySet()) {
				Integer rowNum = entrySet.getKey();
				if (rowNum > 0) {
					List<String> obj = entrySet.getValue();
					String key1 = (String) obj.get(0) + obj.get(2);

					if (metadataMap.containsKey(key1)) {
						Map<String, String> metadataValues = metadataMap.get(key1);
						List<String> uomData = entrySet.getValue();
						getMetaDataValue(metadataValues, uomData, headerList1);

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
					if (rowid == 1 && cell.getColumnIndex() < headerList2.size()) {
						cell.setCellStyle(ExcelWorkbookUtility.getHeaderStyle(workbook, cellStyle));
						cellStyle = null;
					} else if (rowid == 1 && cell.getColumnIndex() >= headerList2.size()) {
						cell.setCellStyle(ExcelWorkbookUtility.getMetaDataHeaderStyle(workbook, cellStyle));
						cellStyle = null;
					}

				}
			}

			// Write the workbook in file system
			FileOutputStream out = new FileOutputStream(outputFile);
			workbook.write(out);
			out.close();
			workbook.close();
			System.out.println("File Generated in path : " + outputFile);

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
}
