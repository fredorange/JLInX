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
 * <p>Java class for Immunization complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Immunization">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="dateAdministered" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="administeredBy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="vaccine">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="type" type="{http://indivo.org/vocab/xml/documents#}CodedValue"/>
 *                   &lt;element name="manufacturer" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="lot" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="expiration" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="sequence" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="anatomicSurface" type="{http://indivo.org/vocab/xml/documents#}CodedValue" minOccurs="0"/>
 *         &lt;element name="adverseEvent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Immunization", propOrder = {
    "dateAdministered",
    "administeredBy",
    "vaccine",
    "sequence",
    "anatomicSurface",
    "adverseEvent"
})
public class XImmunization {

    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dateAdministered;
    protected String administeredBy;
    @XmlElement(required = true)
    protected XImmunization.Vaccine vaccine;
    @XmlElement(required = true, type = String.class)
    @XmlJavaTypeAdapter(Adapter2 .class)
    @XmlSchemaType(name = "integer")
    protected Integer sequence;
    protected XCodedValue anatomicSurface;
    protected String adverseEvent;

    /**
     * Gets the value of the dateAdministered property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public XMLGregorianCalendar getDateAdministered() {
        return dateAdministered;
    }

    /**
     * Sets the value of the dateAdministered property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDateAdministered(XMLGregorianCalendar value) {
        this.dateAdministered = value;
    }

    /**
     * Gets the value of the administeredBy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdministeredBy() {
        return administeredBy;
    }

    /**
     * Sets the value of the administeredBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdministeredBy(String value) {
        this.administeredBy = value;
    }

    /**
     * Gets the value of the vaccine property.
     * 
     * @return
     *     possible object is
     *     {@link XImmunization.Vaccine }
     *     
     */
    public XImmunization.Vaccine getVaccine() {
        return vaccine;
    }

    /**
     * Sets the value of the vaccine property.
     * 
     * @param value
     *     allowed object is
     *     {@link XImmunization.Vaccine }
     *     
     */
    public void setVaccine(XImmunization.Vaccine value) {
        this.vaccine = value;
    }

    /**
     * Gets the value of the sequence property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Integer getSequence() {
        return sequence;
    }

    /**
     * Sets the value of the sequence property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSequence(Integer value) {
        this.sequence = value;
    }

    /**
     * Gets the value of the anatomicSurface property.
     * 
     * @return
     *     possible object is
     *     {@link XCodedValue }
     *     
     */
    public XCodedValue getAnatomicSurface() {
        return anatomicSurface;
    }

    /**
     * Sets the value of the anatomicSurface property.
     * 
     * @param value
     *     allowed object is
     *     {@link XCodedValue }
     *     
     */
    public void setAnatomicSurface(XCodedValue value) {
        this.anatomicSurface = value;
    }

    /**
     * Gets the value of the adverseEvent property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdverseEvent() {
        return adverseEvent;
    }

    /**
     * Sets the value of the adverseEvent property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdverseEvent(String value) {
        this.adverseEvent = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="type" type="{http://indivo.org/vocab/xml/documents#}CodedValue"/>
     *         &lt;element name="manufacturer" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="lot" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="expiration" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "type",
        "manufacturer",
        "lot",
        "expiration"
    })
    public static class Vaccine {

        @XmlElement(required = true)
        protected XCodedValue type;
        protected String manufacturer;
        protected String lot;
        @XmlElement(type = String.class)
        @XmlJavaTypeAdapter(Adapter3 .class)
        @XmlSchemaType(name = "date")
        protected XMLGregorianCalendar expiration;

        /**
         * Gets the value of the type property.
         * 
         * @return
         *     possible object is
         *     {@link XCodedValue }
         *     
         */
        public XCodedValue getType() {
            return type;
        }

        /**
         * Sets the value of the type property.
         * 
         * @param value
         *     allowed object is
         *     {@link XCodedValue }
         *     
         */
        public void setType(XCodedValue value) {
            this.type = value;
        }

        /**
         * Gets the value of the manufacturer property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getManufacturer() {
            return manufacturer;
        }

        /**
         * Sets the value of the manufacturer property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setManufacturer(String value) {
            this.manufacturer = value;
        }

        /**
         * Gets the value of the lot property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getLot() {
            return lot;
        }

        /**
         * Sets the value of the lot property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setLot(String value) {
            this.lot = value;
        }

        /**
         * Gets the value of the expiration property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public XMLGregorianCalendar getExpiration() {
            return expiration;
        }

        /**
         * Sets the value of the expiration property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setExpiration(XMLGregorianCalendar value) {
            this.expiration = value;
        }

    }

}
