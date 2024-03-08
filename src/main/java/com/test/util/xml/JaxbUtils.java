package com.test.util.xml;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Source;
import javax.xml.transform.sax.SAXSource;
import java.io.StringReader;

/**
 * @author trangle
 */
public class JaxbUtils {

    public static void object2Xml(Object object) throws JAXBException {

        JAXBContext cxt = JAXBContext.newInstance(object.getClass());
        Marshaller marshaller = cxt.createMarshaller();

        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");// 编码格式
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);// 是否格式化生成的xml串
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, false);// 默认false表示xml指令存在

        marshaller.marshal(object, System.out);
    }

    public static Object xml2Object(Class<?> clazz, String xmlContent) throws Exception {
        JAXBContext jc = JAXBContext.newInstance(clazz);
        Unmarshaller unmarshaller = jc.createUnmarshaller();

        StringReader reader = new StringReader(xmlContent);

        SAXParserFactory sax = SAXParserFactory.newInstance();
        sax.setNamespaceAware(false);
        XMLReader xmlReader = sax.newSAXParser().getXMLReader();

        Source source = new SAXSource(xmlReader, new InputSource(reader));

        return unmarshaller.unmarshal(source);
    }
}
