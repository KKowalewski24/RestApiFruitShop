package pl.kkowalewski.springrestfruitshop.api.ver1.model.customer;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class CustomerDto {

    /*------------------------ FIELDS REGION ------------------------*/
    @ApiModelProperty(value = "Customer First Name", required = true)
    private String firstName;

    @ApiModelProperty(value = "Customer Last Name", required = true)
    private String lastName;

    @ApiModelProperty(value = "Customer URL", required = true)
    @JsonProperty("customer_url")
    private String customerUrl;

    /*------------------------ METHODS REGION ------------------------*/
    public CustomerDto() {
    }

    public CustomerDto(String firstName) {
        this.firstName = firstName;
    }

    public CustomerDto(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public CustomerDto(String firstName, String lastName, String customerUrl) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.customerUrl = customerUrl;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCustomerUrl() {
        return customerUrl;
    }

    public void setCustomerUrl(String customerUrl) {
        this.customerUrl = customerUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CustomerDto that = (CustomerDto) o;

        return new EqualsBuilder()
                .append(firstName, that.firstName)
                .append(lastName, that.lastName)
                .append(customerUrl, that.customerUrl)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(firstName)
                .append(lastName)
                .append(customerUrl)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("firstName", firstName)
                .append("lastName", lastName)
                .append("customerUrl", customerUrl)
                .toString();
    }
}
