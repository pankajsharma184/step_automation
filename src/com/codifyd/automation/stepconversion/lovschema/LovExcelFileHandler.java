package com.codifyd.automation.stepconversion.lovschema;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.codifyd.automation.stepconversion.util.ConfigHandler;
import com.codifyd.automation.stepconversion.util.FileConversionHandler;
import com.codifyd.automation.stepconversion.util.HandlerConstants;
import com.codifyd.automation.stepconversion.util.InputValidator;
import com.codifyd.automation.stepconversion.util.UserInputFileUtilDO;
import com.codifyd.stepxsd.ListOfValueType;
import com.codifyd.stepxsd.ListsOfValuesType;
import com.codifyd.stepxsd.MetaDataType;
import com.codifyd.stepxsd.NameType;
import com.codifyd.stepxsd.ObjectFactory;
import com.codifyd.stepxsd.STEPProductInformation;
import com.codifyd.stepxsd.TrueFalseType;
import com.codifyd.stepxsd.ValidationType;
import com.codifyd.stepxsd.ValueType;

public class LovExcelFileHandler implements FileConversionHandler {

	public void handleFile(UserInputFileUtilDO userInput) throws Exception {

		try {

			// parse the input for errors
			InputValidator.validateExcelToXML(userInput);

			ConfigHandler configFile = userInput.getConfigFile();
			List<String> headerList = new ArrayList<String>();
			for (String key : configFile.keySet())
				headerList.add(key);

			// Read the Excel and build the UOM Objects
			TreeMap<String, ArrayList<LovExcelInfo>> excelinfo = new TreeMap<String, ArrayList<LovExcelInfo>>();
			readExcel(new File(userInput.getInputPath()), excelinfo, headerList);

			String delimeter = userInput.getDelimiters();
			if (delimeter.equals("|")) {
				delimeter = "\\|";
			}

			File outputFile = new File(
					Paths.get(new File(userInput.getOutputPath()).getPath(), userInput.getFilename()).toUri());

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

					if (valueinfo.getValueID() != null && !valueinfo.getValueID().trim().equals("")) {
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

			jaxbMarshaller.marshal(stepProductInformation, outputFile);

			System.out.println("File Generated in path : " + outputFile.getAbsolutePath());

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	private void readExcel(File inputFile, TreeMap<String, ArrayList<LovExcelInfo>> excelinfo, List<String> headerList)
			throws Exception {
		try {
			InputStream fs = new FileInputStream(inputFile);

			// Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(fs);

			// Get first/desired sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(0);
			ArrayList<String> columnHeader = new ArrayList<String>();
			for (Iterator<Row> iterator = sheet.iterator(); iterator.hasNext();) {
				DataFormatter df = new DataFormatter();
				Row row = (Row) iterator.next();
				if (row.getRowNum() == 0)
					for (Iterator<Cell> iterator2 = row.iterator(); iterator2.hasNext();)
						columnHeader.add(df.formatCellValue(iterator2.next()));

				if (row.getRowNum() > 0) {
					LovExcelInfo lovInfo = new LovExcelInfo();
					String id = df.formatCellValue(row.getCell(headerList.indexOf("")));
					for (Iterator<Cell> iterator2 = row.iterator(); iterator2.hasNext();) {
						Cell cell = (Cell) iterator2.next();

						if (cell.getColumnIndex() == headerList.indexOf("LOV_ID"))
							lovInfo.setLovID(df.formatCellValue(cell));

						else if (cell.getColumnIndex() == headerList.indexOf("LOV_Name"))
							lovInfo.setLovName(df.formatCellValue(cell));

						else if (cell.getColumnIndex() == headerList.indexOf("LOV_Group_ID"))
							lovInfo.setLovGroupID(df.formatCellValue(cell));

						else if (cell.getColumnIndex() == headerList.indexOf("Referenced"))
							lovInfo.setReferenced(df.formatCellValue(cell));

						else if (cell.getColumnIndex() == headerList.indexOf("Allow_User_Value_Addition"))
							lovInfo.setAllowUserValueAddition(df.formatCellValue(cell));

						else if (cell.getColumnIndex() == headerList.indexOf("User_Value_ID"))
							lovInfo.setUserValueID(df.formatCellValue(cell));

						else if (cell.getColumnIndex() == headerList.indexOf("Validation_BaseType"))
							lovInfo.setBaseType(df.formatCellValue(cell));

						else if (cell.getColumnIndex() == headerList.indexOf("Minimum_Value"))
							lovInfo.setMinValue(df.formatCellValue(cell));

						else if (cell.getColumnIndex() == headerList.indexOf("Maximum_Value"))
							lovInfo.setMaxValue(df.formatCellValue(cell));

						else if (cell.getColumnIndex() == headerList.indexOf("Maximum_Length"))
							lovInfo.setMaxLength(df.formatCellValue(cell));

						else if (cell.getColumnIndex() == headerList.indexOf("Input_Mask"))
							lovInfo.setInputMask(df.formatCellValue(cell));

						else if (cell.getColumnIndex() == headerList.indexOf("Value_ID"))
							lovInfo.setValueID(df.formatCellValue(cell));

						else if (cell.getColumnIndex() == headerList.indexOf("Value_Name"))
							lovInfo.setValueName(df.formatCellValue(cell));

						else if (cell.getColumnIndex() > 12 && cell.getColumnIndex() < columnHeader.size()) {
							Map<String, String> map = lovInfo.getLovMetadata();
							if (map == null) {
								map = lovInfo.createMetadatMap();
							}
							map.put(columnHeader.get(cell.getColumnIndex()), df.formatCellValue(cell));
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
			workbook.close();

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

}
