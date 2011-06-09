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

/**
 * This package is dedicated to OAuth authentication used by Indivo.
 * JLInX uses the SignPost API to deal with the OAuth process.
 * However, some Indivo characteristics require the use of a dedicated OAuthProvider class. Here are these characteristics : <br/>
 * - Indivo does not exactly respect version 0.1a of OAuth, i.e it does not send the "oauth_callback_confirmed" parameter along with the request token.
 * This misleads the standard SignPost provider and corrupts the process.<br/>
 * - Indivo requires the parameter "indivo_record_id" to be sent in the request's body, which is not allowed by the standard provider.<br/>
 * 
 * @author dev-indivo@brialon.com
 */
package com.orange.jlinx.auth;
