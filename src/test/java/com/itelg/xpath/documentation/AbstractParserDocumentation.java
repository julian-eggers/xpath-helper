package com.itelg.xpath.documentation;

import com.itelg.xpath.helper.XPathHelper;
import com.itelg.xpath.helper.parser.AbstractParser;

import nu.xom.Element;

public class AbstractParserDocumentation
{
	public static void main(String[] args) throws Exception
	{
		String xml = "<data><stringValue>Test</stringValue>"
				+ "<doubleValue>1.12</doubleValue>"
				+ "<testEnum>VALUE</testEnum></data>";
		TestClass testClass = new TestParser().parse(xml);
		System.out.println(testClass);
	}
	
	public static class TestParser extends AbstractParser<TestClass>
	{
		@Override
		protected TestClass doParse(Element rootElement) throws Exception
		{
			TestClass test = new TestClass();
			test.stringValue = XPathHelper.getString("stringValue", rootElement);
			test.doubleValue = XPathHelper.getDouble("doubleValue", rootElement);
			test.testEnum = XPathHelper.getEnum("testEnum", TestClass.TestEnum.class, rootElement);
			return test;
		}
	}
	
	public static class TestClass
	{
		private String stringValue;
		private Double doubleValue;
		private TestEnum testEnum;
		
		@Override
		public String toString()
		{
			return "TestClass [stringValue=" + stringValue + ", doubleValue=" + doubleValue + ", testEnum=" + testEnum + "]";
		}

		private enum TestEnum
		{
			VALUE;
		}
	}
}