package com.algorithms.collections.mutable;

import com.algorithms.controls.Option;

public class ArrayList<T> implements List<T> {
    private ArrayFixed<T> underlying;
    private int population;

    @SuppressWarnings("unchecked")
    private ArrayList(ArrayFixed<T> initial) {
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

    public static <T> ArrayList<T> apply(T... initial) {
        return new ArrayList<T>(ArrayFixed.fill(initial));
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
    public ArrayList<T> push(T element) {
        if (this.population == this.underlying.getLength()) {
            grow(this.underlying);
        }
        this.underlying.set(this.population, element);
        this.population++;
        return this;
    }

    @Override
    public ArrayList<T> pop(int index) {
        this.underlying.set(this.population - 1, null);
        this.population--;
        return this;
    }
}
