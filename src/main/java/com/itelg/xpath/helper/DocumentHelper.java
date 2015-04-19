package com.itelg.xpath.helper;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;

import org.apache.commons.io.IOUtils;

public class DocumentHelper
{
	public static Element getRootElement(String xml) throws Exception
	{
		InputStream inputStream = new ByteArrayInputStream(xml.getBytes());
		return getRootElement(inputStream);
	}
	
	public static Element getRootElement(InputStream inputStream) throws Exception
	{
		Document document = new Builder().build(inputStream);
		Element rootElement = document.getRootElement();
		IOUtils.closeQuietly(inputStream);
		return rootElement;
	}
}