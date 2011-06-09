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

import com.orange.jlinx.IndivoInternalError;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import java.util.logging.Logger;
import javax.xml.datatype.XMLGregorianCalendar;
import com.orange.jlinx.admin.Account;
import com.orange.jlinx.admin.AuthSystem;
import com.orange.jlinx.admin.Record;
import com.orange.jlinx.document.ext.*;
import com.orange.jlinx.document.medical.*;
import com.orange.jlinx.document.x.*;
import java.util.logging.Level;
import javax.xml.datatype.Duration;

/**
 * DocumentFactory allows to convert any JAXB generated object instance into a
 * JLInX object. The input parameters are systematically instances of classes
 * from the package com.orange.jlinx.document.x, which is dedicated to JAXB
 * generated stuff.
 * 
 * @author dev-indivo@brialon.com
 * 
 */
public class DocumentFactory {

  private static Logger log = Logger.getLogger(DocumentFactory.class.toString());

  /**
   * Convert any document content which type is unknown to a Document object.
   * The runtime type of the result is a subclass of Document corresponding to
   * the type of content given as parameter. If type is unknown then an
   * AnyDocument instance is returned.
   *
   * @param object
   *            the document content to convert
   * @return a Document object
   */
  public static Document getDocument(Object object) {
    return getDocument(object, Document.class);
  }

  /**
   * Convert any document content which type is unknown to a Document object.
   * The runtime type of the result is given by the documentClass parameter.
   * If given object has an unknown type, then the result type is intended to
   * be AnyDocument. If desired class does not match the one resulting from
   * the conversion, then a ClassCastException may be thrown.
   *
   * @param <D>
   *            the runtime type of the result
   * @param object
   *            the document content to convert
   * @param documentClass
   *            Class instance of the desired runtime type of the result
   * @return a Document object
   */
  @SuppressWarnings("unchecked")
  public static <D extends Document> D getDocument(Object object,
          Class<D> documentClass) {
    D result = null;
    if (object != null) {
      if (object instanceof XVitalSign) {
        result = (D) getVitalSign((XVitalSign) object);
      } else if (object instanceof XDemographics) {
        result = (D) getDemographics((XDemographics) object);
      } else if (object instanceof XProvider) {
        result = (D) getProvider((XProvider) object);
      } else if (object instanceof XImmunization) {
        result = (D) getImmunization((XImmunization) object);
      } else if (object instanceof XProcedure) {
        result = (D) getProcedure((XProcedure) object);
      } //	else if (object instanceof XContact)
      //		result = (D) getContact((XContact) object);
      else if (object instanceof XEquipment) {
        result = (D) getEquipment((XEquipment) object);
      } else if (object instanceof XAllergy) {
        result = (D) getAllergy((XAllergy) object);
      } else if (object instanceof XMedication) {
        result = (D) getMedication((XMedication) object);
      } else if (object instanceof XProblem) {
        result = (D) getProblem((XProblem) object);
      } else {
        String errorMessage = "Attempting to convert an unknown indivo object. Unknown object is " + object.getClass().getCanonicalName();
        log.log(Level.SEVERE, errorMessage);
        throw new IndivoInternalError(errorMessage);

        // For all unidentifined documents create an object (type object).
        //result = (D) getUnidentifiedDocument(object);  // Commented because don't work
      }
    }
    return result;
  }

  /**
   * Returns a VitalSign instance from a XVitalSign object. The returned value
   * is null if given parameter is null.
   *
   * @param xVitalSign
   *            the object to convert
   * @return a VitalSign instance having the same information as the given
   *         xVitalSign (or null if parameter is null)
   */
  public static VitalSign getVitalSign(XVitalSign xVitalSign) {
    VitalSign result = null;
    if (xVitalSign != null) {
      Date dateMeasured = getDate(xVitalSign.getDateMeasured());
      CodedValue name = getCodedValue(xVitalSign.getName());
      Double value = xVitalSign.getValue();
      CodedValue unit = getCodedValue(xVitalSign.getUnit());
      String site = xVitalSign.getSite();
      String position = xVitalSign.getPosition();
      String comments = xVitalSign.getComments();
      result = new VitalSign(dateMeasured, name, value, unit, site, position, comments);
    }
    return result;
  }

