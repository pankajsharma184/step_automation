package com.codifyd.automation.uom;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
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
import com.codifyd.stepxsd.TrueFalseType;
import com.codifyd.stepxsd.UnitConversionType;
import com.codifyd.stepxsd.UnitFamilyType;
import com.codifyd.stepxsd.UnitType;
import com.codifyd.stepxsd.ValueType;

public class UomExcelHandler {
	public void handleFile(UserInputFileUtilDO userInputFileUtilDO) throws IOException {

//		Map<String, AttributeXMLInfo> inputValues = new HashMap();
		File inputFile = new File(userInputFileUtilDO.getInputPath());
		File outputFile = new File(userInputFileUtilDO.getOutputPath()+"\\"+userInputFileUtilDO.getFilename());
		File proerty = new File(userInputFileUtilDO.getPropertiesFile());
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(STEPProductInformation.class);
			Unmarshaller jaxbUnMarshaller = jaxbContext.createUnmarshaller();
			STEPProductInformation objectFactory = (STEPProductInformation) jaxbUnMarshaller.unmarshal(inputFile);

			writeExcel(objectFactory, outputFile, proerty);
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void writeExcel(STEPProductInformation objectFactory, File outputFile, File property)
			throws FileNotFoundException, IOException {

		// Create blank workbook
		XSSFWorkbook workbook = new XSSFWorkbook();
		// Create a blank sheet
		XSSFSheet spreadsheet = workbook.createSheet("Attribute Info");
		// Create row object
		XSSFRow row;
		// Excel cell color
		XSSFCellStyle cellStyle = null;

		// Create Property Object and Load Properties File
		Properties properties = new Properties();
		properties.load(new FileInputStream(property));

		// Create Header List From Properties File
		TreeMap<Integer, String> propertyMap = new TreeMap<Integer, String>();
		for (String key : properties.stringPropertyNames()) {
			propertyMap.put(Integer.parseInt(key), properties.getProperty(key));
		}

		ArrayList<String> headerList = new ArrayList<String>();
		for (Entry ent : propertyMap.entrySet()) {
			headerList.add(ent.getValue().toString());
		}

		// Metadata Header List
		HashSet<String> metaHeader = new HashSet<String>();
		for (Object unitList : objectFactory.getUnitList().getUnitFamilyOrUnit()) {
			for (UnitType unit : ((UnitFamilyType) unitList).getUnit()) {
				for (Object value : unit.getMetaData().getValueOrMultiValueOrValueGroup()) {
					metaHeader.add(((ValueType) value).getAttributeID());
				}
			}
		}

		// Add metadata header list into headerList
		headerList.addAll(metaHeader);

//		System.out.println(headerList);

		// Create metadata map
		TreeMap<String, Map<String, String>> metadataMap = new TreeMap<String, Map<String, String>>();
		int i = 0;

		// Create UomMap Store Uom Info
		Map<Integer, List<String>> uomMap = new TreeMap<Integer, List<String>>();
		uomMap.put(i, headerList);

		for (Object family : objectFactory.getUnitList().getUnitFamilyOrUnit()) {
			String unitGroupId = ((UnitFamilyType) family).getID();
			String unitGroupName = ((UnitFamilyType) family).getName().get(0).getContent();
			String conversionFactor = "";
			String conversionoffset = "";
			String conversionBaseUnitId = "";
			for (UnitType unit : ((UnitFamilyType) family).getUnit()) {
				i++;
				List<String> data = new ArrayList<String>();
				Map<String, String> map = new HashMap<String, String>();
				
				
				String unitId = unit.getID();
				String unitName = unit.getName().get(0).getContent();
				String reference=unit.isReferenced()?TrueFalseType.TRUE.toString() : TrueFalseType.FALSE.toString();

				if (null != unit.getUnitConversion()) {
					UnitConversionType conversion = unit.getUnitConversion();
					conversionFactor = conversion.getFactor();
					conversionoffset = conversion.getOffset();
					conversionBaseUnitId = conversion.getBaseUnitID();
				}

				for (Object metaDataValue : unit.getMetaData().getValueOrMultiValueOrValueGroup()) {
					String id = ((ValueType) metaDataValue).getAttributeID();
					String value = ((ValueType) metaDataValue).getContent();
					map.put(id, value);
				}

				metadataMap.put(unitId, map);

				data.add(unitId);
				data.add(unitName);
				data.add(unitGroupId);
				data.add(unitGroupName);
				data.add(reference);
				data.add(conversionBaseUnitId);
				data.add(conversionFactor);
				data.add(conversionoffset);

				for (int index = data.size(); index <= headerList.size(); index++) {
					data.add("");
				}

				uomMap.put(i, data);

			}
		}
		
		for (Entry<Integer, List<String>> entrySet : uomMap.entrySet()) {
			Integer rowNum = entrySet.getKey();
			if (rowNum > 0) {
				String attrID = (String) entrySet.getValue().get(0);
				if (metadataMap.containsKey(attrID)) {
					Map<String, String> metadataValues = metadataMap.get(attrID);
					List uomData = entrySet.getValue();
					for (Map.Entry<String, String> itr : metadataValues.entrySet()) {
						String metaAttrID = itr.getKey();
						String metaAttrValue = itr.getValue();
						int index = headerList.indexOf(metaAttrID);
						uomData.add(index, metaAttrValue);
					}
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
