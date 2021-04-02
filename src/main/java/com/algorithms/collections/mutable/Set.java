package com.algorithms.collections.mutable;

import com.algorithms.controls.Option;

public interface Set<T> {
    int getLength();
    Option<T> get(T element);
    Set<T> remove(T element);
    Set<T> add(T element);
}
