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

package com.orange.jlinx;

/**
 * This class handles the identification of an Indivo record. It can be based either on
 * a record ID or a carenet ID. This class offers a single place to manage this dual aspect.
 * 
 * @author dev-indivo@brialon.com
 *
 */
public class RecordId {
	private String recordId;
	private String carenetId;
	
	/**
	 * Create a new RecordId instance from a record ID or a carenet ID.
	 * It means one of the parameters should be bull.
	 * If both are not null, then the carenet ID has the priority to identify the record.
	 * If both are null, then an IndivoException is thrown.
	 * 
	 * @param recordId
	 * @param carenetId
	 * @throws IndivoException if both recordId and carenetId are null
	 */
	public RecordId(String recordId, String carenetId) throws IndivoException {
		if(recordId==null || recordId.trim().isEmpty())
			this.recordId=null;
		else
			this.recordId = recordId;
		if(carenetId==null || carenetId.trim().isEmpty())
			this.carenetId=null;
		else
			this.carenetId = carenetId;
		if(this.recordId==null && this.carenetId==null)
			throw new IndivoException("Both recordId and carenetId are null !");
	}

	/**
	 * @return true if encapsulated ID is a carenet ID
	 */
	public boolean isCarenet(){
		return this.carenetId!=null;
	}
	
	/**
	 * @return the encapsulated ID of the record (either a carenet or record ID) 
	 */
	public String getId(){
		return this.isCarenet()?this.carenetId:this.recordId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RecordId [");
		if (getId() != null) {
			builder.append("id=");
			builder.append(getId());
			builder.append(", ");
		}
		builder.append("isCarenet=");
		builder.append(isCarenet());
		builder.append("]");
		return builder.toString();
	}
	
}
