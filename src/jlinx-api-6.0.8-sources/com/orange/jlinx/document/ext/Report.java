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

import com.orange.jlinx.document.Document;


/**
 * The Report class represents the informations contained in a single Indivo Report document.
 * That is one document plus its associated meta data.
 * 
 * @author dev-indivo@brialon.com
 *
 * @param <D>	the type of the medical document present in the report (may be any Document type)
 */
public class Report<D extends Document> {

	private DocumentMeta meta;
	private D document;
	
	/**
	 * @return the meta
	 */
	public DocumentMeta getMeta() {
		return meta;
	}
	/**
	 * @param meta the meta to set
	 */
	public void setMeta(DocumentMeta meta) {
		this.meta = meta;
	}
	/**
	 * @return the medical
	 */
	public D getDocument() {
		return document;
	}
	/**
	 * @param medical the medical to set
	 */
	public void setDocument(D medical) {
		this.document = medical;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Report [");
		if (document != null) {
			builder.append("document=");
			builder.append(document);
			builder.append(", ");
		}
		if (meta != null) {
			builder.append("meta=");
			builder.append(meta);
		}
		builder.append("]");
		return builder.toString();
	}

}