  /**
   * Returns an Equipment instance from a XEquipment object. The returned
   * value is null if given parameter is null.
   *
   * @param x
   *            the object to convert
   * @return an Equipment instance having the same information as the given
   *         equipment (or null if parameter is null)
   */
  public static Equipment getEquipment(XEquipment x) {
    Equipment result = null;
    if (x != null) {
      String type = x.getType();
      String name = x.getName();
      String vendor = x.getVendor();
      String id = x.getId();
      String description = x.getDescription();
      String specification = x.getSpecification();
      String certification = x.getCertification();
      Date dateStarted = getDate(x.getDateStarted());
      Date dateStopped = getDate(x.getDateStopped());

      result = new Equipment(type, name, vendor, id, description, specification, certification, dateStarted, dateStopped);
    }
    return result;
  }

  /**
   * Returns an Allergy.Allergen instance from a XAllergy.Allergen object. The returned
   * value is null if given parameter is null.
   *
   * @param x
   *            the object to convert
   * @return an Allergy.Allergen instance having the same information as the given
   *         allergy (or null if parameter is null)
   */
  public static Allergy.Allergen getAllergen(XAllergy.Allergen x) {
    if (x != null) {
      return new Allergy.Allergen(
              getCodedValue(x.getName()),
              getCodedValue(x.getType()));
    } else {
      return null;
    }
  }

  /**
   * Returns an Allergy instance from a XAllergy object. The returned
   * value is null if given parameter is null.
   *
   * @param x
   *            the object to convert
   * @return an Allergy instance having the same information as the given
   *         allergy (or null if parameter is null)
   */
  public static Allergy getAllergy(XAllergy x) {
    if (x != null) {
      return new Allergy(
              getAllergen(x.getAllergen()),
              x.getReaction(),
              x.getSpecifics(),
              x.getDiagnosedBy(),
              getDate(x.getDateDiagnosed()));
    } else {
      return null;
    }
  }

  /**
   * Returns an Immunization.Vaccine instance from a XImmunization.Vaccine object. The returned
   * value is null if given parameter is null.
   *
   * @param x
   *            the object to convert
   * @return an Immunization.Vaccine instance having the same information as the given
   *         immunization (or null if parameter is null)
   */
  public static Immunization.Vaccine getVaccine(XImmunization.Vaccine x) {
    if (x != null) {
      return new Immunization.Vaccine(
              getCodedValue(x.getType()),
              x.getManufacturer(),
              x.getLot(),
              getDate(x.getExpiration()));
    } else {
      return null;
    }
  }

  /**
   * Returns an Immunization instance from a XImmunization object. The returned
   * value is null if given parameter is null.
   *
   * @param x
   *            the object to convert
   * @return an Immunization instance having the same information as the given
   *         immunization (or null if parameter is null)
   */
  public static Immunization getImmunization(XImmunization x) {
    if (x != null) {
      return new Immunization(
              getDate(x.getDateAdministered()),
              x.getAdministeredBy(),
              getVaccine(x.getVaccine()),
              x.getSequence(),
              getCodedValue(x.getAnatomicSurface()),
              x.getAdverseEvent());
    } else {
      return null;
    }
  }

  /**
   * Returns an Provider instance from a XProvider object. The returned
   * value is null if given parameter is null.
   *
   * @param x
   *            the object to convert
   * @return an Provider instance having the same information as the given
   *         provider (or null if parameter is null)
   */
  public static Provider getProvider(XProvider x) {
    if (x != null) {
      return new Provider(
              x.getName(),
              x.getInstitution());
    } else {
      return null;
    }
  }

  /**
   * Returns an Procedure instance from a XProcedure object. The returned
   * value is null if given parameter is null.
   *
   * @param x
   *            the object to convert
   * @return an Procedure instance having the same information as the given
   *         procedure (or null if parameter is null)
   */
  public static Procedure getProcedure(XProcedure x) {
    if (x != null) {
      return new Procedure(
              getDate(x.getDatePerformed()),
              getCodedValue(x.getName()),
              getProvider(x.getProvider()),
              x.getLocation(),
              x.getComments());
    } else {
      return null;
    }
  }

