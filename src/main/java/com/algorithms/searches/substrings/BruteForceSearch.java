package com.algorithms.searches.substrings;

import com.algorithms.controls.Eval;
import com.algorithms.controls.Option;

public class BruteForceSearch implements SubstringSearch {
    private BruteForceSearch() { }

    public static BruteForceSearch apply() {
        return new BruteForceSearch();
    }

    @Override
    public Option<String> find(String searchSpace, String substring) {
        return findInner(searchSpace, substring, 0, 0).run();
    }

    private Eval<Option<String>> findInner(String searchSpace, String substring, int currentIndex, int runnerIndex) {
        if (substring.isBlank()) {
            return Eval.done(Option.none());
        } else if (runnerIndex == substring.length()) {
            return Eval.done(Option.some(searchSpace.substring(currentIndex, currentIndex + runnerIndex)));
        } else if (currentIndex == (searchSpace.length() - substring.length() + 1)) {
            return Eval.done(Option.none());
        } else if (searchSpace.charAt(currentIndex + runnerIndex) == substring.charAt(runnerIndex)) {
            return Eval.more(() -> findInner(searchSpace, substring, currentIndex, runnerIndex + 1));
        } else {
            return Eval.more(() -> findInner(searchSpace, substring, currentIndex + 1, 0));
        }
    }
}
