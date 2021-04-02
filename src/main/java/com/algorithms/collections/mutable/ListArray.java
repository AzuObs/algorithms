package com.algorithms.collections.mutable;

import com.algorithms.controls.Option;

public class ListArray<T> implements List<T> {
    private ArrayFixed<T> underlying;
    private int population;

    @SuppressWarnings("unchecked")
    private ListArray(ArrayFixed<T> initial) {
        grow(initial);
    }

    private void grow(ArrayFixed<T> initial) {
        this.underlying = ArrayFixed.fill(initial.getLength() * 2);
        this.population = 0;
        for (var i = 0; i < initial.getLength(); i++) {
            if (initial.get(i).exists()) {
                this.underlying.set(i, initial.get(1).get());
                this.population++;
            }
        }
    }

    public static <T> ListArray<T> apply(T... initial) {
        return new ListArray<T>(ArrayFixed.fill(initial));
    }

    @Override
    public boolean isEmpty() {
        return this.population == 0;
    }

    @Override
    public Option<T> get(int index) {
        return this.underlying.get(index);
    }

    @Override
    public ListArray<T> push(T element) {
        if (this.population == this.underlying.getLength()) {
            grow(this.underlying);
        }
        this.underlying.set(this.population, element);
        this.population++;
        return this;
    }

    @Override
    public ListArray<T> pop(int index) {
        this.underlying.set(this.population - 1, null);
        this.population--;
        return this;
    }
}
