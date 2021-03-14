package com.algorithms.collections.cache;

import com.algorithms.controls.option.Option;

// daniel
public interface Cache<T> {
    Option<T> get(T element);
    Cache<T> add(T element);
}
