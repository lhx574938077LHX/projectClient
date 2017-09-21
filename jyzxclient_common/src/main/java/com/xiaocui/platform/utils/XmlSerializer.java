package com.xiaocui.platform.utils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class XmlSerializer {

    public static String serializer(Object obj, Class<?> objclass) {
        String xml = "";
        JAXBContext context;
        try {
            context = JAXBContext.newInstance(objclass);
            Marshaller mar = context.createMarshaller();
            mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            mar.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

            StringWriter writer = new StringWriter();
            mar.marshal(obj, writer);
            xml = writer.toString();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return xml;
    }

    @SuppressWarnings("unchecked")
    public static <T> T deserializer(String xml, Class<T> objclass) {
        T result = null;
        JAXBContext context;
        try {
            context = JAXBContext.newInstance(objclass);
            Unmarshaller unmar = context.createUnmarshaller();
            StringReader sr = new StringReader(xml);
            result = (T) unmar.unmarshal(sr);
            sr.close();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return result;
    }
}

