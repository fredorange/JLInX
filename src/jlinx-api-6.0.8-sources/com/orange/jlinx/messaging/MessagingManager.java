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

package com.orange.jlinx.messaging;

import java.util.List;

import com.orange.jlinx.IndivoException;
import com.orange.jlinx.IndivoNonFatalException;
import com.orange.jlinx.RecordId;
import com.orange.jlinx.ServiceManager;
import com.orange.jlinx.TransportManager;
import com.orange.jlinx.auth.AccessToken;
import com.orange.jlinx.document.Document;
import com.orange.jlinx.document.x.XResultOk;

/**
 * MessagingManager allows a PHA to use the messaging services provided by the Indivo server.
 * 
 * @author dev-indivo@brialon.com
 *
 */
public class MessagingManager {

	public static final String SEVERITY_LOW="low";
	public static final String SEVERITY_MEDIUM="medium";
	public static final String SEVERITY_HIGH="high";
	
	/**
	 * MessagingManager mainly relies on a DocumentManager instance for interactions with Indivo.
	 */
	private ServiceManager documentManager;
	
	/**
	 * Used to ensure that 2 consecutive calls to getUniqueId() won't return the same value. 
	 */
	private Long previousUniqueId;
	
 	/**
 	 * Creates a new MessagingManager instance.
 	 * 
 	 * @param serviceManager	this will be used for interacting with the Indivo server
 	 */
 	public MessagingManager(ServiceManager serviceManager){
		this.documentManager=serviceManager;
		this.previousUniqueId=0L;
	}
	
 	/**
 	 * Send a message to a record's inbox.
 	 * 
	 * @param accessToken	required OAuth access token for authentication and record identification
 	 * @param recordId	identifies the destination record
 	 * @param subject	message subjects
 	 * @param body	message body
 	 * @param severity	message importance (please use constants MessagingManager.SEVERITY_*)
 	 * @param attachments	an optional list of attached documents, sent along with the message
	 * @return	true if operation succeeded
	 * @throws IndivoException	if a fatal error occur
 	 */
 	public boolean sendMessageToRecord(AccessToken accessToken, String recordId, String subject, String body,
 			String severity, List<Document> attachments) throws IndivoException{
		String messageId=getUniqueId();
 		RecordId builtInRecordId=new RecordId(recordId, null);
		String urlSuffix=this.documentManager.getUrlBuilder().getSendMessage(builtInRecordId, messageId, false);
 		String content=getSendMessageToRecordContent(subject, body, severity, attachments==null?0:attachments.size());
		boolean result=false;
		try {
			XResultOk xResult=(XResultOk)this.documentManager.doPostRequest(urlSuffix, accessToken, content);
			if(attachments!=null){
				for(int i=0; xResult!=null && i<attachments.size(); i++){
			 		urlSuffix=this.documentManager.getUrlBuilder().getSendMessageWithAttachment(builtInRecordId, messageId, i+1, false);
					xResult=(XResultOk)this.documentManager.doPostRequest(urlSuffix, accessToken, attachments.get(i).getXmlElement());
				}
			}
			result=xResult!=null;
		} catch (IndivoNonFatalException e) {
			// result will be false
		}
		return result;
	}

 	/**
 	 * Send a notification to a record's feed.
 	 * 
	 * @param accessToken	required OAuth access token for authentication and record identification
 	 * @param recordId	identifies the destination record
 	 * @param notificationContent	the text of the notification
 	 * @param relativeUrl	optional application relative URL
 	 * @param documentId	optional existing document ID
	 * @return	true if operation succeeded
	 * @throws IndivoException	if a fatal error occur
 	 */
 	public boolean sendNotificationToRecord(AccessToken accessToken, String recordId, String notificationContent, String relativeUrl, String documentId) throws IndivoException{
 		String urlSuffix=this.documentManager.getUrlBuilder().getSendNotification(new RecordId(recordId, null), false);
 		String content=getSendNotificationToRecordContent(notificationContent, relativeUrl, documentId);
		boolean result=false;
		try {
			XResultOk xResult=(XResultOk)this.documentManager.doPostRequest(urlSuffix, accessToken, content);
			result=xResult!=null;
		} catch (IndivoNonFatalException e) {
			// result will be false
		}
		return result;
	}

	/**
	 * Return a unique id to be used as a messageId when sending messages.
	 * 
	 * @return	a unique String
	 */
	private synchronized String getUniqueId() {
		Long result=System.currentTimeMillis();
		if(result==this.previousUniqueId)
			result++;
		this.previousUniqueId=result;
		return result.toString();
	}
	
	/**
	 * Builds a string like expected by the Indivo server when sending a message to a record.
	 * (subject={subject}&body={body}&severity={severity}&num_attachments={num_attachments})
	 * 
	 * @param subject
	 * @param body
	 * @param severity
	 * @param nbAttachments
	 * @return
	 */
	private String getSendMessageToRecordContent(String subject, String body, String severity, int nbAttachments) {
		StringBuffer result=new StringBuffer();
		result.append("subject=");
		result.append(TransportManager.urlEncode(subject));
		result.append("&body=");
		result.append(TransportManager.urlEncode(body));
		if(severity!=null){
			result.append("&severity=");
			result.append(TransportManager.urlEncode(severity));
		}
		if(nbAttachments>0)
			result.append("&num_attachments="+nbAttachments);
		return result.toString();
	}

	/**
	 * Builds a string like expected by the Indivo server when sending a notification to a record.
	 * (content={notification_content}&app_url={relative_url}&document_id={document_id})
	 * 
	 * @param notificationContent
	 * @param relativeUrl
	 * @param documentId
	 * @return
	 */
	private String getSendNotificationToRecordContent(String notificationContent, String relativeUrl, String documentId) {
		StringBuffer result=new StringBuffer();
		result.append("content=");
		result.append(TransportManager.urlEncode(notificationContent));
		if(relativeUrl!=null){
			result.append("&app_url=");
			result.append(TransportManager.urlEncode(relativeUrl));
		}
		if(documentId!=null){
			result.append("&document_id=");
			result.append(TransportManager.urlEncode(documentId));
		}
		return result.toString();
	}

}
