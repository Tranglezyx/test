package com.test.sls;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

@Slf4j
public class ErrorPhoneApp {

    public static void main(String[] args) throws IOException {
        String toString = FileUtils.readFileToString(new File("2023-11-19日发送失败号码.txt"), "utf-8");
        String[] split = toString.split("\n");
        File file = new File("2023-11-19日发送失败号码-1.txt");
        for (String str : split) {
            str += ",20231119235959,9457,9457";
            FileUtils.write(file, str + "\n", "utf-8", true);
        }
    }
}
