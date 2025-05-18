package patterns.factory;

public abstract class Product {
    private String name;
    private double calories;
    private double protein;
    private double fat;
    private double carbs;

    public Product(String name, double calories, double protein, double fat, double carbs) {
        this.name = name;
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.carbs = carbs;
    }

    // Геттеры
    public String getName() { return name; }
    public double getCalories() { return calories; }
    public double getProtein() { return protein; }
    public double getFat() { return fat; }
    public double getCarbs() { return carbs; }

    @Override
    public String toString() {
        return String.format("%s: %.1f ккал, Б: %.1fг, Ж: %.1fг, У: %.1fг",
                name, calories, protein, fat, carbs);
    }
}