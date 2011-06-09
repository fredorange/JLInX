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
package com.orange.jlinx.document;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBElement;

import com.orange.jlinx.IndivoException;
import com.orange.jlinx.IndivoNonFatalException;
import com.orange.jlinx.QueryFilter;
import com.orange.jlinx.RecordId;
import com.orange.jlinx.ServiceManager;
import com.orange.jlinx.TransportManager;
import com.orange.jlinx.auth.AccessToken;
import com.orange.jlinx.document.ext.DocumentMeta;
import com.orange.jlinx.document.ext.DocumentStatus;
import com.orange.jlinx.document.ext.DocumentStatusHistory;
import com.orange.jlinx.document.ext.Reports;
import com.orange.jlinx.document.x.XDocumentMeta;
import com.orange.jlinx.document.x.XDocumentMetaList;
import com.orange.jlinx.document.x.XDocumentStatusHistory;
import com.orange.jlinx.document.x.XReports;
import com.orange.jlinx.document.x.XResultOk;

/**
 * DocumentManager is the entry point for document related services offered by Indivo.
 * This class is parameterized with the type of Document it is intended to manage.
 * 
 * @author dev-indivo@brialon.com
 *
 */
public class DocumentManager<D extends Document> {

  public static final String STATUS_ACTIVE = "active";
  public static final String STATUS_VOID = "void";
  public static final String STATUS_ARCHIVED = "archived";
  public static final String RELATION_TYPE_ANNOTATION = "annotation";
  public static final String ORDER_BY_MODIFIED_AT = "modified_at";
  public static final String ORDER_BY_MIME_TYPE = "mime_type";
  public static final String ORDER_BY_EXTERNAL_ID = "external_id";
  public static final String ORDER_BY_TYPE = "type";
  public static final String ORDER_BY_STATUS = "status";
  public static final String ORDER_BY_CREATOR = "creator";
  public static final String ORDER_BY_REPLACED_BY = "replaced_by";
  public static final String ORDER_BY_ID = "id";
  public static final String ORDER_BY_SIZE = "size";
  public static final String ORDER_BY_CREATED_AT = "created_at";
  public static final String ORDER_BY_ORIGINAL = "original";
  public static final String ORDER_BY_LABEL = "label";
  public static final String ORDER_BY_DIGEST = "digest";
  /**
   * Informations on the document type managed by this DocumentManager instance.
   */
  private DocumentType type;
  /**
   * DocumentManager mainly relies on a ServiceManager instance for interactions with Indivo.
   */
  private ServiceManager serviceManager;

  /**
   * Creates a new DocumentManager for the given DocumentType.
   * This method is intended to be used internally by the Indivo class.
   *
   * @param serviceManager	the ServiceManager for interacting with the Indivo server
   * @param type	the desired DocumentType
   */
  public DocumentManager(ServiceManager serviceManager, DocumentType type) {
    this.serviceManager = serviceManager;
    this.type = type;
  }

  /**
   * Post a document on the Indivo server.
   * Parameter accessToken may be null if isAppSpecific is true. In this case, it refers to an application only related document.
   *
   * @param accessToken	optional OAuth access token for authentication and record identification
   * @param document	the Document to post
   * @param isAppSpecific true if document is specific to current application
   * @return	a DocumentMeta object representing the response received from the Indivo server
   * @throws IndivoException	if a fatal error occur
   */
  public DocumentMeta postDocument(AccessToken accessToken, D document, boolean isAppSpecific) throws IndivoException {
    DocumentMeta result = null;
    RecordId recordId = null;
    if (accessToken != null) {
      recordId = accessToken.getRecordId();
    }
    String urlSuffix = this.serviceManager.getUrlBuilder().getDocument(recordId, isAppSpecific);
    JAXBElement<?> content = document.getXmlElement();
    try {
      XDocumentMeta xResult;
      if (content == null) {
        xResult = (XDocumentMeta) this.serviceManager.doPostRequest(urlSuffix, accessToken, document.toString());
      } else {
        xResult = (XDocumentMeta) this.serviceManager.doPostRequest(urlSuffix, accessToken, content);
      }
      result = DocumentFactory.getDocumentMeta(xResult);
    }
    catch (IndivoNonFatalException e) {
      // unwilling to happen, anyway result will be null
    }
    return result;
  }

