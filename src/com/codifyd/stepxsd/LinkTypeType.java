//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.04.16 at 01:19:41 PM IST 
//


package com.codifyd.stepxsd;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * 
 *                 Links an attribute to a link type of which links are allowed to have values for this attribute.
 *             
 * 
 * <p>Java class for LinkTypeType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LinkTypeType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="LinkTypeID" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}NMTOKEN">
 *             &lt;enumeration value="CA"/>
 *             &lt;enumeration value="PA"/>
 *             &lt;enumeration value="CP"/>
 *             &lt;enumeration value="C1A"/>
 *             &lt;enumeration value="C1P"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LinkTypeType")
public class LinkTypeType {

    @XmlAttribute(name = "LinkTypeID", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String linkTypeID;

    /**
     * Gets the value of the linkTypeID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLinkTypeID() {
        return linkTypeID;
    }

    /**
     * Sets the value of the linkTypeID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLinkTypeID(String value) {
        this.linkTypeID = value;
    }

}
