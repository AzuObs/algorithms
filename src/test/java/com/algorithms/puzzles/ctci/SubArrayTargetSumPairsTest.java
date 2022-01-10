package com.algorithms.puzzles.ctci;

import org.junit.Test;

public class SubArrayTargetSumPairsTest {

  @Test public void empty() { assert SubArrayTargetSumPairs.count(new int[0], 10) == 0; }
  @Test public void one() { assert SubArrayTargetSumPairs.count(new int[] { 1 }, 10) == 0; }
  @Test public void twoNoMatch() { assert SubArrayTargetSumPairs.count(new int[] { 0, 1 }, 10) == 0; }
  @Test public void twoMatch() { assert SubArrayTargetSumPairs.count(new int[] { 0, 10 }, 10) == 1; }
  @Test public void threeNoMatch() { assert SubArrayTargetSumPairs.count(new int[] { 0, 1, 2 }, 10) == 0; }
  @Test public void threeOneMatch() { assert SubArrayTargetSumPairs.count(new int[] { 0, 1, 10 }, 10) == 1; }
  @Test public void threeTwoMatch() { assert SubArrayTargetSumPairs.count(new int[] { 0, 10, 10 }, 10) == 2; }
  @Test public void mixed() { assert SubArrayTargetSumPairs.count(new int[] { 0, 2, 8, 5, 5, 10, 10 }, 10) == 4; }

}
