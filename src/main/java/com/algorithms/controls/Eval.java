package com.algorithms.controls;

import java.util.function.Supplier;

/**
 * Control data structure which allows for computations. Eval is an implementation of a trampoline which allows for
 * stack-safe recursion.
 */
public abstract class Eval<T> {
    public abstract T run();

    public static <T> Eval<T> done(T input) { return Done.apply(input); }
    public static <T> Eval<T> more(Supplier<Eval<T>> computation) { return More.apply(computation); }

    public static class Done<T> extends Eval<T> {
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
    }

    public static class More<T> extends Eval<T> {
        private final Supplier<Eval<T>> computation;

        private More(Supplier<Eval<T>> computation) {
            this.computation = computation;
        }

        public static <T> More<T> apply(Supplier<Eval<T>> computation) {
            return new More<T>(computation);
        }

        protected Eval<T> get() {
            return computation.get();
        }

        @Override
        public T run() {
            Eval<T> result = this.get();
            // this will always be More<T>
            while (result instanceof Eval.More<?>) {
                result = ((More<T>) result).get();
            }
            // this will always be Done<T>
            return result.run();
        }
    }
}
