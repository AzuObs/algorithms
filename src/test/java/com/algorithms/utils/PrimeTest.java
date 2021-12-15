package com.algorithms.utils;

import com.algorithms.collections.immutable.List;
import org.junit.Test;

public class PrimeTest {

  @Test public void getPrimesLt2() { assert Prime.getPrimes(1).equals(List.empty()); }
  @Test public void getPrimesEq2() { assert Prime.getPrimes(2).equals(List.single(2)); }
  @Test public void getPrimesGt2() {assert Prime.getPrimes(3).equals(List.cons(3, List.single(2))); }
  @Test public void getPrimesGt3() { assert Prime.getPrimes(4).equals(List.cons(3, List.single(2))); }

  @Test public void isPrime1() { assert !Prime.isPrime(1); }
  @Test public void isPrime2() { assert Prime.isPrime(2); }
  @Test public void isPrime3() { assert Prime.isPrime(3); }
  @Test public void isPrime4() { assert !Prime.isPrime(4); }
  @Test public void isPrime5() { assert Prime.isPrime(5); }

  @Test public void nextPrime1() { assert Prime.nextPrime(1) == 2; }
  @Test public void nextPrime2() { assert Prime.nextPrime(2) == 3; }
  @Test public void nextPrime3() { assert Prime.nextPrime(3) == 5; }
}
