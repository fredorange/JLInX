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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

import com.orange.jlinx.IndivoException;
import com.orange.jlinx.RecordId;
import com.orange.jlinx.auth.AccessToken;

public class AccessTokenDao {

	public static final String COMMON_RECORD_TOKEN_NAME="recordTokenStore";
	public static final String COMMON_CARENET_TOKEN_NAME="carenetTokenStore";
	
	private static final String TOKEN_LOCATION = "";
	private static final String TOKEN_FILE_EXTENSION = ".xml";

	public void saveAccessToken(AccessToken token, String tokenName) throws DaoException{
		Properties properties=new Properties();
		properties.setProperty("recordId", token.getRecordId().getId());
		properties.setProperty("isCarenet", new Boolean(token.getRecordId().isCarenet()).toString());
		properties.setProperty("token", token.getToken());
		properties.setProperty("tokenSecret", token.getTokenSecret());
		FileOutputStream outputFile;
		String tokenFileName=getTokenFileName(tokenName);
		try {
			outputFile = new FileOutputStream(tokenFileName);
			properties.storeToXML(outputFile, "token saved on "+new Date());
		} catch (FileNotFoundException e) {
			throw new DaoException("Error when saving token : can't open file "+tokenFileName,e);
		} catch (IOException e) {
			throw new DaoException("Error when saving token : can't write to "+tokenFileName,e);
		}
	}
	
	public AccessToken loadAccessToken(String tokenName) throws DaoException{
		AccessToken result=null;
		Properties properties=new Properties();
		FileInputStream inputFile;
		String tokenFileName=getTokenFileName(tokenName);
		try {
			inputFile = new FileInputStream(tokenFileName);
			properties.loadFromXML(inputFile);
			result=new AccessToken();
			String recordIdProperty=properties.getProperty("recordId");
			boolean isCarenetProperty=properties.getProperty("isCarenet").equals(new Boolean(true).toString());
			RecordId recordId=new RecordId(isCarenetProperty?null:recordIdProperty, isCarenetProperty?recordIdProperty:null);
			result.setRecordId(recordId);
			result.setToken(properties.getProperty("token"));
			result.setTokenSecret(properties.getProperty("tokenSecret"));
		} catch (FileNotFoundException e) {
			throw new DaoException("Error when loading token : can't find file "+tokenFileName,e);
		} catch (InvalidPropertiesFormatException e) {
			throw new DaoException("Error when loading token : wrong format of file "+tokenFileName,e);
		} catch (IOException e) {
			throw new DaoException("Error when loading token : can't read from "+tokenFileName,e);
		} catch (IndivoException e) {
			throw new DaoException("Error when loading token : recordId is null",e);
		}
		return result;
	}
	
	private static String getTokenFileName(String tokenName){
		return TOKEN_LOCATION+tokenName+TOKEN_FILE_EXTENSION;
	}
}
