package com.algorithms.collections.array;

import com.algorithms.controls.option.Option;

import java.util.function.Function;

/**
 * Interface for array-like data structures. These data structures can have empty elements
 */
public interface Array<T> {
    int getLength();
    Option<T> get(int index);
    Array<T> set(int index, T input);
    Array<T> clear(int index);
    <R> Array<R> map(Function<T, R> mapper);
}
