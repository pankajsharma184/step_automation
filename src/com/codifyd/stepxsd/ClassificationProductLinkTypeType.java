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
 * <p>Java class for ClassificationProductLinkTypeType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ClassificationProductLinkTypeType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Name" type="{http://www.stibosystems.com/step}NameType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="AttributeLink" type="{http://www.stibosystems.com/step}AttributeLinkType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="DimensionLink" type="{http://www.stibosystems.com/step}DimensionLinkType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="AttributeGroupLink" type="{http://www.stibosystems.com/step}AttributeGroupLinkType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="UserTypeLink" type="{http://www.stibosystems.com/step}UserTypeLinkType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="TargetUserTypeLink" type="{http://www.stibosystems.com/step}TargetUserTypeLinkType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Inherited" type="{http://www.stibosystems.com/step}TrueFalseType" default="false" />
 *       &lt;attribute name="ID" use="required" type="{http://www.stibosystems.com/step}StepLinkTypeID" />
 *       &lt;attribute name="DefinesValidAttributes" type="{http://www.stibosystems.com/step}TrueFalseType" default="false" />
 *       &lt;attribute name="IgnoreLOVFilters" type="{http://www.stibosystems.com/step}TrueFalseType" default="false" />
 *       &lt;attribute name="Revised" type="{http://www.stibosystems.com/step}TrueFalseType" default="true" />
 *       &lt;attribute name="MultiValued" type="{http://www.stibosystems.com/step}TrueFalseType" default="true" />
 *       &lt;attribute name="Mandatory" type="{http://www.stibosystems.com/step}TrueFalseType" default="false" />
 *       &lt;attribute name="Accumulated" type="{http://www.stibosystems.com/step}TrueFalseType" default="false" />
 *       &lt;attribute name="Selected" type="{http://www.stibosystems.com/step}SelectedType" default="true" />
 *       &lt;attribute name="Referenced" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ClassificationProductLinkTypeType", propOrder = {
    "name",
    "attributeLink",
    "dimensionLink",
    "attributeGroupLink",
    "userTypeLink",
    "targetUserTypeLink"
})
public class ClassificationProductLinkTypeType {

