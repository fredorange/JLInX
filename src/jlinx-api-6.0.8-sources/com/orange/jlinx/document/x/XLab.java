//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.2-hudson-jaxb-ri-2.2-63- 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.06.01 at 03:34:16 PM CEST 
//


package com.orange.jlinx.document.x;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;


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
 *         &lt;element name="dateMeasured" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="labType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="laboratory" type="{http://indivo.org/vocab/xml/documents#}LabProvider" minOccurs="0"/>
 *         &lt;element name="labPanel" type="{http://indivo.org/vocab/xml/documents#}LabPanel" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="labTest" type="{http://indivo.org/vocab/xml/documents#}LabTest" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="comments" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "dateMeasured",
    "labType",
    "laboratory",
    "labPanel",
    "labTest",
    "comments"
})
@XmlRootElement(name = "Lab")
public class XLab {

    @XmlElement(required = true, type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dateMeasured;
    @XmlElement(required = true)
    protected String labType;
    protected XLabProvider laboratory;
    protected List<XLabPanel> labPanel;
    protected List<XLabTest> labTest;
    protected String comments;

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
     * Gets the value of the labType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLabType() {
        return labType;
    }

    /**
     * Sets the value of the labType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLabType(String value) {
        this.labType = value;
    }

    /**
     * Gets the value of the laboratory property.
     * 
     * @return
     *     possible object is
     *     {@link XLabProvider }
     *     
     */
    public XLabProvider getLaboratory() {
        return laboratory;
    }

    /**
     * Sets the value of the laboratory property.
     * 
     * @param value
     *     allowed object is
     *     {@link XLabProvider }
     *     
     */
    public void setLaboratory(XLabProvider value) {
        this.laboratory = value;
    }

    /**
     * Gets the value of the labPanel property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the labPanel property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLabPanel().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link XLabPanel }
     * 
     * 
     */
    public List<XLabPanel> getLabPanel() {
        if (labPanel == null) {
            labPanel = new ArrayList<XLabPanel>();
        }
        return this.labPanel;
    }

    /**
     * Gets the value of the labTest property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the labTest property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLabTest().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link XLabTest }
     * 
     * 
     */
    public List<XLabTest> getLabTest() {
        if (labTest == null) {
            labTest = new ArrayList<XLabTest>();
        }
        return this.labTest;
    }

    /**
     * Gets the value of the comments property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComments() {
        return comments;
    }

    /**
     * Sets the value of the comments property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComments(String value) {
        this.comments = value;
    }

}
