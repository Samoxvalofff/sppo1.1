package core;

import patterns.factory.Product;
import patterns.factory.ProductFactory;
import patterns.factory.PlantProductFactory;
import patterns.factory.AnimalProductFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ProductSelectionService {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ProductFactory plantFactory = new PlantProductFactory();
    private static final ProductFactory animalFactory = new AnimalProductFactory();

    protected static final List<Product> AVAILABLE_PRODUCTS = Arrays.asList(
            plantFactory.createProduct("Яблоко", 52, 0.3, 0.2, 14),
            plantFactory.createProduct("Банан", 89, 1.1, 0.3, 22.8),
            plantFactory.createProduct("Овсянка", 68, 2.4, 1.4, 12.0),
            plantFactory.createProduct("Миндаль", 579, 21.2, 49.9, 21.6),
            plantFactory.createProduct("Брокколи", 34, 2.8, 0.4, 6.6),
            animalFactory.createProduct("Курица", 165, 31, 3.6, 0),
            animalFactory.createProduct("Лосось", 208, 20.0, 13.0, 0.0),
            animalFactory.createProduct("Яйца", 143, 12.6, 9.9, 0.7),
            animalFactory.createProduct("Греческий йогурт", 59, 10.0, 0.4, 3.6),
            animalFactory.createProduct("Говядина", 250, 26.0, 15.0, 0.0),
            animalFactory.createProduct("Сыр Чеддер", 402, 25.0, 33.0, 1.3)
    );

    public List<Product> selectProducts() {
        List<Product> selected = new ArrayList<>();
        System.out.println("\nДоступные продукты:");
        for (int i = 0; i < AVAILABLE_PRODUCTS.size(); i++) {
            System.out.printf("%2d. %s\n", i + 1, AVAILABLE_PRODUCTS.get(i));
        }

        while (true) {
            try {
                System.out.print("\nВыберите продукт (1-" + AVAILABLE_PRODUCTS.size() + "), 0 - закончить: ");
                int choice = Integer.parseInt(scanner.nextLine());

                if (choice == 0) break;
                if (choice < 0 || choice > AVAILABLE_PRODUCTS.size()) {
                    System.out.println("Неверный номер продукта");
                    continue;
                }

                Product product = AVAILABLE_PRODUCTS.get(choice - 1);
                selected.add(product);
                System.out.println("Добавлен: " + product.getName());

            } catch (NumberFormatException e) {
                System.out.println("Введите номер продукта");
            }
        }
        return selected;
    }
}