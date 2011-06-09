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
package com.orange.jlinx.document.ext;

import java.net.URI;

/**
 * CodedValue represents the data type of the same name used in Indivo documents. 
 * 
 * @author dev-indivo@brialon.com
 *
 */
public class CodedValue {

  private String humanReadable;
  private URI type;
  private String value;
  private String abbrev;

  /**
   * (This is the full constructor)
   * Creates a new CodedValue instance, using all available information.
   *
   * @param humanReadable
   * @param type      this can be null as it is optional (null)
   * @param value     this can be null as it is optional (null)
   * @param abbrev	this can be null as it is optional (null)
   */
  public CodedValue(String humanReadable, URI type, String value, String abbrev) {
    this.humanReadable = humanReadable;
    this.type = type;
    this.value = value;
    this.abbrev = abbrev;
  }

  /**
   * (This is the minimal constructor)
   * Creates a new CodedValue instance using the minimal set of required information.
   *
   * @param humanReadable
   */
  public CodedValue(String humanReadable) {
    this(humanReadable, null, null, null);
  }

  /**
   * Creates a new CodedValue instance using its value and abbreviation.
   *
   * @param humanReadable
   * @param abbrev	this can be null as it is optional
   */
  public CodedValue(String humanReadable, String abbrev) {
    this(humanReadable, null, null, abbrev);
  }

  /**
   * @return the humanReadable
   */
  public String getHumanReadable() {
    return humanReadable;
  }

  /**
   * @param humanReadable the humanReadable to set
   */
  public void setHumanReadable(String humanReadable) {
    this.humanReadable = humanReadable;
  }

  /**
   * @return the type
   */
  public URI getType() {
    return type;
  }

  /**
   * @param type the type to set
   */
  public void setType(URI type) {
    this.type = type;
  }

  /**
   * @return the value
   */
  public String getValue() {
    return value;
  }

  /**
   * @param value the value to set
   */
  public void setValue(String value) {
    this.value = value;
  }

  /**
   * @return the abbrev
   */
  public String getAbbrev() {
    return abbrev;
  }

  /**
   * @param abbrev the abbrev to set
   */
  public void setAbbrev(String abbrev) {
    this.abbrev = abbrev;
  }

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("CodedValue [");
    if (humanReadable != null) {
      builder.append("humanReadable=");
      builder.append(humanReadable);
    }
    if (abbrev != null) {
      builder.append(", ");
      builder.append("abbrev=");
      builder.append(abbrev);
    }
    if (type != null) {
      builder.append(", ");
      builder.append("type=");
      builder.append(type);
    }
    if (value != null) {
      builder.append(", ");
      builder.append("value=");
      builder.append(value);
    }
    builder.append("]");
    return builder.toString();
  }
}
