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
 * <p>Java class for PublicationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PublicationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Name" type="{http://www.stibosystems.com/step}NameType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="MetaData" type="{http://www.stibosystems.com/step}MetaDataType" minOccurs="0"/>
 *         &lt;element name="Version" type="{http://www.stibosystems.com/step}VersionType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Basket" type="{http://www.stibosystems.com/step}BasketType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="PaginationRules" type="{http://www.stibosystems.com/step}ConfigurationType" minOccurs="0"/>
 *         &lt;element name="PageTemplateLink" type="{http://www.stibosystems.com/step}PageTemplateLinkType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="ProductTemplateLink" type="{http://www.stibosystems.com/step}ProductTemplateLinkType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="PublicationSection" type="{http://www.stibosystems.com/step}PublicationSectionType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="SuppressedEntityCrossReference" type="{http://www.stibosystems.com/step}SuppressedEntityCrossReferenceType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="EntityCrossReference" type="{http://www.stibosystems.com/step}EntityCrossReferenceType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="ID" type="{http://www.stibosystems.com/step}StepID" />
 *       &lt;attribute name="UserTypeID" type="{http://www.stibosystems.com/step}StepObjectTypeID" />
 *       &lt;attribute name="MasterDocumentID" use="required" type="{http://www.stibosystems.com/step}StepID" />
 *       &lt;attribute name="PagesPerSpread" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="DefaultPageTemplateID" type="{http://www.stibosystems.com/step}StepID" />
 *       &lt;attribute name="DTPType" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}NMTOKEN">
 *             &lt;enumeration value="InDesign"/>
 *             &lt;enumeration value="Quark"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="ParentID" type="{http://www.stibosystems.com/step}StepID" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PublicationType", propOrder = {
    "name",
    "metaData",
    "version",
    "basket",
    "paginationRules",
    "pageTemplateLink",
    "productTemplateLink",
    "publicationSection",
    "suppressedEntityCrossReference",
    "entityCrossReference"
})
public class PublicationType {

    @XmlElement(name = "Name")
    protected List<NameType> name;
    @XmlElement(name = "MetaData")
    protected MetaDataType metaData;
    @XmlElement(name = "Version")
    protected List<VersionType> version;
    @XmlElement(name = "Basket")
    protected List<BasketType> basket;
    @XmlElement(name = "PaginationRules")
    protected byte[] paginationRules;
    @XmlElement(name = "PageTemplateLink")
    protected List<PageTemplateLinkType> pageTemplateLink;
    @XmlElement(name = "ProductTemplateLink")
    protected List<ProductTemplateLinkType> productTemplateLink;
    @XmlElement(name = "PublicationSection")
    protected List<PublicationSectionType> publicationSection;
    @XmlElement(name = "SuppressedEntityCrossReference")
    protected List<SuppressedEntityCrossReferenceType> suppressedEntityCrossReference;
    @XmlElement(name = "EntityCrossReference")
    protected List<EntityCrossReferenceType> entityCrossReference;
    @XmlAttribute(name = "ID")
    protected String id;
    @XmlAttribute(name = "UserTypeID")
    protected String userTypeID;
    @XmlAttribute(name = "MasterDocumentID", required = true)
    protected String masterDocumentID;
    @XmlAttribute(name = "PagesPerSpread")
    protected String pagesPerSpread;
    @XmlAttribute(name = "DefaultPageTemplateID")
    protected String defaultPageTemplateID;
    @XmlAttribute(name = "DTPType", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String dtpType;
    @XmlAttribute(name = "ParentID")
    protected String parentID;

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
     * Gets the value of the version property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the version property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getVersion().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link VersionType }
     * 
     * 
     */
    public List<VersionType> getVersion() {
        if (version == null) {
            version = new ArrayList<VersionType>();
        }
        return this.version;
    }

    /**
     * Gets the value of the basket property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the basket property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBasket().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BasketType }
     * 
     * 
     */
    public List<BasketType> getBasket() {
        if (basket == null) {
            basket = new ArrayList<BasketType>();
        }
        return this.basket;
    }

    /**
     * Gets the value of the paginationRules property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getPaginationRules() {
        return paginationRules;
    }

    /**
     * Sets the value of the paginationRules property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setPaginationRules(byte[] value) {
        this.paginationRules = value;
    }

    /**
     * Gets the value of the pageTemplateLink property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the pageTemplateLink property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPageTemplateLink().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PageTemplateLinkType }
     * 
     * 
     */
    public List<PageTemplateLinkType> getPageTemplateLink() {
        if (pageTemplateLink == null) {
            pageTemplateLink = new ArrayList<PageTemplateLinkType>();
        }
        return this.pageTemplateLink;
    }

    /**
     * Gets the value of the productTemplateLink property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the productTemplateLink property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProductTemplateLink().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ProductTemplateLinkType }
     * 
     * 
     */
    public List<ProductTemplateLinkType> getProductTemplateLink() {
        if (productTemplateLink == null) {
            productTemplateLink = new ArrayList<ProductTemplateLinkType>();
        }
        return this.productTemplateLink;
    }

    /**
     * Gets the value of the publicationSection property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the publicationSection property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPublicationSection().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PublicationSectionType }
     * 
     * 
     */
    public List<PublicationSectionType> getPublicationSection() {
        if (publicationSection == null) {
            publicationSection = new ArrayList<PublicationSectionType>();
        }
        return this.publicationSection;
    }

    /**
     * Gets the value of the suppressedEntityCrossReference property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the suppressedEntityCrossReference property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSuppressedEntityCrossReference().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SuppressedEntityCrossReferenceType }
     * 
     * 
     */
    public List<SuppressedEntityCrossReferenceType> getSuppressedEntityCrossReference() {
        if (suppressedEntityCrossReference == null) {
            suppressedEntityCrossReference = new ArrayList<SuppressedEntityCrossReferenceType>();
        }
        return this.suppressedEntityCrossReference;
    }

    /**
     * Gets the value of the entityCrossReference property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the entityCrossReference property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEntityCrossReference().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EntityCrossReferenceType }
     * 
     * 
     */
    public List<EntityCrossReferenceType> getEntityCrossReference() {
        if (entityCrossReference == null) {
            entityCrossReference = new ArrayList<EntityCrossReferenceType>();
        }
        return this.entityCrossReference;
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
     * Gets the value of the userTypeID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserTypeID() {
        return userTypeID;
    }

    /**
     * Sets the value of the userTypeID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserTypeID(String value) {
        this.userTypeID = value;
    }

    /**
     * Gets the value of the masterDocumentID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMasterDocumentID() {
        return masterDocumentID;
    }

    /**
     * Sets the value of the masterDocumentID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMasterDocumentID(String value) {
        this.masterDocumentID = value;
    }

    /**
     * Gets the value of the pagesPerSpread property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPagesPerSpread() {
        return pagesPerSpread;
    }

    /**
     * Sets the value of the pagesPerSpread property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPagesPerSpread(String value) {
        this.pagesPerSpread = value;
    }

    /**
     * Gets the value of the defaultPageTemplateID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDefaultPageTemplateID() {
        return defaultPageTemplateID;
    }

    /**
     * Sets the value of the defaultPageTemplateID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDefaultPageTemplateID(String value) {
        this.defaultPageTemplateID = value;
    }

    /**
     * Gets the value of the dtpType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDTPType() {
        return dtpType;
    }

    /**
     * Sets the value of the dtpType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDTPType(String value) {
        this.dtpType = value;
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
