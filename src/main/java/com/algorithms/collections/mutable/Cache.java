package com.algorithms.collections.mutable;

import com.algorithms.controls.Option;

// daniel
public interface Cache<T> {
    Option<T> get(T element);
    Cache<T> add(T element);
}