  /**
   * Returns a Reports instance from a XReports object. One XReports instance
   * can contain a list of documents of any type (among the medical document
   * types). The runtime type of the medical documents contained in the
   * returned result is the one given by the documentClass parameter. The
   * returned value is null if given parameter is null.
   *
   * @param <D>
   *            the runtime type of the documents contained in the returned
   *            result (can be any Document)
   * @param xReports
   *            the object to convert
   * @param documentClass
   *            a Class object determining the runtime type of the documents
   *            contained in the returned value
   * @return a Reports instance having the same information as the given
   *         xReports (or null if parameter is null)
   */
  public static <D extends Document> Reports<D> getReports(XReports xReports,
          Class<D> documentClass) {
    Reports<D> result = null;
    if (xReports != null) {
      result = new Reports<D>();
      result.setTotalDocumentCount(xReports.getSummary().getTotalDocumentCount());
      result.setLimit(xReports.getSummary().getLimit());
      result.setOffset(xReports.getSummary().getOffset());
      result.setOrderBy(xReports.getSummary().getOrderBy());
      result.setReportList(getReport(xReports.getReport(), documentClass));
    }
    return result;
  }

  /**
   * Returns a List of Report instances from a List of XReport objects. One
   * XReport instance can contain a document of any type (among the medical
   * document types). The runtime type of the elements contained in the
   * returned result is the one given by the documentClass parameter. The
   * returned value is null if given parameter is null.
   *
   * @param <D>
   *            the runtime type of the elements contained in the returned
   *            result (can be any Document)
   * @param xReportList
   *            the object to convert
   * @param documentClass
   *            a Class object determining the runtime type of the elements
   *            contained in the returned value
   * @return a List of Report instances having the same information as the
   *         objects contained in the given xReportList (or null if parameter
   *         is null)
   */
  private static <D extends Document> List<Report<D>> getReport(List<XReport> xReportList, Class<D> documentClass) {
    List<Report<D>> result = new ArrayList<Report<D>>();
    if (xReportList != null) {
      for (XReport xReport : xReportList) {
        Report<D> report = new Report<D>();
        if (xReport.getMeta() != null) {
          report.setMeta(getDocumentMeta(xReport.getMeta().getDocument()));
        }
        report.setDocument(getAnyMedical(xReport.getItem(),
                documentClass));
        if (report.getDocument() != null) {
          result.add(report);
        }
      }
    }
    return result;
  }

  /**
   * Returns a Medical instance from a XAnyMedical object. One XAnyMedical
   * instance can contain a document of any type (among the medical document
   * types). The runtime type of the returned result is the one given by the
   * medicalClass parameter. The returned value is null if given parameter is
   * null.
   *
   * @param <D>
   *            the runtime type of the returned result (can be any
   *            implementation of the Medical interface)
   * @param xAnyMedical
   *            the object to convert
   * @param medicalClass
   *            a Class object determining the runtime type of the returned
   *            value
   * @return a Medical instance having the same information as the given
   *         xAnyMedical (or null if parameter is null)
   */
  @SuppressWarnings("unchecked")
  private static <D extends Document> D getAnyMedical(XAnyMedical xAnyMedical, Class<D> medicalClass) {
    if (xAnyMedical == null) {
      throw new IllegalArgumentException("Parameter is null");
    }

    if (medicalClass.isAssignableFrom(VitalSign.class)) {
      return (D) getVitalSign(xAnyMedical.getVitalSign());
    } else if (medicalClass.isAssignableFrom(Equipment.class)) {
      return (D) getEquipment(xAnyMedical.getEquipment());
    } else if (medicalClass.isAssignableFrom(Allergy.class)) {
      return (D) getAllergy(xAnyMedical.getAllergy());
    } else if (medicalClass.isAssignableFrom(Immunization.class)) {
      return (D) getImmunization(xAnyMedical.getImmunization());
    } else if (medicalClass.isAssignableFrom(Procedure.class)) {
      return (D) getProcedure(xAnyMedical.getProcedure());
    } else if (medicalClass.isAssignableFrom(Medication.class)) {
      return (D) getMedication(xAnyMedical.getMedication());
    } else if (medicalClass.isAssignableFrom(Prescription.class)) {
      return (D) getPrescription(xAnyMedical.getPrescription());
    } else if (medicalClass.isAssignableFrom(Problem.class)) {
      return (D) getProblem(xAnyMedical.getProblem());
    } else {
      String errorMsg = "Warning: class " + medicalClass.getCanonicalName() + " is unknown when extracting from Report.";
      log.severe(errorMsg);
      throw new IndivoInternalError(errorMsg);
    }
  }

