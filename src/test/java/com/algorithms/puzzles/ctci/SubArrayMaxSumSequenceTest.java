package com.algorithms.puzzles.ctci;

import org.junit.Test;

public class SubArrayMaxSumSequenceTest {

  @Test public void empty() { assert SubArrayMaxSumSequence.get(new int[0]) == Integer.MIN_VALUE; }
  @Test public void oneNeg() { assert SubArrayMaxSumSequence.get(new int[] { -2 }) == -2; }
  @Test public void onePos() { assert SubArrayMaxSumSequence.get(new int[] { 2 }) == 2; }
  @Test public void manyNeg() { assert SubArrayMaxSumSequence.get(new int[] { -2, -1, -4 }) == -1; }
  @Test public void manyPos() { assert SubArrayMaxSumSequence.get(new int[] { 4, 2, 3 }) == 9; }
  @Test public void posThenNeg() { assert SubArrayMaxSumSequence.get(new int[] { 2, -4 }) == 2; }
  @Test public void negThenPos() { assert SubArrayMaxSumSequence.get(new int[] { -4, 2 }) == 2; }
  @Test public void bridge() { assert SubArrayMaxSumSequence.get(new int[] { 2, -1, 3 }) == 4; }
  @Test public void noBridge() { assert SubArrayMaxSumSequence.get(new int[] { 2, -3, 3 }) == 3; }
  @Test public void mixed() { assert SubArrayMaxSumSequence.get(new int[] { -3, 6, 5, 12, 20, -60, 4, -6, 10 }) == 43; }

}
