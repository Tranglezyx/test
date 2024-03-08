package com.test.util.txt;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class TxtApp {

    public static void main(String[] args) throws IOException {
        String filePath = "D:\\txt\\logs\\tpgi\\1.txt";
//        String filePath = "D:\\txt\\logs\\tpgi\\20230712\\1 (2).txt";
        String file = FileUtils.readFileToString(new File(filePath), "UTF-8");
        String[] split = file.split("\n");
        StringBuilder sb = new StringBuilder("");
        for (String str : split) {
            int index = str.indexOf("destId");
            String substring = str.substring(index + 9, index + 20);
            sb.append(substring);
            sb.append("\n");
        }
        String newFilePath = "D:\\txt\\logs\\tpgi\\1-号码提取.txt";
        FileUtils.writeByteArrayToFile(new File(newFilePath),sb.toString().getBytes("utf-8"));
    }
}
