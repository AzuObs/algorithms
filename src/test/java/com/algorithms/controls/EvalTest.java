package com.algorithms.controls;

import org.junit.Test;

import java.math.BigInteger;

public class EvalTest {

    private static BigInteger STACK_DEPTH = BigInteger.valueOf(10000000);

    @Test(expected = StackOverflowError.class)
    public void nonEvalVersionThrowsStackOverflow() {
        addWhile(EvalTest.STACK_DEPTH, BigInteger.ZERO);
    }

    protected BigInteger addWhile(BigInteger current, BigInteger total) {
        return current.equals(BigInteger.ZERO)
                ? total
                : addWhile(current.subtract(BigInteger.ONE), current.add(total));
    }

    @Test
    public void evalComputationIsStackSafe() {
        var result = evalAddWhile(EvalTest.STACK_DEPTH, BigInteger.ZERO).run();
        assert !result.equals(BigInteger.ZERO);
    }

    private Eval<BigInteger> evalAddWhile(BigInteger current, BigInteger total) {
        return current.equals(BigInteger.ONE)
                ? Eval.done(total)
                : Eval.more(() -> evalAddWhile(current.subtract(BigInteger.ONE), current.add(total)));
    }
}
