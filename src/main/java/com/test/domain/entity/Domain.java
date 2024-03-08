package com.test.domain.entity;

import lombok.Data;

import java.util.Date;

/**
 * <p>
 * description
 * </p>
 *
 */
@Data
public class Domain {

    private Date creationDate;
    private Date lastUpdateDate;
    private Long createdBy;
    private Long lastUpdatedBy;
    private Long objectVersionNumber;

}
