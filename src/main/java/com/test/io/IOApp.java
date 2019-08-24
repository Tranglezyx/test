package com.test.io;

/**
 * @author trangle
 */
public class IOApp {

    public static final String fileUrl = "/Users/trangle/docker/redis/redis.log";
    public static final String fileName = "sms-soap.xml";
    public static final String url = "../../../";
//    public static final String url = System.getProperty("user.dir") + "/src/main/resources/";

    public static void main(String[] args) throws Exception {
//        String str = System.currentTimeMillis() + new Date().toString() + fileName + "\r\nHello World\r\n";

//        IOUtils.reader(url, fileName);
//        IOUtils.fileInputStream(url, fileName);
//        IOUtils.fileOutputStream(url, fileName, str);
//        IOUtils.writer(url, fileName, str);

//        IOUtils.bufferReader(url,fileName);
        System.out.println(IOUtils.bufferReader(IOApp.class.getClassLoader().getResource(fileName).getPath()));
    }

}
