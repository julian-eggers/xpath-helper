package com.itelg.xpath.exception;

import org.junit.Assert;
import org.junit.Test;

public class XPathValueConvertExceptionTest
{
	@Test
	public void testThrowableAndValue()
	{
		XPathValueConvertException exception = new XPathValueConvertException(new Exception("error"), "Test");
		Assert.assertEquals("Test", exception.getValue());
		Assert.assertEquals("error", exception.getCause().getMessage());
	}
	
	@Test
	public void testValue()
	{
		XPathValueConvertException exception = new XPathValueConvertException("Test");
		Assert.assertEquals("Test", exception.getValue());
	}
}