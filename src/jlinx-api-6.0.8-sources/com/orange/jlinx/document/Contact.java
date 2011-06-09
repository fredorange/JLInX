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

import java.util.*;
import javax.xml.bind.JAXBElement;
import com.orange.jlinx.document.ext.*;
import com.orange.jlinx.document.x.XContact;

/**
 * This class handles the Contact document used by Indivo to identify records.
 * 
 * @author dev-indivo@brialon.com
 *
 */
public class Contact extends Document {

  private Name name;
  private List<Email> email;
  private List<Address> address;
  private List<Location> location;
  private List<PhoneNumber> phoneNumber;
  private List<InstantMessengerName> instantMessengerName;
  private String thumbnail;
  /**
   * A DocumentType identifying the Indivo document type handled by this class.
   */
  public static DocumentType DOCUMENT_TYPE = new DocumentType(
          "Contact",
          "http://indivo.org/vocab/xml/documents#Contact",
          null,
          Contact.class);

  /**
   * Creates a new empty Contact document.
   */
  public Contact() {
    super(DOCUMENT_TYPE);

    this.email = new ArrayList<Email>();
    this.address = new ArrayList<Address>();
    this.location = new ArrayList<Location>();
    this.phoneNumber = new ArrayList<PhoneNumber>();
    this.instantMessengerName = new ArrayList<InstantMessengerName>();
  }


  /**
   * @return the name
   */
  public Name getName() {
    return name;
  }

  /**
   * @param name the name to set
   */
  public void setName(Name name) {
    this.name = name;
  }

  /**
   * @return the thumbnail
   */
  public String getThumbnail() {
    return thumbnail;
  }

  /**
   * @param thumbnail the thumbnail to set
   */
  public void setThumbnail(String thumbnail) {
    this.thumbnail = thumbnail;
  }

  /**
   * @return the email
   */
  public List<Email> getEmail() {
    return email;
  }

  /**
   * @return the address
   */
  public List<Address> getAddress() {
    return address;
  }

  /**
   * @return the location
   */
  public List<Location> getLocation() {
    return location;
  }

  /**
   * @return the phoneNumber
   */
  public List<PhoneNumber> getPhoneNumber() {
    return phoneNumber;
  }

  /**
   * @return the instantMessengerName
   */
  public List<InstantMessengerName> getInstantMessengerName() {
    return instantMessengerName;
  }


  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("Contact [");
    if (address != null) {
      builder.append("address=");
      builder.append(address);
    }
    if (email != null) {
      builder.append(", ");
      builder.append("email=");
      builder.append(email);
    }
    if (instantMessengerName != null) {
      builder.append(", ");
      builder.append("instantMessengerName=");
      builder.append(instantMessengerName);
    }
    if (location != null) {
      builder.append(", ");
      builder.append("location=");
      builder.append(location);
    }
    if (name != null) {
      builder.append(", ");
      builder.append("name=");
      builder.append(name);
    }
    if (phoneNumber != null) {
      builder.append(", ");
      builder.append("phoneNumber=");
      builder.append(phoneNumber);
    }
    if (thumbnail != null) {
      builder.append(", ");
      builder.append("thumbnail=");
      builder.append(thumbnail);
    }
    builder.append("]");
    return builder.toString();
  }

  /* (non-Javadoc)
   * @see com.orange.jlinx.document.Document#getXmlElement()
   */
  @Override
  public JAXBElement<?> getXmlElement() {
    XContact xcontact = XDocumentFactory.getXContact(this);
    JAXBElement<XContact> result = new JAXBElement<XContact>(this.getQName(), XContact.class, xcontact);
    return result;
  }
}
