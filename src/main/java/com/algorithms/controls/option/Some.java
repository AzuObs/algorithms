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
    public boolean exists() {
        return true;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int hashCode() {
        return underlying.hashCode();
    }
    
    @Override
    public boolean equals(Object other) {
        if (other instanceof Some<?>) {
            return this.get().equals(((Some<?>)other).get());
        } else {
            return false;
        }
    }
}
