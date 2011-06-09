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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.logging.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.orange.jlinx.auth.AccessToken;

/**
 * The ServiceManager class is responsible for giving access to Indivo API services:<br/>
 * - getting URL of Indivo services,<br/>
 * - sending and receiving content from/to Indivo server,<br/>
 * - transforming data objects into XML and vice versa.<br/>
 * The ServiceManager is intended to be created internally by an Indivo instance and to rely on a TransportManager object. 
 * 
 * @author dev-indivo@brialon.com
 *
 */
public class ServiceManager {
	
	private TransportManager transportManager;
	private static Marshaller marshaller;
	private static Unmarshaller unmarshaller;
	private Logger logger;
	private ServiceUrlBuilder urlBuilder;
	
	private static final String XML_SCHEMA_PACKAGE_NAME = "com.orange.jlinx.document.x";
	
	/**
 	 * Creates a new ServiceManager instance, relying on given TransportManager object for communications
 	 * with the Indivo server.
 	 * 
 	 * @param transportManager	a TransportManager object initialized with the desired Indivo server information
 	 * @param appId	the appId of the client PHA
 	 * @throws IndivoException	if a fatal error occur
 	 */
 	public ServiceManager(TransportManager transportManager, String appId) throws IndivoException{
		this.transportManager=transportManager;
		this.urlBuilder=new ServiceUrlBuilder(appId);
		if(ServiceManager.marshaller==null && ServiceManager.unmarshaller==null){
			try {
				JAXBContext jaxbContext = JAXBContext.newInstance(XML_SCHEMA_PACKAGE_NAME);
				ServiceManager.marshaller = jaxbContext.createMarshaller();
				ServiceManager.marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				ServiceManager.unmarshaller = jaxbContext.createUnmarshaller();
			} catch (JAXBException e) {
				throw new IndivoException("Error when initializing JAXB engine !",e);
			}
		}
		this.logger=Logger.getLogger(this.getClass().toString());
	}

	/**
	 * @return the urlBuilder
	 */
	public ServiceUrlBuilder getUrlBuilder() {
		return this.urlBuilder;
	}

	/**
	 * Perform a GET request at given URL suffix on the Indivo server, using the given access token
	 * for recordId reference and OAuth request signing.
	 *  
	 * @param urlSuffix	the URL suffix identifying desired Indivo service
	 * @param accessToken	the AccessToken object for authenticating and identifying the target record
	 * @param filter	optional filter parameters
	 * @return	an object representing the response received from the Indivo server
	 * @throws IndivoException	if any fatal error occur or a non fatal HTTP error is received by the server
     * @throws IndivoNonFatalException if the server raises an error for a functional reason rather than technical (i.e documentId doesn't exist) 
	 */
	public Object doGetRequest(String urlSuffix, AccessToken accessToken, QueryFilter filter) throws IndivoException, IndivoNonFatalException {
		String queryString="";
		if(filter!=null)
			queryString=getFilterQueryString(filter,urlSuffix.contains("?"));
		byte[] response=this.transportManager.fetchContent(urlSuffix+queryString, accessToken, TransportManager.METHOD_GET);
		return this.unmarshalDocument(response);
	}

	/**
	 * Perform a POST request at given URL suffix on the Indivo server, using the given access token
	 * for recordId reference and OAuth request signing. Parameter document represents the XML document
	 * to send along with the POST request.
	 *  
	 * @param urlSuffix	the URL suffix identifying desired Indivo service
	 * @param accessToken	the AccessToken object for authenticating and identifying the target record 
	 * @param document	a JAXBElement representing the XML response to send to the Indivo server 
	 * @return	an object representing the response received from the Indivo server
	 * @throws IndivoException	if any fatal error occur or a non fatal HTTP error is received by the server
     * @throws IndivoNonFatalException if the server raises an error for a functional reason rather than technical (i.e documentId doesn't exist) 
	 */
	public Object doPostRequest(String urlSuffix, AccessToken accessToken, JAXBElement<?> document) throws IndivoException, IndivoNonFatalException {
		String xmlDocument=marshalDocument(document);
		byte[] response=this.transportManager.fetchContent(urlSuffix, accessToken, TransportManager.METHOD_POST, xmlDocument, TransportManager.CONTENT_TYPE_XML);
		return this.unmarshalDocument(response);
	}