  /**
   * Post a document to a record on the Indivo server, using a unique externalId.
   * Parameter accessToken may be null if isAppSpecific is true. In this case, it refers to an application only related document.
   *
   * @param accessToken	optional OAuth access token for authentication and record identification
   * @param document	the Document to post
   * @param externalId	the unique externalId associated to the document
   * @param isAppSpecific true if document is specific to current application
   * @return	a DocumentMeta object representing the response received from the Indivo server
   * @throws IndivoException	if a fatal error occur
   */
  public DocumentMeta postDocumentByExternalId(AccessToken accessToken, D document, String externalId, boolean isAppSpecific) throws IndivoException {
    DocumentMeta result = null;
    RecordId recordId = null;
    if (accessToken != null) {
      recordId = accessToken.getRecordId();
    }
    String urlSuffix = this.serviceManager.getUrlBuilder().getDocumentByExternalId(recordId, externalId, isAppSpecific);
    JAXBElement<?> content = document.getXmlElement();
    try {
      XDocumentMeta xResult;
      if (content == null) {
        xResult = (XDocumentMeta) this.serviceManager.doPutRequest(urlSuffix, accessToken, document.toString());
      } else {
        xResult = (XDocumentMeta) this.serviceManager.doPutRequest(urlSuffix, accessToken, content);
      }
      result = DocumentFactory.getDocumentMeta(xResult);
    }
    catch (IndivoNonFatalException e) {
      // unwilling to happen, anyway result will be null
    }
    return result;
  }

  /**
   * Replace a document on the Indivo server thanks to its documentId.
   * This method only applies to record related documents.
   *
   * @param accessToken	required OAuth access token for authentication and record identification
   * @param documentId	the documentId of document to replace
   * @param document	the Document to post
   * @return	a DocumentMeta object representing the response received from the Indivo server
   * or null if document was not found
   * @throws IndivoException	if a fatal error occur
   */
  public DocumentMeta replaceDocumentById(AccessToken accessToken, String documentId, D document) throws IndivoException {
    DocumentMeta result = null;
    String urlSuffix = this.serviceManager.getUrlBuilder().getReplaceDocumentById(accessToken.getRecordId(), documentId, false);
    JAXBElement<?> content = document.getXmlElement();
    try {
      XDocumentMeta xResult;
      if (content == null) {
        xResult = (XDocumentMeta) this.serviceManager.doPostRequest(urlSuffix, accessToken, document.toString());
      } else {
        xResult = (XDocumentMeta) this.serviceManager.doPostRequest(urlSuffix, accessToken, content);
      }
      result = DocumentFactory.getDocumentMeta(xResult);
    }
    catch (IndivoNonFatalException e) {
      // result will be null
    }
    return result;
  }

  /**
   * Get a document on the Indivo server thanks to its documentId.
   * Parameter accessToken may be null if isAppSpecific is true. In this case, it refers to an application only related document.
   *
   * @param accessToken	optional OAuth access token for authentication and record identification
   * @param documentId	the documentId of desired document
   * @param isAppSpecific true if document is specific to current application
   * @return	a Document instance representing the document received from the server
   * or null if document was not found
   * @throws IndivoException	if a fatal error occur
   */
  public D getDocumentById(AccessToken accessToken, String documentId, boolean isAppSpecific) throws IndivoException {
    return this.getDocumentById(accessToken, documentId, isAppSpecific, null);
  }

  /**
   * Get a document on the Indivo server thanks to its documentId.
   * Parameter accessToken may be null if isAppSpecific is true. In this case, it refers to an application only related document.
   *
   * @param accessToken	optional OAuth access token for authentication and record identification
   * @param documentId	the documentId of desired document
   * @param isAppSpecific true if document is specific to current application
   * @return	an object representing the document received from the server (as created by JAXB)
   * or null if document was not found
   * @throws IndivoException	if a fatal error occur
   */
  @SuppressWarnings("unchecked")
  private D getDocumentById(AccessToken accessToken, String documentId, boolean isAppSpecific, DocumentType type) throws IndivoException {
    D result = null;
    RecordId recordId = null;
    if (accessToken != null) {
      recordId = accessToken.getRecordId();
    }
    String urlSuffix = this.serviceManager.getUrlBuilder().getDocumentById(recordId, documentId, isAppSpecific);
    try {
      Object rawResult = this.serviceManager.doGetRequest(urlSuffix, accessToken, null);
      if (type == null) {
        result = (D) DocumentFactory.getDocument(rawResult);
      } else {
        result = (D) DocumentFactory.getDocument(rawResult, type.getDocumentClass());
      }
    }
    catch (IndivoNonFatalException e) {
      // result will be null
    }
    return result;
  }

