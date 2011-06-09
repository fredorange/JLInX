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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * DocumentMeta represents the document meta data information used in Indivo documents. 
 * 
 * @author dev-indivo@brialon.com
 *
 */
public class DocumentMeta {
    private Date createdAt;
    private Principal creator;
    private Date suppressedAt;
    private Principal suppressor;
    private String replacedBy;
    private String replaces;
    private String original;
    private String latestId;
    private Date latestCreatedAt;
    private String latestCreatedBy;
    private String label;
    private String id;
    private String size;
    private String digest;
    private String type;
    
    private String status;
    private Boolean nevershare;
    private List<Relation> relatesTo;
    private List<Relation> isRelatedFrom;
    private String record_id;

    
	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public Boolean getNevershare() {
		return nevershare;
	}


	public void setNevershare(Boolean nevershare) {
		this.nevershare = nevershare;
	}


	public List<Relation> getRelatesTo() {
		return relatesTo;
	}


	public void setRelatesTo(List<Relation> relatesTo) {
		this.relatesTo = relatesTo;
	}


	public List<Relation> getIsRelatedFrom() {
		return isRelatedFrom;
	}


	public void setIsRelatedFrom(List<Relation> isRelatedFrom) {
		this.isRelatedFrom = isRelatedFrom;
	}


	public String getRecord_id() {
		return record_id;
	}


	public void setRecord_id(String recordId) {
		record_id = recordId;
	}


	/**
	 * @return the createdAt
	 */
	public Date getCreatedAt() {
		return createdAt;
	}


	/**
	 * @param createdAt the createdAt to set
	 */
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}


	/**
	 * @return the creator
	 */
	public Principal getCreator() {
		return creator;
	}


	/**
	 * @param creator the creator to set
	 */
	public void setCreator(Principal creator) {
		this.creator = creator;
	}


	/**
	 * @return the suppressedAt
	 */
	public Date getSuppressedAt() {
		return suppressedAt;
	}


	/**
	 * @param suppressedAt the suppressedAt to set
	 */
	public void setSuppressedAt(Date suppressedAt) {
		this.suppressedAt = suppressedAt;
	}


	/**
	 * @return the suppressor
	 */
	public Principal getSuppressor() {
		return suppressor;
	}


	/**
	 * @param suppressor the suppressor to set
	 */
	public void setSuppressor(Principal suppressor) {
		this.suppressor = suppressor;
	}


	/**
	 * @return the replacedBy
	 */
	public String getReplacedBy() {
		return replacedBy;
	}


	/**
	 * @param replacedBy the replacedBy to set
	 */
	public void setReplacedBy(String replacedBy) {
		this.replacedBy = replacedBy;
	}


	/**
	 * @return the replaces
	 */
	public String getReplaces() {
		return replaces;
	}


	/**
	 * @param replaces the replaces to set
	 */
	public void setReplaces(String replaces) {
		this.replaces = replaces;
	}


	/**
	 * @return the original
	 */
	public String getOriginal() {
		return original;
	}


	/**
	 * @param original the original to set
	 */
	public void setOriginal(String original) {
		this.original = original;
	}


	/**
	 * @return the latestId
	 */
	public String getLatestId() {
		return latestId;
	}


	/**
	 * @param latestId the latestId to set
	 */
	public void setLatestId(String latestId) {
		this.latestId = latestId;
	}


	/**
	 * @return the latestCreatedAt
	 */
	public Date getLatestCreatedAt() {
		return latestCreatedAt;
	}


	/**
	 * @param latestCreatedAt the latestCreatedAt to set
	 */
	public void setLatestCreatedAt(Date latestCreatedAt) {
		this.latestCreatedAt = latestCreatedAt;
	}


	/**
	 * @return the latestCreatedBy
	 */
	public String getLatestCreatedBy() {
		return latestCreatedBy;
	}


	/**
	 * @param latestCreatedBy the latestCreatedBy to set
	 */
	public void setLatestCreatedBy(String latestCreatedBy) {
		this.latestCreatedBy = latestCreatedBy;
	}


	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}


	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}


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
	 * @return the size
	 */
	public String getSize() {
		return size;
	}


	/**
	 * @param size the size to set
	 */
	public void setSize(String size) {
		this.size = size;
	}


	/**
	 * @return the digest
	 */
	public String getDigest() {
		return digest;
	}


	/**
	 * @param digest the digest to set
	 */
	public void setDigest(String digest) {
		this.digest = digest;
	}


	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}


	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DocumentMeta [");
		if (createdAt != null) {
			builder.append("createdAt=");
			builder.append(new SimpleDateFormat().format(createdAt));
			builder.append(", ");
		}
		if (creator != null) {
			builder.append("creator=");
			builder.append(creator);
			builder.append(", ");
		}
		if (digest != null) {
			builder.append("digest=");
			builder.append(digest);
			builder.append(", ");
		}
		if (id != null) {
			builder.append("id=");
			builder.append(id);
			builder.append(", ");
		}
		if (label != null) {
			builder.append("label=");
			builder.append(label);
			builder.append(", ");
		}
		if (latestCreatedAt != null) {
			builder.append("latestCreatedAt=");
			builder.append(new SimpleDateFormat().format(latestCreatedAt));
			builder.append(", ");
		}
		if (latestCreatedBy != null) {
			builder.append("latestCreatedBy=");
			builder.append(latestCreatedBy);
			builder.append(", ");
		}
		if (latestId != null) {
			builder.append("latestId=");
			builder.append(latestId);
			builder.append(", ");
		}
		if (original != null) {
			builder.append("original=");
			builder.append(original);
			builder.append(", ");
		}
		if (replacedBy != null) {
			builder.append("replacedBy=");
			builder.append(replacedBy);
			builder.append(", ");
		}
		if (replaces != null) {
			builder.append("replaces=");
			builder.append(replaces);
			builder.append(", ");
		}
		if (size != null) {
			builder.append("size=");
			builder.append(size);
			builder.append(", ");
		}
		if (suppressedAt != null) {
			builder.append("suppressedAt=");
			builder.append(new SimpleDateFormat().format(suppressedAt));
			builder.append(", ");
		}
		if (suppressor != null) {
			builder.append("suppressor=");
			builder.append(suppressor);
			builder.append(", ");
		}
		if (type != null) {
			builder.append("type=");
			builder.append(type);
		}
		if (status != null) {
			builder.append("Status=");
			builder.append(status);
		}
		if (relatesTo != null) {
			builder.append("RelatesTo=");
			builder.append(relatesTo);
		}
		if (isRelatedFrom != null) {
			builder.append("IsRelatedFrom=");
			builder.append(isRelatedFrom);
		}
		if (record_id != null) {
			builder.append("record_id=");
			builder.append(record_id);
		}
		builder.append("nevershare=");
		builder.append(nevershare);

		builder.append("]");
		return builder.toString();
	}

}
