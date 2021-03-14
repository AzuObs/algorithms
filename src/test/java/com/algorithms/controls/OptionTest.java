package com.algorithms.controls;

import com.algorithms.controls.option.None;
import com.algorithms.controls.option.Some;
import org.junit.Test;

public class OptionTest {

    @Test
    public void someEquals() {
        var a = Some.apply(1);
        var b = Some.apply(1);
        assert a.equals(b);
    }

    @Test
    public void someHashCode() {
        var a = Some.apply(1);
        var b = Some.apply(1);
        assert a.hashCode() == b.hashCode();
    }

    @Test
    public void noneEquals() {
        var a = None.apply();
        var b = None.apply();
        assert a.equals(b);
    }

    @Test
    public void noneHashCode() {
        var a = None.apply();
        var b = None.apply();
        assert a.hashCode() == b.hashCode();
    }
}
