package com.itelg.zkoss.helper;

import java.util.Date;

import org.joda.time.DateTime;

public class DateHelper
{
	public static Date toDate(String date, String format)
	{
		DateTime dateTime = toDateTime(date, format);

		if (dateTime != null)
		{
			return dateTime.toDate();
		}

		return null;
	}

	public static DateTime toDateTime(String date, String format)
	{

		return null;
	}
}