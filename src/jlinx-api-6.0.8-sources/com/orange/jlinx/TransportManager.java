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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.logging.Logger;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.basic.DefaultOAuthConsumer;
import oauth.signpost.exception.OAuthException;

import com.orange.jlinx.auth.AccessToken;

/**
 * This class in internal to the JLInX API. It performs all communications with the Indivo server at raw level.
 * 
 * @author dev-indivo@brialon.com
 *
 */
public class TransportManager {

  private String consumerKey;
  private String consumerSecret;
  private URL indivoServerUrl;
  private Logger logger;
  private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(TransportManager.class);
  /**
   *  The following constants define very general values, slightly not subject to change
   */
  public static final String METHOD_GET = "GET";
  public static final String METHOD_POST = "POST";
  public static final String METHOD_PUT = "PUT";
  public static final String METHOD_DELETE = "DELETE";
  public static final String PROPERTY_CONTENT_TYPE = "Content-Type";
  public static final String CONTENT_TYPE_TEXT_PLAIN = "text/plain";
  public static final String CONTENT_TYPE_XML = "application/xml";
  private static final String DEFAULT_CONTENT_TYPE = CONTENT_TYPE_TEXT_PLAIN;
  private static final String DOC_BUGGY_START = "<?xml version=\"1.0\" encoding=\"UTF-16";
  private static final String DOC_WORKAROUND_START = "<?xml version=\"1.0\" encoding=\"UTF-8";
  private static final String STANDARD_ENCODING = "UTF-8";
  private static final int BUFFER_SIZE = 64 * 1024;

  /**
   * Creates a Transport object using main configuration parameters.
   * This method is intended to be used by the Indivo class.
   *
   * @param consumerKey	the consumer key of the application
   * @param consumerSecret	the consumer secret of the application
   * @param indivoServerUrl	the URL of the Indivo server
   * @throws IndivoException	if any fatal error occur
   */
  public TransportManager(String consumerKey, String consumerSecret, URL indivoServerUrl) throws IndivoException {
    this.consumerKey = consumerKey;
    this.consumerSecret = consumerSecret;
    this.indivoServerUrl = indivoServerUrl;
    this.logger = Logger.getLogger(this.getClass().toString());
  }

  /**
   * @return	the URL of the Indivo server
   */
  public URL getIndivoServerUrl() {
    return indivoServerUrl;
  }

  /**
   * This method is used for 2-legged oAuth process.
   *
   * @return	a brand new OAuthConsumer instance, encapsulating consumer info
   */
  public OAuthConsumer getNewOAuthConsumer() {
    return new DefaultOAuthConsumer(this.consumerKey, this.consumerSecret);
  }

  /**
   * This method is used for 3-legged oAuth process.
   * If given token is null, then this method is equivalent to a 2-legged auth.
   *
   * @param token	the access token which info is to be encapsulated in the result
   * @return	a brand new OAuthConsumer instance, encapsulating consumer info plus
   * the access token info given in parameter
   */
  public OAuthConsumer getNewOAuthConsumer(AccessToken token) {
    OAuthConsumer result = this.getNewOAuthConsumer();
    if (token != null) {
      result.setTokenWithSecret(token.getToken(), token.getTokenSecret());
    }
    return result;
  }

  /**
   * This method performs an authenticated request to the Indivo server and returns the response as is.
   *
   * @param urlSufix	the URL of the request (without the protocol and host which are already configured at construction)
   * @param accessToken	an access token previously provided by the Indivo server after a successful authentication
   * @param httpMethod	the HTTP method used for the request (POST, GET, ...)
   * @return	the raw content as sent by server in response
   * @throws IndivoException	if any fatal error occur or a non fatal HTTP error is received by the server
   * @throws IndivoNonFatalException if the server raises an error for a functional reason rather than technical (i.e documentId doesn't exist)
   */
  public byte[] fetchContent(
          String urlSufix,
          AccessToken accessToken,
          String httpMethod) throws IndivoException, IndivoNonFatalException {
    return fetchContent(urlSufix, accessToken, httpMethod, null, null);
  }

