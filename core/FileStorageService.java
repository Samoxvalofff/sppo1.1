package core;

import patterns.builder.DietPlan;
import patterns.composite.CompositeDietCycle;
import patterns.decorator.Person;
import patterns.factory.Product;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileStorageService {
    private static final String FILE_PATH = "nutrition_diary.txt";
    private static final String SEPARATOR = "=".repeat(50);

    public void saveFullData(String name, double weight, double height, int age,
                             double metabolism, List<Product> products,
                             DietPlan plan, Person person,
                             double harrisCalories, double mifflinCalories,
                             CompositeDietCycle cycle) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(SEPARATOR + "\n");
            writer.write(name + "\n");

            for (Product p : products) {
                writer.write(String.format("%s: %.1f ккал, Б: %.1fг, Ж: %.1fг, У: %.1fг\n",
                        p.getName(), p.getCalories(), p.getProtein(), p.getFat(), p.getCarbs()));
            }

            writer.write(String.format("%.0f %.0f %d %.1f\n", weight, height, age, metabolism));

            writer.write("=== Сформированный рацион ===\n");
            writer.write(plan.getTotalNutrition() + "\n");

            writer.write("=== Характеристики человека ===\n");
            writer.write(person.toString() + "\n");

            writer.write("=== Расчёт калорий (Харрис-Бенедикт) ===\n");
            writer.write(String.format("Норма: %.1f ккал/день\n", harrisCalories));
            writer.write("\n=== Расчёт калорий (Миффлин-Сан Жеор) ===\n");
            writer.write(String.format("Норма: %.1f ккал/день\n", mifflinCalories));

            double calories = mifflinCalories;
            double protein = calories * 0.13 / 4;
            double fat = calories * 0.30 / 9;
            double carbs = calories * 0.57 / 4;

            writer.write("\n=== Рекомендуемое распределение БЖУ ===\n");
            writer.write(String.format("Белки: %.1fг (13%% от калорий)\n", protein));
            writer.write(String.format("Жиры: %.1fг (30%% от калорий)\n", fat));
            writer.write(String.format("Углеводы: %.1fг (57%% от калорий)\n", carbs));

            writer.write("\n=== Рекомендуемые циклы питания ===\n");
            writer.write(cycle.getDescription() + "\n");
            writer.write("Общая длительность: " + cycle.getDuration() + " дней\n");
        }
    }
}