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

import com.orange.jlinx.document.Provider;
import com.orange.jlinx.document.Document;
import com.orange.jlinx.document.DocumentType;
import com.orange.jlinx.document.XDocumentFactory;
import com.orange.jlinx.document.ext.CodedValue;
import com.orange.jlinx.document.x.XProcedure;
import java.util.Date;
import javax.xml.bind.JAXBElement;

/**
 *
 * @author fzql9967
 */
public class Procedure extends Document {

  private Date datePerformed;
  private CodedValue name;
  private Provider provider;
  private String location;
  private String comments;
  /**
   * A DocumentType identifying the Indivo document type handled by this
   * class.
   */
  public static DocumentType DOCUMENT_TYPE = new DocumentType(
          "Procedure",
          "http://indivo.org/vocab/xml/documents#Procedure",
          "procedures",
          Procedure.class);

  @Override
  public JAXBElement<?> getXmlElement() {
    XProcedure xProcedure = XDocumentFactory.getXProcedure(this);
    JAXBElement<XProcedure> result = new JAXBElement<XProcedure>(
            this.getQName(),
            XProcedure.class,
            xProcedure);

    return result;
  }

  public Procedure(Date datePerformed, CodedValue name, Provider provider, String location, String comments) {
    super(DOCUMENT_TYPE);

    this.datePerformed = datePerformed;
    this.name = name;
    this.provider = provider;
    this.location = location;
    this.comments = comments;
  }

  // DatePerformed
  public Date getDatePerformed() {
    return datePerformed;
  }

  public void setDatePerformed(Date datePerformed) {
    this.datePerformed = datePerformed;
  }

  // Name
  public CodedValue getName() {
    return name;
  }

  public void setName(CodedValue name) {
    this.name = name;
  }

  // Provider
  public Provider getProvider() {
    return provider;
  }

  public void setProvider(Provider provider) {
    this.provider = provider;
  }

  // Location
  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  // Comments
  public String getComments() {
    return comments;
  }

  public void setComments(String comments) {
    this.comments = comments;
  }

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("Procedure [");
    if (datePerformed != null) {
      builder.append("datePerformed=");
      builder.append(datePerformed);
      builder.append(", ");
    }
    if (name != null) {
      builder.append("name=");
      builder.append(name.toString());
      builder.append(", ");
    }
    if (provider != null) {
      builder.append("provider=");
      builder.append(provider);
      builder.append(", ");
    }
    if (location != null) {
      builder.append("location=");
      builder.append(location);
      builder.append(", ");
    }
    if (comments != null) {
      builder.append("comments=");
      builder.append(comments);
    }
    builder.append("]");
    return builder.toString();
  }
}
