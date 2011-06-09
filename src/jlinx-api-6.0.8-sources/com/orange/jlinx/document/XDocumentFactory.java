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
import java.util.logging.Logger;
import javax.xml.datatype.*;
import com.orange.jlinx.document.ext.*;
import com.orange.jlinx.document.medical.*;
import com.orange.jlinx.document.x.*;

/**
 * XDocumentFactory allows to convert user-friendly objects into JAXB generated
 * object instances. The returned values are systematically instances of classes
 * from the package com.orange.indivo.document.x, which is dedicated to JAXB
 * generated stuff.
 * 
 * @author dev-indivo@brialon.com
 * 
 */
public class XDocumentFactory {

  private static Logger logger = Logger.getLogger(XDocumentFactory.class.toString());

  /**
   * Returns an XCodedValue instance from a CodedValue object. The returned
   * value is null if given parameter is null.
   *
   * @param codedValue
   *            the object to convert
   * @return an XCodedValue instance having the same information as the given
   *         codedValue (or null if parameter is null)
   */
  public static XCodedValue getXCodedValue(CodedValue codedValue) {
    XCodedValue result = null;
    if (codedValue != null) {
      result = new XCodedValue();
      if (codedValue.getType() != null) {
        result.setType(codedValue.getType().toString());
      }
      result.setCodeValue(codedValue.getValue());
      result.setAbbrev(codedValue.getAbbrev());
      result.setValue(codedValue.getHumanReadable());
    }
    return result;
  }

  /**
   * Returns an XCodedValue instance from a CodedValue object. The returned
   * value is null if given parameter is null.
   *
   * @param codedValue
   *            the object to convert
   * @return an XCodedValue instance having the same information as the given
   *         codedValue (or null if parameter is null)
   */
  public static XValueAndUnit getXValueAndUnit(ValueAndUnit valueAndUnit) {
    XValueAndUnit result = null;
    if (valueAndUnit != null) {
      result = new XValueAndUnit();
      result.setValue(valueAndUnit.getValue());
      result.setTextValue(valueAndUnit.getTextValue());
      result.setUnit(getXCodedValue(valueAndUnit.getUnit()));
    }
    return result;
  }

  /**
   * Returns a XPrescription instance from an {@link Prescription} object. The returned
   * value is null if given parameter is null.
   *
   * @param prescription the object to convert
   * @return an XPrescription instance having the same information as the given
   *         prescription (or null if parameter is null)
   */
  public static Duration getXDuration(Long duration) {
    Duration result = null;
    if (duration != null) {
      try {
        result = DatatypeFactory.newInstance().newDuration(duration.longValue());
      }
      catch (DatatypeConfigurationException e) {
        logger.warning(e.getMessage());
      }
    }
    return result;
  }

  /**
   * Returns an XVitalSign instance from a VitalSign object. The returned
   * value is null if given parameter is null.
   *
   * @param vitalSign
   *            the object to convert
   * @return an XVitalSign instance having the same information as the given
   *         vitalSign (or null if parameter is null)
   */
  public static XVitalSign getXVitalSign(VitalSign vitalSign) {
    XVitalSign result = null;
    if (vitalSign != null) {
      result = new XVitalSign();
      result.setDateMeasured(getXmlGregorianCalendar(vitalSign.getDateMeasured()));
      result.setName(getXCodedValue(vitalSign.getName()));
      result.setValue(vitalSign.getValue());
      result.setUnit(getXCodedValue(vitalSign.getUnit()));
      result.setSite(vitalSign.getSite());
      result.setPosition(vitalSign.getPosition());
      result.setComments(vitalSign.getComments());
    }
    return result;
  }

  /**
   * Returns a XEquipment instance from an {@link Equipment} object. The returned
   * value is null if given parameter is null.
   *
   * @param equipment the object to convert
   * @return an XEquipment instance having the same information as the given
   *         equipment (or null if parameter is null)
   */
  public static XEquipment getXEquipment(Equipment equipment) {
    XEquipment result = null;
    if (equipment != null) {
      result = new XEquipment();

      result.setType(equipment.getType());
      result.setName(equipment.getName());
      result.setVendor(equipment.getVendor());
      result.setId(equipment.getId());
      result.setDescription(equipment.getDescription());
      result.setSpecification(equipment.getSpecification());
      result.setCertification(equipment.getCertification());
      result.setDateStarted(getXmlGregorianCalendar(equipment.getDateStarted()));
      result.setDateStopped(getXmlGregorianCalendar(equipment.getDateStopped()));
    }
    return result;
  }

