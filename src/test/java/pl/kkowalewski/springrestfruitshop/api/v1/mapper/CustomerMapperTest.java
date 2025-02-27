package pl.kkowalewski.springrestfruitshop.api.v1.mapper;

import org.junit.jupiter.api.Test;
import pl.kkowalewski.springrestfruitshop.api.v1.model.customer.CustomerDto;
import pl.kkowalewski.springrestfruitshop.model.Customer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CustomerMapperTest {

    /*------------------------ FIELDS REGION ------------------------*/
    private static final Long ID = 1L;
    private static final String FIRST_NAME = "ABC";
    private static final String LAST_NAME = "CDE";

    private CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    /*------------------------ METHODS REGION ------------------------*/
    @Test
    public void customerToCustomerDtoTest() {
        CustomerDto customerDto = customerMapper
                .customerToCustomerDto(new Customer(ID, FIRST_NAME, LAST_NAME));

        assertEquals(FIRST_NAME, customerDto.getFirstName());
        assertEquals(LAST_NAME, customerDto.getLastName());
    }

    @Test
    public void customerToCustomerDtoNullTest() {
        CustomerDto customerDto = customerMapper.customerToCustomerDto(null);

        assertNull(customerDto);
    }

    @Test
    public void customerDtoToCustomerTest() {
        Customer customer = customerMapper
                .customerDtoToCustomer(new CustomerDto(FIRST_NAME, LAST_NAME));

        assertEquals(FIRST_NAME, customer.getFirstName());
        assertEquals(LAST_NAME, customer.getLastName());
    }

    @Test
    public void customerDtoToCustomerNullTest() {
        Customer customer = customerMapper.customerDtoToCustomer(null);

        assertNull(customer);
    }
}
    