package com.itelg.xpath.helper;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateHelper
{
    /**
     * Prevent initialization
     */
    private DateHelper()
    {

    }

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