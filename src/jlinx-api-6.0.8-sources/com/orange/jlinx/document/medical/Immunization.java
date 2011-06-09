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
import com.orange.jlinx.document.x.XImmunization;
import java.util.Date;
import javax.xml.bind.JAXBElement;

/**
 *
 * @author fzql9967
 */
public class Immunization extends Document {

  private Date dateDiagnosed;
  private Date dateAdministered;
  private String administeredBy;
  private Immunization.Vaccine vaccine;
  private Integer sequence;
  private CodedValue anatomicSurface;
  private String adverseEvent;
  /**
   * A DocumentType identifying the Indivo document type handled by this
   * class.
   */
  public static DocumentType DOCUMENT_TYPE = new DocumentType(
          "Immunization",
          "http://indivo.org/vocab/xml/documents#Immunization",
          "immunization",
          Immunization.class);

  @Override
  public JAXBElement<?> getXmlElement() {
    XImmunization xImmunization = XDocumentFactory.getXImmunization(this);
    JAXBElement<XImmunization> result = new JAXBElement<XImmunization>(
            this.getQName(),
            XImmunization.class,
            xImmunization);

    return result;
  }

  public Immunization(Date dateAdministered, String administeredBy, Immunization.Vaccine vaccine, Integer sequence, CodedValue anatomicSurface, String adverseEvent) {
    super(DOCUMENT_TYPE);

    this.dateAdministered = dateAdministered;
    this.administeredBy = administeredBy;
    this.vaccine = vaccine;
    this.sequence = sequence;
    this.anatomicSurface = anatomicSurface;
    this.adverseEvent = adverseEvent;
  }


  // DateAdministered
  public Date getDateAdministered() {
    return dateDiagnosed;
  }

  public void setDateAdministered(Date dateAdministered) {
    this.dateAdministered = dateAdministered;
  }

  // AdministeredBy
  public String getAdministeredBy() {
    return administeredBy;
  }

  public void setAdministeredBy(String administeredBy) {
    this.administeredBy = administeredBy;
  }

  // Vaccine
  public Immunization.Vaccine getVaccine() {
    return vaccine;
  }

  public void setVaccine(Immunization.Vaccine vaccine) {
    this.vaccine = vaccine;
  }

  // Sequence
  public Integer getSequence() {
    return sequence;
  }

  public void setSequence(Integer sequence) {
    this.sequence = sequence;
  }

  // AnatomicSurface
  public CodedValue getAnatomicSurface() {
    return anatomicSurface;
  }

  public void setAnatomicSurface(CodedValue anatomicSurface) {
    this.anatomicSurface = anatomicSurface;
  }

  // AdverseEvent
  public String getAdverseEvent() {
    return adverseEvent;
  }

  public void setAdverseEvent(String adverseEvent) {
    this.adverseEvent = adverseEvent;
  }


  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("Immunization [");
    if (dateDiagnosed != null) {
      builder.append("dateDiagnosed=");
      builder.append(dateDiagnosed);
    }
    if (dateAdministered != null) {
      builder.append(", ");
      builder.append("dateAdministered=");
      builder.append(dateAdministered);
    }
    if (administeredBy != null) {
      builder.append(", ");
      builder.append("administeredBy=");
      builder.append(administeredBy);
    }
    if (vaccine != null) {
      builder.append(", ");
      builder.append("vaccine=");
      builder.append(vaccine.toString());
    }
    if (sequence != null) {
      builder.append(", ");
      builder.append("sequence=");
      builder.append(sequence);
    }
    if (anatomicSurface != null) {
      builder.append(", ");
      builder.append("anatomicSurface=");
      builder.append(anatomicSurface.toString());
    }
    if (adverseEvent != null) {
      builder.append(", ");
      builder.append("adverseEvent=");
      builder.append(adverseEvent);
    }
    builder.append("]");

    return builder.toString();
  }

  public static class Vaccine {

    protected CodedValue type;
    protected String manufacturer;
    protected String lot;
    protected Date expiration;

    public Vaccine(CodedValue type, String manufacturer, String lot, Date expiration) {
      this.type = type;
      this.manufacturer = manufacturer;
      this.lot = lot;
      this.expiration = expiration;
    }

    // Type
    public CodedValue getType() {
      return type;
    }

    public void setType(CodedValue type) {
      this.type = type;
    }

    // Manufacturer
    public String getManufacturer() {
      return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
      this.manufacturer = manufacturer;
    }

    // Lot
    public String getLot() {
      return lot;
    }

    public void setLot(String lot) {
      this.lot = lot;
    }

    // Expiration
    public Date getExpiration() {
      return expiration;
    }

    public void setExpiration(Date expiration) {
      this.expiration = expiration;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
      StringBuilder builder = new StringBuilder();
      builder.append("Vaccine [");
      if (type != null) {
        builder.append("type=");
        builder.append(type.toString());
      }
      if (manufacturer != null) {
        builder.append(", ");
        builder.append("manufacturer=");
        builder.append(manufacturer);
      }
      if (lot != null) {
        builder.append(", ");
        builder.append("lot=");
        builder.append(lot);
      }
      if (expiration != null) {
        builder.append(", ");
        builder.append("expiration=");
        builder.append(expiration);
      }
      builder.append("]");
      return builder.toString();
    }
  }
}
