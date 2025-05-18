package patterns.observer;

import patterns.factory.Product;

public class NutritionTrackerObserver implements DietPlanObserver {
    private double totalCalories = 0;

    @Override
    public void onProductAdded(Product product) {
        totalCalories += product.getCalories();
        System.out.printf("[TRACKER] Текущая сумма калорий: %.1f\n", totalCalories);
    }
}