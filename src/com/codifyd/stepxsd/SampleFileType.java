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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SampleFileType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SampleFileType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SampleFileContents" type="{http://www.stibosystems.com/step}ConfigurationType"/>
 *       &lt;/sequence>
 *       &lt;attribute name="SampleFileName" use="required" type="{http://www.stibosystems.com/step}NonEmptyStringType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SampleFileType", propOrder = {
    "sampleFileContents"
})
public class SampleFileType {

    @XmlElement(name = "SampleFileContents", required = true)
    protected byte[] sampleFileContents;
    @XmlAttribute(name = "SampleFileName", required = true)
    protected String sampleFileName;

    /**
     * Gets the value of the sampleFileContents property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getSampleFileContents() {
        return sampleFileContents;
    }

    /**
     * Sets the value of the sampleFileContents property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setSampleFileContents(byte[] value) {
        this.sampleFileContents = value;
    }

    /**
     * Gets the value of the sampleFileName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSampleFileName() {
        return sampleFileName;
    }

    /**
     * Sets the value of the sampleFileName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSampleFileName(String value) {
        this.sampleFileName = value;
    }

}
