package com.codifyd.automation.stepconversion.attributeschema;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.codifyd.automation.stepconversion.util.ConfigHandler;
import com.codifyd.automation.stepconversion.util.FileConversionHandler;
import com.codifyd.automation.stepconversion.util.HandlerConstants;
import com.codifyd.automation.stepconversion.util.InputValidator;
import com.codifyd.automation.stepconversion.util.UserInputFileUtilDO;
import com.codifyd.automation.stepconversion.util.ExcelWorkbookUtility;
import com.codifyd.stepxsd.AttributeGroupLinkType;
import com.codifyd.stepxsd.AttributeType;
import com.codifyd.stepxsd.DimensionLinkType;
import com.codifyd.stepxsd.LinkTypeType;
import com.codifyd.stepxsd.STEPProductInformation;
import com.codifyd.stepxsd.TrueFalseType;
import com.codifyd.stepxsd.UnitLinkType;
import com.codifyd.stepxsd.UnitType;
import com.codifyd.stepxsd.UserTypeLinkType;
import com.codifyd.stepxsd.ValueTemplateType;
import com.codifyd.stepxsd.ValueType;

public class AttributeXMLFileHandler implements FileConversionHandler {

	// public static void main(String[] args) {
	@Override
	public void handleFile(UserInputFileUtilDO userInputFileUtilDO) throws Exception {
		try {
			// parse the input for errors
			InputValidator.validateXMLToExcel(userInputFileUtilDO);

			File inputFile = new File(userInputFileUtilDO.getInputPath());
			File outputFile = new File(Paths
					.get(new File(userInputFileUtilDO.getOutputPath()).getPath(), userInputFileUtilDO.getFilename())
					.toUri());
			ConfigHandler configFile = userInputFileUtilDO.getConfigFile();
			String delimiter = userInputFileUtilDO.getDelimiters();

			JAXBContext jaxbContext = JAXBContext.newInstance(STEPProductInformation.class);
			Unmarshaller jaxbUnMarshaller = jaxbContext.createUnmarshaller();
			STEPProductInformation objectFactory = (STEPProductInformation) jaxbUnMarshaller.unmarshal(inputFile);

			writeExcel(objectFactory, outputFile, configFile, delimiter);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}

	private static void writeExcel(STEPProductInformation objectFactory, File file, ConfigHandler configFile,
			String delimiter) throws Exception {

		try {

			Set<String> metaDataHeaderAttributes = new HashSet<String>();

			LinkedList<String> headerList1 = new LinkedList<String>();
			LinkedList<String> headerList2 = new LinkedList<String>();

			for (String key : configFile.keySet()) {
				headerList1.add(configFile.get(key));
				headerList2.add(key);
			}

			if (null != objectFactory.getAttributeList()) {
				List<AttributeType> attributeList1 = objectFactory.getAttributeList().getAttribute();
				for (AttributeType attribute : attributeList1) {
					if (null != attribute.getMetaData()) {
						List<Object> metadataValues = attribute.getMetaData().getValueOrMultiValueOrValueGroup();
						HashMap<String, String> map = new HashMap<String, String>();
						for (Object val : metadataValues) {
							if (val instanceof ValueType) {
								String k = ((ValueType) val).getAttributeID();
								String v = ((ValueType) val).getContent();
								map.put(k, v);
								metaDataHeaderAttributes.add(k);
							}
						}
					}
				}

				headerList1.addAll(metaDataHeaderAttributes);
				// Create blank workbook
				XSSFWorkbook workbook = new XSSFWorkbook();
				// Create a blank sheet
				XSSFSheet spreadsheet = workbook.createSheet("Attribute Info");
				// Create row object
				XSSFRow row;
				// Excel cell color
				XSSFCellStyle cellStyle = null;
				HashMap<String, String> unitMap = new HashMap<String, String>();
				Map<String, List<String>> attributeMap = new LinkedHashMap<String, List<String>>();
				Map<String, Map<String, String>> metadataAttribute = new HashMap<String, Map<String, String>>();
				int i = 0;
				attributeMap.put(String.valueOf(i), headerList1);

				if (objectFactory.getUnitList() != null) {
					List<Object> unitList = objectFactory.getUnitList().getUnitFamilyOrUnit();
					if (!unitList.isEmpty()) {
						for (Object unit : unitList) {
							if (unit instanceof UnitType) {
								String k = ((UnitType) unit).getID();
								String v = ((UnitType) unit).getName().get(0).getContent();
								unitMap.put(k, v);
							}
						}
					}
				}
				List<AttributeType> attributeList = objectFactory.getAttributeList().getAttribute();
				for (AttributeType attribute : attributeList) {
					i++;
					List<String> attributeInfo = new ArrayList<String>();
					for (int index = attributeInfo.size(); index <= headerList1.size(); index++) {
						attributeInfo.add("");
					}

					String id = attribute.getID();
					String name = attribute.getName().get(0).getContent();

					String validationBase = "";
					String inputMask = "";
					String minValue = "";
					String maxValue = "";
					String maxLength = "";
					String unitID = "";
					String unitName = "";
					if (null != attribute.getValidation()) {
						validationBase = attribute.getValidation().getBaseType();
						inputMask = (null != attribute.getValidation().getInputMask())
								? attribute.getValidation().getInputMask()
								: "";
						minValue = (null != attribute.getValidation().getMinValue())
								? attribute.getValidation().getMinValue()
								: "";
						maxValue = (null != attribute.getValidation().getMaxValue())
								? attribute.getValidation().getMaxValue()
								: "";
						maxLength = (null != attribute.getValidation().getMaxLength())
								? attribute.getValidation().getMaxLength().toString()
								: "";
						if (null != attribute.getValidation().getUnitLink()
								|| !attribute.getValidation().getUnitLink().isEmpty()) {
							StringBuffer strID = new StringBuffer();
							StringBuffer strName = new StringBuffer();
							for (UnitLinkType unitLinks : attribute.getValidation().getUnitLink()) {
								String uname = (unitMap.containsKey(unitLinks.getUnitID()))
										? unitMap.get(unitLinks.getUnitID())
										: "";
								strID.append(unitLinks.getUnitID());
								strID.append(delimiter);
								strName.append(uname);
								strName.append(delimiter);
							}
							unitID = strID.toString();
							unitName = strName.toString();
						}
					} else {
						validationBase = HandlerConstants.LOV;
					}

					String listOfValueID = (null != attribute.getListOfValueLink())
							? attribute.getListOfValueLink().getListOfValueID()
							: "";
					String type = attribute.getProductMode().toString();
					if (type.equals(HandlerConstants.NORMAL)) {
						type = HandlerConstants.SPECIFICATION;
					} else {
						type = HandlerConstants.DESCRIPTION;
					}

					String mandatory = (null != attribute.getMandatory()) ? attribute.getMandatory().toString()
							: TrueFalseType.FALSE.toString();
					String multiVlaued = (null != attribute.getMultiValued()) ? attribute.getMultiValued().toString()
							: TrueFalseType.FALSE.toString();
					String externallyMaintained = (null != attribute.getExternallyMaintained())
							? attribute.getExternallyMaintained().toString()
							: TrueFalseType.FALSE.toString();
					String calculated = (null != attribute.getDerived()) ? attribute.getDerived().toString()
							: TrueFalseType.FALSE.toString();

					String valueTemplate = "";
					if (null != attribute.getValueTemplateOrUnitTemplate())
						for (Object obj : attribute.getValueTemplateOrUnitTemplate())
							if (obj instanceof ValueTemplateType)
								valueTemplate = ((ValueTemplateType) obj).getContent();

					String userTypeID = "";
					if (null != attribute.getUserTypeLink() && !attribute.getUserTypeLink().isEmpty()) {
						StringBuffer str = new StringBuffer();
						for (UserTypeLinkType userType : attribute.getUserTypeLink()) {
							str.append(userType.getUserTypeID());
							str.append(delimiter);
						}
						userTypeID = str.toString();
					}

					String linkType = "";
					if (null != attribute.getLinkType() && !attribute.getLinkType().isEmpty()) {
						StringBuffer str = new StringBuffer();
						for (LinkTypeType lt : attribute.getLinkType()) {
							str.append(lt.getLinkTypeID());
							str.append(delimiter);
						}
						linkType = str.toString();
					}

					String defaultUnitID = (null != attribute.getDefaultUnitID()) ? attribute.getDefaultUnitID() : "";
					String defaultUnitName = (unitMap.containsKey(defaultUnitID)) ? unitMap.get(defaultUnitID) : "";
					String attributeGroup = "";
					if (null != attribute.getAttributeGroupLink() && !attribute.getAttributeGroupLink().isEmpty()) {
						StringBuffer str = new StringBuffer();
						for (AttributeGroupLinkType attrGrp : attribute.getAttributeGroupLink()) {
							str.append(attrGrp.getAttributeGroupID());
							str.append(delimiter);
						}
						attributeGroup = str.toString();
					}
					String hierarchicalFilter = (null != attribute.getHierarchicalFiltering())
							? attribute.getHierarchicalFiltering().toString()
							: TrueFalseType.FALSE.toString();
					String classificationHierarchicalFilter = (null != attribute
							.getClassificationHierarchicalFiltering())
									? attribute.getClassificationHierarchicalFiltering().toString()
									: TrueFalseType.FALSE.toString();
					String dimensionLink = "";
					if (null != attribute.getDimensionLink() && !attribute.getDimensionLink().isEmpty()) {
						StringBuffer str = new StringBuffer();
						for (DimensionLinkType dlink : attribute.getDimensionLink()) {
							str.append(dlink.getDimensionID());
							str.append(delimiter);
						}
						dimensionLink = str.toString();
					}

					String completenessScore = (null != attribute.getCompleteness()) ? attribute.getCompleteness() : "";

					String fullTextIndexible = (null != attribute.getFullTextIndexed())
							? attribute.getFullTextIndexed().toString()
							: TrueFalseType.FALSE.toString();
					if (null != attribute.getMetaData()) {
						List<Object> metadataValues = attribute.getMetaData().getValueOrMultiValueOrValueGroup();
						HashMap<String, String> map = new HashMap<String, String>();
						for (Object val : metadataValues) {
							if (val instanceof ValueType) {
								String k = ((ValueType) val).getAttributeID();
								String v = ((ValueType) val).getContent();
								map.put(k, v);
							}
						}
						metadataAttribute.put(id, map);
					}
					attributeInfo.set(headerList2.indexOf("STEP_ID"), id);
					attributeInfo.set(headerList2.indexOf("STEP_Name"), name);
					attributeInfo.set(headerList2.indexOf("Validation_Base_Type"), validationBase);
					attributeInfo.set(headerList2.indexOf("List_of_Values"), listOfValueID);
					attributeInfo.set(headerList2.indexOf("Minimum_Value"), minValue);
					attributeInfo.set(headerList2.indexOf("Maximum_Value"), maxValue);
					attributeInfo.set(headerList2.indexOf("Maximum_Length"), maxLength);
					attributeInfo.set(headerList2.indexOf("Mask"), inputMask);
					attributeInfo.set(headerList2.indexOf("Type"), type);
					attributeInfo.set(headerList2.indexOf("Mandatory"), mandatory);
					attributeInfo.set(headerList2.indexOf("Multi_Valued"), multiVlaued);
					attributeInfo.set(headerList2.indexOf("Externally_Maintained"), externallyMaintained);
					attributeInfo.set(headerList2.indexOf("Calculated"), calculated);
					attributeInfo.set(headerList2.indexOf("Value_Template"), valueTemplate);
					attributeInfo.set(headerList2.indexOf("Validity"), userTypeID);
					attributeInfo.set(headerList2.indexOf("LinkType"), linkType);
					attributeInfo.set(headerList2.indexOf("Unit_ID"), unitID);
					attributeInfo.set(headerList2.indexOf("Unit_Name"), unitName);
					attributeInfo.set(headerList2.indexOf("Default_Unit_ID"), defaultUnitID);
					attributeInfo.set(headerList2.indexOf("Default_Unit_Name"), defaultUnitName);
					attributeInfo.set(headerList2.indexOf("Attribute_Group_Reference_ID"), attributeGroup);
					attributeInfo.set(headerList2.indexOf("Attribute_Group_Reference_Name"), "");
					attributeInfo.set(headerList2.indexOf("Full_Text_Indexible"), fullTextIndexible);
					attributeInfo.set(headerList2.indexOf("Completeness_Score"), completenessScore);
					attributeInfo.set(headerList2.indexOf("Hierarchical_Filtering"), hierarchicalFilter);
					attributeInfo.set(headerList2.indexOf("Classification_Hierarchical_Filtering"),
							classificationHierarchicalFilter);
					attributeInfo.set(headerList2.indexOf("Dimension_Dependencies"), dimensionLink);

					attributeMap.put(String.valueOf(i++), attributeInfo);
				}

				for (Map.Entry<String, List<String>> entrySet : attributeMap.entrySet()) {
					Integer rowNum = Integer.parseInt(entrySet.getKey());
					if (rowNum > 0) {
						String attrID = entrySet.getValue().get(0);
						if (metadataAttribute.containsKey(attrID)) {
							Map<String, String> metadataValues = metadataAttribute.get(attrID);
							List<String> attributeData = entrySet.getValue();
							for (Map.Entry<String, String> itr : metadataValues.entrySet()) {
								String metaAttrID = itr.getKey();
								String metaAttrValue = itr.getValue();
								int index = headerList1.indexOf(metaAttrID);
								attributeData.add(index, metaAttrValue);
							}
						}
					}
				}

				// Iterate over data and write to sheet
				Set<String> keyid = attributeMap.keySet();
				int rowid = 0;
				for (String key : keyid) {
					row = spreadsheet.createRow(rowid++);
					List<String> objectArr = attributeMap.get(key);
					int cellid = 0;
					for (Object obj : objectArr) {
						if (obj instanceof String) {
							Cell cell = row.createCell(cellid++);
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
				FileOutputStream out = new FileOutputStream(file);
				workbook.write(out);
				out.close();
				workbook.close();
				System.out.println("File Generated in path : " + file.getAbsolutePath());

			} else {
				throw new Exception("Invalid XML File For Attribute");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}

	}

}
