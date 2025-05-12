package core;

import patterns.composite.CompositeDietCycle;
import patterns.composite.DietCycle;
import patterns.composite.SingleDietCycle;
import patterns.factory.Product;

import java.util.ArrayList;
import java.util.List;

public class DietCycleService {
    private static final double CARB_THRESHOLD = 15.0;

    public CompositeDietCycle createDietCycle(List<Product> availableProducts) {
        List<Product> lowCarbProducts = new ArrayList<>();
        List<Product> highCarbProducts = new ArrayList<>();

        for (Product p : availableProducts) {
            if (p.getCarbs() < CARB_THRESHOLD) {
                lowCarbProducts.add(p);
            } else {
                highCarbProducts.add(p);
            }
        }

        DietCycle lowCarbCycle = new SingleDietCycle(
                String.format("Низкоуглеводный (5 дней), необходимо 150г, совет: %s",
                        formatProductList(lowCarbProducts)),
                5);

        DietCycle highCarbCycle = new SingleDietCycle(
                String.format("Высокоуглеводный (2 дня), необходимо 350г, много в: %s",
                        formatProductList(highCarbProducts)),
                2);

        CompositeDietCycle fullCycle = new CompositeDietCycle();
        fullCycle.add(lowCarbCycle);
        fullCycle.add(highCarbCycle);
        return fullCycle;
    }

    private String formatProductList(List<Product> products) {
        StringBuilder sb = new StringBuilder();
        for (Product p : products) {
            sb.append(String.format("%s (%.1fг), ", p.getName(), p.getCarbs()));
        }
        return !sb.isEmpty() ? sb.substring(0, sb.length() - 2) : "";
    }
}