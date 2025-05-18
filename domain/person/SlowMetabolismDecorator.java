package patterns.decorator;

public class SlowMetabolismDecorator extends PersonDecorator {
    private double metabolicFactor;

    public SlowMetabolismDecorator(Person person, double metabolicFactor) {
        super(person);
        this.metabolicFactor = metabolicFactor;
    }

    @Override
    public double getMetabolicRate() {
        return metabolicFactor;
    }

    @Override
    public String toString() {
        return person.toString() + String.format(", метаболизм: x%.1f", metabolicFactor);
    }
}