package com.codifyd.automation.bgp;

import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

public class Util {
//	String str = "https://grandandtoy-dev.scloud.stibo.com/restapiv2/background-processes/BGP_136302/execution-report?context=Context1&workspace=Main";

	public static Set<String> getURLs(String inputFile, String inputUrl) {
		if (inputUrl.endsWith("/")) {
			inputUrl = inputUrl + "restapiv2/background-processes/";
		}else {
			inputUrl = inputUrl + "/restapiv2/background-processes/";
		}
		Set<String> urlSet = new HashSet<String>();
		try {
			Properties properties = new Properties();
			properties.load(new FileInputStream(new File(inputFile)));
			for (String prop : properties.stringPropertyNames()) {
//				System.out.println(prop);
				List<String> list = Arrays.asList(prop.split(" "));
				for (String id : list)
					if (id.startsWith("BGP_")) {
						urlSet.add(inputUrl + id + "/execution-report?context=Context1&workspace=Main");
					}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return urlSet;
	}

	public static String getAttributeID(String entryText) {
		int beginIndex = entryText.indexOf("step://attribute?id=") + "step://attribute?id=".length();
		int endIndex = entryText.indexOf('\"', beginIndex);
//		System.out.println(beginIndex + "  " + endIndex);
		String attribute = entryText.substring(beginIndex, endIndex);
		return attribute;
	}

	public static String getProductID(String entryText) {
		int beginIndex = entryText.indexOf("step://product?id=") + "step://product?id=".length();
		int endIndex = entryText.indexOf('\"', beginIndex);
//		System.out.println(beginIndex + "  " + endIndex);
		String product = entryText.substring(beginIndex, endIndex);
		return product;
	}

	public static String getErrorValue(String entryText, String attributeID, String productID) {
		int beginIndex = entryText.indexOf("isn\'t valid (") + "isn\'t valid (".length();
		int endIndex = entryText.indexOf(")", beginIndex);
		String errorValue = entryText.substring(beginIndex, endIndex);

		return errorValue;
	}

}
