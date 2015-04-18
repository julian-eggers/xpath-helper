package com.itelg.zkoss.helper;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DateHelper
{
	private static final Map<String, DateTimeFormatter> dateTimeFormatters = new ConcurrentHashMap<>();
	
	public static Date toDate(String date, String format)
	{
		return toDateTime(date, format).toDate();
	}

	public static DateTime toDateTime(String date, String format)
	{
		DateTimeFormatter dateTimeFormatter = dateTimeFormatters.get(format);
		
		if (dateTimeFormatter == null)
		{
			dateTimeFormatter = DateTimeFormat.forPattern(format);
			dateTimeFormatters.put(format, dateTimeFormatter);
		}
		
		return dateTimeFormatter.parseDateTime(date);
	}
}