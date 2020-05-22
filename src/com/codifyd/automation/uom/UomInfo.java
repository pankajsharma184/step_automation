package com.codifyd.automation.uom;

import java.util.LinkedHashMap;

public class UomInfo {
	private String id;
	private String name;
	private String parentId;
	private String parentName;
	private String referenced;
	private String baseUnitId;
	private String factor;
	private String offset;
	private LinkedHashMap<String, String> uomMetadata;

	public LinkedHashMap<String, String> createUomMetadatMap() {
		return new LinkedHashMap<String, String>();
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the parentId
	 */
	public String getParentId() {
		return parentId;
	}

	/**
	 * @param parentId the parentId to set
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	/**
	 * @return the parentName
	 */
	public String getParentName() {
		return parentName;
	}

	/**
	 * @param parentId the parentName to set
	 */
	public void setParentName(String parentName) {
		this.parentName = parentName;
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
	 * @return the baseUnitId
	 */
	public String getBaseUnitId() {
		return baseUnitId;
	}

	/**
	 * @param baseUnitId the baseUnitId to set
	 */
	public void setBaseUnitId(String baseUnitId) {
		this.baseUnitId = baseUnitId;
	}

	/**
	 * @return the factor
	 */
	public String getFactor() {
		return factor;
	}

	/**
	 * @param factor the factor to set
	 */
	public void setFactor(String factor) {
		this.factor = factor;
	}

	/**
	 * @return the offset
	 */
	public String getOffset() {
		return offset;
	}

	/**
	 * @param offset the offset to set
	 */
	public void setOffset(String offset) {
		this.offset = offset;
	}

	/**
	 * @return the uomMetadata
	 */
	public LinkedHashMap<String, String> getUomMetadata() {
		return uomMetadata;
	}

	/**
	 * @param uomMetadata the uomMetadata to set
	 */
	public void setUomMetadata(LinkedHashMap<String, String> uomMetadata) {
		this.uomMetadata = uomMetadata;
	}

}
