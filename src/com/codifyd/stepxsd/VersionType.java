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
 * <p>Java class for VersionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="VersionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Name" type="{http://www.stibosystems.com/step}NameType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="MetaData" type="{http://www.stibosystems.com/step}MetaDataType" minOccurs="0"/>
 *         &lt;element name="TermsListLink" type="{http://www.stibosystems.com/step}TermsListLinkType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="LayerMapping" type="{http://www.stibosystems.com/step}LayerMappingType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="ID" type="{http://www.stibosystems.com/step}StepID" />
 *       &lt;attribute name="ContextID" use="required" type="{http://www.stibosystems.com/step}StepQualifierID" />
 *       &lt;attribute name="IsMaster" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="WorkspaceID" use="required" type="{http://www.stibosystems.com/step}StepID" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VersionType", propOrder = {
    "name",
    "metaData",
    "termsListLink",
    "layerMapping"
})
public class VersionType {

    @XmlElement(name = "Name")
    protected List<NameType> name;
    @XmlElement(name = "MetaData")
    protected MetaDataType metaData;
    @XmlElement(name = "TermsListLink")
    protected List<TermsListLinkType> termsListLink;
    @XmlElement(name = "LayerMapping")
    protected List<LayerMappingType> layerMapping;
    @XmlAttribute(name = "ID")
    protected String id;
    @XmlAttribute(name = "ContextID", required = true)
    protected String contextID;
    @XmlAttribute(name = "IsMaster")
    protected String isMaster;
    @XmlAttribute(name = "WorkspaceID", required = true)
    protected String workspaceID;

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
     * Gets the value of the termsListLink property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the termsListLink property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTermsListLink().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TermsListLinkType }
     * 
     * 
     */
    public List<TermsListLinkType> getTermsListLink() {
        if (termsListLink == null) {
            termsListLink = new ArrayList<TermsListLinkType>();
        }
        return this.termsListLink;
    }

    /**
     * Gets the value of the layerMapping property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the layerMapping property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLayerMapping().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LayerMappingType }
     * 
     * 
     */
    public List<LayerMappingType> getLayerMapping() {
        if (layerMapping == null) {
            layerMapping = new ArrayList<LayerMappingType>();
        }
        return this.layerMapping;
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
     * Gets the value of the isMaster property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsMaster() {
        return isMaster;
    }

    /**
     * Sets the value of the isMaster property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsMaster(String value) {
        this.isMaster = value;
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

}
