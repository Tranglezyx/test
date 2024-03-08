package com.test.io.soap.dto;

import javax.xml.bind.annotation.XmlElement;

/**
 * @author trangle
 */
public class EsbResponse {

    @XmlElement(name = "esb:RETURN_CODE")
    private String returnCode;

    @XmlElement(name = "esb:RETURN_DESC")
    private String returnDesc;

    @XmlElement(name = "esb:RETURN_DATA")
    private String returnData;

    public EsbResponse setReturnCode(String returnCode) {
        this.returnCode = returnCode;
        return this;
    }

    public EsbResponse setReturnDesc(String returnDesc) {
        this.returnDesc = returnDesc;
        return this;
    }

    public EsbResponse setReturnData(String returnData) {
        this.returnData = returnData;
        return this;
    }
}
