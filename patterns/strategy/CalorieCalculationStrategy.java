package patterns.strategy;

import patterns.decorator.Person;

public interface CalorieCalculationStrategy {
    double calculateCalories(Person person);
}