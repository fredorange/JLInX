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
 * Relation represents the data type of the same name used in Indivo documents. 
 * 
 * @author fp
 *
 */
public class Relation {
	private String type;
	private int count;

	/**
	 * (This is the full constructor)
	 * Creates a new Relation instance, using all available information.
	 * 
	 * @param type
	 * @param count
	 */
	public Relation(String type, int count) {
		this.type = type;
		this.count = count;
	}

	/**
	 * Creates a new Relation instance using the minimal set of required information. 
	 * @param type
	 */
	public Relation(String type) {
		this(type,0);
	}
	/**
	 * (This is the null constructor)
	 * Creates a new Relation instance. 
	 * @param type
	 */
	public Relation() {
		this(null,0);
	}


	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Relation [");
		if (type != null) {
			builder.append("type=");
			builder.append(type);
			builder.append(", ");
		}
		builder.append("count=");
		builder.append(count);
		
		builder.append("]");
		return builder.toString();
	}
	

}
