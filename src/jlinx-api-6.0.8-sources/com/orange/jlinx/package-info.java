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
 * The root package of JLInX contains essential classes most of which are API internal.
 * The Indivo class is the main API entry point. One instance of this class links 1 client application to 1 Indivo server.
 * An Indivo instance creates a TransportManager object which handles all the communications with the Indivo server.
 * This object encapsulates the server's identification information plus the application consumer key.
 * OAuth authentication mechanisms (getting tokens) are managed by class AuthenticationManager, which instance is created by the Indivo object.
 * This class relies on the TransportManager object for communicating with the Indivo server.
 * The use of the Indivo API services, based on REST HTTP requests and XML documents exchange, is managed by the ServiceManager class.
 * This class knows the URL of all Indivo services through the ServiceUrlBuilder class and provides XML transformations.
 * 
 * Here is a brief description of subpackages:
 * - com.orange.jlinx.admin: dedicated to Indivo administrative services
 * - com.orange.jlinx.auth: dedicated to OAuth authentication mechanisms
 * - com.orange.jlinx.document: dedicated to Indivo document services
 * - com.orange.jlinx.messaging: dedicated to Indivo messaging services
 * 
 * - com.orange.jlinx.template: contains abstract servlets which may be a base for PHA OAuth management
 * - com.orange.jlinx.sample.pha: contains a demo PHA based on JLInX
 * - com.orange.jlinx.test: contains all JUnit tests to validate the API
 * - com.orange.jlinx.lab: used for development purpose
 * 
 * @author dev-indivo@brialon.com
 */

package com.orange.jlinx;

