package com.algorithms.collections.array;

import com.algorithms.controls.option.None;
import com.algorithms.controls.option.Option;
import com.algorithms.controls.option.Some;

import java.util.function.Consumer;
import java.util.function.Function;

/*
 * This array does not automatically grow in size.
 */
public class ArrayFixed<T> implements Array<T> {
    private Object[] underlying;

    @SuppressWarnings("unchecked")
    private ArrayFixed(T... initial) {
        this.underlying = initial;
    }

    @SuppressWarnings("unchecked")
    public static <T> ArrayFixed<T> fill(int size) {
        return ArrayFixed.fill((T[]) new Object[size]);
    }

    public static <T> ArrayFixed<T> fill(T... initial) {
        return new ArrayFixed<T>(initial);
    }

    public static <T> ArrayFixed<T> fill(T element, int size) {
        var array = ArrayFixed.<T>fill(size);
        for (var i = 0; i < size; i++) {
            array.set(i, element);
        }
        return array;
    }

    @SuppressWarnings("unchecked")
    public static <T> ArrayFixed<T> merge(Array<T> ones, Array<T> twos) {
        var tmp = (T[]) new Object[ones.getLength() + twos.getLength()];
        for (var i = 0; i < ones.getLength(); i++) {
            tmp[i] = ones.get(i).get();
        }
        for (var i = 0; i < twos.getLength(); i++) {
            tmp[i + ones.getLength()] = twos.get(i).get();
        }
        return ArrayFixed.fill(tmp);
    }

    @SuppressWarnings("unchecked")
    private T[] narrow() {
        return (T[]) this.underlying;
    }

    @Override
    public int getLength() {
        return this.underlying.length;
    }

    @Override
    public Option<T> get(int index) {
        if (index < this.underlying.length) {
            return this.underlying[index] == null
                ? None.apply()
                : Some.apply(narrow()[index]);
        } else {
            return None.apply();
        }
    }

    @Override
    public ArrayFixed<T> set(int index, T element) {
        this.underlying[index] = element;
        return this;
    }

    @Override
    public ArrayFixed<T> clear(int index) {
        this.underlying[index] = null;
        return this;
    }

    @Override
    public boolean contains(T element) {
        for (Object entry: this.underlying) {
            if (entry.equals(element)) return true;
        }
        return false;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <R> ArrayFixed<R> map(Function<T, R> mapper) {
        R[] tmp = (R[]) new Object[this.underlying.length];
        for (var i = 0; i < tmp.length; i++) {
            if (this.get(i).exists()) {
                tmp[i] = mapper.apply(this.get(i).get());
            }
        }
        return new ArrayFixed<R>(tmp);
    }

    @Override
    public ArrayFixed<T> forEach(Consumer<T> doer) {
        this.map(n -> {
            doer.accept(n);
            return n;
        });
        return this;
    }
}
