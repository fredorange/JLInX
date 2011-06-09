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

import javax.servlet.http.HttpServlet;

import com.orange.jlinx.template.StartUrlServlet;

/**
 * Servlet implementation class IndivoStartServlet
 */
public class IndivoStartServlet extends StartUrlServlet {

	private static final long serialVersionUID = 1L;
       
    /**
     * @throws DaoException 
     * @see HttpServlet#HttpServlet()
     */
    public IndivoStartServlet() throws DaoException {
        super(DaoFactory.getIndivoDaoForPha().getIndivo());
    }

}
