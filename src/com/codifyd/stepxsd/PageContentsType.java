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
 * <p>Java class for PageContentsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PageContentsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice maxOccurs="unbounded" minOccurs="0">
 *         &lt;element name="Frame" type="{http://www.stibosystems.com/step}FrameType"/>
 *         &lt;element name="NoteFrame" type="{http://www.stibosystems.com/step}NoteFrameType"/>
 *         &lt;element name="GalleyFrame" type="{http://www.stibosystems.com/step}GalleyFrameType"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PageContentsType", propOrder = {
    "frameOrNoteFrameOrGalleyFrame"
})
public class PageContentsType {

    @XmlElements({
        @XmlElement(name = "Frame", type = FrameType.class),
        @XmlElement(name = "NoteFrame", type = NoteFrameType.class),
        @XmlElement(name = "GalleyFrame", type = GalleyFrameType.class)
    })
    protected List<Object> frameOrNoteFrameOrGalleyFrame;

    /**
     * Gets the value of the frameOrNoteFrameOrGalleyFrame property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the frameOrNoteFrameOrGalleyFrame property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFrameOrNoteFrameOrGalleyFrame().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FrameType }
     * {@link NoteFrameType }
     * {@link GalleyFrameType }
     * 
     * 
     */
    public List<Object> getFrameOrNoteFrameOrGalleyFrame() {
        if (frameOrNoteFrameOrGalleyFrame == null) {
            frameOrNoteFrameOrGalleyFrame = new ArrayList<Object>();
        }
        return this.frameOrNoteFrameOrGalleyFrame;
    }

}
