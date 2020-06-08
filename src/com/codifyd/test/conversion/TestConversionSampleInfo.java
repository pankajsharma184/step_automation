package com.codifyd.test.conversion;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.codifyd.automation.bgp.BGPReportSampleInfo;
import com.codifyd.automation.stepconversion.attributelink.AttributeLinkSampleInfo;
import com.codifyd.automation.stepconversion.attributeschema.AttributeSampleInfo;
import com.codifyd.automation.stepconversion.lovschema.LovSampleInfo;
import com.codifyd.automation.stepconversion.mil.MILSampleInfo;
import com.codifyd.automation.stepconversion.uom.UomSampleInfo;
import com.codifyd.test.util.UserInputUtil;

public class TestConversionSampleInfo {
	public static void main(String[] args) {
		Map<Integer, String> option = new HashMap<>();
		option.put(1, "AttributeSchema -  Properties");
		option.put(2, "AttributeSchema -  InputSampleXML");
		option.put(3, "AttributeSchema -  InputSampleExcel");
		option.put(4, "AttributeLinkSchema - Properties");
		option.put(5, "AttributeLinkSchema - InputSampleXML");
		option.put(6, "AttributeLinkSchema - InputSampleExcel");
		option.put(7, "LOV - Properties");
		option.put(8, "LOV - InputSampleXML");
		option.put(9, "LOV - InputSampleExcel");
		option.put(10, "UOM - Properties");
		option.put(11, "UOM - InputSampleXML");
		option.put(12, "UOM - InputSampleExcel");
		option.put(13, "MIL - Properties");
		option.put(14, "MIL - InputSampleXML");
		option.put(15, "BGP - InputSampleText");

		AttributeSampleInfo attributeSampleInfo = new AttributeSampleInfo();
		AttributeLinkSampleInfo attributeLinkSampleInfo = new AttributeLinkSampleInfo();
		LovSampleInfo lovSampleInfo = new LovSampleInfo();
		UomSampleInfo uomSampleInfo = new UomSampleInfo();
		MILSampleInfo milSampleInfo = new MILSampleInfo();
		BGPReportSampleInfo bgpReportSampleInfo = new BGPReportSampleInfo();

		try {
			Integer choice = UserInputUtil.getUserSelectedOptionFromMap(option);
			switch (choice) {

			case 1:
				attributeSampleInfo.getSamplePropertiesFile();
				break;
			case 2:
				attributeSampleInfo.getSampleInputXMLFile();
				break;
			case 3:
				attributeSampleInfo.getSampleInputExcelFile();
				break;

			case 4:
				attributeLinkSampleInfo.getSamplePropertiesFile();
				break;
			case 5:
				attributeLinkSampleInfo.getSampleInputXMLFile();
				break;
			case 6:
				attributeLinkSampleInfo.getSampleInputExcelFile();
				break;

			case 7:
				lovSampleInfo.getSamplePropertiesFile();
				break;
			case 8:
				lovSampleInfo.getSampleInputXMLFile();
				break;
			case 9:
				lovSampleInfo.getSampleInputExcelFile();
				break;

			case 10:
				uomSampleInfo.getSamplePropertiesFile();
				break;
			case 11:
				uomSampleInfo.getSampleInputXMLFile();
				break;
			case 12:
				uomSampleInfo.getSampleInputExcelFile();
				break;

			case 13:
				milSampleInfo.getSampleConfigPropertiesFile();
				break;
			case 14:
				milSampleInfo.getSampleInputXMLFile();
				break;
			case 15:
				bgpReportSampleInfo.getSampleInputFile();
				break;

			default:
				break;

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
