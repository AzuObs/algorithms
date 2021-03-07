package com.algorithms.controls.eval;

public class Done<T> extends Eval<T> {
    private final T underlying;

    private Done(T input) {
        this.underlying = input;
    }

    public static <T> Done<T> apply(T result) {
        return new Done<T>(result);
    }

    @Override
    public T run() {
        return this.underlying;
    }

    @Override
    protected Eval<T> get() {
        return this;
    }
}
