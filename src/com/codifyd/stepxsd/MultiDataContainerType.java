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
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *                 A multi valued DataContainer holds a set of DataContainers of the same type, each identified by an ID.
 *             
 * 
 * <p>Java class for MultiDataContainerType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MultiDataContainerType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice maxOccurs="unbounded" minOccurs="0">
 *         &lt;element name="DataContainer" type="{http://www.stibosystems.com/step}DataContainerType"/>
 *         &lt;element name="DeleteDataContainer" type="{http://www.stibosystems.com/step}DeleteDataContainerType"/>
 *       &lt;/choice>
 *       &lt;attribute name="Type" type="{http://www.stibosystems.com/step}StepDataContainerTypeID" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MultiDataContainerType", propOrder = {
    "dataContainerOrDeleteDataContainer"
})
public class MultiDataContainerType {

    @XmlElements({
        @XmlElement(name = "DataContainer", type = DataContainerType.class),
        @XmlElement(name = "DeleteDataContainer", type = DeleteDataContainerType.class)
    })
    protected List<Object> dataContainerOrDeleteDataContainer;
    @XmlAttribute(name = "Type")
    protected String type;

    /**
     * Gets the value of the dataContainerOrDeleteDataContainer property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dataContainerOrDeleteDataContainer property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDataContainerOrDeleteDataContainer().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DataContainerType }
     * {@link DeleteDataContainerType }
     * 
     * 
     */
    public List<Object> getDataContainerOrDeleteDataContainer() {
        if (dataContainerOrDeleteDataContainer == null) {
            dataContainerOrDeleteDataContainer = new ArrayList<Object>();
        }
        return this.dataContainerOrDeleteDataContainer;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

}
