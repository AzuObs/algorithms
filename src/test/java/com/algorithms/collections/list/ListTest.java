package com.algorithms.collections.list;

import com.algorithms.controls.option.Some;
import org.junit.Test;

import java.util.function.Function;

public class ListTest {

    @Test
    public void reverseWorks() {
        var mapped = Cons.apply(1, Cons.apply(2, Cons.apply(3, Nil.apply()))).reverse();
        assert mapped.getHead().get().equals(3);
        assert mapped.getTail().getHead().get().equals(2);
        assert mapped.getTail().getTail().getHead().get().equals(1);
        assert mapped.getTail().getTail().getTail().getTail().equals(Nil.apply());
    }

    @Test
    public void mapPreservesOrder() {
        var mapped = Cons.apply(1, Cons.apply(2, Cons.apply(3, Nil.apply()))).map(Function.identity());
        assert mapped.getHead().get().equals(1);
        assert mapped.getTail().getHead().get().equals(2);
        assert mapped.getTail().getTail().getHead().get().equals(3);
        assert mapped.getTail().getTail().getTail().getTail().equals(Nil.apply());
    }

    @Test
    public void mapperFunctionWorks() {
        var mapped = Cons.apply(1, Cons.apply(2, Cons.apply(3, Nil.apply()))).map(n -> n * 2);
        assert mapped.getHead().get().equals(2);
        assert mapped.getTail().getHead().get().equals(4);
        assert mapped.getTail().getTail().getHead().get().equals(6);
        assert mapped.getTail().getTail().getTail().getTail().equals(Nil.apply());
    }

    @Test
    public void flatMap() {
        var mapped = Cons.apply(1, Cons.apply(2, Nil.apply()))
                .flatMap(n -> isEven(n) ? Cons.apply(n, Nil.apply()) : Nil.apply());
        assert mapped.getLength() == 1;
        assert mapped.getHead().equals(Some.apply(2));
    }

    private boolean isEven(int n) {
        return n % 2 == 0;
    }
}
