package com.itelg.xpath.helper.parser;

import java.io.ByteArrayInputStream;

import nu.xom.Element;

import org.junit.Assert;
import org.junit.Test;

import com.itelg.xpath.helper.XPathHelper;
import com.itelg.xpath.helper.parser.AbstractParser;
import com.itelg.xpath.helper.test.support.XmlLoader;

public class AbstractParserTest
{
	@Test
	public void testParseString() throws Exception
	{
		TestParser parser = new TestParser();
		TestObject object = parser.parse(XmlLoader.loadXml("valid.xml"));
		Assert.assertEquals("TEST", object.getValue());
	}
	
	@Test(expected = Exception.class)
	public void testParseStringFileNotFound() throws Exception
	{
		TestParser parser = new TestParser();
		parser.parse(XmlLoader.loadXml("notfound.xml"));
	}
	
	@Test(expected = Exception.class)
	public void testParseStringInvalidXml() throws Exception
	{
		TestParser parser = new TestParser();
		parser.parse("<test/></test>");
	}
	
	@Test
	public void testParseInputStream() throws Exception
	{
		TestParser parser = new TestParser();
		TestObject object = parser.parse(XmlLoader.loadXmlStream("valid.xml"));
		Assert.assertEquals("TEST", object.getValue());
	}
	
	@Test(expected = Exception.class)
	public void testParseInputStreamFileNotFound() throws Exception
	{
		TestParser parser = new TestParser();
		parser.parse(XmlLoader.loadXmlStream("notfound.xml"));
	}
	
	@Test(expected = Exception.class)
	public void testParseInputStreamInvalidXml() throws Exception
	{
		TestParser parser = new TestParser();
		parser.parse(new ByteArrayInputStream("<test/></test>".getBytes()));
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