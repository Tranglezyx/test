package com.test.io;

import java.io.*;
import java.util.Date;

/**
 * @author trangle
 */
public class IOUtils {

    public static final byte[] bytes = new byte[1024];

    public static File generateFileAndDir(String url, String fileName) throws Exception {
        File dir = new File(url);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File(url + fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
        return file;
    }

    public static void fileOutputStream(String url, String fileName, String content) throws Exception {
        FileOutputStream outputStream = new FileOutputStream(generateFileAndDir(url, fileName), true);
        byte[] b = content.getBytes();
        outputStream.write(b);
        outputStream.close();
    }

    /**
     * 读取文件
     *
     * @param url
     * @param fileName
     * @throws Exception
     */
    public static void fileInputStream(String url, String fileName) throws Exception {
        File file = new File(url + fileName);
        FileInputStream inputStream = new FileInputStream(file);
        int length;
        while ((length = inputStream.read(bytes)) != -1) {
            System.out.println(new String(bytes, 0, length));
        }
        inputStream.close();
    }

    public static void reader(String url, String fileName) throws Exception {
        FileReader reader = new FileReader(url + fileName);
        char[] chars = new char[1024];
        int length;
        while ((length = reader.read(chars)) != -1) {
            System.out.println(new String(chars, 0, length));
        }
        reader.close();
    }

    public static void writer(String url, String fileName, String content) throws Exception {
        FileWriter fileWriter = new FileWriter(generateFileAndDir(url, fileName), true);
        fileWriter.write(content);
        fileWriter.close();
    }

    /**
     * 读取文件
     *
     * @param url
     * @param fileName
     * @return
     * @throws Exception
     */
    public static StringBuilder bufferReader(String url, String fileName) throws Exception {
        return bufferReader(url + fileName);
    }

    /**
     * 读取文件
     *
     * @param filePath
     * @throws Exception
     */
    public static StringBuilder bufferReader(String filePath) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
        int length;
        char[] chars = new char[1024];
        StringBuilder sb = new StringBuilder();
        while ((length = bufferedReader.read(chars)) != -1) {
            sb.append(new String(chars, 0, length));
        }
        bufferedReader.close();
        return sb;
    }
}
