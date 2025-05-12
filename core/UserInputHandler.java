package core;

import patterns.builder.DietPlan;
import patterns.composite.CompositeDietCycle;
import patterns.decorator.Person;
import patterns.decorator.SlowMetabolismDecorator;
import patterns.factory.Product;
import patterns.observer.LoggingObserver;
import patterns.observer.NutritionTrackerObserver;
import patterns.observer.ObservableDietPlan;
import patterns.strategy.CalorieCalculator;
import patterns.strategy.HarrisBenedictStrategy;
import patterns.strategy.MifflinStJeorStrategy;

import java.util.List;

public class UserInputHandler {
    private final UserInputService inputService;
    private final ProductSelectionService productSelectionService;
    private final DietCycleService dietCycleService;
    private final FileStorageService fileStorageService;

    public UserInputHandler() {
        this.inputService = new UserInputService();
        this.productSelectionService = new ProductSelectionService();
        this.dietCycleService = new DietCycleService();
        this.fileStorageService = new FileStorageService();
    }

    public void start() {
        System.out.println("=== Конструктор рациона питания ===");

        while (true) {
            try {
                String name = inputService.getValidInput(
                        "Введите имя (или 'exit' для выхода):",
                        input -> !input.isEmpty(),
                        "Имя не может быть пустым");

                if (name.equalsIgnoreCase("exit")) break;

                double weight = inputService.readValidDouble("Вес (кг):", 30, 120);
                double height = inputService.readValidDouble("Рост (см):", 100, 250);
                int age = inputService.readValidInt("Возраст:", 1, 120);
                double metabolism = inputService.readValidDouble("Коэффициент метаболизма (0.8-1.2):", 0.8, 1.2);

                List<Product> selectedProducts = productSelectionService.selectProducts();

                DietPlan.Builder builder = new DietPlan.Builder();
                for (Product product : selectedProducts) {
                    builder.addProduct(product);
                }
                DietPlan plan = builder.setDuration(7).build();

                ObservableDietPlan observablePlan = ObservableDietPlan.createFrom(plan);
                observablePlan.addObserver(new LoggingObserver());
                observablePlan.addObserver(new NutritionTrackerObserver());

                Person person = new Person(weight, height, age);
                person = new SlowMetabolismDecorator(person, metabolism);

                CalorieCalculator calculator = new CalorieCalculator(new HarrisBenedictStrategy());
                double harrisCalories = calculator.calculate(person);
                calculator.setStrategy(new MifflinStJeorStrategy());
                double mifflinCalories = calculator.calculate(person);

                CompositeDietCycle fullCycle = dietCycleService.createDietCycle(productSelectionService.AVAILABLE_PRODUCTS);

                fileStorageService.saveFullData(name, weight, height, age, metabolism,
                        selectedProducts, plan, person,
                        harrisCalories, mifflinCalories, fullCycle);

                System.out.println("\n✓ Все данные сохранены в nutrition_diary.txt");

            } catch (Exception e) {
                System.out.println("⚠ Ошибка: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new UserInputHandler().start();
    }
}