package com.algorithms.puzzles.ctci;

import org.junit.Test;

import java.util.Arrays;

public class QueensTest {

  @Test public void _1() {
    var result = Queens.ways(1);
    assert result.size() == 1;
    assert Arrays.equals(result.get(0), new Integer[] { 0 });
  }

  @Test public void _2() {
    var result = Queens.ways(2);
    assert result.size() == 0;
  }

  @Test public void _3() {
    var result = Queens.ways(3);
    assert result.size() == 0;
  }

  @Test public void _4() {
    var result = Queens.ways(4);
    assert result.size() == 2;
    assert Arrays.equals(result.get(0), new Integer[] { 1, 3, 0, 2 });
    assert Arrays.equals(result.get(1), new Integer[] { 2, 0, 3, 1 });
  }

}
