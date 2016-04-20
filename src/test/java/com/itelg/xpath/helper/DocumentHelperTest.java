package com.itelg.xpath.helper;

import java.io.InputStream;
import java.io.InputStreamReader;

import org.junit.Assert;
import org.junit.Test;

import com.itelg.xpath.helper.test.support.XmlLoader;

import nu.xom.Element;

public class DocumentHelperTest
{
    @Test(expected = IllegalAccessException.class)
    public void testPrivateConstructor() throws InstantiationException, IllegalAccessException
    {
        DocumentHelper.class.newInstance();
        Assert.fail("Constructor should be private");
    }

    @Test
    public void testGetRootElementString() throws Exception
    {
        String xml = XmlLoader.loadXml("valid.xml");
        Element rootElement = DocumentHelper.getRootElement(xml);
        Assert.assertNotNull(rootElement);
        Assert.assertEquals("test", rootElement.getLocalName());
    }

    @Test
    public void testGetRootElementInputStream() throws Exception
    {
        try (InputStream xml = XmlLoader.loadXmlStream("valid.xml"))
        {
            Element rootElement = DocumentHelper.getRootElement(xml);
            Assert.assertNotNull(rootElement);
            Assert.assertEquals("test", rootElement.getLocalName());
        }
    }

    @Test
    public void testGetRootElementReader() throws Exception
    {
        try (InputStream inputStream = XmlLoader.loadXmlStream("valid.xml"))
        {
            Element rootElement = DocumentHelper.getRootElement(new InputStreamReader(inputStream));
            Assert.assertNotNull(rootElement);
            Assert.assertEquals("test", rootElement.getLocalName());
        }
    }
}