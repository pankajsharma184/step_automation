package com.codifyd.automation.util;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;

public class Utility {
	public static String getDelimeter(String str) {
		String delim = null;
		if(str.indexOf("\\|") != -1) {
			delim = "\\|";
		}else if(str.indexOf(",") != -1) {
			delim = ",";
		}else{
			delim = ";";
		}
		return delim;
	}
	
	public static Boolean toBoolean(String str) {
		boolean bool=false;
		if(!str.trim().equals(""))
		{
			bool=Boolean.parseBoolean(str.toLowerCase());
		}
		return bool;
	}
}
