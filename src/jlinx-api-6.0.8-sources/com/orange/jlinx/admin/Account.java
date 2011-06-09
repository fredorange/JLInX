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

package com.orange.jlinx.admin;

import java.util.Date;
import java.util.List;


/**
 * This class handles the data attached to one Indivo account.
 * 
 * @author dev-indivo@brialon.com
 *
 */
public class Account {
	private String id;
	private String secret;
	private String fullName;
	private String contactEmail;
	private Date lastLoginAt;
	private int totalLoginCount;
	private int failedLoginCount;
	private String state;
	private Date lastStateChange;
	private List<AuthSystem> authSystemList;
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the secret
	 */
	public String getSecret() {
		return secret;
	}
	/**
	 * @param secret the secret to set
	 */
	public void setSecret(String secret) {
		this.secret = secret;
	}
	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}
	/**
	 * @param fullName the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	/**
	 * @return the contactEmail
	 */
	public String getContactEmail() {
		return contactEmail;
	}
	/**
	 * @param contactEmail the contactEmail to set
	 */
	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}
	/**
	 * @return the lastLoginAt
	 */
	public Date getLastLoginAt() {
		return lastLoginAt;
	}
	/**
	 * @param lastLoginAt the lastLoginAt to set
	 */
	public void setLastLoginAt(Date lastLoginAt) {
		this.lastLoginAt = lastLoginAt;
	}
	/**
	 * @return the totalLoginCount
	 */
	public int getTotalLoginCount() {
		return totalLoginCount;
	}
	/**
	 * @param totalLoginCount the totalLoginCount to set
	 */
	public void setTotalLoginCount(int totalLoginCount) {
		this.totalLoginCount = totalLoginCount;
	}
	/**
	 * @return the failedLoginCount
	 */
	public int getFailedLoginCount() {
		return failedLoginCount;
	}
	/**
	 * @param failedLoginCount the failedLoginCount to set
	 */
	public void setFailedLoginCount(int failedLoginCount) {
		this.failedLoginCount = failedLoginCount;
	}
	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @return the lastStateChange
	 */
	public Date getLastStateChange() {
		return lastStateChange;
	}
	/**
	 * @param lastStateChange the lastStateChange to set
	 */
	public void setLastStateChange(Date lastStateChange) {
		this.lastStateChange = lastStateChange;
	}
	/**
	 * @return the authSystemList
	 */
	public List<AuthSystem> getAuthSystemList() {
		return authSystemList;
	}
	/**
	 * @param authSystemList the authSystemList to set
	 */
	public void setAuthSystemList(List<AuthSystem> authSystemList) {
		this.authSystemList = authSystemList;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Account [");
		if (authSystemList != null) {
			builder.append("authSystemList=");
			builder.append(authSystemList);
			builder.append(", ");
		}
		if (contactEmail != null) {
			builder.append("contactEmail=");
			builder.append(contactEmail);
			builder.append(", ");
		}
		builder.append("failedLoginCount=");
		builder.append(failedLoginCount);
		builder.append(", ");
		if (fullName != null) {
			builder.append("fullName=");
			builder.append(fullName);
			builder.append(", ");
		}
		if (id != null) {
			builder.append("id=");
			builder.append(id);
			builder.append(", ");
		}
		if (lastLoginAt != null) {
			builder.append("lastLoginAt=");
			builder.append(lastLoginAt);
			builder.append(", ");
		}
		if (lastStateChange != null) {
			builder.append("lastStateChange=");
			builder.append(lastStateChange);
			builder.append(", ");
		}
		if (secret != null) {
			builder.append("secret=");
			builder.append(secret);
			builder.append(", ");
		}
		if (state != null) {
			builder.append("state=");
			builder.append(state);
			builder.append(", ");
		}
		builder.append("totalLoginCount=");
		builder.append(totalLoginCount);
		builder.append("]");
		return builder.toString();
	}
	
}
