package pl.kkowalewski.springrestfruitshop.service.customer;

import org.springframework.stereotype.Service;
import pl.kkowalewski.springrestfruitshop.AppConstant;
import pl.kkowalewski.springrestfruitshop.api.ver1.mapper.CustomerMapper;
import pl.kkowalewski.springrestfruitshop.api.ver1.model.customer.CustomerDto;
import pl.kkowalewski.springrestfruitshop.model.Customer;
import pl.kkowalewski.springrestfruitshop.repository.CustomerRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    /*------------------------ FIELDS REGION ------------------------*/
    private CustomerMapper customerMapper;
    private CustomerRepository customerRepository;

    /*------------------------ METHODS REGION ------------------------*/
    public CustomerServiceImpl(CustomerMapper customerMapper,
                               CustomerRepository customerRepository) {
        this.customerMapper = customerMapper;
        this.customerRepository = customerRepository;
    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        return customerRepository
                .findAll()
                .stream()
                .map(customer -> {
                    CustomerDto customerDto = customerMapper.customerToCustomerDto(customer);
                    customerDto.setCustomerUrl(AppConstant.CUSTOMERS_ROOT_PATH + customer.getId());
                    return customerDto;
                }).collect(Collectors.toList());
    }

    @Override
    public CustomerDto getCustomerById(Long id) {
        return customerRepository.findById(id)
                .map(customerMapper::customerToCustomerDto)
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public CustomerDto createNewCustomer(CustomerDto customerDto) {
        Customer customer = customerRepository.save(customerMapper
                .customerDtoToCustomer(customerDto));
        CustomerDto returnedCustomerDto = customerMapper
                .customerToCustomerDto(customer);

        returnedCustomerDto.setCustomerUrl(AppConstant.CUSTOMERS_ROOT_PATH + customer.getId());

        return returnedCustomerDto;
    }
}
