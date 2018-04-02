package jokefetching;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FetcherFactory implements IFetcherFactory {

    private final List<String> availableTypes = Arrays.asList("EduJoke", "ChuckNorris", "Moma", "Tambal");

    @Override
    public List<String> getAvailableTypes() {
        return availableTypes;
    }

    @Override
    public List<IJokeFetcher> getJokeFetchers(String jokesToFetch) {
        List<IJokeFetcher> fetchers = new ArrayList<>();
        String[] tokens = jokesToFetch.split(",");
        for(String s: tokens){
            switch (s){
                case "EduJoke":
                    fetchers.add(new EduJoke());
                    break;
                case "ChuckNorris":
                    fetchers.add(new ChuckNorris());
                    break;
                case "Moma":
                    fetchers.add(new Moma());
                    break;
                case "Tambal":
                    fetchers.add(new Tambal());
                    break;
            }
        }
        return fetchers;
    }

    public static void main(String[] args) {
        IFetcherFactory factory = new FetcherFactory();
        List<IJokeFetcher> res = factory.getJokeFetchers("eduprog,chucknorris");
        System.out.println(res.size());
    }
}
