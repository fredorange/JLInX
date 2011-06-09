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
package com.orange.jlinx.document;

import com.orange.jlinx.document.Document;
import com.orange.jlinx.document.DocumentType;
import com.orange.jlinx.document.XDocumentFactory;
import com.orange.jlinx.document.x.XProvider;
import javax.xml.bind.JAXBElement;

/**
 *
 * @author fzql9967
 */
public class Provider extends Document {

  private String name;
  private String institution;
  /**
   * A DocumentType identifying the Indivo document type handled by this
   * class.
   */
  public static DocumentType DOCUMENT_TYPE = new DocumentType(
          "Provider",
          "http://indivo.org/vocab/xml/documents#Provider",
          "providers",
          Provider.class);

  @Override
  public JAXBElement<?> getXmlElement() {
    XProvider xProvider = XDocumentFactory.getXProvider(this);
    JAXBElement<XProvider> result = new JAXBElement<XProvider>(
            this.getQName(),
            XProvider.class,
            xProvider);

    return result;
  }

  public Provider(String name, String institution) {
    super(DOCUMENT_TYPE);

    this.name = name;
    this.institution = institution;
  }

  // Name
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  // Institution
  public String getInstitution() {
    return institution;
  }

  public void setInstitution(String institution) {
    this.institution = institution;
  }

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("Procedure [");
    if (name != null) {
      builder.append("name=");
      builder.append(name);
    }
    if (institution != null) {
      builder.append(", ");
      builder.append("institution=");
      builder.append(institution);
    }
    builder.append("]");
    return builder.toString();
  }
}
