package com.algorithms.puzzles.ctci;

import org.junit.Test;

public class AddWithoutArithmeticTest {

  @Test public void zeros() { assert AddWithoutArithmetic.add(0, 0) == 0; }
  @Test public void zeroOne() { assert AddWithoutArithmetic.add(0, 1) == 1; }
  @Test public void oneZero() { assert AddWithoutArithmetic.add(1, 0) == 1; }
  @Test public void oneOne() { assert AddWithoutArithmetic.add(1, 1) == 2; }
  @Test public void manyOne() { assert AddWithoutArithmetic.add(5, 1) == 6; }
  @Test public void oneMany() { assert AddWithoutArithmetic.add(1, 5) == 6; }
  @Test public void manyMany() { assert AddWithoutArithmetic.add(5, 5) == 10; }

}
