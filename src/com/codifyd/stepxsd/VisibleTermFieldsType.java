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
 * <p>Java class for VisibleTermFieldsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="VisibleTermFieldsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="VisibleTermField" type="{http://www.stibosystems.com/step}VisibleTermFieldType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VisibleTermFieldsType", propOrder = {
    "visibleTermField"
})
public class VisibleTermFieldsType {

    @XmlElement(name = "VisibleTermField")
    protected List<VisibleTermFieldType> visibleTermField;

    /**
     * Gets the value of the visibleTermField property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the visibleTermField property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getVisibleTermField().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link VisibleTermFieldType }
     * 
     * 
     */
    public List<VisibleTermFieldType> getVisibleTermField() {
        if (visibleTermField == null) {
            visibleTermField = new ArrayList<VisibleTermFieldType>();
        }
        return this.visibleTermField;
    }

}
