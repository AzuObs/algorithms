package com.algorithms.controls.eval;

import java.util.function.Supplier;

public class More<T> extends Eval<T> {
    private final Supplier<Eval<T>> computation;

    private More(Supplier<Eval<T>> computation) {
        this.computation = computation;
    }

    public static <T> More<T> apply(Supplier<Eval<T>> computation) {
        return new More<T>(computation);
    }

    @Override
    protected Eval<T> get() {
        return computation.get();
    }

    @Override
    public T run() {
        Eval<T> result = this.get();
        // this will always be More<T>
        while (result instanceof More<?>) {
            result = result.get();
        }
        // this will always be Done<T>
        return result.run();
    }
}
