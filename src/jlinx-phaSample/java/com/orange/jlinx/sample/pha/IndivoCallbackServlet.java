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

package com.orange.jlinx.sample.pha;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.orange.jlinx.template.CallbackUrlServlet;

/**
 * Servlet implementation class IndivoCallbackServlet
 */
public class IndivoCallbackServlet extends CallbackUrlServlet {

	private static final long serialVersionUID = 1L;
       
    /**
     * @throws DaoException 
     * @see HttpServlet#HttpServlet()
     */
    public IndivoCallbackServlet() throws DaoException {
        super(DaoFactory.getIndivoDaoForPha().getIndivo());
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    super.doGet(request, response);
		String display = "<html><head><link type='text/css' href='../css/indivo.css' rel='Stylesheet' /></head>"
			+ "<body>"
	        + "<h2>Authorization completed successfully</h2>"
	        + "<strong>token: </strong>"
	        + this.accessToken.getToken() + "<br/>"
	        + "<strong>secret: </strong>"
	        + this.accessToken.getTokenSecret() + "<br/>"
	        + "<strong>authorized for recordId: </strong>"
	        + this.accessToken.getRecordId();
		    ServletOutputStream respOut = response.getOutputStream();
		    respOut.println(display);
		    try{
		    	String tokenName;
		    	if(this.accessToken.getRecordId().isCarenet())
		    		tokenName=AccessTokenDao.COMMON_CARENET_TOKEN_NAME;
		    	else
		    		tokenName=AccessTokenDao.COMMON_RECORD_TOKEN_NAME;
				DaoFactory.getAccessTokenDao().saveAccessToken(this.accessToken, tokenName);
			    respOut.println("<br/><br/>token saved"
				        + "</body></html>");
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

}
