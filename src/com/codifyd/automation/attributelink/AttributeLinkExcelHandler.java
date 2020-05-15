package com.codifyd.automation.attributelink;

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
import com.codifyd.stepxsd.AttributeLinkType;
import com.codifyd.stepxsd.ProductType;
import com.codifyd.stepxsd.STEPProductInformation;
import com.codifyd.stepxsd.TrueFalseType;
import com.codifyd.stepxsd.ValueType;

public class AttributeLinkExcelHandler {

	public void handleFile(UserInputFileUtilDO userInputFileUtilDO) throws IOException {

//		Map<String, AttributeXMLInfo> inputValues = new HashMap();
		File inputFile = new File(userInputFileUtilDO.getInputPath());
		File outputFile = new File(userInputFileUtilDO.getOutputPath() + "\\" + userInputFileUtilDO.getFilename());
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

	@SuppressWarnings("rawtypes")
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
		for (ProductType product : objectFactory.getProducts().getProduct())
			for (AttributeLinkType link : product.getAttributeLink())
				if (null != link.getMetaData())
					for (Object value : link.getMetaData().getValueOrMultiValueOrValueGroup())
						if (value instanceof ValueType)
							metaHeader.add(((ValueType) value).getAttributeID());

		// Add metadata header list into headerList
		headerList.addAll(metaHeader);

		// Create metadata map
		TreeMap<String, Map<String, String>> metadataMap = new TreeMap<String, Map<String, String>>();
		int i = 0;

		// Create attributeLinkMap Store attributeLink Info
		Map<Integer, List<String>> attributeLinkMap = new TreeMap<Integer, List<String>>();
		attributeLinkMap.put(i, headerList);
		for (ProductType product : objectFactory.getProducts().getProduct()) {

			String productId = product.getID();
			String productName = product.getName().get(0).getContent();
			String objectType = product.getUserTypeID();
			String parentID = null != product.getParentID() ? product.getParentID() : "";

			if (null != product.getAttributeLink()) {

				for (AttributeLinkType link : product.getAttributeLink()) {
					i++;
					List<String> data = new ArrayList<String>();

					String attributeId = link.getAttributeID();
					String mandatory = null != link.getMandatory() ? link.getMandatory().toString()
							: TrueFalseType.FALSE.toString();
					String qualifierId = null != link.getQualifierID() ? link.getQualifierID()
							: TrueFalseType.FALSE.toString();
					String inherited = null != link.getInherited() ? link.getInherited().toString() : "";
					String referenced = link.isReferenced() ? TrueFalseType.TRUE.toString()
							: TrueFalseType.FALSE.toString();

					if (null != link.getMetaData()) {
						Map<String, String> map = new HashMap<String, String>();
						for (Object value : link.getMetaData().getValueOrMultiValueOrValueGroup()) {
							if (value instanceof ValueType) {
								String key = ((ValueType) value).getAttributeID();
								String val = ((ValueType) value).getContent();
								map.put(key, val);
							}
						}
						metadataMap.put(productId + attributeId, map);
					}

					data.add(productId);
					data.add(productName);
					data.add(objectType);
					data.add(parentID);
					data.add(attributeId);
					data.add(mandatory);
					data.add(qualifierId);
					data.add(inherited);
					data.add(referenced);

					for (int index = data.size(); index <= headerList.size(); index++) {
						data.add("");
					}
					attributeLinkMap.put(i, data);
				}
			}
		}

		// Adding metadata value in attributelink info map
		for (Entry<Integer, List<String>> entrySet : attributeLinkMap.entrySet()) {
			Integer rowNum = entrySet.getKey();
			if (rowNum > 0) {
				String attributeId = entrySet.getValue().get(0) + entrySet.getValue().get(4);
				if (metadataMap.containsKey(attributeId)) {
					Map<String, String> metadataValues = metadataMap.get(attributeId);
					List<String> attributeData = entrySet.getValue();
					for (Entry<String, String> itr : metadataValues.entrySet()) {
						String metaAttrID = itr.getKey();
						String metaAttrValue = itr.getValue();
						int index = headerList.indexOf(metaAttrID);
						attributeData.add(index, metaAttrValue);
					}
				}
			}
		}

		// Iterating over the array, create rows and columns
		Set<Integer> keyid = attributeLinkMap.keySet();
		int rowid = 0;
		for (Integer key : keyid) {
			row = spreadsheet.createRow(rowid++);
			List<String> objArr = attributeLinkMap.get(key);
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