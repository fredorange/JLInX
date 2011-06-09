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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.orange.jlinx.Indivo;
import com.orange.jlinx.IndivoException;
import com.orange.jlinx.RecordId;
import com.orange.jlinx.auth.AuthenticationManager;
import com.orange.jlinx.auth.RequestToken;

/**
 * This Servlet is intended to serve as a base for an Indivo PHA start URL.
 * The start URL must deal with OAuth authentication which is the purpose of this class.
 * It uses an Indivo object which should be created by implementing class.
 * 
 * @author dev-indivo@brialon.com
 *
 */
public abstract class StartUrlServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private static final String PARAM_RECORD_ID="record_id";
	private static final String PARAM_CARENET_ID="carenet_id";

	private Indivo indivo;

	/**
	 * Creates a new StartUrlServlet with the specified Indivo instance.
	 * @param indivo	an instance of Indivo referencing an Indivo server
	 */
	protected StartUrlServlet(Indivo indivo){
		this.indivo=indivo;
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AuthenticationManager authManager=null;
		try {
			authManager = indivo.getAuthenticationManager();
		} catch (IndivoException e) {
			throw new ServletException(e);
		}
		
		try {
			String recordIdParam=request.getParameter(PARAM_RECORD_ID);
			String carenetIdParam=request.getParameter(PARAM_CARENET_ID);
			RecordId recordId=new RecordId(recordIdParam, carenetIdParam);

			// get request token from Indivo server
			RequestToken token=authManager.getRequestToken(recordId);
			// store token in session
			SessionHelper.setRequestToken(request, token);
			// redirect to authorize URL
			// authorize URL have to be encoded ...
			String encodedUrl = response.encodeRedirectURL(token.getAuthorizationUrl().toString());
			// ... before being used
			response.sendRedirect(encodedUrl);	   

		} catch (IndivoException e) {
			throw new ServletException(e);
		}
	}
	
} 
