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
 * 
 *                 Contains all AnalyzerItems.
 *             
 * 
 * <p>Java class for AnalyzerItemsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AnalyzerItemsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AnalyzerItem" type="{http://www.stibosystems.com/step}AnalyzerItemType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AnalyzerItemsType", propOrder = {
    "analyzerItem"
})
public class AnalyzerItemsType {

    @XmlElement(name = "AnalyzerItem")
    protected List<AnalyzerItemType> analyzerItem;

    /**
     * Gets the value of the analyzerItem property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the analyzerItem property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAnalyzerItem().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AnalyzerItemType }
     * 
     * 
     */
    public List<AnalyzerItemType> getAnalyzerItem() {
        if (analyzerItem == null) {
            analyzerItem = new ArrayList<AnalyzerItemType>();
        }
        return this.analyzerItem;
    }

}
