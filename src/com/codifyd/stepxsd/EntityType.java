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
 * <p>Java class for EntityType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EntityType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="KeyValue" type="{http://www.stibosystems.com/step}KeyValueType" minOccurs="0"/>
 *         &lt;element name="ParentKeyValue" type="{http://www.stibosystems.com/step}ParentKeyValueType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Name" type="{http://www.stibosystems.com/step}NameType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;choice maxOccurs="unbounded" minOccurs="0">
 *           &lt;element name="Entity" type="{http://www.stibosystems.com/step}EntityType"/>
 *           &lt;element name="SuppressedProductCrossReference" type="{http://www.stibosystems.com/step}SuppressedProductCrossReferenceType"/>
 *           &lt;element name="ProductCrossReference" type="{http://www.stibosystems.com/step}ProductCrossReferenceType"/>
 *           &lt;element name="SuppressedClassificationCrossReference" type="{http://www.stibosystems.com/step}SuppressedClassificationCrossReferenceType"/>
 *           &lt;element name="ClassificationCrossReference" type="{http://www.stibosystems.com/step}ClassificationCrossReferenceType"/>
 *           &lt;element name="SuppressedEntityCrossReference" type="{http://www.stibosystems.com/step}SuppressedEntityCrossReferenceType"/>
 *           &lt;element name="EntityCrossReference" type="{http://www.stibosystems.com/step}EntityCrossReferenceType"/>
 *           &lt;element name="SuppressedAssetCrossReference" type="{http://www.stibosystems.com/step}SuppressedAssetCrossReferenceType"/>
 *           &lt;element name="AssetCrossReference" type="{http://www.stibosystems.com/step}AssetCrossReferenceType"/>
 *           &lt;element name="SuppressedContextCrossReference" type="{http://www.stibosystems.com/step}SuppressedContextCrossReferenceType"/>
 *           &lt;element name="ContextCrossReference" type="{http://www.stibosystems.com/step}ContextCrossReferenceType"/>
 *           &lt;element name="SuppressedWorkspaceCrossReference" type="{http://www.stibosystems.com/step}SuppressedWorkspaceCrossReferenceType"/>
 *           &lt;element name="WorkspaceCrossReference" type="{http://www.stibosystems.com/step}WorkspaceCrossReferenceType"/>
 *           &lt;element name="Values" type="{http://www.stibosystems.com/step}ValuesType"/>
 *           &lt;element name="DataContainers" type="{http://www.stibosystems.com/step}DataContainersType"/>
 *           &lt;element name="CurrentTasks" type="{http://www.stibosystems.com/step}CurrentTasksType"/>
 *           &lt;element name="DeleteClassificationReference" type="{http://www.stibosystems.com/step}DeleteClassificationReferenceType" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;element name="DeleteProductCrossReference" type="{http://www.stibosystems.com/step}DeleteProductCrossReferenceType" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;element name="DeleteAssetCrossReference" type="{http://www.stibosystems.com/step}DeleteAssetCrossReferenceType" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;element name="DeleteEntityCrossReference" type="{http://www.stibosystems.com/step}DeleteEntityCrossReferenceType" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;element name="DeleteSuppressedClassificationCrossReference" type="{http://www.stibosystems.com/step}DeleteSuppressedClassificationCrossReferenceType" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;element name="DeleteSuppressedProductCrossReference" type="{http://www.stibosystems.com/step}DeleteSuppressedProductCrossReferenceType" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;element name="DeleteSuppressedEntityCrossReference" type="{http://www.stibosystems.com/step}DeleteSuppressedEntityCrossReferenceType" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;element name="DeleteSuppressedAssetCrossReference" type="{http://www.stibosystems.com/step}DeleteSuppressedAssetCrossReferenceType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *       &lt;attribute name="Changed" type="{http://www.stibosystems.com/step}TrueFalseType" default="false" />
 *       &lt;attribute name="SourcePos" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="AnalyzerResult" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="ParentID" type="{http://www.stibosystems.com/step}StepID" />
 *       &lt;attribute name="ID" type="{http://www.stibosystems.com/step}StepID" />
 *       &lt;attribute name="QualifierID" type="{http://www.stibosystems.com/step}StepQualifierID" />
 *       &lt;attribute name="Republished" type="{http://www.stibosystems.com/step}TrueFalseType" default="false" />
 *       &lt;attribute name="UserTypeID" type="{http://www.stibosystems.com/step}StepObjectTypeID" />
 *       &lt;attribute name="Selected" type="{http://www.stibosystems.com/step}SelectedType" default="true" />
 *       &lt;attribute name="Referenced" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EntityType", propOrder = {
    "keyValue",
    "parentKeyValue",
    "name",
    "entityOrSuppressedProductCrossReferenceOrProductCrossReference"
})
public class EntityType {

