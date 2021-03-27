package com.algorithms.searches.substrings;

import com.algorithms.controls.eval.Done;
import com.algorithms.controls.eval.Eval;
import com.algorithms.controls.eval.More;
import com.algorithms.controls.option.None;
import com.algorithms.controls.option.Option;
import com.algorithms.controls.option.Some;

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
            return Done.apply(None.apply());
        } else if (runnerIndex == substring.length()) {
            return Done.apply(Some.apply(searchSpace.substring(currentIndex, currentIndex + runnerIndex)));
        } else if (currentIndex == (searchSpace.length() - substring.length() + 1)) {
            return Done.apply(None.apply());
        } else if (searchSpace.charAt(currentIndex + runnerIndex) == substring.charAt(runnerIndex)) {
            return More.apply(() -> findInner(searchSpace, substring, currentIndex, runnerIndex + 1));
        } else {
            return More.apply(() -> findInner(searchSpace, substring, currentIndex + 1, 0));
        }
    }
}
