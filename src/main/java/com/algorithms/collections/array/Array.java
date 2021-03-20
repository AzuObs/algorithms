package com.algorithms.collections.array;

import com.algorithms.controls.option.Option;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Interface for array-like data structures. These data structures can have empty elements
 */
public interface Array<T> {
    int getLength();
    Option<T> get(int index);
    Array<T> set(int index, T element);
    Array<T> clear(int index);
    Array<T> forEach(Consumer<T> doer);
    <R> Array<R> map(Function<T, R> mapper);
    boolean contains(T element);
}
