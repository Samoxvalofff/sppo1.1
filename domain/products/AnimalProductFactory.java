package patterns.factory;

public class AnimalProductFactory implements ProductFactory {
    @Override
    public Product createProduct(String name, double calories, double protein, double fat, double carbs) {
        return new AnimalProduct(name, calories, protein, fat, carbs);
    }
}