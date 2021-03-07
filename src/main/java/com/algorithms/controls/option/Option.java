package com.algorithms.collections.option;

import java.util.function.Function;

public interface Option<T> {
    T get() throws NullPointerException;
    <R> Option<R> map(Function<T, R> mapper);
    boolean isPresent();
    boolean isEmpty();
}
