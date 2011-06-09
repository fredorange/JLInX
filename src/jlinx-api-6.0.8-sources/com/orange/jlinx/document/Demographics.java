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

import javax.xml.bind.JAXBElement;
import com.orange.jlinx.document.x.XDemographics;
import java.util.*;

/**
 * This class handles the Demographics document used by Indivo to identify records.
 * 
 * @author dev-indivo@brialon.com
 *
 */
public class Demographics extends Document {

  private Date dateOfBirth;
  private Date dateOfDeath;
  private String gender;
  private List<String> ethnicity;
  private List<String> language;
  private String maritalStatus;
  private String employmentStatus;
  private String employmentIndustry;
  private String occupation;
  private String religion;
  private String income;
  private String highestEducation;
  private Boolean organDonor;
  /**
   * A DocumentType identifying the Indivo document type handled by this class.
   */
  public static DocumentType DOCUMENT_TYPE = new DocumentType(
          "Demographics",
          "http://indivo.org/vocab/xml/documents#Demographics",
          "demographics",
          Demographics.class);

  /* (non-Javadoc)
   * @see com.orange.jlinx.document.Document#getXmlElement()
   */
  @Override
  public JAXBElement<?> getXmlElement() {
    XDemographics xDemographics = XDocumentFactory.getXDemographics(this);
    JAXBElement<XDemographics> result = new JAXBElement<XDemographics>(this.getQName(), XDemographics.class, xDemographics);
    return result;
  }

  public Demographics() {
    super(DOCUMENT_TYPE);

    this.ethnicity = new ArrayList<String>();
    this.language = new ArrayList<String>();
  }

  /**
   * Creates a new empty Demographics document.
   */
  public Demographics(Date dateOfBirth, Date dateOfDeath, String gender, List<String> ethnicity, List<String> language, String maritalStatus, String employmentStatus, String employmentIndustry, String occupation, String religion, String income, String highestEducation, boolean organDonor) {
    super(DOCUMENT_TYPE);

    this.dateOfBirth = dateOfBirth;
    this.dateOfDeath = dateOfDeath;
    this.gender = gender;
    this.ethnicity = ethnicity;
    this.language = language;
    this.maritalStatus = maritalStatus;
    this.employmentStatus = employmentStatus;
    this.employmentIndustry = employmentIndustry;
    this.occupation = occupation;
    this.religion = religion;
    this.income = income;
    this.highestEducation = highestEducation;
    this.organDonor = organDonor;
  }

  /**
   * @return the dateOfBirth
   */
  public Date getDateOfBirth() {
    return dateOfBirth;
  }

  /**
   * @param dateOfBirth the dateOfBirth to set
   */
  public void setDateOfBirth(Date dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  /**
   * @return the dateOfDeath
   */
  public Date getDateOfDeath() {
    return dateOfDeath;
  }

  /**
   * @param dateOfDeath the dateOfDeath to set
   */
  public void setDateOfDeath(Date dateOfDeath) {
    this.dateOfDeath = dateOfDeath;
  }

  /**
   * @return the gender
   */
  public String getGender() {
    return gender;
  }

  /**
   * @param gender the gender to set
   */
  public void setGender(String gender) {
    this.gender = gender;
  }

  /**
   * @return the ethnicity
   */
  public List<String> getEthnicity() {
    return ethnicity;
  }

  /**
   * @return the language
   */
  public List<String> getLanguage() {
    return language;
  }

  /**
   * @return the maritalStatus
   */
  public String getMaritalStatus() {
    return maritalStatus;
  }

  /**
   * @param maritalStatus the maritalStatus to set
   */
  public void setMaritalStatus(String maritalStatus) {
    this.maritalStatus = maritalStatus;
  }

  /**
   * @return the employmentStatus
   */
  public String getEmploymentStatus() {
    return employmentStatus;
  }

  /**
   * @param employmentStatus the employmentStatus to set
   */
  public void setEmploymentStatus(String employmentStatus) {
    this.employmentStatus = employmentStatus;
  }

  /**
   * @return the employmentIndustry
   */
  public String getEmploymentIndustry() {
    return employmentIndustry;
  }

  /**
   * @param employmentIndustry the employmentIndustry to set
   */
  public void setEmploymentIndustry(String employmentIndustry) {
    this.employmentIndustry = employmentIndustry;
  }

  /**
   * @return the occupation
   */
  public String getOccupation() {
    return occupation;
  }

  /**
   * @param occupation the occupation to set
   */
  public void setOccupation(String occupation) {
    this.occupation = occupation;
  }

  /**
   * @return the religion
   */
  public String getReligion() {
    return religion;
  }

  /**
   * @param religion the religion to set
   */
  public void setReligion(String religion) {
    this.religion = religion;
  }

  /**
   * @return the income
   */
  public String getIncome() {
    return income;
  }

  /**
   * @param income the income to set
   */
  public void setIncome(String income) {
    this.income = income;
  }

  /**
   * @return the highestEducation
   */
  public String getHighestEducation() {
    return highestEducation;
  }

  /**
   * @param highestEducation the highestEducation to set
   */
  public void setHighestEducation(String highestEducation) {
    this.highestEducation = highestEducation;
  }

  /**
   * @return the organDonor
   */
  public boolean isOrganDonor() {
    return organDonor;
  }

  /**
   * @param organDonor the organDonor to set
   */
  public void setOrganDonor(boolean organDonor) {
    this.organDonor = organDonor;
  }
  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("Demographics [");
    if (dateOfBirth != null) {
      builder.append("dateOfBirth=");
      builder.append(dateOfBirth);
    }
    if (dateOfDeath != null) {
      builder.append(", ");
      builder.append("dateOfDeath=");
      builder.append(dateOfDeath);
    }
    if (employmentIndustry != null) {
      builder.append(", ");
      builder.append("employmentIndustry=");
      builder.append(employmentIndustry);
    }
    if (employmentStatus != null) {
      builder.append(", ");
      builder.append("employmentStatus=");
      builder.append(employmentStatus);
    }
    if (ethnicity != null) {
      builder.append(", ");
      builder.append("ethnicity=");
      builder.append(ethnicity);
    }
    if (gender != null) {
      builder.append(", ");
      builder.append("gender=");
      builder.append(gender);
    }
    if (highestEducation != null) {
      builder.append(", ");
      builder.append("highestEducation=");
      builder.append(highestEducation);
    }
    if (income != null) {
      builder.append(", ");
      builder.append("income=");
      builder.append(income);
    }
    if (language != null) {
      builder.append(", ");
      builder.append("language=");
      builder.append(language);
    }
    if (maritalStatus != null) {
      builder.append(", ");
      builder.append("maritalStatus=");
      builder.append(maritalStatus);
    }
    if (occupation != null) {
      builder.append(", ");
      builder.append("occupation=");
      builder.append(occupation);
    }
    if (organDonor != null) {
      builder.append(", ");
      builder.append("organDonor=");
      builder.append(organDonor);
    }
    if (religion != null) {
      builder.append(", ");
      builder.append("religion=");
      builder.append(religion);
    }
    builder.append("]");
    return builder.toString();
  }
}
