package patterns.observer;

import patterns.factory.Product;

public interface DietPlanObserver {
    void onProductAdded(Product product);
}