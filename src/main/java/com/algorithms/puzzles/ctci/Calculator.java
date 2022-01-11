package com.algorithms.puzzles.ctci;

import java.util.LinkedList;

public class Calculator {

  private interface Symbol { }
  private interface Operator extends Symbol { }
  private static class Number implements Symbol { public int value; public Number(int v) {value = v;} }
  private static class Addition implements Operator { }
  private static class Subtraction implements Operator { }
  private static class Multiplication implements Operator { }
  private static class Division implements Operator { }

  public static class LexerException extends Exception { public LexerException(String msg) { super(msg); } }
  private static class Lexer {
    private LinkedList<Symbol> symbols;

    public Lexer() {
      symbols = new LinkedList<>();
    }

    public void ingest(char c) throws LexerException {
      if (isNumber(c)) {
        symbols.add(toNumber(c));
        return;
      }
      if (isOperator(c)) {
        symbols.add(toOperator(c));
        return;
      }
      throw new LexerException(String.format("Cannot parse %c", c));
    }

    private boolean isNumber(char c) {
      try {
        toNumber(c);
        return true;
      } catch (LexerException e) {
        return false;
      }
    }

    private boolean isOperator(char c) {
      try {
        toOperator(c);
        return true;
      } catch (LexerException e) {
        return false;
      }
    }

    private Number toNumber(char c) throws LexerException {
      try {
        return new Number(Integer.parseInt(String.format("%c", c)));
      } catch (NumberFormatException e) {
        throw new LexerException(String.format("Cannot convert %c to number", c));
      }
    }

    private Operator toOperator(char c) throws LexerException {
      switch (c) {
        case '+': return new Addition();
        case '-': return new Subtraction();
        case '*': return new Multiplication();
        case '/': return new Division();
        default: throw new LexerException(String.format("Cannot convert %c to operator", c));
      }
    }

    public LinkedList<Symbol> getSymbols() {
      return symbols;
    }
  }

  static public int compute(String symbolString) {
    var symbols = parse(symbolString);
    if (!symbols.isEmpty()) symbols.push(new Addition());
    return computeInner(symbols, new Addition(), new Number(0), 0);
  }

  static private int computeInner(LinkedList<Symbol> remaining, Operator prevOp, Number prevNb, int total) {
    if (remaining.size() == 0) return total;
    var currOp = (Operator) remaining.remove();
    var currNb = (Number) remaining.remove();
    var nextOp = (Operator) remaining.peek();
    if (nextOp instanceof Multiplication || nextOp instanceof Division) {
      if (nextOp instanceof Multiplication) return computeInner(remaining, currOp, new Number(currNb.value * prevNb.value), total);
      if (nextOp instanceof Division) return computeInner(remaining, currOp, new Number(currNb.value / prevNb.value), total);
      return computeInner(remaining, currOp, currNb, total);
    }
    else {
      if (currOp instanceof Multiplication) return computeInner(remaining, currOp, currNb, total + prevNb.value * currNb.value);
      if (currOp instanceof Division) return computeInner(remaining, currOp, currNb, total + prevNb.value / currNb.value);
      if (currOp instanceof Addition) return computeInner(remaining, currOp, currNb, total + currNb.value);
      return computeInner(remaining, currOp, currNb, total - currNb.value);
    }
  }

  static private LinkedList<Symbol> parse(String symbolString) {
    var lexer = new Lexer();
    try {
      for (var i = 0; i < symbolString.length(); i++) { lexer.ingest(symbolString.charAt(i)); }}
    catch (LexerException e) {
      return new LinkedList<>();
    }
    return lexer.getSymbols();
  }
}
