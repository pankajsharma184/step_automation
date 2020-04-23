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
 * <p>Java class for PageType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PageType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Name" type="{http://www.stibosystems.com/step}NameType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="SuppressedClassificationReference" type="{http://www.stibosystems.com/step}SuppressedClassificationReferenceType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="ClassificationReference" type="{http://www.stibosystems.com/step}ClassificationReferenceType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="SuppressedProductCrossReference" type="{http://www.stibosystems.com/step}SuppressedProductCrossReferenceType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="ProductCrossReference" type="{http://www.stibosystems.com/step}ProductCrossReferenceType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="SuppressedAssetCrossReference" type="{http://www.stibosystems.com/step}SuppressedAssetCrossReferenceType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="AssetCrossReference" type="{http://www.stibosystems.com/step}AssetCrossReferenceType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Values" type="{http://www.stibosystems.com/step}ValuesType" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="ID" type="{http://www.stibosystems.com/step}StepID" />
 *       &lt;attribute name="VersionID" use="required" type="{http://www.stibosystems.com/step}StepID" />
 *       &lt;attribute name="PageNumber" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PageType", propOrder = {
    "name",
    "suppressedClassificationReference",
    "classificationReference",
    "suppressedProductCrossReference",
    "productCrossReference",
    "suppressedAssetCrossReference",
    "assetCrossReference",
    "values"
})
public class PageType {

    @XmlElement(name = "Name")
    protected List<NameType> name;
    @XmlElement(name = "SuppressedClassificationReference")
    protected List<SuppressedClassificationReferenceType> suppressedClassificationReference;
    @XmlElement(name = "ClassificationReference")
    protected List<ClassificationReferenceType> classificationReference;
    @XmlElement(name = "SuppressedProductCrossReference")
    protected List<SuppressedProductCrossReferenceType> suppressedProductCrossReference;
    @XmlElement(name = "ProductCrossReference")
    protected List<ProductCrossReferenceType> productCrossReference;
    @XmlElement(name = "SuppressedAssetCrossReference")
    protected List<SuppressedAssetCrossReferenceType> suppressedAssetCrossReference;
    @XmlElement(name = "AssetCrossReference")
    protected List<AssetCrossReferenceType> assetCrossReference;
    @XmlElement(name = "Values")
    protected ValuesType values;
    @XmlAttribute(name = "ID")
    protected String id;
    @XmlAttribute(name = "VersionID", required = true)
    protected String versionID;
    @XmlAttribute(name = "PageNumber")
    protected String pageNumber;

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
     * Gets the value of the suppressedClassificationReference property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the suppressedClassificationReference property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSuppressedClassificationReference().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SuppressedClassificationReferenceType }
     * 
     * 
     */
    public List<SuppressedClassificationReferenceType> getSuppressedClassificationReference() {
        if (suppressedClassificationReference == null) {
            suppressedClassificationReference = new ArrayList<SuppressedClassificationReferenceType>();
        }
        return this.suppressedClassificationReference;
    }

    /**
     * Gets the value of the classificationReference property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the classificationReference property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getClassificationReference().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ClassificationReferenceType }
     * 
     * 
     */
    public List<ClassificationReferenceType> getClassificationReference() {
        if (classificationReference == null) {
            classificationReference = new ArrayList<ClassificationReferenceType>();
        }
        return this.classificationReference;
    }

    /**
     * Gets the value of the suppressedProductCrossReference property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the suppressedProductCrossReference property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSuppressedProductCrossReference().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SuppressedProductCrossReferenceType }
     * 
     * 
     */
    public List<SuppressedProductCrossReferenceType> getSuppressedProductCrossReference() {
        if (suppressedProductCrossReference == null) {
            suppressedProductCrossReference = new ArrayList<SuppressedProductCrossReferenceType>();
        }
        return this.suppressedProductCrossReference;
    }

    /**
     * Gets the value of the productCrossReference property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the productCrossReference property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProductCrossReference().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ProductCrossReferenceType }
     * 
     * 
     */
    public List<ProductCrossReferenceType> getProductCrossReference() {
        if (productCrossReference == null) {
            productCrossReference = new ArrayList<ProductCrossReferenceType>();
        }
        return this.productCrossReference;
    }

    /**
     * Gets the value of the suppressedAssetCrossReference property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the suppressedAssetCrossReference property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSuppressedAssetCrossReference().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SuppressedAssetCrossReferenceType }
     * 
     * 
     */
    public List<SuppressedAssetCrossReferenceType> getSuppressedAssetCrossReference() {
        if (suppressedAssetCrossReference == null) {
            suppressedAssetCrossReference = new ArrayList<SuppressedAssetCrossReferenceType>();
        }
        return this.suppressedAssetCrossReference;
    }

    /**
     * Gets the value of the assetCrossReference property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the assetCrossReference property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAssetCrossReference().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AssetCrossReferenceType }
     * 
     * 
     */
    public List<AssetCrossReferenceType> getAssetCrossReference() {
        if (assetCrossReference == null) {
            assetCrossReference = new ArrayList<AssetCrossReferenceType>();
        }
        return this.assetCrossReference;
    }

    /**
     * Gets the value of the values property.
     * 
     * @return
     *     possible object is
     *     {@link ValuesType }
     *     
     */
    public ValuesType getValues() {
        return values;
    }

    /**
     * Sets the value of the values property.
     * 
     * @param value
     *     allowed object is
     *     {@link ValuesType }
     *     
     */
    public void setValues(ValuesType value) {
        this.values = value;
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
     * Gets the value of the versionID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersionID() {
        return versionID;
    }

    /**
     * Sets the value of the versionID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersionID(String value) {
        this.versionID = value;
    }

    /**
     * Gets the value of the pageNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPageNumber() {
        return pageNumber;
    }

    /**
     * Sets the value of the pageNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPageNumber(String value) {
        this.pageNumber = value;
    }

}
