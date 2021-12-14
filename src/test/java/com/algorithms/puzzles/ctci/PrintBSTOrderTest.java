package com.algorithms.puzzles.ctci;

import org.junit.Test;
import com.algorithms.puzzles.ctci.PrintBSTOrder.Node;

import java.util.Arrays;

public class PrintBSTOrderTest {

  @Test
  public void printEmpty() {
    assert Arrays.equals(PrintBSTOrder.ordered(null), new int[0]);
  }

  @Test
  public void printOne() {
    var node = new Node(10);
    assert Arrays.equals(PrintBSTOrder.ordered(node), new int[] { 10 });
  }

  @Test
  public void printTwo() {
    var node = new Node(10, new Node(5), null);
    assert Arrays.equals(PrintBSTOrder.ordered(node), new int[] { 5, 10 });
  }

  @Test
  public void printThree() {
    var node = new Node(10, new Node(5), new Node(12));
    assert Arrays.equals(PrintBSTOrder.ordered(node), new int[] { 5, 10, 12 });
  }

  @Test
  public void printMany() {
    var node =
        new Node(10,
            new Node(5,
                new Node(4),
                new Node(8,
                    new Node(7),
                    new Node(9))),
            new Node(12));
    assert Arrays.equals(PrintBSTOrder.ordered(node), new int[] { 4, 5, 7, 8, 9, 10, 12 });
  }
}
