package pl.kkowalewski.springrestfruitshop.service.customer;

import org.springframework.stereotype.Service;
import pl.kkowalewski.springrestfruitshop.api.ver1.mapper.CustomerMapper;
import pl.kkowalewski.springrestfruitshop.api.ver1.model.customer.CustomerDto;
import pl.kkowalewski.springrestfruitshop.exception.ResourceNotFoundException;
import pl.kkowalewski.springrestfruitshop.model.Customer;
import pl.kkowalewski.springrestfruitshop.repository.CustomerRepository;

import java.util.List;
import java.util.stream.Collectors;

import static pl.kkowalewski.springrestfruitshop.constant.AppConstants.CUSTOMERS_ROOT_PATH;
import static pl.kkowalewski.springrestfruitshop.constant.AppConstants.SLASH;

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

    private CustomerDto prepareCustomerDto(Customer customer) {
        Customer customerSave = customerRepository.save(customer);
        CustomerDto customerDto = customerMapper.customerToCustomerDto(customerSave);
        customerDto.setCustomerUrl(CUSTOMERS_ROOT_PATH + SLASH + customerSave.getId());

        return customerDto;
    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        return customerRepository
                .findAll()
                .stream()
                .map(customer -> {
                    CustomerDto customerDto = customerMapper.customerToCustomerDto(customer);
                    customerDto.setCustomerUrl(CUSTOMERS_ROOT_PATH + customer.getId());
                    return customerDto;
                }).collect(Collectors.toList());
    }

    @Override
    public CustomerDto getCustomerById(Long id) {
        return customerRepository.findById(id)
                .map(customerMapper::customerToCustomerDto)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public CustomerDto createNewCustomer(CustomerDto customerDto) {
        return prepareCustomerDto(customerMapper.customerDtoToCustomer(customerDto));
    }

    @Override
    public CustomerDto saveCustomerByDto(Long id, CustomerDto customerDto) {
        Customer customer = customerMapper.customerDtoToCustomer(customerDto);
        customer.setId(id);

        return prepareCustomerDto(customer);
    }

    @Override
    public CustomerDto patchCustomer(Long id, CustomerDto customerDto) {
        return customerRepository.findById(id).map(customer -> {
            if (customerDto.getFirstName() != null) {
                customer.setFirstName(customerDto.getFirstName());
            } else if (customerDto.getLastName() != null) {
                customer.setLastName(customerDto.getLastName());
            }

            CustomerDto customerDtoSave = customerMapper
                    .customerToCustomerDto(customerRepository.save(customer));
            customerDtoSave.setCustomerUrl(CUSTOMERS_ROOT_PATH + SLASH + id);

            return customerDtoSave;
        }).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void deleteCustomerById(Long id) {
        customerRepository.deleteById(id);
    }
}
