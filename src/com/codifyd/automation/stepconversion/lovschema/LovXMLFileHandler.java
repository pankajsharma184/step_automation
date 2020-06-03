package com.codifyd.automation.stepconversion.lovschema;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
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
import javax.xml.bind.Unmarshaller;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.codifyd.automation.stepconversion.util.FileConversionHandler;
import com.codifyd.automation.stepconversion.util.InputValidator;
import com.codifyd.automation.stepconversion.util.UserInputFileUtilDO;
import com.codifyd.automation.stepconversion.util.Utility;
import com.codifyd.stepxsd.ListOfValueType;
import com.codifyd.stepxsd.STEPProductInformation;
import com.codifyd.stepxsd.TrueFalseType;
import com.codifyd.stepxsd.ValueType;

public class LovXMLFileHandler implements FileConversionHandler {

	public void handleFile(UserInputFileUtilDO userInputFileUtilDO) throws Exception {

		// parse the input for errors
		InputValidator.validateXMLToExcel(userInputFileUtilDO);

		File inputFile = new File(userInputFileUtilDO.getInputPath());
		File outputFile = new File(
				Paths.get(new File(userInputFileUtilDO.getOutputPath()).getPath(), userInputFileUtilDO.getFilename())
						.toUri());
		Properties properties = userInputFileUtilDO.getPropertiesFile();

		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(STEPProductInformation.class);
			Unmarshaller jaxbUnMarshaller = jaxbContext.createUnmarshaller();
			STEPProductInformation objectFactory = (STEPProductInformation) jaxbUnMarshaller.unmarshal(inputFile);

			writeExcel(objectFactory, outputFile, properties);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public static void writeExcel(STEPProductInformation objectFactory, File outputFile, Properties properties)
			throws Exception {
		try {
			// Create blank workbook
			XSSFWorkbook workbook = new XSSFWorkbook();
			// Create a blank sheet
			XSSFSheet spreadsheet = workbook.createSheet("LOV Info");
			// Create row object
			XSSFRow row;
			// Excel cell color
			XSSFCellStyle cellStyle = null;

			// Create Header List From Properties File
			TreeMap<Integer, String> propertyMap = new TreeMap<Integer, String>();
			for (String key : properties.stringPropertyNames()) {
				int i = Integer.parseInt(key);
				propertyMap.put(i, properties.getProperty(key));
			}

			ArrayList<String> headerList = new ArrayList<String>();
			for (Entry<Integer, String> ent : propertyMap.entrySet()) {
				headerList.add(ent.getValue().toString());
			}

			// Metadata Header List
			HashSet<String> metaHeader = new HashSet<String>();
			for (ListOfValueType listOfValueType : objectFactory.getListsOfValues().getListOfValue())
				if (null != listOfValueType.getMetaData())
					for (Object value : listOfValueType.getMetaData().getValueOrMultiValueOrValueGroup())
						if (value instanceof ValueType)
							metaHeader.add(((ValueType) value).getAttributeID());

			headerList.addAll(metaHeader);

			// Create metadata map
			TreeMap<String, Map<String, String>> metadataMap = new TreeMap<String, Map<String, String>>();

			int i = 0;

			// Creating Lov map for storing
			Map<Integer, List<String>> lovMap = new TreeMap<Integer, List<String>>();
			lovMap.put(i, headerList);

			for (ListOfValueType listOfValue : objectFactory.getListsOfValues().getListOfValue()) {

				String lovID = listOfValue.getID();
				String lovName = listOfValue.getName().get(0).getContent();
				String parentID = null != listOfValue.getParentID() ? listOfValue.getParentID() : "";
				String referenced = listOfValue.isReferenced() ? TrueFalseType.TRUE.toString()
						: TrueFalseType.FALSE.toString();
				String allowUserValueAdd = null != listOfValue.getAllowUserValueAddition()
						? listOfValue.getAllowUserValueAddition().toString()
						: TrueFalseType.FALSE.toString();
				String userValueId = null != listOfValue.getUseValueID() ? listOfValue.getUseValueID().toString()
						: TrueFalseType.FALSE.toString();

				String validation = listOfValue.getValidation().getBaseType();
				String inputMask = (null != listOfValue.getValidation().getInputMask())
						? listOfValue.getValidation().getInputMask()
						: "";
				String maxValue = (null != listOfValue.getValidation().getMaxValue())
						? listOfValue.getValidation().getMaxValue()
						: "";
				String minValue = (null != listOfValue.getValidation().getMinValue())
						? listOfValue.getValidation().getMinValue()
						: "";
				String maxLength = (null != listOfValue.getValidation().getMaxLength())
						? listOfValue.getValidation().getMaxLength()
						: "";

				if (null != listOfValue.getMetaData()) {
					Map<String, String> map = new HashMap<String, String>();
					for (Object value : listOfValue.getMetaData().getValueOrMultiValueOrValueGroup())
						if (value instanceof ValueType) {
							String key = (((ValueType) value).getAttributeID());
							String val = (((ValueType) value).getContent());
							map.put(key, val);
						}
					metadataMap.put(lovID, map);
				}

				List<ValueType> valuelist = listOfValue.getValue();
				for (ValueType value : valuelist) {
					i++;
					ArrayList<String> lovinfo = new ArrayList<String>();
					String valueId = (null != value.getID()) ? value.getID() : "";
					String valueName = value.getContent();
					lovinfo.add(lovID);
					lovinfo.add(lovName);
					lovinfo.add(parentID);
					lovinfo.add(referenced);
					lovinfo.add(allowUserValueAdd);
					lovinfo.add(userValueId);
					lovinfo.add(validation);
					lovinfo.add(inputMask);
					lovinfo.add(maxValue);
					lovinfo.add(minValue);
					lovinfo.add(maxLength);
					lovinfo.add(valueId);
					lovinfo.add(valueName);

					for (int index = lovinfo.size(); index <= headerList.size(); index++) {
						lovinfo.add("");
					}

					lovMap.put(i, lovinfo);
				}

			}

			// Adding metadata value in Lovmap info map
			for (Entry<Integer, List<String>> entrySet : lovMap.entrySet()) {
				Integer rowNum = entrySet.getKey();
				if (rowNum > 0) {
					String lovId = entrySet.getValue().get(0) + entrySet.getValue().get(4);
					if (metadataMap.containsKey(lovId)) {
						Map<String, String> metadataValues = metadataMap.get(lovId);
						List<String> lovData = entrySet.getValue();
						for (Entry<String, String> itr : metadataValues.entrySet()) {
							String metaAttrID = itr.getKey();
							String metaAttrValue = itr.getValue();
							int index = headerList.indexOf(metaAttrID);
							lovData.add(index, metaAttrValue);
						}
					}
				}
			}

			// Iterate over data and write to sheet
			Set<Integer> keyid = lovMap.keySet();
			int rowid = 0;
			for (Integer key : keyid) {
				row = spreadsheet.createRow(rowid++);
				List<String> objectArr = lovMap.get(key);
				int cellid = 0;
				for (Object obj : objectArr) {
					Cell cell = row.createCell(cellid++);
					if (obj instanceof String) {
						cell.setCellValue((String) obj);

						if (rowid == 1 && cell.getColumnIndex() < propertyMap.size()) {
							cell.setCellStyle(Utility.getHeaderStyle(workbook, cellStyle));
							cellStyle = null;
						} else if (rowid == 1 && cell.getColumnIndex() >= propertyMap.size()) {
							cell.setCellStyle(Utility.getMetaDataHeaderStyle(workbook, cellStyle));
							cellStyle = null;
						}

					}
				}
			}
			// Write the workbook in file system
			FileOutputStream out = new FileOutputStream(outputFile);
			workbook.write(out);
			out.close();
			workbook.close();
			System.out.println("File Generated in path : " + outputFile.getAbsolutePath());
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

}
