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
package com.orange.jlinx.document.medical;

import java.util.Date;
import javax.xml.bind.JAXBElement;
import com.orange.jlinx.document.Document;
import com.orange.jlinx.document.DocumentType;
import com.orange.jlinx.document.XDocumentFactory;
import com.orange.jlinx.document.ext.CodedValue;
import com.orange.jlinx.document.x.XVitalSign;

/**
 * This class represents the medical document VitalSign of Indivo.
 * 
 * @author dev-indivo@brialon.com
 *
 */
public class VitalSign extends Document {

  /**
   * A DocumentType identifying the Indivo document type handled by this class.
   */
  private Date dateMeasured;
  private CodedValue name;
  private Double value;
  private CodedValue unit;
  private String site;
  private String position;
  private String comments;
  /**
   * A DocumentType identifying the Indivo document type handled by this
   * class.
   */
  public static DocumentType DOCUMENT_TYPE = new DocumentType(
          "VitalSign",
          "http://indivo.org/vocab/xml/documents#VitalSign",
          "vitals",
          VitalSign.class);

  /* (non-Javadoc)
   * @see com.orange.jlinx.document.Document#getXmlElement()
   */
  @Override
  public JAXBElement<?> getXmlElement() {
    XVitalSign xVitalSign = XDocumentFactory.getXVitalSign(this);
    JAXBElement<XVitalSign> result = new JAXBElement<XVitalSign>(
            this.getQName(),
            XVitalSign.class,
            xVitalSign);
    return result;
  }

  /**
   * This is the full constructor.
   * Creates a new VitalSign instance, using all available information.
   *
   * @param dateMeasured
   * @param name
   * @param value
   * @param unit
   * @param site	this can be null as it is optional
   * @param position	this can be null as it is optional
   * @param comments	this can be null as it is optional
   */
  public VitalSign(Date dateMeasured, CodedValue name, Double value, CodedValue unit, String site, String position, String comments) {
    super(DOCUMENT_TYPE);

    this.dateMeasured = dateMeasured;
    this.name = name;
    this.value = value;
    this.unit = unit;
    this.site = site;
    this.position = position;
    this.comments = comments;
  }

  /**
   * This is the minimal constructor.
   * Creates a new VitalSign instance using the minimal set of required information.
   *
   * @param dateMeasured
   * @param name
   * @param value
   * @param unit
   */
  public VitalSign(Date dateMeasured, String name, Double value, String unit) {
    this(dateMeasured, new CodedValue(name), value, new CodedValue(unit), null, null, null);
  }

  /**
   * @return the dateMeasured
   */
  public Date getDateMeasured() {
    return dateMeasured;
  }

  /**
   * @param dateMeasured the dateMeasured to set
   */
  public void setDateMeasured(Date dateMeasured) {
    this.dateMeasured = dateMeasured;
  }

  /**
   * @return the name
   */
  public CodedValue getName() {
    return name;
  }

  /**
   * @param name the name to set
   */
  public void setName(CodedValue name) {
    this.name = name;
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

  /**
   * @return the site
   */
  public String getSite() {
    return site;
  }

  /**
   * @param site the site to set
   */
  public void setSite(String site) {
    this.site = site;
  }

  /**
   * @return the position
   */
  public String getPosition() {
    return position;
  }

  /**
   * @param position the position to set
   */
  public void setPosition(String position) {
    this.position = position;
  }

  /**
   * @return the comments
   */
  public String getComments() {
    return comments;
  }

  /**
   * @param comments the comments to set
   */
  public void setComments(String comments) {
    this.comments = comments;
  }

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("VitalSign [");
    if (comments != null) {
      builder.append("comments=");
      builder.append(comments);
      builder.append(", ");
    }
    if (dateMeasured != null) {
      builder.append("dateMeasured=");
      builder.append(dateMeasured);
      builder.append(", ");
    }
    if (name != null) {
      builder.append("name=");
      builder.append(name);
      builder.append(", ");
    }
    if (position != null) {
      builder.append("position=");
      builder.append(position);
      builder.append(", ");
    }
    if (site != null) {
      builder.append("site=");
      builder.append(site);
      builder.append(", ");
    }
    if (unit != null) {
      builder.append("unit=");
      builder.append(unit);
      builder.append(", ");
    }
    if (value != null) {
      builder.append("value=");
      builder.append(value);
    }
    builder.append("]");
    return builder.toString();
  }
}
