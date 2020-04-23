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
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *                 A set of Data Containers.
 *             
 * 
 * <p>Java class for DataContainersType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DataContainersType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice maxOccurs="unbounded" minOccurs="0">
 *         &lt;element name="DataContainer" type="{http://www.stibosystems.com/step}SingleDataContainerType"/>
 *         &lt;element name="MultiDataContainer" type="{http://www.stibosystems.com/step}MultiDataContainerType"/>
 *         &lt;element name="DeleteDataContainer" type="{http://www.stibosystems.com/step}DeleteSingleDataContainerType"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DataContainersType", propOrder = {
    "dataContainerOrMultiDataContainerOrDeleteDataContainer"
})
public class DataContainersType {

    @XmlElements({
        @XmlElement(name = "DataContainer", type = SingleDataContainerType.class),
        @XmlElement(name = "MultiDataContainer", type = MultiDataContainerType.class),
        @XmlElement(name = "DeleteDataContainer", type = DeleteSingleDataContainerType.class)
    })
    protected List<Object> dataContainerOrMultiDataContainerOrDeleteDataContainer;

    /**
     * Gets the value of the dataContainerOrMultiDataContainerOrDeleteDataContainer property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dataContainerOrMultiDataContainerOrDeleteDataContainer property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDataContainerOrMultiDataContainerOrDeleteDataContainer().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SingleDataContainerType }
     * {@link MultiDataContainerType }
     * {@link DeleteSingleDataContainerType }
     * 
     * 
     */
    public List<Object> getDataContainerOrMultiDataContainerOrDeleteDataContainer() {
        if (dataContainerOrMultiDataContainerOrDeleteDataContainer == null) {
            dataContainerOrMultiDataContainerOrDeleteDataContainer = new ArrayList<Object>();
        }
        return this.dataContainerOrMultiDataContainerOrDeleteDataContainer;
    }

}
