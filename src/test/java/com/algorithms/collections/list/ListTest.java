package com.algorithms.collections.list;

import org.junit.Test;

import java.util.function.Function;

public class ListTest {

    @Test
    public void reverseWorks() {
        var mapped = List.cons(1, Cons.apply(2, Cons.apply(3, Nil.pure()))).reverse();
        assert mapped.getHead().get().equals(3);
        assert mapped.getTail().getHead().get().equals(2);
        assert mapped.getTail().getTail().getHead().get().equals(1);
        assert mapped.getTail().getTail().getTail().getTail().equals(Nil.pure());
    }

    @Test
    public void mapPreservesOrder() {
        var mapped = Cons.apply(1, Cons.apply(2, Cons.apply(3, Nil.pure()))).map(Function.identity());
        assert mapped.getHead().get().equals(1);
        assert mapped.getTail().getHead().get().equals(2);
        assert mapped.getTail().getTail().getHead().get().equals(3);
        assert mapped.getTail().getTail().getTail().getTail().equals(Nil.pure());
    }

    @Test
    public void mapperFunctionWorks() {
        var mapped = Cons.apply(1, Cons.apply(2, Cons.apply(3, Nil.pure()))).map(n -> n * 2);
        assert mapped.getHead().get().equals(2);
        assert mapped.getTail().getHead().get().equals(4);
        assert mapped.getTail().getTail().getHead().get().equals(6);
        assert mapped.getTail().getTail().getTail().getTail().equals(Nil.pure());
    }
}
