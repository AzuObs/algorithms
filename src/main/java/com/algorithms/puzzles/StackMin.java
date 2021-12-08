package com.algorithms.puzzles;

import java.util.NoSuchElementException;

public class StackMin {
    private Node head;
    private int size;

    private class Node {
        private int value;
        private int min;
        private Node next;

        public Node(int value, int min, Node next) {
            this.value = value;
            this.next = next;
            this.min = min;
        }
    }

    public StackMin() {}

    public void push(int value) {
        var min= head == null
                ? value
                : Math.min(head.min, value);
        size++;
        head = new Node(value, min, head);
    }

    public int pop() {
        if (head == null) throw new NoSuchElementException("Stack is empty");
        var previousHead = head;
        head = head.next;
        size--;
        return previousHead.value;
    }

    public int peek() {
        if (head == null) throw new NoSuchElementException("Stack is empty");
        return head.value;
    }

    public int min() {
        if (head == null) throw new NoSuchElementException("Stack is empty");
        return head.min;
    }

    public int size() {
        return size;
    }
}
