package pl.kkowalewski.springrestfruitshop.controller.ver1;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.kkowalewski.springrestfruitshop.api.ver1.model.customer.CustomerDto;
import pl.kkowalewski.springrestfruitshop.api.ver1.model.customer.CustomerListDto;
import pl.kkowalewski.springrestfruitshop.service.customer.CustomerService;

import static pl.kkowalewski.springrestfruitshop.constant.AppConstants.CUSTOMERS_ROOT_PATH;
import static pl.kkowalewski.springrestfruitshop.constant.AppConstants.ID;
import static pl.kkowalewski.springrestfruitshop.constant.AppConstants.ROOT;
import static pl.kkowalewski.springrestfruitshop.constant.AppConstants.SLASH;

@RestController
@RequestMapping(CUSTOMERS_ROOT_PATH)
public class CustomerController {

    /*------------------------ FIELDS REGION ------------------------*/
    private final CustomerService customerService;

    /*------------------------ METHODS REGION ------------------------*/
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping({ROOT, SLASH})
    @ResponseStatus(HttpStatus.OK)
    public CustomerListDto getListOfCustomers() {
        return new CustomerListDto(customerService.getAllCustomers());
    }

    @GetMapping(SLASH + ID)
    @ResponseStatus(HttpStatus.OK)
    public CustomerDto getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }

    @PostMapping({ROOT, SLASH})
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDto createNewCustomer(@RequestBody CustomerDto customerDto) {
        return customerService.createNewCustomer(customerDto);
    }

    @PutMapping(SLASH + ID)
    @ResponseStatus(HttpStatus.OK)
    public CustomerDto updateCustomer(@PathVariable Long id, @RequestBody CustomerDto customerDto) {
        return customerService.saveCustomerByDto(id, customerDto);
    }

    @PatchMapping(SLASH + ID)
    @ResponseStatus(HttpStatus.OK)
    public CustomerDto patchCustomer(@PathVariable Long id, @RequestBody CustomerDto customerDto) {
        return customerService.patchCustomer(id, customerDto);
    }

    @DeleteMapping(SLASH + ID)
    @ResponseStatus(HttpStatus.OK)
    public void deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomerById(id);
    }
}
