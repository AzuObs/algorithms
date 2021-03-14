package com.algorithms.collections.array;

import com.algorithms.collections.array.ArrayFixed;
import com.algorithms.collections.list.Nil;
import com.algorithms.controls.option.None;
import com.algorithms.controls.option.Some;
import org.junit.Test;

public class ArrayFixedTest {

    @Test
    public void fillBySize() {
        var array = ArrayFixed.fill(3);
        assert array.get(0).equals(None.apply());
        assert array.get(1).equals(None.apply());
        assert array.get(2).equals(None.apply());
        assert array.get(3).equals(None.apply());
    }

    @Test
    public void fillByElements() {
        var array = ArrayFixed.fill('a', 'b', 'c');
        assert array.get(0).equals(Some.apply('a'));
        assert array.get(1).equals(Some.apply('b'));
        assert array.get(2).equals(Some.apply('c'));
        assert array.get(3).equals(None.apply());
    }

    @Test
    public void fillByElement() {
        var array = ArrayFixed.fill(Nil.<Integer>apply(), 3);
        assert array.get(0).equals(Some.apply(Nil.apply()));
        assert array.get(1).equals(Some.apply(Nil.apply()));
        assert array.get(2).equals(Some.apply(Nil.apply()));
        assert array.get(3).equals(None.apply());
    }

    @Test
    public void map() {
        var array = ArrayFixed.fill('a', 'b', 'c');
        var mapped = array.map(n -> (char) (n + 1));
        assert mapped.get(0).equals(Some.apply('b'));
        assert mapped.get(1).equals(Some.apply('c'));
        assert mapped.get(2).equals(Some.apply('d'));
    }
}
