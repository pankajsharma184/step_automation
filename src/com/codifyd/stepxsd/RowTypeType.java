//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.04.16 at 01:19:41 PM IST 
//


package com.codifyd.stepxsd;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RowTypeType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RowTypeType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Name" type="{http://www.stibosystems.com/step}NameType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="TableTypeLink" type="{http://www.stibosystems.com/step}TableTypeLinkType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="PublicationTypeValidity" type="{http://www.stibosystems.com/step}PublicationTypeValidityType" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="ID" use="required" type="{http://www.stibosystems.com/step}StepAttributeID" />
 *       &lt;attribute name="ParentID" type="{http://www.stibosystems.com/step}StepAttributeID" />
 *       &lt;attribute name="Height" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="HeightUnit" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="TextStyle" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="BackgroundColor" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="VerticalAlignment" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="RuleAbove" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="RuleBelow" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="CellRotation" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="ProcessFlag" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="CellVerticalStoryDirection" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="CellImageScale" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="KeepWithNext" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="HeadingLevel" type="{http://www.w3.org/2001/XMLSchema}integer" />
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
@XmlType(name = "RowTypeType", propOrder = {
    "name",
    "tableTypeLink",
    "publicationTypeValidity"
})
public class RowTypeType {

    @XmlElement(name = "Name")
    protected List<NameType> name;
    @XmlElement(name = "TableTypeLink")
    protected List<TableTypeLinkType> tableTypeLink;
    @XmlElement(name = "PublicationTypeValidity")
    protected PublicationTypeValidityType publicationTypeValidity;
    @XmlAttribute(name = "ID", required = true)
    protected String id;
    @XmlAttribute(name = "ParentID")
    protected String parentID;
    @XmlAttribute(name = "Height")
    protected BigDecimal height;
    @XmlAttribute(name = "HeightUnit")
    protected String heightUnit;
    @XmlAttribute(name = "TextStyle")
    protected String textStyle;
    @XmlAttribute(name = "BackgroundColor")
    protected String backgroundColor;
    @XmlAttribute(name = "VerticalAlignment")
    protected String verticalAlignment;
    @XmlAttribute(name = "RuleAbove")
    protected String ruleAbove;
    @XmlAttribute(name = "RuleBelow")
    protected String ruleBelow;
    @XmlAttribute(name = "CellRotation")
    protected String cellRotation;
    @XmlAttribute(name = "ProcessFlag")
    protected String processFlag;
    @XmlAttribute(name = "CellVerticalStoryDirection")
    protected String cellVerticalStoryDirection;
    @XmlAttribute(name = "CellImageScale")
    protected String cellImageScale;
    @XmlAttribute(name = "KeepWithNext")
    protected Boolean keepWithNext;
    @XmlAttribute(name = "HeadingLevel")
    protected BigInteger headingLevel;
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
     * Gets the value of the tableTypeLink property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the tableTypeLink property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTableTypeLink().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TableTypeLinkType }
     * 
     * 
     */
    public List<TableTypeLinkType> getTableTypeLink() {
        if (tableTypeLink == null) {
            tableTypeLink = new ArrayList<TableTypeLinkType>();
        }
        return this.tableTypeLink;
    }

    /**
     * Gets the value of the publicationTypeValidity property.
     * 
     * @return
     *     possible object is
     *     {@link PublicationTypeValidityType }
     *     
     */
    public PublicationTypeValidityType getPublicationTypeValidity() {
        return publicationTypeValidity;
    }

    /**
     * Sets the value of the publicationTypeValidity property.
     * 
     * @param value
     *     allowed object is
     *     {@link PublicationTypeValidityType }
     *     
     */
    public void setPublicationTypeValidity(PublicationTypeValidityType value) {
        this.publicationTypeValidity = value;
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
     * Gets the value of the height property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getHeight() {
        return height;
    }

    /**
     * Sets the value of the height property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setHeight(BigDecimal value) {
        this.height = value;
    }

    /**
     * Gets the value of the heightUnit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHeightUnit() {
        return heightUnit;
    }

    /**
     * Sets the value of the heightUnit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHeightUnit(String value) {
        this.heightUnit = value;
    }

    /**
     * Gets the value of the textStyle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTextStyle() {
        return textStyle;
    }

    /**
     * Sets the value of the textStyle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTextStyle(String value) {
        this.textStyle = value;
    }

    /**
     * Gets the value of the backgroundColor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBackgroundColor() {
        return backgroundColor;
    }

    /**
     * Sets the value of the backgroundColor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBackgroundColor(String value) {
        this.backgroundColor = value;
    }

    /**
     * Gets the value of the verticalAlignment property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVerticalAlignment() {
        return verticalAlignment;
    }

    /**
     * Sets the value of the verticalAlignment property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVerticalAlignment(String value) {
        this.verticalAlignment = value;
    }

    /**
     * Gets the value of the ruleAbove property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRuleAbove() {
        return ruleAbove;
    }

    /**
     * Sets the value of the ruleAbove property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRuleAbove(String value) {
        this.ruleAbove = value;
    }

    /**
     * Gets the value of the ruleBelow property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRuleBelow() {
        return ruleBelow;
    }

    /**
     * Sets the value of the ruleBelow property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRuleBelow(String value) {
        this.ruleBelow = value;
    }

    /**
     * Gets the value of the cellRotation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCellRotation() {
        return cellRotation;
    }

    /**
     * Sets the value of the cellRotation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCellRotation(String value) {
        this.cellRotation = value;
    }

    /**
     * Gets the value of the processFlag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProcessFlag() {
        return processFlag;
    }

    /**
     * Sets the value of the processFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProcessFlag(String value) {
        this.processFlag = value;
    }

    /**
     * Gets the value of the cellVerticalStoryDirection property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCellVerticalStoryDirection() {
        return cellVerticalStoryDirection;
    }

    /**
     * Sets the value of the cellVerticalStoryDirection property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCellVerticalStoryDirection(String value) {
        this.cellVerticalStoryDirection = value;
    }

    /**
     * Gets the value of the cellImageScale property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCellImageScale() {
        return cellImageScale;
    }

    /**
     * Sets the value of the cellImageScale property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCellImageScale(String value) {
        this.cellImageScale = value;
    }

    /**
     * Gets the value of the keepWithNext property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isKeepWithNext() {
        return keepWithNext;
    }

    /**
     * Sets the value of the keepWithNext property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setKeepWithNext(Boolean value) {
        this.keepWithNext = value;
    }

    /**
     * Gets the value of the headingLevel property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getHeadingLevel() {
        return headingLevel;
    }

    /**
     * Sets the value of the headingLevel property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setHeadingLevel(BigInteger value) {
        this.headingLevel = value;
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
