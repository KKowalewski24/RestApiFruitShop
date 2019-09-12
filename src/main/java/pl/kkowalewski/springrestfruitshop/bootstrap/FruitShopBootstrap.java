package pl.kkowalewski.springrestfruitshop.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.kkowalewski.springrestfruitshop.model.Category;
import pl.kkowalewski.springrestfruitshop.model.Customer;
import pl.kkowalewski.springrestfruitshop.model.Vendor;
import pl.kkowalewski.springrestfruitshop.repository.CategoryRepository;
import pl.kkowalewski.springrestfruitshop.repository.CustomerRepository;
import pl.kkowalewski.springrestfruitshop.repository.VendorRepository;

@Component
public class FruitShopBootstrap implements CommandLineRunner {

    /*------------------------ FIELDS REGION ------------------------*/
    public static final Long ID_ONE = 1L;
    public static final Long ID_TWO = 2L;
    public static final String CUSTOMER_FIRST_NAME_ONE = "Michale";
    public static final String CUSTOMER_FIRST_NAME_TWO = "Sam";
    public static final String CUSTOMER_LAST_NAME_ONE = "Weston";
    public static final String CUSTOMER_LAST_NAME_TWO = "Axe";

    private final CategoryRepository categoryRepository;
    private final CustomerRepository customerRepository;
    private final VendorRepository vendorRepository;

    /*------------------------ METHODS REGION ------------------------*/
    public FruitShopBootstrap(CategoryRepository categoryRepository,
                              CustomerRepository customerRepository,
                              VendorRepository vendorRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
        this.vendorRepository = vendorRepository;
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

    private Vendor prepareVendor(Long id, String name) {
        Vendor vendor = new Vendor(id, name);
        vendorRepository.save(vendor);

        return vendor;
    }

    @Override
    public void run(String... args) {
        Category fruits = prepareCategory("Fruits");
        Category dried = prepareCategory("Dried");
        Category fresh = prepareCategory("Fresh");
        Category exotic = prepareCategory("Exotic");

        Customer customer1 = prepareCustomer(ID_ONE,
                CUSTOMER_FIRST_NAME_ONE, CUSTOMER_LAST_NAME_ONE);
        Customer customer2 = prepareCustomer(ID_TWO,
                CUSTOMER_FIRST_NAME_TWO, CUSTOMER_LAST_NAME_TWO);

        Vendor vendor1 = prepareVendor(ID_ONE, "one");
        Vendor vendor2 = prepareVendor(ID_TWO, "two");

        System.out.println("Categories Loaded: " + categoryRepository.count());
        System.out.println("Customers Loaded: " + customerRepository.count());
        System.out.println("Vendors Loaded: " + vendorRepository.count());
    }
}
