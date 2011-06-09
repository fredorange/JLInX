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

package com.orange.jlinx.admin;


/**
 * This class handles the data associated to an Indivo record.
 * 
 * @author dev-indivo@brialon.com
 *
 */
public class Record {
    protected String contactDocumentId;
    protected String demographicsDocumentId;
    protected String id;
    protected String label;
	/**
	 * @return the contactDocumentId
	 */
	public String getContactDocumentId() {
		return contactDocumentId;
	}
	/**
	 * @param contactDocumentId the contactDocumentId to set
	 */
	public void setContactDocumentId(String contactDocumentId) {
		this.contactDocumentId = contactDocumentId;
	}
	/**
	 * @return the demographicsDocumentId
	 */
	public String getDemographicsDocumentId() {
		return demographicsDocumentId;
	}
	/**
	 * @param demographicsDocumentId the demographicsDocumentId to set
	 */
	public void setDemographicsDocumentId(String demographicsDocumentId) {
		this.demographicsDocumentId = demographicsDocumentId;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}
	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Record [");
		if (contactDocumentId != null) {
			builder.append("contactDocumentId=");
			builder.append(contactDocumentId);
			builder.append(", ");
		}
		if (demographicsDocumentId != null) {
			builder.append("demographicsDocumentId=");
			builder.append(demographicsDocumentId);
			builder.append(", ");
		}
		if (id != null) {
			builder.append("id=");
			builder.append(id);
			builder.append(", ");
		}
		if (label != null) {
			builder.append("label=");
			builder.append(label);
		}
		builder.append("]");
		return builder.toString();
	}

}
