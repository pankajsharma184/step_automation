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
 *                 A list of GDSN Data Pools
 *             
 * 
 * <p>Java class for DataPoolsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DataPoolsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DataPoolReceiver" type="{http://www.stibosystems.com/step}DataPoolReceiverType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="DataPoolPublisher" type="{http://www.stibosystems.com/step}DataPoolPublisherType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DataPoolsType", propOrder = {
    "dataPoolReceiver",
    "dataPoolPublisher"
})
public class DataPoolsType {

    @XmlElement(name = "DataPoolReceiver")
    protected List<DataPoolReceiverType> dataPoolReceiver;
    @XmlElement(name = "DataPoolPublisher")
    protected List<DataPoolPublisherType> dataPoolPublisher;

    /**
     * Gets the value of the dataPoolReceiver property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dataPoolReceiver property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDataPoolReceiver().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DataPoolReceiverType }
     * 
     * 
     */
    public List<DataPoolReceiverType> getDataPoolReceiver() {
        if (dataPoolReceiver == null) {
            dataPoolReceiver = new ArrayList<DataPoolReceiverType>();
        }
        return this.dataPoolReceiver;
    }

    /**
     * Gets the value of the dataPoolPublisher property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dataPoolPublisher property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDataPoolPublisher().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DataPoolPublisherType }
     * 
     * 
     */
    public List<DataPoolPublisherType> getDataPoolPublisher() {
        if (dataPoolPublisher == null) {
            dataPoolPublisher = new ArrayList<DataPoolPublisherType>();
        }
        return this.dataPoolPublisher;
    }

}
