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
 * <p>Java class for SingleResultLabTest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SingleResultLabTest">
 *   &lt;complexContent>
 *     &lt;extension base="{http://indivo.org/vocab/xml/documents#}LabTest">
 *       &lt;sequence>
 *         &lt;element name="result" type="{http://indivo.org/vocab/xml/documents#}Result"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SingleResultLabTest", propOrder = {
    "result"
})
public class XSingleResultLabTest
    extends XLabTest
{

    @XmlElement(required = true)
    protected XResult result;

    /**
     * Gets the value of the result property.
     * 
     * @return
     *     possible object is
     *     {@link XResult }
     *     
     */
    public XResult getResult() {
        return result;
    }

    /**
     * Sets the value of the result property.
     * 
     * @param value
     *     allowed object is
     *     {@link XResult }
     *     
     */
    public void setResult(XResult value) {
        this.result = value;
    }

}
