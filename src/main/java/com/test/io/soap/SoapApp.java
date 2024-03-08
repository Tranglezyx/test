package com.test.io.soap;

import com.test.io.IOUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.json.XML;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

/**
 * @author trangle
 */
public class SoapApp {

    public static final String SOAP_URL = "http://10.0.62.132/crc/esbservice/";
    public static final String ESB_SN = "#{ESB_SN}";
    public static final String REQUEST_DATA = "#{REQUEST_DATA}";
    public static final String SMS_FILE_NAME = "sms-soap.xml";
    public static final String EMAIL_FILE_NAME = "email-soap.xml";
    public static final String SOAP = "soap.xml";
    public static final String CONTENT_TYPE = "text/xml;charset=UTF-8";

    public static void main(String[] args) throws Exception {
        sms();
//        email();
//        System.out.println(generateMsg(EMAIL_FILE_NAME));
//        System.out.println(generateMsg(SMS_FILE_NAME));
//        System.out.println(HttpClientUtils.request(SOAP_URL, generateMsg(EMAIL_FILE_NAME), CONTENT_TYPE));
    }

    public static void email() throws Exception {
        sender(EMAIL_FILE_NAME);
    }

    public static void sms() throws Exception {
        sender(SMS_FILE_NAME);
    }

    public static void sender(String fileName) throws Exception {
        String msg = generateMsg(fileName);

        URL url = new URL(SOAP_URL);
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        httpConn.setRequestProperty("Content-Length",
                String.valueOf(msg.getBytes().length));
        httpConn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
        httpConn.setRequestMethod("POST");
        httpConn.setDoOutput(true);
        httpConn.setDoInput(true);

        OutputStream outputStream = httpConn.getOutputStream();
        outputStream.write(msg.getBytes());

        if (200 != (httpConn.getResponseCode())) {
            throw new Exception("HTTP Request is not success, Response code is " + httpConn.getResponseCode());
        }

        // 获取HTTP响应数据
        InputStreamReader isr = new InputStreamReader(
                httpConn.getInputStream(), "utf-8");
        BufferedReader inReader = new BufferedReader(isr);
        StringBuffer result = new StringBuffer();
        String inputLine;
        while ((inputLine = inReader.readLine()) != null) {
            result.append(inputLine);
        }
        System.out.println(XML.toJSONObject(result.toString()));
    }

    public static String generateMsg(String fileName) throws Exception {
        String soap = IOUtils.bufferReader(SoapApp.class.getClassLoader().getResource(SOAP).getPath()).toString();
        String msg = IOUtils.bufferReader(SoapApp.class.getClassLoader().getResource(fileName).getPath()).toString();
        soap = soap.replace(ESB_SN, generateCode("0070", "000001"));
        return soap.replace(REQUEST_DATA, Base64.getEncoder().encodeToString(msg.getBytes()));
    }

    public static String generateCode(String systemCode, String sequence) {
        return systemCode + DateFormatUtils.format(System.currentTimeMillis(), "yyyyMMddHHssmm") + sequence;
    }


}
