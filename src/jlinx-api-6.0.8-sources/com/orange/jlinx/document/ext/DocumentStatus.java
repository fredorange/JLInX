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

import java.util.Date;

/**
 * This class represents the information on a document status,
 * as used when retrieving a document's status history. 
 * 
 * @author dev-indivo@brialon.com
 *
 */
public class DocumentStatus {

    private String status;
    private String reason;
    private String by;
    private Date at;
    
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the reason
	 */
	public String getReason() {
		return reason;
	}
	/**
	 * @param reason the reason to set
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}
	/**
	 * @return the by
	 */
	public String getBy() {
		return by;
	}
	/**
	 * @param by the by to set
	 */
	public void setBy(String by) {
		this.by = by;
	}
	/**
	 * @return the at
	 */
	public Date getAt() {
		return at;
	}
	/**
	 * @param at the at to set
	 */
	public void setAt(Date at) {
		this.at = at;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DocumentStatus [");
		if (at != null) {
			builder.append("at=");
			builder.append(at);
			builder.append(", ");
		}
		if (by != null) {
			builder.append("by=");
			builder.append(by);
			builder.append(", ");
		}
		if (reason != null) {
			builder.append("reason=");
			builder.append(reason);
			builder.append(", ");
		}
		if (status != null) {
			builder.append("status=");
			builder.append(status);
		}
		builder.append("]");
		return builder.toString();
	}

}