  /**
   * Get the meta data of a document on the Indivo server thanks to its documentId.
   * Parameter accessToken may be null if isAppSpecific is true. In this case, it refers to an application only related document.
   *
   * @param accessToken	optional OAuth access token for authentication and record identification
   * @param documentId	the documentId of desired document
   * @param isAppSpecific true if document is specific to current application
   * @return	a DocumentMeta object representing the response received from the Indivo server
   * or null if document was not found
   * @throws IndivoException	if a fatal error occur
   */
  private DocumentMeta getDocumentMeta(AccessToken accessToken, String documentId, String externalId, boolean isAppSpecific) throws IndivoException {
    DocumentMeta result = null;
    RecordId recordId = null;
    if (accessToken != null) {
      recordId = accessToken.getRecordId();
    }
    String urlSuffix;
    if (externalId == null) {
      urlSuffix = this.serviceManager.getUrlBuilder().getDocumentMetaById(recordId, documentId, isAppSpecific);
    } else {
      urlSuffix = this.serviceManager.getUrlBuilder().getDocumentMetaByExternalId(recordId, externalId, isAppSpecific);
    }
    try {
      XDocumentMeta xResult = (XDocumentMeta) this.serviceManager.doGetRequest(urlSuffix, accessToken, null);
      result = DocumentFactory.getDocumentMeta(xResult);
    }
    catch (IndivoNonFatalException e) {
      // result will be null
    }
    return result;
  }

  /**
   * Get the meta data of a document on the Indivo server thanks to its documentId.
   * Parameter accessToken may be null if isAppSpecific is true. In this case, it refers to an application only related document.
   *
   * @param accessToken	optional OAuth access token for authentication and record identification
   * @param documentId	the documentId of desired document
   * @param isAppSpecific true if document is specific to current application
   * @return	a DocumentMeta object representing the response received from the Indivo server
   * or null if document was not found
   * @throws IndivoException	if a fatal error occur
   */
  public DocumentMeta getDocumentMetaById(AccessToken accessToken, String documentId, boolean isAppSpecific) throws IndivoException {
    return this.getDocumentMeta(accessToken, documentId, null, isAppSpecific);
  }

  /**
   * Get the meta data of a document on the Indivo server using a unique externalId.
   * Parameter accessToken may be null if isAppSpecific is true. In this case, it refers to an application only related document.
   *
   * @param accessToken	optional OAuth access token for authentication and record identification
   * @param externalId	the unique externalId of desired document
   * @param isAppSpecific true if document is specific to current application
   * @return	a DocumentMeta object representing the response received from the Indivo server
   * or null if document was not found
   * @throws IndivoException	if a fatal error occur
   */
  public DocumentMeta getDocumentMetaByExternalId(AccessToken accessToken, String externalId, boolean isAppSpecific) throws IndivoException {
    return this.getDocumentMeta(accessToken, null, externalId, isAppSpecific);
  }

  /**
   * Delete a document on the Indivo server using its documentId. This operation
   * will fail if other documents have been linked to the document in question, or if the document
   * has already been versioned. In this case, the method returns false.
   * This method only applies to record related documents.
   *
   * @param accessToken	required OAuth access token for authentication and record identification
   * @param documentId	the documentId of the document to be deleted
   * @param isAppSpecific true if document is specific to current application
   * @return	true if the operation succeeded
   * @throws IndivoException	if a fatal error occur
   */
  public boolean deleteDocumentById(AccessToken accessToken, String documentId, boolean isAppSpecific) throws IndivoException {
    boolean result = false;
    RecordId recordId = null;
    if (accessToken != null) {
      recordId = accessToken.getRecordId();
    }
    // TODO: when things improve on Indivo side, parameter "isAppSpecific" may replace the following "false"
    String urlSuffix = this.serviceManager.getUrlBuilder().getDocumentById(recordId, documentId, false);
    try {
      XResultOk xResult = (XResultOk) this.serviceManager.doDeleteRequest(urlSuffix, accessToken);
      result = xResult != null;
    }
    catch (IndivoNonFatalException e) {
      // result will be false
    }
    return result;
  }