  /**
   * Returns a XAllergy.Allergen instance from an {@link Allergy.Allergen} object. The returned
   * value is null if given parameter is null.
   *
   * @param allergen the object to convert
   * @return an XAllergy.Allergen instance having the same information as the given
   *         Allergy.Allergen (or null if parameter is null)
   */
  public static XAllergy.Allergen getXAllergen(Allergy.Allergen allergen) {
    XAllergy.Allergen result = null;
    if (allergen != null) {
      result = new XAllergy.Allergen();

      result.setName(getXCodedValue(allergen.getName()));
      result.setType(getXCodedValue(allergen.getType()));
    }
    return result;
  }

  /**
   * Returns a XImmunization instance from an {@link Immunization} object. The returned
   * value is null if given parameter is null.
   *
   * @param immunization the object to convert
   * @return an XImmunization instance having the same information as the given
   *         immunization (or null if parameter is null)
   */
  public static XImmunization getXImmunization(Immunization immunization) {
    XImmunization result = null;
    if (immunization != null) {
      result = new XImmunization();
      // Init XVaccine
      XImmunization.Vaccine vaccine = new XImmunization.Vaccine();
      vaccine.setType(getXCodedValue(immunization.getVaccine().getType()));
      vaccine.setManufacturer(immunization.getVaccine().getManufacturer());
      vaccine.setLot(immunization.getVaccine().getLot());
      vaccine.setExpiration(getXmlGregorianCalendar(immunization.getVaccine().getExpiration()));

      result.setDateAdministered(getXmlGregorianCalendar(immunization.getDateAdministered()));
      result.setAdministeredBy(immunization.getAdministeredBy());
      result.setVaccine(vaccine);
      result.setSequence(immunization.getSequence());
      result.setAnatomicSurface(getXCodedValue(immunization.getAnatomicSurface()));
      result.setAdverseEvent(immunization.getAdverseEvent());
    }
    return result;
  }

  /**
   * Returns a XImmunization.Vaccine instance from an {@link Immunization.Vaccine} object. The returned
   * value is null if given parameter is null.
   *
   * @param vaccine the object to convert
   * @return an XImmunization.Vaccine instance having the same information as the given
   *         Immunization.Vaccine (or null if parameter is null)
   */
  public static XImmunization.Vaccine getXVaccine(Immunization.Vaccine vaccine) {
    XImmunization.Vaccine result = null;
    if (vaccine != null) {
      result = new XImmunization.Vaccine();
      result.setType(getXCodedValue(vaccine.getType()));
      result.setManufacturer(vaccine.getManufacturer());
      result.setLot(vaccine.getLot());
      result.setExpiration(getXmlGregorianCalendar(vaccine.getExpiration()));
    }
    return result;
  }

  /**
   * Returns a XAllergy instance from an {@link Allergy} object. The returned
   * value is null if given parameter is null.
   *
   * @param allergy the object to convert
   * @return an XAllergy instance having the same information as the given
   *         allergy (or null if parameter is null)
   */
  public static XAllergy getXAllergy(Allergy allergy) {
    XAllergy result = null;
    if (allergy != null) {
      result = new XAllergy();
      // Init XAllergen
      XAllergy.Allergen allergen = new XAllergy.Allergen();
      allergen.setName(getXCodedValue(allergy.getAllergen().getName()));
      allergen.setType(getXCodedValue(allergy.getAllergen().getType()));

      result.setAllergen(allergen);
      result.setReaction(allergy.getReaction());
      result.setSpecifics(allergy.getSpecifics());
      result.setDiagnosedBy(allergy.getDiagnosedBy());
      result.setDateDiagnosed(getXmlGregorianCalendar(allergy.getDateDiagnosed()));
    }
    return result;
  }

  /**
   * Returns an XProblem instance from a Problem object. The
   * returned value is null if given parameter is null.
   *
   * @param problem
   *            the object to convert
   * @return an XProblem instance having the same information as the
   *         given problem (or null if parameter is null)
   */
  public static XProblem getXProblem(Problem problem) {
    XProblem result = null;
    if (problem != null) {
      result = new XProblem();
      result.setDateOnset(getXmlGregorianCalendar(problem.getDateOnset()));
      result.setDateResolution(getXmlGregorianCalendar(problem.getDateResolution()));
      result.setName(getXCodedValue(problem.getName()));
      result.setComments(problem.getComments());
      result.setDiagnosedBy(problem.getDiagnosedBy());
    }
    return result;
  }

