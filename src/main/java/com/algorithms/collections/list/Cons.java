package com.algorithms.collections.list;

import com.algorithms.controls.eval.Done;
import com.algorithms.controls.eval.Eval;
import com.algorithms.controls.eval.More;
import com.algorithms.controls.option.Option;
import com.algorithms.controls.option.Some;

import java.util.function.Function;

public class Cons<T> implements List<T> {
    private final T head;
    private final List<T> tail;
    private final int length;

    private Cons(T head, List<T> tail) {
        this.head = head;
        this.tail = tail;
        this.length = tail.getLength() + 1;
    }

    public static <T> List<T> apply(T head, List<T> tail) {
        return new Cons<T>(head, tail);
    }

    @Override
    public int getLength() {
        return this.length;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Option<T> getHead() {
        return Some.apply(this.head);
    }

    @Override
    public List<T> getTail() {
        return this.tail;
    }

    @Override
    public List<T> prepend(T element) {
        return Cons.apply(element, this);
    }

    @Override
    public List<T> dropHead() {
        return this.tail;
    }

    @Override
    public List<T> reverse() {
        return reverseInner(this, Nil.pure()).run();
    }

    private Eval<List<T>> reverseInner(List<T> from, List<T> to) {
        if (from.isEmpty()) {
            return Done.apply(to);
        } else {
            return More.apply(() -> reverseInner(from.getTail(), Cons.apply(from.getHead().get(), to)));
        }
    }

    @Override
    public <R> List<R> map(Function<T, R> mapper) {
        return mapInner(this, Nil.pure(), mapper).run();
    }

    private <R> Eval<List<R>> mapInner(List<T> from, List<R> to, Function<T, R> mapper) {
        if (from.isEmpty()) {
            return Done.apply(to.reverse());
        } else {
            return More.apply(() -> {
                var mapped = mapper.apply(from.getHead().get());
                return mapInner(from.getTail(), Cons.apply(mapped, to), mapper);
            });
        }
    }
}
