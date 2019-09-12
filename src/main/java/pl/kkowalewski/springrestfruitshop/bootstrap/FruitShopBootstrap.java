package pl.kkowalewski.springrestfruitshop.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.kkowalewski.springrestfruitshop.model.Category;
import pl.kkowalewski.springrestfruitshop.model.Customer;
import pl.kkowalewski.springrestfruitshop.repository.CategoryRepository;
import pl.kkowalewski.springrestfruitshop.repository.CustomerRepository;

@Component
public class FruitShopBootstrap implements CommandLineRunner {

    /*------------------------ FIELDS REGION ------------------------*/
    public static final Long CUSTOMER_ID_ONE = 1L;
    public static final Long CUSTOMER_ID_TWO = 2L;
    public static final String CUSTOMER_FIRST_NAME_ONE = "Michale";
    public static final String CUSTOMER_FIRST_NAME_TWO = "Sam";
    public static final String CUSTOMER_LAST_NAME_ONE = "Weston";
    public static final String CUSTOMER_LAST_NAME_TWO = "Axe";

    private final CategoryRepository categoryRepository;
    private final CustomerRepository customerRepository;

    /*------------------------ METHODS REGION ------------------------*/
    public FruitShopBootstrap(CategoryRepository categoryRepository,
                              CustomerRepository customerRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
    }

    private Category prepareCategory(String name) {
        Category category = new Category(name);
        categoryRepository.save(category);

        return category;
    }

    private Customer prepareCustomer(Long id, String firstName, String lastName) {
        Customer customer = new Customer(id, firstName, lastName);
        customerRepository.save(customer);

        return customer;
    }

    @Override
    public void run(String... args) {
        Category fruits = prepareCategory("Fruits");
        Category dried = prepareCategory("Dried");
        Category fresh = prepareCategory("Fresh");
        Category exotic = prepareCategory("Exotic");

        Customer customer1 = prepareCustomer(CUSTOMER_ID_ONE,
                CUSTOMER_FIRST_NAME_ONE, CUSTOMER_LAST_NAME_ONE);
        Customer customer2 = prepareCustomer(CUSTOMER_ID_TWO,
                CUSTOMER_FIRST_NAME_TWO, CUSTOMER_LAST_NAME_TWO);

        System.out.println("Categories Loaded: " + categoryRepository.count());
        System.out.println("Customers Loaded: " + customerRepository.count());
    }
}
