package com.test.soap.dto;

import javax.xml.bind.annotation.XmlElement;

/**
 * @author trangle
 */
public class EsbBody {

    @XmlElement(name = "esb:RESPONSE")
    private EsbResponse response;

    public EsbBody setResponse(EsbResponse response) {
        this.response = response;
        return this;
    }
}
