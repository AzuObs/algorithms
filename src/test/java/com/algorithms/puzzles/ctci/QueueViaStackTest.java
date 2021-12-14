package com.algorithms.puzzles.ctci;

import org.junit.Test;

public class QueueViaStackTest {

  @Test
  public void pushes() {
    var stack = new QueueViaStack<Integer>();
    stack.push(10);
    assert stack.size() == 1;
    stack.push(9);
    stack.push(8);
    assert stack.size() == 3;
  }

  @Test
  public void peeks() {
    var stack = new QueueViaStack<Integer>();
    stack.push(10);
    assert stack.peek() == 10;
    stack.push(9);
    stack.push(8);
    assert stack.peek() == 10;
  }

  @Test
  public void pops() {
    var stack = new QueueViaStack<Integer>();
    stack.push(10);
    assert stack.pop() == 10;
    stack.push(9);
    stack.push(8);
    assert stack.pop() == 9;
    assert stack.pop() == 8;
  }

  @Test
  public void pushesPeeksAndPops() {
    var stack = new QueueViaStack<Integer>();
    stack.push(10);
    stack.push(9);
    stack.push(8);
    assert stack.pop() == 10;
    assert stack.pop() == 9;
    assert stack.peek() == 8;
    assert stack.pop() == 8;
  }
}
