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

import javax.xml.bind.JAXBElement;

/**
 * This class may represent any Indivo document. It encapsulates its content as an Object.
 * It is used to handle an Indivo document when:<br/>
 * - the document type is not important (e.g when getting a DocumentManager to perform a status change)<br/>
 * - the document type is not known in advance by the user (e.g when retrieving all documents in a record)<br/>
 * - the type of a document sent by server is not known by the API (e.g when retrieving related documents)
 * 
 * @author dev-indivo@brialon.com
 *
 */
public class AnyDocument extends Document {

  private Object content;
  /**
   * A DocumentType identifying the Indivo document type handled by this class.
   */
  public static DocumentType DOCUMENT_TYPE = new DocumentType(
          "any",
          "http://orange.com/any",
          null,
          AnyDocument.class);

  /**
   * Creates a new instance of AnyDocument, from given raw content.
   * @param content
   */
  public AnyDocument(Object content) {
    super(DOCUMENT_TYPE);
    
    this.content = content;
  }

  /**
   * @return the content
   */
  public Object getContent() {
    return content;
  }

  /**
   * @param content the content to set
   */
  public void setContent(Object content) {
    this.content = content;
  }

  /* (non-Javadoc)
   * @see com.orange.jlinx.document.Document#getXmlElement()
   */
  @Override
  public JAXBElement<?> getXmlElement() {
    return null;
  }

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return this.content.toString();
  }

  /* (non-Javadoc)
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object o) {
    return o != null && this.toString().equals(o.toString());
  }
}
