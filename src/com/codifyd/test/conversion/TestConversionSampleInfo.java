package com.codifyd.test.conversion;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.codifyd.automation.stepconversion.attributelink.AttributeLinkSampleInfo;
import com.codifyd.automation.stepconversion.attributeschema.AttributeSampleInfo;
import com.codifyd.automation.stepconversion.lovschema.LovSampleInfo;
import com.codifyd.automation.stepconversion.uom.UomSampleInfo;
import com.codifyd.test.util.UserInputUtil;

public class TestConversionSampleInfo {
	public static void main(String[] args) {
		Map<Integer, String> option1 = new HashMap<>();
		option1.put(1, "AttributeSchema");
		option1.put(2, "AttributeLinkSchema");
		option1.put(3, "LOV");
		option1.put(4, "UOM");

		Map<Integer, String> option2 = new HashMap<>();
		option2.put(1, "Properties");
		option2.put(2, "InputSampleXML");
		option2.put(3, "InputSampleExcel");

		try {
			Integer choice = UserInputUtil.getUserSelectedOptionFromMap(option1);
			Integer choice2 = 0;
			switch (choice) {
			case 1:
				AttributeSampleInfo attributeSampleInfo = new AttributeSampleInfo();
				choice2 = UserInputUtil.getUserSelectedOptionFromMap(option2);
				switch (choice2) {
				case 1:
					attributeSampleInfo.getSamplePropertiesFile();
					break;
				case 2:
					attributeSampleInfo.getSampleInputXMLFile();
					break;
				case 3:
					attributeSampleInfo.getSampleInputExcelFile();
					break;
				default:
					break;
				}
				break;
			case 2:
				AttributeLinkSampleInfo attributeLinkSampleInfo = new AttributeLinkSampleInfo();
				choice2 = UserInputUtil.getUserSelectedOptionFromMap(option2);
				switch (choice2) {
				case 1:
					attributeLinkSampleInfo.getSamplePropertiesFile();
					break;
				case 2:
					attributeLinkSampleInfo.getSampleInputXMLFile();
					break;
				case 3:
					attributeLinkSampleInfo.getSampleInputExcelFile();
					break;
				default:
					break;
				}
				break;
			case 3:
				LovSampleInfo lovSampleInfo = new LovSampleInfo();
				choice2 = UserInputUtil.getUserSelectedOptionFromMap(option2);
				switch (choice2) {
				case 1:
					lovSampleInfo.getSamplePropertiesFile();
					break;
				case 2:
					lovSampleInfo.getSampleInputXMLFile();
					break;
				case 3:
					lovSampleInfo.getSampleInputExcelFile();
					break;
				default:
					break;
				}
				break;
			case 4:
				UomSampleInfo uomSampleInfo = new UomSampleInfo();
				choice2 = UserInputUtil.getUserSelectedOptionFromMap(option2);
				switch (choice2) {
				case 1:
					uomSampleInfo.getSamplePropertiesFile();
					break;
				case 2:
					uomSampleInfo.getSampleInputXMLFile();
					break;
				case 3:
					uomSampleInfo.getSampleInputExcelFile();
					break;
				default:
					break;
				}
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
