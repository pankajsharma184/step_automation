//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.04.16 at 01:19:41 PM IST 
//


package com.codifyd.stepxsd;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *                 Represents a LOV. Values in this LOV have the dimension dependencies described by the dimension links
 *                 and must conform to the validation rules described in Validation. The list of values is described by the
 *                 nested Value and/or ValueGroup elements.
 *             
 * 
 * <p>Java class for ListOfValueType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ListOfValueType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Name" type="{http://www.stibosystems.com/step}NameType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Validation" type="{http://www.stibosystems.com/step}ValidationType"/>
 *         &lt;element name="DimensionLink" type="{http://www.stibosystems.com/step}DimensionLinkType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="MetaData" type="{http://www.stibosystems.com/step}MetaDataType" minOccurs="0"/>
 *         &lt;element name="AttributeGroupLink" type="{http://www.stibosystems.com/step}AttributeGroupLinkType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;choice>
 *           &lt;element name="Value" type="{http://www.stibosystems.com/step}ValueType" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;element name="ValueGroup" type="{http://www.stibosystems.com/step}ValueGroupType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;/choice>
 *         &lt;element name="DeleteValue" type="{http://www.stibosystems.com/step}DeleteValueType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="ID" type="{http://www.stibosystems.com/step}StepLOVID" />
 *       &lt;attribute name="ParentID" type="{http://www.stibosystems.com/step}StepID" />
 *       &lt;attribute name="AllowUserValueAddition" type="{http://www.stibosystems.com/step}TrueFalseType" default="false" />
 *       &lt;attribute name="UseValueID" type="{http://www.stibosystems.com/step}TrueFalseType" default="false" />
 *       &lt;attribute name="IDPattern" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="ReplaceListOfValuesValues" type="{http://www.stibosystems.com/step}TrueFalseType" default="false" />
 *       &lt;attribute name="DefaultUnitID" type="{http://www.stibosystems.com/step}StepID" />
 *       &lt;attribute name="Referenced" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *       &lt;attribute name="Selected" type="{http://www.stibosystems.com/step}SelectedType" default="true" />
 *       &lt;attribute name="Changed" type="{http://www.stibosystems.com/step}TrueFalseType" default="false" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ListOfValueType", propOrder = {
    "name",
    "validation",
    "dimensionLink",
    "metaData",
    "attributeGroupLink",
    "value",
    "valueGroup",
    "deleteValue"
})
public class ListOfValueType {

    @XmlElement(name = "Name")
    protected List<NameType> name;
    @XmlElement(name = "Validation", required = true)
    protected ValidationType validation;
    @XmlElement(name = "DimensionLink")
    protected List<DimensionLinkType> dimensionLink;
    @XmlElement(name = "MetaData")
    protected MetaDataType metaData;
    @XmlElement(name = "AttributeGroupLink")
    protected List<AttributeGroupLinkType> attributeGroupLink;
    @XmlElement(name = "Value")
    protected List<ValueType> value;
    @XmlElement(name = "ValueGroup")
    protected List<ValueGroupType> valueGroup;
    @XmlElement(name = "DeleteValue")
    protected List<DeleteValueType> deleteValue;
    @XmlAttribute(name = "ID")
    protected String id;
    @XmlAttribute(name = "ParentID")
    protected String parentID;
    @XmlAttribute(name = "AllowUserValueAddition")
    protected TrueFalseType allowUserValueAddition;
    @XmlAttribute(name = "UseValueID")
    protected TrueFalseType useValueID;
    @XmlAttribute(name = "IDPattern")
    protected String idPattern;
    @XmlAttribute(name = "ReplaceListOfValuesValues")
    protected TrueFalseType replaceListOfValuesValues;
    @XmlAttribute(name = "DefaultUnitID")
    protected String defaultUnitID;
    @XmlAttribute(name = "Referenced")
    protected Boolean referenced;
    @XmlAttribute(name = "Selected")
    protected Boolean selected;
    @XmlAttribute(name = "Changed")
    protected TrueFalseType changed;

    /**
     * Gets the value of the name property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the name property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getName().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link NameType }
     * 
     * 
     */
    public List<NameType> getName() {
        if (name == null) {
            name = new ArrayList<NameType>();
        }
        return this.name;
    }

    /**
     * Gets the value of the validation property.
     * 
     * @return
     *     possible object is
     *     {@link ValidationType }
     *     
     */
    public ValidationType getValidation() {
        return validation;
    }

    /**
     * Sets the value of the validation property.
     * 
     * @param value
     *     allowed object is
     *     {@link ValidationType }
     *     
     */
    public void setValidation(ValidationType value) {
        this.validation = value;
    }

    /**
     * Gets the value of the dimensionLink property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dimensionLink property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDimensionLink().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DimensionLinkType }
     * 
     * 
     */
    public List<DimensionLinkType> getDimensionLink() {
        if (dimensionLink == null) {
            dimensionLink = new ArrayList<DimensionLinkType>();
        }
        return this.dimensionLink;
    }

    /**
     * Gets the value of the metaData property.
     * 
     * @return
     *     possible object is
     *     {@link MetaDataType }
     *     
     */
    public MetaDataType getMetaData() {
        return metaData;
    }

    /**
     * Sets the value of the metaData property.
     * 
     * @param value
     *     allowed object is
     *     {@link MetaDataType }
     *     
     */
    public void setMetaData(MetaDataType value) {
        this.metaData = value;
    }

    /**
     * Gets the value of the attributeGroupLink property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the attributeGroupLink property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAttributeGroupLink().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AttributeGroupLinkType }
     * 
     * 
     */
    public List<AttributeGroupLinkType> getAttributeGroupLink() {
        if (attributeGroupLink == null) {
            attributeGroupLink = new ArrayList<AttributeGroupLinkType>();
        }
        return this.attributeGroupLink;
    }

    /**
     * Gets the value of the value property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the value property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getValue().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ValueType }
     * 
     * 
     */
    public List<ValueType> getValue() {
        if (value == null) {
            value = new ArrayList<ValueType>();
        }
        return this.value;
    }

    /**
     * Gets the value of the valueGroup property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the valueGroup property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getValueGroup().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ValueGroupType }
     * 
     * 
     */
    public List<ValueGroupType> getValueGroup() {
        if (valueGroup == null) {
            valueGroup = new ArrayList<ValueGroupType>();
        }
        return this.valueGroup;
    }

    /**
     * Gets the value of the deleteValue property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the deleteValue property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDeleteValue().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DeleteValueType }
     * 
     * 
     */
    public List<DeleteValueType> getDeleteValue() {
        if (deleteValue == null) {
            deleteValue = new ArrayList<DeleteValueType>();
        }
        return this.deleteValue;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getID() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setID(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the parentID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParentID() {
        return parentID;
    }

    /**
     * Sets the value of the parentID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParentID(String value) {
        this.parentID = value;
    }

    /**
     * Gets the value of the allowUserValueAddition property.
     * 
     * @return
     *     possible object is
     *     {@link TrueFalseType }
     *     
     */
    public TrueFalseType getAllowUserValueAddition() {
        if (allowUserValueAddition == null) {
            return TrueFalseType.FALSE;
        } else {
            return allowUserValueAddition;
        }
    }

    /**
     * Sets the value of the allowUserValueAddition property.
     * 
     * @param value
     *     allowed object is
     *     {@link TrueFalseType }
     *     
     */
    public void setAllowUserValueAddition(TrueFalseType value) {
        this.allowUserValueAddition = value;
    }

    /**
     * Gets the value of the useValueID property.
     * 
     * @return
     *     possible object is
     *     {@link TrueFalseType }
     *     
     */
    public TrueFalseType getUseValueID() {
        if (useValueID == null) {
            return TrueFalseType.FALSE;
        } else {
            return useValueID;
        }
    }

    /**
     * Sets the value of the useValueID property.
     * 
     * @param value
     *     allowed object is
     *     {@link TrueFalseType }
     *     
     */
    public void setUseValueID(TrueFalseType value) {
        this.useValueID = value;
    }

    /**
     * Gets the value of the idPattern property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIDPattern() {
        return idPattern;
    }

    /**
     * Sets the value of the idPattern property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIDPattern(String value) {
        this.idPattern = value;
    }

    /**
     * Gets the value of the replaceListOfValuesValues property.
     * 
     * @return
     *     possible object is
     *     {@link TrueFalseType }
     *     
     */
    public TrueFalseType getReplaceListOfValuesValues() {
        if (replaceListOfValuesValues == null) {
            return TrueFalseType.FALSE;
        } else {
            return replaceListOfValuesValues;
        }
    }

    /**
     * Sets the value of the replaceListOfValuesValues property.
     * 
     * @param value
     *     allowed object is
     *     {@link TrueFalseType }
     *     
     */
    public void setReplaceListOfValuesValues(TrueFalseType value) {
        this.replaceListOfValuesValues = value;
    }

    /**
     * Gets the value of the defaultUnitID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDefaultUnitID() {
        return defaultUnitID;
    }

    /**
     * Sets the value of the defaultUnitID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDefaultUnitID(String value) {
        this.defaultUnitID = value;
    }

    /**
     * Gets the value of the referenced property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isReferenced() {
        if (referenced == null) {
            return false;
        } else {
            return referenced;
        }
    }

    /**
     * Sets the value of the referenced property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setReferenced(Boolean value) {
        this.referenced = value;
    }

    /**
     * Gets the value of the selected property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isSelected() {
        if (selected == null) {
            return true;
        } else {
            return selected;
        }
    }

    /**
     * Sets the value of the selected property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSelected(Boolean value) {
        this.selected = value;
    }

    /**
     * Gets the value of the changed property.
     * 
     * @return
     *     possible object is
     *     {@link TrueFalseType }
     *     
     */
    public TrueFalseType getChanged() {
        if (changed == null) {
            return TrueFalseType.FALSE;
        } else {
            return changed;
        }
    }

    /**
     * Sets the value of the changed property.
     * 
     * @param value
     *     allowed object is
     *     {@link TrueFalseType }
     *     
     */
    public void setChanged(TrueFalseType value) {
        this.changed = value;
    }

}