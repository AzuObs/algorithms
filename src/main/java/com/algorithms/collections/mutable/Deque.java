package com.algorithms.collections.mutable;

import com.algorithms.controls.Option;

public interface Deque<T> {
    Deque<T> prepend(T element);
    Deque<T> append(T element);
    Option<T> pop();
    boolean isEmpty();
    boolean exists();
}