  /**
   * Returns an Account instance from a XAccount object. The returned value is
   * null if given parameter is null.
   *
   * @param xAccount
   *            the object to convert
   * @return a Account instance having the same information as the given
   *         xAccount (or null if parameter is null)
   */
  public static Account getAccount(XAccount xAccount) {
    Account result = null;
    if (xAccount != null) {
      result = new Account();
      result.setId(xAccount.getId());
      result.setSecret(xAccount.getSecret());
      result.setFullName(xAccount.getFullName());
      result.setContactEmail(xAccount.getContactEmail());
      result.setLastLoginAt(getDate(xAccount.getLastLoginAt()));
      if (xAccount.getFailedLoginCount() != null) {
        result.setFailedLoginCount(xAccount.getFailedLoginCount().intValue());
      }
      if (xAccount.getTotalLoginCount() != null) {
        result.setTotalLoginCount(xAccount.getTotalLoginCount().intValue());
      }
      result.setState(xAccount.getState());
      result.setLastStateChange(getDate(xAccount.getLastStateChange()));
      result.setSecret(xAccount.getSecret());
      if (xAccount.getAuthSystem() != null) {
        List<AuthSystem> authSystemList = new ArrayList<AuthSystem>();
        result.setAuthSystemList(authSystemList);
        for (XAccount.AuthSystem xAuthSystem : xAccount.getAuthSystem()) {
          AuthSystem authSystem = getAuthSystem(xAuthSystem);
          if (authSystem != null) {
            authSystemList.add(authSystem);
          }
        }
      }
    }

    return result;
  }

  /**
   * Returns a Record instance from a XRecord object. The returned value is
   * null if given parameter is null.
   *
   * @param xRecord
   *            the object to convert
   * @return a Record instance having the same information as the given
   *         xRecord (or null if parameter is null)
   */
  public static Record getRecord(XRecord xRecord) {
    Record result = null;
    if (xRecord != null) {
      result = new Record();
      result.setId(xRecord.getId());
      result.setLabel(xRecord.getLabel());
      result.setContactDocumentId(xRecord.getContact().getDocumentId());
      result.setDemographicsDocumentId(xRecord.getDemographics().getDocumentId());
    }

    return result;
  }

  /**
   * Returns a Demographics instance from a XDemographics object. The returned
   * value is null if given parameter is null.
   *
   * @param xDemographics
   *            the object to convert
   * @return a Demographics instance having the same information as the given
   *         xDemographics (or null if parameter is null)
   */
  public static Demographics getDemographics(XDemographics xDemographics) {
    Demographics result = null;
    if (xDemographics != null) {
      result = new Demographics(
              getDate(xDemographics.getDateOfBirth()),
              getDate(xDemographics.getDateOfDeath()),
              xDemographics.getGender(),
              xDemographics.getEthnicity(),
              xDemographics.getLanguage(),
              xDemographics.getMaritalStatus(),
              xDemographics.getEmploymentStatus(),
              xDemographics.getEmploymentIndustry(),
              xDemographics.getOccupation(),
              xDemographics.getReligion(),
              xDemographics.getIncome(),
              xDemographics.getHighestEducation(),
              xDemographics.isOrganDonor());
    }

    return result;
  }

