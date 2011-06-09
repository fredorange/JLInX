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

package com.orange.jlinx.document.ext;

/**
 * Principal represents the data type of the same name used in Indivo documents. 
 * 
 * @author dev-indivo@brialon.com
 *
 */
public class Principal {
	private String fullname;
	private String id;
	private String type;
	
	/**
	 * (This is the full constructor)
	 * Creates a new Principal instance, using all available information.
	 * 
	 * @param fullname
	 * @param id	this can be null as it is optional
	 * @param type	this can be null as it is optional
	 */
	public Principal(String fullname, String id, String type) {
		this.id = id;
		this.type = type;
		this.fullname = fullname;
	}

	/**
	 * (This is the minimal constructor)
	 * Creates a new Principal instance using the minimal set of required information. 
	 * @param fullname
	 */
	public Principal(String fullname) {
		this(fullname,null,null);
	}

	/**
	 * @return the fullname
	 */
	public String getFullname() {
		return fullname;
	}

	/**
	 * @param fullname the fullname to set
	 */
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Principal [");
		if (fullname != null) {
			builder.append("fullname=");
			builder.append(fullname);
			builder.append(", ");
		}
		if (id != null) {
			builder.append("id=");
			builder.append(id);
			builder.append(", ");
		}
		if (type != null) {
			builder.append("type=");
			builder.append(type);
		}
		builder.append("]");
		return builder.toString();
	}
	
}
