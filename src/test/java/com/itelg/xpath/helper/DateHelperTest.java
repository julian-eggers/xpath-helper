package com.itelg.xpath.helper;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.fest.assertions.Assertions;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class DateHelperTest
{
	@BeforeClass
	public static void init()
	{
		System.setProperty("user.timezone", "Europe/Berlin");
	}
	
	/**
	 * START {@link java.util.Date}
	 */
	@Test
	public void testToDate()
	{
		Assert.assertEquals(new DateTime(2015, 1, 1, 0, 0).toDate(), DateHelper.toDate("2015-01-01", "yyyy-MM-dd"));
		Assert.assertEquals(new DateTime(2015, 1, 1, 0, 0).toDate(), DateHelper.toDate("2015-01-01", "yyyy-MM-dd"));
		Assert.assertEquals(new DateTime(2015, 1, 1, 1, 1, 1).toDate(), DateHelper.toDate("2015-01-01 01:01:01", "yyyy-MM-dd HH:mm:ss"));
		Assert.assertEquals(new DateTime(2015, 1, 1, 1, 1, 1).toDate(), DateHelper.toDate("2015-01-01 01:01:01", "yyyy-MM-dd HH:mm:ss"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testToDateWithWrongFormat()
	{
		DateHelper.toDate("2015-01-01 01:01:01", "");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testToDateWithWrongDate()
	{
		DateHelper.toDate("", "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * START {@link org.joda.time.DateTime}
	 */
	@Test
	public void testToDateTime()
	{
		Assert.assertEquals(new DateTime(2015, 1, 1, 0, 0), DateHelper.toDateTime("2015-01-01", "yyyy-MM-dd"));
		Assert.assertEquals(new DateTime(2015, 1, 1, 0, 0), DateHelper.toDateTime("2015-01-01", "yyyy-MM-dd"));
		Assert.assertEquals(new DateTime(2015, 1, 1, 1, 1, 1), DateHelper.toDateTime("2015-01-01 01:01:01", "yyyy-MM-dd HH:mm:ss"));
		Assert.assertEquals(new DateTime(2015, 1, 1, 1, 1, 1), DateHelper.toDateTime("2015-01-01 01:01:01", "yyyy-MM-dd HH:mm:ss"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testToDateTimeWithWrongFormat()
	{
		DateHelper.toDateTime("2015-01-01 01:01:01", "");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testToDateTimeWithWrongDate()
	{
		DateHelper.toDateTime("", "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * START java.time.*
	 */
	@Test
	public void testToZoneDateTime()
	{
		Assert.assertEquals(ZonedDateTime.of(2015, 7, 8, 12, 21, 30, 0, ZoneId.of("+02:00")), DateHelper.toZonedDateTime("2015-07-08T12:21:30.667+02:00", DateTimeFormatter.ISO_OFFSET_DATE_TIME).withNano(0));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testToZoneDateTimeWithWrongFormat()
	{
		DateHelper.toZonedDateTime("2015-07-08T12:21:30.667+02:00", null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testToZoneDateTimeWithWrongDate()
	{
		DateHelper.toZonedDateTime("", DateTimeFormatter.ISO_OFFSET_DATE_TIME);
	}
	
	@Test
	public void testToLocalDateTime()
	{
		LocalDateTime localDateTime = DateHelper.toLocalDateTime("2015-07-08T12:21:30.667+02:00", DateTimeFormatter.ISO_OFFSET_DATE_TIME).withNano(0);
		Assertions.assertThat(localDateTime).isIn(LocalDateTime.of(2015, 7, 8, 10, 21, 30, 0), LocalDateTime.of(2015, 7, 8, 12, 21, 30, 0));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testToLocalDateTimeWithWrongFormat()
	{
		DateHelper.toLocalDateTime("2015-07-08T12:21:30.667+02:00", null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testToLocalDateTimeWithWrongDate()
	{
		DateHelper.toLocalDateTime("", DateTimeFormatter.ISO_OFFSET_DATE_TIME);
	}
}