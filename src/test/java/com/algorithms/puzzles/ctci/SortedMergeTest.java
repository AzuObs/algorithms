package com.algorithms.puzzles.ctci;

import org.junit.Test;

import java.util.Arrays;

public class SortedMergeTest {

  @Test
  public void _2() {
    var as = new int[] { 0 };
    var bs = new int[] { 1, 0 };
    var result = SortedMerge.sortInner(as, bs, 1, 1);
    assert Arrays.equals(result, new int[] { 0, 1 });
  }

  @Test
  public void _5Mixed() {
    var as = new int[] { 0, 3, 5 };
    var bs = new int[] { 1, 8, 0, 0, 0 };
    var result = SortedMerge.sortInner(as, bs, 3, 2);
    assert Arrays.equals(result, new int[] { 0, 1, 3, 5, 8 });
  }

  @Test
  public void _5LeftBias() {
    var as = new int[] { 0, 1, 2 };
    var bs = new int[] { 3, 4, 0, 0, 0 };
    var result = SortedMerge.sortInner(as, bs, 3, 2);
    assert Arrays.equals(result, new int[] { 0, 1, 2, 3, 4 });
  }

  @Test
  public void _5RightBias() {
    var as = new int[] { 2, 3, 4 };
    var bs = new int[] { 0, 1, 0, 0, 0 };
    var result = SortedMerge.sortInner(as, bs, 3, 2);
    assert Arrays.equals(result, new int[] { 0, 1, 2, 3, 4 });
  }
}
