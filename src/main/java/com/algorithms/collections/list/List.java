package com.algorithms.collections.list;

import com.algorithms.controls.option.Option;

import java.util.function.Consumer;
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
    List<T> concat(List<T> other);
    boolean contains(T element);
    <R> List<R> map(Function<T, R> mapper);
    <R> List<R> flatMap(Function<T, List<R>> mapper);
    List<T> forEach(Consumer<T> task);

    static <T> List<T> empty() {
        return Nil.apply();
    }
}
