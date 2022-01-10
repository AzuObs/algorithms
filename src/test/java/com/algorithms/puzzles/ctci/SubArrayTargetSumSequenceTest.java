package com.algorithms.puzzles.ctci;

import org.junit.Test;

public class SubArrayTargetSumSequenceTest {

  @Test public void empty() { assert SubArrayTargetSumSequence.count(new int[0], 10) == 0; }
  @Test public void one() { assert SubArrayTargetSumSequence.count(new int[] { 1 }, 10) == 0; }
  @Test public void noResults() { assert SubArrayTargetSumSequence.count(new int[] { 1, 2, 3 }, 10) == 0; }
  @Test public void oneWithOneResult() { assert SubArrayTargetSumSequence.count(new int[] { 10 }, 10) == 1; }
  @Test public void manyWithOneResult() { assert SubArrayTargetSumSequence.count(new int[] { 1, 2, 3, 4 }, 10) == 1; }
  @Test public void manyWithOneResultNonEqualTotal() { assert SubArrayTargetSumSequence.count(new int[] { -3, 1, 2, 3, 4, 5 }, 10) == 1; }
  @Test public void manyWithManyResults() { assert SubArrayTargetSumSequence.count(new int[] { -3, 1, 2, 3, 4, 5, 5, 10 }, 10) == 3; }

}
