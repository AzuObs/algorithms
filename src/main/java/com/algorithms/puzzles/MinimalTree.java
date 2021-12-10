package com.algorithms.puzzles;

public class MinimalTree {
  public static class Node {
    public final int val;
    public final int size;
    public final Node left, right;
    public Node(int val, int size, Node left, Node rigth) {
      this.val = val;
      this.size = size;
      this.left = left;
      this.right = rigth;
    }
    public Node(int val, int size) {
      this.val = val;
      this.size = size;
      this.left = this.right = null;
    }
  }

  public static Node make(int[] sortedArray) {
    return recurse(0, sortedArray.length - 1, sortedArray);
  }

  private static Node recurse(int lbound, int ubound, int[] sorted) {
    if (ubound < lbound) return null;
    if (ubound == lbound) return new Node(sorted[lbound], 1, null, null);
    if (ubound == lbound + 1) return new Node(sorted[lbound], 2, new Node(sorted[ubound], 1), null);
    var medianBound = lbound + (ubound - lbound) / 2;
    var left = recurse(lbound, medianBound - 1, sorted);
    var right = recurse(medianBound + 1, ubound, sorted);
    return new Node(sorted[medianBound], left.size + right.size + 1, left, right);
  }
}
