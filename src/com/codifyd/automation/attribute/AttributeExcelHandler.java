package com.codifyd.automation.attribute;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

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
import com.codifyd.stepxsd.AttributeGroupLinkType;
import com.codifyd.stepxsd.AttributeType;
import com.codifyd.stepxsd.DimensionLinkType;
import com.codifyd.stepxsd.LinkTypeType;
import com.codifyd.stepxsd.STEPProductInformation;
import com.codifyd.stepxsd.UnitLinkType;
import com.codifyd.stepxsd.UnitType;
import com.codifyd.stepxsd.UserTypeLinkType;
import com.codifyd.stepxsd.ValueType;

public class AttributeExcelHandler {
	private static final String NORMAL = "Normal";
	private static final String DESCRIPTION = "Description";
	private static final String SPECIFICATION = "Specification";
	private static final String FALSE = "false";

	// public static void main(String[] args) {
	public void handleFile(UserInputFileUtilDO userInputFileUtilDO) throws IOException {

//		Map<String, AttributeXMLInfo> inputValues = new HashMap();
		File inputFile = new File(userInputFileUtilDO.getInputPath());
		File outputFile = new File(userInputFileUtilDO.getOutputPath() + "\\" + userInputFileUtilDO.getFilename());
		Properties properties = userInputFileUtilDO.getPropertiesFile();
		String delimeterString = userInputFileUtilDO.getDelimeters();
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(STEPProductInformation.class);
			Unmarshaller jaxbUnMarshaller = jaxbContext.createUnmarshaller();
			STEPProductInformation objectFactory = (STEPProductInformation) jaxbUnMarshaller.unmarshal(inputFile);

			writeExcel(objectFactory, outputFile, properties, delimeterString);
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

	private static void writeExcel(STEPProductInformation objectFactory, File file, Properties prop, String delim)
			throws FileNotFoundException, IOException {

		// accessing data from properties file
		Set<String> metaDataHeaderAttributes = new HashSet<String>();
		ArrayList<Integer> al = new ArrayList<Integer>();
		for (String key : prop.stringPropertyNames()) {
			al.add(Integer.parseInt(key));
		}
		Collections.sort(al);
		LinkedList<String> list = new LinkedList<String>();
		for (Object j : al) {
			list.add(prop.getProperty(j.toString()));
		}
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
//		System.out.println(metaDataHeaderAttributes);

		list.addAll(metaDataHeaderAttributes);
		// Create blank workbook
		XSSFWorkbook workbook = new XSSFWorkbook();
		// Create a blank sheet
		XSSFSheet spreadsheet = workbook.createSheet("Attribute Info");
		// Create row object
		XSSFRow row;
		// Excel cell color
		XSSFCellStyle cellStyle = null;
		HashMap<String, String> unitMap = new HashMap<String, String>();
		Map<String, List> attributeMap = new LinkedHashMap<String, List>();
		Map<String, Map<String, String>> metadataAttribute = new HashMap<String, Map<String, String>>();
		int i = 0;
		attributeMap.put(String.valueOf(i), list);

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
			List<Object> attributeInfo = new ArrayList<>(list.size());
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
				minValue = (null != attribute.getValidation().getMinValue()) ? attribute.getValidation().getMinValue()
						: "";
				maxValue = (null != attribute.getValidation().getMaxValue()) ? attribute.getValidation().getMaxValue()
						: "";
				maxLength = (null != attribute.getValidation().getMaxLength())
						? attribute.getValidation().getMaxLength().toString()
						: "";
				if (null != attribute.getValidation().getUnitLink()
						|| !attribute.getValidation().getUnitLink().isEmpty()) {
					StringBuffer strID = new StringBuffer();
					StringBuffer strName = new StringBuffer();
					for (UnitLinkType unitLinks : attribute.getValidation().getUnitLink()) {
						String uname = (unitMap.containsKey(unitLinks.getUnitID())) ? unitMap.get(unitLinks.getUnitID())
								: "";
						strID.append(unitLinks.getUnitID());
						strID.append(delim);
						strName.append(uname);
						strName.append(delim);
					}
					unitID = strID.toString();
					unitName = strName.toString();
				}
			}

			String listOfValueID = (null != attribute.getListOfValueLink())
					? attribute.getListOfValueLink().getListOfValueID()
					: "";
			String type = attribute.getProductMode().toString();
			if (type.equals(NORMAL)) {
				type = SPECIFICATION;
			} else {
				type = DESCRIPTION;
			}

			String mandatory = (null != attribute.getMandatory()) ? attribute.getMandatory().toString() : FALSE;
			String multiVlaued = (null != attribute.getMultiValued()) ? attribute.getMultiValued().toString() : FALSE;
			String externallyMaintained = (null != attribute.getExternallyMaintained())
					? attribute.getExternallyMaintained().toString()
					: FALSE;
			String calculated = (null != attribute.getDerived()) ? attribute.getDerived().toString() : FALSE;
			String valueTemplate = "";
			if (null != attribute.getValueTemplateOrUnitTemplate()) {
				for (Object obj : attribute.getValueTemplateOrUnitTemplate()) {
					valueTemplate = attribute.getValueTemplateOrUnitTemplate().get(0).toString();
				}
			}
			String userTypeID = "";
			if (null != attribute.getUserTypeLink() && !attribute.getUserTypeLink().isEmpty()) {
				StringBuffer str = new StringBuffer();
				for (UserTypeLinkType userType : attribute.getUserTypeLink()) {
					str.append(userType.getUserTypeID());
					str.append(delim);
				}
				userTypeID = str.toString();
			}

			String linkType = "";
			if (null != attribute.getLinkType() && !attribute.getLinkType().isEmpty()) {
				StringBuffer str = new StringBuffer();
				for (LinkTypeType lt : attribute.getLinkType()) {
					str.append(lt.getLinkTypeID());
					str.append(delim);
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
					str.append(delim);
				}
				attributeGroup = str.toString();
			}
			String hierarchicalFilter = (null != attribute.getHierarchicalFiltering())
					? attribute.getHierarchicalFiltering().toString()
					: FALSE;
			String classificationHierarchicalFilter = (null != attribute.getClassificationHierarchicalFiltering())
					? attribute.getClassificationHierarchicalFiltering().toString()
					: FALSE;
			String dimensionLink = "";
			if (null != attribute.getDimensionLink() && !attribute.getDimensionLink().isEmpty()) {
				StringBuffer str = new StringBuffer();
				for (DimensionLinkType dlink : attribute.getDimensionLink()) {
					str.append(dlink.getDimensionID());
					str.append(delim);
				}
				dimensionLink = str.toString();
			}

			String completenessScore = (null != attribute.getCompleteness()) ? attribute.getCompleteness() : "";

			String fullTextIndexible = (null != attribute.getFullTextIndexed())
					? attribute.getFullTextIndexed().toString()
					: FALSE;
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
			attributeInfo.add(id);
			attributeInfo.add(name);
			attributeInfo.add(validationBase);
			attributeInfo.add(listOfValueID);
			attributeInfo.add(minValue);
			attributeInfo.add(maxValue);
			attributeInfo.add(maxLength);
			attributeInfo.add(inputMask);
			attributeInfo.add(type);
			attributeInfo.add(mandatory);
			attributeInfo.add(multiVlaued);
			attributeInfo.add(externallyMaintained);
			attributeInfo.add(calculated);
			attributeInfo.add(valueTemplate);
			attributeInfo.add(userTypeID);
			attributeInfo.add(linkType);
			attributeInfo.add(unitID);
			attributeInfo.add(unitName);
			attributeInfo.add(defaultUnitID);
			attributeInfo.add(defaultUnitName);
			attributeInfo.add(attributeGroup);
			attributeInfo.add("");
			attributeInfo.add(fullTextIndexible);
			attributeInfo.add(completenessScore);
			attributeInfo.add(hierarchicalFilter);
			attributeInfo.add(classificationHierarchicalFilter);
			attributeInfo.add(dimensionLink);
			for (int index = attributeInfo.size(); index <= list.size(); index++) {
				attributeInfo.add("");
			}
			attributeMap.put(String.valueOf(i++), attributeInfo);
		}
		for (Map.Entry<String, List> entrySet : attributeMap.entrySet()) {
			Integer rowNum = Integer.parseInt(entrySet.getKey());
			if (rowNum > 0) {
				String attrID = (String) entrySet.getValue().get(0);
				if (metadataAttribute.containsKey(attrID)) {
					Map<String, String> metadataValues = metadataAttribute.get(attrID);
					List attributeData = entrySet.getValue();
					for (Map.Entry<String, String> itr : metadataValues.entrySet()) {
						String metaAttrID = itr.getKey();
						String metaAttrValue = itr.getValue();
						int index = list.indexOf(metaAttrID);
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
			List objectArr = attributeMap.get(key);
			int cellid = 0;
			for (Object obj : objectArr) {
				Cell cell = row.createCell(cellid++);
				if (obj instanceof String) {
					cell.setCellValue((String) obj);

					if (rowid == 1 && cell.getColumnIndex() < al.size()) {
						cellStyle = workbook.createCellStyle();
						cellStyle.setFillForegroundColor(new XSSFColor(new java.awt.Color(50, 120, 180)));
						cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
						cell.setCellStyle(cellStyle);
					} else if (rowid == 1 && cell.getColumnIndex() >= al.size()) {
						cellStyle = workbook.createCellStyle();
						cellStyle.setFillForegroundColor(new XSSFColor(new java.awt.Color(155, 195, 230)));
						cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
						cell.setCellStyle(cellStyle);
					}

				} else {
					if (obj != null) {
						System.out.println(obj);
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

	}

}
