package com.algorithms.utils;

import com.algorithms.collections.list.Cons;
import com.algorithms.collections.list.List;
import com.algorithms.collections.list.Nil;
import com.algorithms.controls.eval.Done;
import com.algorithms.controls.eval.Eval;
import com.algorithms.controls.eval.More;

public class Primes {
    public static int nextPrime(int after) {
        return nextPrimeInner(Cons.apply(2, Nil.apply()), after, 3).run();
    }

    // daniel extract into memoized utility
    private static Eval<Integer> nextPrimeInner(List<Integer> primes, int after, int candidate) {
        var isPrime = primes.none(prime -> candidate == prime || candidate % prime == 0);

        if (isPrime && candidate > after) {
            return Done.apply(candidate);
        } else if (isPrime) {
            return More.apply(() -> nextPrimeInner(Cons.apply(candidate, primes), after, candidate + 1));
        } else {
            return More.apply(() -> nextPrimeInner(primes, after, candidate + 1));
        }
    }
}
