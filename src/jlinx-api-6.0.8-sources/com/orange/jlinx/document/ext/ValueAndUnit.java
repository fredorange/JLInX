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

/**
 * CodedValue represents the data type of the same name used in Indivo documents. 
 * 
 * @author dev-indivo@brialon.com
 *
 */
public class ValueAndUnit {

  private Double value;
  private String textValue;
  private CodedValue unit;

  /**
   * (This is the full constructor)
   * Creates a new ValueAndUnit instance, using all available information.
   *
   * @param humanReadable
   * @param value     this can be null as it is optional (null)
   * @param textValue this can be null as it is optional (null)
   * @param unit      this can be null as it is optional (null)
   */
  public ValueAndUnit(Double value, String textValue, CodedValue unit) {
    this.value = value;
    this.textValue = textValue;
    this.unit = unit;
  }

  /**
   * (This is the minimal constructor)
   * Creates a new ValueAndUnit instance using the minimal set of required information.
   *
   * @param humanReadable
   */
  public ValueAndUnit(Double value) {
    this(value, null, null);
  }

  /**
   * @return the value
   */
  public Double getValue() {
    return value;
  }

  /**
   * @param value the value to set
   */
  public void setValue(Double value) {
    this.value = value;
  }

  /**
   * @return the textValue
   */
  public String getTextValue() {
    return textValue;
  }

  /**
   * @param textValue the textValue to set
   */
  public void setTextValue(String textValue) {
    this.textValue = textValue;
  }

  /**
   * @return the unit
   */
  public CodedValue getUnit() {
    return unit;
  }

  /**
   * @param unit the unit to set
   */
  public void setUnit(CodedValue unit) {
    this.unit = unit;
  }


  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("ValueAndUnit [");
    if (getValue() != null) {
      builder.append("value=");
      builder.append(value);
    }
    if (textValue != null) {
      builder.append(", ");
      builder.append("textValue=");
      builder.append(textValue);
    }
    if (unit != null) {
      builder.append(", ");
      builder.append("unit=");
      builder.append(unit.toString());
    }
    builder.append("]");
    return builder.toString();
  }
}