  /**
   * Returns a Problem instance from a XProblem object. The returned
   * value is null if given parameter is null.
   *
   * @param xProblem
   *            the object to convert
   * @return a Problem instance having the same information as the given
   *         xProblem (or null if parameter is null)
   */
  public static Problem getProblem(XProblem xProblem) {
    Problem result = null;
    if (xProblem != null) {
      result = new Problem(
              getDate(xProblem.getDateOnset()),
              getDate(xProblem.getDateResolution()),
              getCodedValue(xProblem.getName()),
              xProblem.getComments(),
              xProblem.getDiagnosedBy());
    }

    return result;
  }

  /**
   * Returns a Medication instance from a XMedication object. The returned
   * value is null if given parameter is null.
   *
   * @param xMedication
   *            the object to convert
   * @return a Medication instance having the same information as the given
   *         xMedication (or null if parameter is null)
   */
  public static Medication getMedication(XMedication xMedication) {
    Medication result = null;
    if (xMedication != null) {
      result = new Medication(
              getDate(xMedication.getDateStarted()),
              getDate(xMedication.getDateStopped()),
              xMedication.getReasonStopped(),
              getCodedValue(xMedication.getName()),
              getCodedValue(xMedication.getBrandName()),
              getValueAndUnit(xMedication.getDose()),
              getCodedValue(xMedication.getRoute()),
              getValueAndUnit(xMedication.getStrength()),
              getCodedValue(xMedication.getFrequency()),
              getPrescription(xMedication.getPrescription()),
              xMedication.getDetails());
    }

    return result;
  }

  /**
   * Returns a Prescription instance from a XPrescription object. The returned
   * value is null if given parameter is null.
   *
   * @param xPrescription
   *            the object to convert
   * @return a Prescription instance having the same information as the given
   *         xPrescription (or null if parameter is null)
   */
  public static Prescription getPrescription(XPrescription xPrescription) {
    Prescription result = null;
    if (xPrescription != null) {
      result = new Prescription(
              getProvider(xPrescription.getBy()),
              getDate(xPrescription.getOn()),
              getDate(xPrescription.getStopOn()),
              xPrescription.isDispenseAsWritten(),
              getDuration(xPrescription.getDuration()),
              xPrescription.getRefillInfo(),
              xPrescription.getInstructions());
    }

    return result;
  }

  /**
   * Returns an AuthSystem instance from a XAccount.AuthSystem object. The
   * returned value is null if given parameter is null.
   *
   * @param xAuthSystem
   *            the object to convert
   * @return a AuthSystem instance having the same information as the given
   *         xAuthSystem (or null if parameter is null)
   */
  private static AuthSystem getAuthSystem(XAccount.AuthSystem xAuthSystem) {
    AuthSystem result = null;
    if (xAuthSystem != null) {
      result = new AuthSystem();
      result.setName(xAuthSystem.getName());
      result.setUserName(xAuthSystem.getUsername());
    }

    return result;
  }

  /**
   * Returns a DocumentStatusHistory instance from a XDocumentStatusHistory
   * object. The returned value is null if given parameter is null.
   *
   * @param xDocumentStatusHistory
   *            the object to convert
   * @return a DocumentStatusHistory instance having the same information as
   *         the given xDocumentStatusHistory (or null if parameter is null)
   */
  public static DocumentStatusHistory getDocumentStatusHistory(XDocumentStatusHistory xDocumentStatusHistory) {
    DocumentStatusHistory result = null;
    if (xDocumentStatusHistory != null) {
      result = new DocumentStatusHistory();
      result.setDocumentId(xDocumentStatusHistory.getDocumentId());
      List<DocumentStatus> documentStatusList = new ArrayList<DocumentStatus>();
      for (XDocumentStatus xDocumentStatus : xDocumentStatusHistory.getDocumentStatus()) {
        DocumentStatus documentStatus = getDocumentStatus(xDocumentStatus);
        if (documentStatus != null) {
          documentStatusList.add(documentStatus);
        }
      }
      result.setDocumentStatus(documentStatusList);
    }

    return result;
  }