	/**
	 * Perform a POST request at given URL suffix on the Indivo server, using the given access token
	 * for recordId reference and OAuth request signing. Parameter content is sent along with the POST request,
	 * as plain text. Note that the content must be encoded like the parameters of a URL.
	 *  
	 * @param urlSuffix	the URL suffix identifying desired Indivo service
	 * @param accessToken	the AccessToken object for authenticating and identifying the target record 
	 * @param content	a String to send to the Indivo server 
	 * @return	an object representing the response received from the Indivo server
	 * @throws IndivoException	if any fatal error occur or a non fatal HTTP error is received by the server
     * @throws IndivoNonFatalException if the server raises an error for a functional reason rather than technical (i.e documentId doesn't exist) 
	 */
	public Object doPostRequest(String urlSuffix, AccessToken accessToken, String content) throws IndivoException, IndivoNonFatalException {
		byte[] response=this.transportManager.fetchContent(urlSuffix, accessToken, TransportManager.METHOD_POST, content, TransportManager.CONTENT_TYPE_TEXT_PLAIN);
		return this.unmarshalDocument(response);
	}

	/**
	 * Perform a PUT request at given URL suffix on the Indivo server, using the given access token
	 * for recordId reference and OAuth request signing. Parameter content is sent along with the PUT request,
	 * as plain text. Note that the content must be encoded like the parameters of a URL.
	 *  
	 * @param urlSuffix	the URL suffix identifying desired Indivo service
	 * @param accessToken	the AccessToken object for authenticating and identifying the target record 
	 * @param content	a String to send to the Indivo server 
	 * @return	an object representing the response received from the Indivo server
	 * @throws IndivoException	if any fatal error occur or a non fatal HTTP error is received by the server
     * @throws IndivoNonFatalException if the server raises an error for a functional reason rather than technical (i.e documentId doesn't exist) 
	 */
	public Object doPutRequest(String urlSuffix, AccessToken accessToken, String content) throws IndivoException, IndivoNonFatalException {
		byte[] response=this.transportManager.fetchContent(urlSuffix, accessToken, TransportManager.METHOD_PUT, content, TransportManager.CONTENT_TYPE_TEXT_PLAIN);
		return this.unmarshalDocument(response);
	}

	/**
	 * Perform a PUT request at given URL suffix on the Indivo server, using the given access token
	 * for recordId reference and OAuth request signing. Parameter document represents the XML document
	 * to send along with the POST request.
	 *  
	 * @param urlSuffix	the URL suffix identifying desired Indivo service
	 * @param accessToken	the AccessToken object for authenticating and identifying the target record 
	 * @param document	a JAXBElement representing the XML response to send to the Indivo server 
	 * @return	an object representing the response received from the Indivo server
	 * @throws IndivoException	if any fatal error occur or a non fatal HTTP error is received by the server
     * @throws IndivoNonFatalException if the server raises an error for a functional reason rather than technical (i.e documentId doesn't exist) 
	 */
	public Object doPutRequest(String urlSuffix, AccessToken accessToken, JAXBElement<?> document) throws IndivoException, IndivoNonFatalException {
		String xmlDocument=marshalDocument(document);
		byte[] response=this.transportManager.fetchContent(urlSuffix, accessToken, TransportManager.METHOD_PUT, xmlDocument, TransportManager.CONTENT_TYPE_XML);
		return this.unmarshalDocument(response);
	}

	/**
	 * Perform a DELETE request at given URL suffix on the Indivo server, using the given access token
	 * for recordId reference and OAuth request signing.
	 *  
	 * @param urlSuffix	the URL suffix identifying desired Indivo service
	 * @param accessToken	the AccessToken object for authenticating and identifying the target record 
	 * @return	an object representing the response received from the Indivo server
	 * @throws IndivoException	if any fatal error occur or a non fatal HTTP error is received by the server
     * @throws IndivoNonFatalException if the server raises an error for a functional reason rather than technical (i.e documentId doesn't exist) 
	 */
	public Object doDeleteRequest(String urlSuffix, AccessToken accessToken) throws IndivoException, IndivoNonFatalException {
		byte[] response=this.transportManager.fetchContent(urlSuffix, accessToken, TransportManager.METHOD_DELETE, null, null);
		return this.unmarshalDocument(response);
	}

