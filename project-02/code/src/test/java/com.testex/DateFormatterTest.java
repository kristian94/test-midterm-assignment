package com.testex;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.TimeZone;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Created by Kristian Nielsen on 29-03-2018.
 */
public class DateFormatterTest  {
    @Rule
    public ExpectedException exceptionGrabber = ExpectedException.none();

    @Test
    public void testGetFormattedDate() throws JokeException {
        DateFormatter dateFormatter = new DateFormatter();

        String legalFormat = TimeZone.getAvailableIDs()[0];
        String illegalFormat = "xyz123";



        assertThat("Legal timezone did not return true", dateFormatter.getFormattedDate(legalFormat), notNullValue());
        exceptionGrabber.expect(JokeException.class);

        assertThat("Exception should be thrown", dateFormatter.getFormattedDate(illegalFormat), notNullValue());
    }
}