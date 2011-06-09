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

import java.net.URI;


/**
 * This class handles the construction of Indivo URL for server calls.
 * 
 * @author dev-indivo@brialon.com
 *
 */
public class ServiceUrlBuilder {

	private String appId;

	public ServiceUrlBuilder(String appId) {
		this.appId = appId;
	}

	/**
	 * records/
	 * 
	 * @return the Indivo server URL suffix for accessing given recordId 
	 */
	public String getRecord(){
		return "records/";
	}

	/**
	 * records/{record_id}
	 * 
	 * @param recordId
	 * @return the Indivo server URL suffix for accessing given recordId 
	 */
	public String getRecord(String recordId){
		return this.getRecord()+recordId;
	}

	/**
	 * carenets/{carenet_id}
	 * 
	 * @param carenetId
	 * @return the Indivo server URL suffix for accessing given carenetId 
	 */
	public String getCarenet(String carenetId){
		return "carenets/"+carenetId;
	}

	/**
	 * apps/{app_id}
	 * 
	 * @return the application suffix
	 */
	public String getApp() {
		return "apps/"+this.appId;
	}

	/**
	 * [records/{record_id}/|carenets/{carenet_id}/][apps/{app_id}/]
	 * 
	 * @param recordId	the optional record|carenet ID
	 * @param isAppSpecific true if suffix should include the /app/{app_id} part
	 * @return the Indivo server standard URL suffix for record|carenet|app related services
	 */
	private String getBase(RecordId recordId, boolean isAppSpecific){
		StringBuffer result=new StringBuffer();
		if(recordId!=null){
			if(recordId.isCarenet())
				result.append(this.getCarenet(recordId.getId()));
			else
				result.append(this.getRecord(recordId.getId()));
			result.append("/");
		}
		if(isAppSpecific){
			result.append(this.getApp());
			result.append("/");
		}
		return result.toString();
	}

	/**
	 * [records/{record_id}/|carenets/{carenet_id}/][apps/{app_id}/]documents/
	 * 
	 * @param recordId	the optional record|carenet ID
	 * @param isAppSpecific true if suffix should include the /app/{app_id} part
	 * @return the Indivo server URL suffix for accessing documents 
	 */
	public String getDocument(RecordId recordId, boolean isAppSpecific){
		return getBase(recordId, isAppSpecific)+"documents/";
	}
	
	/**
	 * [records/{record_id}/|carenets/{carenet_id}/][apps/{app_id}/]documents/?type={type_url}
	 * 
	 * @param recordId	the optional record|carenet ID
	 * @param documentTypeUri
	 * @param isAppSpecific true if suffix should include the /app/{app_id} part
	 * @return the Indivo server URL suffix for accessing documents of given type
	 */
	public String getDocumentByType(RecordId recordId, URI documentTypeUri, boolean isAppSpecific){
		return getDocument(recordId, isAppSpecific)+"?type="+TransportManager.urlEncode(documentTypeUri.toString());
	}

	/**
	 * [records/{record_id}/|carenets/{carenet_id}/][apps/{app_id}/]reports/minimal/{report-name}/
	 * 
	 * @param recordId	the optional record|carenet ID
	 * @param reportName
	 * @param isAppSpecific true if suffix should include the /app/{app_id} part
	 * @return the Indivo server URL suffix for accessing document reports of given name
	 */
	public String getReport(RecordId recordId, String reportName, boolean isAppSpecific){
		return getBase(recordId, isAppSpecific)+"reports/minimal/"+reportName+"/";
	}

	/**
	 * [records/{record_id}/|carenets/{carenet_id}/][apps/{app_id}/]documents/{document_id}
	 * 
	 * @param recordId	the optional record|carenet ID
	 * @param documentId
	 * @param isAppSpecific true if suffix should include the /app/{app_id} part
	 * @return the Indivo server URL suffix for accessing document having given documentId
	 */
	public String getDocumentById(RecordId recordId, String documentId, boolean isAppSpecific){
		return getDocument(recordId, isAppSpecific)+documentId;
	}

	/**
	 * [records/{record_id}/|carenets/{carenet_id}/][apps/{app_id}/]documents/{document_id}/meta
	 * 
	 * @param recordId	the optional record|carenet ID
	 * @param documentId
	 * @param isAppSpecific true if suffix should include the /app/{app_id} part
	 * @return the Indivo server URL suffix for accessing the meta data of document having given documentId
	 */
	public String getDocumentMetaById(RecordId recordId, String documentId, boolean isAppSpecific){
		return getDocumentById(recordId, documentId, isAppSpecific)+"/meta";
	}

