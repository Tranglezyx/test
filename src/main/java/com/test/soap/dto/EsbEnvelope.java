package com.test.soap.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author trangle
 */

@XmlRootElement(name = "soap:Envelope",namespace = "soap")
public class EsbEnvelope {


    @XmlElement(name = "soap:Body")
    private EsbBody body;

    public EsbEnvelope setBody(EsbBody body) {
        this.body = body;
        return this;
    }
}
