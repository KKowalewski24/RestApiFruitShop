package pl.kkowalewski.springrestfruitshop.service.customer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.kkowalewski.springrestfruitshop.api.v1.mapper.CustomerMapper;
import pl.kkowalewski.springrestfruitshop.api.v1.model.customer.CustomerDto;
import pl.kkowalewski.springrestfruitshop.model.Customer;
import pl.kkowalewski.springrestfruitshop.repository.CustomerRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static pl.kkowalewski.springrestfruitshop.constant.AppConstants.CUSTOMERS_ROOT_PATH;
import static pl.kkowalewski.springrestfruitshop.constant.AppConstants.SLASH;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceImplTest {

    /*------------------------ FIELDS REGION ------------------------*/
    private static final Long CUSTOMER_ID = 1L;
    private static final String CUSTOMER_FIRST_NAME = "ABC";
    private static final String CUSTOMER_LAST_NAME = "CDE";

    @Mock
    private CustomerRepository customerRepository;

    private CustomerService customerService;

    /*------------------------ METHODS REGION ------------------------*/
    @BeforeEach
    public void setUp() {
        customerService = new CustomerServiceImpl(CustomerMapper.INSTANCE, customerRepository);
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

    @Test
    public void createNewCustomerTest() {
        CustomerDto customerDto = new CustomerDto(CUSTOMER_FIRST_NAME, CUSTOMER_LAST_NAME);
        Customer customer = new Customer(CUSTOMER_ID,
                customerDto.getFirstName(), customerDto.getLastName());

        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        CustomerDto savedDto = customerService.createNewCustomer(customerDto);

        assertEquals(customerDto.getFirstName(), savedDto.getFirstName());
        assertEquals(CUSTOMERS_ROOT_PATH + SLASH + CUSTOMER_ID, savedDto.getCustomerUrl());
    }

    @Test
    public void saveCustomerByDtoTest() {
        CustomerDto customerDto = new CustomerDto(CUSTOMER_FIRST_NAME, CUSTOMER_LAST_NAME);
        Customer customer = new Customer(CUSTOMER_ID,
                customerDto.getFirstName(), customerDto.getLastName());

        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        CustomerDto savedDto = customerService.saveCustomerByDto(CUSTOMER_ID, customerDto);

        assertEquals(customerDto.getFirstName(), savedDto.getFirstName());
        assertEquals(CUSTOMERS_ROOT_PATH + SLASH + CUSTOMER_ID, savedDto.getCustomerUrl());
    }

    @Test
    public void deleteCustomerByIdTest() {
        customerRepository.deleteById(CUSTOMER_ID);
        verify(customerRepository).deleteById(anyLong());
    }
}
    