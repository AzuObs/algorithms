package com.algorithms.searches.substrings;

import com.algorithms.controls.Option;

public interface SubstringSearch {
    Option<String> find(String searchSpace, String substring);

    default boolean contains(String searchSpace, String substring) {
        return this.find(searchSpace, substring).exists();
    }
}
