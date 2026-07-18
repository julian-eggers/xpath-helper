package com.itelg.xpath.helper.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;

import org.junit.jupiter.api.Test;

import com.itelg.xpath.helper.XPathHelper;
import com.itelg.xpath.helper.test.support.XmlLoader;

import nu.xom.Element;

class AbstractParserTest
{
    @Test
    void testParseString() throws Exception
    {
        TestParser parser = new TestParser();
        TestObject object = parser.parse(XmlLoader.loadXml("valid.xml"));
        assertEquals("TEST", object.getValue());
    }

    @Test
    void testParseStringFileNotFound()
    {
        TestParser parser = new TestParser();
        assertThrows(Exception.class, () -> parser.parse(XmlLoader.loadXml("notfound.xml")));
    }

    @Test
    void testParseStringInvalidXml()
    {
        TestParser parser = new TestParser();
        assertThrows(Exception.class, () -> parser.parse("<test/></test>"));
    }

    @Test
    void testParseInputStream() throws Exception
    {
        TestParser parser = new TestParser();
        TestObject object = parser.parse(XmlLoader.loadXmlStream("valid.xml"));
        assertEquals("TEST", object.getValue());
    }

    @Test
    void testParseInputStreamFileNotFound()
    {
        TestParser parser = new TestParser();
        assertThrows(Exception.class, () -> parser.parse(XmlLoader.loadXmlStream("notfound.xml")));
    }

    @Test
    void testParseInputStreamInvalidXml()
    {
        TestParser parser = new TestParser();
        assertThrows(Exception.class, () -> parser.parse(new ByteArrayInputStream("<test/></test>".getBytes())));
    }

    @Test
    void testParseReader() throws Exception
    {
        TestParser parser = new TestParser();
        TestObject object = parser.parse(new InputStreamReader(XmlLoader.loadXmlStream("valid.xml")));
        assertEquals("TEST", object.getValue());
    }

    @Test
    void testParseReaderFileNotFound()
    {
        TestParser parser = new TestParser();
        assertThrows(Exception.class, () -> parser.parse(new InputStreamReader(XmlLoader.loadXmlStream("notfound.xml"))));
    }

    @Test
    void testParseReaderInvalidXml()
    {
        TestParser parser = new TestParser();
        assertThrows(Exception.class, () -> parser.parse(new InputStreamReader(new ByteArrayInputStream("<test/></test>".getBytes()))));
    }

    private static class TestParser extends AbstractParser<TestObject>
    {
        @Override
        public TestObject doParse(Element rootElement) throws Exception
        {
            TestObject object = new TestObject();
            object.setValue(XPathHelper.getString("string", rootElement));

            return object;
        }
    }

    private static class TestObject
    {
        private String value;

        public String getValue()
        {
            return value;
        }

        public void setValue(String value)
        {
            this.value = value;
        }
    }
}
