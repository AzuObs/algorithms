package com.algorithms.collections.set;

import com.algorithms.controls.option.Option;

public interface Set<T> {
    int getLength();
    Option<T> get(T element);
    Set<T> remove(T element);
    Set<T> add(T element);
}
