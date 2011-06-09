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

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oauth.signpost.OAuth;

import com.orange.jlinx.Indivo;
import com.orange.jlinx.IndivoException;
import com.orange.jlinx.auth.AccessToken;
import com.orange.jlinx.auth.AuthenticationManager;
import com.orange.jlinx.auth.RequestToken;

public abstract class CallbackUrlServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected Indivo indivo;
	protected AccessToken accessToken;

	public CallbackUrlServlet(Indivo indivo){
		this.indivo=indivo;
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			AuthenticationManager authManager=indivo.getAuthenticationManager();
			// get request parameters
			CallbackForm form=getCallbackForm(request);
			// get request token from session
			RequestToken requestToken=SessionHelper.getRequestToken(request);
			// check if session contains a token 
			if(requestToken==null)
				throw new ServletException("Error: no request token stored in session !");
			// check if server sent a token 
			if(form.getOAuthToken()==null || form.getOAuthToken().length()==0)
				throw new ServletException("Error: no request token given by Indivo server !");
			// check if both tokens match
			if(!form.getOAuthToken().equals(requestToken.getConsumer().getToken()))
				throw new ServletException("Error: request token given by Indivo server doesn't match the one stored in session !");
			// get access token from Indivo server
			this.accessToken=authManager.getAccessToken(requestToken,form.getOAuthVerifier());

		} catch (IndivoException e) {
			throw new ServletException(e);
		}
	}
	
	/**
	 * Retrieve a CallbackForm object representing the form parameters received by the callback servlet.
	 * 
	 * @param request	the HTTP request object
	 * @return	form parameters as a CallbackForm object
	 */
	private static CallbackForm getCallbackForm(ServletRequest request){
		CallbackForm result=new CallbackForm();
		result.setOAuthToken(request.getParameter(OAuth.OAUTH_TOKEN));
		result.setOAuthVerifier(request.getParameter(OAuth.OAUTH_VERIFIER));
		return result;
	}


} 
