package patterns.composite;

public class SingleDietCycle implements DietCycle {
    private String name;
    private int durationDays;

    public SingleDietCycle(String name, int durationDays) {
        this.name = name;
        this.durationDays = durationDays;
    }

    @Override
    public String getDescription() {
        return name + " (" + durationDays + " дней)";
    }

    @Override
    public int getDuration() {
        return durationDays;
    }
}