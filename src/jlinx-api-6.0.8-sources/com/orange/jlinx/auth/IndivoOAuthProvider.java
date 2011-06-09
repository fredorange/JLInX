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

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;

import oauth.signpost.OAuth;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.basic.DefaultOAuthProvider;
import oauth.signpost.basic.HttpURLConnectionResponseAdapter;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import oauth.signpost.exception.OAuthNotAuthorizedException;
import oauth.signpost.http.HttpRequest;
import oauth.signpost.http.HttpResponse;

import com.orange.jlinx.RecordId;
import com.orange.jlinx.TransportManager;

/**
 * A specific OAuthProvider is required for Indivo, for 2 purposes:</br>
 * - as long as Indivo is not fully complient with OAuth 1.0a (i.e send OAUTH_CALLBACK_CONFIRMED),
 * the DefaultOAuthProvider cannot be used,</br>
 * - the parameter indivo_record_id used by the Indivo request token service must be written to the request output,
 * which is not handled by the DefaultOAuthProvider.
 * 
 * @author dev-indivo@brialon.com
 *
 */
public class IndivoOAuthProvider extends DefaultOAuthProvider{

	private static final long serialVersionUID = 1L;

	private static final String PARAM_INDIVO_RECORD_ID="indivo_record_id";
	private static final String PARAM_INDIVO_CARENET_ID="indivo_carenet_id";

	private RecordId sharedRecordId;

	/**
	 * Overwrite DefaultOAuthProvider constructor to perform Indivo specific treatments.
	 * 
	 * @param requestTokenEndpointUrl
	 * @param accessTokenEndpointUrl
	 * @param authorizationWebsiteUrl
	 */
	public IndivoOAuthProvider(String requestTokenEndpointUrl,
			String accessTokenEndpointUrl, String authorizationWebsiteUrl) {
		super(requestTokenEndpointUrl, accessTokenEndpointUrl, authorizationWebsiteUrl);
		// force the use of OAuth 1.0a revision 
		this.setOAuth10a(true);
	}


    /* (non-Javadoc)
     * This method is overridden in order to write the optional recordId|carenetId to the request output.
     * 
     * @see oauth.signpost.basic.DefaultOAuthProvider#sendRequest(oauth.signpost.http.HttpRequest)
     */
    protected HttpResponse sendRequest(HttpRequest request) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) request.unwrap();
        // if a shared recordId is defined, it has to be written in the request output
        if(this.sharedRecordId!=null){
        	// write the recordId to the request output
        	String indivoRecordId;
        	if(this.sharedRecordId.isCarenet())
        		indivoRecordId=PARAM_INDIVO_CARENET_ID;
        	else
        		indivoRecordId=PARAM_INDIVO_RECORD_ID;
        	indivoRecordId+="="+this.sharedRecordId.getId();
	        connection.setRequestProperty(TransportManager.PROPERTY_CONTENT_TYPE, TransportManager.CONTENT_TYPE_TEXT_PLAIN);
	        connection.setDoOutput(true);
			OutputStream os = connection.getOutputStream();
		    os.write(indivoRecordId.getBytes());
		    os.close();
        }
		connection.connect();
        return new HttpURLConnectionResponseAdapter(connection);
    }

    /* (non-Javadoc)
     * This method is overridden to force the use of the Indivo dedicated retrieveRequestToken().
     * 
     * @see oauth.signpost.AbstractOAuthProvider#retrieveRequestToken(oauth.signpost.OAuthConsumer, java.lang.String)
     */
    @Override
    public String retrieveRequestToken(OAuthConsumer consumer, String callbackUrl)
    throws OAuthMessageSignerException, OAuthNotAuthorizedException,
    OAuthExpectationFailedException, OAuthCommunicationException {
    	return this.retrieveRequestToken(consumer, callbackUrl, null);
    }

    /**
     * This method takes into account the Indivo specificities when obtaining a request token, which are
     * detailed in this class description. It has the same signature as the standard retrieveRequestToken()
     * method except it takes an extra parameter recordId. 
     * 
     * @param consumer	the OAuthConsumer to be used for OAuth processing
     * @param callbackUrl	an optional alternative OAuth callback URL
     * @param recordId	an optional record ID to be used when it is received by the start URL 
     * @return	the received request token as a String
     * @throws OAuthMessageSignerException
     * @throws OAuthNotAuthorizedException
     * @throws OAuthExpectationFailedException
     * @throws OAuthCommunicationException
     */
    public String retrieveRequestToken(OAuthConsumer consumer, String callbackUrl, RecordId recordId)
    throws OAuthMessageSignerException, OAuthNotAuthorizedException,
    OAuthExpectationFailedException, OAuthCommunicationException {

		// invalidate current credentials, if any
		consumer.setTokenWithSecret(null, null);
		
		// if a recordId is present, it has to be used only at request sending (another method in this class) ...
		if(recordId!=null){
			// ... therefore we create a protected block to guarantee the shared recordId we set is not overwritten
			synchronized(this){
				// set the shared recordId to be used by method sendRequest()
				this.sharedRecordId=recordId;
		
				// 1.0a expects the callback to be sent while getting the request token.
				// 1.0 service providers would simply ignore this parameter.
				this.retrieveToken(consumer, super.getRequestTokenEndpointUrl(), OAuth.OAUTH_CALLBACK, callbackUrl);
				
				// unset the shared recordId
				this.sharedRecordId=null;
			}
		} else {
			this.retrieveToken(consumer, super.getRequestTokenEndpointUrl(), OAuth.OAUTH_CALLBACK, callbackUrl);
		}
		
		/// Indivo does not send OAUTH_CALLBACK_CONFIRMED for now
		/// this is why the following lines are commented
		/*
		String callbackConfirmed = super.getResponseParameters().getFirst(OAuth.OAUTH_CALLBACK_CONFIRMED);
		super.getResponseParameters().remove(OAuth.OAUTH_CALLBACK_CONFIRMED);
	    isOAuth10a = Boolean.TRUE.toString().equals(callbackConfirmed);
		*/
		
		// 1.0 service providers expect the callback as part of the auth URL,
		// Do not send when 1.0a.
		if (super.isOAuth10a()) {
		    return OAuth.addQueryParameters(super.getAuthorizationWebsiteUrl(),
		    		OAuth.OAUTH_TOKEN, consumer.getToken());
		} else {
		    return OAuth.addQueryParameters(super.getAuthorizationWebsiteUrl(),
		    		OAuth.OAUTH_TOKEN, consumer.getToken(),
		    		OAuth.OAUTH_CALLBACK, callbackUrl);
		}
    }

}
