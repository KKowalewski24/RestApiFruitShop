package pl.kkowalewski.springrestfruitshop.controller.ver1;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.kkowalewski.springrestfruitshop.AppConstant;
import pl.kkowalewski.springrestfruitshop.api.ver1.model.customer.CustomerDto;
import pl.kkowalewski.springrestfruitshop.api.ver1.model.customer.CustomerListDto;
import pl.kkowalewski.springrestfruitshop.service.customer.CustomerService;

@Controller
@RequestMapping(AppConstant.CUSTOMERS_ROOT_PATH)
public class CustomerController {

    /*------------------------ FIELDS REGION ------------------------*/
    private final CustomerService customerService;

    /*------------------------ METHODS REGION ------------------------*/
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping({AppConstant.ROOT, AppConstant.SLASH})
    public ResponseEntity<CustomerListDto> getListOfCustomers() {
        return new ResponseEntity<>(new CustomerListDto(
                customerService.getAllCustomers()), HttpStatus.OK);
    }

    @GetMapping(AppConstant.SLASH + AppConstant.ID)
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable Long id) {
        return new ResponseEntity<>(customerService.getCustomerById(id), HttpStatus.OK);
    }

    @PostMapping({AppConstant.ROOT, AppConstant.SLASH})
    public ResponseEntity<CustomerDto> createNewCustomer(@RequestBody CustomerDto customerDto) {
        return new ResponseEntity<>(customerService
                .createNewCustomer(customerDto), HttpStatus.CREATED);
    }

    @PutMapping(AppConstant.SLASH + AppConstant.ID)
    public ResponseEntity<CustomerDto> updateCustomer(
            @PathVariable Long id, @RequestBody CustomerDto customerDto) {
        return new ResponseEntity<>(customerService
                .saveCustomerByDto(id, customerDto), HttpStatus.OK);
    }

    @PatchMapping(AppConstant.SLASH + AppConstant.ID)
    public ResponseEntity<CustomerDto> patchCustomer(
            @PathVariable Long id, @RequestBody CustomerDto customerDto) {
        return new ResponseEntity<>(customerService
                .patchCustomer(id, customerDto), HttpStatus.OK);
    }

    @DeleteMapping(AppConstant.SLASH + AppConstant.ID)
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomerById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
