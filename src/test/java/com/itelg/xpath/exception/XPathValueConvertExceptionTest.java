package com.itelg.xpath.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class XPathValueConvertExceptionTest
{
    @Test
    void testThrowableAndValue()
    {
        XPathValueConvertException exception = new XPathValueConvertException(new Exception("error"), "Test");
        Assertions.assertEquals("Test", exception.getValue());
        Assertions.assertEquals("error", exception.getCause().getMessage());
    }

    @Test
    void testValue()
    {
        XPathValueConvertException exception = new XPathValueConvertException("Test");
        Assertions.assertEquals("Test", exception.getValue());
    }
}
