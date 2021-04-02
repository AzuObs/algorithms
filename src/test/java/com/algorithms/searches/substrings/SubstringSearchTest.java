package com.algorithms.searches.substrings;

import com.algorithms.controls.Option;
import org.junit.Test;

public class SubstringSearchTest {

    private final String searchSpace = "The quick brown fox jumps over the lazy dog.";

    @Test
    public void bruteForceSearch() {
        var search = BruteForceSearch.apply();
        assert search.find(searchSpace, "The quick").equals(Option.some("The quick"));
        assert search.find(searchSpace, "he ").equals(Option.some("he "));
        assert search.find(searchSpace, "brown fox jumps ov").equals(Option.some("brown fox jumps ov"));
        assert search.find(searchSpace, "quick").equals(Option.some("quick"));
        assert search.find(searchSpace, "brown").equals(Option.some("brown"));
        assert search.find(searchSpace, "dog.").equals(Option.some("dog."));
        assert search.find(searchSpace, "foobar").isEmpty();
        assert search.find(searchSpace, "").isEmpty();
    }

    @Test
    public void rabinKarpSearch() {
        var search = RabinKarpSearch.apply();

        assert search.find(searchSpace, "The quick").equals(Option.some("The quick"));
        assert search.find(searchSpace, "he ").equals(Option.some("he "));
        assert search.find(searchSpace, "brown fox jumps ov").equals(Option.some("brown fox jumps ov"));
        assert search.find(searchSpace, "quick").equals(Option.some("quick"));
        assert search.find(searchSpace, "brown").equals(Option.some("brown"));
        assert search.find(searchSpace, "dog.").equals(Option.some("dog."));
        assert search.find(searchSpace, "foobar").isEmpty();
        assert search.find(searchSpace, "").isEmpty();
    }
}
