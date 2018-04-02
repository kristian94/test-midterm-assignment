package com.testex;

import java.util.Date;

/**
 * Created by Kristian Nielsen on 29-03-2018.
 */
public interface IDateFormatter {
    public String getFormattedDate(String timeZone) throws JokeException;
    public String getFormattedDate(String timeZone, Date date) throws JokeException;
    public String getFormattedDate(String timeZone, Date date, String format) throws JokeException;
}
