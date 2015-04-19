package com.itelg.xpath.helper;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;

import com.itelg.xpath.helper.DateHelper;

public class DateHelperTest
{
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
}