	/**
	 * [records/{record_id}/|carenets/{carenet_id}/][apps/{app_id}/]documents/{document_id}/label
	 * 
	 * @param recordId	the optional record|carenet ID
	 * @param documentId
	 * @param isAppSpecific true if suffix should include the /app/{app_id} part
	 * @return the Indivo server URL suffix for updating the label of document having given documentId
	 */
	public String getUpdateDocumentLabelById(RecordId recordId, String documentId, boolean isAppSpecific){
		return getDocumentById(recordId, documentId, isAppSpecific)+"/label";
	}

	/**
	 * [records/{record_id}/|carenets/{carenet_id}/][apps/{app_id}/]documents/{document_id}/replace
	 * 
	 * @param recordId	the optional record|carenet ID
	 * @param documentId
	 * @param isAppSpecific true if suffix should include the /app/{app_id} part
	 * @return the Indivo server URL suffix for replacing the document having given documentId
	 */
	public String getReplaceDocumentById(RecordId recordId, String documentId, boolean isAppSpecific){
		return getDocumentById(recordId, documentId, isAppSpecific)+"/replace";
	}

	/**
	 * [records/{record_id}/|carenets/{carenet_id}/][apps/{app_id}/]documents/{document_id}/set-status
	 * 
	 * @param recordId	the optional record|carenet ID
	 * @param documentId
	 * @param isAppSpecific true if suffix should include the /app/{app_id} part
	 * @return the Indivo server URL suffix for setting status of document having given documentId
	 */
	public String getSetDocumentStatusById(RecordId recordId, String documentId, boolean isAppSpecific) {
		return getDocumentById(recordId, documentId, isAppSpecific)+"/set-status";
	}

	/**
	 * [records/{record_id}/|carenets/{carenet_id}/][apps/{app_id}/]documents/{document_id}/status-history
	 * 
	 * @param recordId	the optional record|carenet ID
	 * @param documentId
	 * @param isAppSpecific true if suffix should include the /app/{app_id} part
	 * @return the Indivo server URL suffix for getting status history of the document having given documentId
	 */
	public String getDocumentStatusHistoryById(RecordId recordId, String documentId, boolean isAppSpecific) {
		return getDocumentById(recordId, documentId, isAppSpecific)+"/status-history";
	}

	/**
	 * [records/{record_id}/|carenets/{carenet_id}/][apps/{app_id}/]documents/{document_id}/rels/{rel_type}/
	 * 
	 * @param recordId	the optional record|carenet ID
	 * @param documentId
	 * @param relationType
	 * @param isAppSpecific true if suffix should include the /app/{app_id} part
	 * @return the Indivo server URL suffix for relating a document to a new one
	 */
	public String getDocumentRelationByTypeAndId(RecordId recordId, String documentId, String relationType, boolean isAppSpecific) {
		return getDocumentById(recordId, documentId, isAppSpecific)+"/rels/"+relationType+"/";
	}

	/**
	 * [records/{record_id}/|carenets/{carenet_id}/][apps/{app_id}/]documents/{document_id}/rels/{rel_type}/{other_document_id}
	 * 
	 * @param recordId	the optional record|carenet ID
	 * @param documentId
	 * @param relationType
	 * @param otherDocumentId 
	 * @param isAppSpecific true if suffix should include the /app/{app_id} part
	 * @return the Indivo server URL suffix for relating a document to an existing one
	 */
	public String getRelateDocumentToExistingById(RecordId recordId, String documentId, String relationType, String otherDocumentId, boolean isAppSpecific) {
		return getDocumentRelationByTypeAndId(recordId, documentId, relationType, isAppSpecific)+otherDocumentId;
	}

	/**
	 * /accounts/
	 * 
	 * @return the Indivo server URL suffix for accounts
	 */
	public String getAccount() {
		return "accounts/";
	}

	/**
	 * [records/{record_id}/|carenets/{carenet_id}/][apps/{app_id}/]notify
	 * 
	 * @param recordId
	 * @return the Indivo server URL suffix for sending a notification to a record
	 */
	public String getSendNotification(RecordId recordId, boolean isAppSpecific) {
		return getBase(recordId, isAppSpecific)+"notify";
	}

