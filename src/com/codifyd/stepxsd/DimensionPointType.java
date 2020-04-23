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
 *                 Represent a dimension point. The list of DimensionPointLink is links to its parent dimension points.
 *             
 * 
 * <p>Java class for DimensionPointType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DimensionPointType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Name" type="{http://www.stibosystems.com/step}NameType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="MetaData" type="{http://www.stibosystems.com/step}MetaDataType" minOccurs="0"/>
 *         &lt;element name="DimensionPointLink" type="{http://www.stibosystems.com/step}DimensionPointLinkType" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="ID" use="required" type="{http://www.stibosystems.com/step}StepQualifierID" />
 *       &lt;attribute name="GDSNLanguageID" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="DictionaryID" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Selected" type="{http://www.stibosystems.com/step}SelectedType" default="true" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DimensionPointType", propOrder = {
    "name",
    "metaData",
    "dimensionPointLink"
})
public class DimensionPointType {

    @XmlElement(name = "Name")
    protected List<NameType> name;
    @XmlElement(name = "MetaData")
    protected MetaDataType metaData;
    @XmlElement(name = "DimensionPointLink")
    protected DimensionPointLinkType dimensionPointLink;
    @XmlAttribute(name = "ID", required = true)
    protected String id;
    @XmlAttribute(name = "GDSNLanguageID")
    protected String gdsnLanguageID;
    @XmlAttribute(name = "DictionaryID")
    protected String dictionaryID;
    @XmlAttribute(name = "Selected")
    protected Boolean selected;

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
     * Gets the value of the dimensionPointLink property.
     * 
     * @return
     *     possible object is
     *     {@link DimensionPointLinkType }
     *     
     */
    public DimensionPointLinkType getDimensionPointLink() {
        return dimensionPointLink;
    }

    /**
     * Sets the value of the dimensionPointLink property.
     * 
     * @param value
     *     allowed object is
     *     {@link DimensionPointLinkType }
     *     
     */
    public void setDimensionPointLink(DimensionPointLinkType value) {
        this.dimensionPointLink = value;
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
     * Gets the value of the gdsnLanguageID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGDSNLanguageID() {
        return gdsnLanguageID;
    }

    /**
     * Sets the value of the gdsnLanguageID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGDSNLanguageID(String value) {
        this.gdsnLanguageID = value;
    }

    /**
     * Gets the value of the dictionaryID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDictionaryID() {
        return dictionaryID;
    }

    /**
     * Sets the value of the dictionaryID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDictionaryID(String value) {
        this.dictionaryID = value;
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

}
