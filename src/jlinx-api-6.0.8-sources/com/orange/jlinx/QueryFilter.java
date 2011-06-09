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
 * A QueryFilter object contains all values used to parametrize a query on Indivo documents.
 * 
 * @author dev-indivo@brialon.com
 *
 */
public class QueryFilter implements Cloneable {

  private int offset;
  private int limit;
  private String orderBy;
  private boolean descendingOrder;
  private String status;

  //TODO manage modified_since
  public QueryFilter() {
    this.offset = 0;
    this.limit = 0;
    this.orderBy = null;
    this.descendingOrder = false;
    this.status = null;
  }

  /**
   * Filtering object
   *
   * @param offset indicates which item number to start with.<br>
   *               Default: 0
   * @param limit indicates the maximum number of items to return. This is used in combination with offset to accomplish paging.<br>
   *              Default: 0
   * @param orderBy is dependent on the fields returned in the list of items, and each call must thus define which fields are valid.<br>
   *                Default: null
   * @param descendingOrder Descending order : true or false<br>
   *                        Default: false
   * @param status It pertains to the status of documents and can currently be set to one of three options:<br>
   *               QueryFilter.ARCHIVED<br>
   *               QueryFilter.ACTIVE<br>
   *               QueryFilter.VOID<br>
   *               Default: null
   */
  public QueryFilter(int offset, int limit, String orderBy, boolean descendingOrder, String status) {
    this.offset = offset;                     // Default: 0
    this.limit = limit;                       // Default: 0
    this.orderBy = orderBy;                   // Default: null
    this.descendingOrder = descendingOrder;   // Default: false
    this.status = status;                     // Default: null
  }

  /**
   * @return the descendingOrder
   */
  public boolean getDescendingOrder() {
    return descendingOrder;
  }

  /**
   * @param descendingOrder the descendingOrder to set
   */
  public void setDescendingOrder(boolean descendingOrder) {
    this.descendingOrder = descendingOrder;
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
   * @return the status
   */
  public String getStatus() {
    return status;
  }

  /**
   * @param status the status to set
   */
  public void setStatus(String status) {
    this.status = status;
  }

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("QueryFilter [limit=");
    builder.append(limit);
    builder.append(", ");
    builder.append("offset=");
    builder.append(offset);
    builder.append(", ");
    if (orderBy != null) {
      builder.append("orderBy=");
      builder.append(orderBy);
      builder.append(", ");
    }
    if (descendingOrder) {
      builder.append("descendingOrder=");
      builder.append(descendingOrder);
      builder.append(", ");
    }
    if (status != null) {
      builder.append("status=");
      builder.append(status);
    }
    builder.append("]");
    return builder.toString();
  }

  @Override
  public QueryFilter clone() throws CloneNotSupportedException {
    QueryFilter result = new QueryFilter();
    result.offset = this.getOffset();
    result.limit = this.getLimit();
    result.orderBy = this.getOrderBy();
    result.descendingOrder = this.getDescendingOrder();
    result.status = this.getStatus();
    return result;
  }
}
