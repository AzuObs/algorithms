package com.algorithms.collections.immutable;

import com.algorithms.controls.Option;
import org.junit.Test;

import java.util.function.Function;

public class ListTest {

    @Test
    public void reverseWorks() {
        var mapped = List.cons(1, List.cons(2, List.cons(3, List.nil()))).reverse();
        assert mapped.getHead().get().equals(3);
        assert mapped.getTail().getHead().get().equals(2);
        assert mapped.getTail().getTail().getHead().get().equals(1);
        assert mapped.getTail().getTail().getTail().getTail().equals(List.nil());
    }

    @Test
    public void mapPreservesOrder() {
        var mapped = List.cons(1, List.cons(2, List.cons(3, List.nil()))).map(Function.identity());
        assert mapped.getHead().get().equals(1);
        assert mapped.getTail().getHead().get().equals(2);
        assert mapped.getTail().getTail().getHead().get().equals(3);
        assert mapped.getTail().getTail().getTail().getTail().equals(List.nil());
    }

    @Test
    public void mapperFunctionWorks() {
        var mapped = List.cons(1, List.cons(2, List.cons(3, List.nil()))).map(n -> n * 2);
        assert mapped.getHead().get().equals(2);
        assert mapped.getTail().getHead().get().equals(4);
        assert mapped.getTail().getTail().getHead().get().equals(6);
        assert mapped.getTail().getTail().getTail().getTail().equals(List.nil());
    }

    @Test
    public void flatMap() {
        var mapped = List.cons(1, List.cons(2, List.nil()))
                .flatMap(n -> isEven(n) ? List.cons(n, List.nil()) : List.nil());
        assert mapped.getLength() == 1;
        assert mapped.getHead().equals(Option.some(2));
    }

    private boolean isEven(int n) {
        return n % 2 == 0;
    }
}
