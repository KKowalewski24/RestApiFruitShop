package pl.kkowalewski.springrestfruitshop.controller.ver1;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.kkowalewski.springrestfruitshop.AppConstant;
import pl.kkowalewski.springrestfruitshop.api.ver1.model.customer.CustomerDto;
import pl.kkowalewski.springrestfruitshop.service.customer.CustomerService;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class CustomerControllerTest {

    /*------------------------ FIELDS REGION ------------------------*/
    private static final String CUSTOMER_FIRST_NAME_ONE = "ABC";
    private static final String CUSTOMER_FIRST_NAME_TWO = "XYZ";
    private static final String CUSTOMER_LAST_NAME_ONE = "CDE";
    private static final String CUSTOMER_LAST_NAME_TWO = "GEF";
    private static final String CUSTOMER_URL_ONE = AppConstant.CUSTOMERS_ROOT_PATH + 1;
    private static final String CUSTOMER_URL_TWO = AppConstant.CUSTOMERS_ROOT_PATH + 2;

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    private MockMvc mockMvc;

    /*------------------------ METHODS REGION ------------------------*/
    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    private List<CustomerDto> prepareCustomerDtoList(CustomerDto... customerDtos) {
        List<CustomerDto> customerDtoList = new ArrayList<>();

        for (CustomerDto it : customerDtos) {
            customerDtoList.add(it);
        }

        return customerDtoList;
    }

    @Test
    public void getListOfCustomersTest() throws Exception {
        when(customerService.getAllCustomers())
                .thenReturn(prepareCustomerDtoList(
                        new CustomerDto(CUSTOMER_FIRST_NAME_ONE,
                                CUSTOMER_LAST_NAME_ONE, CUSTOMER_URL_ONE),
                        new CustomerDto(CUSTOMER_FIRST_NAME_TWO,
                                CUSTOMER_LAST_NAME_TWO, CUSTOMER_URL_TWO)
                ));

        mockMvc.perform(get(AppConstant.CUSTOMERS_ROOT_PATH)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerDtoList", hasSize(2)));
    }

    @Test
    public void getCustomerByIdTest() throws Exception {
        when(customerService.getCustomerById(anyLong()))
                .thenReturn(new CustomerDto(CUSTOMER_FIRST_NAME_ONE,
                        CUSTOMER_LAST_NAME_ONE, CUSTOMER_URL_ONE));

        mockMvc.perform(get(CUSTOMER_URL_ONE)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName",
                        equalTo(CUSTOMER_FIRST_NAME_ONE)));
    }
}
    