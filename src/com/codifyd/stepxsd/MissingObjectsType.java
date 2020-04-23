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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MissingObjectsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MissingObjectsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Classifications" type="{http://www.stibosystems.com/step}ClassificationsType"/>
 *         &lt;element name="Products" type="{http://www.stibosystems.com/step}ProductsType"/>
 *         &lt;element name="Assets" type="{http://www.stibosystems.com/step}AssetsType"/>
 *         &lt;element name="PublicationGroups" type="{http://www.stibosystems.com/step}PublicationGroupsType"/>
 *         &lt;element name="Publication" type="{http://www.stibosystems.com/step}PublicationType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="PlannedPage" type="{http://www.stibosystems.com/step}PlannedPageType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MissingObjectsType", propOrder = {
    "classifications",
    "products",
    "assets",
    "publicationGroups",
    "publication",
    "plannedPage"
})
public class MissingObjectsType {

    @XmlElement(name = "Classifications", required = true)
    protected ClassificationsType classifications;
    @XmlElement(name = "Products", required = true)
    protected ProductsType products;
    @XmlElement(name = "Assets", required = true)
    protected AssetsType assets;
    @XmlElement(name = "PublicationGroups", required = true)
    protected PublicationGroupsType publicationGroups;
    @XmlElement(name = "Publication")
    protected List<PublicationType> publication;
    @XmlElement(name = "PlannedPage")
    protected List<PlannedPageType> plannedPage;

    /**
     * Gets the value of the classifications property.
     * 
     * @return
     *     possible object is
     *     {@link ClassificationsType }
     *     
     */
    public ClassificationsType getClassifications() {
        return classifications;
    }

    /**
     * Sets the value of the classifications property.
     * 
     * @param value
     *     allowed object is
     *     {@link ClassificationsType }
     *     
     */
    public void setClassifications(ClassificationsType value) {
        this.classifications = value;
    }

    /**
     * Gets the value of the products property.
     * 
     * @return
     *     possible object is
     *     {@link ProductsType }
     *     
     */
    public ProductsType getProducts() {
        return products;
    }

    /**
     * Sets the value of the products property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProductsType }
     *     
     */
    public void setProducts(ProductsType value) {
        this.products = value;
    }

    /**
     * Gets the value of the assets property.
     * 
     * @return
     *     possible object is
     *     {@link AssetsType }
     *     
     */
    public AssetsType getAssets() {
        return assets;
    }

    /**
     * Sets the value of the assets property.
     * 
     * @param value
     *     allowed object is
     *     {@link AssetsType }
     *     
     */
    public void setAssets(AssetsType value) {
        this.assets = value;
    }

    /**
     * Gets the value of the publicationGroups property.
     * 
     * @return
     *     possible object is
     *     {@link PublicationGroupsType }
     *     
     */
    public PublicationGroupsType getPublicationGroups() {
        return publicationGroups;
    }

    /**
     * Sets the value of the publicationGroups property.
     * 
     * @param value
     *     allowed object is
     *     {@link PublicationGroupsType }
     *     
     */
    public void setPublicationGroups(PublicationGroupsType value) {
        this.publicationGroups = value;
    }

    /**
     * Gets the value of the publication property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the publication property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPublication().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PublicationType }
     * 
     * 
     */
    public List<PublicationType> getPublication() {
        if (publication == null) {
            publication = new ArrayList<PublicationType>();
        }
        return this.publication;
    }

    /**
     * Gets the value of the plannedPage property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the plannedPage property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPlannedPage().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PlannedPageType }
     * 
     * 
     */
    public List<PlannedPageType> getPlannedPage() {
        if (plannedPage == null) {
            plannedPage = new ArrayList<PlannedPageType>();
        }
        return this.plannedPage;
    }

}
