package com.algorithms.collections.array;

import com.algorithms.collections.option.Option;

import java.util.function.Function;

/**
 * Interface for array-like data structures. These data structures can have empty elements
 */
public interface ArrayLike<T> {
    Option<T> get(int index);
    ArrayLike<T> set(int index, T input);
    ArrayLike<T> remove(int index);
    ArrayLike<T> doubled();
    <R> ArrayLike<R> map(Function<T, R> mapper);
}
