package com.algorithms.puzzles.ctci;

import org.junit.Test;

public class MinimalTreeTest {
  @Test
  public void emptyArr() {
    var arr = new int[0];
    var tree = MinimalTree.make(arr);
    assert tree == null;
  }

  @Test
  public void oneElement() {
    var arr = new int[] { 10 };
    var tree = MinimalTree.make(arr);
    assert tree.size == 1;
    assert tree.val == 10;
  }

  @Test
  public void twoElements() {
    var arr = new int[] { 9, 10 };
    var tree = MinimalTree.make(arr);
    assert tree.size == 2;
    assert tree.val == 9;
    assert tree.left.size == 1;
    assert tree.left.val == 10;
    assert tree.right == null;
  }

  @Test
  public void threeElements() {
    var arr = new int[] { 8, 9, 10 };
    var tree = MinimalTree.make(arr);
    assert tree.size == 3;
    assert tree.val == 9;
    assert tree.left.size == 1;
    assert tree.left.val == 8;
    assert tree.right.size == 1;
    assert tree.right.val == 10;
  }

  @Test
  public void balanced() {
    var arr = new int[] { 4, 5, 6, 7, 8, 9 };
    var tree = MinimalTree.make(arr);
    assert tree.size == 6;
    assert tree.left.size == 2;
    assert tree.right.size == 3;
  }
}
