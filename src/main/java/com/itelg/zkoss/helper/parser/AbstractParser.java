package com.itelg.zkoss.helper.parser;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractParser<T> implements Parser<T>
{
	protected static final Logger log = LoggerFactory.getLogger(AbstractParser.class);

	@Override
	public T parse(String xml) throws Exception
	{
		try
		{
			InputStream inputStream = new ByteArrayInputStream(xml.getBytes());
			Document document = new Builder().build(inputStream);
			Element rootElement = document.getRootElement();
			T object = doParse(rootElement);
			IOUtils.closeQuietly(inputStream);

			return object;
		}
		catch (Exception e)
		{
			log.warn(xml, e);
			throw e;
		}
	}

	public abstract T doParse(Element rootElement) throws Exception;
}