package pl.kkowalewski.springrestfruitshop.api.ver1.model.customer;

import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class CustomerListDto {

    /*------------------------ FIELDS REGION ------------------------*/
    @ApiModelProperty(value = "List of CustomerDto")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CustomerListDto that = (CustomerListDto) o;

        return new EqualsBuilder()
                .append(customerDtoList, that.customerDtoList)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(customerDtoList)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("customerDtoList", customerDtoList)
                .toString();
    }
}
