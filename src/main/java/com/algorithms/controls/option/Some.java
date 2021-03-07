package com.algorithms.controls.option;

import java.util.function.Function;

public class Some<T> implements Option<T> {
    final T underlying;

    private Some(T element) {
        this.underlying = element;
    }

    public static <T> Option<T> apply(T element) {
        return new Some<T>(element);
    }

    @Override
    public <R> Option<R> map(Function<T, R> mapper) {
        return null;
    }

    @Override
    public T get() throws NullPointerException {
        return underlying;
    }

    @Override
    public boolean isPresent() {
        return true;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
