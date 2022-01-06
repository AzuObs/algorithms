package com.algorithms.puzzles.ctci;

import org.junit.Test;

public class SubArrayTargetSumTest {

  @Test public void empty() { assert SubArrayTargetSum.count(new int[0], 10) == 0; }
  @Test public void one() { assert SubArrayTargetSum.count(new int[] { 1 }, 10) == 0; }
  @Test public void noResults() { assert SubArrayTargetSum.count(new int[] { 1, 2, 3 }, 10) == 0; }
  @Test public void oneWithOneResult() { assert SubArrayTargetSum.count(new int[] { 10 }, 10) == 1; }
  @Test public void manyWithOneResult() { assert SubArrayTargetSum.count(new int[] { 1, 2, 3, 4 }, 10) == 1; }
  @Test public void manyWithOneResultNonEqualTotal() { assert SubArrayTargetSum.count(new int[] { -3, 1, 2, 3, 4, 5 }, 10) == 1; }
  @Test public void manyWithManyResults() { assert SubArrayTargetSum.count(new int[] { -3, 1, 2, 3, 4, 5, 5, 10 }, 10) == 3; }

}
