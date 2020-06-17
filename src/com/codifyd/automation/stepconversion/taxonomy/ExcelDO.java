package com.codifyd.automation.stepconversion.taxonomy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExcelDO {

	// Structure Maps
	List<Map<String, TaxonomyExcelInfo>> structureMapList = new ArrayList<Map<String, TaxonomyExcelInfo>>();

	public void setValues(List<Map<String, TaxonomyExcelInfo>> structureMapList) {
		this.structureMapList = structureMapList;
	}

}