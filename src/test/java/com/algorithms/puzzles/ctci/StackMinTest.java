package com.algorithms.puzzles.ctci;

import org.junit.Test;

public class StackMinTest {

    @Test
    public void pushes() {
        var stack = new StackMin();
        assert stack.size() == 0;
        stack.push(10);
        assert stack.size() == 1;
    }

    @Test
    public void peeks() {
        var stack = new StackMin();
        stack.push(10);
        assert stack.peek() == 10;
    }

    @Test
    public void pops() {
        var stack = new StackMin();
        stack.push(10);
        assert stack.pop() == 10;
    }

    @Test
    public void mins() {
        var stack = new StackMin();
        stack.push(10);
        assert stack.min() == 10;
    }

    @Test
    public void minsMany() {
        var stack = new StackMin();
        stack.push(10);
        assert stack.min() == 10;
        stack.push(8);
        assert stack.min() == 8;
        stack.push(9);
        assert stack.min() == 8;
        stack.push(6);
        assert stack.min() == 6;
    }

    @Test
    public void popsMany() {
        var stack = new StackMin();
        stack.push(10);
        stack.push(9);
        stack.push(8);
        assert stack.pop() == 8;
        assert stack.pop() == 9;
        assert stack.pop() == 10;
    }

    @Test
    public void pushesPopsAndMins() {
        var stack = new StackMin();
        stack.push(10);
        assert stack.min() == 10;
        stack.push(8);
        assert stack.min() == 8;
        assert stack.pop() == 8;
        assert stack.min() == 10;
    }
}
