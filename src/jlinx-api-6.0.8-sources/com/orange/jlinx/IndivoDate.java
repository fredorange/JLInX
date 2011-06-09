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

import java.util.Calendar;
import java.util.Date;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * Date handling from the Indivo point of view.<br>
 * <ul>An Indivo date+time (i.e. mapped in the Indivo REST API to an xml 
 * <code>xs:datetime</code> type) must adhere to the following indivo format:
 * <b>'YYYY-MM-DDTHH:MI:SSZ'</b> where 'Z' means UTC. Example: 2010-10-25T15:45:55Z<br>
 * This is clearly a restriction of the UTC format meaning that the stuff
 * below is forbidden:
 * <li>Fractional seconds not allowed.</li>
 * <li>Timezone not allowed. Hence it is up to the indivo client to operate the 
 * conversion toward UTC.</li>
 * </ul>
 *
 * <ul>An Indivo date (i.e. mapped in the Indivo REST API to an xml 
 * <code>xs:date</code> type) must adhere to the follwing indivo format:
 * <b>'YYYY-MM-dd'</b>. Example: 2010-11-25<br>
 * Indivo doesn't allow a timezone. Hence timezone for produced indivo dates is
 * irrelevant and is valued with the current default one.<br>
 * Note to indivo guys: how do we handle corner case like this one:
 * "February, 20th 2010 at HongKong and Paris". Obviously the end of the day 
 * doesn't occur at the same time in both towns.
 * </ul>
 * See
 * <a href='http://wiki.chip.org/indivo/index.php/Indivo_Basic_Data_Formats'>indivo wiki</a>
 * or <a href='http://www.w3.org/TR/2004/REC-xmlschema-2-20041028/#dateTime'>W3C specs on dates</a>
 * for more details.
 * 
 * @since indivo 0.9.1
 * @author alain
 */
public class IndivoDate {

//  private static final String INDIVO_DATE_PATTERN = "yyyy-MM-dd";
 // private static SimpleDateFormat indivoDateFormat;
 // private static final String INDIVO_DATETIME_PATTERN = "yyyy-MM-dd_HH:mm:ss";
//  private static SimpleDateFormat indivoDateTimeFormat;
//  private static final Object safeParse = new Object();

  /**
   * The current date+time, compliant with the indivo format.<br>
   * Note that the timezone is the default one. Conversion to UTC will occur on data sending.
   * @return A <code>Date</code> object initialized with the current date+time
   *         and the default timezone.
   */
  public static Date nowAsDateTime() {
    Calendar c = Calendar.getInstance();
    c.set(Calendar.MILLISECOND, 0);
    return c.getTime();
  }

  /**
   * The current date, compliant with the indivo format.<br>
   * 
   * @return A <code>Date</code> object initialized with the current date 
   *         (year,month,day) and timefields zeroed (hour,min,sec) and the 
   *          default timezone.
   */
  public static Date nowAsDate() {
    Calendar c = Calendar.getInstance();
    int y = c.get(Calendar.YEAR);
    int m = c.get(Calendar.MONTH);
    int d = c.get(Calendar.DATE);
    c.clear();
    c.set(y, m, d);
    return c.getTime();
  }

  /**
   * Parse a date expected to be in Indivo date format into a <code>Date</code> 
   * object.
   * @param aDate Date to parse
   * @return a <code>Date</code> object
   * @throws IllegalArgumentException if the supplied date isn't compliant 
   *         with the indivo date format.
   */
  public static Date parseAsDate(String aDate) {
    if (aDate == null) {
      throw new IllegalArgumentException("Null value not allowed");
    }
    XMLGregorianCalendar xgl = XSDateTimeCustomBinder.parseDate(aDate);
    Date d = xgl.toGregorianCalendar().getTime();
    return d;
  }

  /**
   * Parse a dateTime expected to be in Indivo date+time format into a 
   * <code>Date</code> object.<br>
   * Supplied date string is parsed according to the default timezone.
   * @param aDateTime date to parse
   * @return a <code>Date</code> object
   * @throws IllegalArgumentException if the supplied date isn't compliant with
   *         the indivo date format.
   */
  public static Date parseAsDateTime(String aDateTime) {
    if (aDateTime == null) {
      throw new IllegalArgumentException("Null value not allowed");
    }
    XMLGregorianCalendar xgl = XSDateTimeCustomBinder.parseDateTime(aDateTime);
    Date d = xgl.toGregorianCalendar().getTime();
    return d;
  }
}
