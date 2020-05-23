package com.codifyd.automation.lov;

import java.util.LinkedHashMap;
import java.util.Map;

public class LovExcelInfo {

	private String allowUserValueAddition;
	private String lovID;
	private String lovName;
	private String lovGroupID;
	private String referenced;
	private String userValueID;
	private String baseType;
	private String inputMask;
	private String maxLength;
	private String maxValue;
	private String minValue;
	private String valueID;
	private String valueName;
	
	private Map<String, String> lovMetadata;
	
	public Map<String, String> createMetadatMap() {
		return new LinkedHashMap<String, String>();
	}
	
	
	/**
	 * @return the allowUserValueAddition
	 */
	public String getAllowUserValueAddition() {
		return allowUserValueAddition;
	}
	/**
	 * @param allowUserValueAddition the allowUserValueAddition to set
	 */
	public void setAllowUserValueAddition(String allowUserValueAddition) {
		this.allowUserValueAddition = allowUserValueAddition;
	}
	/**
	 * @return the lovID
	 */
	public String getLovID() {
		return lovID;
	}
	/**
	 * @param lovID the lovID to set
	 */
	public void setLovID(String lovID) {
		this.lovID = lovID;
	}
	/**
	 * @return the lovName
	 */
	public String getLovName() {
		return lovName;
	}
	/**
	 * @param lovName the lovName to set
	 */
	public void setLovName(String lovName) {
		this.lovName = lovName;
	}
	/**
	 * @return the lovGroupID
	 */
	public String getLovGroupID() {
		return lovGroupID;
	}
	/**
	 * @param lovGroupID the lovGroupID to set
	 */
	public void setLovGroupID(String lovGroupID) {
		this.lovGroupID = lovGroupID;
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
	 * @return the userValueID
	 */
	public String getUserValueID() {
		return userValueID;
	}
	/**
	 * @param userValueID the userValueID to set
	 */
	public void setUserValueID(String userValueID) {
		this.userValueID = userValueID;
	}
	/**
	 * @return the baseType
	 */
	public String getBaseType() {
		return baseType;
	}
	/**
	 * @param baseType the baseType to set
	 */
	public void setBaseType(String baseType) {
		this.baseType = baseType;
	}
	/**
	 * @return the inputMask
	 */
	public String getInputMask() {
		return inputMask;
	}
	/**
	 * @param inputMask the inputMask to set
	 */
	public void setInputMask(String inputMask) {
		this.inputMask = inputMask;
	}
	/**
	 * @return the maxLength
	 */
	public String getMaxLength() {
		return maxLength;
	}
	/**
	 * @param maxLength the maxLength to set
	 */
	public void setMaxLength(String maxLength) {
		this.maxLength = maxLength;
	}
	/**
	 * @return the maxValue
	 */
	public String getMaxValue() {
		return maxValue;
	}
	/**
	 * @param maxValue the maxValue to set
	 */
	public void setMaxValue(String maxValue) {
		this.maxValue = maxValue;
	}
	/**
	 * @return the minValue
	 */
	public String getMinValue() {
		return minValue;
	}
	/**
	 * @param minValue the minValue to set
	 */
	public void setMinValue(String minValue) {
		this.minValue = minValue;
	}
	/**
	 * @return the valueID
	 */
	public String getValueID() {
		return valueID;
	}
	/**
	 * @param valueID the valueID to set
	 */
	public void setValueID(String valueID) {
		this.valueID = valueID;
	}
	/**
	 * @return the valueName
	 */
	public String getValueName() {
		return valueName;
	}
	/**
	 * @param valueName the valueName to set
	 */
	public void setValueName(String valueName) {
		this.valueName = valueName;
	}


	/**
	 * @return the lovMetadata
	 */
	public Map<String, String> getLovMetadata() {
		return lovMetadata;
	}


	/**
	 * @param lovMetadata the lovMetadata to set
	 */
	public void setLovMetadata(Map<String, String> lovMetadata) {
		this.lovMetadata = lovMetadata;
	}
	
	
	
}