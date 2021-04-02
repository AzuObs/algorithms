package com.algorithms.controls;

import org.junit.Test;

public class OptionTest {

    @Test
    public void someEquals() {
        var a = Option.some(1);
        var b = Option.some(1);
        assert a.equals(b);
    }

    @Test
    public void someHashCode() {
        var a = Option.some(1);
        var b = Option.some(1);
        assert a.hashCode() == b.hashCode();
    }

    @Test
    public void noneEquals() {
        var a = Option.none();
        var b = Option.none();
        assert a.equals(b);
    }

    @Test
    public void noneHashCode() {
        var a = Option.none();
        var b = Option.none();
        assert a.hashCode() == b.hashCode();
    }
}
