package com.codifyd.automation.attribute.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.codifyd.automation.util.UserInputFileUtilDO;


public class AttributeXMLHandler {

	public  void handleFile(UserInputFileUtilDO userInputFileUtilDO) {

		
		Runtime runtime = Runtime.getRuntime();
//		long beforeUsedMem = runtime.totalMemory() - runtime.freeMemory();
//		System.out.println(beforeUsedMem);

		try {

			// Read the Excel and build the UOM Objects
			List<AttributeInfo> excelValues = new ArrayList();
			readExcel(new File(userInputFileUtilDO.getInputPath()), excelValues);
			// System.out.println(excelValues.size());

			File file = new File(userInputFileUtilDO.getOutputPath()+ "\\" +userInputFileUtilDO.getFilename());

			// Initialize object factory and add unit values
			ObjectFactory objectFactory = new ObjectFactory();
			STEPProductInformation stepProductInformation = objectFactory.createSTEPProductInformation();
			stepProductInformation.setContextID("Context1");
			stepProductInformation.setWorkspaceID("Main");

			STEPProductInformation.AttributeList attributeList = objectFactory
					.createSTEPProductInformationAttributeList();
			List<STEPProductInformation.AttributeList.Attribute> attributeList1 = attributeList.getAttribute();

			for (AttributeInfo attrInfo : excelValues) {
				STEPProductInformation.AttributeList.Attribute attribute = objectFactory
						.createSTEPProductInformationAttributeListAttribute();

				attribute.setID(attrInfo.getAttributeID());
				attribute.setName(attrInfo.getAttributeName());
				attribute.setHierarchicalFiltering(attrInfo.getHierarchialFiltering());
				attribute.setExternallyMaintained(attrInfo.getExternallyMaitained());
				attribute.setFullTextIndexed(attrInfo.getFullTextIndexed());
				attribute.setDefaultUnitID(attrInfo.getDefault_Unit_ID());
				attribute.setMandatory(attrInfo.getMandatory());
				attribute.setMultiValued(attrInfo.getMulti_Valued());

				if (!"".equals(attrInfo.getList_of_Values())) {

					STEPProductInformation.AttributeList.Attribute.ListOfValueLink listOfValueLink = objectFactory
							.createSTEPProductInformationAttributeListAttributeListOfValueLink();

					listOfValueLink.setListOfValueID(attrInfo.getList_of_Values());
					attribute.setListOfValueLink(listOfValueLink);
				} else {

					STEPProductInformation.AttributeList.Attribute.Validation validation = objectFactory
							.createSTEPProductInformationAttributeListAttributeValidation();

					validation.setBaseType(attrInfo.getValidation_Base_Type());
					validation.setMinValue(attrInfo.getMaximum_Value());
					validation.setMaxValue(attrInfo.getMaximum_Value());
					validation.setMaxLength(attrInfo.getMaximum_Length());
					validation.setInputMask(attrInfo.getMask());

					attribute.setValidation(validation);
				}

				List<STEPProductInformation.AttributeList.Attribute.UserTypeLink> extUserType = attribute
						.getUserTypeLink();
				if(attrInfo.getValidity() != null && !"".equals(attrInfo.getValidity())) {
				String[] userTypeLinks = attrInfo.getValidity().split("\\|");
				for (String userTypeID : userTypeLinks) {
					STEPProductInformation.AttributeList.Attribute.UserTypeLink userTypeLink = objectFactory
							.createSTEPProductInformationAttributeListAttributeUserTypeLink();
					userTypeLink.setUserTypeID(userTypeID);

					extUserType.add(userTypeLink);
				}
				}
				List<STEPProductInformation.AttributeList.Attribute.AttributeGroupLink> extAttrGRP = attribute
						.getAttributeGroupLink();
				if(attrInfo.getAttribute_Group_Reference_ID() != null && !"".equals(attrInfo.getAttribute_Group_Reference_ID())) {
				String[] attrGrpIDs = attrInfo.getAttribute_Group_Reference_ID().split("\\|");
				for (String attrGrpID : attrGrpIDs) {
					STEPProductInformation.AttributeList.Attribute.AttributeGroupLink attrGrpLink = objectFactory
							.createSTEPProductInformationAttributeListAttributeAttributeGroupLink();
					attrGrpLink.setAttributeGroupID(attrGrpID);

					extAttrGRP.add(attrGrpLink);
				}
				}

				STEPProductInformation.AttributeList.Attribute.MetaData metaData = objectFactory
						.createSTEPProductInformationAttributeListAttributeMetaData();
				Map<String, String> metadataMap = attrInfo.getAttributeMetadata();
				List valueList = metaData.getValue();
				for (Map.Entry<String, String> m : metadataMap.entrySet()) {
					STEPProductInformation.AttributeList.Attribute.MetaData.Value value = objectFactory
							.createSTEPProductInformationAttributeListAttributeMetaDataValue();
					value.setAttributeID(m.getKey());
					value.setValue(m.getValue());
					valueList.add(value);
				}
				attribute.setMetaData(metaData);

				STEPProductInformation.AttributeList.Attribute.DimensionLink dimensionLink = objectFactory
						.createSTEPProductInformationAttributeListAttributeDimensionLink();
				dimensionLink.setDimensionID(attrInfo.getDimension_Dependencies());
				attribute.getDimensionLink().add(dimensionLink);

				STEPProductInformation.AttributeList.Attribute.LinkType linkType = objectFactory
						.createSTEPProductInformationAttributeListAttributeLinkType();
				linkType.setLinkTypeID(attrInfo.getLinkType());
				attribute.getLinkType().add(linkType);

				attributeList1.add(attribute);
			}

			stepProductInformation.setAttributeList(attributeList);

			JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(stepProductInformation, file);
			// jaxbMarshaller.marshal(stepProductInformation, System.out);

			System.out.println("File Generated in path : " + file.getAbsolutePath());

		} catch (Exception e) {
			System.out.print("Exception : "+e.getMessage());
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

	private  void readExcel(File file, List<AttributeInfo> excelValues) {
		try {
			List<String> headerList = null;
			InputStream fs = new FileInputStream(file);

			// Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(fs);

			// Get first/desired sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(0);

			for (Iterator<Row> iterator = sheet.iterator(); iterator.hasNext();) {
				Row row = (Row) iterator.next();

				if (row.getRowNum() == 0) {
					headerList = new ArrayList<String>();
					for (Iterator<Cell> iterator2 = row.iterator(); iterator2.hasNext();) {
						Cell cell = (Cell) iterator2.next();
						headerList.add(cell.getStringCellValue());
					}
				}

				if (row.getRowNum() != 0) {
					AttributeInfo attributeInfo = new AttributeInfo();

					for (Iterator<Cell> iterator2 = row.iterator(); iterator2.hasNext();) {
						Cell cell = (Cell) iterator2.next();

						if (cell.getColumnIndex() == 0) {
							attributeInfo.setAttributeID(cell.getStringCellValue());
						}
						if (cell.getColumnIndex() == 1) {
							attributeInfo.setAttributeName(cell.getStringCellValue());
						}
						if (cell.getColumnIndex() == 2) {
							attributeInfo.setFullTextIndexed(cell.getStringCellValue());
						}
						if (cell.getColumnIndex() == 3) {
							attributeInfo.setExternallyMaitained(cell.getStringCellValue());
						}
						if (cell.getColumnIndex() == 4) {
							attributeInfo.setCompletenessScore(cell.getStringCellValue());
						}
						if (cell.getColumnIndex() == 5) {
							attributeInfo.setHierarchialFiltering(cell.getStringCellValue());
						}
						if (cell.getColumnIndex() == 6) {
						}
						if (cell.getColumnIndex() == 7) {
							attributeInfo.setType(cell.getStringCellValue());
						}
						if (cell.getColumnIndex() == 8) {
							attributeInfo.setDimension_Dependencies(cell.getStringCellValue());
						}
						if (cell.getColumnIndex() == 9) {
							attributeInfo.setMandatory(cell.getStringCellValue());
						}
						if (cell.getColumnIndex() == 10) {
							attributeInfo.setValidation_Base_Type(cell.getStringCellValue());
						}
						if (cell.getColumnIndex() == 11) {
							attributeInfo.setList_of_Values(cell.getStringCellValue());
						}
						if (cell.getColumnIndex() == 12) {
							attributeInfo.setMulti_Valued(cell.getStringCellValue());
						}
						if (cell.getColumnIndex() == 13) {
							attributeInfo.setMask(cell.getStringCellValue());
						}
						if (cell.getColumnIndex() == 14) {
							if (cell.getCellTypeEnum().toString().equals("STRING")) {
								attributeInfo.setMinimum_Value(cell.getStringCellValue());
							} else {
								attributeInfo.setMinimum_Value(String.valueOf(cell.getNumericCellValue()));
							}
						}
						if (cell.getColumnIndex() == 15) {
							if (cell.getCellTypeEnum().toString().equals("STRING")) {
								attributeInfo.setMaximum_Value(cell.getStringCellValue());
							} else {
								attributeInfo.setMaximum_Value(String.valueOf(cell.getNumericCellValue()));
							}
						}
						if (cell.getColumnIndex() == 16) {
							if (cell.getCellTypeEnum().toString().equals("STRING")) {
								attributeInfo.setMaximum_Length(cell.getStringCellValue());
							} else {
								attributeInfo.setMaximum_Length(String.valueOf(cell.getNumericCellValue()));
							}
						}
						if (cell.getColumnIndex() == 17) {
							attributeInfo.setUnit_ID(cell.getStringCellValue());
						}
						if (cell.getColumnIndex() == 18) {
							attributeInfo.setUnit_Name(cell.getStringCellValue());
						}
						if (cell.getColumnIndex() == 19) {
							attributeInfo.setUnit_Description(cell.getStringCellValue());
						}
						if (cell.getColumnIndex() == 20) {
							attributeInfo.setDefault_Unit_ID(cell.getStringCellValue());
						}
						if (cell.getColumnIndex() == 21) {
							attributeInfo.setDefault_Unit_Name(cell.getStringCellValue());
						}
						if (cell.getColumnIndex() == 22) {
							attributeInfo.setDefault_Unit_Description(cell.getStringCellValue());
						}
						if (cell.getColumnIndex() == 23) {
							attributeInfo.setAttribute_Group_Reference_ID(cell.getStringCellValue());
						}
						if (cell.getColumnIndex() == 24) {
							attributeInfo.setValidity(cell.getStringCellValue());
						}
						if (cell.getColumnIndex() == 25) {
							attributeInfo.setLinkType(cell.getStringCellValue());
						}

						if (cell.getColumnIndex() > 25) {
							Map<String, String> map = attributeInfo.getAttributeMetadata();
							if (map == null) {
								map = attributeInfo.createMetadatMap();
							}

							if (cell.getCellTypeEnum().toString().equals("STRING")) {
								map.put(headerList.get(cell.getColumnIndex()), cell.getStringCellValue());
							} else {
								map.put(headerList.get(cell.getColumnIndex()),
										String.valueOf(cell.getNumericCellValue()));
							}
							attributeInfo.setAttributeMetadata(map);
						}

					}

					excelValues.add(attributeInfo);
				}
			}
			fs.close();
		} catch (Exception ioe) {
			ioe.printStackTrace();
		}
	}

}