	/**
	 * Transform an object representing a XML document data into its XML representation.
	 * To be complient with Indivo, this method doesn't generate the namespace prefixes.
	 *  
	 * @param document	the document to be marshalled
	 * @return	a String representation in XML format of given document
	 * @throws IndivoException	if a fatal error occur
	 */
	private String marshalDocument(JAXBElement<?> document) throws IndivoException {
		String result=null;
		try {
			ByteArrayOutputStream output=new ByteArrayOutputStream();
			synchronized (ServiceManager.marshaller) {
				ServiceManager.marshaller.marshal(document, output);
			}
			result=output.toString();
		} catch (JAXBException e) {
			throw new IndivoException("Error when marshalling the following document "+document.toString()+" !", e);
		}
		// patch: the result is filtered in order to remove namespace prefixes from the resulting XML
		// since Indivo does'nt like them very much
		// dirty solution but no easy workaround using Java XML API 
		return removeXmlNamespacePrefix(result);
	}
	
	/**
	 * Transform a XML document into a Java object representing its data.
	 *  
	 * @param content	the XML document to be unmarshalled, as a byte array
	 * @return	an instance of the Java class intended to handle data of the given XML document
	 * @throws IndivoException	if a fatal error occur
	 */
	/**
	 * @param content
	 * @return
	 * @throws IndivoException
	 */
	private Object unmarshalDocument(byte[] content) throws IndivoException {
		Object result=null;
		try {
			ByteArrayInputStream input=new ByteArrayInputStream(content);
			synchronized (ServiceManager.unmarshaller) {
				result=ServiceManager.unmarshaller.unmarshal(input);
			}
			// patch: when the unmarshaller return JAXBElement objects, get the value of the JAXBElement
			if(result!=null && result instanceof JAXBElement<?>)
				result=((JAXBElement<?>)result).getValue();
			logger.finer("Unmarshalled object of type "+result.getClass().getName());
		} catch (JAXBException e) {
			result=new String(content);
		}
		return result;
	}
	
	/**
	 * @param xmlDocument
	 * @return	the given XML document without the "ns2:" kind of namespace prefixes
	 */
	private String removeXmlNamespacePrefix(String xmlDocument){
		return xmlDocument.replaceAll("(</?)\\w*:|(xmlns):\\w*(=)", "$1$2$3");
	}
	
	
	/**
	 * Transforms a QueryFilter in a String of this kind:
	 * ?offset={offset}&limit={limit}&order_by={order_by}&status={document_status}&modified_since={modified_since}
	 * 
	 * @param filter	the filter to transform
	 * @param append	true if this string is willing to be added to existing URL parameters, and therefore do not start with "?"
	 * @return	the filter as a String usable in a URL
	 */
	private String getFilterQueryString(QueryFilter filter, boolean append){
		boolean isFirstParameter=!append;
		StringBuilder builder = new StringBuilder();
		if(filter.getOffset()!=0){
			builder.append(isFirstParameter?"?":"&");
			builder.append("offset=");
			builder.append(filter.getOffset());
			isFirstParameter=false;
		}
		if(filter.getLimit()!=0){
			builder.append(isFirstParameter?"?":"&");
			builder.append("limit=");
			builder.append(filter.getLimit());
			isFirstParameter=false;
		}
		if(filter.getOrderBy()!=null) {
			builder.append(isFirstParameter?"?":"&");
			builder.append("order_by=");
			if(filter.getDescendingOrder())
				builder.append("-");
			builder.append(filter.getOrderBy());
			isFirstParameter=false;
		}
		if(filter.getStatus()!=null) {
			builder.append(isFirstParameter?"?":"&");
			builder.append("status=");
			builder.append(filter.getStatus());
			isFirstParameter=false;
		}
		// TODO: manage modified_since
		return builder.toString();
	}
}
