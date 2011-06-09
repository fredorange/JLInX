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
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AnyMedical complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AnyMedical">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="VitalSign" type="{http://indivo.org/vocab/xml/documents#}VitalSign"/>
 *         &lt;element name="Equipment" type="{http://indivo.org/vocab/xml/documents#}Equipment"/>
 *         &lt;element name="Allergy" type="{http://indivo.org/vocab/xml/documents#}Allergy"/>
 *         &lt;element name="Immunization" type="{http://indivo.org/vocab/xml/documents#}Immunization"/>
 *         &lt;element name="Procedure" type="{http://indivo.org/vocab/xml/documents#}Procedure"/>
 *         &lt;element name="Problem" type="{http://indivo.org/vocab/xml/documents#}Problem"/>
 *         &lt;element name="Medication" type="{http://indivo.org/vocab/xml/documents#}Medication"/>
 *         &lt;element name="Prescription" type="{http://indivo.org/vocab/xml/documents#}Prescription"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AnyMedical", propOrder = {
    "vitalSign",
    "equipment",
    "allergy",
    "immunization",
    "procedure",
    "problem",
    "medication",
    "prescription"
})
public class XAnyMedical {

    @XmlElement(name = "VitalSign")
    protected XVitalSign vitalSign;
    @XmlElement(name = "Equipment")
    protected XEquipment equipment;
    @XmlElement(name = "Allergy")
    protected XAllergy allergy;
    @XmlElement(name = "Immunization")
    protected XImmunization immunization;
    @XmlElement(name = "Procedure")
    protected XProcedure procedure;
    @XmlElement(name = "Problem")
    protected XProblem problem;
    @XmlElement(name = "Medication")
    protected XMedication medication;
    @XmlElement(name = "Prescription")
    protected XPrescription prescription;

    /**
     * Gets the value of the vitalSign property.
     * 
     * @return
     *     possible object is
     *     {@link XVitalSign }
     *     
     */
    public XVitalSign getVitalSign() {
        return vitalSign;
    }

    /**
     * Sets the value of the vitalSign property.
     * 
     * @param value
     *     allowed object is
     *     {@link XVitalSign }
     *     
     */
    public void setVitalSign(XVitalSign value) {
        this.vitalSign = value;
    }

    /**
     * Gets the value of the equipment property.
     * 
     * @return
     *     possible object is
     *     {@link XEquipment }
     *     
     */
    public XEquipment getEquipment() {
        return equipment;
    }

    /**
     * Sets the value of the equipment property.
     * 
     * @param value
     *     allowed object is
     *     {@link XEquipment }
     *     
     */
    public void setEquipment(XEquipment value) {
        this.equipment = value;
    }

    /**
     * Gets the value of the allergy property.
     * 
     * @return
     *     possible object is
     *     {@link XAllergy }
     *     
     */
    public XAllergy getAllergy() {
        return allergy;
    }

    /**
     * Sets the value of the allergy property.
     * 
     * @param value
     *     allowed object is
     *     {@link XAllergy }
     *     
     */
    public void setAllergy(XAllergy value) {
        this.allergy = value;
    }

    /**
     * Gets the value of the immunization property.
     * 
     * @return
     *     possible object is
     *     {@link XImmunization }
     *     
     */
    public XImmunization getImmunization() {
        return immunization;
    }

    /**
     * Sets the value of the immunization property.
     * 
     * @param value
     *     allowed object is
     *     {@link XImmunization }
     *     
     */
    public void setImmunization(XImmunization value) {
        this.immunization = value;
    }

    /**
     * Gets the value of the procedure property.
     * 
     * @return
     *     possible object is
     *     {@link XProcedure }
     *     
     */
    public XProcedure getProcedure() {
        return procedure;
    }

    /**
     * Sets the value of the procedure property.
     * 
     * @param value
     *     allowed object is
     *     {@link XProcedure }
     *     
     */
    public void setProcedure(XProcedure value) {
        this.procedure = value;
    }

    /**
     * Gets the value of the problem property.
     * 
     * @return
     *     possible object is
     *     {@link XProblem }
     *     
     */
    public XProblem getProblem() {
        return problem;
    }

    /**
     * Sets the value of the problem property.
     * 
     * @param value
     *     allowed object is
     *     {@link XProblem }
     *     
     */
    public void setProblem(XProblem value) {
        this.problem = value;
    }

    /**
     * Gets the value of the medication property.
     * 
     * @return
     *     possible object is
     *     {@link XMedication }
     *     
     */
    public XMedication getMedication() {
        return medication;
    }

    /**
     * Sets the value of the medication property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMedication }
     *     
     */
    public void setMedication(XMedication value) {
        this.medication = value;
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

}