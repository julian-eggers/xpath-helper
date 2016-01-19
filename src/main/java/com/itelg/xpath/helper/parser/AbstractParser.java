package com.itelg.xpath.helper.parser;

import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itelg.xpath.helper.DocumentHelper;

import nu.xom.Element;

public abstract class AbstractParser<T> implements Parser<T>
{
	protected static final Logger log = LoggerFactory.getLogger(AbstractParser.class);

	@Override
	public T parse(String xml) throws Exception
	{
		try
		{
			Element rootElement = DocumentHelper.getRootElement(xml);
			return doParse(rootElement);
		}
		catch (Exception e)
		{
			log.warn(e.getMessage(), e);
			throw e;
		}
	}
	
	@Override
	public T parse(InputStream xml) throws Exception
	{
		try
		{
			Element rootElement = DocumentHelper.getRootElement(xml);
			return doParse(rootElement);
		}
		catch (Exception e)
		{
			log.warn(e.getMessage(), e);
			throw e;
		}
	}

	protected abstract T doParse(Element rootElement) throws Exception;
}