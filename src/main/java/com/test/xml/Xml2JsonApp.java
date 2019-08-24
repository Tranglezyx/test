package com.test.xml;

import com.test.util.CommUtils;
import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.json.XML;

import java.io.File;
import java.io.IOException;

/**
 * @author trangle
 */
public class Xml2JsonApp {

    public static void main(String[] args) throws IOException {
        String xml = FileUtils.readFileToString(new File(CommUtils.getClasspathUrl() + "response.xml"), "UTF-8");
        JSONObject jsonObject = XML.toJSONObject(xml);

        String code = jsonObject.getJSONObject("soap:Envelope").
                getJSONObject("soap:Body").
                getJSONObject("esb:RESPONSE").
                getString("esb:RETURN_CODE");

        String desc = jsonObject.getJSONObject("soap:Envelope").
                getJSONObject("soap:Body").
                getJSONObject("esb:RESPONSE").
                getString("esb:RETURN_DESC");
        System.out.println(code + "--" + desc);
    }
}
