package com.itelg.xpath.helper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

class DateHelperTest
{
    /**
     * START java.time.*
     */
    @Test
    void testToZoneDateTime()
    {
        assertEquals(ZonedDateTime.of(2015, 7, 8, 12, 21, 30, 0, ZoneId.of("+02:00")), DateHelper
                .toZonedDateTime("2015-07-08T12:21:30.667+02:00", DateTimeFormatter.ISO_OFFSET_DATE_TIME)
                .withNano(0));
    }

    @Test
    void testToZoneDateTimeWithWrongFormat()
    {
        assertThrows(IllegalArgumentException.class,
                () -> DateHelper.toZonedDateTime("2015-07-08T12:21:30.667+02:00", null));
    }

    @Test
    void testToZoneDateTimeWithWrongDate()
    {
        assertThrows(IllegalArgumentException.class,
                () -> DateHelper.toZonedDateTime("", DateTimeFormatter.ISO_OFFSET_DATE_TIME));
    }

    @Test
    void testToLocalDateTime()
    {
        LocalDateTime localDateTime = DateHelper.toLocalDateTime("2015-07-08T12:21:30.667+02:00", DateTimeFormatter.ISO_OFFSET_DATE_TIME).withNano(0);
        assertThat(localDateTime).isIn(LocalDateTime.of(2015, 7, 8, 10, 21, 30, 0), LocalDateTime.of(2015, 7, 8, 12, 21, 30, 0));
    }

    @Test
    void testToLocalDateTimeWithWrongFormat()
    {
        assertThrows(IllegalArgumentException.class,
                () -> DateHelper.toLocalDateTime("2015-07-08T12:21:30.667+02:00", null));
    }

    @Test
    void testToLocalDateTimeWithWrongDate()
    {
        assertThrows(IllegalArgumentException.class,
                () -> DateHelper.toLocalDateTime("", DateTimeFormatter.ISO_OFFSET_DATE_TIME));
    }
}
