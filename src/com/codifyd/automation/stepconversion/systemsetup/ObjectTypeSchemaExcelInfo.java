package com.codifyd.automation.stepconversion.systemsetup;

import java.util.HashSet;
import java.util.Set;

public class ObjectTypeSchemaExcelInfo {

	private String objectTypeID;
	private String idPattern;
	private String objectTypeName;
	private Set<String> parentID;

	/**
	 * @return the objectTypeID
	 */
	public String getObjectTypeID() {
		return objectTypeID;
	}

	/**
	 * @param objectTypeID the objectTypeID to set
	 */
	public void setObjectTypeID(String objectTypeID) {
		this.objectTypeID = objectTypeID;
	}

	/**
	 * @return the idPattern
	 */
	public String getIdPattern() {
		return idPattern;
	}

	/**
	 * @param idPattern the idPattern to set
	 */
	public void setIdPattern(String idPattern) {
		this.idPattern = idPattern;
	}

	/**
	 * @return the objectTypeName
	 */
	public String getName() {
		return objectTypeName;
	}

	/**
	 * @param objectTypeName the objectTypeName to set
	 */
	public void setName(String name) {
		objectTypeName = name;
	}

	/**
	 * @return the parentID
	 */
	public Set<String> getParentID() {
		return parentID;
	}

	/**
	 * @param parentID the parentID to set
	 */
	public void setParentID(Set<String> parentID) {
		this.parentID = parentID;
	}

	/**
	 * @param parentID the parentID to set
	 */
	public Set<String> createParentID() {
		return new HashSet<String>();
	}

}
