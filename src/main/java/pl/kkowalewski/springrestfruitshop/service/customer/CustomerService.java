package pl.kkowalewski.springrestfruitshop.service.customer;

import pl.kkowalewski.springrestfruitshop.api.v1.model.customer.CustomerDto;

import java.util.List;

public interface CustomerService {

    List<CustomerDto> getAllCustomers();

    CustomerDto getCustomerById(Long id);

    CustomerDto createNewCustomer(CustomerDto customerDto);

    CustomerDto saveCustomerByDto(Long id, CustomerDto customerDto);

    CustomerDto patchCustomer(Long id, CustomerDto customerDto);

    void deleteCustomerById(Long id);
}
