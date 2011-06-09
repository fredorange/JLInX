/**
 *     Copyright  (c) 2010        France Telecom / Orange Labs
 *
 *     This file is part of JLInX, Java Lib for Indivo X.
 *
 *     JLInX is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU Lesser General Public License as published by
 *     the Free Software Foundation, version 3 (LGPLv3).
 *
 *     JLInX is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with JLInX.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package com.orange.jlinx.document.medical;

import com.orange.jlinx.document.Document;
import com.orange.jlinx.document.DocumentType;
import com.orange.jlinx.document.XDocumentFactory;
import com.orange.jlinx.document.ext.CodedValue;
import com.orange.jlinx.document.ext.ValueAndUnit;
import com.orange.jlinx.document.x.XMedication;
import java.util.Date;
import javax.xml.bind.JAXBElement;

/**
 *
 * @author fzql9967
 */
public class Medication extends Document {

  private Date dateStarted;
  private Date dateStopped;
  private String reasonStopped;
  private CodedValue name;
  private CodedValue brandName;
  private ValueAndUnit dose;
  private CodedValue route;
  private ValueAndUnit strength;
  private CodedValue frequency;
  private Prescription prescription;
  private String details;
  /**
   * A DocumentType identifying the Indivo document type handled by this
   * class.
   */
  public static DocumentType DOCUMENT_TYPE = new DocumentType(
          "Medication",
          "http://indivo.org/vocab/xml/documents#Medication",
          "medications",
          Medication.class);

  @Override
  public JAXBElement<?> getXmlElement() {
    XMedication xMedication = XDocumentFactory.getXMedication(this);
    JAXBElement<XMedication> result = new JAXBElement<XMedication>(
            this.getQName(),
            XMedication.class,
            xMedication);

    return result;
  }

  public Medication(Date dateStarted, Date dateStopped, String reasonStopped, CodedValue name, CodedValue brandName, ValueAndUnit dose, CodedValue route, ValueAndUnit strength, CodedValue frequency, Prescription prescription, String details) {
    super(DOCUMENT_TYPE);

    this.dateStarted = dateStarted;
    this.dateStopped = dateStopped;
    this.reasonStopped = reasonStopped;
    this.name = name;
    this.brandName = brandName;
    this.dose = dose;
    this.route = route;
    this.strength = strength;
    this.frequency = frequency;
    this.prescription = prescription;
    this.details = details;
  }

  /**
   * @return the dateStarted
   */
  public Date getDateStarted() {
    return dateStarted;
  }

  /**
   * @param dateStarted the dateStarted to set
   */
  public void setDateStarted(Date dateStarted) {
    this.dateStarted = dateStarted;
  }

  /**
   * @return the dateStopped
   */
  public Date getDateStopped() {
    return dateStopped;
  }

  /**
   * @param dateStopped the dateStopped to set
   */
  public void setDateStopped(Date dateStopped) {
    this.dateStopped = dateStopped;
  }

  /**
   * @return the reasonStopped
   */
  public String getReasonStopped() {
    return reasonStopped;
  }

  /**
   * @param reasonStopped the reasonStopped to set
   */
  public void setReasonStopped(String reasonStopped) {
    this.reasonStopped = reasonStopped;
  }

  /**
   * @return the name
   */
  public CodedValue getName() {
    return name;
  }

  /**
   * @param name the name to set
   */
  public void setName(CodedValue name) {
    this.name = name;
  }

  /**
   * @return the brandName
   */
  public CodedValue getBrandName() {
    return brandName;
  }

  /**
   * @param brandName the brandName to set
   */
  public void setBrandName(CodedValue brandName) {
    this.brandName = brandName;
  }

  /**
   * @return the dose
   */
  public ValueAndUnit getDose() {
    return dose;
  }

  /**
   * @param dose the dose to set
   */
  public void setDose(ValueAndUnit dose) {
    this.dose = dose;
  }

  /**
   * @return the route
   */
  public CodedValue getRoute() {
    return route;
  }

  /**
   * @param route the route to set
   */
  public void setRoute(CodedValue route) {
    this.route = route;
  }

  /**
   * @return the strength
   */
  public ValueAndUnit getStrength() {
    return strength;
  }

  /**
   * @param strength the strength to set
   */
  public void setStrength(ValueAndUnit strength) {
    this.strength = strength;
  }

  /**
   * @return the frequency
   */
  public CodedValue getFrequency() {
    return frequency;
  }

  /**
   * @param frequency the frequency to set
   */
  public void setFrequency(CodedValue frequency) {
    this.frequency = frequency;
  }

  /**
   * @return the prescription
   */
  public Prescription getPrescription() {
    return prescription;
  }

  /**
   * @param prescription the prescription to set
   */
  public void setPrescription(Prescription prescription) {
    this.prescription = prescription;
  }

  /**
   * @return the details
   */
  public String getDetails() {
    return details;
  }

  /**
   * @param details the details to set
   */
  public void setDetails(String details) {
    this.details = details;
  }


  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("Medication [");
    if (getDateStarted() != null) {
      builder.append("dateStarted=");
      builder.append(getDateStarted());
    }
    if (getDateStopped() != null) {
      builder.append(", ");
      builder.append("dateStopped=");
      builder.append(getDateStopped());
    }
    if (getReasonStopped() != null) {
      builder.append(", ");
      builder.append("reasonStopped=");
      builder.append(getReasonStopped());
    }
    if (getName() != null) {
      builder.append(", ");
      builder.append("name=");
      builder.append(getName().toString());
    }
    if (getBrandName() != null) {
      builder.append(", ");
      builder.append("brandName=");
      builder.append(getBrandName().toString());
    }
    if (getDose() != null) {
      builder.append(", ");
      builder.append("dose=");
      builder.append(getDose().toString());
    }
    if (getRoute() != null) {
      builder.append(", ");
      builder.append("route=");
      builder.append(getRoute().toString());
    }
    if (getStrength() != null) {
      builder.append(", ");
      builder.append("strength=");
      builder.append(getStrength().toString());
    }
    if (getFrequency() != null) {
      builder.append(", ");
      builder.append("frequency=");
      builder.append(getFrequency().toString());
    }
    if (getPrescription() != null) {
      builder.append(", ");
      builder.append("prescription=");
      builder.append(getPrescription().toString());
    }
    if (getDetails() != null) {
      builder.append(", ");
      builder.append("details=");
      builder.append(getDetails());
    }
    builder.append("]");
    return builder.toString();
  }
}
