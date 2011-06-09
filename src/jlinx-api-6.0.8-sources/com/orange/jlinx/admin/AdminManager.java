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

package com.orange.jlinx.admin;

import com.orange.jlinx.IndivoException;
import com.orange.jlinx.IndivoNonFatalException;
import com.orange.jlinx.ServiceManager;
import com.orange.jlinx.TransportManager;
import com.orange.jlinx.document.Contact;
import com.orange.jlinx.document.DocumentFactory;
import com.orange.jlinx.document.x.XAccount;
import com.orange.jlinx.document.x.XRecord;
import com.orange.jlinx.document.x.XResultOk;

/**
 * The AdminManager provides the functionalities reserved to Indivo administrative applications.
 * The services offered in this class can be used only when the consumer keys and secrets provided
 * when creating the Indivo object are attached to a registered administrative application (not a PHA).
 * 
 * @author dev-indivo@brialon.com
 *
 */
public class AdminManager {
	
	public final static String ACCOUNT_STATE_UNINITIALIZED="uninitialized";
	public final static String ACCOUNT_STATE_ACTIVE="active";
	public final static String ACCOUNT_STATE_DISABLED="disabled";
	public final static String ACCOUNT_STATE_RETIRED="retired";
	
	/**
	 * AdminManager mainly relies on a documentManager instance for interactions with Indivo.
	 */
	private ServiceManager documentManager;
	
 	/**
 	 * Creates a new AdminManager instance.
 	 * 
 	 * @param documentManager	this will be used for interacting with the Indivo server
 	 */
 	public AdminManager(ServiceManager documentManager){
		this.documentManager=documentManager;
	}
	
	/**
	 * Get information on an account.
	 * 
	 * @param accountId
	 * @return	an Account object as returned by the Indivo server
	 * @throws IndivoException	if a fatal error occur
	 */
	public Account getAccountById(String accountId) throws IndivoException {
		String urlSuffix=this.documentManager.getUrlBuilder().getAccountById(accountId);
		XAccount result=null;
		try {
			result = (XAccount)this.documentManager.doPostRequest(urlSuffix, null, (String)null);
		} catch (IndivoNonFatalException e) {
			// result will be null
		}
		return DocumentFactory.getAccount(result);
	}

	/**
	 * Create a new account on the Indivo server.
	 * 
	 * @param accountId
	 * @param contactEmail
	 * @param fullName
	 * @param primarySecret
	 * @param secondarySecret
	 * @return	an Account object as returned by the Indivo server
	 * @throws IndivoException	if a fatal error occur
	 */
	public Account createAccount(String accountId, String contactEmail, String fullName,
			boolean primarySecret, boolean secondarySecret) throws IndivoException {
		String urlSuffix=this.documentManager.getUrlBuilder().getAccount();
		XAccount result=null;
		String content=getCreateAccountContent(accountId, contactEmail, fullName, primarySecret, secondarySecret);
		try {
			result = (XAccount)this.documentManager.doPostRequest(urlSuffix, null, content);
		} catch (IndivoNonFatalException e) {
			// unwilling to happen, anyway result will be null
		}
		return DocumentFactory.getAccount(result);
	}

	/**
	 * Create a new record on the Indivo server using given contact document.
	 * 
	 * @param contact	the initial contact document of this record 
	 * @return	a Record object as returned by the Indivo server
	 * @throws IndivoException	if a fatal error occur
	 */
	public Record createRecord(Contact contact) throws IndivoException {
		String urlSuffix=this.documentManager.getUrlBuilder().getRecord();
		XRecord result=null;
		try {
			result = (XRecord)this.documentManager.doPostRequest(urlSuffix, null, contact.getXmlElement());
		} catch (IndivoNonFatalException e) {
			// unwilling to happen, anyway result will be null
		}
		return DocumentFactory.getRecord(result);
	}

	/**
	 * Set the owner of given record.
	 * 
	 * @param recordId	the id of the record 
	 * @param accountId	the id of the owner's account
	 * @throws IndivoException	if a fatal error occur
	 */
	public Account setRecordOwner(String recordId, String accountId) throws IndivoException {
		String urlSuffix=this.documentManager.getUrlBuilder().getRecordOwner(recordId);
		XAccount result=null;
		try {
			result = (XAccount)this.documentManager.doPutRequest(urlSuffix, null, accountId);
		} catch (IndivoNonFatalException e) {
			// unwilling to happen, anyway result will be null
		}
		return DocumentFactory.getAccount(result);
	}

	/**
	 * Reset an account on the Indivo server.
	 * 
	 * @param accountId
	 * @return	true if operation succeeded
	 * @throws IndivoException	if a fatal error occur
	 */
	public boolean resetAccount(String accountId) throws IndivoException {
		String urlSuffix=this.documentManager.getUrlBuilder().getAccountReset(accountId);
		boolean result=false;
		try {
			XResultOk xResult=(XResultOk)this.documentManager.doPostRequest(urlSuffix, null, (String)null);
			result=xResult!=null;
		} catch (IndivoNonFatalException e) {
			// result will be false
		}
		return result;
	}

	/**
	 * Set user name and password for given account.
	 * 
	 * @param accountId	the id of the desired account
	 * @param userName
	 * @param password
	 * @return	true if operation succeeded
	 * @throws IndivoException
	 */
	public boolean setUserNameAndPasswordForAccount(String accountId, String userName, String password) throws IndivoException {
		String urlSuffix=this.documentManager.getUrlBuilder().getAccountAuthSystems(accountId);
		String content=getSetUserNameAndPasswordContent(accountId, userName, password);
		boolean result=false;
		try {
			XResultOk xResult = (XResultOk)this.documentManager.doPostRequest(urlSuffix, null, content);
			result=xResult!=null;
		} catch (IndivoNonFatalException e) {
			// result will be false
		}
		return result;
	}

	/**
	 * Builds a string like expected by the Indivo server when setting an account's user name and password.
	 * (system=password&username={username}&password={password})
	 * 
	 * @param accountId
	 * @param userName
	 * @param password
	 * @return	the built String
	 */
	private String getSetUserNameAndPasswordContent(String accountId,
			String userName, String password) {
		StringBuffer result=new StringBuffer();
		result.append("system=password&username=");
		result.append(TransportManager.urlEncode(userName));
		result.append("&password=");
		result.append(TransportManager.urlEncode(password));
		return result.toString();
	}

	/**
	 * Builds a string like expected by the Indivo server when creating a new account.
	 * (account_id={account_id}&contact_email={contact_email}&full_name={full_name}&primary_secret_p={0|1}&secondary_secret_p={0|1})
	 * 
	 * @param accountId
	 * @param contactEmail
	 * @param fullName
	 * @param primarySecret
	 * @param secondarySecret
	 * @return	the built string usable for create account calls
	 */
	public static String getCreateAccountContent(String accountId, String contactEmail, String fullName,
			boolean primarySecret, boolean secondarySecret) {
		StringBuffer result=new StringBuffer();
		result.append("account_id=");
		result.append(TransportManager.urlEncode(accountId));
		result.append("&contact_email=");
		result.append(TransportManager.urlEncode(contactEmail));
		result.append("&full_name=");
		result.append(TransportManager.urlEncode(fullName));
		result.append("&primary_secret=");
		result.append(primarySecret?"1":"0");
		result.append("&secondary_secret=");
		result.append(secondarySecret?"1":"0");
		return result.toString();
	}

}
