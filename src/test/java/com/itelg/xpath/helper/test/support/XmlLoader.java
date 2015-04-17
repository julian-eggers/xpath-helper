package com.itelg.xpath.helper.test.support;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;

import org.apache.commons.io.IOUtils;

public class XmlLoader
{
	public static String loadXml(String fileName) throws Exception
	{
		return IOUtils.toString(ClassLoader.getSystemResourceAsStream(fileName));
	}

	private static Element buildDocument(String xml) throws Exception
	{
		InputStream inputStream = new ByteArrayInputStream(xml.getBytes());
		Document document = new Builder().build(inputStream);
		Element rootElement = document.getRootElement();
		IOUtils.closeQuietly(inputStream);

		return rootElement;
	}

	public static Element loadElement(String fileName) throws Exception
	{
		return buildDocument(loadXml(fileName));
	}
}