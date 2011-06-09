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
 * The IndivoException is thrown whenever an error occur during an operation performed within the API.
 * Throwing this exception is intended for the exclusive use of API internal classes.
 * 
 * @author dev-indivo@brialon.com
 *
 */
public class IndivoException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Builds an IndivoException using both dedicated message and encapsulated Exception.
	 * 
	 * @param message	message explaining the reason of the exception
	 * @param t         the encapsulated causing exception
	 */
	public IndivoException(String message, Throwable t) {
		super(message, t);
	}

	/**
	 * Builds an IndivoException using a dedicated message.
	 * 
	 * @param message	message explaining the reason of the exception
	 */
	public IndivoException(String message) {
		super(message);
	}
}
