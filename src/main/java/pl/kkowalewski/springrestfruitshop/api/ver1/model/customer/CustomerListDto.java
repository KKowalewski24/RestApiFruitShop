package pl.kkowalewski.springrestfruitshop.api.ver1.model.customer;

import java.util.List;

public class CustomerListDto {

    /*------------------------ FIELDS REGION ------------------------*/
    private List<CustomerDto> customerDtoList;

    /*------------------------ METHODS REGION ------------------------*/
    public CustomerListDto() {
    }

    public CustomerListDto(List<CustomerDto> customerDtoList) {
        this.customerDtoList = customerDtoList;
    }

    public List<CustomerDto> getCustomerDtoList() {
        return customerDtoList;
    }

    public void setCustomerDtoList(List<CustomerDto> customerDtoList) {
        this.customerDtoList = customerDtoList;
    }
}
