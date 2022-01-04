package com.algorithms.puzzles.ctci;

import org.junit.Test;

public class SmallestDifferenceTest {

  @Test(expected = ArithmeticException.class) public void emptyAs() { SmallestDifference.get(new int[0], new int[] { 1 }); }
  @Test(expected = ArithmeticException.class) public void emptyBs() { SmallestDifference.get(new int[] { 1 }, new int[0]); }
  @Test public void smallAs() { assert SmallestDifference.get(new int[] { 2 }, new int[] { 0, 3, 4, 5 }) == 1;}
  @Test public void smallBs() { assert SmallestDifference.get(new int[] { 0, 3, 4, 5 }, new int[] { 2 }) == 1;}
  @Test public void regular() { assert SmallestDifference.get(new int[] { 2, 10, 20, 100 }, new int[] { 5, 30, 63, 102 }) == 2;}

}
