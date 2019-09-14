package pl.kkowalewski.springrestfruitshop.controller.ver1;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

@Api(description = "Customer API")
@RestController
@RequestMapping(CUSTOMERS_ROOT_PATH)
public class CustomerController {

    /*------------------------ FIELDS REGION ------------------------*/
    private final CustomerService customerService;

    /*------------------------ METHODS REGION ------------------------*/
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @ApiOperation(value = "View List of Customers")
    @GetMapping({ROOT, SLASH})
    @ResponseStatus(HttpStatus.OK)
    public CustomerListDto getListOfCustomers() {
        return new CustomerListDto(customerService.getAllCustomers());
    }

    @ApiOperation(value = "Get Customer by ID")
    @GetMapping(SLASH + ID)
    @ResponseStatus(HttpStatus.OK)
    public CustomerDto getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }

    @ApiOperation(value = "Create a new Customer")
    @PostMapping({ROOT, SLASH})
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDto createNewCustomer(@RequestBody CustomerDto customerDto) {
        return customerService.createNewCustomer(customerDto);
    }

    @ApiOperation(value = "Update an existing Customer")
    @PutMapping(SLASH + ID)
    @ResponseStatus(HttpStatus.OK)
    public CustomerDto updateCustomer(@PathVariable Long id, @RequestBody CustomerDto customerDto) {
        return customerService.saveCustomerByDto(id, customerDto);
    }

    @ApiOperation(value = "Update a Customer Property")
    @PatchMapping(SLASH + ID)
    @ResponseStatus(HttpStatus.OK)
    public CustomerDto patchCustomer(@PathVariable Long id, @RequestBody CustomerDto customerDto) {
        return customerService.patchCustomer(id, customerDto);
    }

    @ApiOperation(value = "Delete a Customer")
    @DeleteMapping(SLASH + ID)
    @ResponseStatus(HttpStatus.OK)
    public void deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomerById(id);
    }
}
