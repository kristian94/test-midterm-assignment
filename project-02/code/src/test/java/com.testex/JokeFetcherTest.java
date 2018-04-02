package com.testex;

import jokefetching.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

//import static org.junit.Assert.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Matchers.*;
import static org.mockito.BDDMockito.*;

/**
 * Created by Kristian Nielsen on 29-03-2018.
 */
@RunWith(MockitoJUnitRunner.class)
public class JokeFetcherTest {

    private JokeFetcher jokeFetcher;
    private String tz = "Europe/Copenhagen";

    @Mock
    IDateFormatter formatter;

    @Mock
    IFetcherFactory factory;

    @Mock
    Moma moma;

    @Mock
    ChuckNorris chuckNorris;

    @Mock
    EduJoke eduJoke;

    @Mock
    Tambal tambal;

    @Before
    public void setup(){
        List<IJokeFetcher> fetchers = Arrays.asList(eduJoke, chuckNorris, moma, tambal);
        when(factory.getJokeFetchers("EduJoke,ChuckNorris,Moma,Tambal")).thenReturn(fetchers);
        when(factory.getJokeFetchers("EduJoke")).thenReturn(Arrays.asList(eduJoke));
        when(factory.getJokeFetchers("ChuckNorris")).thenReturn(Arrays.asList(chuckNorris));
        when(factory.getJokeFetchers("Moma")).thenReturn(Arrays.asList(moma));
        when(factory.getJokeFetchers("Tambal")).thenReturn(Arrays.asList(tambal));
        List<String> types = Arrays.asList("EduJoke","ChuckNorris","Moma","Tambal");
        when(factory.getAvailableTypes()).thenReturn(types);
        jokeFetcher = new JokeFetcher(formatter, factory);
    }

    @Test
    public void testAvailableTypes() throws Exception {
        List<String> types = factory.getAvailableTypes();

        String[] expectedTypes = {"EduJoke", "ChuckNorris", "Moma", "Tambal"};

        assertThat("availableTypes.size() was not 4, as expected", types.size(), is(4));

        for(String s: expectedTypes){
            assertThat("Valid type '"+s+"' was not present" ,types, hasItem(s));
        }
    }

    @Test
    public void isStringValid() throws Exception {
        JokeFetcher jf = new JokeFetcher(formatter, new FetcherFactory());

        String validToken = "EduJoke,ChuckNorris";
        String validToken2 = "EduJoke,Moma";
        String invalidToken = "peter_madsen,prins_henrik";

        assertThat("Valid token incorrectly returned false: " + validToken, jf.isStringValid(validToken), is(true));
        assertThat("Valid token incorrectly returned false: " + validToken2, jf.isStringValid(validToken2), is(true));
        assertThat("Invalid token incorrectly returned true: " + invalidToken, jf.isStringValid(invalidToken), is(false));
    }

    @Test
    public void getJokes() throws Exception {
        String hardDate = "17 feb. 2018 10:56 AM";
        given(formatter.getFormattedDate(eq(tz)))
                .willReturn(hardDate);
        Jokes jokes = jokeFetcher.getJokes("EduJoke", tz);
        assertThat(formatter.getFormattedDate(tz), is(hardDate));
        verify(formatter, timeout(10).times(2)).getFormattedDate(tz);
        assertThat(jokes.timeZoneString, is(hardDate));
    }

    @Test
    public void getEduJoke() throws JokeException {
        Joke joke = new Joke("Test joke 01", "funny-joke-central.com");
        when(eduJoke.getJoke()).thenReturn(joke);
        Jokes jokes = jokeFetcher.getJokes("EduJoke", tz);
        assertThat(jokes.getJokes(), hasItem(joke));
        verify(factory, timeout(10).times(1)).getJokeFetchers("EduJoke");
    }

    @Test
    public void getNorris() throws JokeException {
        Joke joke = new Joke("Test joke 02", "funny-joke-central.com");
        when(chuckNorris.getJoke()).thenReturn(joke);
        Jokes jokes = jokeFetcher.getJokes("ChuckNorris", tz);
        assertThat(jokes.getJokes(), hasItem(joke));
        verify(factory, timeout(10).times(1)).getJokeFetchers("ChuckNorris");
    }

    @Test
    public void getMommaJoke() throws JokeException {
        Joke joke = new Joke("Test joke 03", "funny-joke-central.com");
        when(moma.getJoke()).thenReturn(joke);
        Jokes jokes = jokeFetcher.getJokes("Moma", tz);
        assertThat(jokes.getJokes(), hasItem(joke));
        verify(factory, timeout(10).times(1)).getJokeFetchers("Moma");
    }

    @Test
    public void getTambalJoke() throws JokeException {
        Joke joke = new Joke("Test joke 04", "funny-joke-central.com");
        when(tambal.getJoke()).thenReturn(joke);
        Jokes jokes = jokeFetcher.getJokes("Tambal", tz);
        assertThat(jokes.getJokes(), hasItem(joke));
        verify(factory, timeout(10).times(1)).getJokeFetchers("Tambal");
    }
}