    @XmlElement(name = "KeyValue")
    protected KeyValueType keyValue;
    @XmlElement(name = "ParentKeyValue")
    protected List<ParentKeyValueType> parentKeyValue;
    @XmlElement(name = "Name")
    protected List<NameType> name;
    @XmlElements({
        @XmlElement(name = "Entity", type = EntityType.class),
        @XmlElement(name = "SuppressedProductCrossReference", type = SuppressedProductCrossReferenceType.class),
        @XmlElement(name = "ProductCrossReference", type = ProductCrossReferenceType.class),
        @XmlElement(name = "SuppressedClassificationCrossReference", type = SuppressedClassificationCrossReferenceType.class),
        @XmlElement(name = "ClassificationCrossReference", type = ClassificationCrossReferenceType.class),
        @XmlElement(name = "SuppressedEntityCrossReference", type = SuppressedEntityCrossReferenceType.class),
        @XmlElement(name = "EntityCrossReference", type = EntityCrossReferenceType.class),
        @XmlElement(name = "SuppressedAssetCrossReference", type = SuppressedAssetCrossReferenceType.class),
        @XmlElement(name = "AssetCrossReference", type = AssetCrossReferenceType.class),
        @XmlElement(name = "SuppressedContextCrossReference", type = SuppressedContextCrossReferenceType.class),
        @XmlElement(name = "ContextCrossReference", type = ContextCrossReferenceType.class),
        @XmlElement(name = "SuppressedWorkspaceCrossReference", type = SuppressedWorkspaceCrossReferenceType.class),
        @XmlElement(name = "WorkspaceCrossReference", type = WorkspaceCrossReferenceType.class),
        @XmlElement(name = "Values", type = ValuesType.class),
        @XmlElement(name = "DataContainers", type = DataContainersType.class),
        @XmlElement(name = "CurrentTasks", type = CurrentTasksType.class),
        @XmlElement(name = "DeleteClassificationReference", type = DeleteClassificationReferenceType.class),
        @XmlElement(name = "DeleteProductCrossReference", type = DeleteProductCrossReferenceType.class),
        @XmlElement(name = "DeleteAssetCrossReference", type = DeleteAssetCrossReferenceType.class),
        @XmlElement(name = "DeleteEntityCrossReference", type = DeleteEntityCrossReferenceType.class),
        @XmlElement(name = "DeleteSuppressedClassificationCrossReference", type = DeleteSuppressedClassificationCrossReferenceType.class),
        @XmlElement(name = "DeleteSuppressedProductCrossReference", type = DeleteSuppressedProductCrossReferenceType.class),
        @XmlElement(name = "DeleteSuppressedEntityCrossReference", type = DeleteSuppressedEntityCrossReferenceType.class),
        @XmlElement(name = "DeleteSuppressedAssetCrossReference", type = DeleteSuppressedAssetCrossReferenceType.class)
    })
    protected List<Object> entityOrSuppressedProductCrossReferenceOrProductCrossReference;
    @XmlAttribute(name = "Changed")
    protected TrueFalseType changed;
    @XmlAttribute(name = "SourcePos")
    protected String sourcePos;
    @XmlAttribute(name = "AnalyzerResult")
    protected String analyzerResult;
    @XmlAttribute(name = "ParentID")
    protected String parentID;
    @XmlAttribute(name = "ID")
    protected String id;
    @XmlAttribute(name = "QualifierID")
    protected String qualifierID;
    @XmlAttribute(name = "Republished")
    protected TrueFalseType republished;
    @XmlAttribute(name = "UserTypeID")
    protected String userTypeID;
    @XmlAttribute(name = "Selected")
    protected Boolean selected;
    @XmlAttribute(name = "Referenced")
    protected Boolean referenced;

    /**
     * Gets the value of the keyValue property.
     * 
     * @return
     *     possible object is
     *     {@link KeyValueType }
     *     
     */
    public KeyValueType getKeyValue() {
        return keyValue;
    }

    /**
     * Sets the value of the keyValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link KeyValueType }
     *     
     */
    public void setKeyValue(KeyValueType value) {
        this.keyValue = value;
    }

    /**
     * Gets the value of the parentKeyValue property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the parentKeyValue property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getParentKeyValue().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ParentKeyValueType }
     * 
     * 
     */
    public List<ParentKeyValueType> getParentKeyValue() {
        if (parentKeyValue == null) {
            parentKeyValue = new ArrayList<ParentKeyValueType>();
        }
        return this.parentKeyValue;
    }

