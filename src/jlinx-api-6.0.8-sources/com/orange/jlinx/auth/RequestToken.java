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

import java.net.URL;

import oauth.signpost.OAuthConsumer;

/**
 * The RequestToken class offer a single object to represent all info
 * related to an OAuth request token:<br/>
 * - an OAuthConsumer object containing the request token string and secret,<br/>
 * - the authorization URL sent by the provider to continue the authentication process.<br/>
 * 
 * @author dev-indivo@brialon.com
 *
 */
public class RequestToken {

	private OAuthConsumer consumerFilledWithToken;
	private URL authorizationUrl;
	
	/**
	 * @return the consumerFilledWithToken
	 */
	public OAuthConsumer getConsumer() {
		return consumerFilledWithToken;
	}
	/**
	 * @param consumerFilledWithToken the consumerFilledWithToken to set
	 */
	public void setConsumer(OAuthConsumer consumerFilledWithToken) {
		this.consumerFilledWithToken = consumerFilledWithToken;
	}
	/**
	 * @return the authorizationUrl
	 */
	public URL getAuthorizationUrl() {
		return authorizationUrl;
	}
	/**
	 * @param authorizationUrl the authorizationUrl to set
	 */
	public void setAuthorizationUrl(URL authorizationUrl) {
		this.authorizationUrl = authorizationUrl;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RequestToken [");
		if (authorizationUrl != null) {
			builder.append("authorizationUrl=");
			builder.append(authorizationUrl);
			builder.append(", ");
		}
		if (consumerFilledWithToken != null) {
			builder.append("consumerFilledWithToken=");
			builder.append(consumerFilledWithToken);
		}
		builder.append("]");
		return builder.toString();
	}
}
