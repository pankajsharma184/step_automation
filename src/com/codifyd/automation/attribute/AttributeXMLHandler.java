package com.codifyd.automation.attribute;

import static com.codifyd.automation.util.ErrorLog.getErrorLog;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.codifyd.automation.util.UserInputFileUtilDO;
import com.codifyd.stepxsd.AttributeGroupLinkType;
import com.codifyd.stepxsd.AttributeListType;
import com.codifyd.stepxsd.AttributeType;
import com.codifyd.stepxsd.DimensionLinkType;
import com.codifyd.stepxsd.LinkTypeType;
import com.codifyd.stepxsd.ListOfValueLinkType;
import com.codifyd.stepxsd.MetaDataType;
import com.codifyd.stepxsd.NameType;
import com.codifyd.stepxsd.ObjectFactory;
import com.codifyd.stepxsd.STEPProductInformation;
import com.codifyd.stepxsd.TrueFalseType;
import com.codifyd.stepxsd.UnitLinkType;
import com.codifyd.stepxsd.UserTypeLinkType;
import com.codifyd.stepxsd.ValidationType;
import com.codifyd.stepxsd.ValueTemplateType;
import com.codifyd.stepxsd.ValueType;

public class AttributeXMLHandler {

	private static final String LOV = "lov";
	private static final String PROPERTY = "Property";
	private static final String NORMAL = "Normal";
	private static final String SPECIFICATION = "Specification";
	private static final String MAIN = "Main";
	private static final String CONTEXT1 = "Context1";

