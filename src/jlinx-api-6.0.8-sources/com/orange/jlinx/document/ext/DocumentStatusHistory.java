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

package com.orange.jlinx.document.ext;

import java.util.List;

/**
 * This class represents the information sent by the Indivo server
 * when retrieving a document's status history. 
 * 
 * @author dev-indivo@brialon.com
 *
 */
public class DocumentStatusHistory {

    private List<DocumentStatus> documentStatus;
    private String documentId;
    
	/**
	 * @return the documentStatus
	 */
	public List<DocumentStatus> getDocumentStatus() {
		return documentStatus;
	}
	/**
	 * @param documentStatus the documentStatus to set
	 */
	public void setDocumentStatus(List<DocumentStatus> documentStatus) {
		this.documentStatus = documentStatus;
	}
	/**
	 * @return the documentId
	 */
	public String getDocumentId() {
		return documentId;
	}
	/**
	 * @param documentId the documentId to set
	 */
	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DocumentStatusHistory [");
		if (documentId != null) {
			builder.append("documentId=");
			builder.append(documentId);
			builder.append(", ");
		}
		if (documentStatus != null) {
			builder.append("documentStatus=");
			builder.append(documentStatus);
		}
		builder.append("]");
		return builder.toString();
	}
}
