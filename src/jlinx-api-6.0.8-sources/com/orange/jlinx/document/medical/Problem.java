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
import com.orange.jlinx.document.x.XProblem;
import java.util.Date;
import javax.xml.bind.JAXBElement;

/**
 *
 * @author fzql9967
 */
public class Problem extends Document {

  private Date dateOnset;
  private Date dateResolution;
  private CodedValue name;
  private String comments;
  private String diagnosedBy;
  /**
   * A DocumentType identifying the Indivo document type handled by this
   * class.
   */
  public static DocumentType DOCUMENT_TYPE = new DocumentType(
          "Problem",
          "http://indivo.org/vocab/xml/documents#Problem",
          "problems",
          Problem.class);

  @Override
  public JAXBElement<?> getXmlElement() {
    XProblem xProblem = XDocumentFactory.getXProblem(this);
    JAXBElement<XProblem> result = new JAXBElement<XProblem>(
            this.getQName(),
            XProblem.class,
            xProblem);

    return result;
  }

  public Problem(Date dateOnset, Date dateResolution, CodedValue name, String comments, String diagnosedBy) {
    super(DOCUMENT_TYPE);

    this.dateOnset = dateOnset;
    this.dateResolution = dateResolution;
    this.name = name;
    this.comments = comments;
    this.diagnosedBy = diagnosedBy;
  }

  /**
   * @return the dateOnset
   */
  public Date getDateOnset() {
    return dateOnset;
  }

  /**
   * @param dateOnset the dateOnset to set
   */
  public void setDateOnset(Date dateOnset) {
    this.dateOnset = dateOnset;
  }

  /**
   * @return the dateResolution
   */
  public Date getDateResolution() {
    return dateResolution;
  }

  /**
   * @param dateResolution the dateResolution to set
   */
  public void setDateResolution(Date dateResolution) {
    this.dateResolution = dateResolution;
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
   * @return the comments
   */
  public String getComments() {
    return comments;
  }

  /**
   * @param comments the comments to set
   */
  public void setComments(String comments) {
    this.comments = comments;
  }

  /**
   * @return the diagnosedBy
   */
  public String getDiagnosedBy() {
    return diagnosedBy;
  }

  /**
   * @param diagnosedBy the diagnosedBy to set
   */
  public void setDiagnosedBy(String diagnosedBy) {
    this.diagnosedBy = diagnosedBy;
  }


  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("Problem [");
    if (getDateOnset() != null) {
      builder.append("dateOnset=");
      builder.append(getDateOnset());
    }
    if (getDateResolution() != null) {
      builder.append(", ");
      builder.append("dateResolution=");
      builder.append(getDateResolution());
    }
    if (getName() != null) {
      builder.append(", ");
      builder.append("name=");
      builder.append(getName().toString());
    }
    if (getComments() != null) {
      builder.append(", ");
      builder.append("comments=");
      builder.append(getComments());
    }
    if (getDiagnosedBy() != null) {
      builder.append(", ");
      builder.append("diagnosedBy=");
      builder.append(getDiagnosedBy());
    }
    builder.append("]");
    return builder.toString();
  }
}
