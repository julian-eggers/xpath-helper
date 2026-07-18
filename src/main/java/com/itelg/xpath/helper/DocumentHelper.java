package com.itelg.xpath.helper;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.ParsingException;

public class DocumentHelper
{
    /**
     * Prevent initialization
     */
    private DocumentHelper()
    {

    }

    public static Element getRootElement(String xml) throws SAXException, IOException, ParsingException
    {
        try (InputStream inputStream = new ByteArrayInputStream(xml.getBytes()))
        {
            return getRootElement(inputStream);
        }
    }

    public static Element getRootElement(InputStream inputStream) throws SAXException, IOException, ParsingException
    {
        try (InputStream internal = inputStream)
        {
            Document document = new Builder(createXmlReader()).build(inputStream);
            return document.getRootElement();
        }
    }

    public static Element getRootElement(Reader reader) throws SAXException, IOException, ParsingException
    {
        try (Reader internal = reader)
        {
            Document document = new Builder(createXmlReader()).build(reader);
            return document.getRootElement();
        }
    }

    private static XMLReader createXmlReader() throws SAXException
    {
        try
        {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            factory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
            return factory.newSAXParser().getXMLReader();
        }
        catch (ParserConfigurationException e)
        {
            throw new SAXException(e);
        }
    }
}
