package com.test.io.file;

import java.io.File;
import java.net.URL;

/**
 * @author trangle
 */
public class FileClassLoader {

    public File getAllClassFileByPackage(String packagePath){
        URL url = FileClassLoader.class.getClassLoader().getResource(packagePath.replaceAll("\\.", "/"));
        File classDir = new File(url.getFile());
        return classDir;
    }
}
