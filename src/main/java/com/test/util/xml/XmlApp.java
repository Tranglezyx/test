package com.test.util.xml;

import com.test.util.CommUtils;
import org.apache.commons.io.FileUtils;
import org.dom4j.DocumentException;

import java.io.File;
import java.io.IOException;

/**
 * @author trangle
 */
public class XmlApp {

    public static void main(String[] args) throws DocumentException, IOException {
        String xml = FileUtils.readFileToString(new File(CommUtils.getClasspathUrl() + "response.xml"), "UTF-8");
        System.out.println(xml);
        System.out.println();
        System.out.println();
    }
}
