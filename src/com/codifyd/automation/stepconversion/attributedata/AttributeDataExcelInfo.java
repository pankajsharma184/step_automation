package com.codifyd.automation.stepconversion.attributedata;

import java.util.HashMap;
import java.util.Map;

public class AttributeDataExcelInfo {	
	private String stepID;
	private String uniqueKeyValue;
	private String uniqueKeyID;	
	private Map<String,String> attributeData = new HashMap<>();
	
	public String getStepID() {
		return stepID;
	}
	public void setStepID(String stepID) {
		this.stepID = stepID;
	}
	
	public Map<String, String> getAttributeData() {
		return attributeData;
	}
	public void setAttributeData(Map<String, String> attributeData) {
		this.attributeData = attributeData;
	}
	public String getUniqueKeyValue() {
		return uniqueKeyValue;
	}
	public void setUniqueKeyValue(String uniqueKeyValue) {
		this.uniqueKeyValue = uniqueKeyValue;
	}
	public String getUniqueKeyID() {
		return uniqueKeyID;
	}
	public void setUniqueKeyID(String uniqueKeyID) {
		this.uniqueKeyID = uniqueKeyID;
	}
	
	
}
