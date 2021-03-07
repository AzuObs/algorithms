package com.algorithms.collections.array;

import com.algorithms.collections.option.None;
import com.algorithms.collections.option.Option;
import com.algorithms.collections.option.Some;

import java.util.function.Function;

public class Array<T> implements ArrayLike<T> {
    private T[] underlying;

    @SuppressWarnings("unchecked")
    private Array(T[] inputs) {
        this.underlying = inputs;
    }

    public static <T> Array<T> apply(T... inputs) {
        return new Array<T>(inputs);
    }

    @Override
    public Option<T> get(int index) {
        return this.underlying[index] == null
                ? None.apply()
                : Some.apply(this.underlying[index]);
    }

    @Override
    public Array<T> set(int index, T input) {
        this.underlying[index] = input;
        return this;
    }

    @Override
    public Array<T> remove(int index) {
        this.underlying[index] = null;
        return this;
    }

    @SuppressWarnings("unchecked")
    public Array<T> doubled() {
        var tmp = (T[]) new Object[this.underlying.length * 2];
        for (var i = 0; i < this.underlying.length; i++) {
            tmp[i] = this.underlying[i];
        }
        this.underlying = tmp;
        return this;
    }

    @SuppressWarnings("unchecked")
    public <R> Array<R> map(Function<T, R> mapper) {
        R[] tmp = (R[]) new Object[this.underlying.length];
        for (var i = 0; i < tmp.length; i++) {
            if (this.get(i) instanceof Some<?>) {
                tmp[i] = mapper.apply(this.get(i).get());
            }
        }
        return new Array(tmp);
    }

}
