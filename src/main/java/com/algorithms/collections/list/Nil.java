package com.algorithms.collections.list;

import com.algorithms.controls.option.None;
import com.algorithms.controls.option.Option;

import java.util.function.Function;

public class Nil<T> implements List<T> {
    private static final Nil<?> INSTANCE = new Nil();

    private Nil() {}

    @SuppressWarnings("unchecked")
    public static <T> Nil<T> pure() {
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
    public <R> List<R> map(Function<T, R> mapper) {
        return Nil.<R>pure();
    }
}
