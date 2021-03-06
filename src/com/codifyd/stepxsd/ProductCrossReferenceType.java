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
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *                 A product to product cross-reference.
 *             
 * 
 * <p>Java class for ProductCrossReferenceType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ProductCrossReferenceType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="KeyValue" type="{http://www.stibosystems.com/step}KeyValueType" minOccurs="0"/>
 *         &lt;choice maxOccurs="unbounded" minOccurs="0">
 *           &lt;element name="MetaData" type="{http://www.stibosystems.com/step}MetaDataType" minOccurs="0"/>
 *           &lt;element name="Product" type="{http://www.stibosystems.com/step}ProductType" minOccurs="0"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *       &lt;attribute name="Type" use="required" type="{http://www.stibosystems.com/step}StepLinkTypeID" />
 *       &lt;attribute name="ProductID" type="{http://www.stibosystems.com/step}StepID" />
 *       &lt;attribute name="MountLocation" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="AnalyzerResult" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Changed" type="{http://www.stibosystems.com/step}TrueFalseType" default="false" />
 *       &lt;attribute name="QualifierID" type="{http://www.stibosystems.com/step}StepQualifierID" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProductCrossReferenceType", propOrder = {
    "keyValue",
    "metaDataOrProduct"
})
public class ProductCrossReferenceType {

    @XmlElement(name = "KeyValue")
    protected KeyValueType keyValue;
    @XmlElements({
        @XmlElement(name = "MetaData", type = MetaDataType.class),
        @XmlElement(name = "Product", type = ProductType.class)
    })
    protected List<Object> metaDataOrProduct;
    @XmlAttribute(name = "Type", required = true)
    protected String type;
    @XmlAttribute(name = "ProductID")
    protected String productID;
    @XmlAttribute(name = "MountLocation")
    protected String mountLocation;
    @XmlAttribute(name = "AnalyzerResult")
    protected String analyzerResult;
    @XmlAttribute(name = "Changed")
    protected TrueFalseType changed;
    @XmlAttribute(name = "QualifierID")
    protected String qualifierID;

    /**
     * Gets the value of the keyValue property.
     * 
     * @return
     *     possible object is
     *     {@link KeyValueType }
     *     
     */
    public KeyValueType getKeyValue() {
        return keyValue;
    }

    /**
     * Sets the value of the keyValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link KeyValueType }
     *     
     */
    public void setKeyValue(KeyValueType value) {
        this.keyValue = value;
    }

    /**
     * Gets the value of the metaDataOrProduct property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the metaDataOrProduct property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMetaDataOrProduct().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MetaDataType }
     * {@link ProductType }
     * 
     * 
     */
    public List<Object> getMetaDataOrProduct() {
        if (metaDataOrProduct == null) {
            metaDataOrProduct = new ArrayList<Object>();
        }
        return this.metaDataOrProduct;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * Gets the value of the productID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductID() {
        return productID;
    }

    /**
     * Sets the value of the productID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductID(String value) {
        this.productID = value;
    }

    /**
     * Gets the value of the mountLocation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMountLocation() {
        return mountLocation;
    }

    /**
     * Sets the value of the mountLocation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMountLocation(String value) {
        this.mountLocation = value;
    }

    /**
     * Gets the value of the analyzerResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAnalyzerResult() {
        return analyzerResult;
    }

    /**
     * Sets the value of the analyzerResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAnalyzerResult(String value) {
        this.analyzerResult = value;
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

}
