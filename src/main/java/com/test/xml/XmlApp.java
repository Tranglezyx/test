package com.test.xml;

import com.test.util.CommUtils;
import org.apache.commons.io.FileUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.json.XML;

import java.io.File;
import java.io.IOException;
import java.util.List;

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
