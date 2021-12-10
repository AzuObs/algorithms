package com.algorithms.puzzles;

import java.util.Arrays;

public class PrintBSTOrder {
  public static class Node {
    private final int val;
    private final Node left, right;
    public Node(int val, Node left, Node right) {
      this.val = val;
      this.left = left;
      this.right = right;
    }
    public Node(int val) {
      this.val = val;
      this.left = this.right = null;
    }
  }

  public static int[] ordered(Node node) {
    if (node == null) return new int[0];
    var lordered = ordered(node.left);
    var rordered = ordered(node.right);
    return concat(lordered, node.val, rordered);
  }

  private static int[] concat(int[] left, int median, int[] right) {
    var result = Arrays.copyOf(left, left.length + right.length + 1);
    result[left.length] = median;
    System.arraycopy(right, 0, result, left.length + 1, right.length);
    return result;
  }
}
