package pl.kkowalewski.springrestfruitshop.service.customer;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.kkowalewski.springrestfruitshop.api.ver1.mapper.CustomerMapper;
import pl.kkowalewski.springrestfruitshop.api.ver1.model.customer.CustomerDto;
import pl.kkowalewski.springrestfruitshop.bootstrap.FruitShopBootstrap;
import pl.kkowalewski.springrestfruitshop.model.Customer;
import pl.kkowalewski.springrestfruitshop.repository.CategoryRepository;
import pl.kkowalewski.springrestfruitshop.repository.CustomerRepository;
import pl.kkowalewski.springrestfruitshop.repository.VendorRepository;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerServiceImplIntegrationTest {

    /*------------------------ FIELDS REGION ------------------------*/
    private static final String NAME = "ABC";

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private VendorRepository vendorRepository;

    private CustomerService customerService;

    /*------------------------ METHODS REGION ------------------------*/
    @Before
    public void setUp() {
        customerService = new CustomerServiceImpl(
                CustomerMapper.INSTANCE, customerRepository);
        new FruitShopBootstrap(categoryRepository, customerRepository, vendorRepository).run();
    }

    @Test
    public void patchUpdateCustomerFirstName() {
        Long id = customerRepository.findAll().get(0).getId();
        Customer customer = customerRepository.getOne(id);
        assertNotNull(customer);

        String firstName = customer.getFirstName();
        String lastName = customer.getLastName();

        CustomerDto customerDto = new CustomerDto(NAME);
        customerService.patchCustomer(id, customerDto);
        Customer customerUpdate = customerRepository.findById(id).get();

        assertNotNull(customerUpdate);
        assertEquals(NAME, customerUpdate.getFirstName());
        assertThat(firstName, not(equalTo(customerUpdate.getFirstName())));
        assertThat(lastName, equalTo(customerUpdate.getLastName()));
    }

    @Test
    public void patchUpdateCustomerLastName() {
        Long id = customerRepository.findAll().get(0).getId();
        Customer customer = customerRepository.getOne(id);
        assertNotNull(customer);

        String firstName = customer.getFirstName();
        String lastName = customer.getLastName();

        CustomerDto customerDto = new CustomerDto();
        customerDto.setLastName(NAME);
        customerService.patchCustomer(id, customerDto);
        Customer customerUpdate = customerRepository.findById(id).get();

        assertNotNull(customerUpdate);
        assertEquals(NAME, customerUpdate.getLastName());
        assertThat(firstName, equalTo(customerUpdate.getFirstName()));
        assertThat(lastName, not(equalTo(customerUpdate.getLastName())));
    }
}
    