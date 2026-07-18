package com.itelg.xpath.helper;

import java.io.InputStream;
import java.io.InputStreamReader;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.itelg.xpath.helper.test.support.XmlLoader;

import nu.xom.Element;

class DocumentHelperTest
{
    @Test
    void testGetRootElementString() throws Exception
    {
        String xml = XmlLoader.loadXml("valid.xml");
        Element rootElement = DocumentHelper.getRootElement(xml);
        Assertions.assertNotNull(rootElement);
        Assertions.assertEquals("test", rootElement.getLocalName());
    }

    @Test
    void testGetRootElementInputStream() throws Exception
    {
        try (InputStream xml = XmlLoader.loadXmlStream("valid.xml"))
        {
            Element rootElement = DocumentHelper.getRootElement(xml);
            Assertions.assertNotNull(rootElement);
            Assertions.assertEquals("test", rootElement.getLocalName());
        }
    }

    @Test
    void testGetRootElementReader() throws Exception
    {
        try (InputStream inputStream = XmlLoader.loadXmlStream("valid.xml"))
        {
            Element rootElement = DocumentHelper.getRootElement(new InputStreamReader(inputStream));
            Assertions.assertNotNull(rootElement);
            Assertions.assertEquals("test", rootElement.getLocalName());
        }
    }
}
