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

import java.util.List;

import com.orange.jlinx.document.Document;


/**
 * The Reports class represents the informations contained in an Indivo Reports document.
 * 
 * @author dev-indivo@brialon.com
 *
 * @param <D>	the type of the document present in the report (may be any Document type)
 */
public class Reports<D extends Document> {
    private int totalDocumentCount;
    private int limit;
    private int offset;
    private String orderBy;
    private List<Report<D>> reportList;
    
	/**
	 * @return the totalDocumentCount
	 */
	public int getTotalDocumentCount() {
		return totalDocumentCount;
	}
	/**
	 * @param totalDocumentCount the totalDocumentCount to set
	 */
	public void setTotalDocumentCount(int totalDocumentCount) {
		this.totalDocumentCount = totalDocumentCount;
	}
	/**
	 * @return the limit
	 */
	public int getLimit() {
		return limit;
	}
	/**
	 * @param limit the limit to set
	 */
	public void setLimit(int limit) {
		this.limit = limit;
	}
	/**
	 * @return the offset
	 */
	public int getOffset() {
		return offset;
	}
	/**
	 * @param offset the offset to set
	 */
	public void setOffset(int offset) {
		this.offset = offset;
	}
	/**
	 * @return the orderBy
	 */
	public String getOrderBy() {
		return orderBy;
	}
	/**
	 * @param orderBy the orderBy to set
	 */
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	/**
	 * @return the reportList
	 */
	public List<Report<D>> getReportList() {
		return reportList;
	}
	/**
	 * @param reportList the reportList to set
	 */
	public void setReportList(List<Report<D>> reportList) {
		this.reportList = reportList;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Reports [limit=");
		builder.append(limit);
		builder.append(", offset=");
		builder.append(offset);
		builder.append(", ");
		if (orderBy != null) {
			builder.append("orderBy=");
			builder.append(orderBy);
			builder.append(", ");
		}
		if (reportList != null) {
			builder.append("reportList=");
			builder.append(reportList);
			builder.append(", ");
		}
		builder.append("totalDocumentCount=");
		builder.append(totalDocumentCount);
		builder.append("]");
		return builder.toString();
	}
    
}
