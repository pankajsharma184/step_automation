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
 * <p>Java class for WebSiteType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WebSiteType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Name" type="{http://www.stibosystems.com/step}NameType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="MetaData" type="{http://www.stibosystems.com/step}MetaDataType" minOccurs="0"/>
 *         &lt;element name="Configuration" type="{http://www.stibosystems.com/step}ConfigurationType" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="ID" use="required" type="{http://www.stibosystems.com/step}StepID" />
 *       &lt;attribute name="ParentWorkspaceID" type="{http://www.stibosystems.com/step}StepID" />
 *       &lt;attribute name="WebSiteState" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WebSiteType", propOrder = {
    "name",
    "metaData",
    "configuration"
})
public class WebSiteType {

    @XmlElement(name = "Name")
    protected List<NameType> name;
    @XmlElement(name = "MetaData")
    protected MetaDataType metaData;
    @XmlElement(name = "Configuration")
    protected byte[] configuration;
    @XmlAttribute(name = "ID", required = true)
    protected String id;
    @XmlAttribute(name = "ParentWorkspaceID")
    protected String parentWorkspaceID;
    @XmlAttribute(name = "WebSiteState")
    protected String webSiteState;

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
     * Gets the value of the parentWorkspaceID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParentWorkspaceID() {
        return parentWorkspaceID;
    }

    /**
     * Sets the value of the parentWorkspaceID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParentWorkspaceID(String value) {
        this.parentWorkspaceID = value;
    }

    /**
     * Gets the value of the webSiteState property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWebSiteState() {
        return webSiteState;
    }

    /**
     * Sets the value of the webSiteState property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWebSiteState(String value) {
        this.webSiteState = value;
    }

}
