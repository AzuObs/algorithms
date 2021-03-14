package com.algorithms.controls.option;

import java.util.function.Function;

public interface Option<T> {
    T get() throws NullPointerException;
    <R> Option<R> map(Function<T, R> mapper);
    boolean exists();
    boolean isEmpty();
}
