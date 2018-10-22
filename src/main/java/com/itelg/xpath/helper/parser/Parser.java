package com.itelg.xpath.helper.parser;

import java.io.InputStream;
import java.io.Reader;

public interface Parser<T>
{
    T parse(String xml) throws Exception;

    T parse(InputStream xml) throws Exception;

    T parse(Reader xml) throws Exception;
}