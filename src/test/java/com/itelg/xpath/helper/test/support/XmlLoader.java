package com.itelg.xpath.helper.test.support;

import java.io.InputStream;

import org.apache.commons.io.IOUtils;

public class XmlLoader
{
	public static String loadXml(String fileName) throws Exception
	{
		return IOUtils.toString(ClassLoader.getSystemResourceAsStream(fileName));
	}
	
	public static InputStream loadXmlStream(String fileName) throws Exception
	{
		return ClassLoader.getSystemResourceAsStream(fileName);
	}
}