  /**
   * This method performs an authenticated request to the Indivo server and returns the response as is.
   * Content can optionally be sent to the server along with the request.
   *
   * @param urlSufix	the URL of the request (without the protocol and host which are already configured at construction)
   * @param accessToken	an access token previously provided by the Indivo server after a successful authentication
   * @param httpMethod	the HTTP method used for the request (POST, GET, ...)
   * @param contentToSend	if not null, this content is sent to the server along with the request
   * @param contentType	the MIME type of the content to send
   * @return	the raw content as sent by server in response
   * @throws IndivoException	if any fatal error occur or a non fatal HTTP error is received by the server
   * @throws IndivoNonFatalException if the server raises an error for a functional reason rather than technical (i.e documentId doesn't exist)
   */
  public byte[] fetchContent(
          String urlSufix,
          AccessToken accessToken,
          String httpMethod,
          String contentToSend,
          String contentType)
          throws IndivoException, IndivoNonFatalException {

    // build the URL and connect to it
    URL url;
    try {
      url = new URL(this.indivoServerUrl + urlSufix);
      logger.fine("<- HTTP Request: " + httpMethod + " " + url);
    }
    catch (MalformedURLException e) {
      String errorMessage = "Error: wrong format of URL to get Indivo document ! (" + this.indivoServerUrl + urlSufix + ")";
      logger.severe(errorMessage);
      log.error(errorMessage, e);
      throw new IndivoException(errorMessage, e);
    }
    HttpURLConnection request = null;
    StringBuilder sb = new StringBuilder("HTTP Request:\n");
    try {
      // sign the request
      request = (HttpURLConnection) url.openConnection();
      request.setRequestMethod(httpMethod);
      OAuthConsumer consumer = this.getNewOAuthConsumer(accessToken);
      consumer.sign(request);
      sb.append(" url    = " + url + "\n");
      sb.append(" method = " + httpMethod + "\n");
      // if a document must be sent
      if (contentToSend != null) {
        logger.fine(contentType + "\n" + contentToSend);
        request.setRequestProperty(PROPERTY_CONTENT_TYPE, contentType);
        request.setDoOutput(true);
        OutputStream os = request.getOutputStream();
        os.write(contentToSend.getBytes());
        os.close();
      } else {
        request.setRequestProperty(PROPERTY_CONTENT_TYPE, DEFAULT_CONTENT_TYPE);
      }

      sb.append(" content = " + contentToSend + "\n");
      log.debug(sb.toString());
      // send the request
      request.connect();

      int responseCode = request.getResponseCode();
      logger.fine("-> HTTP Response code: " + responseCode);
      log.debug("-> HTTP Response code: " + responseCode);
      // case of non-fatal exception (not found)
      if (responseCode == HttpURLConnection.HTTP_NOT_FOUND) {
        String errorMessage = "Error: received response code " + responseCode + " (not found) from Indivo !";
        String streamContent = getStreamContent(request.getErrorStream());
        String msg = errorMessage + " (" + streamContent + ")";
        log.error(msg);
        throw new IndivoNonFatalException(responseCode);
      }
      if (responseCode != HttpURLConnection.HTTP_OK) {
        String errorMessage = "Error: received response code " + responseCode + " from Indivo !";
        String streamContent = getStreamContent(request.getErrorStream());
        logger.severe(errorMessage + " (" + streamContent + ")");
        log.error(errorMessage + " (" + streamContent + ")");
        throw new IndivoException(errorMessage);
      }
    }
    catch (IOException e) {
      String errorMessage = "IOException caught: " + e.getMessage();
      log.error(errorMessage, e);
      throw new IndivoException(errorMessage, e);
    }
    catch (OAuthException e) {
      String errorMessage = "Error: can't perform Indivo OAuth authentication !";
      logger.severe(errorMessage);
      log.error(errorMessage, e);
      throw new IndivoException(errorMessage, e);
    }

    return getContentFromConnection(request);
  }

  /**
   * This method fetches HTTP content from an already established (and optionally signed) HTTP connection.
   * It takes into account any specificity of the Indivo server.
   *
   * @param connection	an established (and optionally signed) HTTP connection
   * @return	the raw content read from the connection
   * @throws IndivoException	if a fatal error occur
   */
  private byte[] getContentFromConnection(HttpURLConnection connection) throws IndivoException {
    ByteArrayOutputStream output = new ByteArrayOutputStream();
    try {
      // define input and output
      InputStream input = connection.getInputStream();

      // patch: special filter for Indivo buggy doc start
      byte[] start = new byte[DOC_BUGGY_START.length()];
      int startLength = input.read(start);
      String startString = new String(start, 0, startLength);
      if (startString.equals(DOC_BUGGY_START)) {
        // buggy start found, replace with workaround
        byte[] goodStart = DOC_WORKAROUND_START.getBytes();
        output.write(goodStart);
      } else {
        output.write(start, 0, startLength);
      }

      // fast read remaining of doc
      byte[] remainingBytes = readFullStream(input);
      output.write(remainingBytes);
    }
    catch (IOException e) {
      String errorMessage = "Error reading HTTP response content from Indivo !";
      if (connection != null) {
        String streamContent = getStreamContent(connection.getErrorStream());
        logger.severe(errorMessage + " (" + streamContent + ")");
        log.error(errorMessage + " (" + streamContent + ")");
      }
      throw new IndivoException(errorMessage, e);
    }
    logger.fine("-> HTTP Response:\n" + output.toString());
    log.debug("-> HTTP Response:\n" + output.toString());
    return output.toByteArray();
  }

  /**
   * Encode a String for using as URL parameter. Input string can be null.
   *
   * @param string	the string to encode (can be null)
   * @return	the given string, encoded for using as URL parameter, or null if parameter is null
   */
  public static String urlEncode(String string) {
    String result = null;
    try {
      result = string == null ? null : URLEncoder.encode(string, STANDARD_ENCODING);
    }
    catch (UnsupportedEncodingException e) {
      e.printStackTrace();
      log.error(e);
    }
    return result;
  }

  /**
   * This method reads given stream until its end.
   *
   * @param input	the InputStream to read data from
   * @return	the data read from the stream as a byte array
   * @throws IOException
   */
  public static byte[] readFullStream(InputStream input) throws IOException {
    byte[] result = null;
    if (input != null) {
      InputStream in = input;
      try {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] buffer = new byte[BUFFER_SIZE];
        int size;
        while ((size = in.read(buffer)) > 0) {
          output.write(buffer, 0, size);
        }
        result = output.toByteArray();
      }
      finally {
        in.close();
      }
    }
    return result;
  }

  /**
   * This method is used for debugging and logging purpose only, in order to fetch the content of a InputStream as String.
   *
   * @param input	any InputStream
   * @return	the data read from the stream as a String
   * @throws IndivoException	if a fatal error occur
   */
  private static String getStreamContent(InputStream input) throws IndivoException {
    String result = null;
    try {
      result = new String(readFullStream(input));
    }
    catch (IOException e) {
      throw new IndivoException("Unable to read stream content", e);
    }
    return result;
  }
}
