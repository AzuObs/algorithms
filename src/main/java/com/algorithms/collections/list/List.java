package com.algorithms.collections.list;

import com.algorithms.controls.option.Option;

import java.util.function.Function;

/**
 * Interface for list-like data structures. These data structures cannot have empty elements
 */
public interface List<T> {
    int getLength();
    boolean isEmpty();
    Option<T> getHead();
    List<T> getTail();
    List<T> prepend(T element);
    List<T> dropHead();
    List<T> reverse();
    <R> List<R> map(Function<T, R> mapper);
}
