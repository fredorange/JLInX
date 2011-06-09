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
import com.orange.jlinx.document.Provider;
import com.orange.jlinx.document.XDocumentFactory;
import com.orange.jlinx.document.x.XPrescription;
import java.util.Date;
import javax.xml.bind.JAXBElement;

/**
 *
 * @author fzql9967
 */
public class Prescription extends Document {

  private Provider by;
  private Date on;
  private Date stopOn;
  private Boolean dispenseAsWritten;
  /**
   * Duration of the prescription (expressed in milliseconds)
   */
  private Long duration;
  private String refillInfo;
  private String instructions;
  /**
   * A DocumentType identifying the Indivo document type handled by this
   * class.
   */
  public static DocumentType DOCUMENT_TYPE = new DocumentType(
          "Prescription",
          "http://indivo.org/vocab/xml/documents#Prescription",
          "prescriptions",
          Prescription.class);

  @Override
  public JAXBElement<?> getXmlElement() {
    XPrescription xPrescription = XDocumentFactory.getXPrescription(this);
    JAXBElement<XPrescription> result = new JAXBElement<XPrescription>(
            this.getQName(),
            XPrescription.class,
            xPrescription);

    return result;
  }

  public Prescription(Provider by, Date on, Date stopOn, boolean dispenseAsWritten, long duration, String refillInfo, String instructions) {
    super(DOCUMENT_TYPE);

    this.by = by;
    this.on = on;
    this.stopOn = stopOn;
    this.dispenseAsWritten = dispenseAsWritten;
    this.duration = duration;
    this.refillInfo = refillInfo;
    this.instructions = instructions;
  }


  /**
   * @return the by
   */
  public Provider getBy() {
    return by;
  }

  /**
   * @param by the by to set
   */
  public void setBy(Provider by) {
    this.by = by;
  }

  /**
   * @return the on
   */
  public Date getOn() {
    return on;
  }

  /**
   * @param on the on to set
   */
  public void setOn(Date on) {
    this.on = on;
  }

  /**
   * @return the stopOn
   */
  public Date getStopOn() {
    return stopOn;
  }

  /**
   * @param stopOn the stopOn to set
   */
  public void setStopOn(Date stopOn) {
    this.stopOn = stopOn;
  }

  /**
   * @return the dispenseAsWritten
   */
  public boolean isDispenseAsWritten() {
    return dispenseAsWritten;
  }

  /**
   * @param dispenseAsWritten the dispenseAsWritten to set
   */
  public void setDispenseAsWritten(boolean dispenseAsWritten) {
    this.dispenseAsWritten = dispenseAsWritten;
  }

  /**
   * @return the duration (in millisecond)
   */
  public long getDuration() {
    return duration;
  }

  /**
   * @param duration the duration to set (in millisecond)
   */
  public void setDuration(long duration) {
    this.duration = duration;
  }

  /**
   * @return the refillInfo
   */
  public String getRefillInfo() {
    return refillInfo;
  }

  /**
   * @param refillInfo the refillInfo to set
   */
  public void setRefillInfo(String refillInfo) {
    this.refillInfo = refillInfo;
  }

  /**
   * @return the instructions
   */
  public String getInstructions() {
    return instructions;
  }

  /**
   * @param instructions the instructions to set
   */
  public void setInstructions(String instructions) {
    this.instructions = instructions;
  }


  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("Prescription [");
    if (by != null) {
      builder.append("by=");
      builder.append(by.toString());
    }
    if (on != null) {
      builder.append(", ");
      builder.append("on=");
      builder.append(on);
      builder.append(", ");
    }
    if (stopOn != null) {
      builder.append(", ");
      builder.append("stopOn=");
      builder.append(stopOn);
    }
    if (dispenseAsWritten != null) {
      builder.append(", ");
      builder.append("dispenseAsWritten=");
      builder.append(dispenseAsWritten.toString());
    }
    if (duration != null) {
      builder.append(", ");
      builder.append("duration=");
      builder.append(duration);
    }
    if (refillInfo != null) {
      builder.append(", ");
      builder.append("refillInfo=");
      builder.append(refillInfo);
    }
    if (instructions != null) {
      builder.append(", ");
      builder.append("instructions=");
      builder.append(instructions);
    }
    builder.append("]");
    return builder.toString();
  }
}
