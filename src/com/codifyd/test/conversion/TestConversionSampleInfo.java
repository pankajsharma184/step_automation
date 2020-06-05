package com.codifyd.test.conversion;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.codifyd.automation.stepconversion.attributeschema.AttributeSampleInfo;
import com.codifyd.test.util.UserInputUtil;

public class TestConversionSampleInfo {
	public static void main(String[] args) {
		Map<Integer, String> options = new HashMap<>();
		options.put(1, "Attribute - Properties");
		options.put(2, "Attribute - InputSampleXML");
		options.put(3, "Attribute - InputSampleExcel");
		
		AttributeSampleInfo sampleInfo = new AttributeSampleInfo();

		try {
			Integer choice = UserInputUtil.getUserSelectedOptionFromMap(options);
			switch (choice) {
			case 1:
				sampleInfo.getSamplePropertiesFile();
				break;
			case 2:
				sampleInfo.getSampleInputXMLFile();
				break;
			case 3:
				sampleInfo.getSampleInputExcelFile();
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
