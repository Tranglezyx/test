package com.test.dto;

import java.util.Date;

/**
 * <p>
 * description
 * </p>
 *
 * @author yunxiang.zhou01@hand-china.com 2018/07/16 19:49
 */
public class Domain {

    private Date creationDate;
    private Date lastUpdateDate;
    private Long createdBy;
    private Long lastUpdatedBy;
    private Long objectVersionNumber;

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Long getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(Long lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Long getObjectVersionNumber() {
        return objectVersionNumber;
    }

    void setObjectVersionNumber(Long objectVersionNumber) {
        this.objectVersionNumber = objectVersionNumber;
    }
}
