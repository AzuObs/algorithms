package com.algorithms.collections.mutable;

import com.algorithms.controls.Option;

public class DoublyListDeque<T> implements Deque<T> {
    private Node<T> head;
    private Node<T> tail;
    private int length;

    public DoublyListDeque() {
        this.head = Node.empty();
        this.tail = Node.empty();
        this.length = 0;
    }

    @Override
    public DoublyListDeque<T> prepend(T element) {
        this.head = Node.of(element, Node.empty(), this.head);
        this.head.getNext().setPrevious(this.head);
        if (this.isEmpty()) this.tail = head;
        this.length++;
        return this;
    }

    @Override
    public DoublyListDeque<T> append(T element) {
        this.tail = Node.of(element, this.tail, Node.empty());
        this.tail.getPrevious().setNext(this.tail);
        if (this.isEmpty()) this.head = tail;
        this.length++;
        return this;
    }

    @Override
    public Option<T> pop() {
        if (this.isEmpty()) {
            return Option.none();
        } else {
            var popped = this.head;
            this.head = this.head.getNext();
            this.length--;
            return popped.getValue();
        }
    }

    @Override
    public boolean isEmpty() {
        return this.length == 0;
    }

    @Override
    public boolean exists() {
        return !this.isEmpty();
    }

    private interface Node<T> {
        Option<T> getValue();
        Node<T> setPrevious(Node<T> previous);
        Node<T> getPrevious();
        Node<T> setNext(Node<T> next);
        Node<T> getNext();
        boolean isEmpty();
        boolean exists();

        static <T> Node<T> empty() { return new Empty<T>(); }
        static <T> Node<T> single(T value) { return new Deque<>(value, Node.empty(), Node.empty()); };
        static <T> Node<T> of(T value, Node<T> previous, Node<T> next) { return new Deque<>(value, previous, next); }
    }

    static private class Empty<T> implements Node<T> {
        public Empty() {}
        @Override
        public Option<T> getValue() { return Option.none(); }
        @Override
        public Node<T> setPrevious(Node<T> previous) { return new Empty<T>(); }
        @Override
        public Node<T> getPrevious() { return new Empty<T>(); }
        @Override
        public Node<T> setNext(Node<T> next) { return new Empty<T>(); }
        @Override
        public Node<T> getNext() { return new Empty<T>(); }
        @Override
        public boolean isEmpty() { return true; }
        @Override
        public boolean exists() { return false; }
    }

    static private class Deque<T> implements Node<T> {
        private T value;
        private Node<T> previous;
        private Node<T> next;

        public Deque(T value, Node<T> previous, Node<T> next) {
            this.value = value;
            this.previous = previous;
            this.next = next;
        }

        @Override
        public Option<T> getValue() { return Option.some(value); }
        @Override
        public Node<T> setPrevious(Node<T> previous) { this.previous = previous; return this; }
        @Override
        public Node<T> getPrevious() { return previous; }
        @Override
        public Node<T> setNext(Node<T> next) { this.next = next; return this; }
        @Override
        public Node<T> getNext() { return next; }
        @Override
        public boolean isEmpty() { return false; }
        @Override
        public boolean exists() { return true; }
    }
}
