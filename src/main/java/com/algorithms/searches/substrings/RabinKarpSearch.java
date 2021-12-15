package com.algorithms.searches.substrings;

import com.algorithms.collections.immutable.List;
import com.algorithms.utils.tuples.Tuple2;
import com.algorithms.controls.Eval;
import com.algorithms.controls.Option;
import com.algorithms.utils.Prime;

public class RabinKarpSearch implements SubstringSearch {

    private RabinKarpSearch() { }

    public static RabinKarpSearch apply() {
        return new RabinKarpSearch();
    }

    @Override
    public Option<String> find(String searchSpace, String substring) {
        if (searchSpace.length() < substring.length()) return Option.none();
        if (substring.equals("")) return Option.none();

        var hashedSpace = rabinRollingHash(searchSpace, substring.length(), 0, List.nil()).run();
        var hashedSub = rabinHash(substring);

        return hashedSpace.find(element -> element.value.equals(hashedSub.value) && element.key.equals(hashedSub.key))
                .map(Tuple2::getKey)
                .getHead();
    }

    private static Eval<List<Tuple2<String, Integer>>> rabinRollingHash(
            String searchSpace,
            int substringSize,
            int index,
            List<Tuple2<String, Integer>> result
    ) {
        // exit condition
        if (index > searchSpace.length() - substringSize) {
            return Eval.done(result.reverse());
        }
        // base case is special
        else if (index == 0) {
            return Eval.more(() -> {
                var head = rabinHash(searchSpace.substring(0, substringSize));
                return rabinRollingHash(searchSpace, substringSize, index + 1, List.cons(head, result));
            });
        }
        // calculate new hash based on Rabin's rolling hash algorithm
        else {
            return Eval.more(() -> {
                var previousHash = result.getHead().get().value;
                var hash =
                        (previousHash - searchSpace.charAt(index - 1)
                                * pow(Prime.THIRTY_ONE, substringSize - 1))
                                * Prime.THIRTY_ONE
                                + searchSpace.charAt(index + substringSize - 1);
                var head = Tuple2.apply(searchSpace.substring(index, index + substringSize), hash);
                return rabinRollingHash(searchSpace, substringSize, index + 1, List.cons(head, result));
            });
        }
    }

    private static Tuple2<String, Integer> rabinHash(String of) {
        var hash = 0;
        for (var i = 0; i < of.length(); i++) {
            hash = hash * Prime.THIRTY_ONE + of.charAt(i);
        }
        return Tuple2.apply(of, hash);
    }

    // Math.pow exists but because it's a double, it does not buffer overflow the way an int does when
    // cast back to an int.
    // Using this approach ensures that the powers are always calculated the same way (by int).
    // The alternative would be for our hash to be a double
    private static int pow(int of, int exponent) {
        return powInner(of, exponent, 0, 1).run();
    }

    private static Eval<Integer> powInner(int of, int exponent, int at, int result) {
        if (at == exponent) {
            return Eval.done(result);
        } else {
            return Eval.more(() -> powInner(of, exponent, at + 1, result * of));
        }
    }
}
