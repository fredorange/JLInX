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

import javax.servlet.http.HttpServletRequest;

import com.orange.jlinx.auth.RequestToken;

/**
 * SessionHelper provides methods to store and retrieve a RequestToken in a HTTP session.
 * 
 * @author dev-indivo@brialon.com
 *
 */
public class SessionHelper {

	private static final String ATTRIBUTE_REQUEST_TOKEN = "INDIVO_REQUEST_TOKEN";

	/**
	 * Store given request token in the session of specified request.
	 * 
	 * @param request	the HTTP request object 
	 * @param token	the request token object to store
	 */
	public static void setRequestToken(HttpServletRequest request, RequestToken token){
		request.getSession().setAttribute(ATTRIBUTE_REQUEST_TOKEN, token);
	}
	
	/**
	 * Retrieves a request token object previously stored in the session og given HTTP request.
	 * 
	 * @param request	the HTTP request object 
	 * @return	the request object of the session or null if not found
	 */
	public static RequestToken getRequestToken(HttpServletRequest request){
		RequestToken result=(RequestToken)request.getSession().getAttribute(ATTRIBUTE_REQUEST_TOKEN);
		return result;
	}
	
}
