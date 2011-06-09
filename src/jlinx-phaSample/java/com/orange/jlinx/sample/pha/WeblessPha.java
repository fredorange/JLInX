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

package com.orange.jlinx.sample.pha;

import java.util.Date;

import com.orange.jlinx.Indivo;
import com.orange.jlinx.auth.AccessToken;
import com.orange.jlinx.document.DocumentManager;
import com.orange.jlinx.document.ext.CodedValues;
import com.orange.jlinx.document.ext.DocumentMeta;
import com.orange.jlinx.document.ext.Report;
import com.orange.jlinx.document.medical.VitalSign;

public class WeblessPha {

	/**
	 * @param args
	 * @throws Exception 
	 * @throws DaoException 
	 */
	public static void main(String[] args) throws Exception {
		
		// Indivo object creation
		Indivo indivo=DaoFactory.getIndivoDaoForPha().getIndivo();
		// (see DaoFactory to see how this object is created)
		
		// Retrieve access token previously stored by web app
		AccessToken accessToken;
		try {
			accessToken = DaoFactory.getAccessTokenDao().loadAccessToken(AccessTokenDao.COMMON_RECORD_TOKEN_NAME);
		} catch (DaoException e) {
			throw new Exception("Did not find stored access token : first access to the PHA from Indivo UI.",e);
		}
		
		// Create a DocumentManager to add and read VitalSign documents
		DocumentManager<VitalSign> documentManager=indivo.getDocumentManager(VitalSign.DOCUMENT_TYPE);
		
		// Create a VitalSign object
		// First, create coded values for name and unit (no management of standard coded values exists in the API for instance)
		VitalSign vital=new VitalSign(new Date(), CodedValues.BP_SYS, 12.2, CodedValues.MM_HG, "left arm", "sitting", "correct");
		// Post the VitalSign object to the server
                DocumentMeta doc=documentManager.postDocument(accessToken,vital, false);
		
		// Retrieve the VitalSign Reports from the server
		int nb=0;
		for(Report<VitalSign> report: documentManager.getReports(accessToken, null).getReportList())
                    System.out.println("--------------- report #"+(++nb)+" ---------------\n"+report);
                
                // Change the status
                documentManager.setDocumentStatusById(accessToken, doc.getId(), DocumentManager.STATUS_ARCHIVED , "reason");
                // Delete it
                documentManager.deleteDocumentById(accessToken, doc.getId(), false);
                
                
	}
	
}