    /**
     * Gets the value of the name property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the name property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getName().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link NameType }
     * 
     * 
     */
    public List<NameType> getName() {
        if (name == null) {
            name = new ArrayList<NameType>();
        }
        return this.name;
    }

    /**
     * Gets the value of the entityOrSuppressedProductCrossReferenceOrProductCrossReference property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the entityOrSuppressedProductCrossReferenceOrProductCrossReference property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEntityOrSuppressedProductCrossReferenceOrProductCrossReference().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EntityType }
     * {@link SuppressedProductCrossReferenceType }
     * {@link ProductCrossReferenceType }
     * {@link SuppressedClassificationCrossReferenceType }
     * {@link ClassificationCrossReferenceType }
     * {@link SuppressedEntityCrossReferenceType }
     * {@link EntityCrossReferenceType }
     * {@link SuppressedAssetCrossReferenceType }
     * {@link AssetCrossReferenceType }
     * {@link SuppressedContextCrossReferenceType }
     * {@link ContextCrossReferenceType }
     * {@link SuppressedWorkspaceCrossReferenceType }
     * {@link WorkspaceCrossReferenceType }
     * {@link ValuesType }
     * {@link DataContainersType }
     * {@link CurrentTasksType }
     * {@link DeleteClassificationReferenceType }
     * {@link DeleteProductCrossReferenceType }
     * {@link DeleteAssetCrossReferenceType }
     * {@link DeleteEntityCrossReferenceType }
     * {@link DeleteSuppressedClassificationCrossReferenceType }
     * {@link DeleteSuppressedProductCrossReferenceType }
     * {@link DeleteSuppressedEntityCrossReferenceType }
     * {@link DeleteSuppressedAssetCrossReferenceType }
     * 
     * 
     */
    public List<Object> getEntityOrSuppressedProductCrossReferenceOrProductCrossReference() {
        if (entityOrSuppressedProductCrossReferenceOrProductCrossReference == null) {
            entityOrSuppressedProductCrossReferenceOrProductCrossReference = new ArrayList<Object>();
        }
        return this.entityOrSuppressedProductCrossReferenceOrProductCrossReference;
    }

    /**
     * Gets the value of the changed property.
     * 
     * @return
     *     possible object is
     *     {@link TrueFalseType }
     *     
     */
    public TrueFalseType getChanged() {
        if (changed == null) {
            return TrueFalseType.FALSE;
        } else {
            return changed;
        }
    }

    /**
     * Sets the value of the changed property.
     * 
     * @param value
     *     allowed object is
     *     {@link TrueFalseType }
     *     
     */
    public void setChanged(TrueFalseType value) {
        this.changed = value;
    }

    /**
     * Gets the value of the sourcePos property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourcePos() {
        return sourcePos;
    }

    /**
     * Sets the value of the sourcePos property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourcePos(String value) {
        this.sourcePos = value;
    }

    /**
     * Gets the value of the analyzerResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAnalyzerResult() {
        return analyzerResult;
    }

    /**
     * Sets the value of the analyzerResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAnalyzerResult(String value) {
        this.analyzerResult = value;
    }

    /**
     * Gets the value of the parentID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParentID() {
        return parentID;
    }

    /**
     * Sets the value of the parentID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParentID(String value) {
        this.parentID = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getID() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setID(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the qualifierID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQualifierID() {
        return qualifierID;
    }

    /**
     * Sets the value of the qualifierID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQualifierID(String value) {
        this.qualifierID = value;
    }

    /**
     * Gets the value of the republished property.
     * 
     * @return
     *     possible object is
     *     {@link TrueFalseType }
     *     
     */
    public TrueFalseType getRepublished() {
        if (republished == null) {
            return TrueFalseType.FALSE;
        } else {
            return republished;
        }
    }

    /**
     * Sets the value of the republished property.
     * 
     * @param value
     *     allowed object is
     *     {@link TrueFalseType }
     *     
     */
    public void setRepublished(TrueFalseType value) {
        this.republished = value;
    }

    /**
     * Gets the value of the userTypeID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserTypeID() {
        return userTypeID;
    }

    /**
     * Sets the value of the userTypeID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserTypeID(String value) {
        this.userTypeID = value;
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

    /**
     * Gets the value of the referenced property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isReferenced() {
        if (referenced == null) {
            return false;
        } else {
            return referenced;
        }
    }

    /**
     * Sets the value of the referenced property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setReferenced(Boolean value) {
        this.referenced = value;
    }

}
