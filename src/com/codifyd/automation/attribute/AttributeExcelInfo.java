package com.codifyd.automation.attribute;

import java.util.LinkedHashMap;
import java.util.Map;

public class AttributeInfo {

	private String AttributeID;
	private String AttributeName;
	private String FullTextIndexed;
	private String ExternallyMaitained;
	private String CompletenessScore;
	private String HierarchialFiltering;
	private String ClassificationHierarchialFiltering;
	private String Calculated;
	private String ValueTemplate;
	private String Type;
	private String Dimension_Dependencies;
	private String Mandatory;
	private String Validation_Base_Type;
	private String List_of_Values;
	private String Multi_Valued;
	private String Mask;
	private String Minimum_Value;
	private String Maximum_Value;
	private String Maximum_Length;
	private String Unit_ID;
	private String Unit_Name;
	private String Unit_Description;
	private String Default_Unit_ID;
	private String Default_Unit_Name;
	private String Default_Unit_Description;
	private String Attribute_Group_Reference_ID;
	private String Attribute_Group_Reference_Name;
	private String Validity;
	private String LinkType;
	private Map<String, String> attributeMetadata;
	
	
	public Map<String, String> createMetadatMap() {
		return new LinkedHashMap<String, String>();
	}

	/**
	 * @return the attributeID
	 */
	public String getAttributeID() {
		return AttributeID;
	}

	/**
	 * @param attributeID the attributeID to set
	 */
	public void setAttributeID(String attributeID) {
		AttributeID = attributeID;
	}

	/**
	 * @return the attributeName
	 */
	public String getAttributeName() {
		return AttributeName;
	}

	/**
	 * @param attributeName the attributeName to set
	 */
	public void setAttributeName(String attributeName) {
		AttributeName = attributeName;
	}

	/**
	 * @return the fullTextIndexed
	 */
	public String getFullTextIndexed() {
		return FullTextIndexed;
	}

	/**
	 * @param fullTextIndexed the fullTextIndexed to set
	 */
	public void setFullTextIndexed(String fullTextIndexed) {
		FullTextIndexed = fullTextIndexed;
	}

	/**
	 * @return the externallyMaitained
	 */
	public String getExternallyMaitained() {
		return ExternallyMaitained;
	}

	/**
	 * @param externallyMaitained the externallyMaitained to set
	 */
	public void setExternallyMaitained(String externallyMaitained) {
		ExternallyMaitained = externallyMaitained;
	}

	/**
	 * @return the completenessScore
	 */
	public String getCompletenessScore() {
		return CompletenessScore;
	}

	/**
	 * @param completenessScore the completenessScore to set
	 */
	public void setCompletenessScore(String completenessScore) {
		CompletenessScore = completenessScore;
	}

	/**
	 * @return the hierarchialFiltering
	 */
	public String getHierarchialFiltering() {
		return HierarchialFiltering;
	}

	/**
	 * @param hierarchialFiltering the hierarchialFiltering to set
	 */
	public void setHierarchialFiltering(String hierarchialFiltering) {
		HierarchialFiltering = hierarchialFiltering;
	}

	/**
	 * @return the classificationHierarchialFiltering
	 */
	public String getClassificationHierarchialFiltering() {
		return ClassificationHierarchialFiltering;
	}

	/**
	 * @param classificationHierarchialFiltering the classificationHierarchialFiltering to set
	 */
	public void setClassificationHierarchialFiltering(String classificationHierarchialFiltering) {
		ClassificationHierarchialFiltering = classificationHierarchialFiltering;
	}

	/**
	 * @return the calculated
	 */
	public String getCalculated() {
		return Calculated;
	}

	/**
	 * @param calculated the calculated to set
	 */
	public void setCalculated(String calculated) {
		Calculated = calculated;
	}

	/**
	 * @return the valueTemplate
	 */
	public String getValueTemplate() {
		return ValueTemplate;
	}

	/**
	 * @param valueTemplate the valueTemplate to set
	 */
	public void setValueTemplate(String valueTemplate) {
		ValueTemplate = valueTemplate;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return Type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		Type = type;
	}

	/**
	 * @return the dimension_Dependencies
	 */
	public String getDimension_Dependencies() {
		return Dimension_Dependencies;
	}

	/**
	 * @param dimension_Dependencies the dimension_Dependencies to set
	 */
	public void setDimension_Dependencies(String dimension_Dependencies) {
		Dimension_Dependencies = dimension_Dependencies;
	}

	/**
	 * @return the mandatory
	 */
	public String getMandatory() {
		return Mandatory;
	}

	/**
	 * @param mandatory the mandatory to set
	 */
	public void setMandatory(String mandatory) {
		Mandatory = mandatory;
	}

	/**
	 * @return the validation_Base_Type
	 */
	public String getValidation_Base_Type() {
		return Validation_Base_Type;
	}

	/**
	 * @param validation_Base_Type the validation_Base_Type to set
	 */
	public void setValidation_Base_Type(String validation_Base_Type) {
		Validation_Base_Type = validation_Base_Type;
	}

	/**
	 * @return the list_of_Values
	 */
	public String getList_of_Values() {
		return List_of_Values;
	}

