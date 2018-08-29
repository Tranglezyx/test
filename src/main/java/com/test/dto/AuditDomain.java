package com.test.dto;


/**
 * <p>
 * description
 * </p>
 *
 * @author Trangle 2018/07/16 15:12
 */
public class AuditDomain extends Domain {

    private Long objectVersionNumber;

    @Override
    public Long getObjectVersionNumber() {
        return objectVersionNumber;
    }

    @Override
    public void setObjectVersionNumber(Long objectVersionNumber) {
        this.objectVersionNumber = objectVersionNumber;
    }
}
