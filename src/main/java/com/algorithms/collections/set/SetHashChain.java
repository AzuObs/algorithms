package com.algorithms.collections.set;

import com.algorithms.collections.map.MapHashChain;
import com.algorithms.collections.tuples.Tuple2;
import com.algorithms.controls.option.None;
import com.algorithms.controls.option.Option;

public class SetHashChain<T> implements Set<T> {
    private MapHashChain<T, Option<?>> underlying;

    private SetHashChain(T[] elements) {
        this.underlying = MapHashChain.<T, Option<?>>apply();
        for (T element: elements) {
            this.underlying.add(this.tuplize(element));
        }
    }

    @SafeVarargs
    public static <T> SetHashChain<T> apply(T... elements) {
        return new SetHashChain<T>(elements);
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
    public SetHashChain<T> remove(T element) {
        this.underlying.remove(element);
        return this;
    }

    @Override
    public SetHashChain<T> add(T element) {
        this.underlying.add(this.tuplize(element));
        return this;
    }

    private Tuple2<T, Option<?>> tuplize(T element) {
        return Tuple2.apply(element, None.apply());
    }

    private T detuplize(Tuple2<T, Option<?>> tuple) { return tuple.key; }
}