  /**
   * Returns a DocumentStatus instance from a XDocumentStatus object. The
   * returned value is null if given parameter is null.
   *
   * @param xDocumentStatus
   *            the object to convert
   * @return a DocumentStatus instance having the same information as the
   *         given xDocumentStatus (or null if parameter is null)
   */
  public static DocumentStatus getDocumentStatus(XDocumentStatus xDocumentStatus) {
    DocumentStatus result = null;
    if (xDocumentStatus != null) {
      result = new DocumentStatus();
      result.setAt(getDate(xDocumentStatus.getAt()));
      result.setBy(xDocumentStatus.getBy());
      result.setReason(xDocumentStatus.getReason());
      result.setStatus(xDocumentStatus.getStatus());
    }

    return result;
  }

  /**
   * Returns a DocumentMeta instance from a XDocumentMeta object. The returned
   * value is null if given parameter is null.
   *
   * @param xDocumentMeta
   *            the object to convert
   * @return a DocumentMeta instance having the same information as the given
   *         xDocumentMeta (or null if parameter is null)
   */
  public static DocumentMeta getDocumentMeta(XDocumentMeta xDocumentMeta) {
    DocumentMeta result = null;
    if (xDocumentMeta != null) {
      result = new DocumentMeta();
      result.setCreatedAt(getDate(xDocumentMeta.getCreatedAt()));
      result.setCreator(getPrincipal(xDocumentMeta.getCreator()));
      result.setSuppressedAt(getDate(xDocumentMeta.getSuppressedAt()));
      result.setSuppressor(getPrincipal(xDocumentMeta.getSuppressor()));
      result.setReplacedBy(xDocumentMeta.getReplacedBy() == null ? null
              : xDocumentMeta.getReplacedBy().getId());
      result.setReplaces(xDocumentMeta.getReplaces() == null ? null
              : xDocumentMeta.getReplaces().getId());
      result.setOriginal(xDocumentMeta.getOriginal() == null ? null
              : xDocumentMeta.getOriginal().getId());
      if (xDocumentMeta.getLatest() != null) {
        result.setLatestId(xDocumentMeta.getLatest().getId());
        result.setLatestCreatedAt(getDate(xDocumentMeta.getLatest().getCreatedAt()));
        result.setLatestCreatedBy(xDocumentMeta.getLatest().getCreatedBy());
      }
      result.setLabel(xDocumentMeta.getLabel());
      result.setStatus(xDocumentMeta.getStatus());
      result.setNevershare(xDocumentMeta.isNevershare());
      if (xDocumentMeta.getRelatesTo() != null) {
        List<Relation> relatesTo = new ArrayList<Relation>();
        for (XRelation xrel : xDocumentMeta.getRelatesTo().getRelation()) {
          Relation rel = new Relation(xrel.getType(), xrel.getCount());
          relatesTo.add(rel);
        }
        result.setRelatesTo(relatesTo);
      }
      if(xDocumentMeta.getIsRelatedFrom() != null) {
        List<Relation> isRelatedFrom = new ArrayList<Relation>();
        for (XRelation xrel : xDocumentMeta.getIsRelatedFrom().getRelation()) {
          Relation rel = new Relation(xrel.getType(), xrel.getCount());
          isRelatedFrom.add(rel);
        }
        result.setIsRelatedFrom(isRelatedFrom);
      }
      result.setId(xDocumentMeta.getId());
      result.setRecord_id(xDocumentMeta.getRecordId());
      result.setSize(xDocumentMeta.getSize());
      result.setDigest(xDocumentMeta.getDigest());
      result.setType(xDocumentMeta.getType());
    }

    return result;
  }

  /**
   * Returns a List of DocumentMeta instances from a XDocumentMetaList object.
   * The returned value is null if given parameter is null.
   *
   * @param xDocumentMetaList
   *            the object to convert
   * @return a List of DocumentMeta instances having the same information as
   *         the given xDocumentMetaList (or null if parameter is null)
   */
  public static List<DocumentMeta> getDocumentMetaList(XDocumentMetaList xDocumentMetaList) {
    List<DocumentMeta> result = null;
    if (xDocumentMetaList != null) {
      result = new ArrayList<DocumentMeta>();
      for (XDocumentMeta xMeta : xDocumentMetaList.getDocument()) {
        DocumentMeta meta = DocumentFactory.getDocumentMeta(xMeta);
        result.add(meta);
      }
    }

    return result;
  }

