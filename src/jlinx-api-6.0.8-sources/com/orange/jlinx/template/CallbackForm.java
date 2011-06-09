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

package com.orange.jlinx.template;

/**
 * This class represents the form parameters received by the OAuth callback URL.
 * 
 * @author dev-indivo@brialon.com
 *
 */
public class CallbackForm {

	private String oAuthToken;
	private String oAuthVerifier;
	
	/**
	 * @return the oAuthToken
	 */
	public String getOAuthToken() {
		return oAuthToken;
	}
	/**
	 * @param oAuthToken the oAuthToken to set
	 */
	public void setOAuthToken(String oAuthToken) {
		this.oAuthToken = oAuthToken;
	}
	/**
	 * @return the oAuthVerifier
	 */
	public String getOAuthVerifier() {
		return oAuthVerifier;
	}
	/**
	 * @param oAuthVerifier the oAuthVerifier to set
	 */
	public void setOAuthVerifier(String oAuthVerifier) {
		this.oAuthVerifier = oAuthVerifier;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CallbackForm [oAuthToken=");
		builder.append(oAuthToken);
		builder.append(", oAuthVerifier=");
		builder.append(oAuthVerifier);
		builder.append("]");
		return builder.toString();
	}
	
}
