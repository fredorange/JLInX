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
import com.orange.jlinx.document.x.XAllergy;
import java.util.Date;
import javax.xml.bind.JAXBElement;

/**
 *
 * @author fzql9967
 */
public class Allergy extends Document {

  private Date dateDiagnosed;
  private String diagnosedBy;
  private Allergy.Allergen allergen;
  private String reaction;
  private String specifics;
  /**
   * A DocumentType identifying the Indivo document type handled by this
   * class.
   */
  public static DocumentType DOCUMENT_TYPE = new DocumentType(
          "Allergy",
          "http://indivo.org/vocab/xml/documents#Allergy",
          "allergies",
          Allergy.class);

  @Override
  public JAXBElement<?> getXmlElement() {
    XAllergy xAllergy = XDocumentFactory.getXAllergy(this);
    JAXBElement<XAllergy> result = new JAXBElement<XAllergy>(
            this.getQName(),
            XAllergy.class,
            xAllergy);

    return result;
  }

  public Allergy(Allergy.Allergen allergen, String reaction, String specifics, String diagnosedBy, Date dateDiagnosed) {
    super(DOCUMENT_TYPE);

    this.dateDiagnosed = dateDiagnosed;
    this.diagnosedBy = diagnosedBy;
    this.allergen = allergen;
    this.reaction = reaction;
    this.specifics = specifics;
  }

  // Allergen
  public Allergy.Allergen getAllergen() {
    return allergen;
  }

  public void setAllergen(Allergy.Allergen allergen) {
    this.allergen = allergen;
  }

  // Reaction
  public String getReaction() {
    return reaction;
  }

  public void setReaction(String reaction) {
    this.reaction = reaction;
  }

  // Specifics
  public String getSpecifics() {
    return specifics;
  }

  public void setSpecifics(String specifics) {
    this.specifics = specifics;
  }

  // DiagnosedBy
  public String getDiagnosedBy() {
    return diagnosedBy;
  }

  public void setDiagnosedBy(String diagnosedBy) {
    this.diagnosedBy = diagnosedBy;
  }

  // DateDiagnosed
  public Date getDateDiagnosed() {
    return dateDiagnosed;
  }

  public void setDateDiagnosed(Date dateDiagnosed) {
    this.dateDiagnosed = dateDiagnosed;
  }

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("Allergy [");
    if (allergen != null) {
      builder.append("allergen=");
      builder.append(allergen.toString());
    }
    if (reaction != null) {
      builder.append(", ");
      builder.append("reaction=");
      builder.append(reaction);
    }
    if (specifics != null) {
      builder.append(", ");
      builder.append("specifics=");
      builder.append(specifics);
    }
    if (diagnosedBy != null) {
      builder.append(", ");
      builder.append("diagnosedBy=");
      builder.append(diagnosedBy);
    }
    if (dateDiagnosed != null) {
      builder.append(", ");
      builder.append("dateDiagnosed=");
      builder.append(dateDiagnosed);
    }
    builder.append("]");
    return builder.toString();
  }

  public static class Allergen {

    private CodedValue name;
    private CodedValue type;

    public Allergen(CodedValue name, CodedValue type) {
      this.name = name;
      this.type = type;
    }

    // Name
    public CodedValue getName() {
      return name;
    }

    public void setName(CodedValue value) {
      this.name = value;
    }

    // Type
    public CodedValue getType() {
      return type;
    }

    public void setType(CodedValue value) {
      this.type = value;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
      StringBuilder builder = new StringBuilder();
      builder.append("Allergen [");
      if (name != null) {
        builder.append("name=");
        builder.append(name.toString());
      }
      if (type != null) {
        builder.append(", ");
        builder.append("type=");
        builder.append(type.toString());
      }
      builder.append("]");
      return builder.toString();
    }
  }
}
