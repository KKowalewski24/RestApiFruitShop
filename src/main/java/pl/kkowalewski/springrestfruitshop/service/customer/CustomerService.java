package pl.kkowalewski.springrestfruitshop.service.customer;

import pl.kkowalewski.springrestfruitshop.api.ver1.model.customer.CustomerDto;

import java.util.List;

public interface CustomerService {

    List<CustomerDto> getAllCustomers();

    CustomerDto getCustomerById(Long id);

    CustomerDto createNewCustomer(CustomerDto customerDto);

    CustomerDto saveCustomerByDto(Long id, CustomerDto customerDto);
}
