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
import javax.xml.bind.annotation.XmlValue;


/**
 * 
 *                 Represents additional information calculated by the AnalyzerPlugin.
 *             
 * 
 * <p>Java class for AnalyzerPropType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AnalyzerPropType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="PropID" use="required" type="{http://www.stibosystems.com/step}StepID" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AnalyzerPropType", propOrder = {
    "content"
})
public class AnalyzerPropType {

    @XmlValue
    protected String content;
    @XmlAttribute(name = "PropID", required = true)
    protected String propID;

    /**
     * 
     *                 Represents additional information calculated by the AnalyzerPlugin.
     *             
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the value of the content property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContent(String value) {
        this.content = value;
    }

    /**
     * Gets the value of the propID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPropID() {
        return propID;
    }

    /**
     * Sets the value of the propID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPropID(String value) {
        this.propID = value;
    }

}
