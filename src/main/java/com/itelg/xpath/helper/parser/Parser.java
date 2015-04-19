package com.itelg.xpath.helper.parser;

import java.io.InputStream;

public interface Parser<T>
{
	public T parse(String xml) throws Exception;
	public T parse(InputStream xml) throws Exception;
}