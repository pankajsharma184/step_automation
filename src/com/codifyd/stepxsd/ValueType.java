//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.04.16 at 01:19:41 PM IST 
//


package com.codifyd.stepxsd;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;


/**
 * 
 *                 A value describes the parts that makes up the attribute-value of a product
 *             
 * 
 * <p>Java class for ValueType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ValueType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="UnitID" type="{http://www.stibosystems.com/step}StepUnitID" />
 *       &lt;attribute name="Changed" type="{http://www.stibosystems.com/step}TrueFalseType" default="false" />
 *       &lt;attribute name="LOVQualifierID" type="{http://www.stibosystems.com/step}StepQualifierID" />
 *       &lt;attribute name="AttributeID" type="{http://www.stibosystems.com/step}StepAttributeID" />
 *       &lt;attribute name="DerivedContextID" type="{http://www.stibosystems.com/step}StepQualifierID" />
 *       &lt;attribute name="ID" type="{http://www.stibosystems.com/step}StepID" />
 *       &lt;attribute name="QualifierID" type="{http://www.stibosystems.com/step}StepQualifierID" />
 *       &lt;attribute name="InheritedFrom" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Inherited" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Derived" type="{http://www.stibosystems.com/step}TrueFalseType" default="false" />
 *       &lt;attribute name="UnitName" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ValueType", propOrder = {
    "content"
})
public class ValueType {

    @XmlValue
    protected String content;
    @XmlAttribute(name = "UnitID")
    protected String unitID;
    @XmlAttribute(name = "Changed")
    protected TrueFalseType changed;
    @XmlAttribute(name = "LOVQualifierID")
    protected String lovQualifierID;
    @XmlAttribute(name = "AttributeID")
    protected String attributeID;
    @XmlAttribute(name = "DerivedContextID")
    protected String derivedContextID;
    @XmlAttribute(name = "ID")
    protected String id;
    @XmlAttribute(name = "QualifierID")
    protected String qualifierID;
    @XmlAttribute(name = "InheritedFrom")
    protected String inheritedFrom;
    @XmlAttribute(name = "Inherited")
    protected String inherited;
    @XmlAttribute(name = "Derived")
    protected TrueFalseType derived;
    @XmlAttribute(name = "UnitName")
    protected String unitName;

    /**
     * 
     *                 A value describes the parts that makes up the attribute-value of a product
     *             
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the value of the content property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContent(String value) {
        this.content = value;
    }

    /**
     * Gets the value of the unitID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnitID() {
        return unitID;
    }

    /**
     * Sets the value of the unitID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnitID(String value) {
        this.unitID = value;
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

    /**
     * Gets the value of the lovQualifierID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLOVQualifierID() {
        return lovQualifierID;
    }

    /**
     * Sets the value of the lovQualifierID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLOVQualifierID(String value) {
        this.lovQualifierID = value;
    }

    /**
     * Gets the value of the attributeID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAttributeID() {
        return attributeID;
    }

    /**
     * Sets the value of the attributeID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAttributeID(String value) {
        this.attributeID = value;
    }

    /**
     * Gets the value of the derivedContextID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDerivedContextID() {
        return derivedContextID;
    }

    /**
     * Sets the value of the derivedContextID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDerivedContextID(String value) {
        this.derivedContextID = value;
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
     * Gets the value of the qualifierID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQualifierID() {
        return qualifierID;
    }

    /**
     * Sets the value of the qualifierID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQualifierID(String value) {
        this.qualifierID = value;
    }

    /**
     * Gets the value of the inheritedFrom property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInheritedFrom() {
        return inheritedFrom;
    }

    /**
     * Sets the value of the inheritedFrom property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInheritedFrom(String value) {
        this.inheritedFrom = value;
    }

    /**
     * Gets the value of the inherited property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInherited() {
        return inherited;
    }

    /**
     * Sets the value of the inherited property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInherited(String value) {
        this.inherited = value;
    }

    /**
     * Gets the value of the derived property.
     * 
     * @return
     *     possible object is
     *     {@link TrueFalseType }
     *     
     */
    public TrueFalseType getDerived() {
        if (derived == null) {
            return TrueFalseType.FALSE;
        } else {
            return derived;
        }
    }

    /**
     * Sets the value of the derived property.
     * 
     * @param value
     *     allowed object is
     *     {@link TrueFalseType }
     *     
     */
    public void setDerived(TrueFalseType value) {
        this.derived = value;
    }

    /**
     * Gets the value of the unitName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnitName() {
        return unitName;
    }

    /**
     * Sets the value of the unitName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnitName(String value) {
        this.unitName = value;
    }

}
