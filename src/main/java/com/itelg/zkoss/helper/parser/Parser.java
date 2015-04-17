package com.itelg.zkoss.helper.parser;

public interface Parser<T>
{
	public T parse(String xml) throws Exception;
}