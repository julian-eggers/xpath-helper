package com.itelg.xpath.exception;

public class XPathValueConvertException extends RuntimeException
{
	private static final long serialVersionUID = -3396881151756585678L;
	private String value;
	
	public XPathValueConvertException(Throwable e, String value)
	{
		super(e);
		this.value = value;
	}
	
	public XPathValueConvertException(String value)
	{
		this.value = value;
	}
	
	public String getValue()
	{
		return value;
	}
}