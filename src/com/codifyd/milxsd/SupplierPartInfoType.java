//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.04.16 at 01:22:30 PM IST 
//


package com.codifyd.milxsd;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SupplierPartInfoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SupplierPartInfoType">
 *   &lt;complexContent>
 *     &lt;extension base="{api.codifyd.com/interchange}PartInfoType">
 *       &lt;attribute name="Id" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="IsDistributor" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SupplierPartInfoType")
@XmlSeeAlso({
    Supplier.class
})
public class SupplierPartInfoType
    extends PartInfoType
{

    @XmlAttribute(name = "Id")
    protected String id;
    @XmlAttribute(name = "IsDistributor", required = true)
    protected boolean isDistributor;

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
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
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the isDistributor property.
     * 
     */
    public boolean isIsDistributor() {
        return isDistributor;
    }

    /**
     * Sets the value of the isDistributor property.
     * 
     */
    public void setIsDistributor(boolean value) {
        this.isDistributor = value;
    }

}
