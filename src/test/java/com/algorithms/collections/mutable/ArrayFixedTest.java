package com.algorithms.collections.mutable;

import com.algorithms.collections.immutable.List;
import com.algorithms.controls.Option;
import org.junit.Test;

public class ArrayFixedTest {

    @Test
    public void fillBySize() {
        var array = ArrayFixed.fill(3);
        assert array.get(0).equals(Option.none());
        assert array.get(1).equals(Option.none());
        assert array.get(2).equals(Option.none());
        assert array.get(3).equals(Option.none());
    }

    @Test
    public void fillByElements() {
        var array = ArrayFixed.fill('a', 'b', 'c');
        assert array.get(0).equals(Option.some('a'));
        assert array.get(1).equals(Option.some('b'));
        assert array.get(2).equals(Option.some('c'));
        assert array.get(3).equals(Option.none());
    }

    @Test
    public void fillByElement() {
        var array = ArrayFixed.fill(List.Nil.<Integer>apply(), 3);
        assert array.get(0).equals(Option.some(List.nil()));
        assert array.get(1).equals(Option.some(List.nil()));
        assert array.get(2).equals(Option.some(List.nil()));
        assert array.get(3).equals(Option.none());
    }

    @Test
    public void map() {
        var array = ArrayFixed.fill('a', 'b', 'c');
        var mapped = array.map(n -> (char) (n + 1));
        assert mapped.get(0).equals(Option.some('b'));
        assert mapped.get(1).equals(Option.some('c'));
        assert mapped.get(2).equals(Option.some('d'));
    }
}
