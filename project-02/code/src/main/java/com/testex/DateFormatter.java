package com.testex;

import com.testex.IDateFormatter;
import com.testex.JokeException;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.TimeZone;

public class DateFormatter implements IDateFormatter {

//    private static final String DEFAULT_FORMAT = "dd MMM yyyy hh:mm aa";

    private String format;

    public DateFormatter() {
        this.format = "dd MMM yyyy hh:mm aa";
    }

    public DateFormatter(String format) {
        this.format = format;
    }

    private boolean timeZoneIsValid(String timeZone) {
        return Arrays.asList(TimeZone.getAvailableIDs()).contains(timeZone);
    }

    public String getFormattedDate(String timeZone) throws JokeException {
        return getFormattedDate(timeZone, new Date(), format);
    }

    public String getFormattedDate(String timeZone, Date date) throws JokeException {
        return getFormattedDate(timeZone, date, format);
    }

    public String getFormattedDate(String timeZone, Date date, String format) throws JokeException {
        if (!timeZoneIsValid(timeZone)) {
            throw new JokeException("Invalid Time Zone: " + timeZone);
        }

        SimpleDateFormat simpleFormat = new SimpleDateFormat(format);
        simpleFormat.setTimeZone(TimeZone.getTimeZone(timeZone));
        return simpleFormat.format(date);
    }
}
