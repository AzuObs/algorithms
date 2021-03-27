package com.algorithms.searches.substrings;

import com.algorithms.controls.option.Some;
import org.junit.Test;

public class SubstringSearchTest {

    private final String searchSpace = "The quick brown fox jumps over the lazy dog.";

    @Test
    public void bruteForceSearch() {
        var search = BruteForceSearch.apply();
        assert search.find(searchSpace, "The quick").equals(Some.apply("The quick"));
        assert search.find(searchSpace, "he ").equals(Some.apply("he "));
        assert search.find(searchSpace, "brown fox jumps ov").equals(Some.apply("brown fox jumps ov"));
        assert search.find(searchSpace, "quick").equals(Some.apply("quick"));
        assert search.find(searchSpace, "brown").equals(Some.apply("brown"));
        assert search.find(searchSpace, "dog.").equals(Some.apply("dog."));
        assert search.find(searchSpace, "foobar").isEmpty();
        assert search.find(searchSpace, "").isEmpty();
    }

    @Test
    public void rabinKarpSearch() {
        var search = RabinKarpSearch.apply();

        assert search.find(searchSpace, "The quick").equals(Some.apply("The quick"));
        assert search.find(searchSpace, "he ").equals(Some.apply("he "));
        assert search.find(searchSpace, "brown fox jumps ov").equals(Some.apply("brown fox jumps ov"));
        assert search.find(searchSpace, "quick").equals(Some.apply("quick"));
        assert search.find(searchSpace, "brown").equals(Some.apply("brown"));
        assert search.find(searchSpace, "dog.").equals(Some.apply("dog."));
        assert search.find(searchSpace, "foobar").isEmpty();
        assert search.find(searchSpace, "").isEmpty();
    }
}
