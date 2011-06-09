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
import javax.xml.namespace.QName;

/**
 * The abstract class Document represents an Indivo document. It is the superclass for
 * all the classes handling Indivo documents. 
 * 
 * @author dev-indivo@brialon.com
 *
 */
public abstract class Document{

	private DocumentType documentType;
	
	/**
	 * The Document constructor requires a DocumentType.
	 * 
	 * @param documentType
	 */
	protected Document(DocumentType documentType) {
		this.documentType = documentType;
	}

	/**
	 * @return	a DocumentType representing the type of this document
	 */
	public DocumentType getDocumentType(){
		return this.documentType;
	}
	
	/**
	 * @return	a JAXBElement containing the same data as this document
	 */
	abstract public JAXBElement<?> getXmlElement();

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		return o!=null && this.getClass().equals(o.getClass()) && this.toString().equals(o.toString());
	}
	
	/**
	 * @return	a QName object dedicated to the type of document represented by the implementing class.
	 */
	protected QName getQName() {
		return this.getDocumentType().getQName();
	}
	

}

