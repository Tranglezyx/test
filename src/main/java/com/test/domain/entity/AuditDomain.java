package com.test.domain.entity;


import lombok.Data;

/**
 * <p>
 * description
 * </p>
 *
 * @author Trangle 2018/07/16 15:12
 */
@Data
public class AuditDomain extends Domain {

    private Long objectVersionNumber;

}
