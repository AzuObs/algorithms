package com.algorithms.puzzles.ctci;

import org.junit.Test;

import java.util.NoSuchElementException;

public class ThreeInOneTest {

    @Test
    public void canAddToStack() {
        var stack = new ThreeInOne();
        assert stack.size(1) == 0;
        stack.push(1, 10);
        assert stack.size(1) == 1;
    }

    @Test
    public void canAddToStackAfterGrowth() {
        var stack = new ThreeInOne();
        assert stack.size(1) == 0;
        stack.push(1, 10);
        stack.push(1, 10);
        stack.push(1, 10);
        stack.push(1, 10);
        assert stack.size(1) == 4;
    }

    @Test
    public void onlyOneStackGrowsIfOthersDoNotNeedTo() {
        var stack = new ThreeInOne();
        assert stack.size(0) == 0;
        assert stack.size(1) == 0;
        assert stack.size(2) == 0;
        stack.push(1, 10);
        stack.push(1, 10);
        stack.push(1, 10);
        stack.push(1, 10);
        assert stack.size(0) == 0;
        assert stack.size(1) == 4;
        assert stack.size(2) == 0;
    }

    @Test
    public void manyStacksGrowIfTheyNeedTo() {
        var stack = new ThreeInOne();
        assert stack.size(0) == 0;
        assert stack.size(1) == 0;
        assert stack.size(2) == 0;
        stack.push(1, 10);
        stack.push(1, 10);
        stack.push(1, 10);
        stack.push(1, 10);
        stack.push(2, 10);
        stack.push(2, 10);
        stack.push(2, 10);
        stack.push(2, 10);
        assert stack.size(0) == 0;
        assert stack.size(1) == 4;
        assert stack.size(2) == 4;
    }

    @Test
    public void peekWorks() {
        var stack = new ThreeInOne();
        stack.push(1, 10);
        assert stack.peek(1) == 10;
    }

    @Test
    public void peekWorksAfterGrowth() {
        var stack = new ThreeInOne();
        stack.push(1, 10);
        stack.push(1, 9);
        stack.push(1, 8);
        stack.push(1, 7);
        assert stack.peek(1) == 7;
    }

    @Test
    public void peekCanBeChained() {
        var stack = new ThreeInOne();
        stack.push(1, 10);
        assert stack.peek(1) == 10;
        assert stack.peek(1) == 10;
    }

    @Test
    public void cantPeekEmptyStack() {
        var stack = new ThreeInOne();
        try {
            stack.peek(1);
        } catch (NoSuchElementException e) {
            assert e.getMessage().equals("Stack is empty");
        }
    }

    @Test
    public void popWorks() {
        var stack = new ThreeInOne();
        stack.push(1, 10);
        assert stack.pop(1) == 10;
    }

    @Test
    public void popWorksAfterGrowth() {
        var stack = new ThreeInOne();
        stack.push(1, 10);
        stack.push(1, 9);
        stack.push(1, 8);
        stack.push(1, 7);
        assert stack.pop(1) == 7;
    }

    @Test
    public void popIsLIFO() {
        var stack = new ThreeInOne();
        stack.push(1, 10);
        stack.push(1, 9);
        stack.push(1, 8);
        stack.push(1, 7);
        assert stack.pop(1) == 7;
        assert stack.pop(1) == 8;
        assert stack.pop(1) == 9;
        assert stack.pop(1) == 10;
    }

    @Test
    public void cantPopEmptyStack() {
        var stack = new ThreeInOne();
        try {
            stack.pop(1);
        } catch (NoSuchElementException e) {
            assert e.getMessage().equals("Stack is empty");
        }
    }
}
