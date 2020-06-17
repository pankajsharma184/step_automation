package com.codifyd.automation.stepconversion.util;

import java.io.InputStream;

import com.codifyd.automation.util.AutomationConstants;

public class SampleFileHelperUtil {

	public static InputStream getSamplePropertiesFile(String str) {
		InputStream inStream = null;
		ClassLoader classLoader = SampleFileHelperUtil.class.getClassLoader();
		if (str.equals(AutomationConstants.ATTRIBUTE)) {
			inStream = classLoader.getResourceAsStream("resources/Attribute-Config.properties");
		} else if (str.equals(AutomationConstants.ATTRIBUTELINK)) {
			inStream = classLoader.getResourceAsStream("resources/AttributeLink-Config.properties");
		} else if (str.equals(AutomationConstants.LOV)) {
			inStream = classLoader.getResourceAsStream("resources/LOV-Config.properties");
		} else if (str.equals(AutomationConstants.UOM)) {
			inStream = classLoader.getResourceAsStream("resources/UOM-Config.properties");
		} else if (str.equals(AutomationConstants.PRODUCTTAXONOMY)) {
			inStream = classLoader.getResourceAsStream("resources/Taxonomy-Config.properties");
		} else if (str.equals(AutomationConstants.CLASSIFICATIONTAXONOMY)) {
			inStream = classLoader.getResourceAsStream("resources/Taxonomy-Config.properties");
		} else if (str.equals(AutomationConstants.MIL)) {
			inStream = classLoader.getResourceAsStream("resources/stepTomilconversion-config.properties");
		}
		return inStream;
	}

	public static InputStream getSampleXMLFile(String str) {
		InputStream inStream = null;
		ClassLoader classLoader = SampleFileHelperUtil.class.getClassLoader();
		if (str.equals(AutomationConstants.ATTRIBUTE)) {
			inStream = classLoader.getResourceAsStream("sample/AttributeXMLInputSample.xml");
		} else if (str.equals(AutomationConstants.ATTRIBUTELINK)) {
			inStream = classLoader.getResourceAsStream("sample/AttributeLinkXMLInputSample.xml");
		} else if (str.equals(AutomationConstants.LOV)) {
			inStream = classLoader.getResourceAsStream("sample/LOVXMLInputSample.xml");
		} else if (str.equals(AutomationConstants.UOM)) {
			inStream = classLoader.getResourceAsStream("sample/UnitsOfMeasureXMLInputSample.xml");
		} else if (str.equals(AutomationConstants.PRODUCTTAXONOMY)) {
			inStream = classLoader.getResourceAsStream("sample/TaxonomyXMLInputSample.xml");
		} else if (str.equals(AutomationConstants.CLASSIFICATIONTAXONOMY)) {
			inStream = classLoader.getResourceAsStream("resources/Taxonomy-Config.properties");
		} else if (str.equals(AutomationConstants.MIL)) {
			inStream = classLoader.getResourceAsStream("sample/MilXMLInputSample.xml");
		}

		return inStream;
	}

	public static InputStream getSampleExcelFile(String str) {
		InputStream inStream = null;
		ClassLoader classLoader = SampleFileHelperUtil.class.getClassLoader();
		if (str.equals(AutomationConstants.ATTRIBUTE)) {
			inStream = classLoader.getResourceAsStream("sample/AttributeXMLInputSample.xlsx");
		} else if (str.equals(AutomationConstants.ATTRIBUTELINK)) {
			inStream = classLoader.getResourceAsStream("sample/AttributeLinkXMLInputSample.xlsx");
		} else if (str.equals(AutomationConstants.LOV)) {
			inStream = classLoader.getResourceAsStream("sample/LOVXMLInputSample.xml");
		} else if (str.equals(AutomationConstants.UOM)) {
			inStream = classLoader.getResourceAsStream("sample/UnitsOfMeasureXMLInputSample.xlsx");
		} else if (str.equals(AutomationConstants.PRODUCTTAXONOMY)) {
			inStream = classLoader.getResourceAsStream("sample/TaxonomyXMLInputSample.xlsx");
		}else if (str.equals(AutomationConstants.CLASSIFICATIONTAXONOMY)) {
			inStream = classLoader.getResourceAsStream("sample/TaxonomyXMLInputSample.xlsx");
		}
		return inStream;

	}

	public static InputStream getSampleInputFile(String str) {
		InputStream inStream = null;
		Class<SampleFileHelperUtil> classLoader = SampleFileHelperUtil.class;
		if (str.equals(AutomationConstants.BGP)) {
			inStream = classLoader.getResourceAsStream("sample/BGPReportInputSample.txt");
		}
		return inStream;
	}

}
