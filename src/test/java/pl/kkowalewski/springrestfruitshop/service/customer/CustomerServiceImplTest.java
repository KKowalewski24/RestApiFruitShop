package pl.kkowalewski.springrestfruitshop.service.customer;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pl.kkowalewski.springrestfruitshop.api.ver1.mapper.CustomerMapper;
import pl.kkowalewski.springrestfruitshop.api.ver1.model.customer.CustomerDto;
import pl.kkowalewski.springrestfruitshop.model.Customer;
import pl.kkowalewski.springrestfruitshop.repository.CustomerRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceImplTest {

    /*------------------------ FIELDS REGION ------------------------*/
    private static final Long CUSTOMER_ID = 1L;
    private static final String CUSTOMER_FIRST_NAME = "ABC";
    private static final String CUSTOMER_LAST_NAME = "CDE";

    @Mock
    private CustomerRepository customerRepository;

    private CustomerService customerService;

    /*------------------------ METHODS REGION ------------------------*/
    @Before
    public void setUp() {
        customerService = new CustomerServiceImpl(
                CustomerMapper.INSTANCE, customerRepository);
    }

    private List<Customer> prepareCustomerList(Customer... customers) {
        List<Customer> customerList = new ArrayList<>();

        for (Customer it : customers) {
            customerList.add(it);
        }

        return customerList;
    }

    @Test
    public void getAllCustomersTest() {
        when(customerRepository.findAll())
                .thenReturn(prepareCustomerList(new Customer(), new Customer()));

        assertEquals(2, customerService.getAllCustomers().size());
    }

    @Test
    public void getCustomerByIdTest() {
        when(customerRepository.findById(anyLong()))
                .thenReturn(Optional.of(new Customer(CUSTOMER_ID,
                        CUSTOMER_FIRST_NAME, CUSTOMER_LAST_NAME)));

        CustomerDto customerDto = customerService.getCustomerById(CUSTOMER_ID);

        assertEquals(CUSTOMER_FIRST_NAME, customerDto.getFirstName());
        assertEquals(CUSTOMER_LAST_NAME, customerDto.getLastName());
    }
}
    