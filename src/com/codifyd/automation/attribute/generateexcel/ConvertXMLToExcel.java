package com.codifyd.automation.attribute.generateexcel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.codifyd.automation.attribute.generateexcel.STEPProductInformation.AttributeList.Attribute;
import com.codifyd.automation.attribute.generateexcel.STEPProductInformation.AttributeList.Attribute.AttributeGroupLink;
import com.codifyd.automation.attribute.generateexcel.STEPProductInformation.AttributeList.Attribute.LinkType;
import com.codifyd.automation.attribute.generateexcel.STEPProductInformation.AttributeList.Attribute.MetaData.Value;
import com.codifyd.automation.attribute.generateexcel.STEPProductInformation.AttributeList.Attribute.UserTypeLink;

public class ConvertXMLToExcel {

	public static void main(String[] args) {

		Map<String, AttributeXMLInfo> inputValues = new HashMap();
		File inputFile = new File("C:\\DIUP-Documents\\eclipse-workspace\\Attributes\\20200327-attribute-definitions-tst.xml");

		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(STEPProductInformation.class);
			Unmarshaller jaxbUnMarshaller = jaxbContext.createUnmarshaller();
			STEPProductInformation objectFactory = (STEPProductInformation) jaxbUnMarshaller.unmarshal(inputFile);
			writeExcel(objectFactory);
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

	private static void writeExcel(STEPProductInformation objectFactory) throws FileNotFoundException, IOException {
		// Create blank workbook
		XSSFWorkbook workbook = new XSSFWorkbook();

		// Create a blank sheet
		XSSFSheet spreadsheet = workbook.createSheet("Attribute Info");

		// Create row object
		XSSFRow row;

		// This data needs to be written (Object[])
		Map<String, Object[]> attributeMap = new LinkedHashMap<String, Object[]>();
		Set<String> metadataAttribute = new HashSet();
		int i = 0;
		attributeMap.put(String.valueOf(i),
				new Object[] { "ID", "Name", "Full_Text_Indexible", "Externally_Maintained", "Completeness_Score",
						"Hierarchial_Filtering", "Calculated", "Type", "Dimension_Dependencies", "Mandatory",
						"Validation_Base_Type", "List_of_Values", "Multi_Valued", "Mask", "Minimum_Value",
						"Maximum_Value", "Maximum_Length", "Unit_ID", "Unit_Name", "Unit_Description",
						"Default_Unit_ID", "Default_Unit_Name", "Default_Unit_Description",
						"Attribute_Group_Reference_ID", "Validity", "LinkType" });

		List<Attribute> attributeList = objectFactory.getAttributeList().getAttribute();
		for (Attribute attribute : attributeList) {
			i++;
			String listOfValueID = (null != attribute.getListOfValueLink())
					? attribute.getListOfValueLink().getListOfValueID()
					: "";
			String dimensionLink = "";
			if (null != attribute.getDimensionLink() && !attribute.getDimensionLink().isEmpty()) {
				dimensionLink = attribute.getDimensionLink().get(0).getDimensionID();
			}

			String validationBase = "";
			String inputMask = "";
			String minValue = "";
			String maxValue = "";
			String maxLength = "";
			if (null != attribute.getValidation()) {
				validationBase = attribute.getValidation().getBaseType();
				inputMask = attribute.getValidation().getInputMask();
				minValue = attribute.getValidation().getMinValue();
				maxValue = attribute.getValidation().getMaxValue();
				maxLength = attribute.getValidation().getMaxLength();
			}
			
			if(null != attribute.getMetaData()) {
			List<Value> metadataValues = attribute.getMetaData().getValue();
			for(Value val : metadataValues) {
				metadataAttribute.add(val.getAttributeID());
			}
			}
			

			String attributeGroup = "";
			if (null != attribute.getAttributeGroupLink() && !attribute.getAttributeGroupLink().isEmpty()) {
				StringBuffer str = new StringBuffer();
				for (AttributeGroupLink attrGrp : attribute.getAttributeGroupLink()) {
					str.append(attrGrp.getAttributeGroupID());
					str.append(";");
				}
				attributeGroup = str.toString();
			}

			String userTypeID = "";
			if (null != attribute.getUserTypeLink() && !attribute.getUserTypeLink().isEmpty()) {
				StringBuffer str = new StringBuffer();
				for (UserTypeLink userType : attribute.getUserTypeLink()) {
					str.append(userType.getUserTypeID());
					str.append(";");
				}
				userTypeID = str.toString();
			}

			String linkType = "";
			if (null != attribute.getLinkType() && !attribute.getLinkType().isEmpty()) {
				StringBuffer str = new StringBuffer();
				for (LinkType lt : attribute.getLinkType()) {
					str.append(lt.getLinkTypeID());
					str.append(";");
				}
				linkType = str.toString();
			}

			attributeMap.put(String.valueOf(i++),
					new Object[] { attribute.getID(), attribute.getName(), attribute.getFullTextIndexed(),
							attribute.getExternallyMaintained(), "", attribute.getHierarchicalFiltering(), "",
							attribute.getProductMode(), dimensionLink, attribute.getMandatory(), validationBase,
							listOfValueID, attribute.getMultiValued(), inputMask, minValue, maxValue, maxLength, "", "",
							"", attribute.getDefaultUnitID(), "", "", attributeGroup, userTypeID, linkType });
		}
		// Iterate over data and write to sheet
		Set<String> keyid = attributeMap.keySet();
		int rowid = 0;
		for (String key : keyid) {
			row = spreadsheet.createRow(rowid++);
			Object[] objectArr = attributeMap.get(key);
			int cellid = 0;
			for (Object obj : objectArr) {
				Cell cell = row.createCell(cellid++);
				if (obj instanceof String) {
					cell.setCellValue((String) obj);
				} else {
					if (obj != null) {
						System.out.println(obj);
					}
				}
			}
		}
		// Write the workbook in file system
		FileOutputStream out = new FileOutputStream(new File("C:\\DIUP-Documents\\eclipse-workspace\\Attributes\\AttributesTSTBackup.xlsx"));
		workbook.write(out);
		out.close();
		System.out.println("Attribute.xlsx written successfully");

	}

}
