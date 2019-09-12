package pl.kkowalewski.springrestfruitshop.api.ver1.mapper;

import org.junit.Test;
import pl.kkowalewski.springrestfruitshop.api.ver1.model.customer.CustomerDto;
import pl.kkowalewski.springrestfruitshop.model.Customer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class CustomerMapperTest {

    /*------------------------ FIELDS REGION ------------------------*/
    private static final Long ID = 1L;
    private static final String FIRST_NAME = "ABC";
    private static final String LAST_NAME = "CDE";

    private CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    /*------------------------ METHODS REGION ------------------------*/
    @Test
    public void customerToCustomerDTOTest() {
        CustomerDto customerDto = customerMapper
                .customerToCustomerDto(new Customer(ID, FIRST_NAME, LAST_NAME));

        assertEquals(FIRST_NAME, customerDto.getFirstName());
        assertEquals(LAST_NAME, customerDto.getLastName());
    }

    @Test
    public void customerToCustomerDTONullTest() {
        CustomerDto customerDto = customerMapper.customerToCustomerDto(null);

        assertNull(customerDto);
    }
}
    