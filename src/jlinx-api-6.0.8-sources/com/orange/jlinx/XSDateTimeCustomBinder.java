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

import java.math.BigDecimal;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.apache.log4j.Logger;

/**
 * Indivo date+time converter for XML marshalling/unmarshalling.<br>
 * For now we remain stuck to jlinx design that use XMLGregorianCalendar in 
 * JAXB objects
 *
 * @See IndivoDate
 * @since indivo 0.9.1
 * @author alain
 */
public class XSDateTimeCustomBinder {

  private static final Logger log = Logger.getLogger(XSDateTimeCustomBinder.class);

  /**
   * Parse an incoming Indivo date+time string expected to be in UTC format.
   * Indivo requires that dates be in a normalized UTC format without any 
   * fractional seconds.<br>
   * Well this is not compliant with the UTC normalization, hence incoming date 
   * with an UTC format valid but not indivo compliant will still be accepted.
   * @param s A date in indivo format
   * @return 
   */
  public static XMLGregorianCalendar parseDateTime(String s) {
    try {
      XMLGregorianCalendar xgl = DatatypeFactory.newInstance().newXMLGregorianCalendar(s);
      //xgl.setFractionalSecond(null);    
      return xgl;
    }
    catch(DatatypeConfigurationException ex) {
      String msg = String.format(
              "Unable to parse incoming Indivo date+time string to an XMLGregorianCalendarObject. Incoming datetime='%s'. Reson is: ", s, ex.getMessage());
      log.error(msg, ex);
      throw new IllegalArgumentException(msg, ex);
    }

  }

  /**
   * Convert an Indivo date+time into its string representation.
   * The string representation is a restriction of the UTC normalization in order 
   * to be compliant with indivo server requirements.
   * Furthermore if no timezone defined it is assumed that the date is in an 
   * UTC normalized format and hence the timezone is forced to 0
   * @param dt
   * @return 
   */
  public static String printDateTime(XMLGregorianCalendar dt) {
    XMLGregorianCalendar xgl = dt.normalize();
    xgl.setFractionalSecond(null);
    if(xgl.getTimezone() == DatatypeConstants.FIELD_UNDEFINED) {
      xgl.setTimezone(0);
    }
    String r = xgl.toXMLFormat();
    return r;
  }

  /**
   * Parse an incoming Indivo date as an XMLGregoriantCalendar.
   * Expected format as reported by indivo is: 'YYYY-MM-dd'
   * @param s Indivo date to parse. 
   * @return
   */
  public static XMLGregorianCalendar parseDate(String s) {
    String errMsg = "Unable to parse incoming Indivo date from string to an XMLGregorianCalendarObject. Incoming date='%s'. Reson is: %s";
    try {
      XMLGregorianCalendar xgl = DatatypeFactory.newInstance().newXMLGregorianCalendar(s);
      if(xgl.getHour() != DatatypeConstants.FIELD_UNDEFINED
              || xgl.getMinute() != DatatypeConstants.FIELD_UNDEFINED
              || xgl.getSecond() != DatatypeConstants.FIELD_UNDEFINED
              || xgl.getFractionalSecond() != null) {
        String msg = String.format(errMsg, s, "One or more of the follwing fields are defined: hour, minute, second, fractional second");
        throw new IllegalArgumentException(msg);
      }
      // force fields h,m,s to be valued as 0
      xgl.setTime(0, 0, 0);  
      return xgl;
    }
    catch(DatatypeConfigurationException ex) {
      String msg = String.format(errMsg, s, ex.getMessage());
      log.error(msg, ex);
      throw new IllegalArgumentException(msg, ex);
    }
  }

  private void checkIndivoDateCompliant(XMLGregorianCalendar dt) {
    if((dt.getHour() != 0 && dt.getHour() != DatatypeConstants.FIELD_UNDEFINED)
            || (dt.getMinute() != 0 && dt.getMinute() != DatatypeConstants.FIELD_UNDEFINED)
            || (dt.getSecond() != 0 && dt.getSecond() != DatatypeConstants.FIELD_UNDEFINED)
            || (dt.getMillisecond() != 0 && dt.getMillisecond() != DatatypeConstants.FIELD_UNDEFINED)
            || (dt.getFractionalSecond().compareTo(BigDecimal.valueOf(0)) != 0)) {
      String msg = "Malformed XMLGregorianCalendar for an indivo date. Either hour, minute, second, millisecond or fractionnal seconds is set. value=" + dt.toString();
      throw new IllegalArgumentException(msg);
    }
  }

  /**
   * Export an Indivo date to its indivo xml equivalent
   * @param dt
   * @return
   */
  public static String printDate(XMLGregorianCalendar dt) {
    // timezone ignored since indivo date do not take care of
    // hence no need to normalize UTC
    // ugly testing because XMLGregorianCalendar can be filled in various ways :-(
    if(
       ((dt.getHour() != 0  ) && (dt.getHour() != DatatypeConstants.FIELD_UNDEFINED  )) 
        || ((dt.getMinute() != 0) && (dt.getMinute() != DatatypeConstants.FIELD_UNDEFINED))
        || ((dt.getSecond() != 0) && (dt.getSecond() != DatatypeConstants.FIELD_UNDEFINED))
        || ((dt.getMillisecond() != 0) && (dt.getMillisecond() != DatatypeConstants.FIELD_UNDEFINED))
        || ((dt.getFractionalSecond()!=null) && (dt.getFractionalSecond().compareTo(BigDecimal.valueOf(0)) != 0))
    ){
      String msg = "Malformed XMLGregorianCalendar for an indivo date. Either hour, minute, second, millisecond or fractionnal seconds is set. value=" + dt.toString();
      throw new IllegalArgumentException(msg);
    }
    // Indivo doesn't allow timezone for dates.
    // So lets go for a custom marshalling... 
    // hum hope you'll appreciate loss of the timezone...
    String datePattern = "%04d-%02d-%02d";
    String r = String.format(datePattern, dt.getYear(), dt.getMonth(), dt.getDay());
    return r;
  }
}
