package com.algorithms.puzzles.ctci;

import org.junit.Test;

public class StackOfPlatesTest {

  @Test
  public void pushes() {
    var stacks = new StackOfPlates<Integer>(3);
    stacks.push(10);
    assert stacks.size() == 1;
    assert stacks.pop() == 10;
  }

  @Test
  public void pushesAndCreatesNewStacks() {
    var stacks = new StackOfPlates<Integer>(3);
    stacks.push(10);
    stacks.push(9);
    stacks.push(8);
    assert stacks.getStacks().size() == 1;
    stacks.push(7);
    assert stacks.getStacks().size() == 2;
  }

  @Test
  public void pops() {
    var stacks = new StackOfPlates<Integer>(3);
    stacks.push(10);
    assert stacks.pop() == 10;
    assert stacks.size() == 0;
  }

  @Test
  public void popsAndRemovesStacks() {
    var stacks = new StackOfPlates<Integer>(3);
    stacks.push(10);
    stacks.push(9);
    stacks.push(8);
    stacks.push(7);
    assert stacks.getStacks().size() == 2;
    assert stacks.pop() == 7;
    assert stacks.getStacks().size() == 2;
    assert stacks.pop() == 8;
    assert stacks.getStacks().size() == 1;
  }
}
