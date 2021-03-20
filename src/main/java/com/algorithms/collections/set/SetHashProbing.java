package com.algorithms.collections.set;

import com.algorithms.collections.map.MapHashProbing;
import com.algorithms.collections.tuples.Tuple2;
import com.algorithms.controls.option.None;
import com.algorithms.controls.option.Option;

public class SetHashProbing<T> implements Set<T> {
    private MapHashProbing<T, Option<?>> underlying;

    private SetHashProbing(T[] elements) {
        this.underlying = MapHashProbing.apply();
        for (T element: elements) {
            this.underlying.add(this.tuplize(element));
        }
    }

    @SafeVarargs
    public static <T> SetHashProbing<T> apply(T... elements) {
        return new SetHashProbing<T>(elements);
    }

    @Override
    public int getLength() {
        return this.underlying.getLength();
    }

    @Override
    public Option<T> get(T element) {
        return this.underlying.get(element).map(Tuple2::getKey);
    }

    @Override
    public SetHashProbing<T> remove(T element) {
        this.underlying.remove(element);
        return this;
    }

    @Override
    public SetHashProbing<T> add(T element) {
        this.underlying.add(this.tuplize(element));
        return this;
    }

    private Tuple2<T, Option<?>> tuplize(T element) {
        return Tuple2.apply(element, None.apply());
    }
}
