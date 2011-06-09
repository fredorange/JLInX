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

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;

import oauth.signpost.OAuth;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.exception.OAuthException;

import com.orange.jlinx.IndivoException;
import com.orange.jlinx.RecordId;
import com.orange.jlinx.TransportManager;

/**
 * The AuthenticationManager class offer methods to perform the OAuth authentication on an Indivo server.
 * Since OAuth involves user interaction on the web, this single class is not sufficient for the whole process.
 * Nevertheless is provides methods to get the request token and access token directly from the Indivo server. 
 * 
 * @author dev-indivo@brialon.com
 *
 */
public class AuthenticationManager {
	
	private TransportManager transport;
	private URL uiServerUrl;
	private IndivoOAuthProvider provider;
	private Logger logger;
	
	private static final String PATH_REQUEST_TOKEN="oauth/request_token";
	private static final String PATH_ACCESS_TOKEN="oauth/access_token";
	private static final String PATH_AUTHORIZE_TOKEN="oauth/authorize";
	private static final String PARAM_INDIVO_RECORD_ID="xoauth_indivo_record_id";
	private static final String PARAM_INDIVO_CARENET_ID="xoauth_indivo_carenet_id";

	/**
	 * Creates a new AuthenticationManager based on a Transport object plus the URL of the Indivo UI server.
	 * 
	 * @param transport	a Transport object to perform communications with the Indivo server
	 * @param uiServerUrl	the URL of the Indivo UI server to build the authorize token URL
	 * @throws IndivoException
	 */
	public AuthenticationManager(TransportManager transport, URL uiServerUrl) throws IndivoException {
		this.transport=transport;
		this.uiServerUrl=uiServerUrl;
        // create the Indivo dedicated service provider object
		this.provider = new IndivoOAuthProvider(
			        this.getRequestTokenUrl().toString(),
			        this.getAccessTokenUrl().toString(),
			        this.getAuthorizeTokenUrl().toString());
		this.logger=Logger.getLogger(this.getClass().getName());
	}

	/**
	 * Contact the Indivo server to obtain an OAuth request token. 
	 * 
	 * @param recordId	an optional record ID if available (sent by Indivo server to start page)
	 * @return	the request token returned by the server, as an RequestToken object
	 * @throws IndivoException	if a fatal error occur
	 */
	public RequestToken getRequestToken(RecordId recordId) throws IndivoException{
		return getRequestToken(recordId, null);
	}
	
	/**
	 * Contact the Indivo server to obtain an OAuth request token. 
	 * 
	 * @param recordId	an optional record ID if available (sent by Indivo server to start page)
	 * @param callbackUrl	an optional callback URL in the case the one configured in Indivo for the PHA does not suit
	 * @return	the request token returned by the server, as an RequestToken object
	 * @throws IndivoException	if a fatal error occur
	 */
	public RequestToken getRequestToken(RecordId recordId, String callbackUrl)
	throws IndivoException{
		RequestToken result=null;
    	String callbackValue;
    	if(callbackUrl==null)
    		callbackValue=OAuth.OUT_OF_BAND; // ("oob" value)
    	else
    		callbackValue=callbackUrl;
		try {
			OAuthConsumer consumer=this.transport.getNewOAuthConsumer();
			String url=this.provider.retrieveRequestToken(consumer, callbackValue, recordId);
    		result=new RequestToken();
    		result.setConsumer(consumer);
    		result.setAuthorizationUrl(new URL(url));
		} catch (OAuthException e) {
			throw new IndivoException("Error when retrieving request token !",e);
		} catch (MalformedURLException e) {
			throw new IndivoException("Error: wrong format of OAuth authorize URL !",e);
		}
		return result;
	}
	
	/**
	 * Contact the Indivo server to obtain an OAuth access token. 
	 * 
	 * @param requestToken	the request token previously obtained from the Indivo server
	 * @param verificationCode	the verification code sent by the Indivo server
	 * @return	the access token returned by the server, as an AccessToken object
	 * @throws IndivoException	if a fatal error occur
	 */
	public AccessToken getAccessToken(RequestToken requestToken, String verificationCode) throws IndivoException {
		OAuthConsumer consumer=requestToken.getConsumer();
		try {
			provider.retrieveAccessToken(consumer, verificationCode);
		} catch (OAuthException e) {
			throw new IndivoException("Error when retrieving access token !",e);
		}
		
		AccessToken result=new AccessToken();
		result.setToken(consumer.getToken());
		result.setTokenSecret(consumer.getTokenSecret());
		String recordIdParam=this.provider.getResponseParameters().getFirst(PARAM_INDIVO_RECORD_ID);
		String carenetIdParam=this.provider.getResponseParameters().getFirst(PARAM_INDIVO_CARENET_ID);
		RecordId recordId=new RecordId(recordIdParam, carenetIdParam);
		result.setRecordId(recordId);
		this.logger.fine(this.provider.getResponseParameters().toString());
		return result;
	}
	
	/**
	 * @return the URL to get an OAuth request token on configured Indivo server 
	 * @throws IndivoException	if a fatal error occur
	 */
	private URL getRequestTokenUrl() throws IndivoException {
		URL result=null;
		try {
			result=new URL(this.transport.getIndivoServerUrl(),PATH_REQUEST_TOKEN);
		} catch (MalformedURLException e) {
			throw new IndivoException("Unexpected error: wrong format for request token URL !",e);
		}
		return result;
	}

	/**
	 * @return the URL of the authorize token page on configured Indivo UI server 
	 * @throws IndivoException	if a fatal error occur
	 */
	private URL getAuthorizeTokenUrl() throws IndivoException {
		URL result=null;
		try {
			result=new URL(this.uiServerUrl,PATH_AUTHORIZE_TOKEN);
		} catch (MalformedURLException e) {
			throw new IndivoException("Unexpected error: wrong format for access token URL !",e);
		}
		return result;
	}

	/**
	 * @return the URL to get an OAuth access token on configured Indivo server 
	 * @throws IndivoException	if a fatal error occur
	 */
	private URL getAccessTokenUrl() throws IndivoException {
		URL result=null;
		try {
			result=new URL(this.transport.getIndivoServerUrl(),PATH_ACCESS_TOKEN);
		} catch (MalformedURLException e) {
			throw new IndivoException("Unexpected error: wrong format for authorize token URL !",e);
		}
		return result;
	}

}
