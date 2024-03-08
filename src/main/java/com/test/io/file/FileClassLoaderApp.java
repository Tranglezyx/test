package com.test.io.file;

import java.io.File;
import java.net.URL;

/**
 * @author trangle
 */
public class FileClassLoaderApp {

    public static void main(String[] args) {
        String filePath = "com.test";
        URL url = FileClassLoaderApp.class.getClassLoader().getResource(filePath.replaceAll("\\.", "/"));
        File classDir = new File(url.getFile());
        System.out.println(classDir);
    }
}
