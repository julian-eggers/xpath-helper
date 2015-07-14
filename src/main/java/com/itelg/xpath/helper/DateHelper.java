package com.itelg.xpath.helper;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

public class DateHelper
{
	/**
	 * START {@link java.util.Date}
	 */
	public static Date toDate(String date, String format)
	{
		return toDateTime(date, format).toDate();
	}

	/**
	 * START {@link org.joda.time.DateTime}
	 */
	public static DateTime toDateTime(String date, String format)
	{
		return DateTimeFormat.forPattern(format).parseDateTime(date);
	}

	/**
	 * START java.time.*
	 */
	public static ZonedDateTime toZonedDateTime(String date, DateTimeFormatter formatter)
	{
		try
		{
			return ZonedDateTime.parse(date, formatter);
		}
		catch (Exception e)
		{
			throw new IllegalArgumentException(e);
		}
	}

	public static LocalDateTime toLocalDateTime(String date, DateTimeFormatter formatter)
	{
		try
		{
			ZonedDateTime zonedDateTime = toZonedDateTime(date, formatter);
			return LocalDateTime.ofInstant(zonedDateTime.toInstant(), ZoneId.systemDefault());
		}
		catch (Exception e)
		{
			throw new IllegalArgumentException(e);
		}
	}
}