  /**
   * Modifies the label value in the meta data of a document on the Indivo server thanks to its documentId.
   * This method only applies to record related and record-app related documents.
   *
   * @param accessToken	required OAuth access token for authentication and record identification
   * @param documentId	the documentId of desired document
   * @param label	the label value to set
   * @param isAppSpecific true if document is specific to current application
   * @return	a DocumentMeta object representing the response received from the Indivo server
   * or null if document was not found
   * @throws IndivoException	if a fatal error occur
   */
  public DocumentMeta setDocumentLabelById(AccessToken accessToken, String documentId, String label, boolean isAppSpecific) throws IndivoException {
    DocumentMeta result = null;
    RecordId recordId = null;
    if (accessToken != null) {
      recordId = accessToken.getRecordId();
    }
    String urlSuffix = this.serviceManager.getUrlBuilder().getUpdateDocumentLabelById(recordId, documentId, isAppSpecific);
    try {
      XDocumentMetaList xResult = (XDocumentMetaList) this.serviceManager.doPutRequest(urlSuffix, accessToken, label);
      result = DocumentFactory.getDocumentMeta(xResult.getDocument().get(0));
    }
    catch (IndivoNonFatalException e) {
      // result will be null
    }
    return result;
  }

  /**
   * Change the status of a document on the Indivo server using its documentId.
   * Correct status are DocumentManager.STATUS_ACTIVE, DocumentManager.STATUS_VOID and
   * DocumentManager.STATUS_ARCHIVED.
   * This method only applies to record related documents.
   *
   * @param accessToken	required OAuth access token for authentication and record identification
   * @param documentId	the documentId of the document which status is to be changed
   * @return	true if the operation succeeded
   * @throws IndivoException	if a fatal error occur
   */
  public boolean setDocumentStatusById(AccessToken accessToken, String documentId, String newStatus, String reason) throws IndivoException {
    boolean result = false;
    String urlSuffix = this.serviceManager.getUrlBuilder().getSetDocumentStatusById(accessToken.getRecordId(), documentId, false);
    String content = getSetStatusContent(reason, newStatus);
    try {
      XResultOk xResult = (XResultOk) this.serviceManager.doPostRequest(urlSuffix, accessToken, content);
      result = xResult != null;
    }
    catch (IndivoNonFatalException e) {
      // result will be false
    }
    return result;
  }

  /**
   * Get the status history of the document denoted by the given documentId. It is returned as a List
   * of the successive status the document has gotten.
   * This method only applies to record related documents.
   *
   * @param accessToken	required OAuth access token for authentication and record identification
   * @param documentId	the documentId of the document which status history is desired
   * @return	a list of DocumentStatus instances or null if document was not found
   * @throws IndivoException	if a fatal error occur
   */
  public List<DocumentStatus> getDocumentStatusHistoryById(AccessToken accessToken, String documentId) throws IndivoException {
    List<DocumentStatus> result = null;
    String urlSuffix = this.serviceManager.getUrlBuilder().getDocumentStatusHistoryById(accessToken.getRecordId(), documentId, false);
    try {
      XDocumentStatusHistory xHistory = (XDocumentStatusHistory) this.serviceManager.doGetRequest(urlSuffix, accessToken, null);
      DocumentStatusHistory history = DocumentFactory.getDocumentStatusHistory(xHistory);
      if (history != null) {
        result = history.getDocumentStatus();
      }
    }
    catch (IndivoNonFatalException e) {
      // result will be null
    }
    return result;
  }

  /**
   * Get the list of document meta data for all the documents attached to a record.
   * Warning: this method returns the meta data for all documents that has been posted to Indivo,
   * even if they are not valid (wrong XML syntax for instance). For a list of all the valid documents
   * of a given type, prefer the method getReports().
   * Parameter accessToken may be null if isAppSpecific is true.
   * In this case, it refers to an application only related document.
   *
   * @param accessToken	optional OAuth access token for authentication and record identification
   * @param filter optional filtering parameters
   * @param isAppSpecific true if document is specific to current application
   * @return	a list of DocumentMeta instances
   * @throws IndivoException	if a fatal error occur
   */
  public List<DocumentMeta> getAllDocumentsMeta(AccessToken accessToken,
          QueryFilter filter, boolean isAppSpecific) throws IndivoException {
    List<DocumentMeta> result = null;
    RecordId recordId = null;
    if (accessToken != null) {
      recordId = accessToken.getRecordId();
    }
    String urlSuffix = this.serviceManager.getUrlBuilder().getDocument(recordId, isAppSpecific);
    try {
      XDocumentMetaList xList = (XDocumentMetaList) this.serviceManager.doGetRequest(urlSuffix, accessToken, filter);
      result = DocumentFactory.getDocumentMetaList(xList);
    }
    catch (IndivoNonFatalException e) {
      // unwilling to happen, anyway result will be null
    }
    return result;
  }

