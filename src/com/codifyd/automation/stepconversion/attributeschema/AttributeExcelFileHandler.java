package com.codifyd.automation.stepconversion.attributeschema;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Paths;
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

import com.codifyd.automation.stepconversion.util.ConfigHandler;
import com.codifyd.automation.stepconversion.util.FileConversionHandler;
import com.codifyd.automation.stepconversion.util.HandlerConstants;
import com.codifyd.automation.stepconversion.util.InputValidator;
import com.codifyd.automation.stepconversion.util.UserInputFileUtilDO;
import com.codifyd.automation.util.AutomationConstants;
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

public class AttributeExcelFileHandler implements FileConversionHandler {

	@Override
	public void handleFile(UserInputFileUtilDO userInput) throws Exception {

		try {
			// parse the input for errors
			InputValidator.validateExcelToXML(userInput);
			
			ConfigHandler configFile = userInput.getConfigFile();
			List<String> headerList = new ArrayList<String>();
			for (String key : configFile.keySet())
				headerList.add(key);

			// Read the Excel and build the UOM Objects
			ArrayList<AttributeExcelInfo> excelValues = new ArrayList<AttributeExcelInfo>();
			readExcel(new File(userInput.getInputPath()), excelValues,headerList);

			File outputFile = new File(Paths
					.get(new File(userInput.getOutputPath()).getPath(), userInput.getFilename())
					.toUri());

			// Initialize object factory and add unit values
			ObjectFactory objectFactory = new ObjectFactory();
			STEPProductInformation stepProductInformation = objectFactory.createSTEPProductInformation();
			stepProductInformation.setContextID(HandlerConstants.CONTEXT1);
			stepProductInformation.setWorkspaceID(HandlerConstants.MAIN);

			AttributeListType attributeList = objectFactory.createAttributeListType();
			List<AttributeType> attributeList1 = attributeList.getAttribute();

			for (AttributeExcelInfo attrInfo : excelValues) {
				AttributeType attribute = objectFactory.createAttributeType();

				attribute.setID(attrInfo.getAttributeID());

				NameType nameType = objectFactory.createNameType();
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
						Boolean.parseBoolean(attrInfo.getFullTextIndexed()) ? TrueFalseType.TRUE : TrueFalseType.FALSE);
				attribute.setDefaultUnitID(attrInfo.getDefault_Unit_ID());
				attribute.setMandatory(
						Boolean.parseBoolean(attrInfo.getMandatory()) ? TrueFalseType.TRUE : TrueFalseType.FALSE);
				attribute.setMultiValued(
						Boolean.parseBoolean(attrInfo.getMulti_Valued()) ? TrueFalseType.TRUE : TrueFalseType.FALSE);

				if (attrInfo.getType().equalsIgnoreCase(HandlerConstants.SPECIFICATION)
						|| attrInfo.getType().isEmpty()) {
					attribute.setProductMode(HandlerConstants.NORMAL);
				} else {
					attribute.setProductMode(HandlerConstants.PROPERTY);
				}

				attribute.setDerived(
						Boolean.parseBoolean(attrInfo.getCalculated()) ? TrueFalseType.TRUE : TrueFalseType.FALSE);

				if (attrInfo.getValidation_Base_Type().equalsIgnoreCase(AutomationConstants.LOV)
						&& !attrInfo.getList_of_Values().isEmpty()) {

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
					String[] unitIDs = null;
					if (attrInfo.getUnit_ID() != null) {
						unitIDs = attrInfo.getUnit_ID().split(";|,|\\|");
						for (String unitID : unitIDs) {
							if (!"".equals(unitID) || !unitID.trim().isEmpty()) {
								UnitLinkType unitLink = objectFactory.createUnitLinkType();
								unitLink.setUnitID(unitID);
								extUnitLinks.add(unitLink);
							}
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

				if (attrInfo.getValueTemplate() != null) {
					if (!attrInfo.getValueTemplate().trim().isEmpty()
							|| !"".equals(attrInfo.getValueTemplate().trim())) {
						ValueTemplateType templateType = objectFactory.createValueTemplateType();
						templateType.setContent(attrInfo.getValueTemplate());
						attribute.getValueTemplateOrUnitTemplate().add(templateType);
					}
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
				String[] dimensions = null;
				if (attrInfo.getDimension_Dependencies() != null) {
					dimensions = attrInfo.getDimension_Dependencies().split(";|,|\\|");
					for (String dimensionID : dimensions) {
						if (!dimensionID.trim().isEmpty() || !"".equals(dimensionID)) {
							DimensionLinkType dimensionLink = objectFactory.createDimensionLinkType();
							dimensionLink.setDimensionID(dimensionID);
							extDimensionLink.add(dimensionLink);
						}
					}
				}
				////

				List<LinkTypeType> extLinkType = attribute.getLinkType();
				String[] linkTypes = null;
				if (attrInfo.getLinkType() != null) {
					linkTypes = attrInfo.getLinkType().split(";|,|\\|");
					for (String linkTypeID : linkTypes) {
						if (!linkTypeID.trim().isEmpty() || !"".equals(linkTypeID)) {

							LinkTypeType linkType = objectFactory.createLinkTypeType();
							linkType.setLinkTypeID(linkTypeID);
							extLinkType.add(linkType);
						}
					}
				}

				attributeList1.add(attribute);
			}

			stepProductInformation.setAttributeList(attributeList);

			JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(stepProductInformation, outputFile);

			System.out.println("File Generated in path : " + outputFile.getAbsolutePath());

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}

	private void readExcel(File inputFIle, List<AttributeExcelInfo> excelValues, List<String> headerList) throws Exception {
		try {
			List<String> columnHeader = null;
			InputStream fs = new FileInputStream(inputFIle);

			// Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(fs);

			// Get first/desired sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(0);

			for (Iterator<Row> iterator = sheet.iterator(); iterator.hasNext();) {
				Row row = iterator.next();

				if (row.getRowNum() == 0) {
					columnHeader = new ArrayList<String>();
					for (Iterator<Cell> iterator2 = row.iterator(); iterator2.hasNext();) {
						Cell cell = iterator2.next();
						columnHeader.add(cell.getStringCellValue());
					}
				}

				if (row.getRowNum() > 0) {
					AttributeExcelInfo attributeInfo = new AttributeExcelInfo();
					DataFormatter df = new DataFormatter();
					for (Iterator<Cell> iterator2 = row.iterator(); iterator2.hasNext();) {
						Cell cell = iterator2.next();

						if (cell.getColumnIndex() == headerList.indexOf("STEP_ID")) {
							attributeInfo.setAttributeID(df.formatCellValue(cell));
						
						} else if (cell.getColumnIndex() == headerList.indexOf("STEP_Name")) {
							attributeInfo.setAttributeName(df.formatCellValue(cell));
						
						} else if (cell.getColumnIndex() == headerList.indexOf("Validation_Base_Type")) {
							attributeInfo.setValidation_Base_Type(df.formatCellValue(cell));
						
						} else if (cell.getColumnIndex() == headerList.indexOf("List_of_Values")) {
							attributeInfo.setList_of_Values(df.formatCellValue(cell));
						
						} else if (cell.getColumnIndex() == headerList.indexOf("Minimum_Value")) {
							attributeInfo.setMinimum_Value(df.formatCellValue(cell));
						
						} else if (cell.getColumnIndex() == headerList.indexOf("Maximum_Value")) {
							attributeInfo.setMaximum_Value(df.formatCellValue(cell));
						
						} else if (cell.getColumnIndex() == headerList.indexOf("Maximum_Length")) {
							attributeInfo.setMaximum_Length(df.formatCellValue(cell));
						
						} else if (cell.getColumnIndex() == headerList.indexOf("Mask")) {
							attributeInfo.setMask(df.formatCellValue(cell));
						
						} else if (cell.getColumnIndex() == headerList.indexOf("Type")) {
							attributeInfo.setType(df.formatCellValue(cell));
						
						} else if (cell.getColumnIndex() == headerList.indexOf("Mandatory")) {
							attributeInfo.setMandatory(df.formatCellValue(cell));
						
						} else if (cell.getColumnIndex() == headerList.indexOf("Multi_Valued")) {
							attributeInfo.setMulti_Valued(df.formatCellValue(cell));
						
						} else if (cell.getColumnIndex() == headerList.indexOf("Externally_Maintained")) {
							attributeInfo.setExternallyMaitained(df.formatCellValue(cell));
						
						} else if (cell.getColumnIndex() == headerList.indexOf("Calculated")) {
							attributeInfo.setCalculated(df.formatCellValue(cell));
						
						} else if (cell.getColumnIndex() == headerList.indexOf("Value_Template")) {
							attributeInfo.setValueTemplate(df.formatCellValue(cell));
						
						} else if (cell.getColumnIndex() == headerList.indexOf("Validity")) {
							attributeInfo.setValidity(df.formatCellValue(cell));
						
						} else if (cell.getColumnIndex() == headerList.indexOf("LinkType")) {
							attributeInfo.setLinkType(df.formatCellValue(cell));
						
						} else if (cell.getColumnIndex() == headerList.indexOf("Unit_ID")) {
							attributeInfo.setUnit_ID(df.formatCellValue(cell));
						
						} else if (cell.getColumnIndex() == headerList.indexOf("Unit_Name")) {
							attributeInfo.setUnit_Name(df.formatCellValue(cell));
						
						} else if (cell.getColumnIndex() == headerList.indexOf("Default_Unit_ID")) {
							attributeInfo.setDefault_Unit_ID(df.formatCellValue(cell));
						
						} else if (cell.getColumnIndex() == headerList.indexOf("Default_Unit_Name")) {
							attributeInfo.setDefault_Unit_Name(df.formatCellValue(cell));
						
						} else if (cell.getColumnIndex() == headerList.indexOf("Attribute_Group_Reference_ID")) {
							attributeInfo.setAttribute_Group_Reference_ID(df.formatCellValue(cell));
						
						} else if (cell.getColumnIndex() == headerList.indexOf("Attribute_Group_Reference_Name")) {
							attributeInfo.setAttribute_Group_Reference_Name(df.formatCellValue(cell));
						
						} else if (cell.getColumnIndex() == headerList.indexOf("Full_Text_Indexible")) {
							attributeInfo.setFullTextIndexed(df.formatCellValue(cell));
						
						} else if (cell.getColumnIndex() == headerList.indexOf("Completeness_Score")) {
							attributeInfo.setCompletenessScore(df.formatCellValue(cell));
						
						} else if (cell.getColumnIndex() == headerList.indexOf("Hierarchical_Filtering")) {
							attributeInfo.setHierarchialFiltering(df.formatCellValue(cell));
						
						} else if (cell.getColumnIndex() == headerList.indexOf("Classification_Hierarchical_Filtering")) {
							attributeInfo.setClassificationHierarchialFiltering(df.formatCellValue(cell));
						
						} else if (cell.getColumnIndex() == headerList.indexOf("Dimension_Dependencies")) {
							attributeInfo.setDimension_Dependencies(df.formatCellValue(cell));
						
						} else if (cell.getColumnIndex() > 26 && cell.getColumnIndex() < columnHeader.size()) {
							Map<String, String> map = attributeInfo.getAttributeMetadata();
							if (map == null) {
								map = attributeInfo.createMetadatMap();
							}
							map.put(columnHeader.get(cell.getColumnIndex()), df.formatCellValue(cell));
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
			throw new Exception(e.getMessage());
		}
	}
}
