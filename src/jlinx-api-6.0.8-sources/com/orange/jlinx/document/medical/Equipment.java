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
import com.orange.jlinx.document.x.XEquipment;

public class Equipment extends Document {

   private String type;
   private String name;
   private String vendor;
   private String id;
   private String description;
   private String specification;
   private String certification;
   private Date dateStarted;
   private Date dateStopped;
   /**
    * A DocumentType identifying the Indivo document type handled by this
    * class.
    */
   public static DocumentType DOCUMENT_TYPE = new DocumentType(
           "Equipment",
           "http://indivo.org/vocab/xml/documents#Equipment",
           "equipment",
           Equipment.class);

   /*
    * (non-Javadoc)
    *
    * @see com.orange.jlinx.document.Document#getXmlElement()
    */
   @Override
   public JAXBElement<?> getXmlElement() {
      XEquipment x = XDocumentFactory.getXEquipment(this);
      JAXBElement<XEquipment> result = new JAXBElement<XEquipment>(
              this.getQName(),
              XEquipment.class,
              x);
      return result;
   }

   /**
    * This is the full constructor. Creates a new Equipment instance, using all
    * available information.
    *
    * @param type
    * @param name
    * @param vendor
    * @param id
    * @param description
    * @param specification
    * @param certification
    * @param dateStarted
    * @param dateStopped
    *
    */
   public Equipment(String type, String name, String vendor, String id, String description,
           String specification, String certification, Date dateStarted, Date dateStopped) {
      super(DOCUMENT_TYPE);

      this.type = type;
      this.name = name;
      this.vendor = vendor;
      this.id = id;
      this.description = description;
      this.specification = specification;
      this.certification = certification;
      this.dateStarted = dateStarted;
      this.dateStopped = dateStopped;


   }

   public String getType() {
      return type;
   }

   public void setType(String type) {
      this.type = type;
   }

   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getSpecification() {
      return specification;
   }

   public void setSpecification(String specification) {
      this.specification = specification;
   }

   public String getCertification() {
      return certification;
   }

   public void setCertification(String certification) {
      this.certification = certification;
   }

   public Date getDateStarted() {
      return dateStarted;
   }

   public void setDateStarted(Date dateStarted) {
      this.dateStarted = dateStarted;
   }

   public Date getDateStopped() {
      return dateStopped;
   }

   public void setDateStopped(Date dateStopped) {
      this.dateStopped = dateStopped;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getVendor() {
      return vendor;
   }

   public void setVendor(String vendor) {
      this.vendor = vendor;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   @Override
   public String toString() {

      StringBuilder builder = new StringBuilder();
      builder.append("Equipment [");
      if (type != null) {
         builder.append("type=");
         builder.append(type);
      }
      if (name != null) {
        builder.append(", ");
         builder.append("name=");
         builder.append(name);
      }
      if (vendor != null) {
        builder.append(", ");
         builder.append("vendor=");
         builder.append(vendor);
      }
      if (id != null) {
        builder.append(", ");
         builder.append("id=");
         builder.append(id);
      }
      if (description != null) {
        builder.append(", ");
         builder.append("description=");
         builder.append(description);
      }
      if (specification != null) {
        builder.append(", ");
         builder.append("specification=");
         builder.append(specification);
      }
      if (certification != null) {
        builder.append(", ");
         builder.append("certification=");
         builder.append(certification);
      }
      if (dateStarted != null) {
        builder.append(", ");
         builder.append("DateStarted=");
         builder.append(dateStarted);
      }
      if (dateStopped != null) {
        builder.append(", ");
         builder.append("DateStopped=");
         builder.append(dateStopped);
      }
      builder.append("]");
      return builder.toString();

   }
}
