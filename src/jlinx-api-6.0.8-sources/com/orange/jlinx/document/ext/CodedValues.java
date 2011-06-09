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
 * This is an experimental class which keeps standard coded values available to applications.
 * 
 * @author dev-indivo@brialon.com
 * @see <a href="http://wiki.chip.org/indivo/index.php/Coding_Systems">http://wiki.chip.org/indivo/index.php/Coding_Systems</a>
 */
public class CodedValues {
	
	public static final CodedValue KG=new CodedValue("Kilogramm", "kg");
	public static final CodedValue BP_SYS=new CodedValue("Blood Pressure Systolic", "BPSys");
	public static final CodedValue MM_HG=new CodedValue("Milimeters of Mercury", "mmHg");
	public static final CodedValue MMOL_L=new CodedValue("Millimoles per Liter", "mmol/l");

}
