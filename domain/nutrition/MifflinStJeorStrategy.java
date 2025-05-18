package patterns.strategy;

import patterns.decorator.Person;

public class MifflinStJeorStrategy implements CalorieCalculationStrategy {
    @Override
    public double calculateCalories(Person person) {
        return (10 * person.getWeight())
                + (6.25 * person.getHeight())
                - (5 * person.getAge()) + 5;
    }
}