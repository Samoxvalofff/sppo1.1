package patterns.builder;

import patterns.factory.Product;
import java.util.ArrayList;
import java.util.List;

public class DietPlan {
    private List<Product> products;
    private int durationDays;

    protected DietPlan(List<Product> products, int durationDays) {
        this.products = new ArrayList<>(products);
        this.durationDays = durationDays;
    }

    public List<Product> getProducts() {
        return new ArrayList<>(products);
    }

    public int getDurationDays() {
        return durationDays;
    }

    public String getTotalNutrition() {
        double totalCalories = 0, totalProtein = 0, totalFat = 0, totalCarbs = 0;
        for (Product p : products) {
            totalCalories += p.getCalories();
            totalProtein += p.getProtein();
            totalFat += p.getFat();
            totalCarbs += p.getCarbs();
        }
        return String.format("Итого за день: %.1f ккал, Б: %.1fг, Ж: %.1fг, У: %.1fг",
                totalCalories, totalProtein, totalFat, totalCarbs);
    }

    public static class Builder {
        private final List<Product> products = new ArrayList<>();
        private int durationDays = 7;

        public Builder addProduct(Product product) {
            this.products.add(product);
            return this;
        }

        public Builder setDuration(int durationDays) {
            this.durationDays = durationDays;
            return this;
        }

        public DietPlan build() {
            if (products.isEmpty()) {
                throw new IllegalStateException("Рацион не может быть пустым!");
            }
            return new DietPlan(products, durationDays);
        }
    }
}