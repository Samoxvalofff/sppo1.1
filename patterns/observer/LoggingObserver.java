package patterns.observer;

import patterns.factory.Product;

public class LoggingObserver implements DietPlanObserver {
    @Override
    public void onProductAdded(Product product) {
        System.out.printf("[OBSERVER] Добавлен продукт: %s (%.1f ккал)\n",
                product.getName(), product.getCalories());
    }
}