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

package com.orange.jlinx;

import java.net.MalformedURLException;
import java.net.URL;

import com.orange.jlinx.admin.AdminManager;
import com.orange.jlinx.auth.AuthenticationManager;
import com.orange.jlinx.document.Document;
import com.orange.jlinx.document.DocumentManager;
import com.orange.jlinx.document.DocumentType;
import com.orange.jlinx.messaging.MessagingManager;

/**
 * The Indivo class represents the entry point of the Indivo API. It is used to obtain the instances of
 * the major classes to interact with an Indivo server, which are named Managers.<br/>
 * To create an Indivo instance, configuration parameters of the client application and Indivo server must be used:<br/>
 * - the consumer key of the application<br/>
 * - the consumer secret of the application<br/>
 * - the appId, in the case of a PHA (the email used at PHA registration)<br/>
 * - the URL of the Indivo server<br/>
 * - the URL of the Indivo UI server<br/>
 * 
 * @author dev-indivo@brialon.com
 * @see <a href="http://indivohealth.org">http://indivohealth.org</a>
 */
public class Indivo {

	private TransportManager transportManager;
	private URL uiServerUrl;
	private String appId;
		
	private AuthenticationManager authManager=null;
	private ServiceManager serviceManager=null;
	private AdminManager adminManager=null;
	private MessagingManager messagingManager=null;
	
	/**
	 * Creates an Indivo instance using application and Indivo server configuration parameters.
	 * 
	 * @param consumerKey	the consumer key of the application
	 * @param consumerSecret	the consumer secret of the application
	 * @param appId	the optional appId, required in the case of a PHA (email address used at PHA registration)
	 * @param indivoServerUrl	the URL of the Indivo server
	 * @param indivoUiServerUrl	the URL of the Indivo UI server
	 * @throws MalformedURLException	if one of the URL passed as String is incorrect
	 * @throws IndivoException	if any fatal error occur
	 */
	public Indivo(String consumerKey, String consumerSecret, String appId, String indivoServerUrl,String indivoUiServerUrl)
	throws MalformedURLException, IndivoException{
		URL serverUrl=new URL(indivoServerUrl);
		this.uiServerUrl=new URL(indivoUiServerUrl);
		this.appId=appId;
		this.transportManager=new TransportManager(consumerKey, consumerSecret, serverUrl);
	}
	
    /**
     * Get the AuthenticationManager instance to perform authentication on Indivo server.
     *
     * @return the AuthenticationManager instance
	 * @throws IndivoException	if any fatal error occur
     */
    public AuthenticationManager getAuthenticationManager() throws IndivoException {
        if (this.authManager == null) {
            this.authManager = new AuthenticationManager(transportManager, this.uiServerUrl);
        }
        return this.authManager;
    }

    /**
     * Get the ServiceManager instance for direct Indivo service access.
     * This method is used mainly for development purpose since the ServiceManager should not
     * be used directly. Instead, prefer higher level DocumentManager, AdminManager and MessagingManager.
     *
     * @return the ServiceManager instance
     * @throws IndivoException 
     */
    public ServiceManager getServiceManager() throws IndivoException {
        if (this.serviceManager == null) {
            this.serviceManager = new ServiceManager(this.transportManager, this.appId);
        }
        return this.serviceManager;
    }
    
	/**
	 * Get a DocumentManager instance for Indivo document manipulation. Such instances are parameterized with the class handling the
	 * desired type of document. The type parameter is deduced from the DocumentType parameter.
	 * 
	 * @param <D> the class handling the desired type of document (can be any subclass of Document) 
	 * @param type	a DocumentType instance defining the desired type of document 
	 * @return the DocumentManager instance
	 * @throws IndivoException
	 */
	public <D extends Document> DocumentManager<D> getDocumentManager(DocumentType type) throws IndivoException {
         return new DocumentManager<D>(this.getServiceManager(), type);
	}

	/**
     * Get the AdminManager instance for using the Indivo administrative API.
     * The AdminManager methods can be used only when the consumer keys and secrets provided
     * when creating the Indivo object are attached to a registered administrative application (not a PHA).
     *
     * @return the VitalSignManager instance
     * @throws IndivoException 
     */
    public AdminManager getAdminManager() throws IndivoException {
        if (this.adminManager == null) {
            this.adminManager = new AdminManager(this.getServiceManager());
        }
        return this.adminManager;
    }

    /**
     * Get the MessagingManager instance for using the Indivo messaging features.
     * 
     * @return the MessagingManager instance 
     * @throws IndivoException 
     */
    public MessagingManager getMessagingManager() throws IndivoException {
        if (this.messagingManager == null) {
            this.messagingManager = new MessagingManager(this.getServiceManager());
        }
        return this.messagingManager;
    }

}