  /**
   * Returns a Principal instance from a XPrincipal object. The returned value
   * is null if given parameter is null.
   *
   * @param xPrincipal
   *            the object to convert
   * @return a Principal instance having the same information as the given
   *         xPrincipal (or null if parameter is null)
   */
  public static Principal getPrincipal(XPrincipal xPrincipal) {
    Principal result = null;
    if (xPrincipal != null) {
      String fullname = xPrincipal.getFullname();
      String id = xPrincipal.getId();
      String type = xPrincipal.getType();
      result = new Principal(fullname, id, type);
    }

    return result;
  }

  /**
   * Returns a CodedValue instance from a XCodedValue object. The returned
   * value is null if given parameter is null.
   *
   * @param xCodedValue
   *            the object to convert
   * @return a CodedValue instance having the same information as the given
   *         xCodedValue (or null if parameter is null)
   */
  public static CodedValue getCodedValue(XCodedValue xCodedValue) {
    CodedValue result = null;
    if (xCodedValue != null) {
      URI type = getURI(xCodedValue.getType());
      String value = xCodedValue.getCodeValue();
      String abbrev = xCodedValue.getAbbrev();
      String humanReadable = xCodedValue.getValue();
      result = new CodedValue(humanReadable, type, value, abbrev);
    }

    return result;
  }

  /**
   * Returns a ValueAndUnit instance from a XValueAndUnit object. The returned
   * value is null if given parameter is null.
   *
   * @param xValueAndUnit
   *            the object to convert
   * @return a ValueAndUnit instance having the same information as the given
   *         xValueAndUnit (or null if parameter is null)
   */
  public static ValueAndUnit getValueAndUnit(XValueAndUnit xValueAndUnit) {
    ValueAndUnit result = null;
    if (xValueAndUnit != null) {
      result = new ValueAndUnit(
              xValueAndUnit.getValue(),
              xValueAndUnit.getTextValue(),
              getCodedValue(xValueAndUnit.getUnit()));
    }

    return result;
  }

  /**
   * Returns a ValueAndUnit instance from a XValueAndUnit object. The returned
   * value is null if given parameter is null.
   *
   * @param xValueAndUnit
   *            the object to convert
   * @return a ValueAndUnit instance having the same information as the given
   *         xValueAndUnit (or null if parameter is null)
   */
  public static Long getDuration(Duration xDuration) {
    Long result = null;
    if (xDuration != null) {
      result = xDuration.getTimeInMillis(new Date());
    }
    return result;
  }

  /**
   * Returns a Date instance from a XMLGregorianCalendar object. The returned
   * value is null if given parameter is null.
   *
   * @param calendar
   *            the object to convert
   * @return a Date value representing the same date as the one represented in
   *         the given calendar parameter (or null if parameter is null)
   */
  private static Date getDate(XMLGregorianCalendar calendar) {
    Date result = null;
    if (calendar != null) {
      result = calendar.toGregorianCalendar().getTime();
    }

    return result;
  }

  /**
   * Returns a URI instance from a String object. The returned value is null
   * if given parameter is null.
   *
   * @param uri
   *            the object to convert
   * @return a URI instance representing the one in the given uri parameter
   *         (or null if parameter is null)
   */
  private static URI getURI(String uri) {
    URI result = null;
    if (uri != null) {
      try {
        result = new URI(uri);
      }
      catch (URISyntaxException e) {
        log.warning(e.getMessage());
        throw new IllegalArgumentException("Impossible to create URI object from given String : Wrong syntax", e);
      }
    }

    return result;
  }

  /**
   * Returns an AnyDocument instance which encapsulates given object.
   *
   * @param object
   *            the document content
   * @return an AnyDocument instance
   */
  private static AnyDocument getUnidentifiedDocument(Object object) {
    return new AnyDocument(object);
  }
}
