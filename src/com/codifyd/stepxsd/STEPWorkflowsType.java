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
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *                 A list of STEP Workflow configurations.
 *             
 * 
 * <p>Java class for STEPWorkflowsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="STEPWorkflowsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Stateflow" type="{http://www.stibosystems.com/step}STEPWorkflowType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="STEPWorkflow" type="{http://www.stibosystems.com/step}STEPWorkflowType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Selected" type="{http://www.stibosystems.com/step}SelectedType" default="true" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "STEPWorkflowsType", propOrder = {
    "stateflow",
    "stepWorkflow"
})
public class STEPWorkflowsType {

    @XmlElement(name = "Stateflow")
    protected List<STEPWorkflowType> stateflow;
    @XmlElement(name = "STEPWorkflow")
    protected List<STEPWorkflowType> stepWorkflow;
    @XmlAttribute(name = "Selected")
    protected Boolean selected;

    /**
     * Gets the value of the stateflow property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the stateflow property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStateflow().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link STEPWorkflowType }
     * 
     * 
     */
    public List<STEPWorkflowType> getStateflow() {
        if (stateflow == null) {
            stateflow = new ArrayList<STEPWorkflowType>();
        }
        return this.stateflow;
    }

    /**
     * Gets the value of the stepWorkflow property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the stepWorkflow property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSTEPWorkflow().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link STEPWorkflowType }
     * 
     * 
     */
    public List<STEPWorkflowType> getSTEPWorkflow() {
        if (stepWorkflow == null) {
            stepWorkflow = new ArrayList<STEPWorkflowType>();
        }
        return this.stepWorkflow;
    }

    /**
     * Gets the value of the selected property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isSelected() {
        if (selected == null) {
            return true;
        } else {
            return selected;
        }
    }

    /**
     * Sets the value of the selected property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSelected(Boolean value) {
        this.selected = value;
    }

}