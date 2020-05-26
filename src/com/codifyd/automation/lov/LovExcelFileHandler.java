package com.codifyd.automation.lov;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.codifyd.automation.util.FileConversionHandler;
import com.codifyd.automation.util.HandlerConstants;
import com.codifyd.automation.util.InputValidator;
import com.codifyd.automation.util.UserInputFileUtilDO;
import com.codifyd.stepxsd.ListOfValueType;
import com.codifyd.stepxsd.ListsOfValuesType;
import com.codifyd.stepxsd.MetaDataType;
import com.codifyd.stepxsd.NameType;
import com.codifyd.stepxsd.ObjectFactory;
import com.codifyd.stepxsd.STEPProductInformation;
import com.codifyd.stepxsd.TrueFalseType;
import com.codifyd.stepxsd.ValidationType;
import com.codifyd.stepxsd.ValueType;

public class LovExcelFileHandler implements FileConversionHandler{

	public void handleFile(UserInputFileUtilDO userInputFileUtilDO) throws Exception {

		// parse the input for errors
		InputValidator.validateExcelToXML(userInputFileUtilDO);
		
//		Runtime runtime = Runtime.getRuntime();
//		long beforeUsedMem = runtime.totalMemory() - runtime.freeMemory();
//		System.out.println(beforeUsedMem);

		try {

			// Read the Excel and build the UOM Objects
			TreeMap<String, ArrayList<LovExcelInfo>> excelinfo = new TreeMap<String, ArrayList<LovExcelInfo>>();
			readExcel(new File(userInputFileUtilDO.getInputPath()), excelinfo);

			String delimeter = userInputFileUtilDO.getDelimiters();
			if (delimeter.equals("|")) {
				delimeter = "\\|";
			}

			File file = new File(userInputFileUtilDO.getOutputPath() + "\\" + userInputFileUtilDO.getFilename());

			// Initialize object factory and add unit values
			ObjectFactory objectFactory = new ObjectFactory();
			STEPProductInformation stepProductInformation = objectFactory.createSTEPProductInformation();
			stepProductInformation.setContextID(HandlerConstants.CONTEXT1);
			stepProductInformation.setWorkspaceID(HandlerConstants.MAIN);

			ListsOfValuesType listOfValues = objectFactory.createListsOfValuesType();
			List<ListOfValueType> listOfValue = listOfValues.getListOfValue();
			for (String key : excelinfo.keySet()) {

				ListOfValueType lov = objectFactory.createListOfValueType();
				LovExcelInfo info = excelinfo.get(key).get(0);

				NameType nameType = objectFactory.createNameType();

				lov.setID(info.getLovID());
				nameType.setContent(info.getLovName());
				lov.getName().add(nameType);

				lov.setAllowUserValueAddition(
						Boolean.parseBoolean(info.getAllowUserValueAddition()) ? TrueFalseType.TRUE
								: TrueFalseType.FALSE);

				lov.setParentID(info.getLovGroupID());

				lov.setReferenced(Boolean.parseBoolean(info.getReferenced()));

				lov.setUseValueID(
						Boolean.parseBoolean(info.getUserValueID()) ? TrueFalseType.TRUE : TrueFalseType.FALSE);

				ValidationType validation = objectFactory.createValidationType();
				validation.setBaseType(info.getBaseType());
				validation.setMaxValue(info.getMaxValue());
				validation.setMinValue(info.getMinValue());
				validation.setMaxLength(info.getMaxLength());
				validation.setInputMask(info.getInputMask());

				lov.setValidation(validation);

				List<ValueType> values = lov.getValue();

				ArrayList<LovExcelInfo> list = new ArrayList<LovExcelInfo>();
				list.addAll(excelinfo.get(key));
				for (LovExcelInfo valueinfo : list) {
					ValueType value = objectFactory.createValueType();

					if (!valueinfo.getValueID().trim().equals("")) {
						value.setID(valueinfo.getValueID());
					}
					value.setContent((valueinfo.getValueName()));

					values.add(value);
				}

				if (null != info.getLovMetadata()) {
					Map<String, String> map = info.getLovMetadata();
					MetaDataType meta = objectFactory.createMetaDataType();
					List<Object> valueList = meta.getValueOrMultiValueOrValueGroup();
					for (String key2 : map.keySet()) {
						if (!map.get(key2).equals("")) {
							ValueType value = objectFactory.createValueType();
							value.setAttributeID(key2);
							value.setContent(map.get(key2));
							valueList.add(value);
						}
					}
					lov.setMetaData(meta);
				}
				listOfValue.add(lov);

			}

			stepProductInformation.setListsOfValues(listOfValues);

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

//		long afterUsedMem = runtime.totalMemory() - runtime.freeMemory();
//		System.out.println(afterUsedMem);
//		long actualMemUsed = afterUsedMem - beforeUsedMem;
//		System.out.println(actualMemUsed);
//
//		System.out.println("max memory: " + runtime.maxMemory() / 1024);
//		System.out.println("allocated memory: " + runtime.totalMemory() / 1024);
//		System.out.println("free memory: " + runtime.freeMemory() / 1024);

	}

	private void readExcel(File inputFile, TreeMap<String, ArrayList<LovExcelInfo>> excelinfo) {
		try {
			InputStream fs = new FileInputStream(inputFile);

			// Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(fs);

			// Get first/desired sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(0);
			ArrayList<String> headerList = new ArrayList<String>();
			for (Iterator<Row> iterator = sheet.iterator(); iterator.hasNext();) {
				DataFormatter df = new DataFormatter();
				Row row = (Row) iterator.next();
				if (row.getRowNum() == 0)
					for (Iterator<Cell> iterator2 = row.iterator(); iterator2.hasNext();)
						headerList.add(df.formatCellValue(iterator2.next()));

				if (row.getRowNum() > 0) {
					LovExcelInfo lovInfo = new LovExcelInfo();
					String id = df.formatCellValue(row.getCell(0));
					for (Iterator<Cell> iterator2 = row.iterator(); iterator2.hasNext();) {
						Cell cell = (Cell) iterator2.next();

						if (cell.getColumnIndex() == 0)
							lovInfo.setLovID(df.formatCellValue(cell));

						else if (cell.getColumnIndex() == 1)
							lovInfo.setLovName(df.formatCellValue(cell));

						else if (cell.getColumnIndex() == 2)
							lovInfo.setLovGroupID(df.formatCellValue(cell));

						else if (cell.getColumnIndex() == 3)
							lovInfo.setReferenced(df.formatCellValue(cell));

						else if (cell.getColumnIndex() == 4)
							lovInfo.setAllowUserValueAddition(df.formatCellValue(cell));

						else if (cell.getColumnIndex() == 5)
							lovInfo.setUserValueID(df.formatCellValue(cell));

						else if (cell.getColumnIndex() == 6)
							lovInfo.setBaseType(df.formatCellValue(cell));

						else if (cell.getColumnIndex() == 7)
							lovInfo.setMinValue(df.formatCellValue(cell));

						else if (cell.getColumnIndex() == 8)
							lovInfo.setMaxValue(df.formatCellValue(cell));

						else if (cell.getColumnIndex() == 9)
							lovInfo.setMaxLength(df.formatCellValue(cell));

						else if (cell.getColumnIndex() == 10)
							lovInfo.setInputMask(df.formatCellValue(cell));

						else if (cell.getColumnIndex() == 11)
							lovInfo.setValueID(df.formatCellValue(cell));

						else if (cell.getColumnIndex() == 12)
							lovInfo.setValueName(df.formatCellValue(cell));

						else if (cell.getColumnIndex() > 12 && cell.getColumnIndex() < headerList.size()) {
							Map<String, String> map = lovInfo.getLovMetadata();
							if (map == null) {
								map = lovInfo.createMetadatMap();
							}
							map.put(headerList.get(cell.getColumnIndex()), df.formatCellValue(cell));
							lovInfo.setLovMetadata(map);
						}
					}

					if (!excelinfo.containsKey(id)) {
						ArrayList<LovExcelInfo> list = new ArrayList<LovExcelInfo>();
						list.add(lovInfo);
						excelinfo.put(id, list);
					} else {
						excelinfo.get(id).add(lovInfo);
					}

				}

			}

			for (Entry<String, ArrayList<LovExcelInfo>> inf : excelinfo.entrySet()) {
				ArrayList<LovExcelInfo> list = inf.getValue();
				for (LovExcelInfo key : list) {
					System.out.println(key.getValueID() + " -> " + key.getValueName());
				}
			}
			workbook.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
