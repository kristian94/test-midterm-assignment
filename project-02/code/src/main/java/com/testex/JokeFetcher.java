
package com.testex;

import static com.jayway.restassured.RestAssured.given;

import jokefetching.FetcherFactory;
import jokefetching.IFetcherFactory;
import jokefetching.IJokeFetcher;

import java.util.Arrays;
import java.util.List;

/**
 * Class used to fetch jokes from a number of external joke API's
 */
public class JokeFetcher {

//    public static final List<String> availableTypes = Arrays.asList("eduprog", "chucknorris", "moma", "tambal");
    private IDateFormatter dateFormatter;
    private IFetcherFactory factory;

    public JokeFetcher(IDateFormatter dateFormatter, IFetcherFactory factory){
        this.dateFormatter = dateFormatter;
        this.factory = factory;
    }

    boolean isStringValid(String jokeTokens) {
        String[] tokens = jokeTokens.split(",");
        for (String token : tokens) {
            if (!factory.getAvailableTypes().contains(token)) {
                return false;
            }
        }
        return true;
    }

    public Jokes getJokes(String jokesToFetch, String timeZone) throws JokeException {
        isStringValid(jokesToFetch);
        Jokes jokes = new Jokes();
        for(IJokeFetcher fetcher: factory.getJokeFetchers(jokesToFetch)){
            jokes.addJoke(fetcher.getJoke());
        }
        String tzString = dateFormatter.getFormattedDate(timeZone);
        jokes.setTimeZoneString(tzString);
        return jokes;
    }

    public static void main(String[] args) throws JokeException {
        JokeFetcher jf = new JokeFetcher(new DateFormatter(), new FetcherFactory());
        Jokes jokes = jf.getJokes("EduJoke,ChuckNorris,Moma,Tambal", "Europe/Copenhagen");
        jokes.getJokes().forEach((joke) -> {
            System.out.println(joke);
        });
    }
}
