package com.algorithms.utils;

import com.algorithms.collections.immutable.List;
import com.algorithms.controls.Eval;

public class Prime {
    public static int THIRTY_ONE = 31;

    public static List<Integer> getPrimes(int max) {
        if (max < 2) return List.nil();
        return getPrimesInner(List.single(2), new boolean[max + 1]).run();
    }

    private static Eval<List<Integer>> getPrimesInner(List<Integer> primes, boolean[] sieveOfEratosthenes) {
        var current = primes.getHead().get();
        for (var i = current; i < sieveOfEratosthenes.length; i+= current) {
            sieveOfEratosthenes[i] = true;
        }
        for (var i = current; i < sieveOfEratosthenes.length; i++) {
            if (!sieveOfEratosthenes[i]) {
                var next = i;
                return Eval.more(() -> getPrimesInner(List.cons(next, primes), sieveOfEratosthenes));
            }
        }
        return Eval.done(primes);
    }

    public static int nextPrime(int after) {
        if (after < 2) return 2;
        var primes = getPrimes(after);
        return nextPrimeInner(primes, primes.getHead().get() + 1).run();
    }

    private static Eval<Integer> nextPrimeInner(List<Integer> primes, int candidate) {
        var isPrime = primes.none(prime -> candidate % prime == 0);
        return isPrime
            ? Eval.done(candidate)
            : Eval.more(() -> nextPrimeInner(primes, candidate + 1));
    }

    public static boolean isPrime(int n) {
        return getPrimes(n).contains(n);
    }
}