  /**
   * Get the list of document meta data for all the documents attached to a record and which type is the one represented by the
   * implementing class. Warning: this method returns the meta data for all documents that has been posted to Indivo,
   * even if they are not valid (wrong XML syntax for instance). For a list of all the valid documents, prefer the method
   * getReportsForRecord().
   * Parameter accessToken may be null if isAppSpecific is true. In this case, it refers to an application only related document.
   *
   * @param accessToken	optional OAuth access token for authentication and record identification
   * @param filter optional filtering parameters
   * @param isAppSpecific true if document is specific to current application
   * @return	a list of DocumentMeta instances
   * @throws IndivoException	if a fatal error occur
   */
  public List<DocumentMeta> getAllDocumentsMetaByType(AccessToken accessToken, QueryFilter filter, boolean isAppSpecific) throws IndivoException {
    List<DocumentMeta> result = null;
    RecordId recordId = null;
    if (accessToken != null) {
      recordId = accessToken.getRecordId();
    }
    String urlSuffix = this.serviceManager.getUrlBuilder().getDocumentByType(recordId, this.type.getTypeUri(), isAppSpecific);
    try {
      XDocumentMetaList xList = (XDocumentMetaList) this.serviceManager.doGetRequest(urlSuffix, accessToken, filter);
      result = DocumentFactory.getDocumentMetaList(xList);
    }
    catch (IndivoNonFatalException e) {
      // unwilling to happen, anyway result will be null
    }
    return result;
  }

  /**
   * Get the list of all the documents attached to a record .
   * Warning: this method may return documents that are not considered valid by Indivo.
   * Parameter accessToken may be null if isAppSpecific is true.
   * In this case, it refers to an application only related document.
   *
   * @param accessToken	optional OAuth access token for authentication and record identification
   * @param isAppSpecific true if document is specific to current application
   * @param filter optional filtering parameters
   * @return	a list of Document instances representing the documents received from the server
   * @throws IndivoException	if a fatal error occur
   */
  public List<Document> getAllDocuments(AccessToken accessToken,
          QueryFilter filter, boolean isAppSpecific) throws IndivoException {
    List<Document> result = null;
    List<DocumentMeta> documentList = getAllDocumentsMeta(accessToken, filter, isAppSpecific);
    if (documentList != null) {
      result = new ArrayList<Document>();
      for (DocumentMeta meta : documentList) {
        String documentId = meta.getId();
        Document document = getDocumentById(accessToken, documentId, isAppSpecific);
        if (document != null) {
          result.add(document);
        }
      }
    }
    return result;
  }

  /**
   * Get the list of all the documents attached to a record and which type is the one represented by the
   * implementing class. Warning: this method may return documents that are considered as not valid by Indivo.
   * For a list of all the valid documents, prefer the method getReportsForRecord().
   * Parameter accessToken may be null if isAppSpecific is true. In this case, it refers to an application only related document.
   *
   * @param accessToken	optional OAuth access token for authentication and record identification
   * @param filter optional filtering parameters
   * @param isAppSpecific true if document is specific to current application
   * @return	a list of objects representing the documents received from the server, as created by JAXB
   * @throws IndivoException	if a fatal error occur
   */
  public List<D> getAllDocumentsByType(AccessToken accessToken, QueryFilter filter, boolean isAppSpecific) throws IndivoException {
    List<D> result = null;
    List<DocumentMeta> documentList = getAllDocumentsMetaByType(accessToken, filter, isAppSpecific);
    if (documentList != null) {
      result = new ArrayList<D>();
      for (DocumentMeta meta : documentList) {
        String documentId = meta.getId();
        D document = (D) this.getDocumentById(accessToken, documentId, isAppSpecific, type);
        if (document != null) {
          result.add(document);
        }
      }
    }
    return result;
  }

  /**
   * Get the list of reports for all the documents attached to a record and which type is the one represented by the
   * implementing class. This method returns only documents that are considered as valid by Indivo. A report consists in
   * the document itself plus its meta data.
   * This method only applies to record related documents.
   *
   * @param accessToken	required OAuth access token for authentication and record identification
   * @param filter optional filtering parameters
   * @return	a XReports instance representing the reports received by the Indivo server
   * @throws IndivoException	if a fatal error occur
   */
  @SuppressWarnings("unchecked")
  public Reports<D> getReports(AccessToken accessToken, QueryFilter filter) throws IndivoException {
    Reports<D> result = null;
    if (type.getReportName() != null) {
      String urlSuffix = this.serviceManager.getUrlBuilder().getReport(accessToken.getRecordId(), this.type.getReportName(), false);
      try {
        XReports xResult = (XReports) this.serviceManager.doGetRequest(urlSuffix, accessToken, filter);
        result = (Reports<D>) DocumentFactory.getReports(xResult, type.getDocumentClass());
      }
      catch (IndivoNonFatalException e) {
        // unwilling to happen, anyway result will be null
      }
    }
    return result;
  }

