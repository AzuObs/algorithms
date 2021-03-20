package com.algorithms.collections.list;

import com.algorithms.controls.option.None;
import com.algorithms.controls.option.Option;

import java.util.function.Consumer;
import java.util.function.Function;

public class Nil<T> implements List<T> {
    private static final Nil<?> INSTANCE = new Nil();

    private Nil() {}

    @SuppressWarnings("unchecked")
    public static <T> Nil<T> apply() {
        return (Nil<T>) Nil.INSTANCE;
    }

    @Override
    public int getLength() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public Option<T> getHead() {
        return None.apply();
    }

    @Override
    public List<T> getTail() {
        return this;
    }

    @Override
    public List<T> prepend(T element) {
        return Cons.apply(element, this);
    }

    @Override
    public List<T> dropHead() {
        return this;
    }

    @Override
    public List<T> reverse() {
        return this;
    }

    @Override
    public List<T> concat(List<T> other) {
        return other;
    }

    @Override
    public boolean none(Function<T, Boolean> predicate) {
        return !this.any(predicate);
    }

    @Override
    public boolean any(Function<T, Boolean> predicate) {
        return false;
    }

    @Override
    public boolean contains(T element) {
        return false;
    }

    @Override
    public <R> List<R> map(Function<T, R> mapper) {
        return Nil.<R>apply();
    }

    @Override
    public <R> List<R> flatMap(Function<T, List<R>> mapper) {
        return Nil.<R>apply();
    }

    @Override
    public List<T> forEach(Consumer<T> task) {
        return Nil.<T>apply();
    }
}
