package com.algorithms.puzzles.ctci;

import org.junit.Test;

import java.util.ArrayList;

public class FindDuplicatesTest {

  @Test
  public void zero() {
    var expected = new ArrayList<Integer>();
    assert FindDuplicates.in(0, new int[] { }).equals(expected);
  }

  @Test
  public void one() {
    var expected = new ArrayList<Integer>();
    assert FindDuplicates.in(0, new int[] { 0 }).equals(expected);
  }

  @Test
  public void two() {
    var expected = new ArrayList<Integer>();
    assert FindDuplicates.in(1, new int[] { 0, 1 }).equals(expected);
  }

  @Test
  public void withDuplicates() {
    var expected = new ArrayList<Integer>();
    expected.add(1);
    expected.add(3);
    assert FindDuplicates.in(5, new int[] {0, 1, 1, 2, 3, 3, 4, 5}).equals(expected);
  }

  @Test
  public void withoutDuplicates() {
    var expected = new ArrayList<Integer>();
    assert FindDuplicates.in(5, new int[] {0, 2, 4, 5}).equals(expected);
  }
}