	/**
	 * @param list_of_Values the list_of_Values to set
	 */
	public void setList_of_Values(String list_of_Values) {
		List_of_Values = list_of_Values;
	}

	/**
	 * @return the multi_Valued
	 */
	public String getMulti_Valued() {
		return Multi_Valued;
	}

	/**
	 * @param multi_Valued the multi_Valued to set
	 */
	public void setMulti_Valued(String multi_Valued) {
		Multi_Valued = multi_Valued;
	}

	/**
	 * @return the mask
	 */
	public String getMask() {
		return Mask;
	}

	/**
	 * @param mask the mask to set
	 */
	public void setMask(String mask) {
		Mask = mask;
	}

	/**
	 * @return the minimum_Value
	 */
	public String getMinimum_Value() {
		return Minimum_Value;
	}

	/**
	 * @param minimum_Value the minimum_Value to set
	 */
	public void setMinimum_Value(String minimum_Value) {
		Minimum_Value = minimum_Value;
	}

	/**
	 * @return the maximum_Value
	 */
	public String getMaximum_Value() {
		return Maximum_Value;
	}

	/**
	 * @param maximum_Value the maximum_Value to set
	 */
	public void setMaximum_Value(String maximum_Value) {
		Maximum_Value = maximum_Value;
	}

	/**
	 * @return the maximum_Length
	 */
	public String getMaximum_Length() {
		return Maximum_Length;
	}

	/**
	 * @param maximum_Length the maximum_Length to set
	 */
	public void setMaximum_Length(String maximum_Length) {
		Maximum_Length = maximum_Length;
	}

	/**
	 * @return the unit_ID
	 */
	public String getUnit_ID() {
		return Unit_ID;
	}

	/**
	 * @param unit_ID the unit_ID to set
	 */
	public void setUnit_ID(String unit_ID) {
		Unit_ID = unit_ID;
	}

	/**
	 * @return the unit_Name
	 */
	public String getUnit_Name() {
		return Unit_Name;
	}

	/**
	 * @param unit_Name the unit_Name to set
	 */
	public void setUnit_Name(String unit_Name) {
		Unit_Name = unit_Name;
	}

	/**
	 * @return the unit_Description
	 */
	public String getUnit_Description() {
		return Unit_Description;
	}

	/**
	 * @param unit_Description the unit_Description to set
	 */
	public void setUnit_Description(String unit_Description) {
		Unit_Description = unit_Description;
	}

	/**
	 * @return the default_Unit_ID
	 */
	public String getDefault_Unit_ID() {
		return Default_Unit_ID;
	}

	/**
	 * @param default_Unit_ID the default_Unit_ID to set
	 */
	public void setDefault_Unit_ID(String default_Unit_ID) {
		Default_Unit_ID = default_Unit_ID;
	}

	/**
	 * @return the default_Unit_Name
	 */
	public String getDefault_Unit_Name() {
		return Default_Unit_Name;
	}

	/**
	 * @param default_Unit_Name the default_Unit_Name to set
	 */
	public void setDefault_Unit_Name(String default_Unit_Name) {
		Default_Unit_Name = default_Unit_Name;
	}

	/**
	 * @return the default_Unit_Description
	 */
	public String getDefault_Unit_Description() {
		return Default_Unit_Description;
	}

	/**
	 * @param default_Unit_Description the default_Unit_Description to set
	 */
	public void setDefault_Unit_Description(String default_Unit_Description) {
		Default_Unit_Description = default_Unit_Description;
	}

	/**
	 * @return the attribute_Group_Reference_ID
	 */
	public String getAttribute_Group_Reference_ID() {
		return Attribute_Group_Reference_ID;
	}

	/**
	 * @param attribute_Group_Reference_ID the attribute_Group_Reference_ID to set
	 */
	public void setAttribute_Group_Reference_ID(String attribute_Group_Reference_ID) {
		Attribute_Group_Reference_ID = attribute_Group_Reference_ID;
	}

	/**
	 * @return the attribute_Group_Reference_Name
	 */
	public String getAttribute_Group_Reference_Name() {
		return Attribute_Group_Reference_Name;
	}

	/**
	 * @param attribute_Group_Reference_Name the attribute_Group_Reference_Name to set
	 */
	public void setAttribute_Group_Reference_Name(String attribute_Group_Reference_Name) {
		Attribute_Group_Reference_Name = attribute_Group_Reference_Name;
	}

	/**
	 * @return the validity
	 */
	public String getValidity() {
		return Validity;
	}

	/**
	 * @param validity the validity to set
	 */
	public void setValidity(String validity) {
		Validity = validity;
	}

	

	/**
	 * @return the linkType
	 */
	public String getLinkType() {
		return LinkType;
	}

	/**
	 * @param linkType the linkType to set
	 */
	public void setLinkType(String linkType) {
		LinkType = linkType;
	}

	/**
	 * @return the attributeMetadata
	 */
	public Map<String, String> getAttributeMetadata() {
		return attributeMetadata;
	}

	/**
	 * @param attributeMetadata the attributeMetadata to set
	 */
	public void setAttributeMetadata(Map<String, String> attributeMetadata) {
		this.attributeMetadata = attributeMetadata;
	}

}
