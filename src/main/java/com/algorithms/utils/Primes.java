package com.algorithms.utils;

import com.algorithms.collections.immutable.List;
import com.algorithms.controls.Eval;

public class Primes {
    public static int THIRTY_ONE = 31;

    public static int nextPrime(int after) {
        return nextPrimeInner(List.cons(2, List.nil()), after, 3).run();
    }

    // daniel extract into memoized utility
    private static Eval<Integer> nextPrimeInner(List<Integer> primes, int after, int candidate) {
        var isPrime = primes.none(prime -> candidate == prime || candidate % prime == 0);

        if (isPrime && candidate > after) {
            return Eval.done(candidate);
        } else if (isPrime) {
            return Eval.more(() -> nextPrimeInner(List.cons(candidate, primes), after, candidate + 1));
        } else {
            return Eval.more(() -> nextPrimeInner(primes, after, candidate + 1));
        }
    }
}
