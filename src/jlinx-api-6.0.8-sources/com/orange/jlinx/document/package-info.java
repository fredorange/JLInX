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
 * This package is dedicated to Indivo document services.
 * Indivo is made to manage medical documents but can store any document.
 * JLInX reflects this philosophy in its structure.
 * The abstract class Document is intended to be extended by all classes handling a specific document content.
 * A DocumentType instance is associated to each.
 * Medical documents are considered as particular and so are gathered into the same subpackage (com.orange.jlinx.document.medical).
 * DocumentManager uses the Java generics facility and so has a Document type class in parameter.
 * So, a DocumentType object is required by the Indivo class when creating the DocumentManager.
 * As a result, the Document class no more appears itself but the class handling the real type (as a subclass of Document).
 *  
 * @author dev-indivo@brialon.com
 */

package com.orange.jlinx.document;

