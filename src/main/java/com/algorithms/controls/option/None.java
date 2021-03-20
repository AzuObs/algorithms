package com.algorithms.controls.option;

import java.util.function.Function;

public class None<T> implements Option<T> {
    public static final None<?> INSTANCE = new None<>();

    private None() { }

    @SuppressWarnings("unchecked")
    public static <T> Option<T> apply() {
        return (Option<T>) None.INSTANCE;
    }

    @Override
    public T get() throws NullPointerException {
        throw new NullPointerException("Can't call .get on None");
    }

    @Override
    @SuppressWarnings("unchecked")
    public <R> Option<R> map(Function<T, R> mapper) {
        return (Option<R>) None.INSTANCE;
    }

    @Override
    public boolean exists() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return true;
    }
}
