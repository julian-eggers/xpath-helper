package com.itelg.xpath.helper.parser;

import java.io.InputStream;
import java.io.Reader;

public interface Parser<T>
{
	public T parse(String xml) throws Exception;
	public T parse(InputStream xml) throws Exception;
	public T parse(Reader xml) throws Exception;
}