package com.codifyd.automation.stepconversion.taxonomy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaxonomyExcelInfo {
	String ID;
	String Name;
	String objectType;
	String parentID;
	List<String> children = new ArrayList<>();
	String uniqueKeyID;
	String uniqueKeyValue;
	String structurePath;
	Map<String, String> attributeValues = new HashMap<>();

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getObjectType() {
		return objectType;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	public String getParentID() {
		return parentID;
	}

	public void setParentID(String parentID) {
		this.parentID = parentID;
	}

	public List<String> getChildren() {
		return children;
	}

	public void setChildren(List<String> children) {
		this.children = children;
	}

	public String getUniqueKeyID() {
		return uniqueKeyID;
	}

	public void setUniqueKeyID(String uniqueKeyID) {
		this.uniqueKeyID = uniqueKeyID;
	}

	public String getUniqueKeyValue() {
		return uniqueKeyValue;
	}

	public void setUniqueKeyValue(String uniqueKeyValue) {
		this.uniqueKeyValue = uniqueKeyValue;
	}

	public String getStructurePath() {
		return structurePath;
	}

	public void setStructurePath(String structurePath) {
		this.structurePath = structurePath;
	}

	public Map<String, String> getAttributeValues() {
		return attributeValues;
	}

	public void setAttributeValues(Map<String, String> attributeValues) {
		this.attributeValues = attributeValues;
	}

}
