//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.04.16 at 01:19:41 PM IST 
//


package com.codifyd.stepxsd;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TableDefinitionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TableDefinitionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TableXML" type="{http://www.stibosystems.com/step}TableConfigurationType"/>
 *         &lt;element name="TableFreeTextXML" type="{http://www.stibosystems.com/step}TableConfigurationType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TableDefinitionType", propOrder = {
    "tableXML",
    "tableFreeTextXML"
})
public class TableDefinitionType {

    @XmlElement(name = "TableXML", required = true)
    protected TableConfigurationType tableXML;
    @XmlElement(name = "TableFreeTextXML")
    protected TableConfigurationType tableFreeTextXML;

    /**
     * Gets the value of the tableXML property.
     * 
     * @return
     *     possible object is
     *     {@link TableConfigurationType }
     *     
     */
    public TableConfigurationType getTableXML() {
        return tableXML;
    }

    /**
     * Sets the value of the tableXML property.
     * 
     * @param value
     *     allowed object is
     *     {@link TableConfigurationType }
     *     
     */
    public void setTableXML(TableConfigurationType value) {
        this.tableXML = value;
    }

    /**
     * Gets the value of the tableFreeTextXML property.
     * 
     * @return
     *     possible object is
     *     {@link TableConfigurationType }
     *     
     */
    public TableConfigurationType getTableFreeTextXML() {
        return tableFreeTextXML;
    }

    /**
     * Sets the value of the tableFreeTextXML property.
     * 
     * @param value
     *     allowed object is
     *     {@link TableConfigurationType }
     *     
     */
    public void setTableFreeTextXML(TableConfigurationType value) {
        this.tableFreeTextXML = value;
    }

}