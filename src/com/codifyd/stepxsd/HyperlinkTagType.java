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
 *                 Represents a hyperlink tag.
 *             
 * 
 * <p>Java class for HyperlinkTagType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="HyperlinkTagType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Configuration" type="{http://www.stibosystems.com/step}ConfigurationType" minOccurs="0"/>
 *         &lt;element name="TagFormat" type="{http://www.stibosystems.com/step}TagFormatType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="ID" use="required" type="{http://www.stibosystems.com/step}TagID" />
 *       &lt;attribute name="Tag" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="ParentID" type="{http://www.stibosystems.com/step}StepID" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HyperlinkTagType", propOrder = {
    "configuration",
    "tagFormat"
})
public class HyperlinkTagType {

    @XmlElement(name = "Configuration")
    protected byte[] configuration;
    @XmlElement(name = "TagFormat")
    protected List<TagFormatType> tagFormat;
    @XmlAttribute(name = "ID", required = true)
    protected String id;
    @XmlAttribute(name = "Tag", required = true)
    protected String tag;
    @XmlAttribute(name = "ParentID")
    protected String parentID;

    /**
     * Gets the value of the configuration property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getConfiguration() {
        return configuration;
    }

    /**
     * Sets the value of the configuration property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setConfiguration(byte[] value) {
        this.configuration = value;
    }

    /**
     * Gets the value of the tagFormat property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the tagFormat property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTagFormat().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TagFormatType }
     * 
     * 
     */
    public List<TagFormatType> getTagFormat() {
        if (tagFormat == null) {
            tagFormat = new ArrayList<TagFormatType>();
        }
        return this.tagFormat;
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
     * Gets the value of the tag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTag() {
        return tag;
    }

    /**
     * Sets the value of the tag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTag(String value) {
        this.tag = value;
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

}
