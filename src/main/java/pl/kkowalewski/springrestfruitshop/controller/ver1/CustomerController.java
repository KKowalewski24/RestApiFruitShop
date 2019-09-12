package pl.kkowalewski.springrestfruitshop.controller.ver1;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.kkowalewski.springrestfruitshop.AppConstant;
import pl.kkowalewski.springrestfruitshop.api.ver1.model.customer.CustomerDto;
import pl.kkowalewski.springrestfruitshop.api.ver1.model.customer.CustomerListDto;
import pl.kkowalewski.springrestfruitshop.service.customer.CustomerService;

@RestController
@RequestMapping(AppConstant.CUSTOMERS_ROOT_PATH)
public class CustomerController {

    /*------------------------ FIELDS REGION ------------------------*/
    private final CustomerService customerService;

    /*------------------------ METHODS REGION ------------------------*/
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping({AppConstant.ROOT, AppConstant.SLASH})
    @ResponseStatus(HttpStatus.OK)
    public CustomerListDto getListOfCustomers() {
        return new CustomerListDto(customerService.getAllCustomers());
    }

    @GetMapping(AppConstant.SLASH + AppConstant.ID)
    @ResponseStatus(HttpStatus.OK)
    public CustomerDto getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }

    @PostMapping({AppConstant.ROOT, AppConstant.SLASH})
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDto createNewCustomer(@RequestBody CustomerDto customerDto) {
        return customerService.createNewCustomer(customerDto);
    }

    @PutMapping(AppConstant.SLASH + AppConstant.ID)
    @ResponseStatus(HttpStatus.OK)
    public CustomerDto updateCustomer(@PathVariable Long id,
                                      @RequestBody CustomerDto customerDto) {
        return customerService.saveCustomerByDto(id, customerDto);
    }

    @PatchMapping(AppConstant.SLASH + AppConstant.ID)
    @ResponseStatus(HttpStatus.OK)
    public CustomerDto patchCustomer(@PathVariable Long id,
                                     @RequestBody CustomerDto customerDto) {
        return customerService.patchCustomer(id, customerDto);
    }

    @DeleteMapping(AppConstant.SLASH + AppConstant.ID)
    @ResponseStatus(HttpStatus.OK)
    public void deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomerById(id);
    }
}
