package pl.kkowalewski.springrestfruitshop.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.kkowalewski.springrestfruitshop.model.Category;
import pl.kkowalewski.springrestfruitshop.repository.CategoryRepository;

@Component
public class FruitShopBootstrap implements CommandLineRunner {

    /*------------------------ FIELDS REGION ------------------------*/
    private final CategoryRepository categoryRepository;

    /*------------------------ METHODS REGION ------------------------*/
    public FruitShopBootstrap(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    private Category prepareCategory(String name) {
        Category category = new Category(name);
        categoryRepository.save(category);

        return category;
    }

    @Override
    public void run(String... args) {
        Category fruits = prepareCategory("Fruits");
        Category dried = prepareCategory("Dried");
        Category fresh = prepareCategory("Fresh");
        Category exotic = prepareCategory("Exotic");

        System.out.println("Data Loaded = " + categoryRepository.count());
    }
}
