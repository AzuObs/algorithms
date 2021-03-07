package com.algorithms.collections.arraylist;

import com.algorithms.collections.array.ArrayFixed;
import com.algorithms.controls.option.Option;

public class ArrayListDynamic<T> implements ArrayList<T> {
    private ArrayFixed<T> underlying;
    private int population;

    @SuppressWarnings("unchecked")
    private ArrayListDynamic(ArrayFixed<T> initial) {
        grow(initial);
    }

    private void grow(ArrayFixed<T> initial) {
        this.underlying = ArrayFixed.fill(initial.getLength() * 2);
        this.population = 0;
        for (var i = 0; i < initial.getLength(); i++) {
            if (initial.get(i).isPresent()) {
                this.underlying.set(i, initial.get(1).get());
                this.population++;
            }
        }
    }

    public static <T> ArrayListDynamic<T> apply(T... initial) {
        return new ArrayListDynamic<T>(ArrayFixed.fill(initial));
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
    public ArrayListDynamic<T> push(T element) {
        if (this.population == this.underlying.getLength()) {
            grow(this.underlying);
        }
        this.underlying.set(this.population, element);
        this.population++;
        return this;
    }

    @Override
    public ArrayListDynamic<T> pop(int index) {
        this.underlying.set(this.population - 1, null);
        this.population--;
        return this;
    }
}
