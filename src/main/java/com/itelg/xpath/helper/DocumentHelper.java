package com.itelg.xpath.helper;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.ParsingException;

@SuppressWarnings("deprecation")
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
            XMLReader xmlReader = XMLReaderFactory.createXMLReader();
            xmlReader.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);

            Document document = new Builder(xmlReader).build(inputStream);
            return document.getRootElement();
        }
    }

    public static Element getRootElement(Reader reader) throws SAXException, IOException, ParsingException
    {
        try (Reader internal = reader)
        {
            XMLReader xmlReader = XMLReaderFactory.createXMLReader();
            xmlReader.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);

            Document document = new Builder(xmlReader).build(reader);
            return document.getRootElement();
        }
    }
}