  /**
   * Returns a XMedication instance from an {@link Medication} object. The returned
   * value is null if given parameter is null.
   *
   * @param medication the object to convert
   * @return an XMedication instance having the same information as the given
   *         medication (or null if parameter is null)
   */
  public static XMedication getXMedication(Medication medication) {
    XMedication result = null;
    if (medication != null) {
      result = new XMedication();
      result.setDateStarted(getXmlGregorianCalendar(medication.getDateStarted()));
      result.setDateStopped(getXmlGregorianCalendar(medication.getDateStopped()));
      result.setReasonStopped(medication.getReasonStopped());
      result.setName(getXCodedValue(medication.getName()));
      result.setBrandName(getXCodedValue(medication.getBrandName()));
      result.setDose(getXValueAndUnit(medication.getDose()));
      result.setRoute(getXCodedValue(medication.getRoute()));
      result.setStrength(getXValueAndUnit(medication.getStrength()));
      result.setFrequency(getXCodedValue(medication.getFrequency()));
      result.setPrescription(getXPrescription(medication.getPrescription()));
      result.setDetails(medication.getDetails());
    }
    return result;
  }

  /**
   * Returns a XPrescription instance from an {@link Prescription} object. The returned
   * value is null if given parameter is null.
   *
   * @param prescription the object to convert
   * @return an XPrescription instance having the same information as the given
   *         prescription (or null if parameter is null)
   */
  public static XPrescription getXPrescription(Prescription prescription) {
    XPrescription result = null;
    if (prescription != null) {
      result = new XPrescription();
      result.setBy(getXProvider(prescription.getBy()));
      result.setOn(getXmlGregorianCalendar(prescription.getOn()));
      result.setStopOn(getXmlGregorianCalendar(prescription.getStopOn()));
      result.setDispenseAsWritten(prescription.isDispenseAsWritten());
      result.setDuration(getXDuration(prescription.getDuration()));
      result.setRefillInfo(prescription.getRefillInfo());
      result.setInstructions(prescription.getInstructions());
    }
    return result;
  }

  /**
   * Returns a XProvider instance from an {@link Provider} object. The returned
   * value is null if given parameter is null.
   *
   * @param provider the object to convert
   * @return an XProvider instance having the same information as the given
   *         provider (or null if parameter is null)
   */
  public static XProvider getXProvider(Provider provider) {
    XProvider result = null;
    if (provider != null) {
      result = new XProvider();
      result.setName(provider.getName());
      result.setInstitution(provider.getInstitution());
    }
    return result;
  }

  /**
   * Returns a XProcedure instance from an {@link Procedure} object. The returned
   * value is null if given parameter is null.
   *
   * @param procedure the object to convert
   * @return an XProcedure instance having the same information as the given
   *         procedure (or null if parameter is null)
   */
  public static XProcedure getXProcedure(Procedure procedure) {
    XProcedure result = null;
    if (procedure != null) {
      result = new XProcedure();
      result.setDatePerformed(getXmlGregorianCalendar(procedure.getDatePerformed()));
      result.setName(getXCodedValue(procedure.getName()));
      result.setProvider(getXProvider(procedure.getProvider()));
      result.setLocation(procedure.getLocation());
      result.setComments(procedure.getComments());
    }
    return result;
  }

  /**
   * Returns an XDemographics instance from a Demographics object. The
   * returned value is null if given parameter is null.
   *
   * @param demographics
   *            the object to convert
   * @return an XDemographics instance having the same information as the
   *         given demographics (or null if parameter is null)
   */
  public static XDemographics getXDemographics(Demographics demographics) {
    XDemographics result = null;
    if (demographics != null) {
      result = new XDemographics();
      result.setDateOfBirth(getXmlGregorianCalendar(demographics.getDateOfBirth()));
      result.setDateOfDeath(getXmlGregorianCalendar(demographics.getDateOfDeath()));
      result.setEmploymentIndustry(demographics.getEmploymentIndustry());
      result.setEmploymentStatus(demographics.getEmploymentStatus());
      result.getEthnicity().addAll(demographics.getEthnicity());
      result.setGender(demographics.getGender());
      result.setHighestEducation(demographics.getHighestEducation());
      result.setIncome(demographics.getIncome());
      result.getLanguage().addAll(demographics.getLanguage());
      result.setMaritalStatus(demographics.getMaritalStatus());
      result.setOccupation(demographics.getOccupation());
      result.setOrganDonor(demographics.isOrganDonor());
      result.setReligion(demographics.getReligion());
    }
    return result;
  }

