package com.itelg.xpath.documentation;

import com.itelg.xpath.helper.DocumentHelper;
import com.itelg.xpath.helper.XPathHelper;

import nu.xom.Element;

public class PlainParserDocumentation
{
	public static void main(String[] args) throws Exception
	{
		String xml = "<data><stringValue>Test</stringValue>"
				+ "<doubleValue>1.12</doubleValue></data>";
		Element rootElement = DocumentHelper.getRootElement(xml);
		System.out.println(XPathHelper.getString("stringValue", rootElement));
		System.out.println(XPathHelper.getDouble("doubleValue", rootElement));
	}
}