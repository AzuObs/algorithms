package com.algorithms.collections.list;

import com.algorithms.controls.eval.Done;
import com.algorithms.controls.eval.Eval;
import com.algorithms.controls.eval.More;
import com.algorithms.controls.option.Option;
import com.algorithms.controls.option.Some;

import java.util.function.Consumer;
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
        return reverseInner(this, Nil.apply()).run();
    }

    @Override
    public List<T> concat(List<T> other) {
        return concatInner(this, other, Nil.apply()).run();
    }

    private Eval<List<T>> concatInner(List<T> as, List<T> bs, List<T> to) {
        if (!bs.isEmpty()) {
            return More.apply(() -> concatInner(as, bs.getTail(), to.prepend(bs.getHead().get())));
        } else if (!as.isEmpty()) {
            return More.apply(() -> concatInner(as.getTail(), bs, to.prepend(as.getHead().get())));
        } else {
            return Done.apply(to.reverse());
        }
    }

    @Override
    public boolean none(Function<T, Boolean> predicate) {
        return !this.any(predicate);
    }

    @Override
    public boolean any(Function<T, Boolean> predicate) {
        var some = this.flatMap(element -> predicate.apply(element) ? Cons.apply(element, Nil.apply()) : Nil.apply());
        return !some.isEmpty();
    }

    @Override
    public boolean contains(T element) {
        return containsInner(element, this).run();
    }

    private Eval<Boolean> containsInner(T element, List<T> elements) {
        if (elements.isEmpty()) {
            return Done.apply(false);
        } else {
            return More.apply(() -> {
                var head = elements.getHead();
                if (element.equals(head)) {
                    return Done.apply(true);
                } else {
                    return containsInner(element, elements.getTail());
                }
            });
        }
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
        return mapInner(this, Nil.apply(), mapper).run();
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

    @Override
    public <R> List<R> flatMap(Function<T, List<R>> mapper) {
        var mapped = this.map(mapper);
        return flatten(mapped, Nil.apply()).run();
    }

    private <R> Eval<List<R>> flatten(List<List<R>> from, List<R> to) {
        if (from.isEmpty()) {
            return Done.apply(to.reverse());
        } else {
            return More.apply(() -> flatten(from.getTail(), from.getHead().get().concat(to)));
        }
    }

    @Override
    public List<T> forEach(Consumer<T> task) {
        Function<T, T> taskInner = (element) -> {
            task.accept(element);
            return element;
        };
        return mapInner(this, Nil.apply(), taskInner).run();
    }
}
