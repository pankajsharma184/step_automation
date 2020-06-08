package com.codifyd.automation.stepconversion.util;

import java.io.InputStream;

public class SampleFileHelperUtil {

	public static InputStream getSamplePropertiesFile(String str) {
		InputStream inStream = null;
		ClassLoader classLoader = SampleFileHelperUtil.class.getClassLoader();
		if (str.equals(HandlerConstants.ATTRIBUTE)) {
			inStream = classLoader.getResourceAsStream("resources/Attribute-Config.properties");
		} else if (str.equals(HandlerConstants.ATTRIBUTELINK)) {
			inStream = classLoader.getResourceAsStream("resources/AttributeLink-Config.properties");
		} else if (str.equals(HandlerConstants.LOV)) {
			inStream = classLoader.getResourceAsStream("resources/LOV-Config.properties");
		} else if (str.equals(HandlerConstants.UOM)) {
			inStream = classLoader.getResourceAsStream("resources/UOM-Config.properties");
		} else if (str.equals(HandlerConstants.TAXONOMY)) {
			inStream = classLoader.getResourceAsStream("resources/Taxonomy-Config.properties");
		} else if (str.equals(HandlerConstants.MIL)) {
			inStream = classLoader.getResourceAsStream("resources/stepTomilconversion-config.properties");
		} 
		return inStream;
	}

	public static InputStream getSampleXMLFile(String str) {
		InputStream inStream = null;
		ClassLoader classLoader = SampleFileHelperUtil.class.getClassLoader();
		if (str.equals(HandlerConstants.ATTRIBUTE)) {
			inStream = classLoader.getResourceAsStream("sample/AttributeXMLInputSample.xml");
		} else if (str.equals(HandlerConstants.ATTRIBUTELINK)) {
			inStream = classLoader.getResourceAsStream("sample/AttributeLinkXMLInputSample.xml");
		} else if (str.equals(HandlerConstants.LOV)) {
			inStream = classLoader.getResourceAsStream("sample/LOVXMLInputSample.xml");
		} else if (str.equals(HandlerConstants.UOM)) {
			inStream = classLoader.getResourceAsStream("sample/UnitsOfMeasureXMLInputSample.xml");
		} else if (str.equals(HandlerConstants.TAXONOMY)) {
			inStream = classLoader.getResourceAsStream("sample/TaxonomyXMLInputSample.xml");
		}else if (str.equals(HandlerConstants.MIL)) {
			inStream = classLoader.getResourceAsStream("sample/MilXMLInputSample.xml");
		}

		return inStream;
	}

	public static InputStream getSampleExcelFile(String str) {
		InputStream inStream = null;
		ClassLoader classLoader = SampleFileHelperUtil.class.getClassLoader();
		if (str.equals(HandlerConstants.ATTRIBUTE)) {
			inStream = classLoader.getResourceAsStream("sample/AttributeXMLInputSample.xlsx");
		} else if (str.equals(HandlerConstants.ATTRIBUTELINK)) {
			inStream = classLoader.getResourceAsStream("sample/AttributeLinkXMLInputSample.xlsx");
		} else if (str.equals(HandlerConstants.LOV)) {
			inStream = classLoader.getResourceAsStream("sample/LOVXMLInputSample.xml");
		} else if (str.equals(HandlerConstants.UOM)) {
			inStream = classLoader.getResourceAsStream("sample/UnitsOfMeasureXMLInputSample.xlsx");
		} else if (str.equals(HandlerConstants.TAXONOMY)) {
			inStream = classLoader.getResourceAsStream("sample/TaxonomyXMLInputSample.xlsx");
		} 
		return inStream;

	}
	
	public static InputStream getSampleInputFile(String str){
		InputStream inStream = null;
		Class<SampleFileHelperUtil> classLoader = SampleFileHelperUtil.class;
		if (str.equals(HandlerConstants.BGP)) {
			inStream = classLoader.getResourceAsStream("sample/BGPReportInputSample.txt");
		} 
		return inStream;
	}

}