	public void handleFile(UserInputFileUtilDO userInputFileUtilDO) throws Exception {

//		Runtime runtime = Runtime.getRuntime();
//		long beforeUsedMem = runtime.totalMemory() - runtime.freeMemory();
//		System.out.println(beforeUsedMem);

		try {

			// Read the Excel and build the UOM Objects
			ArrayList<AttributeInfo> excelValues = new ArrayList<AttributeInfo>();
			readExcel(new File(userInputFileUtilDO.getInputPath()), excelValues);

			// System.out.println(excelValues.size());

			File file = new File(userInputFileUtilDO.getOutputPath() + "\\" + userInputFileUtilDO.getFilename());

			// Initialize object factory and add unit values
			ObjectFactory objectFactory = new ObjectFactory();
			STEPProductInformation stepProductInformation = objectFactory.createSTEPProductInformation();
			stepProductInformation.setContextID(CONTEXT1);
			stepProductInformation.setWorkspaceID(MAIN);

			AttributeListType attributeList = objectFactory.createAttributeListType();
			List<AttributeType> attributeList1 = attributeList.getAttribute();

			for (AttributeInfo attrInfo : excelValues) {
				AttributeType attribute = objectFactory.createAttributeType();

				NameType nameType = objectFactory.createNameType();

				attribute.setID(attrInfo.getAttributeID());
				nameType.setContent(attrInfo.getAttributeName());
				attribute.getName().add(nameType);
				attribute.setHierarchicalFiltering(
						Boolean.parseBoolean(attrInfo.getHierarchialFiltering()) ? TrueFalseType.TRUE
								: TrueFalseType.FALSE);
				attribute.setClassificationHierarchicalFiltering(
						Boolean.parseBoolean(attrInfo.getClassificationHierarchialFiltering()) ? TrueFalseType.TRUE
								: TrueFalseType.FALSE);
				attribute.setExternallyMaintained(
						Boolean.parseBoolean(attrInfo.getExternallyMaitained()) ? TrueFalseType.TRUE
								: TrueFalseType.FALSE);
				attribute.setFullTextIndexed(
						Boolean.parseBoolean(attrInfo.getFullTextIndexed().toLowerCase()) ? TrueFalseType.TRUE
								: TrueFalseType.FALSE);
				attribute.setDefaultUnitID(attrInfo.getDefault_Unit_ID());
				attribute.setMandatory(Boolean.parseBoolean(attrInfo.getMandatory().toLowerCase()) ? TrueFalseType.TRUE
						: TrueFalseType.FALSE);
				attribute.setMultiValued(
						Boolean.parseBoolean(attrInfo.getMulti_Valued()) ? TrueFalseType.TRUE : TrueFalseType.FALSE);
				if (attrInfo.getType().trim().equalsIgnoreCase(SPECIFICATION)
						|| attrInfo.getType().trim().equalsIgnoreCase("")) {
					attribute.setProductMode(NORMAL);
				} else {
					attribute.setProductMode(PROPERTY);
				}
				attribute.setDerived(
						Boolean.parseBoolean(attrInfo.getCalculated()) ? TrueFalseType.TRUE : TrueFalseType.FALSE);

				if (attrInfo.getValidation_Base_Type().equalsIgnoreCase(LOV)
						&& !"".equals(attrInfo.getList_of_Values())) {

					ListOfValueLinkType listOfValueLink = objectFactory.createListOfValueLinkType();

					listOfValueLink.setListOfValueID(attrInfo.getList_of_Values());
					attribute.setListOfValueLink(listOfValueLink);
				} else {
					ValidationType validation = objectFactory.createValidationType();

					validation.setBaseType(attrInfo.getValidation_Base_Type());
					validation.setMinValue(attrInfo.getMaximum_Value());
					validation.setMaxValue(attrInfo.getMaximum_Value());
					validation.setMaxLength(attrInfo.getMaximum_Length());
					validation.setInputMask(attrInfo.getMask());

					List<UnitLinkType> extUnitLinks = validation.getUnitLink();

					String[] unitIDs = attrInfo.getUnit_ID().split(";|,|\\|");
					for (String unitID : unitIDs) {
						if (!"".equals(unitID) || !unitID.trim().isEmpty()) {
							UnitLinkType unitLink = objectFactory.createUnitLinkType();
							unitLink.setUnitID(unitID);
							extUnitLinks.add(unitLink);
						}
					}

					attribute.setValidation(validation);
				}

				List<UserTypeLinkType> extUserType = attribute.getUserTypeLink();
				if (attrInfo.getValidity() != null && !"".equals(attrInfo.getValidity())) {
					String[] userTypeLinks = attrInfo.getValidity().split(";|,|\\|");
					for (String userTypeID : userTypeLinks) {
						if ("".equals(userTypeID) || !userTypeID.trim().isEmpty()) {
							UserTypeLinkType userTypeLink = objectFactory.createUserTypeLinkType();
							userTypeLink.setUserTypeID(userTypeID);

							extUserType.add(userTypeLink);
						}
					}
				}

//				attribute.setValueTemplate(attrInfo.getValueTemplate());

				if (!attrInfo.getValueTemplate().trim().isEmpty() || !"".equals(attrInfo.getValueTemplate().trim())) {
					ValueTemplateType templateType = objectFactory.createValueTemplateType();
					templateType.setContent(attrInfo.getValueTemplate());
					attribute.getValueTemplateOrUnitTemplate().add(templateType);
				}

				List<AttributeGroupLinkType> extAttrGRP = attribute.getAttributeGroupLink();
				if (attrInfo.getAttribute_Group_Reference_ID() != null
						&& !"".equals(attrInfo.getAttribute_Group_Reference_ID())) {
					String[] attrGrpIDs = attrInfo.getAttribute_Group_Reference_ID().split(";|,|\\|");
					for (String attrGrpID : attrGrpIDs) {
						if ("".equals(attrGrpID) || !attrGrpID.trim().isEmpty()) {
							AttributeGroupLinkType attrGrpLink = objectFactory.createAttributeGroupLinkType();
							attrGrpLink.setAttributeGroupID(attrGrpID);
							extAttrGRP.add(attrGrpLink);
						}
					}
				}

				MetaDataType metaData = objectFactory.createMetaDataType();
				Map<String, String> metadataMap = attrInfo.getAttributeMetadata();
				List<Object> valueList = metaData.getValueOrMultiValueOrValueGroup();
				for (Entry<String, String> m : metadataMap.entrySet()) {
					if (!"".equals(m.getValue()) || !m.getValue().trim().isEmpty()) {
						ValueType value = objectFactory.createValueType();
						value.setAttributeID(m.getKey());
						value.setContent(m.getValue());
						valueList.add(value);
					}
				}
				if (!valueList.isEmpty()) {
					attribute.setMetaData(metaData);
				}

				////

				List<DimensionLinkType> extDimensionLink = attribute.getDimensionLink();
				String[] dimensions = attrInfo.getDimension_Dependencies().split(";|,|\\|");
				for (String dimensionID : dimensions) {
					if (!dimensionID.trim().isEmpty() || !"".equals(dimensionID)) {
						DimensionLinkType dimensionLink = objectFactory.createDimensionLinkType();
						dimensionLink.setDimensionID(dimensionID);
						extDimensionLink.add(dimensionLink);
					}
				}

				////

				List<LinkTypeType> extLinkType = attribute.getLinkType();
				String[] linkTypes = attrInfo.getLinkType().split(";|,|\\|");
				for (String linkTypeID : linkTypes) {
					if (!linkTypeID.trim().isEmpty() || !"".equals(linkTypeID)) {

						LinkTypeType linkType = objectFactory.createLinkTypeType();
						linkType.setLinkTypeID(linkTypeID);
						extLinkType.add(linkType);
					}
				}

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
			e.printStackTrace();
			String path = new File(userInputFileUtilDO.getOutputPath()).getPath().toString();
			getErrorLog(path, e);
			throw new Exception("Error.lo File Generated At : " + path);
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

	private void readExcel(File file, List<AttributeInfo> excelValues) throws Exception {
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

				if (row.getRowNum() > 0) {
					AttributeInfo attributeInfo = new AttributeInfo();
					DataFormatter df = new DataFormatter();
					for (Iterator<Cell> iterator2 = row.iterator(); iterator2.hasNext();) {
						Cell cell = (Cell) iterator2.next();

						if (cell.getColumnIndex() == 0) {
							attributeInfo.setAttributeID(df.formatCellValue(cell));
						} else if (cell.getColumnIndex() == 1) {
							attributeInfo.setAttributeName(df.formatCellValue(cell));
						} else if (cell.getColumnIndex() == 2) {
							attributeInfo.setValidation_Base_Type(df.formatCellValue(cell));
						} else if (cell.getColumnIndex() == 3) {
							attributeInfo.setList_of_Values(df.formatCellValue(cell));
						} else if (cell.getColumnIndex() == 4) {
							attributeInfo.setMinimum_Value(df.formatCellValue(cell));
						} else if (cell.getColumnIndex() == 5) {
							attributeInfo.setMaximum_Value(df.formatCellValue(cell));
						} else if (cell.getColumnIndex() == 6) {
							attributeInfo.setMaximum_Length(df.formatCellValue(cell));
						} else if (cell.getColumnIndex() == 7) {
							attributeInfo.setMask(df.formatCellValue(cell));
						} else if (cell.getColumnIndex() == 8) {
							attributeInfo.setType(df.formatCellValue(cell));
						} else if (cell.getColumnIndex() == 9) {
							attributeInfo.setMandatory(df.formatCellValue(cell));
						} else if (cell.getColumnIndex() == 10) {
							attributeInfo.setMulti_Valued(df.formatCellValue(cell));
						} else if (cell.getColumnIndex() == 11) {
							attributeInfo.setExternallyMaitained(df.formatCellValue(cell));
						} else if (cell.getColumnIndex() == 12) {
							attributeInfo.setCalculated(df.formatCellValue(cell));
						} else if (cell.getColumnIndex() == 13) {
							attributeInfo.setValueTemplate(df.formatCellValue(cell));
						} else if (cell.getColumnIndex() == 14) {
							attributeInfo.setValidity(df.formatCellValue(cell));
						} else if (cell.getColumnIndex() == 15) {
							attributeInfo.setLinkType(df.formatCellValue(cell));
						} else if (cell.getColumnIndex() == 16) {
							attributeInfo.setUnit_ID(df.formatCellValue(cell));
						} else if (cell.getColumnIndex() == 17) {
							attributeInfo.setUnit_Name(df.formatCellValue(cell));
						} else if (cell.getColumnIndex() == 18) {
							attributeInfo.setDefault_Unit_ID(df.formatCellValue(cell));
						} else if (cell.getColumnIndex() == 19) {
							attributeInfo.setDefault_Unit_Name(df.formatCellValue(cell));
						} else if (cell.getColumnIndex() == 20) {
							attributeInfo.setAttribute_Group_Reference_ID(df.formatCellValue(cell));
						} else if (cell.getColumnIndex() == 21) {
							attributeInfo.setAttribute_Group_Reference_Name(df.formatCellValue(cell));
						} else if (cell.getColumnIndex() == 22) {
							attributeInfo.setFullTextIndexed(df.formatCellValue(cell));
						} else if (cell.getColumnIndex() == 23) {
							attributeInfo.setCompletenessScore(df.formatCellValue(cell));
						} else if (cell.getColumnIndex() == 24) {
							attributeInfo.setHierarchialFiltering(df.formatCellValue(cell));
						} else if (cell.getColumnIndex() == 25) {
							attributeInfo.setClassificationHierarchialFiltering(df.formatCellValue(cell));
						} else if (cell.getColumnIndex() == 26) {
							attributeInfo.setDimension_Dependencies(df.formatCellValue(cell));
						} else if (cell.getColumnIndex() > 26 && cell.getColumnIndex() < headerList.size()) {
							Map<String, String> map = attributeInfo.getAttributeMetadata();
							if (map == null) {
								map = attributeInfo.createMetadatMap();
							}
							map.put(headerList.get(cell.getColumnIndex()), df.formatCellValue(cell));
							attributeInfo.setAttributeMetadata(map);
						}

					}

					excelValues.add(attributeInfo);
				}

			}
			workbook.close();
			fs.close();
		} catch (Exception e) {
			e.printStackTrace();
			getErrorLog(file.getPath(), e);
			throw new Exception("Error.lo File Generated At : " + file.getPath().toString());
		}
	}

}
