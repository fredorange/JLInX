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

import java.net.MalformedURLException;

import com.orange.jlinx.Indivo;
import com.orange.jlinx.IndivoException;

public class DaoFactory {
	
	private static IndivoDao indivoDaoForPha=null;
	private static IndivoDao indivoDaoForAdminApp=null;
	private static AccessTokenDao acessTokenDao=null;

	public static IndivoDao getIndivoDaoForPha() throws DaoException{
		if(DaoFactory.indivoDaoForPha==null){
			Indivo indivo=null;
			try {
				indivo = new Indivo(	SampleConfig.PHA_CONSUMER_KEY,
										SampleConfig.PHA_CONSUMER_SECRET,
										SampleConfig.PHA_APP_ID,
										SampleConfig.INDIVO_SERVER_URL,
										SampleConfig.INDIVO_UI_SERVER_URL);
			} catch (MalformedURLException e) {
				throw new DaoException("Configuration error for Indivo : bad URL.",e);
			} catch (IndivoException e) {
				throw new DaoException("Configuration error for Indivo : unexpected Error.",e);
			}
			DaoFactory.indivoDaoForPha=new IndivoDao(indivo);
		}
		return DaoFactory.indivoDaoForPha;
	}
	
	public static IndivoDao getIndivoDaoForAdminApp() throws DaoException{
		if(DaoFactory.indivoDaoForAdminApp==null){
			Indivo indivo=null;
			try {
				indivo = new Indivo(	SampleConfig.ADMIN_APP_CONSUMER_KEY,
										SampleConfig.ADMIN_APP_CONSUMER_SECRET,
										null,
										SampleConfig.INDIVO_SERVER_URL,
										SampleConfig.INDIVO_UI_SERVER_URL);
			} catch (MalformedURLException e) {
				throw new DaoException("Configuration error for Indivo : bad URL.",e);
			} catch (IndivoException e) {
				throw new DaoException("Configuration error for Indivo : unexpected Error.",e);
			}
			DaoFactory.indivoDaoForAdminApp=new IndivoDao(indivo);
		}
		return DaoFactory.indivoDaoForAdminApp;
	}
	
	public static AccessTokenDao getAccessTokenDao() throws DaoException{
		if(DaoFactory.acessTokenDao==null){
			DaoFactory.acessTokenDao=new AccessTokenDao();
		}
		return DaoFactory.acessTokenDao;
	}
}
