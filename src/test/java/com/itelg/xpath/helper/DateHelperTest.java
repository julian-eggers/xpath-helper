package com.itelg.xpath.helper;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Assert;
import org.junit.Test;

public class DateHelperTest
{
    /**
     * START java.time.*
     */
    @Test
    public void testToZoneDateTime()
    {
        Assert.assertEquals(ZonedDateTime.of(2015, 7, 8, 12, 21, 30, 0, ZoneId.of("+02:00")), DateHelper
                .toZonedDateTime("2015-07-08T12:21:30.667+02:00", DateTimeFormatter.ISO_OFFSET_DATE_TIME)
                .withNano(0));
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
        assertThat(localDateTime).isIn(LocalDateTime.of(2015, 7, 8, 10, 21, 30, 0), LocalDateTime.of(2015, 7, 8, 12, 21, 30, 0));
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
