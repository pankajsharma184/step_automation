//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.04.16 at 01:19:41 PM IST 
//


package com.codifyd.stepxsd;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *                 The elements of the GUISetup, used to enable or disable menu items, workspaces and contexts for users and groups.
 *             
 * 
 * <p>Java class for GUISetupType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GUISetupType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;choice maxOccurs="unbounded" minOccurs="0">
 *           &lt;element name="GUISetupEnabled" type="{http://www.stibosystems.com/step}GUISetupEnabledType"/>
 *           &lt;element name="GUISetupDisabled" type="{http://www.stibosystems.com/step}GUISetupDisabledType"/>
 *           &lt;element name="GUISetupAdvanced" type="{http://www.stibosystems.com/step}GUISetupAdvancedType"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GUISetupType", propOrder = {
    "guiSetupEnabledOrGUISetupDisabledOrGUISetupAdvanced"
})
public class GUISetupType {

    @XmlElementRefs({
        @XmlElementRef(name = "GUISetupDisabled", namespace = "http://www.stibosystems.com/step", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "GUISetupAdvanced", namespace = "http://www.stibosystems.com/step", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "GUISetupEnabled", namespace = "http://www.stibosystems.com/step", type = JAXBElement.class, required = false)
    })
    protected List<JAXBElement<String>> guiSetupEnabledOrGUISetupDisabledOrGUISetupAdvanced;

    /**
     * Gets the value of the guiSetupEnabledOrGUISetupDisabledOrGUISetupAdvanced property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the guiSetupEnabledOrGUISetupDisabledOrGUISetupAdvanced property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGUISetupEnabledOrGUISetupDisabledOrGUISetupAdvanced().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * 
     */
    public List<JAXBElement<String>> getGUISetupEnabledOrGUISetupDisabledOrGUISetupAdvanced() {
        if (guiSetupEnabledOrGUISetupDisabledOrGUISetupAdvanced == null) {
            guiSetupEnabledOrGUISetupDisabledOrGUISetupAdvanced = new ArrayList<JAXBElement<String>>();
        }
        return this.guiSetupEnabledOrGUISetupDisabledOrGUISetupAdvanced;
    }

}