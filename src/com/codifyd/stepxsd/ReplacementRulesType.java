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
 * <p>Java class for ReplacementRulesType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ReplacementRulesType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ReplaceProductValuesForAttributeGroup" type="{http://www.stibosystems.com/step}ReplaceProductValuesForAttributeGroupType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="ReplaceProductToProductCrossReference" type="{http://www.stibosystems.com/step}ReplaceProductToProductCrossReferenceType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="ReplaceProductToAssetCrossReference" type="{http://www.stibosystems.com/step}ReplaceProductToAssetCrossReferenceType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="ReplaceClassificationReference" type="{http://www.stibosystems.com/step}ReplaceClassificationReferenceType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="ReplaceCrossReference" type="{http://www.stibosystems.com/step}ReplaceCrossReferenceType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="ReplaceDataContainer" type="{http://www.stibosystems.com/step}ReplaceDataContainerTypeType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReplacementRulesType", propOrder = {
    "replaceProductValuesForAttributeGroup",
    "replaceProductToProductCrossReference",
    "replaceProductToAssetCrossReference",
    "replaceClassificationReference",
    "replaceCrossReference",
    "replaceDataContainer"
})
public class ReplacementRulesType {

    @XmlElement(name = "ReplaceProductValuesForAttributeGroup")
    protected List<ReplaceProductValuesForAttributeGroupType> replaceProductValuesForAttributeGroup;
    @XmlElement(name = "ReplaceProductToProductCrossReference")
    protected List<ReplaceProductToProductCrossReferenceType> replaceProductToProductCrossReference;
    @XmlElement(name = "ReplaceProductToAssetCrossReference")
    protected List<ReplaceProductToAssetCrossReferenceType> replaceProductToAssetCrossReference;
    @XmlElement(name = "ReplaceClassificationReference")
    protected List<ReplaceClassificationReferenceType> replaceClassificationReference;
    @XmlElement(name = "ReplaceCrossReference")
    protected List<ReplaceCrossReferenceType> replaceCrossReference;
    @XmlElement(name = "ReplaceDataContainer")
    protected List<ReplaceDataContainerTypeType> replaceDataContainer;

    /**
     * Gets the value of the replaceProductValuesForAttributeGroup property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the replaceProductValuesForAttributeGroup property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReplaceProductValuesForAttributeGroup().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ReplaceProductValuesForAttributeGroupType }
     * 
     * 
     */
    public List<ReplaceProductValuesForAttributeGroupType> getReplaceProductValuesForAttributeGroup() {
        if (replaceProductValuesForAttributeGroup == null) {
            replaceProductValuesForAttributeGroup = new ArrayList<ReplaceProductValuesForAttributeGroupType>();
        }
        return this.replaceProductValuesForAttributeGroup;
    }

    /**
     * Gets the value of the replaceProductToProductCrossReference property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the replaceProductToProductCrossReference property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReplaceProductToProductCrossReference().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ReplaceProductToProductCrossReferenceType }
     * 
     * 
     */
    public List<ReplaceProductToProductCrossReferenceType> getReplaceProductToProductCrossReference() {
        if (replaceProductToProductCrossReference == null) {
            replaceProductToProductCrossReference = new ArrayList<ReplaceProductToProductCrossReferenceType>();
        }
        return this.replaceProductToProductCrossReference;
    }

    /**
     * Gets the value of the replaceProductToAssetCrossReference property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the replaceProductToAssetCrossReference property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReplaceProductToAssetCrossReference().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ReplaceProductToAssetCrossReferenceType }
     * 
     * 
     */
    public List<ReplaceProductToAssetCrossReferenceType> getReplaceProductToAssetCrossReference() {
        if (replaceProductToAssetCrossReference == null) {
            replaceProductToAssetCrossReference = new ArrayList<ReplaceProductToAssetCrossReferenceType>();
        }
        return this.replaceProductToAssetCrossReference;
    }

    /**
     * Gets the value of the replaceClassificationReference property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the replaceClassificationReference property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReplaceClassificationReference().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ReplaceClassificationReferenceType }
     * 
     * 
     */
    public List<ReplaceClassificationReferenceType> getReplaceClassificationReference() {
        if (replaceClassificationReference == null) {
            replaceClassificationReference = new ArrayList<ReplaceClassificationReferenceType>();
        }
        return this.replaceClassificationReference;
    }

    /**
     * Gets the value of the replaceCrossReference property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the replaceCrossReference property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReplaceCrossReference().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ReplaceCrossReferenceType }
     * 
     * 
     */
    public List<ReplaceCrossReferenceType> getReplaceCrossReference() {
        if (replaceCrossReference == null) {
            replaceCrossReference = new ArrayList<ReplaceCrossReferenceType>();
        }
        return this.replaceCrossReference;
    }

    /**
     * Gets the value of the replaceDataContainer property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the replaceDataContainer property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReplaceDataContainer().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ReplaceDataContainerTypeType }
     * 
     * 
     */
    public List<ReplaceDataContainerTypeType> getReplaceDataContainer() {
        if (replaceDataContainer == null) {
            replaceDataContainer = new ArrayList<ReplaceDataContainerTypeType>();
        }
        return this.replaceDataContainer;
    }

}
