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


/**
 * 
 *                 A business function dependency of a Business Rule.
 *             
 * 
 * <p>Java class for BusinessFunctionDependencyType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BusinessFunctionDependencyType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="FunctionID" use="required" type="{http://www.stibosystems.com/step}StepBusinessRuleID" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BusinessFunctionDependencyType")
public class BusinessFunctionDependencyType {

    @XmlAttribute(name = "FunctionID", required = true)
    protected String functionID;

    /**
     * Gets the value of the functionID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFunctionID() {
        return functionID;
    }

    /**
     * Sets the value of the functionID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFunctionID(String value) {
        this.functionID = value;
    }

}
