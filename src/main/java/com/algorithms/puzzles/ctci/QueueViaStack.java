package com.algorithms.puzzles.ctci;

import java.util.Stack;

public class QueueViaStack<T> extends Stack<T> {
  private Stack<T> stack, kcast;

  public QueueViaStack() {
    stack = new Stack<>();
    kcast = new Stack<>();
  }

  @Override
  public T peek() {
    shiftToKcast();
    return kcast.peek();
  }

  @Override
  public T pop() {
    shiftToKcast();
    return kcast.pop();
  }

  @Override
  public T push(T element) {
    shiftToStack();
    stack.push(element);
    return element;
  }

  @Override
  public int size() {
    return Math.max(stack.size(), kcast.size());
  }

  private void shiftToKcast() {
    var times = stack.size();
    for (var i = 0; i < times; i++) {
      kcast.push(stack.pop());
    }
  }

  private void shiftToStack() {
    var times = kcast.size();
    for (var i = 0; i < times; i++) {
      stack.push(kcast.pop());
    }
  }
}
