package com.algorithms.puzzles;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Stack;

public class StackOfPlates<T> {
  private ArrayList<Stack<T>> stacks;
  private int maxCapacity;

  public StackOfPlates(int maxCapacity) {
    this.stacks = new ArrayList<>();
    this.stacks.add(new Stack<>());
    this.maxCapacity = maxCapacity;
  }

  public void push(T element) {
    var head = getHeadStack().size() == maxCapacity
        ? addStack()
        : getHeadStack();
    head.push(element);
  }

  public T pop() {
    if (getHeadStack().isEmpty()) removeHeadStack();
    return getHeadStack().pop();
  }

  public ArrayList<Stack<T>> getStacks() {
    return stacks;
  }

  public int size() {
    return stacks.stream().map(Stack::size).reduce(Math::addExact).orElse(0);
  }

  public void popAt(int stack) {

  }

  private Stack<T> addStack() {
    var stack = new Stack<T>();
    stacks.add(stack);
    return stack;
  }

  private Stack<T> getHeadStack() {
    return stacks.get(stacks.size() - 1);
  }

  private void removeHeadStack() {
    if (stacks.size() == 1 && getHeadStack().isEmpty()) throw new NoSuchElementException("Stack is Empty");
    stacks.remove(stacks.size() - 1);
  }
}
