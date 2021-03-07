package com.algorithms.controls.eval;

/**
 * Control data structure which allows for computations. Eval is an implementation of a trampoline which allows for
 * stack-safe recursion.
 */
public abstract class Eval<T> {
    protected abstract Eval<T> get();
    public abstract T run();
}
