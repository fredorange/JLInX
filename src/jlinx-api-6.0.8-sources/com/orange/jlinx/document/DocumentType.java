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

import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Logger;
import javax.xml.namespace.QName;

/**
 * The DocumentType class contains the information belonging to a document type
 * that can be stored in Indivo.
 * 
 * @author dev-indivo@brialon.com
 *
 */
public class DocumentType {
	
	private String typeName;
	private String reportName;
	private URI typeUri;
	private Class<? extends Document> documentClass;
	private Logger logger;
		
	/**
	 * Creates a new DocumentType from its identifying URI
	 * (e.g "http://indivo.org/vocab/xml/documents#VitalSign").
	 * Important: the type name must be the same as the corresponding XML element tag name.
	 * 
	 * @param typeName	the literal name of the document type, must be the same as the
	 * corresponding XML tag name
	 * @param typeUri	the URI defining the document type
	 * @param reportName	the name of the Indivo report service allowing to retrieve the documents
	 * of this type 
	 * @param documentClass	the class used to represent documents of this type
	 */
	public DocumentType(String typeName, String typeUri, String reportName, Class<? extends Document> documentClass){
		this.typeName=typeName;
		this.logger=Logger.getLogger(this.getClass().toString());
		try {
			this.typeUri=new URI(typeUri);
		} catch (URISyntaxException e) {
			logger.warning("Bad type URI: "+typeUri);
		}
		this.reportName=reportName;
		this.documentClass=documentClass;
	}

	/**
	 * @return the typeName
	 */
	public String getTypeName() {
		return this.typeName;
	}

	/**
	 * @return the typeUri
	 */
	public URI getTypeUri() {
		return this.typeUri;
	}

	/**
	 * @return the reportName
	 */
	public String getReportName() {
		return reportName;
	}

	/**
	 * @return the documentClass
	 */
	public Class<? extends Document> getDocumentClass() {
		return this.documentClass;
	}

	/**
	 * Returns the QName for this document type, based on the type name.
	 * 
	 * @return	the QName object for this document type
	 */
	public QName getQName() {
		return new QName(this.typeName);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DocumentType [");
		if (typeName != null) {
			builder.append("typeName=");
			builder.append(typeName);
			builder.append(", ");
		}
		if (typeUri != null) {
			builder.append("typeUri=");
			builder.append(typeUri.toString());
			builder.append(", ");
		}
		if (reportName != null) {
			builder.append("reportName=");
			builder.append(reportName);
			builder.append(", ");
		}
		if (documentClass != null) {
			builder.append("documentClass=");
			builder.append(documentClass.getName());
		}
		builder.append("]");
		return builder.toString();
	}


}
