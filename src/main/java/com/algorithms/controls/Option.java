package com.algorithms.controls;

import java.util.function.Function;

public interface Option<T> {
    T get() throws NullPointerException;
    <R> Option<R> map(Function<T, R> mapper);
    boolean exists();
    boolean isEmpty();

    static <T> Option<T> none() { return None.apply(); }
    static <T> Option<T> some(T element) { return Some.apply(element); }

    class None<T> implements Option<T> {
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

    class Some<T> implements Option<T> {
        final T underlying;

        private Some(T element) {
            this.underlying = element;
        }

        public static <T> Option<T> apply(T element) {
            return new Some<T>(element);
        }

        @Override
        public <R> Option<R> map(Function<T, R> mapper) {
            return Some.apply(mapper.apply(this.get()));
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
            if (other instanceof Option.Some<?>) {
                return this.get().equals(((Some<?>)other).get());
            } else {
                return false;
            }
        }
    }
}
