package com.algorithms.collections.mutable;

import com.algorithms.utils.tuples.Tuple2;
import com.algorithms.controls.Option;

public class SetProbe<T> implements Set<T> {
    private final MapProbe<T, Option<?>> underlying;

    private SetProbe(T[] elements) {
        this.underlying = MapProbe.apply();
        for (T element: elements) {
            this.underlying.add(this.tuplize(element));
        }
    }

    @SafeVarargs
    public static <T> SetProbe<T> apply(T... elements) {
        return new SetProbe<T>(elements);
    }

    @Override
    public int getLength() {
        return this.underlying.getLength();
    }

    @Override
    public Option<T> get(T element) {
        return this.underlying.get(element).map(this::detuplize);
    }

    @Override
    public SetProbe<T> remove(T element) {
        this.underlying.remove(element);
        return this;
    }

    @Override
    public SetProbe<T> add(T element) {
        this.underlying.add(this.tuplize(element));
        return this;
    }

    private Tuple2<T, Option<?>> tuplize(T element) {
        return Tuple2.apply(element, Option.none());
    }

    private T detuplize(Tuple2<T, Option<?>> tuple) { return tuple.key; }
}