    @XmlElement(name = "Name")
    protected List<NameType> name;
    @XmlElement(name = "AttributeLink")
    protected List<AttributeLinkType> attributeLink;
    @XmlElement(name = "DimensionLink")
    protected List<DimensionLinkType> dimensionLink;
    @XmlElement(name = "AttributeGroupLink")
    protected List<AttributeGroupLinkType> attributeGroupLink;
    @XmlElement(name = "UserTypeLink")
    protected List<UserTypeLinkType> userTypeLink;
    @XmlElement(name = "TargetUserTypeLink")
    protected List<TargetUserTypeLinkType> targetUserTypeLink;
    @XmlAttribute(name = "Inherited")
    protected TrueFalseType inherited;
    @XmlAttribute(name = "ID", required = true)
    protected String id;
    @XmlAttribute(name = "DefinesValidAttributes")
    protected TrueFalseType definesValidAttributes;
    @XmlAttribute(name = "IgnoreLOVFilters")
    protected TrueFalseType ignoreLOVFilters;
    @XmlAttribute(name = "Revised")
    protected TrueFalseType revised;
    @XmlAttribute(name = "MultiValued")
    protected TrueFalseType multiValued;
    @XmlAttribute(name = "Mandatory")
    protected TrueFalseType mandatory;
    @XmlAttribute(name = "Accumulated")
    protected TrueFalseType accumulated;
    @XmlAttribute(name = "Selected")
    protected Boolean selected;
    @XmlAttribute(name = "Referenced")
    protected Boolean referenced;

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
     * Gets the value of the attributeLink property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the attributeLink property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAttributeLink().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AttributeLinkType }
     * 
     * 
     */
    public List<AttributeLinkType> getAttributeLink() {
        if (attributeLink == null) {
            attributeLink = new ArrayList<AttributeLinkType>();
        }
        return this.attributeLink;
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
     * Gets the value of the userTypeLink property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the userTypeLink property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUserTypeLink().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link UserTypeLinkType }
     * 
     * 
     */
    public List<UserTypeLinkType> getUserTypeLink() {
        if (userTypeLink == null) {
            userTypeLink = new ArrayList<UserTypeLinkType>();
        }
        return this.userTypeLink;
    }

    /**
     * Gets the value of the targetUserTypeLink property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the targetUserTypeLink property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTargetUserTypeLink().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TargetUserTypeLinkType }
     * 
     * 
     */
    public List<TargetUserTypeLinkType> getTargetUserTypeLink() {
        if (targetUserTypeLink == null) {
            targetUserTypeLink = new ArrayList<TargetUserTypeLinkType>();
        }
        return this.targetUserTypeLink;
    }

    /**
     * Gets the value of the inherited property.
     * 
     * @return
     *     possible object is
     *     {@link TrueFalseType }
     *     
     */
    public TrueFalseType getInherited() {
        if (inherited == null) {
            return TrueFalseType.FALSE;
        } else {
            return inherited;
        }
    }

    /**
     * Sets the value of the inherited property.
     * 
     * @param value
     *     allowed object is
     *     {@link TrueFalseType }
     *     
     */
    public void setInherited(TrueFalseType value) {
        this.inherited = value;
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
     * Gets the value of the definesValidAttributes property.
     * 
     * @return
     *     possible object is
     *     {@link TrueFalseType }
     *     
     */
    public TrueFalseType getDefinesValidAttributes() {
        if (definesValidAttributes == null) {
            return TrueFalseType.FALSE;
        } else {
            return definesValidAttributes;
        }
    }

    /**
     * Sets the value of the definesValidAttributes property.
     * 
     * @param value
     *     allowed object is
     *     {@link TrueFalseType }
     *     
     */
    public void setDefinesValidAttributes(TrueFalseType value) {
        this.definesValidAttributes = value;
    }

    /**
     * Gets the value of the ignoreLOVFilters property.
     * 
     * @return
     *     possible object is
     *     {@link TrueFalseType }
     *     
     */
    public TrueFalseType getIgnoreLOVFilters() {
        if (ignoreLOVFilters == null) {
            return TrueFalseType.FALSE;
        } else {
            return ignoreLOVFilters;
        }
    }

    /**
     * Sets the value of the ignoreLOVFilters property.
     * 
     * @param value
     *     allowed object is
     *     {@link TrueFalseType }
     *     
     */
    public void setIgnoreLOVFilters(TrueFalseType value) {
        this.ignoreLOVFilters = value;
    }

    /**
     * Gets the value of the revised property.
     * 
     * @return
     *     possible object is
     *     {@link TrueFalseType }
     *     
     */
    public TrueFalseType getRevised() {
        if (revised == null) {
            return TrueFalseType.TRUE;
        } else {
            return revised;
        }
    }

    /**
     * Sets the value of the revised property.
     * 
     * @param value
     *     allowed object is
     *     {@link TrueFalseType }
     *     
     */
    public void setRevised(TrueFalseType value) {
        this.revised = value;
    }

    /**
     * Gets the value of the multiValued property.
     * 
     * @return
     *     possible object is
     *     {@link TrueFalseType }
     *     
     */
    public TrueFalseType getMultiValued() {
        if (multiValued == null) {
            return TrueFalseType.TRUE;
        } else {
            return multiValued;
        }
    }

    /**
     * Sets the value of the multiValued property.
     * 
     * @param value
     *     allowed object is
     *     {@link TrueFalseType }
     *     
     */
    public void setMultiValued(TrueFalseType value) {
        this.multiValued = value;
    }

    /**
     * Gets the value of the mandatory property.
     * 
     * @return
     *     possible object is
     *     {@link TrueFalseType }
     *     
     */
    public TrueFalseType getMandatory() {
        if (mandatory == null) {
            return TrueFalseType.FALSE;
        } else {
            return mandatory;
        }
    }

    /**
     * Sets the value of the mandatory property.
     * 
     * @param value
     *     allowed object is
     *     {@link TrueFalseType }
     *     
     */
    public void setMandatory(TrueFalseType value) {
        this.mandatory = value;
    }

    /**
     * Gets the value of the accumulated property.
     * 
     * @return
     *     possible object is
     *     {@link TrueFalseType }
     *     
     */
    public TrueFalseType getAccumulated() {
        if (accumulated == null) {
            return TrueFalseType.FALSE;
        } else {
            return accumulated;
        }
    }

    /**
     * Sets the value of the accumulated property.
     * 
     * @param value
     *     allowed object is
     *     {@link TrueFalseType }
     *     
     */
    public void setAccumulated(TrueFalseType value) {
        this.accumulated = value;
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

}
