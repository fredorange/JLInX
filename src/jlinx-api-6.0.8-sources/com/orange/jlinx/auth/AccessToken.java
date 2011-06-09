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

package com.orange.jlinx.auth;

import com.orange.jlinx.RecordId;

/**
 * The AccessToken class offer an Indivo dedicated object to store all info
 * linked to an OAuth access token:<br/>
 * - the token string,<br/>
 * - the token secret accompanying the token,<br/>
 * - the Indivo record ID for which this token has been granted (either a recordId or a carenetId).<br/>
 * <br/>
 * Since an AccessToken object encapsulates a record ID, it may be used as single input for all the methods
 * requiring a recordId/carenetId plus access token.
 * 
 * @author dev-indivo@brialon.com
 *
 */
public class AccessToken {
	
	private String token;
	private String tokenSecret;
	private RecordId recordId;
	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}
	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}
	/**
	 * @return the tokenSecret
	 */
	public String getTokenSecret() {
		return tokenSecret;
	}
	/**
	 * @param tokenSecret the tokenSecret to set
	 */
	public void setTokenSecret(String tokenSecret) {
		this.tokenSecret = tokenSecret;
	}
	/**
	 * @return the recordId
	 */
	public RecordId getRecordId() {
		return recordId;
	}
	/**
	 * @param recordId the recordId to set
	 */
	public void setRecordId(RecordId recordId) {
		this.recordId = recordId;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AccessToken [");
		if (recordId != null) {
			builder.append("recordId=");
			builder.append(recordId);
			builder.append(", ");
		}
		if (token != null) {
			builder.append("token=");
			builder.append(token);
			builder.append(", ");
		}
		if (tokenSecret != null) {
			builder.append("tokenSecret=");
			builder.append(tokenSecret);
		}
		builder.append("]");
		return builder.toString();
	}

}
