package com.algorithms.puzzles.ctci;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ThreeInOne {
    private int[] values;
    private int[] starts;
    private int[] lengths;

    public ThreeInOne() {
        values = new int[9];
        starts = new int[] { 0, 3, 6 };
        lengths = new int[] { 0, 0, 0 };
    }

    public void push(int stack, int value) {
        if (isStackFull(stack)) growStacks();
        values[starts[stack] + lengths[stack]++] = value;
    }

    public int peek(int stack) {
        if (isStackEmpty(stack)) throw new NoSuchElementException("Stack is empty");
        return values[starts[stack] + lengths[stack] - 1];
    }

    public int pop(int stack) {
        if (isStackEmpty(stack)) throw new NoSuchElementException("Stack is empty");
        return values[starts[stack] + lengths[stack]-- - 1];
    }

    public int size(int stack) {
        return lengths[stack];
    }

    private boolean isStackFull(int stack) {
        return isLastStack(stack)
            ? starts[stack] + lengths[stack] == values.length
            : starts[stack] + lengths[stack] == starts[stack + 1];
    }

    private boolean isStackEmpty(int stack) {
        return lengths[stack] == 0;
    }

    private boolean isFirstStack(int stack) {
        return stack == 0;
    }

    private boolean isLastStack(int stack) {
        return stack == 2;
    }

    private void growStacks() {
        var newLengths = new int[] { 0, 0 ,0 };
        var newStarts = new int[] { 0, 0, 0 };
        for (var i = 0; i <= 2; i++) {
            newLengths[i] = shouldStackGrow(i) ? lengths[i] * 2 : lengths[i];
            newStarts[i] = isFirstStack(i) ? 0 : newStarts[i - 1] + Math.max(newLengths[i - 1], 3);
        }
        var newValues = new int[Arrays.stream(newLengths).map(n -> Math.max(n, 3)).sum()];
        for (var i = 0; i <= 2; i++) {
            System.arraycopy(
                    values,
                    starts[i],
                    newValues,
                    newStarts[i],
                    lengths[i]);
        }
        values = newValues;
    }

    private boolean shouldStackGrow(int stack) {
        var remainingCapacity = isLastStack(stack)
                ? values.length - (starts[stack] + lengths[stack])
                : starts[stack + 1] - (starts[stack] + lengths[stack]);

        return remainingCapacity < lengths[stack];
    }
}