	/**
	 * [records/{record_id}/|carenets/{carenet_id}/][apps/{app_id}/]inbox/{message_id}
	 * 
	 * @param recordId
	 * @param messageId
	 * @return the Indivo server URL suffix for sending a message to a record
	 */
	public String getSendMessage(RecordId recordId, String messageId, boolean isAppSpecific) {
		return getBase(recordId, isAppSpecific)+"inbox/"+messageId;
	}

	/**
	 * [records/{record_id}/|carenets/{carenet_id}/][apps/{app_id}/]inbox/{message_id}/attachments/{attachment_num}
	 * 
	 * @param recordId
	 * @param messageId
	 * @return the Indivo server URL suffix for sending a message attachment to a record
	 */
	public String getSendMessageWithAttachment(RecordId recordId, String messageId, int attachmentNum, boolean isAppSpecific) {
		return getSendMessage(recordId, messageId, isAppSpecific)+"/attachments/"+attachmentNum;
	}

	/**
	 * records/{record_id}/owner
	 * 
	 * @param recordId
	 * @return	the Indivo server URL suffix for setting a record owner
	 */
	public String getRecordOwner(String recordId) {
		return getRecord(recordId)+"/owner";
	}

	/**
	 * /accounts/{account_id}
	 * 
	 * @param accountId 
	 * @return the Indivo server URL suffix for given account ID
	 */
	public String getAccountById(String accountId) {
		return getAccount()+accountId;
	}

	/**
	 * /accounts/{account_id}/authsystems/
	 * 
	 * @param accountId 
	 * @return the Indivo server URL suffix for auth systems of given account ID
	 */
	public String getAccountAuthSystems(String accountId) {
		return getAccountById(accountId)+"/authsystems/";
	}
	
	/**
	 * /accounts/{account_id}/reset
	 * 
	 * @param accountId 
	 * @return the Indivo server URL suffix for resetting given account ID
	 */
	public String getAccountReset(String accountId) {
		return getAccountById(accountId)+"/reset";
	}
	
	/**
	 * external/{app_id}/{external_id}
	 * 
	 * @param externalId
	 * @param isAppSpecific 
	 * @return the ending externalId part used in an Indivo API URL
	 */
	private String getExternalIdPart(String externalId, boolean isAppSpecific) {
		String appId=this.appId+"/";
		if(isAppSpecific)
			appId="";
		return "external/"+appId+externalId;
	}

	/**
	 * [records/{record_id}/|carenets/{carenet_id}/][apps/{app_id}/]documents/external/[{app_id}/]{external_id}
	 * 
	 * @param recordId	the optional record|carenet ID
	 * @param externalId	the unique externalId
	 * @param isAppSpecific true if suffix should include the /app/{app_id} part
	 * @return the Indivo server URL suffix for accessing documents by external ID 
	 */
	public String getDocumentByExternalId(RecordId recordId, String externalId, boolean isAppSpecific){
		return getDocument(recordId, isAppSpecific)+this.getExternalIdPart(externalId, isAppSpecific);
	}
	
	/**
	 * [records/{record_id}/|carenets/{carenet_id}/][apps/{app_id}/]documents/external/[{app_id}/]{external_id}/meta
	 * 
	 * @param recordId	the optional record|carenet ID
	 * @param externalId	the unique externalId
	 * @param isAppSpecific true if suffix should include the /app/{app_id} part
	 * @return the Indivo server URL suffix for accessing the meta data of document having given documentId
	 */
	public String getDocumentMetaByExternalId(RecordId recordId, String externalId, boolean isAppSpecific){
		return getDocumentByExternalId(recordId, externalId, isAppSpecific)+"/meta";
	}

	/**
	 * [records/{record_id}/|carenets/{carenet_id}/][apps/{app_id}/]documents/{document_id}/replace/external/[{app_id}/]{external_id}
	 * 
	 * @param recordId	the optional record|carenet ID
	 * @param documentId	the Id of document to replace
	 * @param externalId	the unique externalId
	 * @param isAppSpecific true if suffix should include the /app/{app_id} part
	 * @return the Indivo server URL suffix for replacing the document having given documentId
	 */
	public String getReplaceDocumentByExternalId(RecordId recordId, String documentId, String externalId, boolean isAppSpecific){
		return getReplaceDocumentById(recordId, documentId, isAppSpecific)+"/"+this.getExternalIdPart(externalId, isAppSpecific);
	}

}
