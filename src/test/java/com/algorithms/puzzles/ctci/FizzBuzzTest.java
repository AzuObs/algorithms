package com.algorithms.puzzles.ctci;

import org.junit.Test;

public class FizzBuzzTest {
    @Test
    public void isReturnsFizzOnEven() {
        assert FizzBuzz.classify(0).equals("Fizz");
        assert FizzBuzz.classify(2).equals("Fizz");
        assert FizzBuzz.classify(-2).equals("Fizz");
        assert FizzBuzz.classify(4).equals("Fizz");
        assert FizzBuzz.classify(10).equals("Fizz");
        assert FizzBuzz.classify(660).equals("Fizz");
    }

    @Test
    public void isReturnsBuzzOnOdd() {
        assert FizzBuzz.classify(1).equals("Buzz");
        assert FizzBuzz.classify(-1).equals("Buzz");
        assert FizzBuzz.classify(-5).equals("Buzz");
        assert FizzBuzz.classify(9).equals("Buzz");
        assert FizzBuzz.classify(11).equals("Buzz");
        assert FizzBuzz.classify(661).equals("Buzz");
    }
}
