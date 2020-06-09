package com.codifyd.automation.stepconversion.lovschema;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
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
import com.codifyd.automation.stepconversion.util.FileConversionHandler;
import com.codifyd.automation.stepconversion.util.InputValidator;
import com.codifyd.automation.stepconversion.util.UserInputFileUtilDO;
import com.codifyd.automation.stepconversion.util.ExcelWorkbookUtility;
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
		ConfigHandler configFile = userInputFileUtilDO.getConfigFile();

		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(STEPProductInformation.class);
			Unmarshaller jaxbUnMarshaller = jaxbContext.createUnmarshaller();
			STEPProductInformation objectFactory = (STEPProductInformation) jaxbUnMarshaller.unmarshal(inputFile);

			writeExcel(objectFactory, outputFile, configFile);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public static void writeExcel(STEPProductInformation objectFactory, File outputFile, ConfigHandler configFile)
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
			LinkedList<String> headerList1 = new LinkedList<String>();
			LinkedList<String> headerList2 = new LinkedList<String>();
			System.out.println(configFile);
			for (String key : configFile.keySet()) {
				headerList1.add(configFile.get(key));
				headerList2.add(key);
			}

			// Metadata Header List
			HashSet<String> metaHeader = new HashSet<String>();
			for (ListOfValueType listOfValueType : objectFactory.getListsOfValues().getListOfValue())
				if (null != listOfValueType.getMetaData())
					for (Object value : listOfValueType.getMetaData().getValueOrMultiValueOrValueGroup())
						if (value instanceof ValueType)
							metaHeader.add(((ValueType) value).getAttributeID());

			headerList1.addAll(metaHeader);

			// Create metadata map
			TreeMap<String, Map<String, String>> metadataMap = new TreeMap<String, Map<String, String>>();

			int i = 0;

			// Creating Lov map for storing
			Map<Integer, List<String>> lovMap = new TreeMap<Integer, List<String>>();
			lovMap.put(i, headerList1);

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
					for (int index = lovinfo.size(); index <= headerList1.size(); index++) {
						lovinfo.add("");
					}

					String valueId = (null != value.getID()) ? value.getID() : "";
					String valueName = value.getContent();

					lovinfo.set(headerList2.indexOf("LOV_ID"), lovID);
					lovinfo.set(headerList2.indexOf("LOV_Name"), lovName);
					lovinfo.set(headerList2.indexOf("LOV_Group_ID"), parentID);
					lovinfo.set(headerList2.indexOf("Referenced"), referenced);
					lovinfo.set(headerList2.indexOf("Allow_User_Value_Addition"), allowUserValueAdd);
					lovinfo.set(headerList2.indexOf("User_Value_ID"), userValueId);
					lovinfo.set(headerList2.indexOf("Validation_BaseType"), validation);
					lovinfo.set(headerList2.indexOf("Minimum_Value"), inputMask);
					lovinfo.set(headerList2.indexOf("Maximum_Value"), maxValue);
					lovinfo.set(headerList2.indexOf("Maximum_Length"), minValue);
					lovinfo.set(headerList2.indexOf("Input_Mask"), maxLength);
					lovinfo.set(headerList2.indexOf("Value_ID"), valueId);
					lovinfo.set(headerList2.indexOf("Value_Name"), valueName);

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
							int index = headerList1.indexOf(metaAttrID);
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

						if (rowid == 1 && cell.getColumnIndex() < headerList2.size()) {
							cell.setCellStyle(ExcelWorkbookUtility.getHeaderStyle(workbook, cellStyle));
							cellStyle = null;
						} else if (rowid == 1 && cell.getColumnIndex() >= headerList2.size()) {
							cell.setCellStyle(ExcelWorkbookUtility.getMetaDataHeaderStyle(workbook, cellStyle));
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
