//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.2-hudson-jaxb-ri-2.2-63- 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.06.01 at 03:34:16 PM CEST 
//


package com.orange.jlinx.document.x;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for LabTest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LabTest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="dateMeasured" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="name" type="{http://indivo.org/vocab/xml/documents#}CodedValue"/>
 *         &lt;element name="final" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LabTest", propOrder = {
    "dateMeasured",
    "name",
    "_final"
})
@XmlSeeAlso({
    XSingleResultLabTest.class,
    XMicroWithCultureLabTest.class
})
public class XLabTest {

    @XmlElement(required = true, type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dateMeasured;
    @XmlElement(required = true)
    protected XCodedValue name;
    @XmlElement(name = "final")
    protected boolean _final;

    /**
     * Gets the value of the dateMeasured property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public XMLGregorianCalendar getDateMeasured() {
        return dateMeasured;
    }

    /**
     * Sets the value of the dateMeasured property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDateMeasured(XMLGregorianCalendar value) {
        this.dateMeasured = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link XCodedValue }
     *     
     */
    public XCodedValue getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link XCodedValue }
     *     
     */
    public void setName(XCodedValue value) {
        this.name = value;
    }

    /**
     * Gets the value of the final property.
     * 
     */
    public boolean isFinal() {
        return _final;
    }

    /**
     * Sets the value of the final property.
     * 
     */
    public void setFinal(boolean value) {
        this._final = value;
    }

}
