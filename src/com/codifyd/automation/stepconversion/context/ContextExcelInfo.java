package com.codifyd.automation.stepconversion.context;

import java.util.ArrayList;
import java.util.List;

public class ContextExcelInfo {
	
	private String contextID;
	private String contextName;	
	private List<String> dimensionPointIDs = new ArrayList<String>();
	
	public String getContextID() {
		return contextID;
	}
	public void setContextID(String contextID) {
		this.contextID = contextID;
	}
	public String getContextName() {
		return contextName;
	}
	public void setContextName(String contextName) {
		this.contextName = contextName;
	}
	public List<String> getDimensionPointIDs() {
		return dimensionPointIDs;
	}
	public void setDimensionPointIDs(List<String> dimensionPointIDs) {
		this.dimensionPointIDs = dimensionPointIDs;
	}


}
