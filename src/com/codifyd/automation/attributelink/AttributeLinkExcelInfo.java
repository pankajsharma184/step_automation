package com.codifyd.automation.attributelink;

import java.util.LinkedHashMap;
import java.util.Map;

public class AttributeLinkExcelInfo {
	
	private String productID;
	private String productName;
	private String parentID;
	private String objectType;
	private String attributeLink;
	private String mandatory;
	private String qualifierID;
	private String inherited;
	private String referenced;
	private Map<String, String> attributeLinkMetadata;
	
	public Map<String, String> createMetadatMap() {
		return new LinkedHashMap<String, String>();
	}
	
	/**
	 * @return the productID
	 */
	public String getProductID() {
		return productID;
	}
	/**
	 * @param productID the productID to set
	 */
	public void setProductID(String productID) {
		this.productID = productID;
	}
	/**
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}
	/**
	 * @param productName the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}
	/**
	 * @return the parentID
	 */
	public String getParentID() {
		return parentID;
	}

	/**
	 * @param parentID the parentID to set
	 */
	public void setParentID(String parentID) {
		this.parentID = parentID;
	}

	/**
	 * @return the objectType
	 */
	public String getObjectType() {
		return objectType;
	}

	/**
	 * @param objectType the objectType to set
	 */
	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	/**
	 * @return the attributeLink
	 */
	public String getAttributeLink() {
		return attributeLink;
	}
	/**
	 * @param attributeLink the attributeLink to set
	 */
	public void setAttributeLink(String attributeLink) {
		this.attributeLink = attributeLink;
	}
	/**
	 * @return the mandatory
	 */
	public String getMandatory() {
		return mandatory;
	}
	/**
	 * @param mandatory the mandatory to set
	 */
	public void setMandatory(String mandatory) {
		this.mandatory = mandatory;
	}
	/**
	 * @return the qualifierID
	 */
	public String getQualifierID() {
		return qualifierID;
	}
	/**
	 * @param qualifierID the qualifierID to set
	 */
	public void setQualifierID(String qualifierID) {
		this.qualifierID = qualifierID;
	}
	/**
	 * @return the inherited
	 */
	public String getInherited() {
		return inherited;
	}
	/**
	 * @param inherited the inherited to set
	 */
	public void setInherited(String inherited) {
		this.inherited = inherited;
	}
	/**
	 * @return the referenced
	 */
	public String getReferenced() {
		return referenced;
	}
	/**
	 * @param referenced the referenced to set
	 */
	public void setReferenced(String referenced) {
		this.referenced = referenced;
	}

	/**
	 * @return the attributeLinkMetadata
	 */
	public Map<String, String> getAttributeLinkMetadata() {
		return attributeLinkMetadata;
	}

	/**
	 * @param attributeLinkMetadata the attributeLinkMetadata to set
	 */
	public void setAttributeLinkMetadata(Map<String, String> attributeLinkMetadata) {
		this.attributeLinkMetadata = attributeLinkMetadata;
	}
	
	
}
