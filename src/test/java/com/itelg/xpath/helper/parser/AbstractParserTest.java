package com.itelg.xpath.helper.parser;

import nu.xom.Element;

import org.junit.Assert;
import org.junit.Test;

import com.itelg.xpath.helper.test.support.XmlLoader;
import com.itelg.zkoss.helper.XPathHelper;
import com.itelg.zkoss.helper.parser.AbstractParser;

public class AbstractParserTest
{
	@Test
	public void testDoParse() throws Exception
	{
		TestParser parser = new TestParser();
		TestObject object = parser.parse(XmlLoader.loadXml("valid.xml"));
		Assert.assertEquals("TEST", object.getValue());
	}
	
	@Test(expected = Exception.class)
	public void testDoParseFileNotFound() throws Exception
	{
		TestParser parser = new TestParser();
		parser.parse(XmlLoader.loadXml("notfound.xml"));
	}
	
	@Test(expected = Exception.class)
	public void testDoParseInvalidXml() throws Exception
	{
		TestParser parser = new TestParser();
		parser.parse("<test/></test>");
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