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
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for Medication complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Medication">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="dateStarted" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="dateStopped" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="reasonStopped" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="name" type="{http://indivo.org/vocab/xml/documents#}CodedValue"/>
 *         &lt;element name="brandName" type="{http://indivo.org/vocab/xml/documents#}CodedValue" minOccurs="0"/>
 *         &lt;element name="dose" type="{http://indivo.org/vocab/xml/documents#}ValueAndUnit"/>
 *         &lt;element name="route" type="{http://indivo.org/vocab/xml/documents#}CodedValue" minOccurs="0"/>
 *         &lt;element name="strength" type="{http://indivo.org/vocab/xml/documents#}ValueAndUnit" minOccurs="0"/>
 *         &lt;element name="frequency" type="{http://indivo.org/vocab/xml/documents#}CodedValue"/>
 *         &lt;element name="prescription" type="{http://indivo.org/vocab/xml/documents#}Prescription" minOccurs="0"/>
 *         &lt;element name="details" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Medication", propOrder = {
    "dateStarted",
    "dateStopped",
    "reasonStopped",
    "name",
    "brandName",
    "dose",
    "route",
    "strength",
    "frequency",
    "prescription",
    "details"
})
public class XMedication {

    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(Adapter3 .class)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dateStarted;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(Adapter3 .class)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dateStopped;
    protected String reasonStopped;
    @XmlElement(required = true)
    protected XCodedValue name;
    protected XCodedValue brandName;
    @XmlElement(required = true)
    protected XValueAndUnit dose;
    protected XCodedValue route;
    protected XValueAndUnit strength;
    @XmlElement(required = true)
    protected XCodedValue frequency;
    protected XPrescription prescription;
    protected String details;

    /**
     * Gets the value of the dateStarted property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public XMLGregorianCalendar getDateStarted() {
        return dateStarted;
    }

    /**
     * Sets the value of the dateStarted property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDateStarted(XMLGregorianCalendar value) {
        this.dateStarted = value;
    }

    /**
     * Gets the value of the dateStopped property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public XMLGregorianCalendar getDateStopped() {
        return dateStopped;
    }

    /**
     * Sets the value of the dateStopped property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDateStopped(XMLGregorianCalendar value) {
        this.dateStopped = value;
    }

    /**
     * Gets the value of the reasonStopped property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReasonStopped() {
        return reasonStopped;
    }

    /**
     * Sets the value of the reasonStopped property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReasonStopped(String value) {
        this.reasonStopped = value;
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
     * Gets the value of the brandName property.
     * 
     * @return
     *     possible object is
     *     {@link XCodedValue }
     *     
     */
    public XCodedValue getBrandName() {
        return brandName;
    }

    /**
     * Sets the value of the brandName property.
     * 
     * @param value
     *     allowed object is
     *     {@link XCodedValue }
     *     
     */
    public void setBrandName(XCodedValue value) {
        this.brandName = value;
    }

    /**
     * Gets the value of the dose property.
     * 
     * @return
     *     possible object is
     *     {@link XValueAndUnit }
     *     
     */
    public XValueAndUnit getDose() {
        return dose;
    }

    /**
     * Sets the value of the dose property.
     * 
     * @param value
     *     allowed object is
     *     {@link XValueAndUnit }
     *     
     */
    public void setDose(XValueAndUnit value) {
        this.dose = value;
    }

    /**
     * Gets the value of the route property.
     * 
     * @return
     *     possible object is
     *     {@link XCodedValue }
     *     
     */
    public XCodedValue getRoute() {
        return route;
    }

    /**
     * Sets the value of the route property.
     * 
     * @param value
     *     allowed object is
     *     {@link XCodedValue }
     *     
     */
    public void setRoute(XCodedValue value) {
        this.route = value;
    }

    /**
     * Gets the value of the strength property.
     * 
     * @return
     *     possible object is
     *     {@link XValueAndUnit }
     *     
     */
    public XValueAndUnit getStrength() {
        return strength;
    }

    /**
     * Sets the value of the strength property.
     * 
     * @param value
     *     allowed object is
     *     {@link XValueAndUnit }
     *     
     */
    public void setStrength(XValueAndUnit value) {
        this.strength = value;
    }

    /**
     * Gets the value of the frequency property.
     * 
     * @return
     *     possible object is
     *     {@link XCodedValue }
     *     
     */
    public XCodedValue getFrequency() {
        return frequency;
    }

    /**
     * Sets the value of the frequency property.
     * 
     * @param value
     *     allowed object is
     *     {@link XCodedValue }
     *     
     */
    public void setFrequency(XCodedValue value) {
        this.frequency = value;
    }

    /**
     * Gets the value of the prescription property.
     * 
     * @return
     *     possible object is
     *     {@link XPrescription }
     *     
     */
    public XPrescription getPrescription() {
        return prescription;
    }

    /**
     * Sets the value of the prescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link XPrescription }
     *     
     */
    public void setPrescription(XPrescription value) {
        this.prescription = value;
    }

    /**
     * Gets the value of the details property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDetails() {
        return details;
    }

    /**
     * Sets the value of the details property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDetails(String value) {
        this.details = value;
    }

}
