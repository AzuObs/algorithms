package com.algorithms.puzzles.ctci;

public class LivingPeople {
  public static class Person {
    public final int birth;
    public final int death;
    private Person(int birth, int death) {
      this.birth = birth;
      this.death = death;
    }
    public static Person create(int birth, int death) throws ImpossibleAgeException {
      if (death - birth < 0 || death - birth > 200) throw new ImpossibleAgeException();
      return new Person(birth, death);
    }
    public static class ImpossibleAgeException extends Exception {
      public ImpossibleAgeException() {
        super("Age is impossible");
      }
    }
  }

  private final int[] livingPeople;
  private final int minYear;

  private LivingPeople(int[] livingPeople, int minYear) {
    this.livingPeople = livingPeople;
    this.minYear = minYear;
  }

  public int getCount(int year) {
    return livingPeople[year - minYear];
  }

  public static LivingPeople create(Person[] persons) {
    var minYear = Integer.MAX_VALUE;
    var maxYear = Integer.MIN_VALUE;
    for (Person person: persons) {
      if (person.birth < minYear) minYear = person.birth;
      if (person.death > maxYear) maxYear = person.death;
    }

    var deltas = new int[maxYear - minYear + 1];
    for (Person person: persons) {
      deltas[person.birth - minYear]++;
      deltas[person.death - minYear]--;
    }

    var livingPeople = new int[deltas.length];
    var count = 0;
    for (var i = 0; i < deltas.length; i++) {
      count+= deltas[i];
      livingPeople[i] = count;
    }

    return new LivingPeople(livingPeople, minYear);
  }
}
