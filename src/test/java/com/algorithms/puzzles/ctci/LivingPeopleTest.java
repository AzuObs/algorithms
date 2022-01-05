package com.algorithms.puzzles.ctci;

import org.junit.Test;
import com.algorithms.puzzles.ctci.LivingPeople.Person;
import com.algorithms.puzzles.ctci.LivingPeople.Person.ImpossibleAgeException;

public class LivingPeopleTest {

  @Test
  public void findTheLivingPeopleInAGivenYear() throws ImpossibleAgeException {
    var persons = new Person[] {
        Person.create(1901, 1902),
        Person.create(1910, 1930),
        Person.create(1901, 1940),
        Person.create(1911, 1920),
        Person.create(1921, 1930),
        Person.create(1941, 1950),
        Person.create(1921, 1962)
    };
    var livingPeople = LivingPeople.create(persons);
    assert livingPeople.getCount(1901) == 2;
    assert livingPeople.getCount(1902) == 1;
    assert livingPeople.getCount(1910) == 2;
    assert livingPeople.getCount(1911) == 3;
    assert livingPeople.getCount(1920) == 2;
    assert livingPeople.getCount(1921) == 4;
  }

}
