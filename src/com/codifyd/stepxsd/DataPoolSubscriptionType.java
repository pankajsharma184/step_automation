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
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * 
 *                 A product subscription with a GDSN data pool
 *             
 * 
 * <p>Java class for DataPoolSubscriptionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DataPoolSubscriptionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Name" type="{http://www.stibosystems.com/step}NameType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="MetaData" type="{http://www.stibosystems.com/step}MetaDataType" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="ID" type="{http://www.stibosystems.com/step}StepID" />
 *       &lt;attribute name="GPC" type="{http://www.stibosystems.com/step}IDLength13" />
 *       &lt;attribute name="TargetMarket" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="GTIN" type="{http://www.stibosystems.com/step}IDLength14" />
 *       &lt;attribute name="ProviderGLN" type="{http://www.stibosystems.com/step}IDLength13" />
 *       &lt;attribute name="WorkspaceID" type="{http://www.stibosystems.com/step}StepID" />
 *       &lt;attribute name="ContextID" type="{http://www.stibosystems.com/step}StepQualifierID" />
 *       &lt;attribute name="DefaultUserTypeID" type="{http://www.stibosystems.com/step}StepObjectTypeID" />
 *       &lt;attribute name="DefaultProductRootID" type="{http://www.stibosystems.com/step}StepID" />
 *       &lt;attribute name="Status">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}NMTOKEN">
 *             &lt;enumeration value="Empty"/>
 *             &lt;enumeration value="ActivationPending"/>
 *             &lt;enumeration value="Active"/>
 *             &lt;enumeration value="DeactivationPending"/>
 *             &lt;enumeration value="Deactivated"/>
 *             &lt;enumeration value="Error"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="Selected" type="{http://www.stibosystems.com/step}SelectedType" default="true" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DataPoolSubscriptionType", propOrder = {
    "name",
    "metaData"
})
public class DataPoolSubscriptionType {

    @XmlElement(name = "Name")
    protected List<NameType> name;
    @XmlElement(name = "MetaData")
    protected MetaDataType metaData;
    @XmlAttribute(name = "ID")
    protected String id;
    @XmlAttribute(name = "GPC")
    protected String gpc;
    @XmlAttribute(name = "TargetMarket")
    protected String targetMarket;
    @XmlAttribute(name = "GTIN")
    protected String gtin;
    @XmlAttribute(name = "ProviderGLN")
    protected String providerGLN;
    @XmlAttribute(name = "WorkspaceID")
    protected String workspaceID;
    @XmlAttribute(name = "ContextID")
    protected String contextID;
    @XmlAttribute(name = "DefaultUserTypeID")
    protected String defaultUserTypeID;
    @XmlAttribute(name = "DefaultProductRootID")
    protected String defaultProductRootID;
    @XmlAttribute(name = "Status")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String status;
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
     * Gets the value of the gpc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGPC() {
        return gpc;
    }

    /**
     * Sets the value of the gpc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGPC(String value) {
        this.gpc = value;
    }

    /**
     * Gets the value of the targetMarket property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTargetMarket() {
        return targetMarket;
    }

    /**
     * Sets the value of the targetMarket property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTargetMarket(String value) {
        this.targetMarket = value;
    }

    /**
     * Gets the value of the gtin property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGTIN() {
        return gtin;
    }

    /**
     * Sets the value of the gtin property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGTIN(String value) {
        this.gtin = value;
    }

    /**
     * Gets the value of the providerGLN property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProviderGLN() {
        return providerGLN;
    }

    /**
     * Sets the value of the providerGLN property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProviderGLN(String value) {
        this.providerGLN = value;
    }

    /**
     * Gets the value of the workspaceID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWorkspaceID() {
        return workspaceID;
    }

    /**
     * Sets the value of the workspaceID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWorkspaceID(String value) {
        this.workspaceID = value;
    }

    /**
     * Gets the value of the contextID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContextID() {
        return contextID;
    }

    /**
     * Sets the value of the contextID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContextID(String value) {
        this.contextID = value;
    }

    /**
     * Gets the value of the defaultUserTypeID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDefaultUserTypeID() {
        return defaultUserTypeID;
    }

    /**
     * Sets the value of the defaultUserTypeID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDefaultUserTypeID(String value) {
        this.defaultUserTypeID = value;
    }

    /**
     * Gets the value of the defaultProductRootID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDefaultProductRootID() {
        return defaultProductRootID;
    }

    /**
     * Sets the value of the defaultProductRootID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDefaultProductRootID(String value) {
        this.defaultProductRootID = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
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
