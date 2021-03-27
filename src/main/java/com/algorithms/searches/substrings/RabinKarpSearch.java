package com.algorithms.searches.substrings;

import com.algorithms.collections.list.Cons;
import com.algorithms.collections.list.List;
import com.algorithms.collections.list.Nil;
import com.algorithms.collections.tuples.Tuple2;
import com.algorithms.controls.eval.Done;
import com.algorithms.controls.eval.Eval;
import com.algorithms.controls.eval.More;
import com.algorithms.controls.option.None;
import com.algorithms.controls.option.Option;
import com.algorithms.utils.Primes;

public class RabinKarpSearch implements SubstringSearch {

    private RabinKarpSearch() { }

    public static RabinKarpSearch apply() {
        return new RabinKarpSearch();
    }

    @Override
    public Option<String> find(String searchSpace, String substring) {
        if (searchSpace.length() < substring.length()) return None.apply();
        if (substring.equals("")) return None.apply();

        var hashedSpace = rabinRollingHash(searchSpace, substring.length(), 0, Nil.apply()).run();
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
            return Done.apply(result.reverse());
        }
        // base case is special
        else if (index == 0) {
            return More.apply(() -> {
                var head = rabinHash(searchSpace.substring(0, substringSize));
                return rabinRollingHash(searchSpace, substringSize, index + 1, Cons.apply(head, result));
            });
        }
        // calculate new hash based on Rabin's rolling hash algorithm
        else {
            return More.apply(() -> {
                var previousHash = result.getHead().get().value;
                var hash =
                        (previousHash - searchSpace.charAt(index - 1)
                                * pow(Primes.THIRTY_ONE, substringSize - 1))
                                * Primes.THIRTY_ONE
                                + searchSpace.charAt(index + substringSize - 1);
                var head = Tuple2.apply(searchSpace.substring(index, index + substringSize), hash);
                return rabinRollingHash(searchSpace, substringSize, index + 1, Cons.apply(head, result));
            });
        }
    }

    private static Tuple2<String, Integer> rabinHash(String of) {
        var hash = 0;
        for (var i = 0; i < of.length(); i++) {
            hash = hash * Primes.THIRTY_ONE + of.charAt(i);
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
            return Done.apply(result);
        } else {
            return More.apply(() -> powInner(of, exponent, at + 1, result * of));
        }
    }
}
