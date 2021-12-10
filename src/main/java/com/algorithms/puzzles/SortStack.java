package com.algorithms.puzzles;

import java.util.Stack;

public class SortStack {
  public static Stack<Integer> sort(Stack<Integer> stack) {
    var sorted = new Stack<Integer>();
    while (!stack.isEmpty()) {
      var popped = stack.pop();

      while (!sorted.isEmpty()) {
        if (popped <= sorted.peek()) {
          sorted.add(popped);
          break;
        } else {
          stack.push(sorted.pop());
        }
      }
      if (sorted.isEmpty()) sorted.push(popped);
    }
    return sorted;
  }
}