  /**
   * Create a relation between 2 documents using their ID.
   * Document designed by documentId is linked to the one represented by relatedDocumentId, with
   * given relation type.
   * This method only applies to record related documents.
   *
   * @param accessToken	required OAuth access token for authentication and record identification
   * @param documentId	the ID of the document originating the relation
   * @param relationType	the relation type (you may use constants DocumentManager.RELATION_TYPE_*)
   * @param relatedDocumentId	the ID of the document linked to the originating one
   * @return	true if operation succeeded
   * @throws IndivoException	if a fatal error occur
   */
  public boolean linkDocumentToExisting(AccessToken accessToken, String documentId, String relationType, String relatedDocumentId)
          throws IndivoException {
    boolean result = false;
    String urlSuffix = this.serviceManager.getUrlBuilder().getRelateDocumentToExistingById(
            accessToken.getRecordId(), documentId, relationType, relatedDocumentId, false);
    try {
      XResultOk xResult = (XResultOk) this.serviceManager.doPutRequest(urlSuffix, accessToken, (String) null);
      result = xResult != null;
    }
    catch (IndivoNonFatalException e) {
      // result will be false
    }
    return result;
  }

  /**
   * Create a relation between a documents and a new one.
   * Document designed by documentId is linked to the new one given as parameter.
   * This method only applies to record related documents.
   *
   * @param accessToken	required OAuth access token for authentication and record identification
   * @param documentId	the ID of the document originating the relation
   * @param relationType	the relation type (you may use constants DocumentManager.RELATION_TYPE_*)
   * @param document	the new document linked to the originating one
   * @return	true if operation succeeded
   * @throws IndivoException	if a fatal error occur
   */
  public boolean linkDocumentToNewOne(AccessToken accessToken, String documentId, String relationType, Document document)
          throws IndivoException {
    boolean result = false;
    String urlSuffix = this.serviceManager.getUrlBuilder().getDocumentRelationByTypeAndId(accessToken.getRecordId(), documentId, relationType, false);
    JAXBElement<?> content = document.getXmlElement();
    try {
      XResultOk xResult;
      if (content == null) {
        xResult = (XResultOk) this.serviceManager.doPostRequest(urlSuffix, accessToken, document.toString());
      } else {
        xResult = (XResultOk) this.serviceManager.doPostRequest(urlSuffix, accessToken, content);
      }
      result = xResult != null;
    }
    catch (IndivoNonFatalException e) {
      // result will be false
    }
    return result;
  }

  /**
   * Get documents related to the one represented by given documentId, with given relation type.
   * This method only applies to record related documents.
   *
   * @param accessToken	required OAuth access token for authentication and record identification
   * @param documentId	the ID of the document originating the relation
   * @param relationType	the relation type (you may use constants DocumentManager.RELATION_TYPE_*)
   * @param filter optional filtering parameters
   * @return	a List of DocumentMeta representing the related documents
   * @throws IndivoException
   */
  public List<DocumentMeta> getDocumentRelationsByType(AccessToken accessToken, String documentId, String relationType, QueryFilter filter)
          throws IndivoException {
    List<DocumentMeta> result = null;
    String urlSuffix = this.serviceManager.getUrlBuilder().getDocumentRelationByTypeAndId(accessToken.getRecordId(), documentId, relationType, false);
    try {
      XDocumentMetaList xList = (XDocumentMetaList) this.serviceManager.doGetRequest(urlSuffix, accessToken, filter);
      result = DocumentFactory.getDocumentMetaList(xList);
    }
    catch (IndivoNonFatalException e) {
      // result will be null
    }
    return result;
  }

  /**
   * Builds a string like expected by the Indivo server when changing a document status.
   *
   * @param reason
   * @param status
   * @return	the built string usable for set-status calls
   */
  private static String getSetStatusContent(String reason, String status) {
    return "reason=" + TransportManager.urlEncode(reason) + "&status=" + TransportManager.urlEncode(status);
  }
}
