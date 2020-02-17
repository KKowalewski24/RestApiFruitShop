package pl.kkowalewski.springrestfruitshop.controller.v1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.kkowalewski.springrestfruitshop.api.v1.model.customer.CustomerDto;
import pl.kkowalewski.springrestfruitshop.controller.RestResponseEntityExceptionHandler;
import pl.kkowalewski.springrestfruitshop.exception.ResourceNotFoundException;
import pl.kkowalewski.springrestfruitshop.service.customer.CustomerService;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pl.kkowalewski.springrestfruitshop.constant.AppConstants.CUSTOMERS_ROOT_PATH;
import static pl.kkowalewski.springrestfruitshop.constant.AppConstants.SLASH;

@ExtendWith(MockitoExtension.class)
public class CustomerControllerTest extends AbstractRestControllerTest {

    /*------------------------ FIELDS REGION ------------------------*/
    private static final String CUSTOMER_FIRST_NAME_ONE = "ABC";
    private static final String CUSTOMER_FIRST_NAME_TWO = "XYZ";
    private static final String CUSTOMER_LAST_NAME_ONE = "CDE";
    private static final String CUSTOMER_LAST_NAME_TWO = "GEF";
    private static final String CUSTOMER_URL_ONE = CUSTOMERS_ROOT_PATH + SLASH + 1;
    private static final String CUSTOMER_URL_TWO = CUSTOMERS_ROOT_PATH + SLASH + 2;

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    private MockMvc mockMvc;

    /*------------------------ METHODS REGION ------------------------*/
    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(customerController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();
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

        mockMvc.perform(get(CUSTOMERS_ROOT_PATH)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerDtoList", hasSize(2)));
    }

    @Test
    public void getCustomerByIdTest() throws Exception {
        when(customerService.getCustomerById(anyLong()))
                .thenReturn(new CustomerDto(CUSTOMER_FIRST_NAME_ONE,
                        CUSTOMER_LAST_NAME_ONE, CUSTOMER_URL_ONE));

        mockMvc.perform(get(CUSTOMER_URL_ONE)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName",
                        equalTo(CUSTOMER_FIRST_NAME_ONE)));
    }

    @Test
    public void getCustomerByIdNotFoundTest() throws Exception {
        when(customerService.getCustomerById(anyLong()))
                .thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(get(CUSTOMERS_ROOT_PATH + SLASH + "-1024")
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isNotFound());
    }

    @Test
    public void createNewCustomerTest() throws Exception {
        CustomerDto customerDto = new CustomerDto(CUSTOMER_FIRST_NAME_ONE, CUSTOMER_LAST_NAME_ONE);
        CustomerDto customerDtoNew = new CustomerDto(customerDto.getFirstName(),
                customerDto.getLastName(), CUSTOMER_URL_ONE);

        when(customerService.createNewCustomer(customerDto)).thenReturn(customerDtoNew);

        mockMvc.perform(post(CUSTOMERS_ROOT_PATH)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customerDto))
        )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName", equalTo(CUSTOMER_FIRST_NAME_ONE)))
                .andExpect(jsonPath("$.customer_url", equalTo(CUSTOMER_URL_ONE)));
    }

    @Test
    public void updateCustomerTest() throws Exception {
        CustomerDto customerDto = new CustomerDto(CUSTOMER_FIRST_NAME_ONE, CUSTOMER_LAST_NAME_ONE);
        CustomerDto customerDtoNew = new CustomerDto(customerDto.getFirstName(),
                customerDto.getLastName(), CUSTOMER_URL_ONE);

        when(customerService.saveCustomerByDto(anyLong(), any(CustomerDto.class)))
                .thenReturn(customerDtoNew);

        mockMvc.perform(put(CUSTOMER_URL_ONE)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customerDto))
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo(CUSTOMER_FIRST_NAME_ONE)))
                .andExpect(jsonPath("$.lastName", equalTo(CUSTOMER_LAST_NAME_ONE)))
                .andExpect(jsonPath("$.customer_url", equalTo(CUSTOMER_URL_ONE)));
    }

    @Test
    public void patchCustomerTest() throws Exception {
        CustomerDto customerDto = new CustomerDto(CUSTOMER_FIRST_NAME_ONE);
        CustomerDto customerDtoNew = new CustomerDto(customerDto.getFirstName(),
                CUSTOMER_LAST_NAME_ONE, CUSTOMER_URL_ONE);

        when(customerService.patchCustomer(anyLong(), any(CustomerDto.class)))
                .thenReturn(customerDtoNew);

        mockMvc.perform(patch(CUSTOMER_URL_ONE)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customerDto))
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo(CUSTOMER_FIRST_NAME_ONE)));
    }

    @Test
    public void deleteCustomerTest() throws Exception {
        mockMvc.perform(delete(CUSTOMER_URL_ONE)
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk());

        verify(customerService).deleteCustomerById(anyLong());
    }
}
    