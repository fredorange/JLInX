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
 * The IndivoNonFatalException is thrown when a non fatal error occur, typically when
 * the Indivo server returns a HTTP 404, meaning given documentId does not exist.
 * Throwing this exception is intended for the exclusive use of API internal classes. 
 * 
 * @author dev-indivo@brialon.com
 *
 */
public class IndivoNonFatalException extends Exception {

	private static final long serialVersionUID = 1L;
	private Integer nonFatalErrorCode=null;

	/**
	 * Build an IndivoNonFatalException with the non-fatal HTTP error code.
	 * 
	 * @param nonFatalErrorCode
	 */
	public IndivoNonFatalException(int nonFatalErrorCode) {
		super("non fatal error");
		this.nonFatalErrorCode=nonFatalErrorCode;
	}

	/**
	 * @return the httpErrorCode
	 */
	public Integer getNonFatalErrorCode() {
		return this.nonFatalErrorCode;
	}

}
