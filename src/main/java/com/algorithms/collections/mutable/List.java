package com.algorithms.collections.mutable;

import com.algorithms.controls.Option;

/**
 * List implementation backed by an Array
 */
public interface List<T> {
    boolean isEmpty();
    Option<T> get(int index);
    List<T> push(T element);
    List<T> pop(int index);
}
