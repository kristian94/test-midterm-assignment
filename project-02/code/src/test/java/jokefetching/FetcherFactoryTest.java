package jokefetching;

import org.junit.Test;

//import static org.junit.Assert.*;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
//import static org.mockito.Matchers.*;
//import static org.mockito.BDDMockito.*;

public class FetcherFactoryTest {

//    public static IFetcherFactory factory = mock(FetcherFactory.class);

    @Test
    public void getJokeFecthers() {
        List<IJokeFetcher> result = new FetcherFactory().getJokeFetchers("EduJoke,ChuckNorris,Moma,Tambal");
        assertThat(result.size(), is(4));
        assertThat(result, hasItem(isA(EduJoke.class)));
    }
}