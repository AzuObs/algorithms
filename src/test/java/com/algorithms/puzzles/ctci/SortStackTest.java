package com.algorithms.puzzles.ctci;

import org.junit.Test;

import java.util.Stack;

public class SortStackTest {

  @Test
  public void sortsEmpty() {
    var stack = new Stack<Integer>();
    var sorted = SortStack.sort(stack);
    assert sorted.size() == 0;
  }

  @Test
  public void sortsOne() {
    var stack = new Stack<Integer>();
    stack.add(10);
    var sorted = SortStack.sort(stack);
    assert sorted.size() == 1;
    assert sorted.pop() == 10;
  }

  @Test
  public void sortsOrdered() {
    var stack = new Stack<Integer>();
    stack.add(8);
    stack.add(9);
    stack.add(10);
    var sorted = SortStack.sort(stack);
    assert sorted.size() == 3;
    assert sorted.pop() == 8;
    assert sorted.pop() == 9;
    assert sorted.pop() == 10;
  }

  @Test
  public void sortsReversed() {
    var stack = new Stack<Integer>();
    stack.add(10);
    stack.add(9);
    stack.add(8);
    var sorted = SortStack.sort(stack);
    assert sorted.size() == 3;
    assert sorted.pop() == 8;
    assert sorted.pop() == 9;
    assert sorted.pop() == 10;
  }

  @Test
  public void sortsRandom() {
    var stack = new Stack<Integer>();
    stack.add(10);
    stack.add(1);
    stack.add(2);
    stack.add(0);
    stack.add(5);
    var sorted = SortStack.sort(stack);
    assert sorted.size() == 5;
    assert sorted.pop() == 0;
    assert sorted.pop() == 1;
    assert sorted.pop() == 2;
    assert sorted.pop() == 5;
    assert sorted.pop() == 10;
  }

}