  /**
   * Returns an XContact instance from a Contact object. The returned value is
   * null if given parameter is null.
   *
   * @param contact
   *            the object to convert
   * @return a XContact instance having the same information as the given
   *         contact (or null if parameter is null)
   */
  public static XContact getXContact(Contact contact) {
    XContact result = null;
    if (contact != null) {
      result = new XContact();
      result.setName(getXName(contact.getName()));
      result.setThumbnail(contact.getThumbnail());
      for (com.orange.jlinx.document.ext.Address address : contact.getAddress()) {
        result.getAddress().add(getXAddress(address));
      }
      for (com.orange.jlinx.document.ext.Email email : contact.getEmail()) {
        result.getEmail().add(getXEmail(email));
      }
      for (com.orange.jlinx.document.ext.InstantMessengerName instantMessengerName : contact.getInstantMessengerName()) {
        result.getInstantMessengerName().add(getXInstantMessengerName(instantMessengerName));
      }
      for (com.orange.jlinx.document.ext.Location location : contact.getLocation()) {
        result.getLocation().add(getXLocation(location));
      }
      for (com.orange.jlinx.document.ext.PhoneNumber phoneNumber : contact.getPhoneNumber()) {
        result.getPhoneNumber().add(getXPhoneNumber(phoneNumber));
      }
    }
    return result;
  }

  /**
   * Returns a XContact.PhoneNumber instance from a PhoneNumber object. The
   * returned value is null if given parameter is null.
   *
   * @param phoneNumber
   *            the object to convert
   * @return the converted object
   */
  private static XContact.PhoneNumber getXPhoneNumber(PhoneNumber phoneNumber) {
    XContact.PhoneNumber result = null;
    if (phoneNumber != null) {
      result = new XContact.PhoneNumber();
      result.setType(phoneNumber.getType());
      result.setValue(phoneNumber.getValue());
    }
    return result;
  }

  /**
   * Returns a XContact.Location instance from a Location object. The returned
   * value is null if given parameter is null.
   *
   * @param location
   *            the object to convert
   * @return the converted object
   */
  private static XContact.Location getXLocation(Location location) {
    XContact.Location result = null;
    if (location != null) {
      result = new XContact.Location();
      result.setLatitude(location.getLatitude());
      result.setLongitude(location.getLongitude());
      result.setType(location.getType());
    }
    return result;
  }

  /**
   * Returns a XContact.InstantMessengerName instance from a
   * InstantMessengerName object. The returned value is null if given
   * parameter is null.
   *
   * @param instantMessengerName
   *            the object to convert
   * @return the converted object
   */
  private static XContact.InstantMessengerName getXInstantMessengerName(
          InstantMessengerName instantMessengerName) {
    XContact.InstantMessengerName result = null;
    if (instantMessengerName != null) {
      result = new XContact.InstantMessengerName();
      result.setProtocol(instantMessengerName.getProtocol());
      result.setValue(instantMessengerName.getValue());
    }
    return result;
  }

  /**
   * Returns a XContact.Email instance from a Email object. The returned value
   * is null if given parameter is null.
   *
   * @param email
   *            the object to convert
   * @return the converted object
   */
  private static XContact.Email getXEmail(Email email) {
    XContact.Email result = null;
    if (email != null) {
      result = new XContact.Email();
      result.setType(email.getType());
      result.setValue(email.getValue());
    }
    return result;
  }

  /**
   * Returns a XContact.Address instance from a Address object. The returned
   * value is null if given parameter is null.
   *
   * @param address
   *            the object to convert
   * @return the converted object
   */
  private static XContact.Address getXAddress(Address address) {
    XContact.Address result = null;
    if (address != null) {
      result = new XContact.Address();
      result.setCountry(address.getCountry());
      result.setLocality(address.getLocality());
      result.setPostalCode(address.getPostalCode());
      result.setRegion(address.getRegion());
      result.setStreetAddress(address.getStreetAddress());
      result.setTimeZone(address.getTimeZone());
      result.setType(address.getType());
    }
    return result;
  }

  /**
   * Returns a XContact.Name instance from a Name object. The returned value
   * is null if given parameter is null.
   *
   * @param name
   *            the object to convert
   * @return the converted object
   */
  private static XContact.Name getXName(Name name) {
    XContact.Name result = null;
    if (name != null) {
      result = new XContact.Name();
      result.setFamilyName(name.getFamilyName());
      result.setFullName(name.getFullName());
    }
    return result;
  }

  /**
   * Returns an XMLGregorianCalendar instance from a Date object. The returned
   * value is null if given parameter is null.
   *
   * @param date
   *            the object to convert
   * @return an XMLGregorianCalendar instance representing the same date as
   *         the given date (or null if parameter is null)
   */
  private static XMLGregorianCalendar getXmlGregorianCalendar(Date date) {
    XMLGregorianCalendar result = null;
    if (date != null) {
      GregorianCalendar calendar = new GregorianCalendar();
      calendar.setTime(date);
      try {
        result = DatatypeFactory.newInstance().newXMLGregorianCalendar(
                calendar);
      }
      catch (DatatypeConfigurationException e) {
        logger.warning(e.getMessage());
      }
    }
    return result;
  }
}
